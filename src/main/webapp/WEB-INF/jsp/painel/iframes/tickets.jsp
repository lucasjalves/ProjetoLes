<html>
<head>
<jsp:include page="../../statics.jsp"></jsp:include>
<script>
$(document).ready(function(){
	var json = ${tickets};
	gerarTabela(json);
	$('#tabela').DataTable({
		"searching" : false
	});
});

function gerarTabela(json){
	$("#bodyTabela").html("");
	$.each(json, function(index, ticket){
		var string ="<tr><th scope='row'><a href='http://localhost:8888/ticket/detalhe?id="+ticket.id+"' >"+ticket.id+"</a></th>"
		+ "<td scope='row'>"+ticket.dtPedido+"</td>"
		+ "<td scope='row'>"+ticket.status+"</td>"
		+ "<td scope='row'>"+ticket.tipo+"</td>"
		string = string + "</tr>";
		$("#bodyTabela").append(string);
	});
}
</script>
</head>
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
				</tr>
			</thead>
			<tbody id="bodyTabela">
			</tbody>
		</table>
		</div>
	</div>
</body>
</html>