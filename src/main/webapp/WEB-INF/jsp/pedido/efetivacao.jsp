<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<title>Confirma��o de pedido</title>
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
			<div class="card-header">Efetiva��o</div>
			<div class="card-body">
				<div class="row">
					<div class="col-sm-6">
						<table class="table">
							<tbody>
								<tr>
									<td><strong>1x Playstation 4</strong></td>
									<td><label>R$ 1500,00 </label></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="col-sm-6">
						<p>&nbsp;&nbsp;Total dos itens: R$ 1.500,00</p>
						<p>+ Frete: R$ 15,00</p>
						<p>- Desconto: R$ 75,75 (Cupom: AABB11 de 5,00%)</p>
						<p>&nbsp;&nbsp;Total do pedido: R$ 1.439,25</p>
					</div>

				</div>
				<br>
				<hr>
				<div class="row">
					<div class="col-sm-3">
						<h5>Endere�o</h5>
					</div>
				</div>
				<div>
					<p>08676-000</p>
					<p>Rua teste, 801, complemento Teste</p>
					<p>Vila figueira</p>
					<p>S�o paulo, SP, Brasil</p>
				</div>
				<br>
				<hr>
				<div class="row">
					<div class="col-sm-3">
						<h5>Pagamento</h5>
					</div>
				</div>
				<p>
					Cr�dito utilizado: <strong> R$ 1.000,00</strong>
				</p>
				<p>Pagamento efetuado em: <strong>01/01/2019 �s 00:00:00</strong></p>
				<h6>Cart�es</h6>
				<ul>
					<li>MasterCard - Final 0123</li>
					<li>MasterCard - Final 9876</li>
					<li>VISA - Final 4567</li>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../componentes/modal.jsp"></jsp:include>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>