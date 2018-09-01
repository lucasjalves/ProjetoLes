<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" 
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" 
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/esm/popper.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
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

.item-hovered:hover{
	background-color: #f7f7f7;
	cursor: pointer;
}

</style>
<script>
	var jsonUsuario = '${jsonUsuario}';
	var mesmoUsuario = ${mesmoUsuario};
	var departamentos = '${departamentos}';
	
	$(document).ready(function(){
		if(mesmoUsuario){
			var listItem = "<li class='list-group-item'>CPF: ${usuario.cpf}</li>"
			$("#listaCardUsuario").append(listItem);	
			var username = $("#username").html();
			var usernameText = username + "<a href='javascript:;' onclick='alterarSenha()' style='float: right;'>Senha</a>";
			$("#username").html(usernameText);
		}
	});
	
	function alterarSenha(){
		$("#errosBackend").html("");
		$("#modalSenha").find("input:password").each(function(){
			esconderErro($(this));
			$(this).val("");
		});
		$("#btnModal").click();

	}

	function validarSenhaForte(campo){
		var bool = isSenhaForte(campo.val())
		if(!bool){
			mostrarErro(campo, "A senha deve conter no mínimo 8 caracteres, pelo menos uma letra e numero e caracter especial");
		}		
		return bool;
	}
	
	function isSenhaForte(senha){
		  var re = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\W\_])[a-zA-Z0-9\W\_]{8,15}$/;
		  return re.test(senha);
	}
	
	function mostrarErro(campo, msgErro) {
		esconderErro(campo);
		$(campo).addClass("invalido");
		$(campo).parent().append(
				"<small id='error' class='form-text msg-erro'>" + msgErro
						+ "</small>");
	}

	function esconderErro(campo) {
		$(campo).removeClass("invalido");
		$(campo).parent().find("small").each(function() {
			$(this).remove();
		});
	}
	
	function validarSenhaIguais(){
		if($("#senhaAntiga").val() !== $("#senhaAntigaCopia").val()){
			esconderErro($("#senhaAntiga"));
			esconderErro($("#senhaAntigaCopia"));
			mostrarErro($("#senhaAntiga"), "As senhas não conferem");
			mostrarErro($("#senhaAntigaCopia"), "As senhas não conferem");
		}else{
			esconderErro($("#senhaAntiga"));
			esconderErro($("#senhaAntigaCopia"));			
		}
	}
	
	function validarCamposSenhaFocusOut(campo){
		$("#errosBackend").html("");
		esconderErro($(campo));
		$("#erroSenha").hide();
		if($(campo).val().length === 0){
			$(campo).addClass("invalido");
			$("#erroSenha").show();
		}else{
			if($(campo).val().trim().length === 0){
				$("#erroSenha").show();
				$(campo).addClass("invalido");
			}else{
				if(validarSenhaForte($(campo))){
					validarSenhaIguais();
				}
			}
		}
	}
	
	function submitMudancaSenha(){
		$("#modalSenha").find("input:password").each(function(){
			validarCamposSenhaFocusOut($(this).get(0));
		});
		if($("#modalSenha .invalido").length === 0){
			var json = {};
			json.senhaNova = $("#senha").val();
			json.senhaAntiga = $("#senhaAntiga").val();
			$.ajax({
				method: "POST",
				contentType: 'application/json',
				url: "/usuario/alterarSenha",
				data: JSON.stringify(json),
				success : function(mensagens){
					if(mensagens.length > 0){
						$("#modalSenha #errosBackend").html("");
						$("#modalSenha #errosBackend").show();
						$.each(mensagens, function(index, erro){
							var erroDisplay = "<small class='form-text' style='color: #dc3545;'>" + erro.mensagem + "</small>";
							$("#modalSenha #errosBackend").append(erroDisplay);
						});
					}else{
						$("#btnModalSucessoSenha").click();
					}
				}
			});
		}
	}
	
	function fecharModalSucesso(){
		$("#modalSenha [data-dismiss='modal']").click();
	}
	
	function abrirModalAlterar(itemLista){
		$("#modalAlteracao #erroValidacao").hide();
		$("#modalAlteracao #errosBackend").hide();
		cleanModalAlteracao();
		var item = $(itemLista).attr("item");
		if(item === "departamento"){
			
		}
	}
	
	function modalAlterarDepartamento(){
		$("#modalAlteracao .modalTitle").html("Alterar deparamento e setor");
		var json = JSON.parse(departamentos);
	}
	
	function cleanModalAlteracao(){
		$("#modalAlteracao .modal-body :visible").each(function(){
			if($(this).attr("id") !== "labelExemplo" || $(this).attr("id") !== "inputExemplo"){
				$(this).remove();
			}
		});
	}
