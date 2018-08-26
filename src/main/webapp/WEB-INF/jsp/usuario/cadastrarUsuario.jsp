<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<title>Cadastro de usu�rio</title>
</head>
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
			var option = "<option value='"+departamento.nome+"'>" + departamento.nome + "</option>";
			$("#departamento").append(option);
		});
	}
	$(document).ready(function(){
		carregarDepartamentosComboBox();
		$("#submit").on('click', function(){
			validarSubmit();
		});
		
		$("#cpf").mask("000.000.000-00");
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
				mostrarErro(campo, "Campo de preenchimento obrigat�rio");
				flgNulo = true;
			}else{
				if(campo.val().trim().length === 0){
					mostrarErro(campo, "Campo de preenchimento obrigat�rio");
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
		}
	}
	
	function validarEmail(campo){
		if(!isEmailValido(campo.val())){
			mostrarErro(campo, "Email inv�lido");
		}		
	}
	
	function validarSenhaForte(campo){
		if(!isSenhaForte(campo.val())){
			mostrarErro(campo, "A senha deve conter no m�nimo 8 caracteres, pelo menos uma letra e numero e caracter especial");
		}		
	}
	
	function validarCpf(campo){
		if(!isCpfValido(campo.val())){
			mostrarErro(campo, "CPF inv�lido");
		}
	}
	function validarSubmit(){
		var flgNulo = false;
	 
		$(".obrigatorio").each(function(){
			esconderErro($(this));
			if($(this).val() != null){
				if($(this).val().length === 0){
					mostrarErro($(this), "Campo de preenchimento obrigat�rio");
					flgNulo = true;
				}else{
					if($(this).val().trim().length === 0){
						mostrarErro($(this), "Campo de preenchimento obrigat�rio");
						flgNulo = true;
					}
				}					
			}
			else{
				mostrarErro($(this), "Campo de preenchimento obrigat�rio");
				flgNulo = true;				
			}
		});
		if(!flgNulo){
			validarEmail($("#email"));
			validarSenhaForte($("#senha"));
			validarCpf($("#cpf"));			
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
		$("#errosBackend").html("");
		$("#divModalSetor").html("");
		$("#errorDepartamento").addClass("msg-erro").hide();
		adicionarSetor();
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
		var jsonSetores = $("#modalDepartamento input:text[name=nome]").serializeObject();
		for(var i = 0; i < jsonSetores.nome.length; i++){
			var jsonSetor = {};
			jsonSetor.nome = jsonSetores.nome[i];
			jsonCadastro.setores.push(jsonSetor);
		}
		
		return jsonCadastro;
	}
</script>

<body>
	<div class="container">
		<form>
			<div class="form-group">
				<label>CPF</label> 
				<input type="text" class="form-control obrigatorio" id="cpf" placeholder="Digite o CPF"> 
			</div>
			<div class="form-group">
				<label>Endere�o de e-mail</label> 
				<input type="email" class="form-control obrigatorio" id="email" placeholder="Digite o email"> 
			</div>
			<div class="form-group">
				<label>Password</label> <input type="password" class="form-control obrigatorio" id="senha" placeholder="Digite a senha">
			</div>
			<div class="form-group">
				<label>Departamento</label> 
				<div class="input-group mb-3">
					 <select class="custom-select obrigatorio" id="departamento">
					  </select>
					  <div class="input-group-append">
					    <a class="btn btn-outline-secondary" id="btnCadastraDepartamento" data-toggle="modal" data-target="#modalDepartamento" onclick="showModalDepartamento()">Button</a>
					  </div>
				</div>
			</div>
			<p  class="btn btn-primary" id="submit">Submit</p>
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
          <button type="button" class="btn btn-success" onclick="cadastrarDepartamento()">Cadastrar</button>
        </div>
      </div>
    </div>
    
    <button type="button" id="btnSucessoCadastro" class="btn btn-primary" style="display:none;" data-toggle="modal" data-target="#sucessoCadastroDepartamento">

	</button>
  </div>
  
  <div class="modal" id="sucessoCadastroDepartamento">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLongTitle">Cadastro de departamento</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        Departamento cadastrado com sucesso!
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-success" data-dismiss="modal">Ok</button>
	      </div>
	    </div>
	  </div>
	</div>
</body>
</html>