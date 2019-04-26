<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<script>
	var listaCupons = ${cupons};

	function formatarValoresLista(json){
		$.each(json, function(index, cupom){
			dataVencimento = cupom.dataVencimento.split("-").reverse().join("/");
			cupom.dataVencimento = dataVencimento;
			valor = cupom.valorDesconto.toString()
			valor = parseFloat(valor).toFixed(2);
			cupom.valorDesconto = valor.replace(",",".") + " %";
		});
	}
	$(document).ready(function(){
		formatarValoresLista(listaCupons);
		renderizarTabela(listaCupons);
		$('#tabela').DataTable({
			"searching" : false
		});
		$("#dataVencimento").mask("00/00/0000");
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
			var string = "<tr><th scope='row'>" + cupom.id + "</th>"
				+ "<td>" + cupom.codigo + "</td>"
				+ "<td>" + cupom.dataVencimento + "</td>"
				+ "<td>" + cupom.valorDesconto + "</td>"
				+ "<td>" + cupom.status + "</td>"
				+ "<td><a class='btn-link' onclick='desativar("+cupom.id+")' style='color: red;margin-right: 20px;' >X</a><a href='#' onclick='alterar("+cupom.id+")' class='btn btn-info'>Alterar</a></td>"
				+ "</tr>";
				
				$("tbody").append(string);
		});		
	}
	
	function buscar(){
		var json = listaCupons;
		var atributos = [];
		$("#busca .busca").each(function(){
			let valor = $(this).val().replace(/ +?/g, '');
			if(valor.length > 0){
				let atributo = $(this).attr("atributo");
				atributos.push(atributo);
			}
		});
		
		if(atributos.length > 0){
			var jsonFiltrado = $.grep(json, function(cupom, i){
				for(let j = 0; j < atributos.length; j++){
					let atributo = atributos[j];
					let valor = $("#"+atributo).val().toLowerCase().replace(/\s+/g, '');
					let valorAtributoCupom = cupom[atributo].toString().toLowerCase().replace(/\s+/g, '');
					if(!valorAtributoCupom.includes(valor)){
						return false;
					}
				}
				return true;
			});			
			
			remontarTabela(jsonFiltrado);
		}else{
			remontarTabela(listaCupons);
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
<title>Consulta de Cupons(Admin)</title>
</head>
<body>
<form id="form">
	<input type="hidden" name='id' id="idCupom" />
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
									<label>ID</label> 
									<input type="text" class="form-control busca" placeholder="ID" id="id" atributo="id">
								</div>
								<div class="col">
									<label>Código</label> 
									<input type="text" class="form-control busca" placeholder="Código" id="codigo" atributo="codigo">
								</div>

							</div>
							<div class="form-row">
								<div class="col">
									<label>Vencimento</label> 
									<input type="text" class="form-control busca" placeholder="Data vencimento" id="dataVencimento" atributo="dataVencimento">
								</div>	
								<div class="col">
									<label>Status</label>
									 <select class="form-control busca" atributo="status" id="status">
										<option selected value="">Selecione...</option>
										<option value="true">Ativo</option>
										<option value="false">Inativo</option>
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