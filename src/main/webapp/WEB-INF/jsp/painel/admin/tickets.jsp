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
		<div class="card-header">Tickets</div>
		  <div class="card-body">
			<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">nº Ticket</th>
					<th scope="col">nº Pedido</th>
					<th scope="col">Solicitante</th>
					<th scope="col">Data</th>
					<th scope="col">Status</th>
					<th scope="col">Tipo</th>
					<th scope="col">Ações</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row">1</th>
					<td><a href="http://localhost:8888/pedido/efetivacao" target="_blank" class="btn btn-link">1</a></td>
					<td><a href="http://localhost:8888/cliente/detalhe" class="btn btn-link">111.222.333-44</a></td>
					<td>24/02/2019</td>
					<td><label style="color: #FFB300;">EM ANÁLISE</label></td>
					<td><label>DEVOLUÇÃO</label></td>
					<td>
						<button class="btn btn-info" onclick="$('#abrirModal').click();">Detalhes</button>
					</td>
				</tr>
				<tr>
					<th scope="row">2</th>
					<td><a href="http://localhost:8888/pedido/efetivacao" target="_blank" class="btn btn-link">2</a></td>
					<td><a href="http://localhost:8888/cliente/detalhe" class="btn btn-link">111.222.333-44</a></td>
					<td>24/02/2019</td>
					<td><label style="color: #FFB300;">EM ANÁLISE</label></td>
					<td><label>TROCA</label></td>
					<td>
						<button href="" class="btn btn-info" onclick="$('#abrirModalTroca').click();">Detalhes</button>
						
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
	        <h5 class="modal-title" id="exampleModalLabel">Produtos</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
			<table class="table" style="margin-top: 15px;">
				<thead>
					<tr>
						<th scope="col">Produtos</th>
						<th scope="col">Quantidade</th>
						<th scope="col">Preço</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><strong>Playstation 4</strong></td>
						<td>1</td>
						<td>R$ 1.500,00</td>
					</tr>
				</tbody>
			</table>
	      </div>
	      <div class="modal-footer">
	      <button type="button" class="btn" data-dismiss="modal">Fechar</button>
	        <button type="button" class="btn btn-success" data-dismiss="modal">Aprovar</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<button type="button" class="btn btn-primary" data-backdrop="false" style="display: none;"
		data-toggle="modal" id="abrirModalTroca" data-target="#cadastroTroca">Launch
		demo modal</button>

	<!-- Modal -->

	<div class="modal fade" id="cadastroTroca">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Produtos</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	<strong style="color: green;">Produto disponível em estoque para troca</strong>
			<table class="table" style="margin-top: 50px; margin-bottom: 30px;">
				<thead>
					<tr>
						<th scope="col">Produtos</th>
						<th scope="col">Quantidade</th>
						<th scope="col">Preço</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><strong>Playstation 4</strong></td>
						<td>1</td>
						<td>R$ 1.500,00</td>
					</tr>
				</tbody>
			</table>
	      </div>
	      <div class="modal-footer">
	      <button type="button" class="btn" data-dismiss="modal">Fechar</button>
	        <button type="button" class="btn btn-success" data-dismiss="modal">Aprovar</button>
	      </div>
	    </div>
	  </div>
	</div>	
</body>
</html>