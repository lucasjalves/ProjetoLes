<html>
<head>
<jsp:include page="../../statics.jsp"></jsp:include>
<script>
function devolverOuTrocar(){
	$("#abrirModal").click();
}

$(document).ready(function(){
	var pedidos = ${pedidos};
	gerarTabela(pedidos);
	$('#tabela').DataTable({
		"searching" : false
	});
});

function gerarTabela(json){
	$("#bodyTabela").html("");
	$.each(json, function(index, pedido){
		let tipo = getBotao(pedido.id)[pedido.status];
		if(tipo === undefined){
			tipo = "<td scope='row'> </td>";
		}
		var string ="<tr><th scope='row'><a href='http://localhost:8888/pedido/detalhe?id="+pedido.id+"' >"+pedido.id+"</a></th>"
		+ "<td scope='row'>"+pedido.idCliente+"</td>"
		+ "<td scope='row'>"+pedido.dtPedido+"</td>"
		+ "<td scope='row'>"+pedido.totalCompra+"</td>"
		+ "<td scope='row'>"+pedido.status+"</td>"
		+ tipo;
		string = string + "</tr>";
		$("#bodyTabela").append(string);
	});
}

function getBotao(id) {
	return {
		"PAGO" : `<td scope='row'><a class='btn btn-warning' onclick='atualizarPedido(`+id+`, "TRANSPORTE")'>ENVIAR</a></td>`,
		"TRANSPORTE" :`<td scope='row'><a class='btn btn-warning' onclick='atualizarPedido(`+id+`, "ENTREGUE")'>ENTREGUE</a></td>`
	};
	
}

function atualizarPedido(id, status){
	$("#formPedido").find("#id").val(id);
	$("#formPedido").find("#status").val(status);
	$("#formPedido").submit();
}
</script>

<style>
.modal-backdrop, .modal-backdrop.fade.in{
opacity: 0.1;
}
</style>
</head>
<form id="formPedido" method="POST" action="http://localhost:8888/pedido/alterarStatus">
	<input type="hidden" name="id" id="id" />
	<input type="hidden" name="statusPedido" id="status" />
</form>
<body>
<div class="card">
	<div class="card-header">Pedido</div>
	  <div class="card-body">
	<table class="table table-hover" id="tabela">
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
		<tbody id="bodyTabela">
				
		</tbody>
	</table>
	</div>
</div>

</body>
</html>