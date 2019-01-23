<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<title>Cadastro de Cliente</title>
</head>
<body>
	<div class="container" style="margin-top: 100px;">
		<div class="card">
			<h5 class="card-header">Cadastrar</h5>
			<div class="card-body">
				<h5 class="card-title">Cadastre um novo cliente</h5>
				<form method="POST" action="cliente/cadastrar">
					<div class="form-group">
						<label>Nome Completo</label> 
						<input type="text" class="form-control" placeholder="Nome" required>
					</div>
					<div class="form-group">
						<label>CPF/CNPJ</label> 
						<input type="text" class="form-control" placeholder="CPF/CNPJ" required>
					</div>													
					<div class="form-group">
						<label>Data nascimento</label> 
						<input type="date" class="form-control" placeholder="DD/MM/YYYY" required>
					</div>
					<div class="form-group">
						<label>Username</label> 
						<input type="text" class="form-control" placeholder="Username" required>
					</div>	
					<div class="form-group">
						<label>Senha</label> 
						<input type="text" class="form-control" placeholder="Senha" required>
					</div>											
					<input type="submit" class="btn btn-primary" value="Cadastrar"/>
				</form>

			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>