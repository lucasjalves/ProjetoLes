<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<title>Painel</title>
<script>
	$(document).ready(function(){
		$(".rota").each(function(){
			
			$(this).on("click", function(){
				$("iframe").attr("src", $(this).attr("rota"));
			});
			
		});		
	});

</script>
<style>
</style>
</head>
<body>
	<div class="container spacer"
		style="width: 1280px !important; max-width: 1280px !important;">
		<div class="row">
			<div class="col-sm-3">
				<div class="list-group">
					<a href="#" class="list-group-item list-group-item-action rota" rota="http://localhost:8888/cliente/dados">Meus dados </a> 
					<a href="#" class="list-group-item list-group-item-action rota" rota="http://localhost:8888/endereco/consulta">Meus endereços</a>
					<a href="#" class="list-group-item list-group-item-action rota" rota="http://localhost:8888/cartao/consulta">Meus cartões</a>
					<a href="#" class="list-group-item list-group-item-action rota" rota="http://localhost:8888/pedido/consulta">Meus pedidos</a>
					<a href="#" class="list-group-item list-group-item-action rota" rota="http://localhost:8888/ticket/consulta">Meus tickets</a>
					<p class="list-group-item list-group-item-dark">Admin</p>
					<a href="#" class="list-group-item list-group-item-action rota" rota="http://localhost:8888/ticket/consultaAdmin">Tickets</a>
					<a href="#" class="list-group-item list-group-item-action rota" rota="http://localhost:8888/pedido/consultaAdmin">Pedidos</a>
					<a href="#" class="list-group-item list-group-item-action rota" rota="http://localhost:8888/grafico">Gráfico</a>				
				</div>
			</div>
			<div class="col-sm-9 embed-responsive embed-responsive-16by9">
				<div>
					<iframe class= "embed-responsive-item" src="http://localhost:8888/cliente/dados"></iframe>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../componentes/modal.jsp"></jsp:include>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>