<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
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
	var jsonListaUsuarios = '${jsonListaUsuarios}';
	$(document).ready(function(){
		var list = JSON.parse(jsonListaUsuarios);
		$.each(list, function(index, usuario){
			var table = "<tr>" +
						"<td>" + usuario.nome + "</td>" +
						"<td>" + usuario.email + "</td>" +
						"<td>" + usuario.ramal + "</td>" +
						"<td>" + usuario.tipoUsuario + "</td>" +
						"<td>" + usuario.departamento.nome + "</td>" +
						"<td>" + usuario.departamento.setores[0].nome + "</td>" +
						"<td>" + usuario.statusCadastro + "</td>" +
						"<td><p data-placement='top' data-toggle='tooltip' title='Edit'><button class='btn btn-primary btn-xs'><span class='glyphicon glyphicon-pencil'></span></button></p></td>" +
						"<td><p data-placement='top' data-toggle='tooltip' title='Edit'><button class='btn btn-danger btn-xs'><span class='glyphicon glyphicon-trash'></span></button></p></td>" +
						"</tr>";
						
			$("#corpoTableUsuarios").append(table);
		});
		
	});
</script>
<body>
<div class="container">
<table class="table table-hover">
  <thead class="thead-light">
    <tr>
      <th scope="col">Nome</th>
      <th scope="col">E-mail</th>
      <th scope="col">Ramal</th>
      <th scope="col">Tipo Usu�rio</th>
      <th scope="col">Departamento</th>
      <th scope="col">Setor</th>
      <th scope="col">Status</th>
      <th scope="col">Editar</th>
      <th scope="col">Deletar</th>
    </tr>
  </thead>
  <tbody id="corpoTableUsuarios">

  </tbody>
</table>

</div>
</body>
</html>