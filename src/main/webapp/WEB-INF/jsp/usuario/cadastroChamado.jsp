<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<title>Cadastro de Chamados</title>
</head>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" 
integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/esm/popper.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
<style>
.invalido {
	border-color: #dc3545;
}

.msg-erro{
	color: red;
}

.hide{
	display: none;
}
body {
  background: #76b852; /* fallback for old browsers */
  background: -webkit-linear-gradient(right, #76b852, #8DC26F);
  background: -moz-linear-gradient(right, #76b852, #8DC26F);
  background: -o-linear-gradient(right, #76b852, #8DC26F);
  background: linear-gradient(to left, #76b852, #8DC26F);
  font-family: "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;      
}
</style>

<body>
</br></br></br></br>
	<div class="container">
		<form action="/usuario/cadastrar/efetivar" method="POST">
		<div class="form-row">
			<div class="form-group col-md-4" style="margin-left: 9px">
				<input type="text" class="form-control obrigatorio" name="assunto" id="assunto" placeholder="Digite o assunto da ocorrencia"> 
			</div>		
			<div class="form-group col-md-3" style="padding-left: 5px">
				<input type="text" class="form-control obrigatorio" name="ramal" id="ramal" placeholder="Digite o ramal"> 
			</div>	
			<div class="container">
                <labael>Descreva o seu chamado:</labael>
				<textarea class="form-control" rows="5" id="comment"></textarea>
			</div>		
		</div>
			<p class="btn btn-primary"style="margin-left:10px" id="submit" onclick="validarSubmit()">Abrir Chamado</p>
		</form>
	</div>
</body>
</html>