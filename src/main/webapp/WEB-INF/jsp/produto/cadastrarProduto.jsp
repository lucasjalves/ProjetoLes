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
		$("#divDepTi").css("display", "none");
		$("#divTelefonia").css("display", "none");
		$("#obrasCivis").css("display", "none");
		$("#divRedeEnergia").css("display", "none");
	    });
	
	function mudaVisibilidade(){
		var valorSelect = document.getElementById("tipoItem").value;
		if(valorSelect =="Obras Civis"){
			$("#divDepTi").css("display", "none");
			$("#divTelefonia").css("display", "none");
			$("#obrasCivis").css("display", "");
			$("#divRedeEnergia").css("display", "none");
		}
		
		else if(valorSelect =="Departamento Ti"){
			$("#divDepTi").css("display", "");
			$("#divTelefonia").css("display", "none");
			$("#obrasCivis").css("display", "none");
			$("#divRedeEnergia").css("display", "none");
		}
		
		else if(valorSelect =="Energia Eletrica"){
			$("#divDepTi").css("display", "none");
			$("#divTelefonia").css("display", "none");
			$("#obrasCivis").css("display", "none");
			$("#divRedeEnergia").css("display", "");
		}
		
		else if(valorSelect=="Planos Telefonia"){
			$("#divDepTi").css("display", "none");
			$("#divTelefonia").css("display", "");
			$("#obrasCivis").css("display", "none");
			$("#divRedeEnergia").css("display", "none");
		}
	}

</script>

<script>
	function cadastraProduto(){
		
		
	}

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
					<select class="form-control" id="tipoItem" onClick="mudaVisibilidade(this)">
						<option>Escolha</option>
						<option>Obras Civis</option>
						<option>Departamento Ti</option>
						<option>Energia Eletrica</option>
						<option>Planos Telefonia</option>
					</select>
				</div>
			</div>
			<div id="divTelefonia">
						<div class="form-group col-md-4 alinhaTudo">
							<label>Nome da Peça </label> <input type="text"
								class="form-control obrigatorio" name="NomePeca" id="NomePeca"
								placeholder="Digite o nome completo">
						</div>
						<div class="form-group col-md-4 alinhaTudo">
							<label>Marca da Peça </label> <input type="text"
								class="form-control obrigatorio" name="marcaPeca" id="marcaPeca"
								placeholder="Digite o nome completo">
						</div>
						<div class="form-group col-md-4 alinhaTudo">
							<label>Modelo da Peça </label> <input type="text"
								class="form-control obrigatorio" name="modeloPeca" id="modeloPeca"
								placeholder="Digite o nome completo">
						</div>
			
			</div>
				<div id="divDepTi">
						<div class="form-group col-md-4 alinhaTudo">
							<label>Nome do Componente </label> <input type="text"
								class="form-control obrigatorio" name="eletricoNome" id="eletricoNome"
								placeholder="Digite o nome completo">
						</div>
						<div class="form-group col-md-4 alinhaTudo">
							<label>Modelo </label> <input type="text"
								class="form-control obrigatorio" name="modelo" id="modelo"
								placeholder="Digite o modelo">
						</div>
						
						<div class="form-group col-md-4 alinhaTudo">
							<label>Marca </label> <input type="text"
								class="form-control obrigatorio" name="marca" id="marcaEletronico"
								placeholder="Digite a marca">
						</div>
				</div>
				<div id="obrasCivis">
						<div class="form-group col-md-4 alinhaTudo">
							<label>Nome da Ferrameta </label> <input type="text"
								class="form-control obrigatorio" name="ferramenteNome" id="ferramenteNome"
								placeholder="Digite o nome completo">
						</div>
						<div class="form-group col-md-2 alinhaTudo">
							<label>Peso(KG) </label> <input type="date"
								class="form-control obrigatorio" name="pesoFerramenta" id="pesoFerramenta">
						</div>
			</div>
			<div id="divRedeEnergia">
						<div class="form-group col-md-4 alinhaTudo">
							<label>Nome do Item </label> <input type="text"
								class="form-control obrigatorio" name="ItemEnergiaNome" id="ferramentaEnergiaNome"
								placeholder="Digite o nome completo">
						</div>
						<div class="form-group col-md-4 alinhaTudo">
							<label>Marca do Item </label> <input type="text"
								class="form-control obrigatorio" name="ItemEnergiaMarca" id="ferramenteNome"
								placeholder="Digite o nome completo">
						</div>
						<div class="form-group col-md-4 alinhaTudo">
							<label>Modelo do Item </label> <input type="text"
								class="form-control obrigatorio" name="ItemEnergiaModelo" id="ItemEnergiaModelo"
								placeholder="Digite o nome completo">
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
			<p class="btn btn-primary" onClick="cadastraProduto()" "id="submit">Cadastrar</p>
		</form>
	</div>
</body>
</html>