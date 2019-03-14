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
			$.post("http://localhost:8888/cliente/alterar", $("#form").serialize())
				.done(function(data){
					abrirModalSucessoOuFalha(data, "Cliente alterado com sucesso!", "Falha ao cadastrar o cliente", data.mensagem.length);
				})
				.fail(function(data){
					abrirModalSucessoOuFalha(data, "Cliente alterado com sucesso!", "Falha ao cadastrar o cliente", 1);
				});
		});	
		
		$("#cpfCnpj").mask("000.000.000-00");
		$("#dtNascimento").mask("23/07/1997");
		$("#genero").val("${cliente.genero}");
		$("#status").val("${cliente.ativo}");
	});

</script>
<title>Alterar dados</title>
</head>
<body>
	<div class="container" style="margin-top: 100px;">
		<div class="card">
			<h5 class="card-header">Alterar</h5>
			<div class="card-body">
				<h5 class="card-title">Alterar dados</h5>
				<form id="form">
				<input type="hidden" name="id" value="${cliente.id}" />
					<div class="form-group">
						<label>Nome Completo</label> 
						<input type="text" class="form-control" name="nome" placeholder="Nome" value="${cliente.nome}"required>
					</div>
					<div class="form-group">
						<label>CPF/CNPJ</label> 
						<input type="text" class="form-control"placeholder="CPF/CNPJ" id="cpfCnpj" name="cpfCnpj" value="${cliente.cpfCnpj}">
					</div>													
					<div class="form-group">
						<label>Data nascimento</label> 
						<input type="text" class="form-control" name="dtNascimento" placeholder="DD/MM/YYYY" id="dtNascimento" value="${cliente.dtNascimento}"required>
					</div>
				  <div class="form-group">
				    <label>Gênero</label>
				    <select class="form-control" id="genero" name="genero">
				      <option value="M">M</option>
				      <option value="F">F</option>
				    </select>
				  </div>
					<div class="form-group">
						<label>E-mail</label> 
						<input type="text" class="form-control" name="email" placeholder="Nome" id="email" value="${cliente.email}" required>
					</div>					  				
					<div class="form-group">
						<label>Username</label> 
						<input type="text" class="form-control" name="username" placeholder="Username" value="${cliente.username}" required>
					</div>	
				<div class="form-group">
				    <label>Status</label>
				    <select id="status" class="form-control" name="ativo">
				      <option value="true">Ativo</option>
				      <option value="false">Inativo</option>
				    </select>
				  </div>					
					<div class="form-group">
						<label>Senha</label> 
						<input type="password" class="form-control" name="senha" placeholder="Senha" required>
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