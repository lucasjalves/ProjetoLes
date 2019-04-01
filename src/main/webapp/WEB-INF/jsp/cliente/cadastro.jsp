<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<script>
	$(document).ready(function(){
		$("#formEndereco input").each(function(){
			$(this).on("focusout", function(){
				$(this).attr("value",$(this).val());
			});
		});
		
		$("#btnCadastrar").on("click", function(){
			$.post("http://localhost:8888/cliente/cadastrar", $("#form").serialize())
				.done(function(data){
					abrirModalSucessoOuFalha(data, "Cliente cadastrado com sucesso!", "Falha ao cadastrar o cliente", data.mensagem.length);
				})
				.fail(function(data){
					abrirModalSucessoOuFalha(data, "Cliente cadastrado com sucesso!", "Falha ao cadastrar o cliente", 1);
				});
		});	
		$("#cpfCnpj").mask("000.000.000-00");
		$("#dtNascimento").mask("00/00/0000");
	});

	function adicionarEndereco(){
		$("#modalEndereco input").each(function(){
			$(this).val("");
		});
		$("#btnAbrirModalEndereco").click();
	}
	function validarEnderecoAlterar(index){
		var clone = $("#formAlterarEndereco").clone();
		clone.find("input").each(function(){
			var nome = $(this).attr("name");
			var nomeSplited = nome.split(".");
			if(nomeSplited.length > 1){
				nome = nomeSplited[nomeSplited.length - 1];
			}
			$(this).attr("name",nome);
		});
		$.post("http://localhost:8888/endereco/adicionar", clone.serialize())
		.done(function(data){
			var adicionado = abrirModalSucessoOuFalha(data, "", "", data.mensagem.length, true, false);
			if(adicionado){
				$("#enderecos [endereco='"+index+"']").html($("#formAlterarEndereco").html());
				atualizarListaEndereco();
				$('#modalEnderecoAlterar').modal('hide');
			}
		})
		.fail(function(data){
			abrirModalSucessoOuFalha(data, "Falha ao validar o endereço", "Falha ao validar o endereço", 1);
		});				
	}
	function validarEndereco(){
		$.post("http://localhost:8888/endereco/adicionar", $("#formEndereco").serialize())
		.done(function(data){
			var adicionado = abrirModalSucessoOuFalha(data, "", "", data.mensagem.length, true, false);
			if(adicionado){
				$('#modalEndereco').modal('hide');
				$("#enderecos").append($("#formEndereco").clone().html());
				atualizarQuantidadeEnderecos();
				atualizarListaEndereco();
			}
		})
		.fail(function(data){
			abrirModalSucessoOuFalha(data, "Falha ao validar o endereço", "Falha ao validar o endereço", 1);
		});		
	}
	
	function atualizarQuantidadeEnderecos(){
		var contadorEndereco = 0;
		$("#enderecos .contadorEnderecos").each(function(){
			$(this).find("input").each(function(){
				var nome = $(this).attr("name");
				var nomeSplited = nome.split(".");
				if(nomeSplited.length > 1){
					nome = nomeSplited[nomeSplited.length - 1];
				}
				nome = "enderecos[" + contadorEndereco + "]." + nome;
				$(this).attr("name",nome);
			});
			$(this).attr("endereco", contadorEndereco);
			contadorEndereco++;
		});		
	}
	
	function atualizarListaEndereco(){
		var contadorEndereco = 0;
		$("#nomesEnderecos").html("");
		$("#enderecos .contadorEnderecos").each(function(){
			var nome = $(this).find("input:first").val();
			adicionarEnderecoLista(nome, contadorEndereco);
			contadorEndereco++;
		});		
	}
	
	function adicionarEnderecoLista(nomeEndereco, index){
		var itemEndereco = $("#itemEndereco").clone();
		itemEndereco.find("span").attr("indiceEndereco", index);
		itemEndereco.find(".nomeEndereco").text(nomeEndereco).attr("onclick","alterarEndereco("+index+")");
		itemEndereco.find("button").attr("onclick","removerEndereco("+index+")");
		$("#nomesEnderecos").append(itemEndereco.html());
	}
	
	function removerEndereco(index){
		$("#nomesEnderecos [indiceEndereco='"+index+"']").remove();
		$("#enderecos [endereco='"+index+"']").remove();
		atualizarQuantidadeEnderecos(false);
	}
	
	function alterarEndereco(index){
		var dados = $("#enderecos [endereco='"+index+"']").clone();
		$("#formAlterarEndereco").html(dados.html());
		$("#formAlterarEndereco input").each(function(){
			$(this).on("focusout", function(){
				$(this).attr("value",$(this).val());
			});
		});
		$("#btnAbrirModalEnderecoAlterar").click();
		$("#btnFecharAlterar").attr("onclick", "validarEnderecoAlterar("+index+")");
	}
