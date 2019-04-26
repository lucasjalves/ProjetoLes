<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" 
integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/esm/popper.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-maskmoney/3.0.2/jquery.maskMoney.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
<link href="http://localhost:8888/css/styles.css" rel="stylesheet">
<link href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css" rel="stylesheet">
<script>
function setCookie(cname, cvalue, exdays) {
	  var d = new Date();
	  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	  var expires = "expires="+d.toUTCString();
	  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
	}

	function getCookie(cname) {
	  var name = cname + "=";
	  var ca = document.cookie.split(';');
	  for(var i = 0; i < ca.length; i++) {
	    var c = ca[i];
	    while (c.charAt(0) == ' ') {
	      c = c.substring(1);
	    }
	    if (c.indexOf(name) == 0) {
	      return c.substring(name.length, c.length);
	    }
	  }
	  return "";
	}
	$(document).ready(function(){
		usuarioLogado();
	});
	
	function usuarioLogado(){
		$.ajax({
			method: 'POST',
			url: 'http://localhost:8888/cliente/logado',
			success: function(data){
				if(data == false){
					$(".admin").each(function(){
						$(this).remove();
					});
				}
				if(data == ""){
					$(".logado").each(function(){
						$(this).remove();
					});
				}
			},
			error: function(data){
				
			}
		});
	}
</script>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


<nav class="navbar navbar-expand-lg fixed-top navbar navbar-dark" style="background: #3d70b2 !important;">
  <a class="navbar-brand" style="background: #3d70b2 !important;" href="#">Les 2019</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="http://localhost:8888">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item dropdown admin">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Produto
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="http://localhost:8888/produto/cadastro">Cadastrar</a>
          <a class="dropdown-item" href="http://localhost:8888/produto/consulta">Consultar</a>
          <div class="dropdown-divider" style="display:none;"></div>
          <a class="dropdown-item" style="display:none;" href="#">Something else here</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Cliente
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="http://localhost:8888/cliente/cadastro">Cadastrar</a>
          <a class="dropdown-item admin" href="http://localhost:8888/cliente/consulta">Consultar</a>
          <div class="dropdown-divider" style="display:none;"></div>
          <a class="dropdown-item" style="display:none;" href="#">Something else here</a>
        </div>
      </li>
      <li class="nav-item dropdown admin">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Cupom
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="http://localhost:8888/cupom/cadastro">Cadastrar</a>
          <a class="dropdown-item" href="http://localhost:8888/cupom/consulta">Consultar</a>
          <div class="dropdown-divider" style="display:none;"></div>
          <a class="dropdown-item" style="display:none;" href="#">Something else here</a>
        </div>
      </li>
		<li class="nav-item"><a class="nav-link"
			href="http://localhost:8888/carrinho">Carrinho <span class="sr-only">(current)</span></a>
		</li>
		<li class="nav-item logado"><a class="nav-link"
			href="http://localhost:8888/painel">Minha conta <span class="sr-only">(current)</span></a>
		</li>
		<li class="nav-item"><a class="nav-link"
			href="http://localhost:8888/cliente/login">Login <span class="sr-only">(current)</span></a>
		</li>				
		</ul>
  </div>
</nav>
