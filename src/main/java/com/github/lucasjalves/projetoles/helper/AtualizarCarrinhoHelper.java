package com.github.lucasjalves.projetoles.helper;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.lucasjalves.projetoles.entidade.Carrinho;
import com.github.lucasjalves.projetoles.entidade.Cupom;
import com.github.lucasjalves.projetoles.entidade.ItemCarrinho;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.util.DataUtil;

public final class AtualizarCarrinhoHelper {
	private AtualizarCarrinhoHelper() {
		
	}
	
	public static Carrinho atualizarSessaoInformacoes(Carrinho carrinho, List<Produto> produtosBanco, List<Cupom> cupons) {
		Iterator<ItemCarrinho> it = carrinho.getItensCarrinho().iterator();
		if(carrinho.getCupom() != null) {
			Cupom cupom = cupons.stream()
					.filter(c -> c.equals(carrinho.getCupom()))
					.collect(Collectors.toList())
					.get(0);
			
			LocalDate dataCupom = null;
			try {
				dataCupom = DataUtil.formatarData(cupom.getDataVencimento());
			} catch (Exception e) {
				e.printStackTrace();
			}
			carrinho.setStatusCupom(cupom.getStatus() && dataCupom.isAfter(LocalDate.now()));
			carrinho.setCupom(cupom);
		}
		while(it.hasNext()) {
			ItemCarrinho item = it.next();
			Produto produto = produtosBanco.stream()
					.filter(p -> item.getProduto().equals(p))
					.collect(Collectors.toList())
					.get(0);
			
			if(produto.getEstoque() < item.getQuantidade()) {
				item.setQuantidade(produto.getEstoque());
				item.getProduto().setQuantidadeSelecionada(produto.getEstoque());
				item.setQuantidadeAtualizada(true);
				CarrinhoHelper.valorizarObjetoItemCarrinho(produto, item, produto.getEstoque(), carrinho);
				
			}
			item.setStatus(produto.getAtivo() && !produto.getEstoque().equals(0));
			if(!item.isStatus()) {
				item.getProduto().setQuantidadeSelecionada(0);
				item.setQuantidade(0);
				CarrinhoHelper.valorizarObjetoItemCarrinho(produto, item, 0, carrinho);
			}
			
		}
		

		
		return carrinho;
	}
	
	public static Carrinho atualizarSessao(Carrinho carrinho) {
		if(carrinho.getCupom() != null) {
			if(!carrinho.isStatusCupom()) {
				carrinho.setCupom(null);
			}
		}
		if(!carrinho.getItensCarrinho().isEmpty()) {
			Set<ItemCarrinho> setFiltrado = carrinho.getItensCarrinho()
					.stream()
					.filter(i -> i.isStatus())
					.collect(Collectors.toSet());
			
			carrinho.setItensCarrinho(setFiltrado);
			setFiltrado.forEach(i -> {
				i.setQuantidadeAtualizada(false);
			});
		}
		return carrinho;
	}
	
}
