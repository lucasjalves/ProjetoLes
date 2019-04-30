package com.github.lucasjalves.projetoles.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.ConfirmacaoTicket;
import com.github.lucasjalves.projetoles.entidade.ItemPedidoTicket;
import com.github.lucasjalves.projetoles.entidade.Pedido;
import com.github.lucasjalves.projetoles.entidade.Ticket;
import com.github.lucasjalves.projetoles.enums.StatusPedido;
import com.github.lucasjalves.projetoles.enums.StatusTicket;
import com.github.lucasjalves.projetoles.enums.TipoTicket;
import com.github.lucasjalves.projetoles.helper.TicketHelper;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.util.CalculoUtil;

@RequestMapping("/ticket")
@Controller
public class TicketController extends ControllerBase {
	private static final String CONSULTA = "painel/iframes/tickets";
	private static final String CONSULTA_ADMIN = "painel/admin/tickets";
	
	@RequestMapping("/consulta")
	public ModelAndView consulta(ModelAndView modelView) throws JsonProcessingException {
		Cliente cliente = 
				getCliente();
		
		modelView.setViewName(CONSULTA);
		modelView.addObject("tickets", mapper.writeValueAsString(cliente.getTickets()));
		return modelView;
	}
	
	@RequestMapping("/consultaAdmin")
	public ModelAndView consultaAdmin(ModelAndView modelView) throws JsonProcessingException {
		List<Ticket> tickets =
				(List<Ticket>) facade.consultar(new Ticket()).getEntidades();
		modelView.setViewName(CONSULTA_ADMIN);
		modelView.addObject("tickets", mapper.writeValueAsString(tickets));
		return modelView;
	}
	
	@RequestMapping("/trocacao")
	public ModelAndView paginaTroca(ModelAndView modelView, @RequestParam Long id) throws Exception {
		httpSession.removeAttribute("confirmacaoTicket");
		modelView.setViewName("painel/iframes/ticket/ticket");
		
		Pedido pedido = getPedido(id);
		modelView.addObject("pedido", pedido);
		return modelView;
	}
	
	@ResponseBody
	@RequestMapping("/confirmar")
	public Resultado confirmar(@RequestBody List<ItemPedidoTicket> itensParaTroca, @RequestParam String tipo, @RequestParam Long id) throws Exception {
		Pedido pedido = getPedido(id);
		Resultado validacaoTicket = TicketHelper.validarTicket(itensParaTroca, tipo, pedido);
		if(!validacaoTicket.getMensagem().isEmpty()) {
			return validacaoTicket;
		}
		ConfirmacaoTicket confirmacao = new ConfirmacaoTicket()
				.withItensPedido(itensParaTroca)
				.withPedido(TicketHelper.montarListagem(itensParaTroca, pedido))
				.withTipoTicket(TipoTicket.valueOf(tipo));
		
		confirmacao.setPedidoEfetivar(pedido);
		httpSession.setAttribute("confirmacaoTicket", confirmacao);
		return new Resultado();
	}
	
	@RequestMapping("/confirmacao")
	public ModelAndView confirmacao(ModelAndView modelView) throws Exception {
		
		modelView.setViewName("painel/iframes/ticket/confirmacao");
		ConfirmacaoTicket confirmacao = 
				(ConfirmacaoTicket) httpSession.getAttribute("confirmacaoTicket");

		Pedido p =  getPedido(confirmacao.getPedidoEfetivar().getId());
		
		Pedido pedido = TicketHelper.montarListagem(confirmacao.getItensPedido(), p);
				
		modelView.addObject("pedido", pedido);
		modelView.addObject("confirmacao", confirmacao);
		modelView.addObject("idPedido", confirmacao.getPedidoEfetivar().getId());
		return modelView;
	}
	
	@ResponseBody
	@RequestMapping("/efetivar")
	public Resultado efetivar(@RequestParam String obs) throws Exception{
		Cliente cliente = getCliente();
		ConfirmacaoTicket confirmacao = 
				(ConfirmacaoTicket) httpSession.getAttribute("confirmacaoTicket");
				
		Ticket ticket = TicketHelper.montarTicketParaEfetivar(confirmacao, obs, cliente);
		Resultado resultado = facade.salvar(ticket);
		if(!resultado.getMensagem().isEmpty()) {
			return resultado;
		}
		
		cliente.getTickets().add(ticket);
		Resultado res = facade.alterar(cliente);
		
		Pedido p = getPedido(ticket.getIdPedido());
		p.setStatus(StatusPedido.TROCADO);
		Resultado resultadoPedido = facade.alterar(p);
		if(!resultadoPedido.getMensagem().isEmpty()) {
			return res;
		}
		if(!res.getMensagem().isEmpty()) {
			return res;
		}
		httpSession.setAttribute("ticket", ticket);
		return new Resultado();
	}
	
