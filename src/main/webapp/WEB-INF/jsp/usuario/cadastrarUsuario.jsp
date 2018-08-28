<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" 
integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/esm/popper.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
<style>
.invalido {
	border-color: #dc3545;
}

.msg-erro{
	color: red;
}

.hide{
	display: none;
}
</style>

<script>
	var listaDepartamentos = '${jsonListaDepartamentos}';
	var departamentoCadastro;
	function carregarDepartamentosComboBox(){
		$("#departamento").html("");
		lista = JSON.parse(listaDepartamentos);
		$.each(lista, function(index, departamento){
			var option = "<option value='"+departamento.nome+"' departamentoid='"+departamento.id+"'>" + departamento.nome + "</option>";
			$("#departamento").append(option);
		});
		var optionCadastrar = "<option>Cadastrar novo</option>";
		$("#departamento").append(optionCadastrar);
		$("#idDepartamentoHidden").val($("#departamento :checked").attr("departamentoid"));
	}
	
	function carregarSetoresComboBox(){
		lista = JSON.parse(listaDepartamentos);
		$.each(lista, function(index, departamento){
			if($("#departamento :checked").html() === departamento.nome){
				$.each(departamento.setores, function(index, setor){
					var option = "<option value='"+setor.nome+"' idSetor='"+setor.id+"'>" + setor.nome + "</option>";
					$("#setores").append(option);
				});
				$("#setores").prop("disabled", false);
			}
		})
		
	}
	$(document).ready(function(){
		carregarDepartamentosComboBox();
		$("#submit").on('click', function(){
			validarSubmit();
		});
		
		$("#statusCadastro").val("ATIVO");
		$("#statusCadastro").attr("value", "ATIVO");
		
		$("#pontos").val("0");
		$("#pontos").attr("value", "0");
		
		$("#departamento").on("change",function(){
			if($("#departamento :checked").html() === "Cadastrar novo"){
				$("#setores").html("");
				$("#setores").prop("disabled", true);
				showModalDepartamento();
				$("#btnCadastraDepartamento").click();
			}else{
				$("#setores").html("");
				carregarSetoresComboBox();
				$("#idDepartamentoHidden").val($("#departamento :checked").attr("departamentoid"));
				$("#setores").val($("#setores :checked").val()).change();
			}
		})
		
		$("#setores").on("change", function(){
			$("#idSetorHidden").val($("#setores :checked").attr("idsetor"));
		});
		
		$("#cpf").mask("000.000.000-00");
		$("#ramal").mask("000000");
		verificarListaDepartamentos();
		
		
		$("input").each(function(){
			$(this).on("focusout", function(){
				validarCampoFocusOut($(this));
			});
		});
	});
	function isEmailValido(email) {
	  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	  email = email.toLowerCase();
	  return re.test(email);
	}
	
	function mostrarErro(campo, msgErro){
		esconderErro(campo);
		$(campo).addClass("invalido");
		$(campo).parent().append("<small id='error' class='form-text msg-erro'>" + msgErro + "</small>");
	}
	
	function esconderErro(campo){
		$(campo).removeClass("invalido");
		$(campo).parent().find("small").each(function(){
			$(this).remove();
		});
	}
	
	function validarCampoFocusOut(campo){
		$("#errosBackend").html("");
		var id = campo.attr("id");
		esconderErro(campo);
		var flgNulo = false;
		if(campo.hasClass("obrigatorio") && campo.parent().find("small").length === 0){
			if(campo.val().length === 0){
				mostrarErro(campo, "Campo de preenchimento obrigatório");
				flgNulo = true;
			}else{
				if(campo.val().trim().length === 0){
					mostrarErro(campo, "Campo de preenchimento obrigatório");
					flgNulo = true;
				}
			}
		}
		if(!flgNulo){	
			if(id === "email"){
				validarEmail(campo);
			}
			if(id === "senha"){
				validarSenhaForte(campo);
			}
			if(id === "cpf"){
				validarCpf(campo);
			}
			if(id === "emailCopia"){
				validarEmailsIguais(campo)
			}
			if(id === "senhaCopia"){
				validarSenhasIguais(campo)
			}
			if(id === "ramal"){
				validarRamal(campo);
			}
		}

	}
	
	function validarEmailsIguais(campo){
		if($("#email").val() !== campo.val()){
			mostrarErro(campo, "Os emails não conferem");
		}else{
			esconderErro(campo);
		}		
	}
	
	function validarSenhasIguais(campo){
		if($("#senha").val() !== campo.val()){
			mostrarErro(campo, "As senhas não conferem");
		}else{
			esconderErro(campo);
		}			
	}
	
	function isSenhaForte(senha){
		  var re = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\W\_])[a-zA-Z0-9\W\_]{8,15}$/;
		  return re.test(senha);
	}
	

	function isCpfValido(cpf) {
		cpf = cpf.replace(/\D/g, "");
		var numeros, digitos, soma, i, resultado, digitos_iguais;
		digitos_iguais = 1;
		if (cpf.length < 11) {
			return false;
		}

		for (i = 0; i < cpf.length - 1; i++)
			if (cpf.charAt(i) != cpf.charAt(i + 1)) {
				digitos_iguais = 0;
				break;
			}
		if (!digitos_iguais) {
			numeros = cpf.substring(0, 9);
			digitos = cpf.substring(9);
			soma = 0;
			for (i = 10; i > 1; i--) {
				soma += numeros.charAt(10 - i) * i;
			}
			resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
			if (resultado != digitos.charAt(0)) {
				return false;
			}
			numeros = cpf.substring(0, 10);
			soma = 0;
			for (i = 11; i > 1; i--) {
				soma += numeros.charAt(11 - i) * i;
			}
			resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
			if (resultado != digitos.charAt(1)) {
				return false;
			}
			return true;
		} else
			return false;

	}
	
	function verificarListaDepartamentos(){
		var json = JSON.parse(listaDepartamentos);
		if(json.length === 0){
			$("#btnCadastraDepartamento").html("Cadastrar Novo");
		}else{
			$("#btnCadastraDepartamento").html("Editar");
			carregarSetoresComboBox();
			$("#setores").val($("#setores :checked").val()).change();
		}
	}
	
	function validarEmail(campo){
		var bool = isEmailValido(campo.val());
		if(!bool){
			mostrarErro(campo, "Email inválido");
		}		
		return bool;
	}
	
	function validarRamal(campo){
		if(campo.val().length < 6){
			mostrarErro(campo, "O ramal deve conter 6 caracteres");
			return false;
		}
		return true;
	}
	
	function validarSenhaForte(campo){
		var bool = isSenhaForte(campo.val())
		if(!bool){
			mostrarErro(campo, "A senha deve conter no mínimo 8 caracteres, pelo menos uma letra e numero e caracter especial");
		}		
		return bool;
	}
	
	function validarCpf(campo){
		var bool = isCpfValido(campo.val());
		if(!bool){
			mostrarErro(campo, "CPF inválido");
		}
		return bool;
	}
	function validarSubmit(){
		var flgNulo = false;
	 	
		$(".obrigatorio").each(function(){
			esconderErro($(this));

			if($(this).val() != null){
				if($(this).val().length === 0){
					mostrarErro($(this), "Campo de preenchimento obrigatório");
					flgNulo = true;
				}else{
					if($(this).val().trim().length === 0){
						mostrarErro($(this), "Campo de preenchimento obrigatório");
						flgNulo = true;
					}
				}					
			}
			else{
				mostrarErro($(this), "Campo de preenchimento obrigatório");
				flgNulo = true;				
			}
		});
		if(!flgNulo){
			validarEmail($("#email"));
			validarSenhaForte($("#senha"));
			validarCpf($("#cpf"));		
			validarEmailsIguais($("#emailCopia"));
			validarSenhasIguais($("#senhaCopia"));
		}
		if($(".invalido").length === 0){
			$("form").submit();
		}
		
	}
	
	function adicionarSetor(){
		var input = "<div class='input-group mb-3'> <input type='text' class='form-control' placeholder='Nome do setor' name='nome' onfocusout='validarSetorModalCadastroFocusOut(this)'>" +
		  "<div class='input-group-append'>" +
		  "<button class='btn btn-success' type='button' onclick='adicionarSetor()' >+</button>" +
		  "</div>" +
		"</div>";	
		$("#divModalSetor").append(input);
		if($("#divModalSetor .input-group").length > 1){
			var qtde = $("#divModalSetor .input-group").length -1;
			console.log(qtde);
			for(var i = 0; i < qtde ; i++){
				$("#divModalSetor").find("button").eq(i)
					.removeClass("btn-success")
					.addClass("btn-danger")
					.attr("onclick", "removerSetor(this)")
					.html("-");
				
			}
		}
	}
	
	function removerSetor(campo){
		$(campo).parent().parent().remove();
	}
	function showModalDepartamento(){
		$("#btnCadastrar").attr("onclick", "cadastrarDepartamento()");
		$("#nomeDepartamento").val("");
		$("#btnCadastrar").html("Cadastrar");
		$("#errosBackend").html("");
		$("#divModalSetor").html("");
		$("#errorDepartamento").addClass("msg-erro").hide();
		if($("#departamento").val() !== null){
			if($("#departamento :checked").html() !== "Cadastrar novo"){
				$("#nomeDepartamento").val($("#departamento").val());
				$.each(JSON.parse(listaDepartamentos), function(index, departamento){
					if(departamento.nome === $("#departamento").val()){
						$("#btnCadastrar").attr("onclick", "alterarDepartamento()");
						$("#btnCadastrar").html("Alterar");
						$.each(departamento.setores, function(index, setor){					
							adicionarSetor();
							var qtde = $("#divModalSetor input:text").length;
							$("#divModalSetor input:text").eq(qtde - 1).val(setor.nome);
							$("#divModalSetor input:text").eq(qtde - 1).attr("setorId", setor.id);
						});
					}
				});				
			}
			else{
				adicionarSetor();
			}
		}else{
			adicionarSetor();
		}
		
		
	}
	
	
	function cadastrarDepartamento(){
		$("#errosBackend").html("");
		$("#modalDepartamento input:text").each(function(){
			$(this).removeClass("invalido");
			if($(this).val().length === 0){
				$(this).addClass("invalido");
				$("#errorDepartamento").show();
			}else{
				if($(this).val().trim().length === 0){
					$(this).addClass("invalido");
					$("#errorDepartamento").show();
				}
			}
		});
		if($("#divModalSetor .input-group").length > 1){
			if($("#modalDepartamento input:text").last().hasClass("invalido")){
				$("#modalDepartamento input:text").last().removeClass("invalido");
			}
		}
		
		if($("#modalDepartamento .invalido").length === 0){
			$("#errorDepartamento").hide();
			jsonCadastro = gerarJsonCadastroDepartamento();
			$.ajax({
				method: "POST",
				contentType: 'application/json',
				url: "/departamento/cadastrar",
				data: JSON.stringify(jsonCadastro),
				success: function(resultado){
					if(resultado.mensagem.length > 0){
						$("#errosBackend").html("");
						$.each(resultado.mensagem, function(index, erro){
							var msg = "<small id='error' class='form-text msg-erro'>" + erro.mensagem + "</small>";
							$("#errosBackend").append(msg);
						});
						$("#errosBackend").show();
					}else{
						listaDepartamentos = JSON.stringify(resultado.entidades);
						carregarDepartamentosComboBox();
						$("#departamento").val($("#nomeDepartamento").val()).change();
						$("#modalDepartamento .close").click();
						$("#btnSucessoCadastro").click();
						$("#btnCadastraDepartamento").html("Editar");
					}
				}
			});
		}
	}
	
	function alterarDepartamento(){
		$("#errosBackend").html("");
		$("#modalDepartamento input:text").each(function(){
			$(this).removeClass("invalido");
			if($(this).val().length === 0){
				$(this).addClass("invalido");
				$("#errorDepartamento").show();
			}else{
				if($(this).val().trim().length === 0){
					$(this).addClass("invalido");
					$("#errorDepartamento").show();
				}
			}
		});
		if($("#divModalSetor .input-group").length > 1){
			if($("#modalDepartamento input:text").last().hasClass("invalido")){
				$("#modalDepartamento input:text").last().removeClass("invalido");
			}
		}	
		
		
		if($("#modalDepartamento .invalido").length === 0){
			$("#errorDepartamento").hide();
			jsonCadastro = gerarJsonAlteracaoDepartamento();
			$.ajax({
				method: "POST",
				contentType: 'application/json',
				url: "/departamento/alterar",
				data: JSON.stringify(jsonCadastro),
				success: function(resultado){
					if(resultado.mensagem.length > 0){
						$("#errosBackend").html("");
						$.each(resultado.mensagem, function(index, erro){
							var msg = "<small id='error' class='form-text msg-erro'>" + erro.mensagem + "</small>";
							$("#errosBackend").append(msg);
						});
						$("#errosBackend").show();
					}else{
						listaDepartamentos = JSON.stringify(resultado.entidades);
						carregarDepartamentosComboBox();
						$("#departamento").val($("#nomeDepartamento").val()).change();
						$("#modalDepartamento .close").click();
						$("#btnSucessoAlteracao").click();
					}
				}
			});
		}
	}
	
	function validarSetorModalCadastroFocusOut(campo){
		var haErro = false;
		$(campo).removeClass("invalido");
		if($(campo).val().length === 0){
			$(campo).addClass("invalido");
			haErro = true;
		}else{
			if($(campo).val().trim().length === 0){
				$(campo).addClass("invalido");
				haErro = true;
			}
		}		
		
		$("#modalDepartamento input:text").each(function(){
			if($(this).hasClass("invalido")){
				$("#errorDepartamento").show();
				haErro = true;
			}
		});
		
		if(!haErro){
			$("#errorDepartamento").hide();
		}
		
		if($("#divModalSetor .input-group").length > 1){
			if($("#modalDepartamento input:text").last().hasClass("invalido")){
				$("#modalDepartamento input:text").last().removeClass("invalido");
			}
		}
	}
	
	$.fn.serializeObject = function()
	{
	    var o = {};
	    var a = this.serializeArray();
	    $.each(a, function() {
	        if (o[this.name] !== undefined) {
	            if (!o[this.name].push) {
	                o[this.name] = [o[this.name]];
	            }
	            o[this.name].push(this.value || '');
	        } else {
	            o[this.name] = this.value || '';
	        }
	    });
	    return o;
	};
	
	function gerarJsonCadastroDepartamento(){
		jsonCadastro = {}
		jsonCadastro.nome = $("#nomeDepartamento").val();
		jsonCadastro.setores = [];
		for(var i = 0; i < $("#modalDepartamento input:text[name=nome]").length; i++){
			var jsonSetor = {};
			jsonSetor.nome = $("#modalDepartamento input:text[name=nome]").eq(i).val();
			jsonCadastro.setores.push(jsonSetor);
		}
		return jsonCadastro;
	}
	
	function gerarJsonAlteracaoDepartamento(){
		jsonCadastro = {}
		jsonCadastro.nome = $("#nomeDepartamento").val();
		jsonCadastro.id = $("#departamento :checked").attr("departamentoid");
		jsonCadastro.setores = [];
		for(var i = 0; i < $("#modalDepartamento input:text[name=nome]").length; i++){
			var jsonSetor = {};
			jsonSetor.nome = $("#modalDepartamento input:text[name=nome]").eq(i).val();
			if($("#divModalSetor input:text").eq(i).length > 0){
				jsonSetor.id = $("#divModalSetor input:text").eq(i).attr("setorid");
			}
			jsonCadastro.setores.push(jsonSetor);
		}
		return jsonCadastro;		
	}
