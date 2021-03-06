<html>
<head>
<jsp:include page="../../../statics.jsp"></jsp:include>
<script>
function salvar(){
	$.post("http://localhost:8888/cartao/alterar", $("#form").serialize())
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
				mensagemFalha: "Falha ao alterar o cartao"
			});
		}
	})
	.fail(data => {
		tratarResponse({
			resultado: data,
			mensagemFalha: "Falha ao alterar o cartao"
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
<input type="hidden" name="id" value="${cartao.id}" />
	<div class="form-group">
		<label>Bandeira</label> 
		<input type="text" class="form-control" name="bandeira" value="${cartao.bandeira}"placeholder="Bandeira" required>
	</div>	
	<div class="form-group">
		<label>N�mero</label> 
		<input type="text" class="form-control" maxlength="16" value="${cartao.numero}" name="numero" placeholder="N�mero" required>
	</div>
	<div class="form-group">
		<label>Data vencimento</label> 
		<input type="text" id="data" class="form-control" value="${cartao.dtVencimento}" name="dtVencimento" placeholder="Data vencimento" required>
	</div>			
	<div class="form-group">
		<label>CVV</label> 
		<input type="text" class="form-control" name="cvv" value="${cartao.cvv}" maxlength="3" placeholder="CVV" required>
	</div>									
			<div class="row"  style="margin-top: 15px;">
				<div class="col-10 col-sm-10 col-md-10"><a href="http://localhost:8888/cartao/consulta" class="btn btn-warning">Voltar</a></div>
				<div class=""><button type="button" class="btn btn-success" onclick="salvar()">Alterar</button></div>
		</div>					
</form>
<jsp:include page="../../../componentes/modal.jsp"></jsp:include>
</body>
</html>