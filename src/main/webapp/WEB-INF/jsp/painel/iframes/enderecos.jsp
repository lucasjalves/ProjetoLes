<html>
<head>
<script>
function devolverOuTrocar(){
	$("#abrirModal").click();
}

function cadastrar(){
	$("#cadastro input").each(function(){
		$(this).val("");
	});
	$('#abrirModal').click();
}

</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/esm/popper.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
<link href="http://localhost:8888/css/styles.css" rel="stylesheet">
<style>
.modal-backdrop, .modal-backdrop.fade.in{
opacity: 0.1;
}
</style>
</head>
<body >
	<div class="card">
		<div class="card-header">Endereços<span class="btn btn-link" style="margin-left: 50px;" onclick="cadastrar()">+ cadastrar novo</span></div>
		  <div class="card-body">
			<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">Cidade</th>
					<th scope="col">UF</th>
					<th scope="col">Endereço</th>
					<th scope="col">Ações</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row">São Paulo</th>
					<td>SP</td>
					<td>Rua teste, 100</td>
					<td>
						<button href="" class="btn btn-link"
							onclick="$('#abrirModal').click();">Alterar</button>
						<button href="" class="btn btn-link">Deletar</button>
					</td>
				</tr>
				<tr>
					<th scope="row">São Paulo</th>
					<td>SP</td>
					<td>Rua teste, 120</td>
					<td>
						<button href="" class="btn btn-link"
							onclick="$('#abrirModal').click();">Alterar</button>
						<button href="" class="btn btn-link">Deletar</button>
					</td>
				</tr>
			</tbody>
		</table>
		</div>
	</div>
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary" data-backdrop="false" style="display: none;"
		data-toggle="modal" id="abrirModal" data-target="#cadastro">Launch
		demo modal</button>

	<!-- Modal -->

	<div class="modal fade" id="cadastro">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Alterar endereço</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
				<form id="form">
					<div class="form-group">
						<label>CEP</label> 
						<input type="text" class="form-control" name="nome" placeholder="Rua" value="08676-250" required>
					</div>
					<div class="form-group">
						<label>Rua</label> 
						<input type="text" class="form-control" name="cpfCnpj" placeholder="Rua" value="Rua teste" required>
					</div>		
					<div class="form-group">
						<label>Número</label> 
						<input type="text" class="form-control" name="senha" placeholder="Rua" value="100"required>
					</div>							
					<div class="form-group">
						<label>Complemento</label> 
						<input type="text" class="form-control" name="senha" placeholder="Complemento" value="Apertamento 1"required>
					</div>														
					<div class="form-group">
						<label>Bairro</label> 
						<input type="text" class="form-control" name="dtNascimento" placeholder="Bairro" value="vila figueira" required>
					</div>
					<div class="form-group">
						<label>Cidade</label> 
						<input type="text" class="form-control" name="username" placeholder="Cidade" value="São paulo" required>
					</div>	
					<div class="form-group">
						<label>Estado</label> 
						<input type="text" class="form-control" name="senha" placeholder="Estado" value="SP" required>
					</div>
					
					<div class="form-group">
						<label>País</label> 
						<input type="text" class="form-control" name="senha" placeholder="País" value="Brasil" required>
					</div>																
				</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" data-dismiss="modal">Save changes</button>
	      </div>
	    </div>
	  </div>
	</div>
</body>
</html>