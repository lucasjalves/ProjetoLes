package com.github.lucasjalves.projetoles.helper;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.ConfirmacaoTicket;
import com.github.lucasjalves.projetoles.entidade.ItemPedido;
import com.github.lucasjalves.projetoles.entidade.ItemPedidoTicket;
import com.github.lucasjalves.projetoles.entidade.Pedido;
import com.github.lucasjalves.projetoles.entidade.Ticket;
import com.github.lucasjalves.projetoles.enums.StatusTicket;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.util.CalculoUtil;
import com.github.lucasjalves.projetoles.util.FormatadorDataUtil;

public final class TicketHelper {
	private TicketHelper() {
	}
	
	public static Resultado validarTicket(List<ItemPedidoTicket> itensParaTroca, String tipo, Pedido pedido) throws Exception {
		 Optional<ItemPedidoTicket> it = itensParaTroca.stream().filter(i -> i.getQuantidade() > 0).findAny();
		 if(!it.isPresent()) {
			 return new Resultado("Selecione algum item para troca");
		 }
		 Map<Long, Integer> mapaProdutos =  new HashMap<>();
		 List<ItemPedido> itensPedido = pedido.getItensPedido();
		 for (ItemPedido item: itensPedido) {
			 mapaProdutos.put(item.getId(), item.getQuantidade());
		 }
		 
		 for(ItemPedidoTicket item: itensParaTroca) {
			Integer quantidadeTotalPedido = mapaProdutos.get(item.getIdItem());
			if(quantidadeTotalPedido == null) {
				return new Resultado("Quantidade não encontrada para o item " + item.getId());
			}
			
			if(item.getQuantidade() > quantidadeTotalPedido || item.getQuantidade() < 0) {
				return new Resultado("Quantidade de produto para a troca está maior do que no pedido, verifique as quantidades para troca!");
			}
			
		 }
		 return new Resultado();
	}
	
	public static Pedido montarListagem(List<ItemPedidoTicket> itensParaTroca, Pedido pedido) {
		Pedido p = new Pedido();
		p.setId(p.getId());
		Map<Long, Integer> mapa = new HashMap<>();
		for(ItemPedidoTicket item: itensParaTroca) {
			mapa.put(item.getIdItem(), item.getQuantidade());
		}
		Iterator<Long> ids = mapa.keySet().iterator();
		while(ids.hasNext()) {
			Long id = ids.next();
			ItemPedido i = pedido.getItensPedido().stream().filter(item -> item.getId().equals(id))
					.collect(Collectors.toList()).get(0);
			
			i.setQuantidade(mapa.get(id));
			p.getItensPedido().add(i);
		}
		
		return p;
	}

	
	public static Ticket montarTicketParaEfetivar(ConfirmacaoTicket confirmacao, String obs, Cliente cliente) throws CloneNotSupportedException {
		Ticket ticket = new Ticket();
		List<ItemPedidoTicket> itens = confirmacao.getItensPedido()
				.stream()
				.filter(i -> i.getQuantidade() > 0)
				.collect(Collectors.toList());
		ticket.setItens(itens);
		ticket.setIdPedido(confirmacao.getPedidoEfetivar().getId());
		ticket.setTipo(confirmacao.getTipoTicket());
		ticket.setObs(obs);
		String dtHora = FormatadorDataUtil.dataFormatada(LocalDateTime.now());
		ticket.setDtPedido(dtHora.split("T")[0]);
		ticket.setHora(dtHora.split("T")[1]);
		ticket.setStatus(StatusTicket.SOLICITADO);
		ticket.setIdCliente(cliente.getId());
		return ticket;
	}
	
	public static Double gerarCredito(Pedido pedido) {
		Double vTotal = 0.00;
		for(ItemPedido item: pedido.getItensPedido()) {
			if(item.getQuantidade() > 0) {
				vTotal += CalculoUtil.StringToDouble(item.getProduto().getPrecoVenda()) * item.getQuantidade();
			}
		}
		
		return vTotal;
	}
}
