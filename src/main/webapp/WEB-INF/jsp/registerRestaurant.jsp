<%@ include file="header.jsp"%>

<div class="container">
	<form data-toggle="validator" role="form" novalidate
		action="registerrestaurant" method="post">
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Información básica</h3>
					</div>
					<div class="panel-body">
						<div class="form-group">
							<input type="text" class="form-control" id="inputName"
								placeholder="Nombre del restaurante" name="name" required>
						</div>

						<div class="form-group">
							<label for="inputStreet" class="control-label">Descripción</label>
							<textarea class="form-control" rows="4" maxlength="500"
								id="description" placeholder="Descripción"></textarea>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Tipos de comida</h3>
					</div>
					<div class="panel-body">
						<!-- Multiple Checkboxes -->
						<div class="col-md-6 checkbox">
							<div class="checkbox form-group">
								<label for="checkboxes-0"> <input type="checkbox"
									name="checkboxes" id="checkboxes-0" value="arabe">
									Árabe
								</label>
							</div>
							<div class="checkbox form-group">
								<label for="checkboxes-1"> <input type="checkbox"
									name="checkboxes" id="checkboxes-1" value="argentina">
									Argentina
								</label>
							</div>
							<div class="checkbox form-group">
								<label for="checkboxes-2"> <input type="checkbox"
									name="checkboxes" id="checkboxes-2" value="armenia">
									Armenia
								</label>
							</div>
							<div class="checkbox form-group">
								<label for="checkboxes-3"> <input type="checkbox"
									name="checkboxes" id="checkboxes-3" value="autor">
									Autor
								</label>
							</div>
							<div class="checkbox form-group">
								<label for="checkboxes-4"> <input type="checkbox"
									name="checkboxes" id="checkboxes-4" value="china">
									China
								</label>
							</div>
							<div class="checkbox form-group">
								<label for="checkboxes-5"> <input type="checkbox"
									name="checkboxes" id="checkboxes-5" value="deli"> Deli
								</label>
							</div>
							<div class="checkbox form-group">
								<label for="checkboxes-6 "> <input type="checkbox"
									name="checkboxes" id="checkboxes-6" value="italiana">
									Italiana
								</label>
							</div>
						</div>
						<div class="col-md-6 checkbox">
							<div class="checkbox form-group">
								<label for="checkboxes-7"> <input type="checkbox"
									name="checkboxes" id="checkboxes-7" value="japonesa">
									Japonesa
								</label>
							</div>
							<div class="checkbox form-group">
								<label for="checkboxes-8"> <input type="checkbox"
									name="checkboxes" id="checkboxes-8" value="mexicana">
									Mexicana
								</label>
							</div>
							<div class="checkbox form-group">
								<label for="checkboxes-9"> <input type="checkbox"
									name="checkboxes" id="checkboxes-9" value="norteamericana">
									Norteamericana
								</label>
							</div>
							<div class="checkbox form-group">
								<label for="checkboxes-10"> <input type="checkbox"
									name="checkboxes" id="checkboxes-10" value="parrilla">
									Parrilla
								</label>
							</div>
							<div class="checkbox form-group">
								<label for="checkboxes-11"> <input type="checkbox"
									name="checkboxes" id="checkboxes-11" value="peruana">
									Peruana
								</label>
							</div>
							<div class="checkbox form-group">
								<label for="checkboxes-12"> <input type="checkbox"
									name="checkboxes" id="checkboxes-12" value="vegetariana">
									Vegetariana
								</label>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Dirección y horario</h3>
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
								<input type="number" min=0 class="form-control" id="inputNumber"
									placeholder="Altura" name="number" required>
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
				<br>
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
				<br>
				<div class="row">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Horario de atención</h3>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label for="inputFrom" class="control-label col-md-4">Hora
							de apertura:</label>
						<div class="form-group col-md-8">
							<input type="time" class="form-control" id="inputFrom"
								placeholder="DD" required name="from">
						</div>
					</div>
					<div class="form-group col-md-6">
						<label for="inputTo" class="control-label col-md-4">Hora
							de cierre:</label>
						<div class="form-group col-md-8">
							<input type="time" class="form-control" id="inputTo"
								placeholder="DD" required name="to">
						</div>
					</div>
					<div class="help-block with-errors"></div>
				</div>
				<div class="row">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Información de compra</h3>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label for="inputFrom" class="control-label col-md-4">Monto
							mínimo:</label>
						<div class="input-group form-group col-md-6 col-md-offset-1">
							<span class="input-group-addon">$</span> <input type="number" min = 0
								class="form-control" aria-label="Amount (to the nearest dollar)" name="minimum" required>
							<span class="input-group-addon">.00</span>
						</div>
					</div>
					<div class="form-group form-group col-md-6">
						<label for="inputTo" class="control-label col-md-4">Costo
							de envío: </label>
						<div class="input-group form-group col-md-6 col-md-offset-1">
							<span class="input-group-addon">$</span> <input type="number" min = 0
								class="form-control" aria-label="Amount (to the nearest dollar)" name="cost" required>
							<span class="input-group-addon">.00</span>
						</div>
					</div>
					<div class="help-block with-errors"></div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-primary disabled">Confirmar</button>
		</div>
	</form>
</div>

<%@ include file="footer.jsp"%>
