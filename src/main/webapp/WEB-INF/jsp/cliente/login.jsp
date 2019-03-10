<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<script>
	$(document).ready(function(){
		$("#cpfCnpj").mask("000.000.000.00");
		$("#btnCadastrar").on("click", function(){
			
			$.post("http://localhost:8888/cliente/efetuarLogin", $("#form").serialize())
			.done(function(data){
				if(data){
					setCookie("admin", "trueAdmin", 1);
				}else{
					setCookie("admin", "falseAdmin", 1);
				}

				window.location.href = "http://localhost:8888";
			})
			.fail(function(data){
				abrirModal("Falha", "Usuário não encontrado");
			});			
		});
	});

</script>
<title>Cadastro de Cliente</title>
</head>
<body>
	<div class="container spacer" >
		<div class="card">
			<h5 class="card-header">Login</h5>
			<div class="card-body">
				<h5 class="card-title">Login</h5>
				<form id="form">
					<div class="form-group">
						<label>CPF</label> 
						<input type="text" class="form-control" name="cpfCnpj" id="cpfCnpj" placeholder="CPF/CNPJ" required>
					</div>
					<div class="form-group">
						<label>Senha</label> 
						<input type="password" class="form-control" name="senha" placeholder="Senha" required>
					</div>																								
				</form>
				<button class="btn btn-primary" id="btnCadastrar">Login</button>
			</div>
		</div>
	</div>
	<jsp:include page="../componentes/modal.jsp"></jsp:include>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>