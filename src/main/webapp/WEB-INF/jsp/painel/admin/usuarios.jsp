<html>
<head>
<jsp:include page="../../statics.jsp"></jsp:include>
<script>
var usuarios = ${usuarios}

$(document).ready(function(){
	gerarTabela(usuarios);
	$('#tabela').DataTable({
		"searching" : false,
		"iDisplayLength": 1000
	});
});

function gerarTabela(json){
	$("#bodyTabela").html("");
	$.each(json, function(index, cliente){
		var string ="<tr><th scope='row'><a href='http://localhost:8888/pedido/detalhe?id="+cliente.id+"' >"+cliente.id+"</a></th>"
		+ "<td scope='row'>"+cliente.cpfCnpj+"</td>"
		+ "<td scope='row'>"+cliente.nome+"</td>"
		+ "<td scope='row'>"+cliente.email+"</td>"
		+ "<td scope='row'>"+cliente.ativo+"</td>";
		string = string + "</tr>";
		$("#bodyTabela").append(string);
	});
}

</script>
</head>
<body>
	<div class="card">
		<div class="card-header">Usuários</div>
		  <div class="card-body">
			<table class="table table-hover" id="tabela">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">CPF</th>
					<th scope="col">Nome</th>
					<th scope="col">Email</th>
					<th scope="col">Status</th>
				</tr>
			</thead>
			<tbody id="bodyTabela">
			</tbody>
		</table>
		</div>
	</div>
</body>
</html>
