<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<title>Cadastro de usuário</title>
</head>
<style>
.invalido {
	border-color: #dc3545;
}

.msg-erro{
	color: red;
}
</style>

<script>

	$(document).ready(function(){
		$("input").each(function(){
			$(this).on("focusout", function(){
				
			});
		});
	});
	function validarEmail(email) {
	  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	  email = email.toLowerCase();
	  return re.test(email);
	}
	
	function mostrarErro(campo, msgErro){
		$(campo).parent().append("<small id='error' class='form-text msg-erro'>" + msg + "</small>");
	}
	
	function esconderErro(campo){
		$(campo).parent().find("small").remove();
	}
</script>

<body>
	<div class="container">
		<form>
			<div class="form-group">
				<label for="exampleInputEmail1">Endereço de e-mail</label> <input
					type="email" class="form-control obrigatorio" id="email"
					placeholder="Enter email"> 
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Password</label> <input
					type="password" class="form-control obrigatorio" id="exampleInputPassword1"
					placeholder="Password">
					<small
					id="emailHelp" class="form-text validacao">Digite um email valido.</small>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</body>
</html>