<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<script>
	var listaCupons = [];
	var cupom={};
	cupom.codigo="AABB11";
	cupom.dtVencimento="23/07/2019";
	cupom.desconto="100%";
	cupom.status="Ativo";
	listaCupons.push(cupom);
	listaCupons = JSON.stringify(listaCupons);
	$(document).ready(function(){
		try{
			listaCupons = JSON.parse(listaCupons);
		}catch(ex){
			listaCupons = {};
		}
		
		renderizarTabela(listaCupons);
	});
	
	function desativar(id){
		if(id === undefined){
			abrirModalSucessoOuFalha(null, "Cupom desativado com sucesso!", "Falha ao desativar o cupom", 1);
			return;
		}
		$("#idCupom").val(id);
		$.post("http://localhost:8888/cupom/deletar", $("#form").serialize())
		.done(function(data){
			renderizarTabela(data.entidades);
			abrirModalSucessoOuFalha(data, "Cupom desativado com sucesso!", "Falha ao desativar o cupom", 1);
		})
		.fail(function(data){
			abrirModalSucessoOuFalha(data, "Cupom desativado com sucesso!", "Falha ao desativar o cupom", 1);
		});
	}
	
	function alterar(id){
		if(id === undefined){
			return;
		}
		
		$("#form")
			.attr("action", "http://localhost:8888/cupom/alteracao")
			.attr("method", "POST");
		$("#idCupom").val(id);
		$("#form").submit();
	}
	
	function renderizarTabela(json){
		$("tbody").html("");
		$.each(json, function(index, cupom){
			var string = "<tr><th scope='row'>" + cupom.codigo + "</th>"
				+ "<td>" + cupom.dtVencimento + "</td>"
				+ "<td>" + cupom.desconto + "</td>"
				+ "<td>" + cupom.status + "</td>"
				+ "<td><button type='button' class='btn btn-danger' onclick='desativar("+cupom.id+")'>Desativar</button><a href='http://localhost:8888/cupom/alteracao' class='btn btn-info'>Alterar</a></td>"
				+ "</tr>";
				
				$("tbody").append(string);
		});		
	}

</script>
<title>Consulta de Cupons(Admin)</title>
</head>
<body>
<form id="form">
	<input type="hidden" name='id' id="idCupom" />
</form>
	<div class="container" style="margin-top: 100px;">
		<form class="form-inline">
			<div class="form-group mb-3 col-md-4 col-md-offset-4">
				<label class="sr-only">Filtro</label> 
				<select class="custom-select" style="width: 100%">
				  <option value="1">Código</option>
				  <option value="2">Data vencimento</option>
				  <option value="3">Desconto</option>
				  <option value="4">Status</option>
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
		      <th scope="col">Código</th>
		      <th scope="col">Data vencimento</th>
		      <th scope="col">Desconto</th>
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