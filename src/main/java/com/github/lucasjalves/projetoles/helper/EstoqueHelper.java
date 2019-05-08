package com.github.lucasjalves.projetoles.helper;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.entidade.Carrinho;
import com.github.lucasjalves.projetoles.entidade.ItemCarrinho;
import com.github.lucasjalves.projetoles.entidade.ItemPedido;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.facade.Facade;

@Component
public class EstoqueHelper {

	@Autowired
	private Facade facade;
	
	public void atualizarEstoque(Carrinho carrinho) throws Exception {
		Iterator<ItemCarrinho> it =  carrinho.getItensCarrinho().iterator();
		while(it.hasNext()) {
			ItemCarrinho item = it.next();
			Produto produto = item.getProduto();
			produto.setEstoque(produto.getEstoque() - item.getQuantidade());
			if(!facade.alterar(produto).getMensagem().isEmpty()) {
				throw new Exception("Falha ao atualizar quantidade no estoque");
			}
		}
	}
	
	public void atualizarQuantidadeEstoque(List<ItemPedido> itens) {
		for(ItemPedido item: itens) {
			Long id = item.getProduto().getIdProduto();
			Produto p = 
					(Produto) facade.consultar(new Produto().withId(id)).getEntidades().get(0);
			
			p.setEstoque(p.getEstoque() + item.getQuantidade());
			facade.alterar(p);
		}
	}
}
