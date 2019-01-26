<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<script>
	var listaClientes = '${listaClientes}';
	$(document).ready(function(){
		try{
			listaClientes = JSON.parse(listaClientes);
		}catch(ex){
			listaClientes = {};
		}
		
		renderizarTabela(listaClientes);
	});
	
	function deletar(id){
		if(id === undefined){
			return;
		}
		$("#idCliente").val(id);
		$.post("http://localhost:8888/cliente/deletar", $("#form").serialize())
		.done(function(data){
			renderizarTabela(data.entidades);
			abrirModalSucessoOuFalha(data, "Cliente excluido com sucesso!", "Falha ao excluir o cliente", 1);
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
			var string = "<tr><th scope='row'>" + cliente.cpfCnpj + "</th>"
				+ "<td>" + cliente.nome + "</td>" 
				+ "<td>" + cliente.username + "</td>"
				+ "<td><button type='button' class='btn btn-danger' onclick='deletar("+cliente.id+")'>Deletar</button><button type='button' class='btn btn-info' onclick='alterar("+cliente.id+")'>Alterar</button></td>"
				+ "</tr>";
				
				$("tbody").append(string);
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
		<table class="table table-hover">
		  <thead>
		    <tr>
		      <th scope="col">CPF/CNPJ</th>
		      <th scope="col">Nome</th>
		      <th scope="col">Email</th>
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