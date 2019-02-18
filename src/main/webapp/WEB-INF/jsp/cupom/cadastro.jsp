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
					abrirModalSucessoOuFalha(data, "Cliente cadastrado com sucesso!", "Falha ao cadastrar o cliente", 1);
				})
				.fail(function(data){
					abrirModalSucessoOuFalha(data, "Cliente cadastrado com sucesso!", "Falha ao cadastrar o cliente", 1);
				});
		});	
	});

</script>
<title>Cadastro de Cupom</title>
</head>
<body>
	<div class="container" style="margin-top: 100px;">
		<div class="card">
			<h5 class="card-header">Cadastrar</h5>
			<div class="card-body">
				<h5 class="card-title">Cadastre um novo cupom</h5>
				<form id="form">
					<div class="form-group">
						<label>Código</label> 
						<input type="text" class="form-control" name="nome" placeholder="Código" required>
					</div>
					<div class="form-group">
						<label>Desconto</label> 
						<input type="text" class="form-control" name="cpfCnpj" placeholder="%" required>
					</div>													
					<div class="form-group">
						<label>Data vencimento</label> 
						<input type="date" class="form-control" name="dtNascimento" placeholder="DD/MM/YYYY" required>
					</div>										
				</form>
				<button class="btn btn-primary">Cadastrar</button>

			</div>
		</div>
	</div>
	<jsp:include page="../componentes/modal.jsp"></jsp:include>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>