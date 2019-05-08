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
	var qtdeCartoes = '${cliente.cartoes.size()}'
	$(document).ready(function(){
		$("#accordion option").each(function(){
			if($(this).text().replace(/\s/g,'').length === 0){
				$(this).detach();
			}
		});
		
		$("#data").mask("00/00/0000");
	});
    function mostrar(){
   	 $("#cartoes input:first")
   	 	.parent()
   	 	.show()
   	 	.maskMoney({prefix:'R$ ', allowNegative: false, thousands:'.', decimal:',', affixesStay: false});
   	 
       let cartoesSelecionados = [];
       let clone = $("#cartaoPrincipal").clone();
       
       clone.find("input").maskMoney({prefix:'R$ ', allowNegative: false, thousands:'.', decimal:',', affixesStay: false});
       
       $("#cartoes select").each(function(){
           cartoesSelecionados.push($(this).val());
       });
       
       btnFechar = $("#fechar").clone().show();
       clone.append(btnFechar);
       $("#rowCartoes").append("<div class='form-group row'>" + clone.html() + "</div>");
       
      
    
       $("#rowCartoes .form-group").each(function(){
           $(this).find("input").maskMoney({prefix:'R$ ', allowNegative: false, thousands:'.', decimal:',', affixesStay: false})
       });
       
       if($("#rowCartoes .form-group").length == qtdeCartoes){
           $("#mostrarCartao").hide();
       }
       
       atualizarBotoesFechar();
   }
    function atualizarBotoesFechar(){
    	var cont = 0;
    	$("#rowCartoes .form-group").each(function(){
    		$(this).find("a").attr("onclick", "removerCartao("+cont+")");
    		cont ++;
    	});
    }
    
    function removerCartao(indice){
    	$("#rowCartoes .form-group").eq(indice).remove();
        if($("#rowCartoes .form-group").length < qtdeCartoes){
            $("#mostrarCartao").show();
        }
    	atualizarBotoesFechar();
    }
	function cadastrarCartao(){
		$.post("http://localhost:8888/cartao/cadastrar", $("#formCadastroCartao").serialize())
		.done(function(data){
			if(data.mensagem.length === 0){
				tratarResponse({
					callback: function(){
						$("#formPedido").submit();
					}
				});				
			}else{
				tratarResponse({
					resultado: data
				});					
			}

		})
		.fail(function(data){
			tratarReponse({
				resultado: data,
				mensagemFalha: "Falha ao cadastrar o cartão"
			});
		});		
	}
	
	function gerarJsonCartoes(){
        var cartoes = [];
        $("#rowCartoes .form-group").each(function(){
            var cartao = $(this).find("select").val();
            var valor = $(this).find(".vlr").val();
            var cvv = $(this).find(".cvv").val();
            var json = {
                valor : valor,
                id: parseInt(cartao),
                cvv: cvv
            }
            cartoes.push(json);
        });
		
        if(cartoes.length === 1){
        	cartoes[0].valor = '${pedido.totalCompra}';
        }
        
		return cartoes;
	}
	function efetivar(){
		var cartoes = gerarJsonCartoes()
		var pedido = {
			id : ${pedido.id},
			cartoes: cartoes
		}	
		
		if($("#utilizar").prop("checked")){
			pedido.creditoUtilizado = '${cliente.creditoDisponivel}';
		}
		console.log(pedido);
		$.ajax({
			method: "POST",
			url: "http://localhost:8888/pedido/efetivar",
			data : JSON.stringify(pedido),
			contentType: "application/json",
			success: function(data){
				tratarResponse({
					resultado: data
				});
			
				if(data.mensagem.length === 0){
					tratarResponse({
						callback: function(){
							$("#formPedidoEfetivacao").submit();
						}
					});
				}
			},
			error: function(data){
				tratarResponse({
					resultado: data,
					mensagemFalha: "Falha ao efetivar o pedido"
				})
			}
		})
	}
	
	function utilizarCredito(utilizar) {
		const creditoDisponivel = parseFloat('${cliente.creditoDisponivel}'.replace(/\./g, "").replace(/\,/g, "."));
		const totalCompra = parseFloat('${pedido.totalCompra}'.replace(/\./g, "").replace(/\,/g, "."));
		var totalAPagar = totalCompra;
		if(utilizar){
			totalAPagar = totalCompra - creditoDisponivel;
		}
		
		if(totalAPagar < 0.00){
			totalAPagar = 0.00;
		}

		$("#totalCompra").text(formatNumber(totalAPagar));
	}
	
	function formatNumber(num) {
	    let string = num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
	    let arrayString = string.split(".");
	    if(arrayString.length === 1){
	        return string + ".00";
	    }
	    if(arrayString[1].length === 1){
	        return string + "0";
	    }
	    return string;
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
<form id="formPedido" action="http://localhost:8888/pedido/confirmacao" method="POST">
 <input type="hidden" name="id" value="${pedido.id}" />
</form>

<form id="formPedidoEfetivacao" action="http://localhost:8888/pedido/efetivacao" method="POST">
	<input type="hidden" name="id" value="${pedido.id}" />
</form>
<div id="fechar" style="display: none;"><div class="col-sm-2"><a href="#" style="color: red" class="btn btn-link"> X</a></div></div>
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
						  <input class="form-check-input" type="radio"  onclick="utilizarCredito(true)" name="radio" id="utilizar" value="option1">
						  <label class="form-check-label" for="inlineRadio1" >Sim</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" onclick="utilizarCredito(false)" name="radio" id="naoUtilizar" value="option2" checked>
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
								<button class="btn btn-link" onclick="$('#btnAbrirModalCartao').click()">+ Cadastrar novo cartão</button>			
							</div>							
						</div>
						<br>
	
						<div id="cartoes">
							<div id="rowCartoes">
								<div class="form-group row" id="cartaoPrincipal">
									<div class="col-sm-4">
										<select class="form-control" >
											<option value="-1">Selecione...</option>
											<c:forEach items="${cliente.cartoes}" var="cartao" >
												<option value="${cartao.id}">
													${cartao.bandeira} -- Final ${cartao.numero.substring(cartao.numero.length() - 4)}
												</option>
											</c:forEach>
										</select>
									</div>	
									
									<div class="col-sm-2" style="display: none;">
										<input type="text" class="form-control vlr" placeholder="Valor R$" />
									</div>		
									
									<div class="col-sm-2">
										<input type="text" class="form-control cvv" placeholder="CVV" />
									</div>																															
								</div>	
							</div>
							<div class="row">
								
								<div class="col-sm-4">
									<c:if test="${cliente.cartoes.size() > 1}">
									<button class="btn btn-link" id="mostrarCartao" onclick="mostrar()">+ Adicionar cartão</button>	
									</c:if>	
								</div>
								<div class="col-sm-6" style="margin-top: 6px;">
									<p>Total à pagar com cartão: <strong id="totalCompra">R$ ${pedido.totalCompra}</strong></p>	
								</div>	
								<div class="col-sm-2">
									<a class="btn btn-warning" href="#" onclick="efetivar()">Efetivar</a>
								</div>								
								
					
							</div>																			
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<button type="button" class="btn btn-primary" data-toggle="modal" id="btnAbrirModalCartao" style="display: none;"
		data-target="#myModalCartao">Open modal</button>
	
	<div class="modal" id="myModalCartao">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
	
				<div class="modal-header">
					<h4 class="modal-title">Cadastrar</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
	
				<div class="modal-body">
					<form id="formCadastroCartao">
						<div class="form-group">
							<label>Bandeira</label> 
							<input type="text" class="form-control" name="bandeira" placeholder="Bandeira" required>
						</div>	
						<div class="form-group">
							<label>Número</label> 
							<input type="text" class="form-control" maxlength="16" name="numero" placeholder="Número" required>
						</div>
						<div class="form-group">
							<label>Data vencimento</label> 
							<input type="text" id="data" class="form-control" name="dtVencimento" placeholder="Data vencimento" required>
						</div>			
						<div class="form-group">
							<label>CVV</label> 
							<input type="text" class="form-control" name="cvv" maxlength="3" placeholder="CVV" required>
						</div>																		
					</form>				
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-info" onclick="cadastrarCartao()">Cadastrar</button>
				</div>
	
			</div>
		</div>
	</div>
		
	<jsp:include page="../componentes/modal.jsp"></jsp:include>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>