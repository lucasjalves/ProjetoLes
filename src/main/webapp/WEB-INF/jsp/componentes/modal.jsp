
<button type="button" class="btn btn-primary" data-toggle="modal" id="btnAbrirModal" style="display: none;"
	data-target="#myModal">Open modal</button>

<div class="modal" id="myModal">
	<div class="modal-dialog">
		<div class="modal-content">

			<div class="modal-header">
				<h4 class="modal-title" id="tituloModal"></h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<div class="modal-body" id="textoModal"></div>

			<div class="modal-footer">
				<button type="button" class="btn btn-info" data-dismiss="modal">Ok</button>
			</div>

		</div>
	</div>
</div>

<script>
	function abrirModalSucessoOuFalha(resultado, mensagemSucesso, mensagemFalha, qtdeMensagemAExibir){
		$("#textoModal").html("");
		if(resultado === undefined || resultado == null || resultado.mensagem === undefined){
			$("#tituloModal").text("Falha");
			if(mensagemFalha === undefined || mensagemFalha == null){
				$("#textoModal").text("Ocorreu um problema, tente novamente mais tarde!");
			}else{
				$("#textoModal").text(mensagemFalha);
			}
			$("#btnAbrirModal").click();
			return;
		}
		if(resultado.mensagem.length > 0){
			$("#tituloModal").text("Falha");
			$.each(resultado.mensagem, function(index, mensagemErro){
				if(qtdeMensagemAExibir == 1){
					$("#textoModal").text(mensagemErro.mensagem);
				}
				if(qtdeMensagemAExibir > 1 || qtdeMensagemAExibir === undefined || qtdeMensagemAExibir === null){
					var texto = "<p>" + mensagemErro.mensagem + "</p>";
					$("#textoModal").append(texto);
				}
			});
			$("#btnAbrirModal").click();
			return;
		}
		if(resultado.mensagem.length === 0){
			$("#tituloModal").text("Sucesso");
			$("#textoModal").text(mensagemSucesso);
			$("#btnAbrirModal").click();
		}
	}

</script>