<%@ include file="header.jsp"%>

<div class="container">
	<div class="col-md-10 col-md-offset-1">
		<form action="signup" data-toggle="validator" role="form"
			method="post" class="form-horizontal">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Información básica</h3>
				</div>
				<div class="panel-body">
					<div class="row">
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
								<label for="inputLastName"
									class="control-label col-sm-1 col-sm-1">Apellido:</label>
								<div class="col-sm-8 col-sm-offset-1">
									<input type="text" class="form-control" id="inputLastName"
										placeholder="Apellido" name="lastname" required>
								</div>
								<div class="help-block with-errors"></div>
							</div>
						</div>
					</div>
					<div class="row">
						<label for="day" class="control-label col-sm-3">Fecha de
							nacimiento:</label>
						<div class="form-group has-feedback col-sm-3">
							<div class="col-sm-10 col-sm-offset-1">
								<div class="form-group">
									<input type="number" min="1" max="31" class="form-control"
										id="day" placeholder="DD" name="day" required>
								</div>
							</div>
						</div>
						<div class="form-group has-feedback col-sm-3">
							<div class="col-sm-10 col-sm-offset-1">
								<div class="form-group">
									<input type="number" class="form-control" id="month" min=1
										max=12 placeholder="MM" name="month" required>
								</div>
							</div>
						</div>
						<div class="form-group has-feedback col-sm-3">
							<div class="col-sm-10 col-sm-offset-1">
								<div class="form-group">
									<input type="number" class="form-control" id="year"
										placeholder="YYYY" name="year" required>
								</div>
							</div>
							<div class="help-block with-errors"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Autenticación</h3>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="form-group">
							<label for="inputEmail"
								class="control-label col-sm-1 col-sm-offset-1">Email:</label>
							<div class="col-sm-8 col-sm-offset-1">
								<input type="email" class="form-control" id="inputEmail"
									maxlength=40 placeholder="Email"
									data-error="El email no es válido" name="email" required>
							</div>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-6">
							<label for="inputPassword" class="control-label col-sm-2 ">Contraseña</label>
							<div class="col-sm-8 col-sm-offset-1">
								<input type="password" data-toggle="validator"
									data-minlength="8" maxlength="16" class="form-control"
									id="inputPassword" placeholder="Contraseña" name="pwd" required>
								<span class="help-block">Mínimo 8 caracteres</span>
							</div>
						</div>
						<div class="form-group col-md-6">
							<label for="inputPassword" class="control-label col-sm-2">Confirmación</label>
							<div class="col-sm-8 col-sm-offset-1">
								<input type="password" class="form-control"
									id="inputPasswordConfirm" data-match="#inputPassword"
									data-match-error="Verificación incorrecta"
									placeholder="Confirmación" required>
								<div class="help-block with-errors"></div>
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
					<div class="row">
						<div class="col-md-4">
							<div class="form-group has-feedback">
								<label for="inputStreet" class="control-label col-sm-2 ">Calle:</label>
								<div class="col-sm-10">
									<input type="text" maxlength="30" class="form-control"
										id="inputStreet" placeholder="Calle" name="street" required>
								</div>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group has-feedback">
								<label for="inputNumber" class="control-label col-sm-4">Altura:</label>
								<div class="col-sm-8">
									<input type="number" min=0 class="form-control"
										id="inputNumber" placeholder="Altura" name="number" required>
								</div>
							</div>
						</div>
						<div class="col-md-2">
							<div class="form-group has-feedback">
								<label for="inputFloor" class="control-label col-sm-3">Piso:</label>
								<div class="col-sm-9">
									<input type="number" min=0 class="form-control" id="inputFloor"
										placeholder="Piso" name="floor">
								</div>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group has-feedback">
								<label for="inputApartment" class="control-label col-sm-3">Depto:</label>
								<div class="col-sm-9">
									<input type="text" maxlength=1 class="form-control"
										id="inputApartment" placeholder="Depto" name="apartment">
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<div class="form-group has-feedback">
								<label for="neighbohood" class="control-label col-sm-4">Barrio:</label>
								<div class="col-sm-8">
									<input type="text" maxlength=40 class="form-control"
										id="neighborhood" placeholder="Barrio" name="neighborhood"
										required>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group has-feedback">
								<label for="inputProvince" class="control-label col-sm-4">Provincia</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" maxlength=30
										id="inputProvince" placeholder="Provincia" required
										name="province">
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group has-feedback">
								<label for="inputCity" class="control-label col-sm-4">Localidad:</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" maxlength=30
										id="inputCity" placeholder="Localidad" name="city" required>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group col-md-4 col-md-offset-4">
				<button type="submit" class="btn btn-primary disabled">Submit</button>
			</div>
		</form>
	</div>
</div>

<%@ include file="footer.jsp"%>
