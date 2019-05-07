<html>
<head>
<jsp:include page="../../../statics.jsp"></jsp:include>
<script>
function salvar(){
	$.post("http://localhost:8888/cartao/cadastrar", $("#form").serialize())
	.done(data => {
		if(data.mensagem.length === 0){
			tratarResponse({
				callback: () => {
					 window.location.href = "http://localhost:8888/cartao/consulta";
				}
			});
		} else {
			tratarResponse({
				resultado: data,
				mensagemFalha: "Falha ao cadastrar o cartao"
			});
		}
	})
	.fail(data => {
		tratarResponse({
			resultado: data,
			mensagemFalha: "Falha ao cadastrar o cartao"
		});
	});
}

$(document).ready(function(){
	$("#data").mask("00/00/0000");
})
</script>
</head>
<body >
<form id="form">
	<div class="form-group">
		<label>Bandeira</label> 
		<input type="text" class="form-control" name="bandeira" placeholder="Bandeira" required>
	</div>	
	<div class="form-group">
		<label>Número</label> 
		<input type="text" class="form-control" maxlength="16" name="numero" placeholder="Número" required>
	</div>
	<div class="form-group">
		<label>Data vencimento</label> 
		<input type="text" id="data" class="form-control" name="dtVencimento" placeholder="Data vencimento" required>
	</div>			
	<div class="form-group">
		<label>CVV</label> 
		<input type="text" class="form-control" name="cvv" maxlength="3" placeholder="CVV" required>
	</div>									
			<div class="row"  style="margin-top: 15px;">
				<div class="col-10 col-sm-10 col-md-10"><a href="http://localhost:8888/cartao/consulta" class="btn btn-warning">Voltar</a></div>
				<div class=""><button type="button" class="btn btn-success" onclick="salvar()">Salvar</button></div>
		</div>					
</form>
<jsp:include page="../../../componentes/modal.jsp"></jsp:include>
</body>
</html>