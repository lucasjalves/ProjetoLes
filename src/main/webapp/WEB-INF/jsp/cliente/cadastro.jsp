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
					abrirModalSucessoOuFalha(data, "Cliente cadastrado com sucesso!", "Falha ao cadastrar o cliente", data.mensagem.length);
				})
				.fail(function(data){
					abrirModalSucessoOuFalha(data, "Cliente cadastrado com sucesso!", "Falha ao cadastrar o cliente", 1);
				});
		});	
		$("#cpfCnpj").mask("000.000.000-00");
		$("#dtNascimento").mask("23/07/1997");
	});

</script>
<title>Cadastro de Cliente</title>
</head>
<body>
	<div class="container" style="margin-top: 100px;">
		<div class="card">
			<h5 class="card-header">Cadastrar</h5>
			<div class="card-body">
				<h5 class="card-title">Cadastre um novo cliente</h5>
				<form id="form">
					<div class="form-group">
						<label>Nome Completo</label> 
						<input type="text" class="form-control" name="nome" placeholder="Nome" required>
					</div>
					<div class="form-group">
						<label>CPF/CNPJ</label> 
						<input type="text" class="form-control" name="cpfCnpj" id="cpfCnpj" placeholder="CPF/CNPJ" required>
					</div>													
					<div class="form-group">
						<label>Data nascimento</label> 
						<input type="text" id="dtNascimento" class="form-control" name="dtNascimento" placeholder="DD/MM/YYYY" required>
					</div>
					  <div class="form-group">
					    <label>Gênero</label>
					    <select class="form-control" name="genero">
					      <option selected>M</option>
					      <option>F</option>
					    </select>
					  </div>
					<div class="form-group">
						<label>E-mail</label> 
						<input type="text" class="form-control" name="email" placeholder="Nome" id="email" required>
					</div>					  								
					<div class="form-group">
						<label>Username</label> 
						<input type="text" class="form-control" name="username" placeholder="Username" required>
					</div>	
					<div class="form-group">
					    <label>Status</label>
					    <select class="form-control" name="ativo">
					      <option selected value="true">Ativo</option>
					      <option value="false">Inativo</option>
					    </select>
					 </div>					
					<div class="form-group">
						<label>Senha</label> 
						<input type="password" class="form-control" name="senha" placeholder="Senha" required>
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