<html>
<head>
<jsp:include page="../../statics.jsp"></jsp:include>
<script>
$(document).ready(function(){
	var json = ${enderecos};
	gerarTabela(json);
	$('#tabela').DataTable({
		"searching" : false
	});
});

function gerarTabela(json){
	$("#bodyTabela").html("");
	$.each(json, function(index, endereco){
		var string ="<tr><th scope='row'><a href='http://localhost:8888/endereco/detalhe?id="+endereco.id+"' >"+endereco.id+"</a></th>"
		+ "<td scope='row'>"+endereco.nome+"</td>"
		+ "<td scope='row'>"+endereco.uf+", "+endereco.cidade+", "+endereco.rua+"</td>"
		+ "<td scope='row'><a href='http://localhost:8888/endereco/detalhe?id="+endereco.id+"' >Alterar</a></td>"
		string = string + "</tr>";
		$("#bodyTabela").append(string);
	});
}


</script>
</head>
<body >
	<div class="card">
		<div class="card-header">Endere�os<span class="btn btn-link" style="margin-left: 50px;" onclick="cadastrar()">+ cadastrar novo</span></div>
		  <div class="card-body">
			<table class="table table-hover" id="tabela">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Nome</th>
					<th scope="col">Endere�o</th>					
					<th scope="col">A��es</th>
				</tr>
			</thead>
			<tbody id="bodyTabela">
			</tbody>
		</table>
		</div>
	</div> 
</body>
</html>