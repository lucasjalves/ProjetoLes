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
		<div class="form-row">
			<div class="form-group col-md-4">
				<label>Nome </label> 
				<input type="text" class="form-control obrigatorio" name="nome" id="nome" placeholder="Digite o nome completo"> 
			</div>
			<div class="form-group col-md-3">
				<label>CPF </label> 
				<input type="text" class="form-control obrigatorio" name="cpf" id="cpf" placeholder="Digite o CPF"> 
				<input type="hidden" name="statusCadastro" id="statusCadastro"/>
			</div>
		
			<div class="form-group col-md-3">
				<label>Ramal </label> 
				<input type="text" class="form-control obrigatorio" name="ramal" id="ramal" placeholder="Digite o ramal"> 
			</div>
		</div>
<table class="table table-hover">
  <thead class="thead-light">
    <tr>
      <th scope="col">Nome</th>
      <th scope="col">Quantidade</th>
      <th scope="col">Preço</th>
      <th scope="col">Descrição</th>
      <th scope="col">Status</th>
      <th scope="col">Detalhar</th>
      <th scope="col">Desativar</th>
    </tr>
  </thead>
  <tbody id="corpoTableUsuarios">
	<tr>
		<td>Nome Teste</td>
		<td>1000</td>
		<td>10,00R$</td>
		<td>Descrição teste</td>
		<td>ATIVO</td>
		<td><button class='btn btn-primary btn-xs'  data-toggle="modal" data-target="#modalProduto" data-backdrop="false">Alterar</button></td>
		<td><button class='btn btn-danger btn-xs' data-toggle="modal" data-target="#modalExclucao" data-backdrop="false">Desativar</button></td>
	</tr>
  </tbody>
</table>

  
  </div>
  <div class="modal" id="modalProduto">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      
        <div class="modal-header">
          <h4 class="modal-title">Alteração de produto</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <div class="modal-body">
 			<div class="form-group" >
				<label>Nome</label> 
				<input type="text" class="form-control" placeholder="Nome do produto" name="nome" value="Nome teste"> 				
			</div>
 			<div class="form-group" >
				<label>Quantidade</label> 
				<input type="text" class="form-control" placeholder="Quantidade do produto" name="qtde" value="1000"> 
			</div> 
 			<div class="form-group" >
				<label>Data cadastro</label> 
				<input type="text" class="form-control"  disabled value="01/09/2018"> 
			</div>
 			<div class="form-group" >
				<label>Descrição</label> 
				<input type="text" class="form-control" placeholder="Descrição do produto" name="descricao" value="Descrição"> 
			</div> 	
 			<div class="form-group" >
				<label>Preço</label> 
				<input type="text" class="form-control" placeholder="Preço do produto" name="preco" value="10,00"> 
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