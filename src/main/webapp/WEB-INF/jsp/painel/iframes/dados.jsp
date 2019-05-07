<html>
<head>
<jsp:include page="../../statics.jsp"></jsp:include>
<script>
	function alterar(){
		$.ajax({
			method: "POST",
			data: $("#form").serialize(),
			url: "http://localhost:8888/cliente/alterar",
			success: function(data){
				if(data.mensagem.length === 0){
					tratarResponse({
						callback: function(){
							$("#confirmacaoDados").submit();
						}
					});				
				}else{
					tratarResponse({
						resultado: data
					});					
				}
			},
			error: function(data){
				tratarResponse({
					resultado: data,
					mensagemFalha: "Falha falha ao altera os dados do cliente"
				});			
			}
		});		
	}
	
	$(document).ready(function(){
		$("#genero").val('${cliente.genero}');
		$("#dtNascimento").mask("00/00/0000");
	});
</script>
</head>
<body>
<form id="confirmacaoDados" method="POST" action="http://localhost:8888/cliente/dados"></form>
	<form id="form">
	
		<input type="hidden" name="id" value="${cliente.id}" />
		<input type="hidden" name="cpfCnpj" value="${cliente.cpfCnpj}" />
		<input type="hidden" name="ativo" value="${cliente.ativo}" />
		<input type="hidden" name="tipoUsuario" value="${cliente.tipoUsuario}" />
		<div class="form-group">
			<label><strong>Crédito disponível: ${cliente.creditoDisponivel} R$</strong> </label> 
		</div>
		
		<div class="form-group">
			<label>Nome Completo</label> <input type="text" class="form-control"
				name="nome" placeholder="Nome" value="${cliente.nome}" required>
		</div>
		<div class="form-group">
			<label>CPF/CNPJ</label> <input type="text" class="form-control"
				placeholder="CPF/CNPJ" value="${cliente.cpfCnpj}"
				disabled>
		</div>
		<div class="form-group">
			<label>Data nascimento</label> <input type="text"
				class="form-control" id="dtNascimento" name="dtNascimento" placeholder="DD/MM/YYYY"
				value="${cliente.dtNascimento}" required>
		</div>
		<div class="form-group">
			<label>E-mail</label> 
			<input type="text" class="form-control" name="email" placeholder="Nome" id="email" value="${cliente.email}" required>
		</div>
		<div class="form-group">
			<label>Gênero</label> <select class="form-control" name="genero" id="genero">
				<option selected>M</option>
				<option>F</option>
			</select>
		</div>
		<div class="form-group">
			<label>Username</label> <input type="text" class="form-control"
				name="username" placeholder="Username" value="${cliente.username}" required>
		</div>
		<div class="form-group">
			<label>Senha</label> <input type="password" class="form-control"
				name="senha" placeholder="Senha" required>
		</div>
	</form>
	<button class="btn btn-primary" id="btnCadastrar" onclick="alterar()">Alterar</button>
	<jsp:include page="../../componentes/modal.jsp"></jsp:include>
</body>
</html>