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
import com.github.lucasjalves.projetoles.entidade.ItemPedido;
import com.github.lucasjalves.projetoles.entidade.Pedido;
import com.github.lucasjalves.projetoles.entidade.Ticket;
import com.github.lucasjalves.projetoles.enums.TipoTicket;
import com.github.lucasjalves.projetoles.helper.TicketHelper;
import com.github.lucasjalves.projetoles.rns.Resultado;

@RequestMapping("/ticket")
@Controller
public class TicketController extends ControllerBase {
	private static final String CONSULTA = "painel/iframes/tickets";
	private static final String CONSULTA_ADMIN = "painel/admin/tickets";
	
	@RequestMapping("/consulta")
	public ModelAndView consulta(ModelAndView modelView) {
		modelView.setViewName(CONSULTA);
		return modelView;
	}
	
	@RequestMapping("/consultaAdmin")
	public ModelAndView consultaAdmin(ModelAndView modelView) {
		modelView.setViewName(CONSULTA_ADMIN);
		return modelView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/trocacao")
	public ModelAndView paginaTroca(ModelAndView modelView, @RequestParam Long id) throws Exception {
		httpSession.removeAttribute("confirmacaoTicket");
		modelView.setViewName("painel/iframes/ticket/ticket");
		Resultado resultado = facade.consultar(new Pedido().withId(id));
		Optional<Pedido> optional = (Optional<Pedido>) resultado.getEntidades().stream().findFirst();
		if(optional.isEmpty()) {
			throw new Exception("Pedido não encontrado: " + id);
		}
		
		Pedido pedido = optional.get();
		modelView.addObject("pedido", pedido);
		return modelView;
	}
	
	@ResponseBody
	@RequestMapping("/confirmar")
	public Resultado confirmar(@RequestBody List<ItemPedido> itensParaTroca, @RequestParam String tipo, @RequestParam Long id) throws Exception {
		Optional<Pedido> optional = (Optional<Pedido>) facade.consultar(new Pedido().withId(id)).getEntidades().stream().findFirst();
		if(optional.isEmpty()) {
			throw new Exception("Pedido não encontrado para troca!");
		}
		Pedido pedido = optional.get();
		Resultado validacaoTicket = TicketHelper.validarTicket(itensParaTroca, tipo, pedido);
		if(!validacaoTicket.getMensagem().isEmpty()) {
			return validacaoTicket;
		}
		ConfirmacaoTicket confirmacao = new ConfirmacaoTicket()
				.withItensPedido(itensParaTroca)
				.withPedido(pedido)
				.withTipoTicket(TipoTicket.valueOf(tipo));
		
		httpSession.setAttribute("confirmacaoTicket", confirmacao);
		return new Resultado();
	}
	
	@RequestMapping("/confirmacao")
	public ModelAndView confirmacao(ModelAndView modelView) throws CloneNotSupportedException {
		modelView.setViewName("painel/iframes/ticket/confirmacao");
		ConfirmacaoTicket confirmacao = 
				(ConfirmacaoTicket) httpSession.getAttribute("confirmacaoTicket");
		
		Pedido pedido = TicketHelper.montarTicketListagem(confirmacao);
				
		modelView.addObject("pedido", pedido);
		modelView.addObject("confirmacao", confirmacao);
		return modelView;
	}
	
	@ResponseBody
	@RequestMapping("/efetivar")
	public Resultado efetivar(@RequestParam String obs) throws JsonProcessingException, CloneNotSupportedException{
		ConfirmacaoTicket confirmacao = 
				(ConfirmacaoTicket) httpSession.getAttribute("confirmacaoTicket");
				
		Ticket ticket = TicketHelper.montarTicketParaEfetivar(confirmacao, obs);
		Resultado resultado = facade.salvar(ticket);
		if(!resultado.getMensagem().isEmpty()) {
			return resultado;
		}
		Cliente cliente = getCliente();
		cliente.getTickets().add(ticket);
		Resultado res = facade.alterar(cliente);
		
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
			if(optional.isEmpty()) {
				throw new Exception("Ticket não encontrado");
			}
			t = optional.get();
		}
		httpSession.removeAttribute("ticket");
		modelView.setViewName("painel/iframes/ticket/detalhe");
		modelView.addObject("ticket", t);
		modelView.addObject("pedido", t.getPedido());
		return modelView;
	}
}
