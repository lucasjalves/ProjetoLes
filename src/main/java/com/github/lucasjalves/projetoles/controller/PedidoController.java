package com.github.lucasjalves.projetoles.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.lucasjalves.projetoles.entidade.Carrinho;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.Endereco;
import com.github.lucasjalves.projetoles.entidade.Pedido;
import com.github.lucasjalves.projetoles.enums.StatusPedido;
import com.github.lucasjalves.projetoles.helper.ClienteHelper;
import com.github.lucasjalves.projetoles.helper.EfetivacaoPedidoHelper;
import com.github.lucasjalves.projetoles.helper.EstoqueHelper;
import com.github.lucasjalves.projetoles.helper.PedidoHelper;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.util.CalculoUtil;

@Controller
@RequestMapping("/pedido")
public class PedidoController extends ControllerBase{

	private static final String EFETIVACAO = "pedido/efetivacao";
	private static final String CONSULTA = "painel/iframes/pedidos";
	private static final String CONSULTA_ADMIN = "painel/admin/pedidos";
	
	@Autowired
	private PedidoHelper pedidoHelper;
	
	@Autowired
	private EstoqueHelper estoqueHelper;
	
	@ResponseBody
	@RequestMapping("/confirmar")
	public Resultado confirmar(@ModelAttribute Endereco endereco) throws Exception {
		Cliente cliente =
				getCliente();
		
		Carrinho carrinho =
				(Carrinho) httpSession.getAttribute("carrinho");
		
		if(cliente == null) {
			return new Resultado("Cliente não logado!");
		}
		if(carrinho == null) {
			return new Resultado("Carrinho inexistente!");
		}
		if(carrinho.getItensCarrinho().isEmpty()) {
			return new Resultado("Itens no carrinho vazios!");
		}
		Pedido pedido = pedidoHelper.gerarPedido(endereco, carrinho, cliente);
		Resultado resultado = facade.salvar(pedido);
		if(resultado.getMensagem().isEmpty()) {
			try {
				estoqueHelper.atualizarEstoque(carrinho);
			}catch(Exception e) {
				return new Resultado(e.getMessage());
			}
			
			Pedido p = (Pedido) resultado.getEntidades().get(0);
			cliente.getPedidos().add(pedido);
			resultado = facade.alterar(cliente);
			cliente.getPedidos().remove(pedido);
			
			httpSession.setAttribute("pedido", p);
			httpSession.removeAttribute("carrinho");
		}
		
		return resultado;
				
	}
	
	@RequestMapping("/confirmacao")
	public ModelAndView confirmacao(ModelAndView modelView, @RequestParam Long id) throws Exception {
		Pedido pedido = (Pedido)httpSession.getAttribute("pedido");
	
		Cliente cliente = getCliente();
		if(pedido != null) {
			final Pedido pedidoClone = pedido;
			
			Optional<Pedido> pedidoConfirmado = cliente.getPedidos()
					.stream().filter(p -> p.equalsPedido(pedidoClone))
					.findFirst();
			
			if(!pedidoConfirmado.isPresent()) {
				throw new Exception("Pedido não encontrado");
			}
			
			pedido = pedidoConfirmado.get();
		}
		if(pedido == null) {
			pedido = (Pedido) facade.consultar(new Pedido().withId(id)).getEntidades().get(0);
		}
		httpSession.removeAttribute("pedido");
		modelView.addObject("pedido", pedido);
		modelView.addObject("cliente", cliente);
		modelView.addObject("creditoZerado", CalculoUtil.isValorZerado(cliente.getCreditoDisponivel()));
		return modelView;
	}
	
