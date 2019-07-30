package com.github.lucasjalves.projetoles.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.lucasjalves.projetoles.controller.ControllerBase;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.ConfirmacaoTicket;
import com.github.lucasjalves.projetoles.entidade.Pedido;
import com.github.lucasjalves.projetoles.entidade.Ticket;
import com.github.lucasjalves.projetoles.enums.StatusPedido;
import com.github.lucasjalves.projetoles.helper.TicketHelper;
import com.github.lucasjalves.projetoles.rns.Resultado;

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

	@GetMapping("/get/{id}")
	public Resultado getById(@PathVariable Long id) {
		return facade.consultar(new Ticket().withId(id));
	}
}