</script>

<body>
	<div class="container">
		<form action="/usuario/cadastrar/efetivar" method="POST">
		<div class="form-row">
			<div class="form-group col-md-4">
				<label>Nome </label> 
				<input type="text" class="form-control obrigatorio" name="nome" id="nome" placeholder="Digite o nome completo"> 
			</div>
			<div class="form-group col-md-3">
				<label>CPF </label> 
				<input type="text" class="form-control obrigatorio" name="cpf" id="cpf" placeholder="Digite o CPF"> 
				<input type="hidden" name="statusCadastro" id="statusCadastro"/>
			</div>
		
			<div class="form-group col-md-3">
				<label>Ramal </label> 
				<input type="text" class="form-control obrigatorio" name="ramal" id="ramal" placeholder="Digite o ramal"> 
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-3">
				<label>Endereço de e-mail</label> 
				<input type="email" class="form-control obrigatorio" name="email" id="email" placeholder="Digite o email"> 
			</div>
			<div class="form-group col-md-3" id="divEmailCopia" >
				<label>Digite novamente o email</label> 
				<input type="email" class="form-control obrigatorio" id="emailCopia" placeholder="Digite o email"> 
			</div>
			<div class="form-group col-md-4">
				<label>Username: </label> 
				<input type="text" class="form-control obrigatorio" name="username" id="username" placeholder="Digite o username"> 
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-5">
				<label>Senha</label> <input type="password" name="senha" class="form-control obrigatorio" id="senha" placeholder="Digite a senha">
			</div>
			<div class="form-group col-md-5" id="divSenhaCopia">
				<label>Digite novamente a senha</label> <input type="password" class="form-control obrigatorio" id="senhaCopia" placeholder="Digite a senha">
			</div>	
		</div>	
		<div class="form-row">
			<div class="form-group col-md-5">
				<label>Departamento</label> 
				<div class="input-group mb-3">
					 <select class="custom-select obrigatorio" id="departamento" name="departamento.nome">
					  </select>
					  <div class="input-group-append">
					    <a class="btn btn-outline-secondary" id="btnCadastraDepartamento" data-toggle="modal" data-target="#modalDepartamento" onclick="showModalDepartamento()" data-backdrop="false">Button</a>
					  </div>
				</div>
			</div>
		
			<div class="form-group col-md-5">
				<label>Setores</label> 
				<div class="input-group mb-3">
					 <select class="custom-select obrigatorio" id="setores" disabled>
					  </select>
				</div>
			</div>
			<input type="hidden" id="pontos" name="pontos" />
			<input type="hidden" id="idDepartamentoHidden" name="departamento.id" />
			<input type="hidden" id="idSetorHidden" name="setor.id" />
			
		</div>
		<div class="form-row">
			<div class="form-group col-md-5">
				<label>Tipo de usuário</label> 
				<div class="input-group mb-3">
					 <select class="custom-select obrigatorio" id="tipoUsuario" name="tipoUsuario">
					 	<option value="ADMINISTRADOR">Administrador</option>
					 	<option value="ATENDENTE">Atendente</option>
					 	<option value="CLIENTE">Cliente</option>
					  </select>				
				</div>
			</div>
		</div>
			<p class="btn btn-primary" id="submit" onclick="validarSubmit()">Cadastrar</p>
		</form>
	</div>
	

  <div class="modal" id="modalDepartamento">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
      
        <div class="modal-header">
          <h4 class="modal-title">Cadastro de departamento</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <div class="modal-body">
 			<div class="form-group" id="bodyModalDepartamento">
				<label>Nome</label> 
				<input type="text" class="form-control obrigatorioDepartamento" onfocusout="validarSetorModalCadastroFocusOut(this)" 
				id="nomeDepartamento" placeholder="Digite o nome do departamento" name="departamento"> 
			</div>         
			<hr/>
			<h3 style="text-align: center;">Setores</h3>
			<div id="divModalSetor">
				<div class="input-group mb-3">
				  <input type="text" class="form-control" placeholder="Nome do setor" name="nome">
				  <div class="input-group-append">
				    <button class="btn btn-success" type="button">+</button>
				  </div>
				</div>
			</div>
			<small id='errorDepartamento' class='form-text' style="display: none;">Preencha todos os campos</small>
			<div id="errosBackend" style="display:none;">
			</div>
        </div>
        
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" style="position: relative; right: 60%" data-dismiss="modal">Fechar</button>
          <button type="button" class="btn btn-success" id="btnCadastrar" onclick="cadastrarDepartamento()">Cadastrar</button>
        </div>
      </div>
    </div>
    
    <button type="button" id="btnSucessoCadastro" class="btn btn-primary" style="display:none;" data-toggle="modal" data-target="#sucessoCadastroDepartamento" data-backdrop="false"></button>
    <button type="button" id="btnSucessoAlteracao" class="btn btn-primary" style="display:none;" data-toggle="modal" data-target="#sucessoAlteracaoDepartamento" data-backdrop="false"></button>
  </div>

	<div class="modal" id="sucessoCadastroDepartamento">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Cadastro de
						departamento</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Departamento cadastrado com sucesso!</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">Ok</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal" id="sucessoAlteracaoDepartamento">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Alteração
						de departamento</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Departamento alterado com sucesso!</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal" >Ok</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>