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
<script>
	var jsonUsuario = '${jsonUsuario}';
	var jsonParsed = JSON.parse(jsonUsuario);
	var mesmoUsuario = ${mesmoUsuario};
	
	$(document).ready(function(){
		if(mesmoUsuario){
			var listItem = "<li class='list-group-item'>CPF: ${usuario.cpf}</li>"
			$("#listaCardUsuario").append(listItem);			
		}
	});
</script>
<body>
	<div class="container">
		<div class="row">
			<div class="card col-md-3" >
			     <img class="card-img-top" src="https://www.weact.org/wp-content/uploads/2016/10/Blank-profile.png" alt="Card image cap" style="width: 220px;">
			  <div class="card-body">
			    <h5 class="card-title">${usuario.nome}</h5>
			    <p class="card-text">${usuario.tipoUsuario}</p>
			    <p class="card-text">${usuario.username}</p>
			  </div>
			  <ul class="list-group list-group-flush" id="listaCardUsuario">
			    <li class="list-group-item">${usuario.departamento.nome}</li>
			    <li class="list-group-item">${usuario.setor.nome}</li>
			    <li class="list-group-item">${usuario.email}</li>
			    <li class="list-group-item">Ramal: ${usuario.ramal}</li>
			    <li class="list-group-item">Pontos: ${usuario.pontos}</li>
			  </ul>
			</div>
			<div class="card col-md-9">
			  <div class="card-header" style="margin-left: -18px; width: 105%">
			    Featured
			  </div>
			  <div class="card-body">
			    <h5 class="card-title">Special title treatment</h5>
			    <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
			    <a href="#" class="btn btn-primary">Go somewhere</a>
			  </div>
			</div>
		</div>
	</div>
</body>
</html>