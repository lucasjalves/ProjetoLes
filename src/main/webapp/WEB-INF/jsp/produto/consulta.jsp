<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<script>
	var listaProdutos = ${produtos};
	$(document).ready(function(){
		
		renderizarTabela(listaProdutos);
		$('#tabela').DataTable({
			"searching" : false
		});
	});
	
	function desativar(id){
		if(id === undefined){
			abrirModalSucessoOuFalha(null, "Produto desativado com sucesso!", "Falha ao desativar o produto", 1);
			return;
		}
		$("#idProduto").val(id);
		$.post("http://localhost:8888/produto/desativar", $("#form").serialize())
		.done(function(data){		
			abrirModalSucessoOuFalha(data, "Produto desativado com sucesso!", "Falha ao desativar o produto", 1, false,false,function(){
				window.location.replace("http://localhost:8888/produto/consulta");
			});
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
			var string = "<tr><th scope='row'>" + produto.id + "</th>"
				+ "<td>" + produto.codigoBarras + "</td>" 
				+ "<td>" + produto.modelo + "</td>" 
				+ "<td>" + produto.marca + "</td>" 
				+ "<td>" + produto.precoVenda + "</td>"
				+ "<td>" + produto.estoque + "</td>"
				+ "<td>" + produto.categoria + "</td>"
				+ "<td>" + produto.ativo + "</td>"
				+ "<td><a class='btn-link'onclick='desativar("+produto.id+")' style='color: red;margin-right: 20px;'>X</a><button id='"+index+"'onclick='alterar("+produto.id+")' class='btn btn-info'>Alterar</button></td>"
				+ "</tr>";
				$("tbody").append(string);
		});		
	}

	function buscar(){
		var json = listaProdutos;
		var atributos = [];
		$("#busca .busca").each(function(){
			let valor = $(this).val().replace(/ +?/g, '');
			if(valor.length > 0){
				let atributo = $(this).attr("atributo");
				atributos.push(atributo);
			}
		});
		
		if(atributos.length > 0){
			var jsonFiltrado = $.grep(json, function(produto, i){
				for(let j = 0; j < atributos.length; j++){
					let atributo = atributos[j];
					let valor = $("#"+atributo).val().toLowerCase().replace(/\s+/g, '');
					let valorAtributoProduto = produto[atributo].toString().toLowerCase().replace(/\s+/g, '');
					if(!valorAtributoProduto.includes(valor)){
						return false;
					}
				}
				return true;
			});			
			
			remontarTabela(jsonFiltrado);
		}else{
			remontarTabela(listaProdutos);
		}

	}
	
	function remontarTabela(json){
		$('#tabela').DataTable().clear();
		$('#tabela').DataTable().destroy();
		renderizarTabela(json);
		$('#tabela').DataTable({
			"searching" : false
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
		<div class="accordion" id="accordionExample" style="margin-bottom: 20px;">
			<div class="card">
				<div class="card-header" id="headingOne">
					<h5 class="mb-0">
						<button class="btn btn-link" type="button" data-toggle="collapse"
							data-target="#collapseOne" aria-expanded="true"
							aria-controls="collapseOne">Buscar</button>
					</h5>
				</div>

				<div id="collapseOne" class="collapse show"
					aria-labelledby="headingOne" data-parent="#accordionExample">
					<div class="card-body">
						<form id="busca">
							<div class="form-row">
								<div class="col">
									<label>Modelo</label> 
									<input type="text" class="form-control busca" placeholder="Modelo" id="modelo" atributo="modelo">
								</div>
								<div class="col">
									<label>Marca</label> 
									<input type="text" class="form-control busca" placeholder="Marca" id="marca" atributo="marca">
								</div>
								<div class="col">
									<label>Código de barras</label> 
									<input type="text" class="form-control busca" placeholder="Cod. Barras" id="codigoBarras" atributo="codigoBarras">
								</div>
							</div>
							<div class="form-row">
								<div class="col">
									<label>ID</label> <input type="text" class="form-control busca" placeholder="ID" id="id" atributo="id">
								</div>
								<div class="col">
									<label>Status</label>
									 <select class="form-control busca" atributo="ativo" id="ativo">
										<option selected value="">Selecione...</option>
										<option value="true">Ativo</option>
										<option value="false">Inativo</option>
									</select>
								</div>
								<div class="col">
									<label>Categoria</label> <select class="form-control busca" atributo="categoria" id="categoria">
										<option selected value="">Selecione...</option>
										<c:forEach items="${categorias}" var="categoria">
											<option value="${categoria}">${categoria}</option>
										</c:forEach>
									</select>
								</div>							
							</div>
								<div class="form-row" style="margin-top: 20px;">
									<div class="col">
										<a href="#" class="btn btn-primary" onclick="buscar()">Buscar</a>
									</div>								
								</div>								
						</form>
					</div>
				</div>
			</div>
		</div>
		<table id="tabela" class="table table-hover">
		  <thead>
		    <tr>	
		      <th scope="col">ID</th>
		      <th scope="col">Cód. Barras</th>
		      <th scope="col">Modelo</th>
		      <th scope="col">Marca</th>
		      <th scope="col">Preço de Venda</th>
		      <th scope="col">Estoque</th>
		      <th scope="col">Categoria</th>
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


