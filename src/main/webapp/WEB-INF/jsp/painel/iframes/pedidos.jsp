<html>
<head>
<jsp:include page="../../statics.jsp"></jsp:include>
<script>

$(document).ready(function(){
	var json = ${pedidos};
	gerarTabela(json);
	$('#tabela').DataTable({
		"searching" : false
	});
});

function gerarTabela(json){
	$("#bodyTabela").html("");
	$.each(json, function(index, pedido){
		let tipo = getBotao(pedido.id)[pedido.status];
		if(tipo === undefined){
			tipo = "<td scope='row'> <td>";
		}
		var string ="<tr><th scope='row'><a href='http://localhost:8888/pedido/detalhe?id="+pedido.id+"' >"+pedido.id+"</a></th>"
		+ "<td scope='row'>"+pedido.dtPedido+"</td>"
		+ "<td scope='row'>"+pedido.totalCompra+"</td>"
		+ "<td scope='row'>"+pedido.status+"</td>"
		+ tipo;
		string = string + "</tr>";
		$("#bodyTabela").append(string);
	});
}

function irParaConfirmacao(id){
	$("#idConfirmacao").val(id);
	$("#formConfirmacao").submit();
}

function solicitarTroca(id) {
	$("#idTroca").val(id);
	$("#solicitarTroca").submit();
}

function getBotao(id) {
	return {
		"PAGO" : `<td scope='row'><a class='btn btn-success' onclick="irParaEfetivacao(`+id+`)">Visualizar</a></td>`,
		"SOLICITADO" : `<td scope='row'><a class='btn btn-success' onclick="irParaConfirmacao(`+id+`)">Comprar</a></td>`,
		"ENTREGUE" : `<td scope='row'><a class='btn btn-warning' onclick="solicitarTroca(`+id+`)">Trocar</a></td>`,
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

<form id="solicitarTroca" action="http://localhost:8888/ticket/trocacao">
	<input type="hidden" name="id" id="idTroca" />
</form>
	<div class="card">
		<div class="card-header">Pedidos</div>
		  <div class="card-body">
	<table class="table table-hover" id="tabela">
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
	</div>
</div>
</body>
</html>