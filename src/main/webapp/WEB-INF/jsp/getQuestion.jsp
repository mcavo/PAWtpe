<%@ include file="header.jsp"%>

<div class="container">
	<h3 class="text-center">Por favor, ingrese sus datos</h3>
	<form role="form" action="getq" method="post">
		<br>
		<div class="form-group row">
			<div class="col-sm-offset-4 col-sm-4">
				<div class="col-sm-offset-1 col-sm-10 focus">
					<input type="email" name="email" class="form-control" id="email"
						placeholder="Email" autofocus>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group has-feedback">
				<label for="inputName"
					class="control-label col-sm-1 col-sm-offset-1">Nombre:</label>
				<div class="col-sm-8 col-sm-offset-1">
					<input type="text" class="form-control" id="inputName"
						placeholder="Nombre" name="firstname" required>
				</div>
				<div class="help-block with-errors"></div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group has-feedback">
				<label for="inputLastName" class="control-label col-sm-1 col-sm-1">Apellido:</label>
				<div class="col-sm-8 col-sm-offset-1">
					<input type="text" class="form-control" id="inputLastName"
						placeholder="Apellido" name="lastname" required>
				</div>
				<div class="help-block with-errors"></div>
			</div>
		</div>
		<div class="col-sm-offset-4 col-sm-4">
			<div class="col-sm-offset-2 col-sm-8">
				<button class="btn btn-lg btn-primary btn-block" type="submit">Siguiente</button>
			</div>
		</div>
	</form>
</div>

<%@ include file="footer.jsp"%>