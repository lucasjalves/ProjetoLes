<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../../statics.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<script>

function efetivarTicket(){
	$("#obs").val($("#textarea").val());
	$.post("http://localhost:8888/ticket/efetivar", $("#formConfirmacao").serialize())
	.done(data => {
		if(data.mensagem.length === 0){
			tratarResponse({
				callback: () => {
					$("#formEfetivacao").submit();
				}
			});
		} else {
			tratarResponse({
				resultado: data,
				mensagemFalha: "Falha ao efetivar o ticket"
			});
		}
	})
	.fail(data => {
		tratarResponse({
			resultado: data,
			mensagemFalha: "Falha ao efetivar o ticket"			
		});
	});
}
</script>
<title>Confirmação de troca do pedido</title>
<style>
@media (min-width: 200px)
.card-deck{
	flex-flow: row wrap;
	margin-right: -15px;
	margin-left: -15px;
}

</style>
</head>
<form id="formEfetivacao" action="http://localhost:8888/ticket/detalhe">
</form>

<form id="formConfirmacao" action="http://localhost:8888/ticket/efetivar">
	<input type="hidden" name="obs" id="obs" />

</form>
<body>
	<div class="container spacer">
		<div class="card">
			<div class="card-header">Itens para geração de ticket de ${confirmacao.tipoTicket}</div>
			<div class="card-body">
			<strong >Confirmação dos itens</strong>
				<div class="row" style="margin-top: 20px;">
					<div class="col-sm-12">
						<table class="table">
							<tbody>
								<c:forEach var="item" items="${pedido.itensPedido}">
									<c:if test="${item.quantidade > 0}">
										<tr>
											<td><strong>${item.quantidade}x ${item.produto.modelo}</strong></td>
											<td><label>R$ ${item.produto.precoVenda} </label></td>
										</tr>
									</c:if>
								</c:forEach>

							</tbody>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">		
						<strong>Escreva o motivo para a troca...(opcional)</strong>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<textarea rows="5" style="width: 85%" id="textarea"></textarea>
					</div>
				</div>
				<br>
			</div>
				
		</div>
			<div class="row"  style="margin-top: 15px;">
					<div class="col-10 col-sm-10 col-md-10"><a href="http://localhost:8888/ticket/trocacao?id=${idPedido}" class="btn btn-warning">Voltar</a></div>
					<div class=""><button type="button" class="btn btn-success" onclick="efetivarTicket()">Confirmar</button></div>
			</div>				
	</div>
	<jsp:include page="../../../componentes/modal.jsp"></jsp:include>
</body>
</html>