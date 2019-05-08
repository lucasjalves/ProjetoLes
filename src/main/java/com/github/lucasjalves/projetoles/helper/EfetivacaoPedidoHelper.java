package com.github.lucasjalves.projetoles.helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.lucasjalves.projetoles.entidade.CartaoCredito;
import com.github.lucasjalves.projetoles.entidade.CartaoCreditoPagamento;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.Pedido;
import com.github.lucasjalves.projetoles.enums.StatusPedido;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.util.CalculoUtil;

public final class EfetivacaoPedidoHelper {

	public static Resultado validarPedido(Pedido pedido, Cliente cliente) {
		Double valorTotal = CalculoUtil.StringToDouble(pedido.getTotalCompra());
		Double creditoUtilizado = CalculoUtil.StringToDouble(pedido.getCreditoUtilizado());
		boolean creditoNaoUtilizado = CalculoUtil.isValorZerado(pedido.getCreditoUtilizado());
		Optional<CartaoCreditoPagamento> cartoes = pedido.getCartoes().stream().filter(c -> c.getId() != -1).findFirst();
		if (creditoUtilizado >= valorTotal) {
			return new Resultado();
		}
		
		Resultado r = validarCvvCartoes(cliente, pedido);
		if(!r.getMensagem().isEmpty()) {
			return r;
		}

		if(!cartoes.isPresent() && (creditoUtilizado < valorTotal)) {
			return new Resultado("Você deve selecionar um cartão para pagamento");
		}
		if(!pedido.getCartoes().isEmpty()) {
			Set<Long> ids = pedido.getCartoes()
					.stream().map(c -> c.getId())
					.collect(Collectors.toCollection(HashSet::new));
			
			if(ids.size() != pedido.getCartoes().size()) {
				return new Resultado("Você deve selecionar mais de um cartão diferente.");
			}
		}
		
		List<String> valores = pedido.getCartoes()
				.stream()
				.map(c -> c.getValor())
				.collect(Collectors.toList());
		Double valor = 0.0;
		for(String v: valores) {
			try {
				valor = valor + CalculoUtil.StringToDouble(v);
			}catch(Exception e) {
				return new Resultado("Você deve preencher um valor para cada cartão");
			}
			
		}
		if(!creditoNaoUtilizado) {
		
			if(!cartoes.isPresent()) {
				valor = 0.00;
			} else {
				if(pedido.getCartoes().size() != 1) {
					valor = CalculoUtil.StringToDouble(pedido.getCreditoUtilizado()) + valor;
				} else {
					valor = CalculoUtil.StringToDouble(pedido.getCreditoUtilizado()) + (valorTotal - CalculoUtil.StringToDouble(pedido.getCreditoUtilizado()));
				}
				
			}
		}

		if(!valor.equals(valorTotal)) {
			return new Resultado("O valor a pagar por cartão não está igual ao total. Verifique os valores a pagar");
		}
	
		return new Resultado();
	}

	private static Resultado validarCvvCartoes(Cliente cliente, Pedido pedidoParaEfetivar) {
		List<CartaoCredito> listaCartoesCliente = cliente.getCartoes();
		List<CartaoCreditoPagamento> listaCartoesPagamento = pedidoParaEfetivar.getCartoes();
		
		for(CartaoCreditoPagamento cartao: listaCartoesPagamento) {
			Long idCartao = cartao.getIdCartao();
			CartaoCredito cartaoCliente = listaCartoesCliente.stream()
					.filter(cartaoLista -> cartaoLista.getId().equals(idCartao))
					.collect(Collectors.toList())
					.get(0);
			String finalCartao = cartaoCliente.getNumero().substring(cartaoCliente.getNumero().length() - 4);
			if(!cartaoCliente.getCvv().equals(cartao.getCvv())) {
				return new Resultado("Verifique o CVV do cartão com final " + finalCartao);
			}
		}
		return new Resultado();
	}
	public static Pedido setarDadosParaEfetivar(Pedido pedidoAEfetivar, Pedido pedidoDadosPagamento, Cliente cliente) throws Exception {
		pedidoAEfetivar.setCreditoUtilizado(pedidoDadosPagamento.getCreditoUtilizado());
		Double creditoUtilizado = CalculoUtil.StringToDouble(pedidoDadosPagamento.getCreditoUtilizado());
		Double totalCompra = CalculoUtil.StringToDouble(pedidoAEfetivar.getTotalCompra());
		if(creditoUtilizado < totalCompra) {
			pedidoAEfetivar.setCartoes(mapearCartoes(cliente, pedidoDadosPagamento.getCartoes()));
		} else {
			pedidoAEfetivar.setCartoes(new ArrayList<>());
			pedidoAEfetivar.setCreditoUtilizado(pedidoAEfetivar.getTotalCompra());
		}
		
		
		pedidoAEfetivar.setStatus(StatusPedido.PAGO);
		pedidoAEfetivar.setIdCliente(cliente.getId());
		return pedidoAEfetivar;
	}
	
	private static List<CartaoCreditoPagamento> mapearCartoes(Cliente cliente, List<CartaoCreditoPagamento> cartoes) throws Exception {
		for(CartaoCreditoPagamento cartao: cartoes) {
			Optional<CartaoCredito> cartaoCliente = cliente.getCartoes().stream()
				.filter(c -> cartao.getId().equals(c.getId()))
				.findFirst();
			
			if(!cartaoCliente.isPresent()) {
				throw new Exception("Cartão não encontrado");
			}
			
			cartao.setBandeira(cartaoCliente.get().getBandeira());
			cartao.setCvv(cartaoCliente.get().getCvv());
			cartao.setDtVencimento(cartaoCliente.get().getDtVencimento());
			cartao.setNumero(cartaoCliente.get().getNumero());
			cartao.setIdCartao(cartao.getId());
			cartao.setId(null);
			
		}
		
		return cartoes;
	}
}
