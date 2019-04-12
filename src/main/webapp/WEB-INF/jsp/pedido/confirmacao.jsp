<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<title>Confirmação de pedido</title>
<script>
	$(document).ready(function(){
		$("#accordion option").each(function(){
			if($(this).text().replace(/\s/g,'').length === 0){
				$(this).detach();
			}
		});
	});
	function mostrar(){
		$("#valor1").show();
		$("#cartoes .aparecer").eq(0).show().removeClass("aparecer");
	}
</script>
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
		<div id="accordion">
			<div class="card">
				<div class="card-header" id="headingOne">
					<h5 class="mb-0">
						<button class="btn btn-link" data-toggle="collapse"
							data-target="#collapseOne" aria-expanded="true"
							aria-controls="collapseOne" style="color: black;">Informações do pedido</button>
					</h5>
				</div>

				<div id="collapseOne" class="collapse show"
					aria-labelledby="headingOne" data-parent="#accordion">
					<div class="card-body">
						<table class="table left" style="width: 45%;">
							<tbody>
							<c:forEach items="${pedido.itensPedido}" var="item">
								<tr>
									<td><strong>${item.quantidade}x ${item.produto.modelo}</strong></td>
									<td><label>R$ ${item.produto.precoVenda} </label></td>
								</tr>							
							</c:forEach>
							</tbody>
						</table>
						<div class="right">
							<p>+ Frete: R$ ${pedido.frete}</p>
							<c:if test="${pedido.cupomPedido != null}">
								<p>- Desconto: R$ ${pedido.desconto}  (Cupom: ${pedido.cupomPedido.codigo} de ${pedido.cupomPedido.valorDesconto}%)</p>							
							</c:if>
							<p style="color: green;">&nbsp;&nbsp;Total à pagar: R$ ${pedido.totalCompra}</p>
						</div>
					</div>
				</div>
			</div>
			<div class="card">
				<div class="card-header" id="headingTwo">
					<h5 class="mb-0">
						<button class="btn btn-link collapsed" data-toggle="collapse"
							data-target="#collapseTwo" aria-expanded="false"
							aria-controls="collapseTwo" style="color: black;">Endereço de entrega</button>
					</h5>
				</div>
				<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordion">
					<div class="card-body">
						<div class="form-group row">
							<div class="col-sm-10">
								<label>Endereço</label> <select class="form-control"
									id="exampleFormControlSelect1">
									<option selected>Casa 1</option>
									<option>Casa 2</option>
								</select>
							</div>
						</div>
						<div>
							<p>${pedido.endereco.cep}</p>
							<p>${pedido.endereco.rua}, ${pedido.endereco.numero}<c:if test="${pedido.endereco.complemento != null}">, ${pedido.endereco.complemento}</c:if></p>
							<p>${pedido.endereco.bairro}</p>
							<p>${pedido.endereco.cidade}, ${pedido.endereco.uf}, ${pedido.endereco.pais}</p>
						</div>
					</div>
				</div>
			</div>
			<div class="card">
				<div class="card-header" id="headingThree">
					<h5 class="mb-0">
						<button class="btn btn-link collapsed" data-toggle="collapse"
							data-target="#collapseThree" aria-expanded="false"
							aria-controls="collapseThree" style="color: black;">Pagamento
						</button>
					</h5>
				</div>
				<div id="collapseThree" class="collapse"
					aria-labelledby="headingThree" data-parent="#accordion">
					<div class="card-body">
					<c:if test="${!creditoZerado}">
						<p>Crédito disponível: <strong>R$ ${cliente.creditoDisponivel}</strong></p>	
						<h6>Deseja utilizar nesta compra?</h6>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">
						  <label class="form-check-label" for="inlineRadio1">Sim</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2" checked>
						  <label class="form-check-label" for="inlineRadio2">Não</label>
						</div>					
						<br>
						<hr>
					</c:if>
						<div class="row">
							<div class="col-sm-3">
								<h5 style="margin-top: 6px;">Cartões de crédito</h5>		
							</div>
							<div class="col-sm-3">
								<button class="btn btn-link">+ Cadastrar novo cartão</button>			
							</div>							
						</div>
						<br>
	
						<div id="cartoes">
							<div class="form-group row">
								<div class="col-sm-4">
									<select class="form-control" >
										<option value="-1">Selecione...</option>
										<c:forEach items="${cliente.cartoes}" var="cartao" >
											<option value="${cartao.id}">
												${cartao.bandeira} -- Final ${cartao.numero.substring(cartao.numero.size - 4)}
											</option>
										</c:forEach>
									</select>
								</div>	
								<div class="col-sm-2" id="valor1" style="display: none;">
									<input type="text" class="form-control" placeholder="Valor R$" />
								</div>																					
							</div>
							<div class="form-group row aparecer" id="parte2" style="display: none;">
								<div class="col-sm-4">
									<select class="form-control" >
										<option>MasterCard - Final 9876 <option>
										<option>VISA - Final 4567 <option>
									</select>
								</div>	
								<div class="col-sm-2">
									<input type="text" class="form-control" placeholder="Valor R$" />
								</div>																					
							</div>						
							<div class="form-group row aparecer" id="parte3" style="display: none;">
								<div class="col-sm-4">
									<select class="form-control" >
										<option>VISA - Final 4567 <option>
									</select>
								</div>	
								<div class="col-sm-2">
									<input type="text" class="form-control" placeholder="Valor R$" />
								</div>																					
							</div>		
							<div class="row">
								<div class="col-sm-4">
									<button class="btn btn-link" onclick="mostrar()">+ Adicionar cartão</button>		
								</div>
								<div class="col-sm-6" style="margin-top: 6px;">
									<p>Total à pagar com cartão: <strong>R$ ${pedido.totalCompra}</strong></p>	
								</div>	
								<div class="col-sm-2">
									<a class="btn btn-warning" href="http://localhost:8888/pedido/efetivacao">Efetivar</a>
								</div>								
								
					
							</div>																			
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../componentes/modal.jsp"></jsp:include>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>