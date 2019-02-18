<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<script>
	$(document).ready(function(){
		$("#btnCadastrar").on("click", function(){
			$.post("http://localhost:8888/cliente/cadastrar", $("#form").serialize())
				.done(function(data){
					abrirModalSucessoOuFalha(data, "Produto cadastrado com sucesso!", "Falha ao cadastrar o cliente", 1);
				})
				.fail(function(data){
					abrirModalSucessoOuFalha(data, "Produto cadastrado com sucesso!", "Falha ao cadastrar o cliente", 1);
				});
		});	
	});


</script>
<title>Cadastro de Produto</title>
</head>
<body>
	<div class="container spacer" >
		<div class="card">
			<h5 class="card-header">Cadastro</h5>
			<div class="card-body">
				<h5 class="card-title">Cadastre um novo produto</h5>
				<form id="form">
					<div class="form-group">
						<label>Nome</label> 
						<input type="text" class="form-control" name="nome" >
					</div>
					<div class="form-group">
					    <label>Descrição</label>
					    <textarea class="form-control" name="descricao"  rows="3"></textarea>
					</div>					
					<div class="form-group">
						<label>Especificações</label> 
						<textarea class="form-control" name="descricao"  rows="3"></textarea>
					</div>									
					<div class="form-group">
						<label>Preço</label> 
						<input type="text" class="form-control" name="preco"  >
					</div>
					<div class="form-group">
						<label>Marca</label> 
						<input type="text" class="form-control" name="marca" >
					</div>
					<div class="form-group">
						<label>Modelo</label> 
						<input type="text" class="form-control" name="modelo"  >
					</div>
					<div class="form-group">
						<label>Altura</label> 
						<input type="text" class="form-control" name="altura"  >
					</div>
					<div class="form-group">
						<label>Largura</label> 
						<input type="text" class="form-control" name="largura" >
					</div>
					<div class="form-group">
						<label>Comprimento</label> 
						<input type="text" class="form-control" name="comprimento" >
					</div>	
					<div class="form-group">
						<label>Peso</label> 
						<input type="text" class="form-control" name="peso" >
					</div>	
					<div class="form-group">
						<label>Estoque</label> 
						<input type="text" class="form-control" name="Estoque" >
					</div>						
					<div class="form-group">
						<label>Conteúdo Embalagem</label> 
						<textarea class="form-control" name="conteudoEmbalagem"  rows="3"></textarea>
					</div>
					
				<div class="form-group">
				    <label>Status</label>
				    <select class="form-control">
				      <option selected>Ativo</option>
				      <option>Inativo</option>
				    </select>
				  </div>																		
				</form>
				<button class="btn btn-primary" id="btnCadastrar">Cadastrar</button>

			</div>
		</div>
	</div>
	<jsp:include page="../componentes/modal.jsp"></jsp:include>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>