<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<title>Cadastro de usuário</title>
</head>
<style>
.invalido {
	border-color: #dc3545;
}

.msg-erro{
	color: red;
}
</style>

<script>
	var listaDepartamentos = '${jsonListaDepartamentos}';
	
	$(document).ready(function(){
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
			debugger;
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
		}
	}
	
	function validarEmail(campo){
		if(!isEmailValido(campo.val())){
			mostrarErro(campo, "Email inválido");
		}		
	}
	
	function validarSenhaForte(campo){
		if(!isSenhaForte(campo.val())){
			mostrarErro(campo, "A senha deve conter no mínimo 8 caracteres, pelo menos uma letra e numero e caracter especial");
		}		
	}
	
	function validarCpf(campo){
		if(!isCpfValido(campo.val())){
			mostrarErro(campo, "CPF inválido");
		}
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
		}
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
				<label>Endereço de e-mail</label> 
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
					    <a class="btn btn-outline-secondary" id="btnCadastraDepartamento" data-toggle="modal" data-target="#modalDepartamento">Button</a>
					  </div>
				</div>
			</div>
			<p  class="btn btn-primary" id="submit">Submit</p>
		</form>
	</div>
	

  <div class="modal" id="modalDepartamento">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <div class="modal-header">
          <h4 class="modal-title">Modal Heading</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <div class="modal-body">
          Modal body..
        </div>
        
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>
</body>
</html>