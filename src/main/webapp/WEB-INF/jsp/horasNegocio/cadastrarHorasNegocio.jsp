<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
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
<script>
	$(document).ready(function() {
		$("#horaInicio").mask("00:00");
		$("#horaFim").mask("00:00");
		
		$(".obrigatorio").each(function(){
			$(this).on("focusout", function(){
				validarCampoFocusOut($(this));
			});
		});
	});
	
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
			
		}
	}
</script>
</head>
<body>
	<div class="container" style="padding-top: 30px;">
		<div class="card">
			<div class="card-header">Cadastro de horas de negócio</div>
			<div class="card-body">
				<h5 class="card-title">Cadastre horas de funcionamento de
					negócio</h5>
				<form action="#">
					<div class="form-row">
						<div class="form-group col-md-4">
							<label>Nome </label> <input type="text"
								class="form-control obrigatorio" name="nome" id="nome"
								placeholder="Digite o nome completo">
						</div>
						<div class="form-group col-md-4">
							<label>Descrição </label> <input type="text" class="form-control obrigatorio"
								name="descricao" id="descricao" placeholder="Descrição">
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-4">
							<label>Hora de início</label> <input type="text"
								class="form-control obrigatorio" name="" id="horaInicio">
						</div>
						<div class="form-group col-md-4">
							<label>Hora de fim</label> <input type="text"
								class="form-control obrigatorio" name="" id="horaFim">
						</div>
					</div>
				</form>
				<h5 class="card-title">Dias da semana</h5>
				<div class="form-row">
					<div class="form-group col-md-4">
						<div class="form-check">
							<input class="form-check-input" type="checkbox" value=""
								id="segunda"> <label class="form-check-label"
								for="segunda"> Segunda-feira </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" value=""
								id="terca"> <label class="form-check-label" for="terca">
								Terça-feira </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" value=""
								id="quarta"> <label class="form-check-label"
								for="quarta"> Quarta-feira </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" value=""
								id="quinta"> <label class="form-check-label"
								for="quinta"> Quinta-feira </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" value=""
								id="sexta"> <label class="form-check-label" for="sexta">
								Sexta-feira </label>
						</div>
					</div>
					<div class="form-group col-md-4">
						<div class="form-check">
							<input class="form-check-input" type="checkbox" value=""
								id="sabado"> <label class="form-check-label"
								for="sabado"> Sábado </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" value=""
								id="domingo"> <label class="form-check-label"
								for="domingo"> Domingo </label>
						</div>
					</div>
				</div>
				<p class="btn btn-primary" id="submit">Cadastrar</p>
			</div>
		</div>
	</div>
</body>
</html>