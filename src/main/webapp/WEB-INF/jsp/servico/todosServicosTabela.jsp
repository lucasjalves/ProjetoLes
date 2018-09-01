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
<html>
<div class="container">
<table class="table table-hover">
  <thead class="thead-light">
    <tr>
      <th scope="col">Nome</th>
      <th scope="col">Descrição</th>
 	  <th scope="col">Status</th>  
 	  <th scope="col">Editar</th>   
 	  <th scope="col">Desativar</th>      
    </tr>
  </thead>
  <tbody id="corpoTableUsuarios">
	<tr>
		<td>Nome Teste</td>
		<td>Descrição</td>
		<td>ATIVO</td>		
		<td><button class='btn btn-primary btn-xs'  data-toggle="modal" data-target="#modalServico" data-backdrop="false">Alterar</button></td>
		<td><button class='btn btn-danger btn-xs' data-toggle="modal" data-target="#modalExclucao" data-backdrop="false">Desativar</button></td>
	</tr>
  </tbody>
</table>

  
  </div>
  <div class="modal" id="modalServico">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      
        <div class="modal-header">
          <h4 class="modal-title">Cadastro de departamento</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <div class="modal-body">
 			<div class="form-group" >
				<label>Nome</label> 
				<input type="text" class="form-control" placeholder="Nome do produto" name="nome" value="Nome teste"> 				
			</div>
 			<div class="form-group" >
				<label>Descrição</label> 
				<input type="text" class="form-control" placeholder="Descrição do serviço" name="qtde" value="Descrição"> 
			</div> 
			 <div class="form-group">
			    <label for="exampleFormControlSelect1">Status</label>
			    <select class="form-control" id="exampleFormControlSelect1">
			      <option>ATIVO</option>
			      <option>INATIVO</option>
			    </select>
			  </div>		
			<small id='errorDepartamento' class='form-text' style="display: none;">Preencha todos os campos</small>
			<div id="errosBackend" style="display:none;">
			</div>
        </div>
        
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" style="position: relative; right: 60%" data-dismiss="modal">Fechar</button>
          <button type="button" class="btn btn-success" id="btnCadastrar" data-dismiss="modal">Alterar</button>
        </div>
      </div>
    </div>
    </div>
</html>
</body>