</script>
<body>
	<div class="container">
		<div class="row">
			<div class="card col-md-3" >
			     <img class="card-img-top" src="https://www.weact.org/wp-content/uploads/2016/10/Blank-profile.png" alt="Card image cap" style="width: 220px;">
			  <div class="card-body">
			    <h5 class="card-title">${usuario.nome}</h5>
			    <p class="card-text">${usuario.tipoUsuario}</p>
			    <p class="card-text" id="username">${usuario.username}</p>
			  </div>
			  <ul class="list-group list-group-flush" id="listaCardUsuario">
			    <li class="list-group-item item-hovered" id="itemDepartamento" onmouseover="">${usuario.departamento.nome}</li>
			    <li class="list-group-item">${usuario.setor.nome}</li>
			    <li class="list-group-item">${usuario.email}</li>
			    <li class="list-group-item">Ramal: ${usuario.ramal}</li>
			    <li class="list-group-item">Pontos: ${usuario.pontos}</li>
			  </ul>
			</div>
			<div class="card col-md-9">
			  <div class="card-header" style="margin-left: -18px; width: 105%">
			    Featured
			  </div>
			  <div class="card-body">
			    <h5 class="card-title">Special title treatment</h5>
			    <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
			    <a href="#" class="btn btn-primary">Go somewhere</a>
			  </div>
			</div>
		</div>
	</div>
	<div class="modal" id="modalSenha">
	    <div class="modal-dialog modal-dialog-centered">
	      <div class="modal-content">
	      
	        <div class="modal-header">
	          <h4 class="modal-title">Alterar Senha</h4>
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	        </div>
	        
	        <div class="modal-body">
	 			<div class="form-group" >
					<label>Senha</label> 
					<input type="password" class="form-control"  
					id="senhaAntiga" placeholder="Digite a senha atual" onfocusout="validarCamposSenhaFocusOut(this)"> 
				</div>     

	 			<div class="form-group" >
					<label>Digite novamente</label> 
					<input type="password" class="form-control"  
					id="senhaAntigaCopia" placeholder="Digite novamente a senha atual" onfocusout="validarCamposSenhaFocusOut(this)"> 
				</div> 			
				
	 			<div class="form-group" >
					<label>Digite a nova senha</label> 
					<input type="password" class="form-control"  
					id="senha" placeholder="Digite a nova senha" name="senha" onfocusout="validarCamposSenhaFocusOut(this)"> 
				</div> 		    
				<small id='erroValidacao' class='form-text' style="display: none; color: #dc3545;">Preencha todos os campos</small>
				<div id="errosBackend" style="display:none;">
				</div>
	        </div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-danger" style="position: relative; right: 60%" data-dismiss="modal" >Fechar</button>
	          <button type="button" class="btn btn-success" id="btnCadastrar" onclick="submitMudancaSenha()">Alterar</button>
	        </div>
	      </div>
	    </div>
	  </div>
	<button type="button" id="btnModal" style="display:none;" data-toggle="modal" data-target="#modalSenha" data-backdrop="false"></button>
	<button type="button" id="btnModalSucessoSenha" style="display:none;" data-toggle="modal" data-target="#sucessoAlteracaoSenha" data-backdrop="false"></button>
	<button type="button" id="btnModalAlteracao" style="display:none;" data-toggle="modal" data-target="#modalAlteracao" data-backdrop="false"></button>

	<div class="modal" id="sucessoAlteracaoSenha">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Alteração de senha</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" onclick="fecharModalSucesso()">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Senha alterada com sucesso!</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal"
						onclick="fecharModalSucesso()">Ok</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal" id="modalAlteracao">
  		<div class="modal-dialog modal-dialog-centered">
	      <div class="modal-content">
	      
	        <div class="modal-header">
	          <h4 class="modal-title"></h4>
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	        </div>
	        
	        <div class="modal-body">
	        	<div id="forms">
		 			<div class="form-group" style="display: none;">
						<label id="labelExemplo"></label> 
						<input type="text" class="form-control"  
						id="" placeholder="" onfocusout="" id="inputExemplo"> 
					</div>   
				</div>   		    
				<small id='erroSenha' class='form-text' style="display: none; color: #dc3545;">Preencha todos os campos</small>
				<div id="errosBackend" style="display:none;">
				</div>
	        </div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-danger" style="position: relative; right: 60%" data-dismiss="modal" >Fechar</button>
	          <button type="button" class="btn btn-success" id="btnCadastrar">Alterar</button>
	        </div>
	      </div>
	    </div>
	</div>
</body>
</html>