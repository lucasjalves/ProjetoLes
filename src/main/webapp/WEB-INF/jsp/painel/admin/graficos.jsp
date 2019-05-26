<html>
<head>
<jsp:include page="../../statics.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0/dist/Chart.min.js"></script>
<script>
var colors = {
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
var estados = ['AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA', 'MT', 'MS', 'MG', 'PA', 'PB', 'PR', 'PE', 'PI', 'RJ', 'RN', 'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO'];
var mapaMeses = {
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
var mesesGlobal = new Array("Janeiro","Fevereiro","Marco","Abril","Maio","Junho","Julho","Agosto","Septembro","Outubro","Novembro","Dezembro");
var mapaEstadosPorMes = [];
var pedidos = ${pedidos};

function gerarDados(pedidos, mesComeco, mesLimite){
	var meses = gerarMapaMeses();
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
		
		meses[indice].estados[indiceEstado].qtde = meses[indice].estados[indiceEstado].qtde + 1;
	});
	var datasets = [];
	for(var i = 0; i < estados.length; i++){
		  dados = {
				  label: estados[i],
		          fill: false,
		          borderColor: Object.keys(colors)[i],
		          data: []
		  }
		  for(var j = mesComeco - 1; j <= mesLimite -1; j++){
			  dados.data.push(meses[j].estados[i].qtde);
		  }
		  datasets.push(dados);
	}
	
	return datasets;
}

function gerarMapaMeses(){
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
function createChart(){
	var ctx = document.getElementById('myChart').getContext('2d');
	var myChart = new Chart(ctx, {
	    type: 'line',
	    data: {
	        labels: mesesGlobal,
	        datasets: gerarDados(pedidos,1,12)
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
$(document).ready(function(){
	createChart();
});
</script>
</head>
<body>

<div class="chart-container" style="height: 700px;">
	<canvas id="myChart" width="400" height="400"></canvas>
</div>


</body>


</html>
