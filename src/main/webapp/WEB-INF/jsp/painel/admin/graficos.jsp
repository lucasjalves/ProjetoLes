<html>
<head>
<jsp:include page="../../statics.jsp"></jsp:include>
<style>
.hide {
	display: none;
}
</style>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0/dist/Chart.min.js"></script>
<script>
window.colors = {
		  "darkseagreen": "#8b008b",
		  "darkorange": "#e9967a",
		  "aqua": "#00ffff",
		  "aquamarine": "#7fffd4",
		  "orange": "#f0ffff",
		  "darkturquoise": "#ff69b4",
		  "firebrick": "#8fbc8f",
		  "black": "#000000",
		  "blanchedalmond": "#ffebcd",
		  "blue": "#0000ff",
		  "blueviolet": "#8a2be2",
		  "brown": "#a52a2a",
		  "burlywood": "#deb887",
		  "cadetblue": "#5f9ea0",
		  "chartreuse": "#7fff00",
		  "chocolate": "#d2691e",
		  "coral": "#ff7f50",
		  "cornflowerblue": "#6495ed",
		  "cornsilk": "#fff8dc",
		  "crimson": "#dc143c",
		  "cyan": "#00ffff",
		  "darkblue": "#00008b",
		  "darkcyan": "#008b8b",
		  "darkgoldenrod": "#b8860b",
		  "purple": "#a9a9a9",
		  "darkgreen": "#006400",
		  "red": "#a9a9a9",
		}
window.estados = ['AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA', 'MT', 'MS', 'MG', 'PA', 'PB', 'PR', 'PE', 'PI', 'RJ', 'RN', 'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO'];
window.mapaMeses = {
	1 : "Janeiro",
	2 : "Fevereiro",
	3 : "Março",
	4 : "Abril",
	5 : "Maio",
	6 : "Junho",
	7 : "Julho",
	8 : "Agosto",
	9 : "Setembro",
	10 : "Outubro",
	11 : "Novembro",
	12 : "Dezembro"
};

window.mesesGlobal = new Array("Janeiro","Fevereiro","Marco","Abril","Maio","Junho","Julho","Agosto","Septembro","Outubro","Novembro","Dezembro");
window.mapaEstadosPorMes = [];
window.pedidos = ${pedidos};

function gerarDados(pedidos, mesComeco, mesLimite, estados){
	var meses = gerarMapaMeses(estados);
	pedidos.forEach(function(pedido, index){
		var mes = pedido.dtPedido.split("/")[1];
		var mes = parseInt(mes);
		var estado = pedido.endereco.uf;
		var indice = meses.findIndex(function(el){
			return el.numero === mes;
		});
		
		var indiceEstado = meses[indice].estados.findIndex(function(el){
			return el.estado === estado;
		});
		
		if(indiceEstado != -1) {
			meses[indice].estados[indiceEstado].qtde = meses[indice].estados[indiceEstado].qtde + 1;
		}
	});
	var datasets = [];
	for(var i = 0; i < estados.length; i++){
		  dados = {
				  label: estados[i],
		          fill: false,
		          borderColor: Object.keys(window.colors)[i],
		          data: []
		  }
		  for(var j = mesComeco - 1; j <= mesLimite -1; j++){
			  dados.data.push(meses[j].estados[i].qtde);
		  }
		  datasets.push(dados);
	}
	
	return datasets;
}

function gerarMapaMeses(estados){
	var dados = [];
	for(var i = 1; i<=12; i++){
		var mes = {};
		mes.numero = i;
		mes.estados = [];
		for(var j = 0; j< estados.length; j++){
			mes.estados.push({
				estado: estados[j],
				qtde : 0
			});
		}
		dados.push(mes);
	}
	return dados;
}
function createChart(meses, ds){
	$('#myChart').remove();
	$('.chart-container').append('<canvas id="myChart" width="400" height="400"></canvas>');
	var ctx = document.getElementById('myChart').getContext('2d');
	window.myChart = new Chart(ctx, {
	    type: 'line',
	    data: {
	        labels: meses,
	        datasets: ds
	    },
	    options: {
	    	responsive: true,
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero: true
	                }
	            }]
	        }
	    }
	});
}

