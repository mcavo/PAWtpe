<%@ include file="header.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="container">
	<c:if test="${message != null}">
		<div class="container">
			<c:if test="${error}">
				<div class="alert alert-danger" role="alert">${message}</div>
			</c:if>
			<c:if test="${success}">
				<div class="alert alert-success" role="alert">${message}</div>
			</c:if>
			<c:if test="${warning}">
				<div class="alert alert-warning" role="alert">${message}</div>
			</c:if>
			<c:if test="${info}">
				<div class="alert alert-info" role="alert">${message}</div>
			</c:if>
		</div>
	</c:if>
	<div class="col-md-10 col-md-offset-1">
		<form:form action="update" data-toggle="validator" role="form"
			commandName="editProfileForm" method="post" class="form-horizontal">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Información básica</h3>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group has-feedback">
								<form:label path="firstname" for="inputName"
									class="control-label col-sm-1 col-sm-offset-1">Nombre:</form:label>
								<div class="col-sm-8 col-sm-offset-1">
									<form:input path="firstname" type="text" class="form-control"
										id="inputName" placeholder="Nombre" name="firstname"
										value="${user.firstName}"></form:input>
								</div>
								<div class="help-block with-errors"></div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group has-feedback">
								<form:label path="lastname" for="inputLastName"
									class="control-label col-sm-1 col-sm-1">Apellido:</form:label>
								<div class="col-sm-8 col-sm-offset-1">
									<form:input path="lastname" type="text" class="form-control"
										id="inputLastName" placeholder="Apellido" name="lastname"
										value="${user.lastName}"></form:input>
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
									<form:input path="birthDay" type="number" min="1" max="31"
										class="form-control" id="day" placeholder="DD" name="day"
										value="${day}"></form:input>
								</div>
							</div>
						</div>
						<div class="form-group has-feedback col-sm-3">
							<div class="col-sm-10 col-sm-offset-1">
								<div class="form-group">
									<form:input path="birthMonth" type="number"
										class="form-control" id="month" min="1" max="12"
										placeholder="MM" name="month" value="${month}"></form:input>
								</div>
							</div>
						</div>
						<div class="form-group has-feedback col-sm-3">
							<div class="col-sm-10 col-sm-offset-1">
								<div class="form-group">
									<form:input path="birthYear" type="number" class="form-control"
										id="year" placeholder="YYYY" name="year" value="${year}"></form:input>
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
							<form:label path="email" for="inputEmail"
								class="control-label col-sm-1 col-sm-offset-1">Email:</form:label>
							<div class="col-sm-8 col-sm-offset-1">
								<form:input path="email" type="email" class="form-control"
									id="inputEmail" maxlength="40" placeholder="Email"
									data-error="El email no es válido" name="email"
									value="${user.email}"></form:input>
							</div>
							<div class="help-block with-errors"></div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-4 col-md-offset-4">
							<a href="#">Cambiar contraseña</a>
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
								<form:label path="street" for="inputStreet"
									class="control-label col-sm-2 ">Calle:</form:label>
								<div class="col-sm-10">
									<form:input path="street" type="text" maxlength="30"
										class="form-control" id="inputStreet" placeholder="Calle"
										name="street" value="${user.address.street}"></form:input>
								</div>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group has-feedback">
								<form:label path="number" for="inputNumber"
									class="control-label col-sm-4">Altura:</form:label>
								<div class="col-sm-8">
									<form:input path="number" type="number" min="0"
										class="form-control" id="inputNumber" placeholder="Altura"
										name="number" value="${user.address.number}"></form:input>
								</div>
							</div>
						</div>
						<div class="col-md-2">
							<div class="form-group has-feedback">
								<form:label path="floor" for="inputFloor"
									class="control-label col-sm-3">Piso:</form:label>
								<div class="col-sm-9">
									<form:input path="floor" type="number" min="0"
										class="form-control" id="inputFloor" placeholder="Piso"
										name="floor" value="${user.address.floor}"></form:input>
								</div>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group has-feedback">
								<form:label path="apartment" for="inputApartment"
									class="control-label col-sm-3">Depto:</form:label>
								<div class="col-sm-9">
									<form:input path="apartment" type="text" maxlength="1"
										class="form-control" id="inputApartment" placeholder="Depto"
										name="apartment" value="${user.address.apartment}"></form:input>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<div class="form-group has-feedback">
								<form:label path="neigh" for="neighbohood"
									class="control-label col-sm-4">Barrio:</form:label>
								<div class="col-sm-8">
									<form:select path="neigh" id="neighborhood">
										<form:options items="${neighList}" itemValue="id"
											itemLabel="name" selected="${user.address.neighborhood}" />
									</form:select>
								</div>

							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group has-feedback">
								<form:label path="prov" for="inputProvince"
									class="control-label col-sm-4">Provincia</form:label>
								<div class="col-sm-8">
									<form:input path="prov" type="text" class="form-control"
										maxlength="30" id="inputProvince" placeholder="Provincia"
										name="province" value="${user.address.province}"></form:input>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group has-feedback">
								<form:label path="city" for="inputCity"
									class="control-label col-sm-4">Localidad:</form:label>
								<div class="col-sm-8">
									<form:input path="city" type="text" class="form-control"
										maxlength="30" id="inputCity" placeholder="Localidad"
										name="city" value="${user.address.city}"></form:input>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Información de recuperación</h3>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group has-feedback">
								<form:label path="question" for="question"
									class="control-label col-sm-4">Pregunta:</form:label>
								<div class="col-sm-8">
									<form:select path="question" id="question">
											<form:options items="${questList}" itemValue="id"
											itemLabel="question" selected="${user.questionid}" />
									</form:select>
								</div>

							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group has-feedback">
								<label for="inputLastName"
									class="control-label col-sm-1 col-sm-1">Respuesta:</label>
								<div class="col-sm-8 col-sm-offset-1">
									<form:input path="answer" type="text" class="form-control"
										maxlength="100" id="inputAnswer" placeholder="Respuesta"
										name="answer" value="${user.answer}"></form:input>
								</div>
								<div class="help-block with-errors"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group col-md-4 col-md-offset-4">
				<form:errors path="*" />
				<button type="submit" class="btn btn-primary disabled">Confirmar</button>
			</div>
		</form:form>
	</div>
</div>

<%@ include file="footer.jsp"%>

