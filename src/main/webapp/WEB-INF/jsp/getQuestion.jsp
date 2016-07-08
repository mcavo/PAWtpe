<%@ include file="header.jsp"%>

	<h3 class="text-center">Por favor, ingrese sus datos</h3>
	<form role="form" action="getq" method="post">
		<br>
		<div class="form-group row">
			<label for="inputEmail" class="control-label col-sm-1 col-sm-offset-1">Email:</label>
			<div class="col-sm-offset-1 col-sm-8">
				<div class="col-sm-offset-1 col-sm-10 focus">
					<input type="email" name="email" class="form-control" id="email"
						placeholder="Email" autofocus>
				</div>
			</div>
		</div>
		<br>
		<div class="col-sm-offset-4 col-sm-4">
			<div class="col-sm-offset-2 col-sm-8">
				<button class="btn btn-lg btn-primary btn-block" type="submit">Siguiente</button>
			</div>
		</div>
	</form>

<%@ include file="footer.jsp"%>