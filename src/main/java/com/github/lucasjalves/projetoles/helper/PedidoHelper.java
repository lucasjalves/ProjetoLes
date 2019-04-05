package com.github.lucasjalves.projetoles.helper;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.entidade.Carrinho;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.Cupom;
import com.github.lucasjalves.projetoles.entidade.CupomPedido;
import com.github.lucasjalves.projetoles.entidade.Endereco;
import com.github.lucasjalves.projetoles.entidade.EnderecoPedido;
import com.github.lucasjalves.projetoles.entidade.ItemCarrinho;
import com.github.lucasjalves.projetoles.entidade.ItemPedido;
import com.github.lucasjalves.projetoles.entidade.Pedido;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.entidade.ProdutoPedido;
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
	public Pedido gerarPedido(Endereco endereco, Carrinho carrinho, Cliente cliente) throws CloneNotSupportedException {
		Pedido pedido = new Pedido();
		List<Produto> produtos = (List<Produto>) facade.consultar(new Produto()).getEntidades();
		cliente = (Cliente) facade.consultar(cliente).getEntidades().get(0);
		
		List<Endereco> enderecos = cliente.getEnderecos()
				.stream()
				.filter(e -> e.equals(endereco))
				.collect(Collectors.toList());
		
		if(!enderecos.isEmpty()) {
			pedido.setEndereco(to(enderecos.get(0)));
			pedido.getEndereco().setId(null);
		}
		
		Iterator<ItemCarrinho> it = carrinho.getItensCarrinho().iterator();
		Double total = 0.00;
		Double frete = 0.00;
		Double desconto = 0.00;
		if(carrinho.getCupom() != null) {
			desconto = carrinho.getCupom().getValorDesconto() / 100;
			desconto = desconto * total;	
			pedido.setCupom(to(carrinho.getCupom()));
			pedido.getCupom().setId(null);
		}
		while(it.hasNext()) {
			ItemCarrinho i = it.next();
			Produto produto = produtos.stream().filter(p -> p.equals(i.getProduto()))
					.collect(Collectors.toList()).get(0);
			
			total = total + (StringUtils.StringToDouble(produto.getPrecoVenda())) * i.getQuantidade();
			frete = frete + FreteUtil.calcularFrete(produto,  i.getQuantidade());
			
			ItemPedido itemPedido = new ItemPedido()
										.withProduto(to(produto))
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
	
	public static ProdutoPedido to(Produto produto) {
		ProdutoPedido p = new ProdutoPedido();
		p.setAltura(produto.getAltura());
		p.setAtivo(produto.getAtivo());
		p.setCategoria(produto.getCategoria());
		p.setCodigoBarras(produto.getCodigoBarras());
		p.setComprimento(produto.getComprimento());
		p.setConteudoEmbalagem(produto.getConteudoEmbalagem());
		p.setDescricao(produto.getDescricao());
		p.setEspecificacoes(produto.getEspecificacoes());
		p.setEstoque(produto.getEstoque());
		p.setLargura(produto.getLargura());
		p.setMarca(produto.getMarca());
		p.setModelo(produto.getModelo());
		p.setNome(produto.getNome());
		p.setPeso(produto.getPeso());
		p.setPrecoCompra(produto.getPrecoCompra());
		p.setPrecoVenda(produto.getPrecoVenda());
		return p;
	}
	
	public static EnderecoPedido to(Endereco endereco) {
		EnderecoPedido e = new EnderecoPedido();
		e.setBairro(endereco.getBairro());
		e.setCep(endereco.getCep());
		e.setCidade(endereco.getCidade());
		e.setComplemento(endereco.getComplemento());
		e.setNome(endereco.getNome());
		e.setNumero(endereco.getNumero());
		e.setPais(endereco.getPais());
		e.setRua(endereco.getRua());
		e.setUf(endereco.getUf());
		return e;
	}
	
	public static CupomPedido to(Cupom cupom) {
		CupomPedido cp = new CupomPedido();
		cp.setCodigo(cupom.getCodigo());
		cp.setDataVencimento(cupom.getDataVencimento());
		cp.setStatus(cupom.getStatus());
		cp.setValorDesconto(cupom.getValorDesconto());
		return cp;
	}
}
