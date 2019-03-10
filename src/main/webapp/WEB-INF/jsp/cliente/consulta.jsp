<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<script>
	var listaClientes = ${listaClientes};
		
	$(document).ready(function(){
		renderizarTabela(listaClientes);
		$("#cpfCnpj").mask("000.000.000-00");
		$("#dtNascimento").mask("00/00/0000");
		$('#tabela').DataTable({
			"searching" : false
		});
	});
	
	function deletar(id){
		if(id === undefined){
			return;
		}
		$("#idCliente").val(id);
		$.post("http://localhost:8888/cliente/deletar", $("#form").serialize())
		.done(function(data){
			if(abrirModalSucessoOuFalha(data, "Cliente excluido com sucesso!", "Falha ao excluir o cliente", 1, false, false , function(){
				window.location.replace("http://localhost:8888/cliente/consulta");
			}));
		})
		.fail(function(data){
			abrirModalSucessoOuFalha(data, "Cliente excluido com sucesso!", "Falha ao excluir o cliente", 1);
		});
	}
	
	function alterar(id){
		if(id === undefined){
			return;
		}
		
		$("#form")
			.attr("action", "alteracao")
			.attr("method", "POST");
		$("#idCliente").val(id);
		$("#form").submit();
	}
	
	function renderizarTabela(json){
		$("tbody").html("");
		$.each(json, function(index, cliente){
			var string = "<tr><th scope='row'>" + cliente.id + "</th>"
				+ "<td  style='width: 200px;'>" + cliente.cpfCnpj + "</td>" 
				+ "<td  style='width: 330px;'>" + cliente.nome + "</td>" 
				+ "<td>" + cliente.dtNascimento + "</td>" 
				+ "<td style='width: 300px;'>" + cliente.email + "</td>" 
				+ "<td>" + cliente.ativo + "</td>" 
				+ "<td>" + cliente.username + "</td>"
				+ "<td><a class='btn-link' onclick='deletar("+cliente.id+")' style='color: red;margin-right: 20px;'>X</a><button type='button' class='btn btn-info' onclick='alterar("+cliente.id+")'>Alterar</button></td>"
				+ "</tr>";
				
				$("tbody").append(string);
		});		
	}

	function buscar(){
		var json = listaClientes;
		var atributos = [];
		$("#busca .busca").each(function(){
			let valor = $(this).val().replace(/ +?/g, '');
			if(valor.length > 0){
				let atributo = $(this).attr("atributo");
				atributos.push(atributo);
			}
		});
		
		if(atributos.length > 0){
			var jsonFiltrado = $.grep(json, function(cliente, i){
				for(let j = 0; j < atributos.length; j++){
					let atributo = atributos[j];
					let valor = $("#"+atributo).val().toLowerCase().replace(/\s+/g, '');
					let valorAtributoCliente = cliente[atributo].toString().toLowerCase().replace(/\s+/g, '');
					if(!valorAtributoCliente.includes(valor)){
						return false;
					}
				}
				return true;
			});			
			
			remontarTabela(jsonFiltrado);
		}else{
			remontarTabela(listaClientes);
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
<title>Consulta de Clientes</title>
</head>
<body>
<form id="form">
	<input type="hidden" name='id' id="idCliente" />
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
									<label>CPF/CNPJ</label> 
									<input type="text" class="form-control busca" placeholder="CPF/CNPJ" id="cpfCnpj" atributo="cpfCnpj">
								</div>
								<div class="col">
									<label>Nome</label> 
									<input type="text" class="form-control busca" placeholder="Nome" id="nome" atributo="nome">
								</div>
							</div>
							<div class="form-row">
								<div class="col">
									<label>Nascimento</label> <input type="text" class="form-control busca" placeholder="Data nascimento" id="dtNascimento" atributo="dtNascimento">
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
									<label>Username</label> 
									<input type="text" class="form-control busca" placeholder="Username" id="username" atributo="username">
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
		<table class="table table-hover" id="tabela">
		  <thead>
		    <tr>
		      <th scope="col">ID</th>
		      <th scope="col">CPF/CNPJ</th>
		      <th scope="col">Nome</th>
		      <th scope="col">Data nascimento</th>
		      <th scope="col">Email</th>
		      <th scope="col">Status</th>
		      <th scope="col">Username</th>
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