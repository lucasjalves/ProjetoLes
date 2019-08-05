package com.github.lucasjalves.projetoles.controller.rest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.lucasjalves.projetoles.controller.ControllerBase;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.ConfirmacaoTicket;
import com.github.lucasjalves.projetoles.entidade.Pedido;
import com.github.lucasjalves.projetoles.entidade.Ticket;
import com.github.lucasjalves.projetoles.enums.StatusPedido;
import com.github.lucasjalves.projetoles.enums.StatusTicket;
import com.github.lucasjalves.projetoles.enums.TipoTicket;
import com.github.lucasjalves.projetoles.helper.TicketHelper;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.util.CalculoUtil;

@RestController
@RequestMapping("rest/ticket")
@CrossOrigin
public class RestTicketController extends ControllerBase{

	@GetMapping("/cliente/{cpf}")
	public Resultado getTicketsByCliente(@PathVariable String cpf) {
		Resultado resultadoConsultaCliente = 
				facade.consultar(new Cliente().withCpf(cpf));
		
		Cliente cliente = (Cliente) resultadoConsultaCliente.getEntidades().get(0);
		Resultado resultadoConsultaTickets = new Resultado();
		resultadoConsultaTickets.setEntidades(cliente.getTickets());
		
		return resultadoConsultaTickets;
	}
	
	@GetMapping("/todos")
	public Resultado getTodos() {
		Resultado resultadoConsultaTicket = this.facade.consultar(new Ticket());
		List<Ticket> listaTicket = (List<Ticket>) resultadoConsultaTicket.getEntidades();
		resultadoConsultaTicket.setEntidades(listaTicket
				.stream()
				.sorted(Comparator.comparing(Ticket::getId).reversed())
				.collect(Collectors.toList()));
		return resultadoConsultaTicket;
	}
	@PostMapping("/efetivar")
	public Resultado confirmar(@RequestBody Ticket ticketRequest) throws Exception {
		Resultado resultado = facade.consultar(new Pedido().withId(ticketRequest.getIdPedido()));
		Pedido pedido = (Pedido) resultado.getEntidades().get(0);
		
		Resultado resultadoConsultaCliente = facade.consultar(new Cliente().withId(ticketRequest.getIdCliente()));
		
		Cliente cliente = (Cliente) resultadoConsultaCliente.getEntidades().get(0);
		Resultado validacaoTicket = TicketHelper.validarTicket(ticketRequest.getItens(), ticketRequest.getTipo().toString(), pedido);
		if(!validacaoTicket.getMensagem().isEmpty()) {
			return validacaoTicket;
		}
		ConfirmacaoTicket confirmacao = new ConfirmacaoTicket()
				.withItensPedido(ticketRequest.getItens())
				.withPedido(TicketHelper.montarListagem(ticketRequest.getItens(), pedido))
				.withTipoTicket(ticketRequest.getTipo());
		
		confirmacao.setPedidoEfetivar(pedido);
		
		Ticket ticket = TicketHelper.montarTicketParaEfetivar(confirmacao, ticketRequest.getObs(), cliente);
		Resultado resultadoValidarTicket = facade.salvar(ticket);
		if(!resultadoValidarTicket.getMensagem().isEmpty()) {
			return resultado;
		}
		
		cliente.getTickets().add(ticket);
		Resultado resultadoCadastroTicket = facade.alterar(cliente);
		if(!resultadoCadastroTicket.getMensagem().isEmpty()) {
			return resultadoCadastroTicket;
		}
		
		pedido.setStatus(StatusPedido.TROCADO);
		Resultado resultadoPedido = facade.alterar(pedido);
		if(!resultadoPedido.getMensagem().isEmpty()) {
			return resultadoPedido;
		}
		Cliente clienteAlterado = (Cliente) resultadoCadastroTicket.getEntidades().get(0);
		Resultado resultadoRetornoSucesso = new Resultado();
		List<Ticket> listaComUltimoTicket = new ArrayList<>();
		listaComUltimoTicket.add(clienteAlterado.getTickets().get(clienteAlterado.getTickets().size() - 1));
		resultadoRetornoSucesso.setEntidades(listaComUltimoTicket);
		return resultadoRetornoSucesso;
	}

	@PostMapping("/aprovar/{id}")
	public Resultado aprovar(@PathVariable Long id, @RequestParam(required=true) String status, @RequestParam(required=true) Long idCliente) {
		Resultado resultadoConsultaTicket =  facade.consultar(new Ticket().withId(id));
		Resultado resultadoConsultaCliente = facade.consultar(new Cliente().withId(idCliente));

		
		StatusTicket statusAAlterar = StatusTicket.valueOf(status);
		
		Ticket ticket = (Ticket) resultadoConsultaTicket.getEntidades().get(0);
		Cliente cliente = (Cliente) resultadoConsultaCliente.getEntidades().get(0);
		
		Resultado resultadoConsultaPedido = facade.consultar(new Pedido().withId(ticket.getIdPedido()));
		Pedido pedido = (Pedido) resultadoConsultaPedido.getEntidades().get(0);
		if(ticket.getTipo().equals(TipoTicket.DEVOLUCAO) && statusAAlterar.equals(StatusTicket.APROVADO)) {
			Double valor = CalculoUtil.StringToDouble(cliente.getCreditoDisponivel());
			Pedido pAtualizado = TicketHelper.montarListagem(ticket.getItens(), pedido);
			valor = valor + TicketHelper.gerarCredito(pAtualizado);
			valor = (valor + CalculoUtil.StringToDouble(pedido.getFrete())) - CalculoUtil.StringToDouble(pedido.getDesconto());
			cliente.setCreditoDisponivel(CalculoUtil.formatMoney(valor));
			Resultado resultadoCliente = facade.alterar(cliente);
			if(!resultadoCliente.getMensagem().isEmpty()) {
				return resultadoCliente;
			}
		} 
		ticket.setStatus(statusAAlterar);
		
		return facade.alterar(ticket);
	}
	@GetMapping("/get/{id}")
	public Resultado getById(@PathVariable Long id) {
		return facade.consultar(new Ticket().withId(id));
	}
}
