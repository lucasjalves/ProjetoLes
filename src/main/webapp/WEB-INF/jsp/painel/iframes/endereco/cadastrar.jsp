<html>
<head>
<jsp:include page="../../../statics.jsp"></jsp:include>
<script>
function salvar(){
	$.post("http://localhost:8888/endereco/adicionarEndereco", $("#formEndereco").serialize())
	.done(data => {
		if(data.mensagem.length === 0){
			tratarResponse({
				callback: () => {
					 window.location.href = "http://localhost:8888/endereco/consulta";
				}
			});
		} else {
			tratarResponse({
				resultado: data,
				mensagemFalha: "Falha ao alterar o endereco"
			});
		}
	})
	.fail(data => {
		tratarResponse({
			resultado: data,
			mensagemFalha: "Falha ao alterar o endereco"		
		});
	});
}
</script>
</head>
<body >
<form id="formEndereco">
	<div class="contadorEnderecos">
		<div class="form-group">
			<label>Nome</label> 
			<input type="text" class="form-control" name="nome"  placeholder="Nome" required>
		</div>
		<div class="form-group">
			<label>CEP</label> 
			<input type="text" class="form-control" name="cep" placeholder="CEP" required>
		</div>
		<div class="form-group">
			<label>Número</label> 
			<input type="text" class="form-control" name="numero" placeholder="numero" required>
		</div>		
		<div class="form-group">
			<label>Rua</label> 
			<input type="text" class="form-control" name="rua" placeholder="Rua" required>
		</div>		
		<div class="form-group">
			<label>Complemento</label> 
			<input type="text" class="form-control" name="complemento" placeholder="Complemento" required>
		</div>														
		<div class="form-group">
			<label>Bairro</label> 
			<input type="text" class="form-control" name="bairro"  placeholder="Bairro" required>
		</div>
		<div class="form-group">
			<label>Cidade</label> 
			<input type="text" class="form-control" name="cidade" placeholder="Cidade" required>
		</div>	
		<div class="form-group">
			<label>Estado</label> 
			<input type="text" class="form-control" name="uf" placeholder="Estado" required>
		</div>
		<div class="form-group">
			<label>País</label> 
			<input type="text" class="form-control" name="pais"placeholder="País" required>
		</div>		
		<div class="row"  style="margin-top: 15px;">
				<div class="col-10 col-sm-10 col-md-10"><a href="http://localhost:8888/endereco/consulta" class="btn btn-warning">Voltar</a></div>
				<div class=""><button type="button" class="btn btn-success" onclick="salvar()">Salvar</button></div>
		</div>		
	</div>												
</form>
<jsp:include page="../../../componentes/modal.jsp"></jsp:include>
</body>
</html>