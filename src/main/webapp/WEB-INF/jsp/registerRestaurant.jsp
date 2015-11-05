<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="managersHeader.jsp"%>

<div class="container">
	<form:form data-toggle="validator" role="form" action="register"
		commandName="registerRestForm" method="post">
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Información básica</h3>
					</div>
					<div class="panel-body">
						<div class="form-group">
							<form:input path="name" type="text" class="form-control"
								id="inputName" placeholder="Nombre del restaurante" name="name"
								required="required"></form:input>

						</div>

						<div class="form-group">
							<label for="description" class="control-label">Descripción</label>
							<form:textarea path="description" class="form-control" rows="4"
								maxlength="500" id="description" placeholder="Descripción"
								name="description"></form:textarea>
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
								<label for="checkboxes-0"> <form:input path="tfood"
										type="checkbox" name="checkboxes" id="checkboxes-0"
										value="arabe"></form:input> Árabe
								</label>
							</div>
							<div class="checkbox form-group">
								<label for="checkboxes-1"> <form:input path="tfood"
										type="checkbox" name="checkboxes" id="checkboxes-1"
										value="argentina"></form:input> Argentina
								</label>
							</div>
							<div class="checkbox form-group">
								<label for="checkboxes-2"> <form:input path="tfood"
										type="checkbox" name="checkboxes" id="checkboxes-2"
										value="armenia"></form:input> Armenia
								</label>
							</div>
							<div class="checkbox form-group">
								<label for="checkboxes-3"> <form:input path="tfood"
										type="checkbox" name="checkboxes" id="checkboxes-3"
										value="autor"></form:input> Autor
								</label>
							</div>
							<div class="checkbox form-group">
								<label for="checkboxes-4"> <form:input path="tfood"
										type="checkbox" name="checkboxes" id="checkboxes-4"
										value="china"></form:input> China
								</label>
							</div>
							<div class="checkbox form-group">
								<label for="checkboxes-5"> <form:input path="tfood"
										type="checkbox" name="checkboxes" id="checkboxes-5"
										value="deli"></form:input> Deli
								</label>
							</div>
							<div class="checkbox form-group">
								<label for="checkboxes-6 "> <form:input path="tfood"
										type="checkbox" name="checkboxes" id="checkboxes-6"
										value="italiana"></form:input> Italiana
								</label>
							</div>
						</div>
						<div class="col-md-6 checkbox">
							<div class="checkbox form-group">
								<label for="checkboxes-7"> <form:input path="tfood"
										type="checkbox" name="checkboxes" id="checkboxes-7"
										value="japonesa"></form:input> Japonesa
								</label>
							</div>
							<div class="checkbox form-group">
								<label for="checkboxes-8"> <form:input path="tfood"
										type="checkbox" name="checkboxes" id="checkboxes-8"
										value="mexicana"></form:input> Mexicana
								</label>
							</div>
							<div class="checkbox form-group">
								<label for="checkboxes-9"> <form:input path="tfood"
										type="checkbox" name="checkboxes" id="checkboxes-9"
										value="norteamericana"></form:input> Norteamericana
								</label>
							</div>
							<div class="checkbox form-group">
								<label for="checkboxes-10"> <form:input path="tfood"
										type="checkbox" name="checkboxes" id="checkboxes-10"
										value="parrilla"></form:input> Parrilla
								</label>
							</div>
							<div class="checkbox form-group">
								<label for="checkboxes-11"> <form:input path="tfood"
										type="checkbox" name="checkboxes" id="checkboxes-11"
										value="peruana"></form:input> Peruana
								</label>
							</div>
							<div class="checkbox form-group">
								<label for="checkboxes-12"> <form:input path="tfood"
										type="checkbox" name="checkboxes" id="checkboxes-12"
										value="vegetariana"></form:input> Vegetariana
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
								<form:input path="street" type="text" maxlength="30"
									class="form-control" id="inputStreet" placeholder="Calle"
									name="street" required="required"></form:input>
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group has-feedback">
							<label for="inputNumber" class="control-label col-sm-4">Altura:</label>
							<div class="col-sm-8">
								<form:input path="number" type="number" min="0"
									class="form-control" id="inputNumber" placeholder="Altura"
									name="number" required="required"></form:input>

							</div>
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group has-feedback">
							<label for="inputFloor" class="control-label col-sm-3">Piso:</label>
							<div class="col-sm-9">
								<form:input path="floor" type="number" min="0"
									class="form-control" id="inputFloor" placeholder="Piso"
									name="floor"></form:input>
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group has-feedback">
							<label for="inputApartment" class="control-label col-sm-3">Depto:</label>
							<div class="col-sm-9">
								<form:input path="apartment" type="text" maxlength="1"
									class="form-control" id="inputApartment" placeholder="Depto"
									name="apartment"></form:input>
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
								<form:select path="neigh" id="neighborhood">
									<form:options items="${neighList}" itemValue="id"
										itemLabel="name" />
								</form:select>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group has-feedback">
							<label for="inputCity" class="control-label col-sm-4">Localidad:</label>
							<div class="col-sm-8">
								<form:input path="city" type="text" class="form-control"
									maxlength="30" id="inputCity" placeholder="Localidad"
									name="city" required="required"></form:input>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group has-feedback">
							<label for="inputProvince" class="control-label col-sm-4">Provincia</label>
							<div class="col-sm-8">

								<form:input path="prov" type="text" class="form-control"
									maxlength="30" id="inputProvince" placeholder="Provincia"
									required="required" name="province"></form:input>
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
							<form:input path="from" type="time" class="form-control"
								id="inputFrom" placeholder="DD" required="required" name="from"></form:input>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label for="inputTo" class="control-label col-md-4">Hora
							de cierre:</label>
						<div class="form-group col-md-8">
							<form:input path="to" type="time" class="form-control"
								id="inputTo" placeholder="DD" required="required" name="to"></form:input>
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
							<span class="input-group-addon">$</span>
							<form:input path="minamount" type="number" min="0"
								class="form-control" aria-label="Amount (to the nearest dollar)"
								name="minimum" required="required"></form:input>
							<span class="input-group-addon">.00</span>
						</div>
					</div>
					<div class="help-block with-errors"></div>
				</div>
				<div class="row">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Información de delivery</h3>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label for="inputFrom" class="control-label col-md-4">Hora
							de apertura:</label>
						<div class="form-group col-md-8">
							<form:input path="deliveryfrom" type="time" class="form-control"
								id="deliveryinputFrom" placeholder="DD" required="required"
								name="from"></form:input>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label for="inputTo" class="control-label col-md-4">Hora
							de cierre:</label>
						<div class="form-group col-md-8">
							<form:input path="deliveryto" type="time" class="form-control"
								id="deliveryinputTo" placeholder="DD" required="required"
								name="to"></form:input>
						</div>
					</div>
					<div class="col-md-6 checkbox">
						<c:forEach items="${neighList}" var="neigh">
							<div class="checkbox form-group">
								<form:input path="delneigh" type="checkbox" name="checkboxes"
									id="neighdel-${neigh.id}" value="${neigh.id}"></form:input>
								${neigh.name}

							</div>
						</c:forEach>

					</div>
					<div class="form-group form-group col-md-6">
						<label for="inputTo" class="control-label col-md-4">Costo
							de envío: </label>
						<div class="input-group form-group col-md-6 col-md-offset-1">
							<span class="input-group-addon">$</span>
							<form:input path="delamount" type="number" min="0"
								class="form-control" aria-label="Amount (to the nearest dollar)"
								name="cost"></form:input>
							<span class="input-group-addon">.00</span>
						</div>
					</div>
					<div class="help-block with-errors"></div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<form:errors path="*" />
			<button type="submit" class="btn btn-primary disabled">Confirmar</button>
		</div>
	</form:form>
</div>

<%@ include file="footer.jsp"%>
