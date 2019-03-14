<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<script>
	$(document).ready(function(){
		$("#dataVencimento").val($("#dataVencimento").val().split("-").reverse().join("/"));
		var desconto = $("#desconto").val();
		desconto = parseFloat(desconto).toFixed(2).replace(/\./g, ",");;
		$("#desconto").val(desconto);
		$("#btnCadastrar").on("click", function(){
			var desconto = $("#desconto").val();
			desconto = desconto.replace(/\./g, "").replace(",",".");
			$("#hiddenDesconto").val(desconto);
			$.post("http://localhost:8888/cupom/alterar", $("#form").serialize())
				.done(function(data){
					abrirModalSucessoOuFalha(data, "Cupom alterado com sucesso!", "Falha ao alterado o Cupom", data.mensagem.length);
				})
				.fail(function(data){
					abrirModalSucessoOuFalha(data, "Cupom alterado com sucesso!", "Falha ao alterado o Cupom", 1);
				});
		});	
		$("#desconto").maskMoney({prefix:'% ', allowNegative: false, thousands:'.', decimal:',', affixesStay: false});
		$("#dataVencimento").mask("00/00/0000");
		$("#status").val('${cupom.status}');
	});

</script>
<title>Cadastro de Cupom</title>
</head>
<body>
	<div class="container" style="margin-top: 100px;">
		<div class="card">
			<h5 class="card-header">Cadastrar</h5>
			<div class="card-body">
				<h5 class="card-title">Cadastre um novo cupom</h5>
				<form id="form">
				<input type="hidden" id="id" name="id" value="${cupom.id}" />
				<input type="hidden" name="valorDesconto" id="hiddenDesconto" />
					<div class="form-group">
						<label>Código</label> 
						<input type="text" class="form-control" name="codigo" placeholder="Código" value="${cupom.codigo}" required>
					</div>
					<div class="form-group">
						<label>Desconto</label> 
						<input type="text" class="form-control" id="desconto" placeholder="%"  value="${cupom.valorDesconto}" required>
					</div>													
					<div class="form-group">
						<label>Data vencimento</label> 
						<input type="text" class="form-control" id="dataVencimento" name="dataVencimento"  value="${cupom.dataVencimento}" placeholder="DD/MM/YYYY" required>
					</div>	
					<div class="form-group">
					    <label>Status</label>
					    <select class="form-control" id="status" name="status">
					      <option selected value="true">Ativo</option>
					      <option value="false">Inativo</option>
					    </select>
					 </div>														
				</form>
				<button class="btn btn-primary" id="btnCadastrar">Alterar</button>

			</div>
		</div>
	</div>
	<jsp:include page="../componentes/modal.jsp"></jsp:include>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>