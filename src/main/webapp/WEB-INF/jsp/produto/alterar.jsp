<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<script>
	$(document).ready(function(){
		$("#btnCadastrar").on("click", function(){
			$.post("http://localhost:8888/produto/alterar", $("#form").serialize())
				.done(function(data){
					abrirModalSucessoOuFalha(data, "Produto alterado com sucesso!", "Falha ao alterar o produto", data.mensagem.length);
				})
				.fail(function(data){
					abrirModalSucessoOuFalha(data, "Produto alterado com sucesso!", "Falha ao alterar o produto", 1);
				});
		});	
		
		$("#ativo").val('${produto.ativo}');
		$("#categoria").val('${produto.categoria}');
		$("#precoCompra").maskMoney({prefix:'R$ ', allowNegative: false, thousands:'.', decimal:',', affixesStay: false});
		$("#precoVenda").maskMoney({prefix:'R$ ', allowNegative: false, thousands:'.', decimal:',', affixesStay: false});
	});


</script>
<title>Alteração de Produto</title>
</head>
<body>
	<div class="container spacer" >
		<div class="card">
			<h5 class="card-header">Alterar</h5>
			<div class="card-body">
				<h5 class="card-title">Altere este produto</h5>
				<form id="form">
				<input type="hidden" name="id" value="${produto.id}" />
					<div class="form-group">
						<label>Nome</label> 
						<input type="text" class="form-control" name="nome" value="${produto.nome}">
					</div>
					<div class="form-group">
					    <label>Descrição</label>
					    <textarea class="form-control" name="descricao"  rows="3">${produto.descricao}</textarea>
					</div>	
					<div class="form-group">
					    <label>Categoria</label>
				    <select class="form-control" id="categoria" name="categoria">
						<c:forEach items="${categorias}" var="categoria">
							<option value="${categoria}">${categoria}</option>
						</c:forEach>
				    </select>
					</div>									
					<div class="form-group">
						<label>Especificações</label> 
						<textarea class="form-control" name="especificacoes"  rows="3">${produto.especificacoes}</textarea>
					</div>									
					<div class="form-group">
						<label>Preço de compra</label> 
						<input type="text" class="form-control" id="precoCompra" name="precoCompra"  value="${produto.precoCompra}">
					</div>
					<div class="form-group">
						<label>Preço de venda</label> 
						<input type="text" class="form-control" id="precoVenda" name="precoVenda"   value="${produto.precoVenda}">
					</div>		
					<div class="form-group">
						<label>Código de barras</label> 
						<input type="text" class="form-control" id="codigoBarras" name="codigoBarras"   value="${produto.codigoBarras}">
					</div>								
					<div class="form-group">
						<label>Marca</label> 
						<input type="text" class="form-control" name="marca"  value="${produto.marca}">
					</div>
					<div class="form-group">
						<label>Modelo</label> 
						<input type="text" class="form-control" name="modelo"   value="${produto.modelo}">
					</div>
					<div class="form-group">
						<label>Altura</label> 
						<input type="text" class="form-control" name="altura"   value="${produto.altura}">
					</div>
					<div class="form-group">
						<label>Largura</label> 
						<input type="text" class="form-control" name="largura"  value="${produto.largura}">
					</div>
					<div class="form-group">
						<label>Comprimento</label> 
						<input type="text" class="form-control" name="comprimento"  value="${produto.comprimento}">
					</div>	
					<div class="form-group">
						<label>Peso</label> 
						<input type="text" class="form-control" name="peso"  value="${produto.peso}">
					</div>	
					<div class="form-group">
						<label>Estoque</label> 
						<input type="text" class="form-control" name="Estoque"  value="${produto.estoque}">
					</div>						
					<div class="form-group">
						<label>Conteúdo Embalagem</label> 
						<textarea class="form-control" name="conteudoEmbalagem"  rows="3">${produto.conteudoEmbalagem}</textarea>
					</div>
					
				<div class="form-group">
				    <label>Status</label>
				    <select class="form-control" id="ativo" name="ativo">
				      <option selected value="true">Ativo</option>
				      <option value="false">Inativo</option>
				    </select>
				  </div>																		
				</form>
				<button class="btn btn-primary" id="btnCadastrar">Alterar</button>

			</div>
		</div>
	</div>
	<jsp:include page="../componentes/modal.jsp"></jsp:include>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>