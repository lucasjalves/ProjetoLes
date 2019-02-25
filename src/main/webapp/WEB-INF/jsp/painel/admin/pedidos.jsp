<html>
<head>
<script>
function devolverOuTrocar(){
	$("#abrirModal").click();
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
<body>
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">Nº Pedido</th>
				<th scope="col">Comprador</th>
				<th scope="col">Data</th>
				<th scope="col">Total R$</th>
				<th scope="col">Status</th>
				<th scope="col">Ações</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th scope="row"><button class="btn btn-link" onclick="$('#abrirModal').click();">1</button></th>
				<td><a href="http://localhost:8888/cliente/detalhe" class="btn btn-link">111.222.333-44</a></td>
				<td>20/02/2019</td>
				<td>1.000,00</td>
				<td style="color: #FFB300;">NÃO PAGO</td>
				<td></td>
			</tr>
			<tr>
				<th scope="row"><a class="btn btn-link" href="http://localhost:8888/pedido/efetivacao" target="_blank">2</a></th>
				<td><a href="http://localhost:8888/cliente/detalhe" class="btn btn-link">222.222.333-44</a></td>
				<td>21/02/2019</td>
				<td>1.500,00</td>
				<td style="color: green;">ENTREGUE</td>
				<td></td>
			</tr>
			<tr>
				<th scope="row"><button  class="btn btn-link" onclick="$('#abrirModal').click();">3</button></th>
				<td><a href="http://localhost:8888/cliente/detalhe" class="btn btn-link">444.222.111-33</a></td>
				<td>22/02/2019</td>
				<td>500,00</td>
				<td style="color: red;">CANCELADO</td>
				<td></td>
			</tr>
			<tr>
				<th scope="row"><a class="btn btn-link" href="http://localhost:8888/pedido/efetivacao" target="_blank">4</a></th>
				<td><a href="http://localhost:8888/cliente/detalhe" class="btn btn-link">444.222.111-33</a></td>
				<td>23/02/2019</td>
				<td>1.500,00</td>
				<td style="color: green;">APROVADO</td>
				<td>
					<button class="btn btn-danger">Cancelar</button>			
					<button class="btn btn-success">Enviar</button>			
				</td>
			</tr>	
			<tr>
				<th scope="row"><a class="btn btn-link" href="http://localhost:8888/pedido/efetivacao" target="_blank">5</a></th>
				<td><a href="http://localhost:8888/cliente/detalhe" class="btn btn-link">444.222.111-33</a></td>
				<td>23/02/2019</td>
				<td>1.500,00</td>
				<td style="color: green;">EM TRANSPORTE</td>
				<td>		
					<button class="btn btn-success">Entregue</button>			
				</td>
			</tr>					
		</tbody>
	</table>

	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary" data-backdrop="false" style="display: none;"
		data-toggle="modal" id="abrirModal" data-target="#exampleModalCenter">Launch
		demo modal</button>

	<!-- Modal -->
	<div class="modal fade" id="exampleModalCenter" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Devolução/troca</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<table class="table" style="margin-top: 15px;">
						<thead>
							<tr>
								<th scope="col">Produtos</th>
								<th scope="col" style="width: 100px;">Quantidade</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><strong>Playstation 4</strong></td>
								<td><strong>1</strong></td>
							</tr>
						</tbody>
					</table>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">Confirmar</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>