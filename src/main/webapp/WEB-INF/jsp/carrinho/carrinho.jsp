<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<script>
function aplicarCupom(){
	$("#codigoCupomValido").val($("#codigoCupom").val());
	$.post("http://localhost:8888/carrinho/adicionarCupom", $("#formCupom").serialize())
	.done(function(data){
		var sucesso = abrirModalSucessoOuFalha(data, " ", "Falha ao adicionar o cupom", 1, true, false);
		if(sucesso){
			window.location.replace("http://localhost:8888/carrinho");
		}
	})
	.fail(function(data){
		abrirModalSucessoOuFalha(data, " ", "Falha ao adicionar o cupom", 1);
	});
}

function efetuarPedido(){
	$("#idEndereco").val($("#comboEndereco").val());
	$.post("http://localhost:8888/pedido/confirmar", $("#formEnderecoHidden").serialize())
	.done(function(data){
		var sucesso = abrirModalSucessoOuFalha(data, " ", "Falha ao confirmar o pedido", 1, true, false);
		if(sucesso){
			window.location.replace("http://localhost:8888/pedido/confirmacao?id=0");
		}
	})
	.fail(function(data){
		abrirModalSucessoOuFalha(data, " ", "Falha ao confirmar o pedido", 1);
	});
}
function removerCupom(){
	
	$.post("http://localhost:8888/carrinho/removerCupom", $("#formCupom").serialize())
	.done(function(data){
		var sucesso = abrirModalSucessoOuFalha(data, " ", "Falha ao remover o cupom", 1, true, false);
		if(sucesso){
			window.location.replace("http://localhost:8888/carrinho");
		}
	})
	.fail(function(data){
		abrirModalSucessoOuFalha(data, " ", "Falha ao remover o cupom", 1);
	});	
}

function atualizarQuantidade(id, input, quantidadeSessao){
	if(input.value.replace(/\D/g, "").length === 0){
		input.value = quantidadeSessao;
		return;
	}
	$("#idProduto").val(id);
	$("#quantidade").val(input.value);
	$.post("http://localhost:8888/carrinho/alterar", $("#form").serialize())
	.done(function(data){
		var sucesso = abrirModalSucessoOuFalha(data, " ", "Falha ao adicionar item no carrinho", 1, true, false);
		if(sucesso){
			window.location.replace("http://localhost:8888/carrinho");
		}else{
			input.value = quantidadeSessao;
		}
	})
	.fail(function(data){
		abrirModalSucessoOuFalha(data, " ", "Falha ao adicionar item no carrinho", 1);
	});
}

function remover(id){
	$("#idProduto").val(id);
	$.post("http://localhost:8888/carrinho/remover", $("#form").serialize())
	.done(function(data){
		window.location.replace("http://localhost:8888/carrinho");
		
	})
	.fail(function(data){
		abrirModalSucessoOuFalha(data, "Produto cadastrado com sucesso!", "Falha ao adicionar item no carrinho", 1);
	});
}

$(document).ready(function(){
	$("#comboEndereco").on("change", function(){
		$("#formEnderecoHidden").val($(this).val());
	});
	$("#btnCadastrarEndereco").on("click", function(){
		cadastrarEndereco(function(data){
			if(abrirModalSucessoOuFalha(data, " ", "Falha ao cadastrar o endereço", data.mensagem.length, true, false)){
				window.location.replace("http://localhost:8888/carrinho");
			}
		}, function(data){
			abrirModalSucessoOuFalha(data, " ", "Falha ao cadastrar o endereço", data.mensagem.length, false, false);
		});
	});
});
</script>
<meta charset="ISO-8859-1">
<title>Carrinho</title>
<style>
@media (min-width: 200px)
.card-deck{
	flex-flow: row wrap;
	margin-right: -15px;
	margin-left: -15px;
}
</style>
</head>
<body>
<form id="form">
 <input type="hidden" name="id" id="idProduto" />
 <input type="hidden"  name="quantidadeSelecionada" id="quantidade" value=""/>
</form>
<form id="formCupom">
 	<input type="hidden" name="codigo" id="codigoCupomValido" />
</form>
<form id="formEnderecoHidden">
	<input type="hidden" name="id" id="idEndereco" />
