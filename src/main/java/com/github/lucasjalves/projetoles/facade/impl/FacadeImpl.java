package com.github.lucasjalves.projetoles.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.dao.DAO;
import com.github.lucasjalves.projetoles.dao.impl.ClienteDAO;
import com.github.lucasjalves.projetoles.dao.impl.CupomDAO;
import com.github.lucasjalves.projetoles.dao.impl.ProdutoDAO;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.Cupom;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.helper.ProcessarRegraNegocioHelper;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;
import com.github.lucasjalves.projetoles.rns.strategy.impl.ClienteDadosObrigatorios;
import com.github.lucasjalves.projetoles.rns.strategy.impl.DadosObrigatoriosCupom;
import com.github.lucasjalves.projetoles.rns.strategy.impl.DadosObrigatoriosProduto;
import com.github.lucasjalves.projetoles.rns.strategy.impl.EntidadeDadoObrigatorio;
import com.github.lucasjalves.projetoles.rns.strategy.impl.QuantidadeEstoqueProduto;

@Component
final public class FacadeImpl implements Facade {

	private ProcessarRegraNegocioHelper regraNegocioHelper;
	
	private Map<String, Map<String, List<Strategy>>> rns = 
			new HashMap<>();
	
	private Map<String, DAO> daos = new HashMap<>();
	
	@Autowired
	private ClienteDAO clienteDAO;
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private CupomDAO cupomDAO;
	@PostConstruct
	public void setUpRns() {
		
		Map<String,List<Strategy>> mapaStrategyProduto = new HashMap<>();
		
		
		List<Strategy> rnsProduto =new ArrayList<>();
		List<Strategy> rnsProdutoAlterar =new ArrayList<>();
		List<Strategy> rnsProdutoConsulta = new ArrayList<>();
		rnsProduto.add(new DadosObrigatoriosProduto());
		rnsProdutoAlterar.add(new DadosObrigatoriosProduto());
		rnsProdutoAlterar.add(new EntidadeDadoObrigatorio());
		rnsProdutoConsulta.add(new QuantidadeEstoqueProduto());
		mapaStrategyProduto.put("SALVAR", rnsProduto);
		mapaStrategyProduto.put("ALTERAR", rnsProdutoAlterar);
		mapaStrategyProduto.put("CONSULTAR", rnsProdutoConsulta);
		rns.put(Produto.class.getName(), mapaStrategyProduto);
		
		
		Map<String,List<Strategy>> mapaStrategyCliente = new HashMap<>();
		List<Strategy> rnsCliente = new ArrayList<>();
		List<Strategy> rnsClienteAlterar = new ArrayList<>();
		
		rnsCliente.add(new ClienteDadosObrigatorios());
		rnsClienteAlterar.add(new EntidadeDadoObrigatorio());
		rnsClienteAlterar.add(new ClienteDadosObrigatorios());
		
		mapaStrategyCliente.put("SALVAR", rnsCliente);
		mapaStrategyCliente.put("ALTERAR", rnsClienteAlterar);
		rns.put(Cliente.class.getName(), mapaStrategyCliente);
		
		
		Map<String, List<Strategy>> mapaStrategyCoupom = new HashMap<>();
		List<Strategy> rnsCupom = new ArrayList<>();
		List<Strategy> rnsCupomAlterar = new ArrayList<>();
		
		rnsCupom.add(new DadosObrigatoriosCupom());	
		rnsCupomAlterar.add(new DadosObrigatoriosCupom());
		rnsCupomAlterar.add(new EntidadeDadoObrigatorio());
		
		mapaStrategyCoupom.put("SALVAR", rnsCupom);
		mapaStrategyCoupom.put("ALTERAR", rnsCupomAlterar);
		
		rns.put(Cupom.class.getName(), mapaStrategyCoupom);
		
		daos.put(Cliente.class.getName(), clienteDAO);
		daos.put(Produto.class.getName(), produtoDAO);
		daos.put(Cupom.class.getName(), cupomDAO);
	}
	@Override
	public Resultado salvar(Entidade entidade) {
		Resultado resultado = new Resultado();	
		List<String> mensagens = processar(entidade, "SALVAR");
		resultado.setMensagem(mensagens);
		if(mensagens.isEmpty()) {
			daos.get(entidade.getClass().getName()).salvar(entidade);
		}
		return resultado;
	}

	@Override
	public Resultado consultar(Entidade entidade) {
		Resultado resultado = new Resultado();	
		List<String> mensagens = processar(entidade, "CONSULTAR");
		resultado.setMensagem(mensagens);
		if(mensagens.isEmpty()) {
			resultado.setEntidades(daos.get(entidade.getClass().getName()).buscar(entidade));
		}
		
		return resultado;
	}

	@Override
	public Resultado alterar(Entidade entidade) {
		Resultado resultado = new Resultado();	
		List<String> mensagens = processar(entidade, "ALTERAR");
		resultado.setMensagem(mensagens);
		if(mensagens.isEmpty()) {
			daos.get(entidade.getClass().getName()).salvar(entidade);
		}
		return resultado;
	}

	@Override
	public Resultado excluir(Entidade entidade) {
		Resultado resultado = new Resultado();	
		List<String> mensagens = processar(entidade, "EXCLUIR");
		resultado.setMensagem(mensagens);
		if(mensagens.isEmpty()) {
			daos.get(entidade.getClass().getName()).excluir(entidade);
		}
		return resultado;
	}

	private List<String> processar(Entidade entidade, String operacao) {
		List<String> mensagens = new ArrayList<>();
		Map<String, List<Strategy>> rnsEntidade = 
				rns.get(entidade.getClass().getName());
		
		List<Strategy> listaRns = rnsEntidade.get(operacao);
		if(listaRns == null) {
			return mensagens;
		}
		for(Strategy strategy: listaRns) {
			mensagens.addAll(strategy.processar(entidade));
		}
		
		return mensagens;
	}

}
