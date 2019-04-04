package com.github.lucasjalves.projetoles.helper;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.entidade.Carrinho;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.CupomPedido;
import com.github.lucasjalves.projetoles.entidade.Endereco;
import com.github.lucasjalves.projetoles.entidade.EnderecoPedido;
import com.github.lucasjalves.projetoles.entidade.ItemCarrinho;
import com.github.lucasjalves.projetoles.entidade.ItemPedido;
import com.github.lucasjalves.projetoles.entidade.Pedido;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.enums.StatusPedido;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.util.CalculoUtil;
import com.github.lucasjalves.projetoles.util.FormatadorDataUtil;
import com.github.lucasjalves.projetoles.util.FreteUtil;
import com.github.lucasjalves.projetoles.util.StringUtils;

@Component
public class PedidoHelper {

	@Autowired
	private Facade facade;
	
	@SuppressWarnings("unchecked")
	public Pedido gerarPedido(Endereco endereco, Carrinho carrinho, Cliente cliente) {
		Pedido pedido = new Pedido();
		List<Produto> produtos = (List<Produto>) facade.consultar(new Produto()).getEntidades();
		cliente = (Cliente) facade.consultar(cliente).getEntidades().get(0);
		
		List<Endereco> enderecos = cliente.getEnderecos()
				.stream()
				.filter(e -> e.equals(endereco))
				.collect(Collectors.toList());
		
		if(!enderecos.isEmpty()) {
			pedido.setEndereco(enderecos.get(0));
		}
		
		Iterator<ItemCarrinho> it = carrinho.getItensCarrinho().iterator();
		Double total = 0.00;
		Double frete = 0.00;
		Double desconto = 0.00;
		if(carrinho.getCupom() != null) {
			desconto = carrinho.getCupom().getValorDesconto() / 100;
			desconto = desconto * total;	
			pedido.setCupom(carrinho.getCupom());
		}
		while(it.hasNext()) {
			ItemCarrinho i = it.next();
			Produto produto = produtos.stream().filter(p -> p.equals(i.getProduto()))
					.collect(Collectors.toList()).get(0);
			
			total = total + (StringUtils.StringToDouble(produto.getPrecoVenda())) * i.getQuantidade();
			frete = frete + FreteUtil.calcularFrete(produto,  i.getQuantidade());
			
			ItemPedido itemPedido = new ItemPedido()
										.withProduto(produto)
										.withQuantidade(i.getQuantidade())
										.withValorTotal(CalculoUtil.calcularValorTotal(produto.getPrecoVenda(), i.getQuantidade()));
			itemPedido.getProduto().setId(null);
			pedido.getItensPedido().add(itemPedido);
		}
		pedido.setTotal(String.format("%,.2f", total));
		pedido.setFrete(String.format("%,.2f", frete));
		pedido.setDesconto(String.format("%,.2f", desconto));
		pedido.setTotalCompra(String.format("%,.2f", (total - desconto) + frete));
		pedido.setStatus(StatusPedido.SOLICITADO);
		String dtHora = FormatadorDataUtil.dataFormatada(LocalDateTime.now());
		pedido.setDtPedido(dtHora.split("T")[0]);
		pedido.setHora(dtHora.split("T")[1]);
		return pedido;
	}
}
