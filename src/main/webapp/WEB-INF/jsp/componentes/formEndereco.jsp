<form id="formEndereco">
	<div class="form-group">
		<label>Nome</label> 
		<input type="text" class="form-control" name="nome" placeholder="Nome" required>
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
		<input type="text" class="form-control" name="bairro" placeholder="Bairro" required>
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
		<input type="text" class="form-control" name="pais" placeholder="País" required>
	</div>																
</form>

<script>
function cadastrarEndereco(callback, callbackFalha){
	$.post("http://localhost:8888/endereco/cadastrar", $("#formEndereco").serialize())
	.done(function(data){
		callback(data);
	})
	.fail(function(data){
		callbackFalha(data);
	});
}
</script>