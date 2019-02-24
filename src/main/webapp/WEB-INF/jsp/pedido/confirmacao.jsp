<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
								<tr>
									<td><strong>1x Playstation 4</strong></td>
									<td><label>R$ 1500,00 </label></td>
								</tr>

							</tbody>
						</table>
						<div class="right">
							<p>&nbsp;&nbsp;Total dos itens: R$ 1.500,00 </p>
							<p>+ Frete: R$ 15,00</p>
							<p>- Desconto: R$ 75,75  (Cupom: AABB11 de 5,00%)</p>
							<p style="color: green;">&nbsp;&nbsp;Total à pagar: R$ 1.439,25</p>
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
							<p>08676-000</p>
							<p>Rua teste, 801, complemento Teste</p>
							<p>Vila figueira</p>
							<p>São paulo, SP, Brasil</p>
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
						<p>Crédito disponível: <strong>R$ 1.000,00</strong></p>	
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
										<option selected>MasterCard - Final 0123</option>
										<option>MasterCard - Final 9876 <option>
										<option>VISA - Final 4567 <option>
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
									<p>Total à pagar com cartão: <strong>R$ 439,25</strong></p>	
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