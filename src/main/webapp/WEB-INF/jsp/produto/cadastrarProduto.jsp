<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/esm/popper.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
	<script
  src="https://code.jquery.com/jquery-3.3.1.js"
  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
  crossorigin="anonymous"></script>
<script>
	$(document).ready(function() {
		$("#divEletronico").css("display", "none");
		$("#divProduto").css("display", "none");
		$("#divFerramenta").css("display", "none");
		$("#divPerecivel").css("display", "none");
	    });
	
	$("#tipoItem").change(function(){
		alert("oioioioi");
		if($("#tipoItem").val =="Produto"){
			$("#divEletronico").css("display", "none");
			$("#divProduto").css("display", "true");
			$("#divFerramenta").css("display", "none");
			$("#divPerecivel").css("display", "none");
		}
		
		else if(tipoItem.value =="Ferramenta"){
			$("#divEletronico").css("display", "none");
			$("#divProduto").css("display", "none");
			$("#divFerramenta").css("display", "true");
			$("#divPerecivel").css("display", "none");
		}
		
		else if(tipoItem.value =="Eletronico"){
			$("#divEletronico").css("display", "true");
			$("#divProduto").css("display", "none");
			$("#divFerramenta").css("display", "none");
			$("#divPerecivel").css("display", "none");
		}
		
		else if(tipoItem.value =="Pereciveis"){
			$("#divEletronico").css("display", "none");
			$("#divProduto").css("display", "none");
			$("#divFerramenta").css("display", "none");
			$("#divPerecivel").css("display", "true");
		}
	});

</script>
</head>
<style>
 .alinhaTudo{
 	float:left;
 }

</style>
<body>
	<div class="container">
		<form action="/produto/cadastrar/efetivar">
			<div class="form-row ">			
				<div class="form-group">
					<label for="sel1">Tipo:</label> 
					<select class="form-control" id="tipoItem">
						<option>Escolha</option>
						<option>Ferramenta</option>
						<option>Eletronico</option>
						<option>Pereciveis</option>
						<option>Produto</option>
					</select>
				</div>
			</div>
			<div id="divProduto">
						<div class="form-group col-md-4 alinhaTudo">
							<label>Nome do Produto </label> <input type="text"
								class="form-control obrigatorio" name="produtoNome" id="produtoNome"
								placeholder="Digite o nome completo">
						</div>
						<div class="form-group col-md-2 alinhaTudo">
							<label>Data de Validade </label> <input type="date"
								class="form-control obrigatorio" name="dtValidadeProduto" id="qtde">
						</div>
			</div>
				<div id="divEletronico">
						<div class="form-group col-md-4 alinhaTudo">
							<label>Nome do Componente </label> <input type="text"
								class="form-control obrigatorio" name="eletronicoNome" id="eletronicoNome"
								placeholder="Digite o nome completo">
						</div>
						<div class="form-group col-md-4 alinhaTudo">
							<label>Modelo </label> <input type="text"
								class="form-control obrigatorio" name="modelo" id="modelo"
								placeholder="Digite o modelo">
						</div>
						
						<div class="form-group col-md-4 alinhaTudo">
							<label>Marca </label> <input type="text"
								class="form-control obrigatorio" name="marca" id="marca"
								placeholder="Digite a marca">
						</div>
				</div>
				<div id="divPerecivel">
						<div class="form-group col-md-4 alinhaTudo">
							<label>Nome do Perecivel </label> <input type="text"
								class="form-control obrigatorio" name="produtoNome" id="produtoNome"
								placeholder="Digite o nome completo">
						</div>
						<div class="form-group col-md-2 alinhaTudo">
							<label>Data de Validade </label> <input type="date"
								class="form-control obrigatorio" name="dtValidadePerecivel" id="dtValidadePerecivel">
						</div>
						<div class="form-group col-md-2 alinhaTudo">
							<label>Marca Perecivel </label> <input type="date"
								class="form-control obrigatorio" name="marcaPerecivel" id="marcaPerecivel">
						</div>
			</div>
			<div id="divFerramenta">
						<div class="form-group col-md-4 alinhaTudo">
							<label>Nome do Perecivel </label> <input type="text"
								class="form-control obrigatorio" name="produtoNome" id="produtoNome"
								placeholder="Digite o nome completo">
						</div>
						<div class="form-group col-md-2 alinhaTudo">
							<label>Data de Validade </label> <input type="date"
								class="form-control obrigatorio" name="dtValidadePerecivel" id="dtValidadePerecivel">
						</div>
						<div class="form-group col-md-2 alinhaTudo">
							<label>Marca Perecivel </label> <input type="date"
								class="form-control obrigatorio" name="marcaPerecivel" id="marcaPerecivel">
						</div>
			</div>
			<br><br>
			<div class="form-row alinhaTudo">
				<div class="form-group col-md-2">
					<label>Preço</label> <input type="email"
						class="form-control obrigatorio alinhaTudo" name="preco" id="preco"
						placeholder="Digite o preco">
				</div>
				<div class="form-group col-md-6">
					<label>Descrição</label> <input type="text" name="descricao"
						class="form-control obrigatorio" id="descricao"
						placeholder="Digite a descrição">
				</div>
				<div class="form-group col-md-4 alinhaTudo">
							<label>Data da Compra </label> <input type="date"
								class="form-control obrigatorio" name="qtde" id="qtde">
				</div>
				<div class="form-group col-md-2 alinhaTudo">
							<label>Quantidade </label> <input type="number"
								class="form-control obrigatorio" name="qtde" id="qtde">
				</div>
				<div class="form-group col-md-2 alinhaTudo">
							<label>Fornecedor </label> <input type="text"
								class="form-control obrigatorio" name="qtde" id="qtde">
				</div>					
			</div><br><br><br><br>
			<p class="btn btn-primary" id="submit">Cadastrar</p>
		</form>
	</div>
</body>
</html>