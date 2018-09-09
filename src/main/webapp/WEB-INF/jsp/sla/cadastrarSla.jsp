<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" 
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" 
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/esm/popper.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
</head>
<body>

	<div class="container">
		<form action="#">
			<div class="form-row">
				<div class="form-group col-md-4">
					<label>Nome </label> 
					<input type="text" class="form-control obrigatorio" name="nome" id="nome" placeholder="Digite o nome completo"> 
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-4">
					<label>Proridade</label>
					<select class="custom-select obrigatorio" id="prioridade"
						name="prioridade">
						<option value="CRITICO">Crítico</option>
						<option value="ALTA">Alta</option>
						<option value="MEDIA">Média</option>
						<option value="BAIXA">Baixa</option>
					</select>
				</div>
			</div>
			<p class="btn btn-primary" id="submit">Cadastrar</p>
		 </form>
		</div>	
	</body>
</html>