	@ResponseBody
	@RequestMapping("/efetivar")
	public Resultado efetivar(@RequestBody Pedido pedido) throws Exception {
		
		Cliente cliente = getCliente();
		if(cliente.getCartoes().isEmpty()) {
			return new Resultado("Você deve cadastrar um cartão para efetivar");
		}
		Resultado resultado = 
				facade.consultar(pedido);
		
		Optional<Pedido> pedidoConsulta =
				(Optional<Pedido>) resultado.getEntidades().stream().findFirst();
		
		if(!pedidoConsulta.isPresent()) {
			return new Resultado("Pedido " + pedido.getId() + " não encontrado");
		}
		
		Pedido pedidoAEfetivar = pedidoConsulta.get();
		pedidoAEfetivar.setCartoes(pedido.getCartoes());
		pedido.setTotalCompra(pedidoConsulta.get().getTotalCompra());
		Resultado resultadoValidacao = 
				EfetivacaoPedidoHelper.validarPedido(pedido);
		
		if(!resultadoValidacao.getMensagem().isEmpty()) {
			return resultadoValidacao;
		}
		
		Pedido pedidoEfetivacao = 
				EfetivacaoPedidoHelper.setarDadosParaEfetivar(pedidoAEfetivar, pedido, cliente);
		
		String credito = ClienteHelper.atualizarCreditoUtilizado(pedidoAEfetivar, cliente);
		
		if(CalculoUtil.isValorZerado(pedidoEfetivacao.getCreditoUtilizado())) {
			return facade.alterar(pedidoEfetivacao);
		}
		
		Resultado alteracaoPedido = facade.alterar(pedidoEfetivacao);
		if(!alteracaoPedido.getMensagem().isEmpty()) {
			return alteracaoPedido;
		}
		Cliente c = (Cliente) facade.consultar(cliente).getEntidades().stream().findFirst().get();
		c.setCreditoDisponivel(credito);
		
		Resultado resultadoAlteracaoCliente =  facade.alterar(c);
		httpSession.setAttribute("cliente", getCliente());
		return resultadoAlteracaoCliente;
	}
	
	@RequestMapping("/efetivacao")
	public ModelAndView efetivacao(ModelAndView modelView, @RequestParam Long id) throws Exception {
		Resultado resultado = 
				facade.consultar(new Pedido().withId(id));
		
		Optional<Pedido> buscaPedido = 
				(Optional<Pedido>) resultado.getEntidades().stream().findFirst();
		
		if(!buscaPedido.isPresent()) {
			throw new Exception("Pedido " + id + " não encontrado");
		}
		
		modelView.addObject("pedido", buscaPedido.get());
		modelView.setViewName(EFETIVACAO);
		modelView.addObject("creditoZerado", CalculoUtil.isValorZerado(buscaPedido.get().getCreditoUtilizado()));
		return modelView;
	}
	
	@RequestMapping("/detalhe")
	public ModelAndView detalhe(ModelAndView modelView, @RequestParam Long id) throws Exception {
		Resultado resultado = 
				facade.consultar(new Pedido().withId(id));
		
		Optional<Pedido> buscaPedido = 
				(Optional<Pedido>) resultado.getEntidades().stream().findFirst();
		
		if(!buscaPedido.isPresent()) {
			throw new Exception("Pedido " + id + " não encontrado");
		}
		
		modelView.addObject("pedido", buscaPedido.get());
		modelView.setViewName("pedido/detalhe");
		modelView.addObject("creditoZerado", CalculoUtil.isValorZerado(buscaPedido.get().getCreditoUtilizado()));
		return modelView;
	}
	
	
	@RequestMapping("/consulta")
	public ModelAndView consulta(ModelAndView modelView) throws JsonProcessingException {
		
		Cliente cliente = getCliente();
		
		modelView.setViewName(CONSULTA);
		modelView.addObject("pedidos", mapper.writeValueAsString(cliente.getPedidos()));
		return modelView;
	}
	
	@RequestMapping("/consultaAdmin")
	public ModelAndView consultaAdmin(ModelAndView modelView) throws JsonProcessingException {
		modelView.setViewName(CONSULTA_ADMIN);
		List<Pedido> pedidos = (List<Pedido>) facade.consultar(new Pedido()).getEntidades();
		modelView.addObject("pedidos", mapper.writeValueAsString(pedidos));
		return modelView;
	}
		
	@SuppressWarnings("unchecked")
	@RequestMapping("/alterarStatus")
	public String alterarStatus(@RequestParam Long id, @RequestParam String statusPedido) throws Exception {
		Optional<Pedido> pedidoConsulta = (Optional<Pedido>) facade.consultar(new Pedido().withId(id)).getEntidades()
				.stream().findFirst();
		
		if(pedidoConsulta.isEmpty()) {
			throw new Exception("Pedido com não encontrado " +id);
		}
		
		Pedido p = pedidoConsulta.get();
		p.setStatus(StatusPedido.valueOf(statusPedido));
		Resultado resultado = facade.alterar(p);
		if(!resultado.getMensagem().isEmpty()) {
			throw new Exception(resultado.getMensagem().get(0));
		}
		
		return "forward:/pedido/consultaAdmin";
	}
	
}
