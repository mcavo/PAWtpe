<%@ include file="header.jsp"%>

<div class="container">
	<form data-toggle="validator" role="form" novalidate="true"
		action="registerRestaurant" method="post">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Información básica</h3>
			</div>
			<div class="panel-body">
				<div class="form-group has-error">
					<input type="text" class="form-control" id="inputName"
						placeholder="Nombre del restaurante" required="" name="name">
				</div>

				<div class="form-group has-error">
					<label for="inputStreet" class="control-label">Descripción</label>
					<textarea class="form-control" rows="5" id="description"
						placeholder="Descripción"></textarea>
				</div>

				<div class="form-group">
					<label for="sel1">Tipo de restaurant</label> <select
						class="form-control" id="sel1" requiered="">
						<option>Chino</option>
						<option>Japonés</option>
						<option>Coreano</option>
						<option>Mexicano</option>
						<option>Parrilla</option>
						<option>Tenedor Libre</option>
						<option>Árabe</option>
					</select>
				</div>

			</div>
		</div>
		<div class="panel panel-default">
			<div class="form-group">
				<div class="panel-heading">
					<h3 class="panel-title">Localización</h3>
				</div>
				<label for="inputStreet" class="control-label">Calle</label>
				<div class="form-group has-error row">
					<div class="col-sm-6">
						<input type="text" class="form-control" id="inputName"
							placeholder="Calle" required="" name="street">
					</div>
					<div class="form-group col-sm-3 has-error">
						<input type="password" class="form-control"
							id="inputPasswordConfirm" data-match="#inputPassword"
							placeholder="Nº" required="">
					</div>
					<div class="form-group col-sm-2 has-error">
						<input type="password" class="form-control"
							id="inputPasswordConfirm" placeholder="Piso" required="">
					</div>
					<div class="form-group col-sm-1 has-error">
						<input type="password" class="form-control"
							id="inputPasswordConfirm" placeholder="Depto" required="">
					</div>

				</div>
			</div>
		</div>
</div>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">Horario</h3>
	</div>
	<div class="form-group">
		<label for="inputBirth" class="control-label">Horario</label>
		<div class="form-inline row">
			<div class="form-group col-md-6 has-error">
				<input type="number" data-toggle="validator" min="1" max="31"
					class="form-control" id="inputBirth" placeholder="DD" required=""
					name="day"> <span class="help-block">Día de
					nacimiento</span>
			</div>
			<div class="form-group col-md-6 has-error">
				<input type="number" data-toggle="validator" class="form-control"
					id="inputmonth" placeholder="DD" required="" name="day"> <span
					class="help-block">Día de nacimiento</span>
			</div>
			<div class="form-group col-md-4 has-error">
				<input type="number" class="form-control" id="inputPasswordConfirm"
					min=1 max=12 data-match-error="Whoops, these don't match"
					placeholder="Confirm" required="">
				<div class="help-block with-errors">
					<ul class="list-unstyled">
						<li>Completa este campo</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="help-block with-errors"></div>
	</div>
</div>
<div class="form-group">
	<label for="inputPassword" class="control-label">Contraseña</label>
	<div class="form-inline row">
		<div class="form-group">
			<input type="password" data-toggle="validator" data-minlength="8"
				maxlength="16" class="form-control" id="inputPassword"
				placeholder="Password" required="" name="pwd"> <span
				class="help-block">Mínimo 6 caracteres</span>
		</div>
	</div>
</div>
<div class="form-group">
	<button type="submit" class="btn btn-primary disabled">Submit</button>
</div>
</form>
</div>

<%@ include file="footer.jsp"%>
