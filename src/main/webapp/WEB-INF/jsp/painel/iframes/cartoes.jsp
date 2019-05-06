<html>
<head>
<jsp:include page="../../statics.jsp"></jsp:include>
<script>
$(document).ready(function(){
	var json = ${cartoes};
	gerarTabela(json);
	$('#tabela').DataTable({
		"searching" : false
	});
});


function gerarTabela(json){
	$("#bodyTabela").html("");
	$.each(json, function(index, cartao){
		var cartaoNumero = cartao.numero.substring(cartao.numero.length - 4);
		var string ="<tr><th scope='row'><a href='http://localhost:8888/cartao/detalhe?id="+cartao.id+"' >"+cartao.id+"</a></th>"
		+ "<td scope='row'>"+cartao.bandeira+"</td>"
		+ "<td scope='row'>****.****.****."+cartaoNumero+"</td>"
		string = string + "</tr>";
		$("#bodyTabela").append(string);
	});
}

func
</script>
</head>
<body >
	<div class="card">
		<div class="card-header">Cartões<span class="btn btn-link" style="margin-left: 50px;" onclick="cadastrar()">+ cadastrar novo</span></div>
		  <div class="card-body">
			<table class="table table-hover" id="tabela">
			<thead>
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Bandeira</th>
					<th scope="col">Número</th>
				</tr>
			</thead>
			<tbody id="bodyTabela">
			</tbody>
		</table>
		</div>
	</div>
</body>
</html>