	@RequestMapping("/detalhe")
	public ModelAndView detalhe(@RequestParam(required = false) Long id, ModelAndView modelView) throws Exception {
		Ticket t = (Ticket) httpSession.getAttribute("ticket");
		if(t == null) {
			if(id == null) {
				throw new Exception("Id necessário para consulta do ticket");
			}
			Optional<Ticket> optional = (Optional<Ticket>) facade.consultar(new Ticket().withId(id)).getEntidades().stream().findFirst();
			if(!optional.isPresent()) {
				throw new Exception("Ticket não encontrado");
			}
			t = optional.get();
		}
		Pedido p = getPedido(t.getIdPedido());
		p = TicketHelper.montarListagem(t.getItens(), p);
		httpSession.removeAttribute("ticket");
		modelView.setViewName("painel/iframes/ticket/detalhe");
		modelView.addObject("ticket", t);
		modelView.addObject("pedido", p);
		return modelView;
	}
	
	@RequestMapping("/alterarStatus")
	public String alterarStatus(@RequestParam String statusTicket, @RequestParam Long id) throws Exception {
		
		Ticket t = getTicket(id);
		if(t.getTipo().equals(TipoTicket.TROCA)) {
			return alterarStatusTroca(statusTicket, id);
		}
		Cliente cliente = (Cliente) facade.consultar(new Cliente().withId(t.getIdCliente())).getEntidades().get(0);
		StatusTicket status = StatusTicket.valueOf(statusTicket);
		if(t.getTipo().equals(TipoTicket.DEVOLUCAO) && status.equals(StatusTicket.APROVADO)) {
			Double valor = CalculoUtil.StringToDouble(cliente.getCreditoDisponivel());
			Pedido p =  getPedido(t.getIdPedido());
			Pedido pAtualizado = TicketHelper.montarListagem(t.getItens(), p);
			valor = valor + TicketHelper.gerarCredito(pAtualizado);
			valor = (valor + CalculoUtil.StringToDouble(p.getFrete())) - CalculoUtil.StringToDouble(p.getDesconto());
			cliente.setCreditoDisponivel(CalculoUtil.formatMoney(valor));
			Resultado resultadoCliente = facade.alterar(cliente);
			
			if(!resultadoCliente.getMensagem().isEmpty()) {
				throw new Exception("Falha ao atualizar crédito do cliente devido a RNS " + resultadoCliente.getMensagem().get(0));
			}
		}
		t.setStatus(status);
		Resultado resultadoTicket = facade.alterar(t);
		if(!resultadoTicket.getMensagem().isEmpty()) {
			throw new Exception("Falha ao atualizar ticket devido a RNS " + resultadoTicket.getMensagem().get(0));
		}
		return "forward:/ticket/consultaAdmin";
	}
	
	public String alterarStatusTroca(String statusTicket, Long id) throws Exception {
		Ticket t = getTicket(id);
		t.setStatus(StatusTicket.valueOf(statusTicket));
		Resultado res = facade.alterar(t);
		if(!res.getMensagem().isEmpty()) {
			throw new Exception(res.getMensagem().get(0));
		}
		return "forward:/ticket/consultaAdmin";
	}
	@SuppressWarnings("unchecked")
	private Ticket getTicket(Long id) throws Exception {
		Optional<Ticket> ticket = 
				(Optional<Ticket>) facade.consultar(new Ticket().withId(id)).getEntidades()
				.stream().findFirst();
		
		if(!ticket.isPresent()) {
			throw new Exception("Ticket não encontrado " + id);
		}
		
		return ticket.get();
	}
	
	private Pedido getPedido(Long id) throws Exception {
		Optional<Pedido> optional = (Optional<Pedido>) facade.consultar(new Pedido().withId(id)).getEntidades().stream().findFirst();
		if(!optional.isPresent()) {
			throw new Exception("Pedido não encontrado para troca!");
		}
		return optional.get();
	}
}
