<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<title>Carrinho</title>
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
		<table class="table table-bordered">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Produtos</th>
					<th scope="col" style="width: 100px;">Quantidade</th>
					<th scope="col" style="width: 165px;">Preço Unitário</th>
					<th scope="col" style="width: 165px;">Total</th>
					<th scope="col" style="width: 100px;">Ação</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><img src="http://placehold.it/100x100" alt="..."
						class="img-responsive left">
						<table class="left">
							<tr>
								<td style="border: none;"><strong>Playstation 4</strong></td>
							</tr>
							<tr>
								<td style="border: none;">Sony</td>
							</tr>
						</table></td>
					<td><input type="number" class="form-control"
						style="width: 80px; margin-top: 30px;" value="1" /></td>
					<td><label style="margin-top: 35px; font-weight: bold;">R$
							1500,00 </label></td>
					<td><label style="color: red; margin-top: 35px;">R$
							1.500,00</label></td>
					<td><button style="margin-top: 30px;"
							class="btn btn-outline-danger">Remover</button></td>
				</tr>

			</tbody>

		</table>
		<div class="card-deck">
			<div class="card" style="width: 18rem;">
				<div class="card-header"><strong>Endereços</strong></div>
				<div class="card-body">
					<div class="form-group row">
						<div class="col-sm-10">
							<select class="form-control" id="exampleFormControlSelect1">
								<option>Casa 1</option>
								<option>Casa 2</option>
							</select>
						</div>
					</div>
					<button class="btn btn-success">Cadastrar novo</button>
				</div>
			</div>
			<div class="card" style="width: 18rem;">
				<div class="card-header">
					<strong>Endereços</strong>
				</div>
				<div class="card-body">
					<ul class="list-group list-group-flush">

						<li class="list-group-item"><strong>Cupom</strong><label
							class="right"><input type="text" class="form-control" /></label></li>

					</ul>
				</div>
			</div>
			<div class="card right" style="width: 18rem;">
				<ul class="list-group list-group-flush">
					<li class="list-group-item"><strong>Total</strong><label
						class="right">R$ 1.500,00</label></li>
					<li class="list-group-item"><strong>Frete</strong><label
						class="right">R$ 15,00</label></li>
					<li class="list-group-item"><strong>Desconto</strong><label
						class="right">R$ 0,00</label></li>
					<li class="list-group-item"><strong>Total</strong><label
						class="right">R$ 1.515,00</label></li>
				</ul>
			</div>

		</div>
	</div>
	<jsp:include page="../componentes/modal.jsp"></jsp:include>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>