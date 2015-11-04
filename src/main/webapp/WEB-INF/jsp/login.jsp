<%@ include file="header.jsp" %>

<div class="container">
	<h3 class="text-center">Por favor, ingrese sus datos</h3>
	<form role="form" action="" method="post">
		<br>
		<div class="form-group row">
			<div class="col-sm-offset-4 col-sm-4">
				<div class="col-sm-offset-1 col-sm-10 focus">
					<input type="email" name="email" class="form-control" id="email" placeholder="Email" autofocus>
				</div>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-sm-offset-4 col-sm-4"> 
				<div class="col-sm-offset-1 col-sm-10">
					<input type="password" name="pwd" class="form-control" id="pwd" placeholder="Contraseņa">
				</div>
			</div>
		</div>
		<div class="col-sm-offset-4 col-sm-4">
			<div class="col-sm-offset-2 col-sm-8">
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
			</div>
		</div>
	</form>
	<div class="col-sm-offset-4 col-sm-4">
			<div class="col-sm-offset-2 col-sm-8">
				<a href="/PAWTPE/bin/user/getq">Olvido su contraseņa?</a>
			</div>
	</div>
</div>

<%@ include file="footer.jsp" %>