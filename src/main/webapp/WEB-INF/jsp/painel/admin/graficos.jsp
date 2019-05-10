<html>
<head>
<jsp:include page="../../statics.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0/dist/Chart.min.js"></script>
<script>
var estados = ['AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA', 'MT', 'MS', 'MG', 'PA', 'PB', 'PR', 'PE', 'PI', 'RJ', 'RN', 'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO']
var pedidos = ${pedidos};
var dados = [];
function filtrar(){
	dados = [];
	for(var i = 0; i < estados.length; i++){
		var qtde = $.grep(pedidos, function(pedido, index){
			return pedido.endereco.uf === estados[i];
		}).length;
		dados.push(qtde);
	}
	createChart();
}
function createChart(){
	var ctx = document.getElementById('myChart').getContext('2d');
	var myChart = new Chart(ctx, {
	    type: 'line',
	    data: {
	        labels: estados,
	        datasets: [{
	            label: '# de vendas por estado',
	            data: dados,
	            borderColor: 'rgba(0, 225, 0, 0.8)'
	        }]
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
	filtrar();
});
</script>
<body>

<div class="chart-container" style="height: 700px;">
	<canvas id="myChart" width="400" height="400"></canvas>
</div>


</body>
</head>
</html>