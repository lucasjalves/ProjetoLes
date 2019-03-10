<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script>
function aplicarCupom(){
	if($("#codigoCupom").val().length === 0){
		$("#descontoCupom").text("Cupom AABB11 expirado").show().attr("style","color: red; border: none;");
		return;
	}
	$("#descontoCupom").text("Cupom AABB11 -- 5,00%").show().attr("style","color: green; border: none;");
	$("#desconto").text("R$ 75,75").attr("style", "color: green;");
	$("#total").text("R$ 1.439,25");
	$("#btnRemoverCupom").show();
}

function removerCupom(){
	$("#descontoCupom").hide();
	$("#desconto").text("R$ 0,00").attr("style", "");
	$("#total").text("R$ 1.515,00");
	$("#btnRemoverCupom").hide();	
	
}
</script>
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
				<c:if test="${carrinho != null}">
					<c:forEach items="${carrinho.keySet()}" var="id">
					<c:set var="itemCarrinho" value="${carrinho.get(id)}"/>
						<tr>
							<td><img src="http://placehold.it/100x100" alt="..."
								class="img-responsive left">
								<table class="left">
									<tr>
										<td style="border: none;"><strong>${itemCarrinho.produto.modelo}</strong></td>
									</tr>
									<tr>
										<td style="border: none;">${itemCarrinho.produto.marca}</td>
									</tr>
								</table></td>
							<td><input type="number" class="form-control"
								style="width: 80px; margin-top: 30px;" value="${itemCarrinho.quantidade}" /></td>
							<td><label style="margin-top: 35px; font-weight: bold;">R$
									${itemCarrinho.produto.precoVenda} </label></td>
							<td><label style="color: red; margin-top: 35px;">R$
									${itemCarrinho.valorTotal}</label></td>
							<td><button style="margin-top: 30px;"
									class="btn btn-outline-danger">Remover</button></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
			
		</table>
		<div class="card-deck">
			<div class="card" style="width: 18rem;height: 250px;">
				<div class="card-header"><strong>Endereços</strong></div>
				<div class="card-body">
					<div class="form-group row">
						<div class="col-sm-5">
							<select class="form-control" id="exampleFormControlSelect1">
								<option>Casa 1</option>
								<option>Casa 2</option>
							</select>
						</div>
					</div>
					<button class="btn btn-success" data-toggle="modal" data-target="#cadastro">Cadastrar novo</button>
				</div>
			</div>
			<div class="card" style="width: 18rem;">
				<div class="card-header">
					<strong>Cupom</strong>
				</div>
				<div class="card-body">
					<ul class="list-group list-group-flush">

						<li class="list-group-item"><strong>Cupom</strong><label
							class="right"><input type="text" id="codigoCupom" class="form-control" /></label></li>
						<li class="list-group-item right" style="display: none; color: green; border: none;" id="descontoCupom"></li>
						<li class="list-group-item" style="border: none;"> 
							<button class="btn btn-success" style="margin-left: 15px;" onclick="aplicarCupom();">Aplicar</button>
							<button class="btn btn-danger" id="btnRemoverCupom" style="display:none;" onclick="removerCupom();">Remover</button>
						</li>
					</ul>
					
				</div>
			</div>
			<div class="card right" style="width: 18rem; height: 250px;">
				<ul class="list-group list-group-flush">
					<li class="list-group-item"><strong>Total</strong><label class="right">R$ 1.500,00</label></li>
					<li class="list-group-item"><strong>Frete</strong><label class="right">R$ 15,00</label></li>
					<li class="list-group-item"><strong>Desconto</strong><label class="right" id="desconto">R$ 0,00</label></li>
					<li class="list-group-item"><strong>Total da compra</strong><label class="right" id="total">R$ 1.515,00</label></li>
				</ul>
			</div>

		</div>
			 <a class="btn btn-warning right" href="http://localhost:8888/pedido/confirmacao" style="margin-left: 15px; margin-top: 30px;" >Finalizar compra</a>
	</div>

	<div class="modal fade" id="cadastro">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Novo endereço</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
				<form id="form">
					<div class="form-group">
						<label>CEP</label> 
						<input type="text" class="form-control" name="nome" placeholder="Rua" required>
					</div>
					<div class="form-group">
						<label>Rua</label> 
						<input type="text" class="form-control" name="cpfCnpj" placeholder="Rua" required>
					</div>		
					<div class="form-group">
						<label>Complemento</label> 
						<input type="text" class="form-control" name="senha" placeholder="Estado" required>
					</div>														
					<div class="form-group">
						<label>Bairro</label> 
						<input type="text" class="form-control" name="dtNascimento" placeholder="Bairro" required>
					</div>
					<div class="form-group">
						<label>Cidade</label> 
						<input type="text" class="form-control" name="username" placeholder="Cidade" required>
					</div>	
					<div class="form-group">
						<label>Estado</label> 
						<input type="text" class="form-control" name="senha" placeholder="Estado" required>
					</div>
					
					<div class="form-group">
						<label>País</label> 
						<input type="text" class="form-control" name="senha" placeholder="País" required>
					</div>																
				</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" data-dismiss="modal">Save changes</button>
	      </div>
	    </div>
	  </div>
	</div>
	<jsp:include page="../componentes/modal.jsp"></jsp:include>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>