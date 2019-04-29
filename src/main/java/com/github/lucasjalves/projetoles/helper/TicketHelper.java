package com.github.lucasjalves.projetoles.helper;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.lucasjalves.projetoles.entidade.ConfirmacaoTicket;
import com.github.lucasjalves.projetoles.entidade.ItemPedido;
import com.github.lucasjalves.projetoles.entidade.Pedido;
import com.github.lucasjalves.projetoles.entidade.Ticket;
import com.github.lucasjalves.projetoles.enums.StatusTicket;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.util.FormatadorDataUtil;

public final class TicketHelper {
	private TicketHelper() {
	}
	
	public static Resultado validarTicket(List<ItemPedido> itensParaTroca, String tipo, Pedido pedido) throws Exception {
		 Optional<ItemPedido> it = itensParaTroca.stream().filter(i -> i.getQuantidade() > 0).findAny();
		 if(it.isEmpty()) {
			 return new Resultado("Selecione algum item para troca");
		 }
		 Map<Long, Integer> mapaProdutos =  new HashMap<>();
		 List<ItemPedido> itensPedido = pedido.getItensPedido();
		 for (ItemPedido item: itensPedido) {
			 mapaProdutos.put(item.getId(), item.getQuantidade());
		 }
		 
		 for(ItemPedido item: itensParaTroca) {
			Integer quantidadeTotalPedido = mapaProdutos.get(item.getId());
			if(quantidadeTotalPedido == null) {
				throw new Exception("Quantidade não encontrada para o item " + item.getId());
			}
			
			if(item.getQuantidade() > quantidadeTotalPedido || item.getQuantidade() < 0) {
				return new Resultado("Quantidade de produto para a troca está maior do que no pedido, verifique as quantidades para troca!");
			}
			
		 }
		 return new Resultado();
	}
	
	public static Pedido montarTicketListagem(ConfirmacaoTicket confirmacao) throws CloneNotSupportedException {
		Pedido p = (Pedido) confirmacao.getPedido().clone();
		Map<Long, Integer> map = new HashMap<>();
		for (ItemPedido i: confirmacao.getItensPedido()) {
			map.put(i.getId(), i.getQuantidade());
		}
		Set<Long> ids = map.keySet();
		Iterator<Long> it = ids.iterator();
		
		while(it.hasNext()) {
			Long i = it.next();
			ItemPedido item = confirmacao.getPedido().getItensPedido().stream().filter(ite -> ite.getId().equals(i))
					.collect(Collectors.toList()).get(0);
			
			item.setQuantidade(map.get(i));
		}
		
		return p;
	}
	
	public static Pedido montarTicketListagem(Ticket ticket) throws CloneNotSupportedException {
		Pedido p = (Pedido) ticket.getPedido().clone();
		p.setId(null);
		Map<Long, Integer> map = new HashMap<>();
		for (ItemPedido i: ticket.getItens()) {
			map.put(i.getId(), i.getQuantidade());
		}
		Set<Long> ids = map.keySet();
		Iterator<Long> it = ids.iterator();
		
		while(it.hasNext()) {
			Long i = it.next();
			ItemPedido item = ticket.getPedido().getItensPedido().stream().filter(ite -> ite.getId().equals(i))
					.collect(Collectors.toList()).get(0);
			
			item.setQuantidade(map.get(i));
		}
		
		return p;
	}
	
	public static Ticket montarTicketParaEfetivar(ConfirmacaoTicket confirmacao, String obs) throws CloneNotSupportedException {
		Ticket ticket = new Ticket();
		ticket.setItens(confirmacao.getItensPedido());
		ticket.setPedido(confirmacao.getPedido());
		ticket.getPedido().setId(null);
		ticket.getPedido().getEndereco().setId(null);
		ticket.getPedido().getItensPedido().stream().forEach(i -> {
			i.getProduto().setId(null);
			i.setId(null);
		});
		ticket.setTipo(confirmacao.getTipoTicket());
		ticket.setObs(obs);
		String dtHora = FormatadorDataUtil.dataFormatada(LocalDateTime.now());
		ticket.setDtPedido(dtHora.split("T")[0]);
		ticket.setHora(dtHora.split("T")[1]);
		ticket.setStatus(StatusTicket.SOLICITADO);
		return ticket;
	}
}
