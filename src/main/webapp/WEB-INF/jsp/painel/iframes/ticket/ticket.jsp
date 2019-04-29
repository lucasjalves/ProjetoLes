<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../../statics.jsp"></jsp:include>
<script>
var tipoTicket = "DEVOLUCAO";
function confirmarTroca(){
	let obj = [];
	$("tbody tr").each(function(){
		const input = $(this).find("input");
		var inputVal = input.val();
		if(inputVal === ""){
			inputVal = "0";
		}
		const produto = {
			id : input.attr("idItem"),
			quantidade: inputVal,
			produto : {
				idProduto: input.attr("idProduto"),
				id: input.attr("idProdutoCompra")				
			}
		};
		obj.push(produto);
		
	});
	console.log(obj);
	$.ajax({
		method: "POST",
		data: JSON.stringify(obj),
		url: "http://localhost:8888/ticket/confirmar?id=${pedido.id}&tipo="+tipoTicket,
		contentType: "application/json",
		success: function(data){
			if(data.mensagem.length === 0){
				tratarResponse({
					callback: function(){
						$("#formConfirmacao").submit();
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
				mensagemFalha: "Falha falha ao processar o ticket de " + tipoTicket 
			});			
		}
	});
	
	
}
</script>
<meta charset="ISO-8859-1">
<title>Troca do pedido</title>
<style>
@media (min-width: 200px)
.card-deck{
	flex-flow: row wrap;
	margin-right: -15px;
	margin-left: -15px;
}
</style>
</head>
<form id="formConfirmacao" action="http://localhost:8888/ticket/confirmacao">

</form>
<body>
	<div class="container spacer">
		<div class="card">
			<div class="card-header">Escolha os produtos para troca</div>
			<div class="card-body">
				<div class="row">
					<div class="col-sm-6">
						<h6>Qual o tipo de ticket?</h6>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio"  onclick="tipoTicket = 'DEVOLUCAO'" name="radio" id="devolucao" value="option1" checked>
						  <label class="form-check-label" for="inlineRadio1">Devolução</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" onclick="tipoTicket = 'TROCA'" name="radio" id="naoUtilizar" value="option2" >
						  <label class="form-check-label" for="inlineRadio2">Troca</label>
						</div>					
						<br>
						<hr>					
						<table class="table">
							<tbody>
								<c:forEach var="item" items="${pedido.itensPedido}">
									<tr>
										<td><strong>${item.quantidade}x ${item.produto.modelo}</strong></td>
										<td><label>R$ ${item.produto.precoVenda} </label></td>
										<td><input type="number" idProduto="${item.produto.idProduto}" max="${item.quantidade}" idItem="${item.id}" idProdutoCompra="${item.produto.id}" style="width: 60px" />
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="col-sm-6">
						<p>&nbsp;&nbsp;Total dos itens: R$ ${pedido.total}</p>
						<p>+ Frete: R$ ${pedido.frete}</p>
						<c:if test="${pedido.cupomPedido ne null}">
							<p>- Desconto: R$ ${pedido.desconto} (Cupom: ${pedido.cupomPedido.codigo} de ${pedido.cupomPedido.valorDesconto}%)</p>
						</c:if>
						<p>&nbsp;&nbsp;Total do pedido: R$ ${pedido.totalCompra}</p>
					</div>

				</div>
				<br>
				<hr>
				<div class="row">
					<div class="col-sm-3">
						<h5>Endereço</h5>
					</div>
				</div>
				<div>
					<p>${pedido.endereco.cep}</p>
						<p>${pedido.endereco.rua}, ${pedido.endereco.numero}
						<c:if test="${pedido.endereco.complemento ne null}">
							, ${pedido.endereco.complemento}
						</c:if>
						</p>
					<p>${pedido.endereco.bairro}</p>
					<p>${pedido.endereco.cidade},${pedido.endereco.uf}, ${pedido.endereco.pais}</p>
				</div>
				<br>
				<hr>
				<div class="row">
					<div class="col-sm-3">
						<h5>Pagamento</h5>
					</div>
				</div>

				<p>
					Crédito utilizado: <strong> R$ ${pedido.creditoUtilizado}</strong>
				</p>				


				<p>Pagamento efetuado em: <strong>${pedido.dtPedido} às ${pedido.hora}</strong></p>
				<h6>Cartões</h6>
				<ul>
					<c:forEach items="${pedido.cartoes}" var="cartao" >
						<li value="${cartao.id}">
							${cartao.bandeira} -- Final ${cartao.numero.substring(cartao.numero.length() - 4)}
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="row" style="margin-top: 15px;">
			<div class="col-10 col-sm-10 col-md-10"><a href="http://localhost:8888/pedido/consulta" class="btn btn-warning">Voltar</a></div>
			<div class=""><button type="button" class="btn btn-success" onclick="confirmarTroca()">Confirmar</button></div>
		</div>
	</div>
</body>
	<jsp:include page="../../../componentes/modal.jsp"></jsp:include>
</html>