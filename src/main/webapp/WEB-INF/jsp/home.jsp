<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<script>
	var listaProdutos = "${listaProdutos}";
	var listaProdutos = [];
	var produto1 = {
		nome: "Playstation 4",
		descricao: "Teste",
		marca: "Sony",
		modelo: "PS4",
		preco: "1,500.00 R$"
	}

	var produto2 = {
			nome: "Xbox One",
			descricao: "Teste",
			marca: "Microsoft",
			modelo: "XONE",
			preco: "1,500.00 R$"
	}
		
	
	listaProdutos.push(produto1);
	listaProdutos.push(produto2);
	listaProdutos.push(produto2);
	listaProdutos.push(produto2);
	listaProdutos.push(produto2);
	listaProdutos.push(produto2);

	
	listaProdutos = JSON.stringify(listaProdutos);
	$(document).ready(function(){
		$(".footer").attr("style", "bottom: -75px;")
		try{
			listaProdutos = 
				JSON.parse(listaProdutos);
		}catch(ex){
			listaProdutos = {};
		}
		renderizarHome(listaProdutos);
	});	
	
	function renderizarHome(json){
		$.each(json, function(index, produto){
			var cardModelo = 
				$("#cardModelo").clone();
			
			cardModelo.find(".card-title").text(produto.nome);
			cardModelo.find(".card-text").text(produto.descricao);
			cardModelo.find(".linha1").text(produto.marca);
			cardModelo.find(".linha2").text(produto.modelo);
			cardModelo.find(".linha3").text(produto.preco);
			cardModelo.find(".card").show();
			$("#main").append(cardModelo.html());
		});
	}

</script>
<title>Compre um produto</title>
</head>
<body>
	<div class="container spacer" id="main">
		
	</div>
	<jsp:include page="componentes/modal.jsp"></jsp:include>
	<jsp:include page="footer.jsp"></jsp:include>
	<jsp:include page="componentes/cardProduto.jsp"></jsp:include>
</body>
</html>