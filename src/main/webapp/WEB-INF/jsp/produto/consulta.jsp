<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<script>
	var listaProdutos = [];
	var produto={};
	produto.marca="Sony";
	produto.modelo="PS4";
	produto.preco="1.500,00 R$";
	produto.estoque="100";
	produto.status="Ativo";
	listaProdutos.push(produto);
	listaProdutos = JSON.stringify(listaProdutos);
	$(document).ready(function(){
		try{
			listaProdutos = JSON.parse(listaProdutos);
		}catch(ex){
			listaProdutos = {};
		}
		
		renderizarTabela(listaProdutos);
	});
	
	function desativar(id){
		if(id === undefined){
			abrirModalSucessoOuFalha(null, "Produto desativado com sucesso!", "Falha ao desativar o produto", 1);
			return;
		}
		$("#idProduto").val(id);
		$.post("http://localhost:8888/produto/deletar", $("#form").serialize())
		.done(function(data){
			renderizarTabela(data.entidades);
			abrirModalSucessoOuFalha(data, "Produto desativado com sucesso!", "Falha ao desativar o produto", 1);
		})
		.fail(function(data){
			abrirModalSucessoOuFalha(data, "Produto desativado com sucesso!", "Falha ao desativar o produto", 1);
		});
	}
	
	function alterar(id){
		if(id === undefined){
			return;
		}
		
		$("#form")
			.attr("action", "http://localhost:8888/produto/alteracao")
			.attr("method", "POST");
		$("#idProduto").val(id);
		$("#form").submit();
	}
	
	function renderizarTabela(json){
		$("tbody").html("");
		$.each(json, function(index, produto){
			var string = "<tr><th scope='row'>" + produto.marca + "</th>"
				+ "<td>" + produto.modelo + "</td>" 
				+ "<td>" + produto.preco + "</td>"
				+ "<td>" + produto.estoque + "</td>"
				+ "<td>" + produto.status + "</td>"
				+ "<td><button type='button' class='btn btn-danger' onclick='desativar("+produto.id+")'>Desativar</button><a href='http://localhost:8888/produto/alteracao' class='btn btn-info'>Alterar</a></td>"
				+ "</tr>";
				
				$("tbody").append(string);
		});		
	}

</script>
<title>Consulta de Produtos(Admin)</title>
</head>
<body>
<form id="form">
	<input type="hidden" name='id' id="idProduto" />
</form>
	<div class="container" style="margin-top: 100px;">
		<form class="form-inline">
			<div class="form-group mb-3 col-md-4 col-md-offset-4">
				<label class="sr-only">Filtro</label> 
				<select class="custom-select" style="width: 100%">
				  <option selected>Marca</option>
				  <option value="1">Produto</option>
				  <option value="2">Status</option>
				  <option value="3">Estoque</option>
				</select>
			</div>
			<div class="form-group mx-sm-4 mb-3 col-md-4 col-md-offset-4">
				<input type="text" class="form-control" style="width: 100%" placeholder="Digite">
			</div>
			<a href="#" class="btn btn-primary mb-3">Buscar</a>
		</form>
		<table class="table table-hover">
		  <thead>
		    <tr>
		      <th scope="col">Marca</th>
		      <th scope="col">Produto</th>
		      <th scope="col">Preço</th>
		      <th scope="col">Estoque</th>
		      <th scope="col">Status</th>
		      <th scope="col">Ações</th>
		    </tr>
		  </thead>
		  <tbody>
		  </tbody>
		</table>
	</div>
	<jsp:include page="../componentes/modal.jsp"></jsp:include>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>