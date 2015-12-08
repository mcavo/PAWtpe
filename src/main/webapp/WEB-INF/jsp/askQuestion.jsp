<%@ include file="header.jsp"%>

	<form role="form" action="ask" method="post">
		<input type="hidden" name="userId" value="${userId}">
		<input type="hidden" name="question" value="${question.id}">
		<br>
		<div class="form-group row">
			<div class="col-sm-offset-4 col-sm-4">
				<div class="col-sm-offset-1 col-sm-10 focus">
					Pregunta: ${question.question}
				</div>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-sm-offset-4 col-sm-4"> 
				<div class="col-sm-offset-1 col-sm-10">
					<input type="text" name="respuesta" class="form-control" id="respuesta" placeholder="Respuesta" autofocus>
				</div>
			</div>
		</div>
		<div class="form-group col-md-6">
							<label for="inputPassword" class="control-label col-sm-2 ">Nueva contraseña</label>
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
		<div class="col-sm-offset-4 col-sm-4">
			<div class="col-sm-offset-2 col-sm-8">
				<button class="btn btn-lg btn-primary btn-block" type="submit">Enviar</button>
			</div>
		</div>
	</form>
</div>

<%@ include file="footer.jsp"%>