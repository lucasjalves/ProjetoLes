<html>
<head>
<jsp:include page="../../statics.jsp"></jsp:include>
<script>
$(document).ready(function(){
	var json = ${tickets};
	gerarTabela(json);
	$('#tabela').DataTable({
		"searching" : false,
		"iDisplayLength": 100
	});
});

function gerarTabela(json){
	$("#bodyTabela").html("");
	$.each(json, function(index, ticket){
		let tipo = gerarBotao(ticket.tipo, ticket.status, ticket.id);
		if(tipo === undefined){
			tipo = "<td scope='row'> </td>";
		}
		var string ="<tr><th scope='row'><a href='http://localhost:8888/ticket/detalhe?id="+ticket.id+"' >"+ticket.id+"</a></th>"
		+ "<td scope='row'>"+ticket.dtPedido+"</td>"
		+ "<td scope='row'>"+ticket.status+"</td>"
		+ "<td scope='row'>"+ticket.tipo+"</td>"
		+ tipo;
		string = string + "</tr>";
		$("#bodyTabela").append(string);
	});
}

function gerarBotao(tipo, status, id){
	if(tipo === "DEVOLUCAO") {
		const tipos = {
			"SOLICITADO" : `<td scope='row'><a class='btn btn-warning' onclick='atualizarTicket(`+id+`, "APROVADO")'>Aceitar</a></td>`
		};
		return tipos[status];
	} else {
		const tipos = {
			"SOLICITADO" : `<td scope='row'><a class='btn btn-warning' onclick='atualizarTicket(`+id+`, "APROVADO")'>Aceitar</a></td>`,
			"APROVADO" : `<td scope='row'><a class='btn btn-warning' onclick='atualizarTicket(`+id+`, "TRANSPORTE")'>Enviar</a></td>`,
			"TRANSPORTE" : `<td scope='row'><a class='btn btn-warning' onclick='atualizarTicket(`+id+`, "ENTREGUE")'>Entregar</a></td>`
		};
		return tipos[status];
	}
}

function atualizarTicket(id, status){
	$("#atualizarStatusDevolucao #statusTicket").val(status);
	$("#atualizarStatusDevolucao #id").val(id);
	$("#atualizarStatusDevolucao").submit();
}

</script>
</head>

<form id="atualizarStatusDevolucao" action="http://localhost:8888/ticket/alterarStatus" method="POST">
	<input type="hidden" name="statusTicket" id="statusTicket" />
	<input type="hidden" name="id" id="id" />
</form>
<body >
	<div class="card">
		<div class="card-header">Tickets</div>
		  <div class="card-body">
			<table class="table table-hover" id="tabela">
			<thead>
				<tr>
					<th scope="col">nº Ticket</th>
					<th scope="col">Data</th>
					<th scope="col">Status</th>
					<th scope="col">Tipo</th>
					<th scope="col">Ação</th>
				</tr>
			</thead>
			<tbody id="bodyTabela">
			</tbody>
		</table>
		</div>
	</div>
</body>
</html>