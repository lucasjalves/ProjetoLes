<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<title>Confirmação de pedido</title>
<style>
@media (min-width: 200px)
.card-deck{
	flex-flow: row wrap;
	margin-right: -15px;
	margin-left: -15px;
}
</style>
</head>
<body>
	<div class="container spacer">
		<div class="card">
			<div class="card-header">Efetivação</div>
			<div class="card-body">
				<div class="row">
					<div class="col-sm-6">
						<table class="table">
							<tbody>
								<c:forEach var="item" items="${pedido.itensPedido}">
									<tr>
										<td><strong>${item.quantidade}x ${item.produto.modelo}</strong></td>
										<td><label>R$ ${item.produto.precoVenda} </label></td>
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
	</div>
	<jsp:include page="../componentes/modal.jsp"></jsp:include>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>