</form>

	<div class="container spacer">
		<table class="table table-bordered">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Produtos</th>
					<th scope="col" style="width: 100px;">Quantidade</th>
					<th scope="col" style="width: 165px;">Preço Unitário</th>
					<th scope="col" style="width: 165px;">Total</th>
					<th scope="col" style="width: 100px;">Ação</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${carrinho != null}">
					<c:forEach items="${carrinho.itensCarrinho}" var="itemCarrinho">
						<tr>
							<td><img src="http://placehold.it/100x100" alt="..."
								class="img-responsive left">
								<table class="left">
									<tr>
										<td style="border: none;"><strong>${itemCarrinho.produto.modelo}</strong></td>
									</tr>
									<tr>
										<td style="border: none;">${itemCarrinho.produto.marca} - 
											<c:if test="${!itemCarrinho.status}"><strong style="color: red; float: right;">Este item será removido por indisponibilidade</strong></c:if>
											<c:if test="${itemCarrinho.quantidadeAtualizada}"><strong style="color: red; float: right;">Quantidade alterada devido estoque!</strong></c:if>
										</td>
									</tr>
								</table></td>
							<td>
								<c:if test="${itemCarrinho.status}">
									<input type="number" class="form-control"style="width: 80px; margin-top: 30px;" onfocusout="atualizarQuantidade('${itemCarrinho.produto.id}', this, '${itemCarrinho.quantidade}')"value="${itemCarrinho.quantidade}" />
								</c:if>
								<c:if test="${!itemCarrinho.status}">
									<input type="number" class="form-control"style="width: 80px; margin-top: 30px;" value="0" disabled />
								</c:if>								
							</td>
							<td><label style="margin-top: 35px; font-weight: bold;">R$
									${itemCarrinho.produto.precoVenda} </label></td>
							<td><label style="color: red; margin-top: 35px;">R$
									${itemCarrinho.valorTotal}</label></td>
							<td>
								<c:if test="${itemCarrinho.status}">
									<button style="margin-top: 30px;" class="btn btn-outline-danger" onclick="remover('${itemCarrinho.produto.id}')">Remover</button>
								</c:if>
								<c:if test="${!itemCarrinho.status}">
									<button style="margin-top: 30px;" class="btn btn-secondary" disabled>Remover</button>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
			
		</table>
		<div class="card-deck">
			<div class="card" style="width: 18rem;height: 250px;">
				<div class="card-header"><strong>Endereços</strong></div>
				<div class="card-body">
					<div class="form-group row">
						<div class="col-sm-12">
							<c:if test="${cliente != null}">
								<select class="form-control" id="comboEndereco">
									<option value="-1">Selecione...</option>
										<c:forEach items="${cliente.enderecos}" var="endereco">
											<option value="${endereco.id}">${endereco.nome}</option>										
										</c:forEach>
								</select>
							</c:if>
							<c:if test="${cliente == null}">
								<p style="color: red;">Você deve estar logado para escolher um endereço de entrega</p>
							</c:if>							
						</div>
						
					</div>
					<c:if test="${cliente != null}">
						<button class="btn btn-success" data-toggle="modal" data-target="#cadastro">Cadastrar novo</button>
					</c:if>		
				</div>
			</div>
			<div class="card" style="width: 18rem;">
				<div class="card-header">
					<strong>Cupom</strong>
				</div>
				<div class="card-body">
					<ul class="list-group list-group-flush">
						<c:if test="${carrinho.cupom == null}">
							<li class="list-group-item"><strong>Cupom</strong><label
								class="right"><input type="text" id="codigoCupom" class="form-control" /></label></li>
							<li class="list-group-item right" style="display: none; color: green; border: none;" id="descontoCupom"></li>
							<li class="list-group-item" style="border: none;"> 
								<button class="btn btn-success" style="margin-left: 15px;" onclick="aplicarCupom();">Aplicar</button>
							</li>
						</c:if>
						<c:if test="${carrinho.cupom != null}">
							<li class="list-group-item"><strong>Cupom</strong><label
								class="right"><input type="text" id="codigoCupom" name="codigo" class="form-control" /></label></li>

							<li class="list-group-item right" style="color: green; border: none;" id="descontoCupom">Cupom ${carrinho.cupom.codigo} -- ${carrinho.cupom.valorDesconto}%</li>
							<c:if test="${!carrinho.statusCupom}">
								<strong style="color: red">Este cupom será removido por vencimento/desativado!</strong>
							</c:if>
							<li class="list-group-item" style="border: none;"> 
								<button class="btn btn-success" style="margin-left: 15px;" onclick="aplicarCupom();">Aplicar</button>
								<button class="btn btn-danger" id="btnRemoverCupom" onclick="removerCupom();">Remover</button>
							</li>
						</c:if>
					</ul>
					
				</div>
			</div>
			<div class="card right" style="width: 18rem; height: 250px;">
				<ul class="list-group list-group-flush">
					<c:if test="${carrinho == null}">
						<li class="list-group-item"><strong>Total</strong><label class="right">R$ 0,00</label></li>
						<li class="list-group-item"><strong>Frete</strong><label class="right">R$ 0,00</label></li>
						<li class="list-group-item"><strong>Desconto</strong><label class="right" id="desconto">R$ 0,00</label></li>
						<li class="list-group-item"><strong>Total da compra</strong><label class="right" id="total">R$ 0,00</label></li>
					</c:if>
					<c:if test="${carrinho != null}">
						<li class="list-group-item"><strong>Total</strong><label class="right">R$ ${carrinho.total}</label></li>
						<li class="list-group-item"><strong>Frete</strong><label class="right">R$ ${carrinho.frete}</label></li>
						<li class="list-group-item"><strong>Desconto</strong><label class="right" id="desconto">R$ ${carrinho.desconto}</label></li>
						<li class="list-group-item"><strong>Total da compra</strong><label class="right" id="total">R$ ${carrinho.totalCompra}</label></li>					
					</c:if>
				</ul>
				
			</div>

		</div>
			 <a class="btn btn-warning right" href="#" style="margin-left: 15px; margin-top: 30px;" onclick="efetuarPedido();">Finalizar compra</a>
	</div>

	<div class="modal fade" id="cadastro">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Novo endereço</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
				<jsp:include page="../componentes/formEndereco.jsp"></jsp:include>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
	        <button type="button" class="btn btn-primary" id="btnCadastrarEndereco" data-dismiss="modal">Salvar</button>
	      </div>
	    </div>
	  </div>
	</div>
	<jsp:include page="../componentes/modal.jsp"></jsp:include>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>