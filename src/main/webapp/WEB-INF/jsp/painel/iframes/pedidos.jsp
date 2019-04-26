<html>
<head>


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
<script>
function devolverOuTrocar(){
	$("#abrirModal").click();
}

$(document).ready(function(){
	var json = ${pedidos};
	gerarTabela(json);
});

function gerarTabela(json){
	$("#bodyTabela").html("");
	$.each(json, function(index, pedido){
		var string ="<tr><th scope='row'>"+pedido.id+"</th>"
		+ "<td scope='row'>"+pedido.dtPedido+"</td>"
		+ "<td scope='row'>"+pedido.totalCompra+"</td>"
		+ "<td scope='row'>"+pedido.status+"</td>"
		+ getBotao(pedido.id)[pedido.status];
		string = string + "</tr>";
		$("#bodyTabela").append(string);
	});
}

function irParaConfirmacao(id){
	$("#idConfirmacao").val(id);
	$("#formConfirmacao").submit();
}

function getBotao(id) {
	return {
		"PAGO" : "<td scope='row'><a class='btn btn-warning' onclick='irParaEfetivacao("+id+")'>Visualizar</a></td>",
		"SOLICITADO" : "<td scope='row'><a class='btn btn-warning' onclick='irParaConfirmacao("+id+")'>Comprar</a></td>"
	};
	
}

function irParaEfetivacao(id){
	$("#idEfetivacao").val(id);
	$("#formEfetivacao").submit();
}
</script>
<style>
.modal-backdrop, .modal-backdrop.fade.in{
opacity: 0.1;
}
</style>
</head>
<body>
<form id="formConfirmacao" target="_parent" action="http://localhost:8888/pedido/confirmacao">
	<input type="hidden" name="id" id="idConfirmacao" />
</form>

<form id="formEfetivacao" target="_parent" action="http://localhost:8888/pedido/efetivacao">
	<input type="hidden" name="id" id="idEfetivacao" />
</form>
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">Nº Pedido</th>
				<th scope="col">Data</th>
				<th scope="col">Total R$</th>
				<th scope="col">Status</th>
				<th scope="col">Ações</th>
			</tr>
		</thead>
		<tbody id="bodyTabela">

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
					<h6>Você deseja</h6>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio"
							name="inlineRadioOptions" id="inlineRadio1" value="option1">
						<label class="form-check-label" for="inlineRadio1">Devolver</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio"
							name="inlineRadioOptions" id="inlineRadio2" value="option2"
							checked> <label class="form-check-label"
							for="inlineRadio2">Trocar</label>
					</div>
					<table class="table" style="margin-top: 50px; margin-bottom: 30px;">
						<thead>
							<tr>
								<th scope="col">Produtos</th>
								<th scope="col" style="width: 100px;">Quantidade</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><strong>Playstation 4</strong></td>
								<td><input type="number" class="form-control" value="1" /></td>
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