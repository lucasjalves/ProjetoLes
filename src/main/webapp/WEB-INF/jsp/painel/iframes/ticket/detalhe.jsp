<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../../statics.jsp"></jsp:include>
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
			<div class="card-header">Ticket: ${ticket.id}</div>
			<div class="card-body">
				<div class="row">
					<div class="col-sm-12">				
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
				</div>
				<div class="row">
					<div class="col-sm-2">Status</div>
					<div class="col-sm-2"><strong>${ticket.status}</strong></div>
				</div>
				<div class="row">
					<div class="col-sm-2">Tipo</div>
					<div class="col-sm-2"><strong>${ticket.tipo}</strong></div>
				</div>	
				<div class="row">
					<div class="col-sm-2">Data e hora</div>
					<div class="col-sm-2"><strong>${ticket.dtPedido}</strong> às <strong>${ticket.hora}</strong></div>
				</div>	
				<div class="row">
					<div class="col-sm-2">Motivo</div>
					<div class="col-sm-10"><strong>${ticket.obs}</strong></div>
				</div>							
			</div>
		</div>
	</div>
</body>
	<jsp:include page="../../../componentes/modal.jsp"></jsp:include>
</html>