function filtrarTabela(){
	var inicio = $("#inputDe").val().replace(/\D/g, "");
	var fim = $("#inputAte").val().replace(/\D/g, "");
	var ano = $("#inputAno").val().replace(/\D/g, "");
	
	if(fim.length === 0){
		fim = 12;
	}
	
	if(inicio.length === 0){
		inicio = 1;
	}
	
	if(ano.length === 0){
		ano = new Date().getFullYear();
	}
	inicio = parseInt(inicio);
	fim = parseInt(fim);
	ano = parseInt(ano);
	var pedidos = $.grep(window.pedidos, (pedido, index) => {
		var mes = pedido.dtPedido.split("/")[1];
		var mes = parseInt(mes);
		var anoPedido = pedido.dtPedido.split("/")[2];
		if(mes >= inicio && mes <= fim && anoPedido == ano){
			return true;
		}
	});
	
	var meses =  window.mesesGlobal.filter((mes, index) => {
		var i = index + 1;
		return (i >= inicio && i<= fim);
	});
	
	var dados = null;
	if($("#row-estados-check input:checked").length === 0) {
		dados = gerarDados(pedidos, inicio, fim, window.estados);
	} else {
		var array = [];
		$("#row-estados-check input:checked").each(function() {
			array.push($(this).attr("estado"));
		});
		dados = gerarDados(pedidos, inicio, fim, array);
		
	}
	window.myChart.clear();
	window.myChart.destroy();
	createChart(meses, dados);
}
$(document).ready(function(){
	createChart(window.mesesGlobal, gerarDados(window.pedidos, 1, 12,window.estados));
	var chavesMeses = Object.keys(window.mapaMeses);
	chavesMeses.forEach((chave, index) => {
		$("#inputDe").append("<option value="+chave+">"+mapaMeses[chave]+"</option>");
		$("#inputAte").append("<option value="+chave+">"+mapaMeses[chave]+"</option>");
	});
	
	for(var i = new Date().getFullYear(); i>= 1900; i--){
		$("#inputAno").append("<option value="+i+">"+i+"</option>");
	}
	
	for(var i = 0; i< estados.length; i++) {
		var div = "<div class='form-group col-2'><div class='form-check form-check-inline'><label class='form-check-label'  style='margin-right: 25px;'>"+estados[i]+"</label>" +
		"<input type='checkbox' class='form-check-input' estado='"+estados[i]+"'/>" +
		"</div></div>";
		
		$("#row-estados-check").append(div);
	}

});


</script>
</head>
<body class="container">

<div id="clone" class="hide">
  	<div class="col-2 form-check">
	    <input type="checkbox" class="form-check-input" value="AM" id="exampleCheck1">
	    <label class="form-check-label" for="exampleCheck1">AM</label>		  		
  	</div>
</div>
<div id="accordion" style="margin-bottom: 20px;">
  <div class="card">
    <div class="card-header" id="headingOne">
      <h5 class="mb-0">
        <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          Filtros
        </button>
      </h5>
    </div>

    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
      <div class="card-body">
      
		<form>
		  <div class="form-row" >
		  	<div class="col-4">
		      <label for="inputState">De</label>
		      <select id="inputDe" class="form-control">
		        <option selected>Selecione...</option>
		      </select>
		  	</div>
		  	<div class="col-4" style="margin-bottom: 20px;">
		      <label for="inputState">Até</label>
		      <select id="inputAte" class="form-control">
		        <option selected>Selecione...</option>
		      </select>			  	
		  	</div>
		  	<div class="col-4">
		      <label for="inputState">Ano</label>
		      <select id="inputAno" class="form-control">
		        <option selected>Selecione...</option>
		      </select>		  		
		  	</div>
		  </div>
		  	<div class="form-row">
		  		<div class="col-12">
		  			<button type="button" class="btn btn-success" style="float:right;" onclick="filtrarTabela()">Filtrar</button>
		  		</div>
		  	</div>
		  	<div class="form-row" id="row-estados-check" style="margin-top: 30px;"></div>
		</form>      
      </div>
    </div>
  </div>
</div>

<div class="chart-container" style="height: 700px;">
	<canvas id="myChart" width="400" height="400"></canvas>
</div>


</body>


</html>
