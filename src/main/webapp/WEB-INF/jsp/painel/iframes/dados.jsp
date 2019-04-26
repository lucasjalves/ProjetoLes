<html>
<head>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/esm/popper.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
<link href="http://localhost:8888/css/styles.css" rel="stylesheet">
</head>
<body>
	<form id="form">
		<input type="hidden" name="id" value="" />
		<div class="form-group">
			<label>Nome Completo</label> <input type="text" class="form-control"
				name="nome" placeholder="Nome" value="Nome teste" required>
		</div>
		<div class="form-group">
			<label>CPF/CNPJ</label> <input type="text" class="form-control"
				name="cpfCnpj" placeholder="CPF/CNPJ" value="111.222.333-44"
				disabled>
		</div>
		<div class="form-group">
			<label>Data nascimento</label> <input type="text"
				class="form-control" name="dtNascimento" placeholder="DD/MM/YYYY"
				value="23/07/1997" required>
		</div>
		<div class="form-group">
			<label>E-mail</label> <input type="text" class="form-control"
				placeholder="E-mail" value="teste@teste.com.br" required>
		</div>
		<div class="form-group">
			<label>Gênero</label> <select class="form-control">
				<option selected>M</option>
				<option>F</option>
			</select>
		</div>
		<div class="form-group">
			<label>Username</label> <input type="text" class="form-control"
				name="username" placeholder="Username" value="usertest" required>
		</div>
		<div class="form-group">
			<label>Senha</label> <input type="password" class="form-control"
				name="senha" placeholder="Senha" value="senhatest" required>
		</div>
	</form>
	<button class="btn btn-primary" id="btnCadastrar">Alterar</button>

</body>
</html>