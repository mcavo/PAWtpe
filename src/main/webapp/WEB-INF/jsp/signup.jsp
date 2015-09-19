<%@ include file="header.jsp"%>

<div class="container">
	<form data-toggle="validator" role="form" novalidate="true"
		action="signup" method="post">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Información básica</h3>
			</div>
			<div class="panel-body">
				<div class="form-group has-error">
					<div class="row">
						<div class="col-sm-offset-1">
							<label for="inputName" class="control-label">Nombre</label>
						</div>
					</div>
					<div class="form-inline row">
						<div class="form-group  col-ms-6 has-error">
							<input type="text" class="form-control" id="inputName"
								placeholder="Nombre" required="" name="firstname">
							<div class="help-block with-errors">
								<ul class="list-unstyled">
									<li>Completa este campo</li>
								</ul>
							</div>
						</div>
						<div class="form-group  col-ms-6 has-error">
							<input type="text" class="form-control" id="inputLastName"
								placeholder="Apellido" required="" name="lastname">
							<div class="help-block with-errors">
								<ul class="list-unstyled">
									<li>Completa este campo</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Dirección</h3>
			</div>
			<div class="panel-body">
				<div class="form-group has-error container-fluid">
					<div class="row">
						<div class="col-md-1"></div>
						<div class="col-md-11">
							<label for="inputName" class="control-label">Nombre</label>
						</div>
					</div>
					<div class="form-inline row">
						<div class="col-md-1"></div>
						<div class="  col-md-5">
							<div class="form-group has-error">
								<input type="text" class="form-control" id="inputName"
									placeholder="Nombre" required="" name="firstname">
								<div class="help-block with-errors">
									<ul class="list-unstyled">
										<li>Completa este campo</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="col-md-5">
							<div class="form-group has-error">
								<input type="text" class="form-control" id="inputLastName"
									placeholder="Apellido" required="" name="lastname">
								<div class="help-block with-errors">
									<ul class="list-unstyled">
										<li>Completa este campo</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="col-ms-1"></div>
					</div>
				</div>
			</div>
		</div>

		<div class="form-group has-error">
			<label for="inputStreet" class="control-label">Numero</label> <input
				type="int" class="form-control" id="inputName"
				placeholder="Cina Saffary" name="number"> <label
				for="inputStreet" class="control-label">Piso</label> <input
				type="text" class="form-control" id="inputName"
				placeholder="Cina Saffary" name="floor"> <label
				for="inputStreet" class="control-label">departamento</label> <input
				type="text" class="form-control" id="inputName"
				placeholder="Cina Saffary" required="" name="apartment"> <label
				for="inputStreet" class="control-label">Ciudad</label> <input
				type="text" class="form-control" id="inputName"
				placeholder="Cina Saffary" required="" name="city"> <label
				for="inputStreet" class="control-label">Provincia</label> <input
				type="text" class="form-control" id="inputName"
				placeholder="Cina Saffary" required="" name="province"> <label
				for="inputStreet" class="control-label">Barrio</label> <input
				type="text" class="form-control" id="neighborhood"
				placeholder="barrio" required="" name="neighborhood">
		</div>
		<div class="form-group">
			<label for="inputEmail" class="control-label">Email</label> <input
				type="email" class="form-control" id="inputEmail" maxlength=40
				placeholder="Email" data-error="Bruh, that email address is invalid"
				required="" name="email">
			<div class="help-block with-errors"></div>
		</div>
		<div class="form-group">
			<label for="inputBirth" class="control-label">Birth</label>
			<div class="form-inline row">
				<div class="form-group col-md-4 has-error">
					<input type="number" data-toggle="validator" min="1" max="31"
						class="form-control" id="day" placeholder="DD" required=""
						name="day"> <span class="help-block">Día de
						nacimiento</span>
				</div>
				<div class="form-group col-md-4 has-error">
					<input type="number" class="form-control" id="month" min=1 max=12
						placeholder="Confirm" required="" name="month"> <span
						class="help-block">Año de nacimiento</span>
				</div>
				<div class="form-group col-md-4 has-error">
					<input type="number" data-toggle="validator" class="form-control"
						id="inputmonth" placeholder="YYYY" required="" name="year">
					<span class="help-block">Año de nacimiento</span>
					<div class="help-block with-errors">
						<ul class="list-unstyled">
							<li>Completa este campo</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="help-block with-errors"></div>
		</div>
		<div class="form-group">
			<label for="inputPassword" class="control-label">Contraseña</label>
			<div class="form-inline row">
				<div class="form-group col-sm-6">
					<input type="password" data-toggle="validator" data-minlength="8"
						maxlength="16" class="form-control" id="inputPassword"
						placeholder="Password" required="" name="pwd"> <span
						class="help-block">Mínimo 6 caracteres</span>
				</div>
				<div class="form-group col-sm-6 has-error">
					<input type="password" class="form-control"
						id="inputPasswordConfirm" data-match="#inputPassword"
						data-match-error="Whoops, these don't match" placeholder="Confirm"
						required="">
					<div class="help-block with-errors">
						<ul class="list-unstyled">
							<li>Completa este campo</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-primary disabled">Submit</button>
		</div>
	</form>
</div>

<%@ include file="footer.jsp"%>
