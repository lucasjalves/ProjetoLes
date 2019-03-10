<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<script>
	var listaProdutos = ${listaProdutos};
	$(document).ready(function(){
		$(".footer").attr("style", "bottom: -75px;")
		renderizarHome(listaProdutos);
	});	
	
	function renderizarHome(json){
		$.each(json, function(index, produto){
			var cardModelo = 
				$("#cardModelo").clone();
			
			cardModelo.find(".card-title").text(produto.modelo);
			cardModelo.find(".card-text").text(produto.descricao);
			cardModelo.find(".linha1").text(produto.marca);
			cardModelo.find(".linha2").text(produto.modelo);
			cardModelo.find(".linha3").text("R$ " + produto.precoVenda);
			cardModelo.find(".card").show();
			cardModelo.find(".comprar").attr("onclick", "irParaCarrinho('"+produto.id+"')");
			$("#main").append(cardModelo.html());
			
		});
	}

</script>
<title>Compre um produto</title>
</head>
<body>
<form id="form" method="POST" action="http://localhost:8888/produto/detalhe">
	<input type="hidden" id="id" name="id" />
</form>
	<div class="container spacer" id="main">
		
	</div>
	<jsp:include page="componentes/modal.jsp"></jsp:include>
	<jsp:include page="footer.jsp"></jsp:include>
	<jsp:include page="componentes/cardProduto.jsp"></jsp:include>
</body>
</html>