</script>
<title>Cadastro de Cliente</title>
</head>
<body>
<div id="itemEndereco" style="display: none;">
	<span>
		<a href="#" style="margin-right: 10px; color: black; text-decoration: underline" class="nomeEndereco"></a>
		<button class="btn btn-link" style="margin-right: 40px; margin-top: -3px;">Remover</button>
	</span>
</div>
	<div class="container" style="margin-top: 100px;">
		<div class="card">
			<h5 class="card-header">Cadastrar</h5>
			<div class="card-body">
				<h5 class="card-title">Cadastre um novo cliente</h5>
				<form id="form">
				<div id="enderecos" style="display: none;">
				
				</div>
					<div class="form-group">
						<label>Nome Completo</label> 
						<input type="text" class="form-control" name="nome" placeholder="Nome" required>
					</div>
					<div class="form-group">
						<label>CPF/CNPJ</label> 
						<input type="text" class="form-control" name="cpfCnpj" id="cpfCnpj" placeholder="CPF/CNPJ" required>
					</div>													
					<div class="form-group">
						<label>Data nascimento</label> 
						<input type="text" id="dtNascimento" class="form-control" name="dtNascimento" placeholder="DD/MM/YYYY" required>
					</div>
					  <div class="form-group">
					    <label>Gênero</label>
					    <select class="form-control" name="genero">
					      <option selected>M</option>
					      <option>F</option>
					    </select>
					  </div>
					  <div class="form-group">
					    <label>Endereços</label>
					    <div id="nomesEnderecos">
					    
					    </div>
						<a href="#" class="btn btn-success" onclick="adicionarEndereco()" style="margin-top: 10px;">Adicionar</a>
					  </div>					 
					<div class="form-group">
						<label>E-mail</label> 
						<input type="text" class="form-control" name="email" placeholder="Nome" id="email" required>
					</div>					  								
					<div class="form-group">
						<label>Username</label> 
						<input type="text" class="form-control" name="username" placeholder="Username" required>
					</div>	
					<div class="form-group">
					    <label>Status</label>
					    <select class="form-control" name="ativo">
					      <option selected value="true">Ativo</option>
					      <option value="false">Inativo</option>
					    </select>
					 </div>					
					<div class="form-group">
						<label>Senha</label> 
						<input type="password" class="form-control" name="senha" placeholder="Senha" required>
					</div>											
				</form>
				<button class="btn btn-primary" id="btnCadastrar">Cadastrar</button>

			</div>
		</div>
	</div>
	<button type="button" class="btn btn-primary" data-toggle="modal" id="btnAbrirModalEnderecoAlterar" style="display: none;"
	data-target="#modalEnderecoAlterar">Open modal</button>
	<div class="modal" id="modalEnderecoAlterar">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<div class="modal-header">
					<h4 class="modal-title">Alterar endereço</h4>
					<button type="button" class="close" id="btnFecharModalEndereco" onclick="$('#modalEnderecoAlterar').modal('hide')">&times;</button>
				</div>

				<div class="modal-body">
					<form id="formAlterarEndereco"></form>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-info"id="btnFecharAlterar" onclick="validarEnderecoAlterar()">Alterar</button>
				</div>

			</div>
		</div>
	</div>
	
	
	<div class="modal" id="modalEndereco">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<div class="modal-header">
					<h4 class="modal-title">Adicionar endereço</h4>
					<button type="button" class="close" id="btnFecharModalEndereco" onclick="$('#modalEndereco').modal('hide')">&times;</button>
				</div>

				<div class="modal-body">
					<jsp:include page="../componentes/formEndereco.jsp"></jsp:include>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-info" onclick="validarEndereco()">Adicionar</button>
				</div>

			</div>
		</div>
	</div>
	<button type="button" class="btn btn-primary" data-toggle="modal" id="btnAbrirModalEndereco" style="display: none;"
	data-target="#modalEndereco">Open modal</button>

	<jsp:include page="../componentes/modal.jsp"></jsp:include>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>