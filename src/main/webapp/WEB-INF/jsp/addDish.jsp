<%@ include file="header.jsp"%>

<div class="container">
	<div class="col-md-8 col-md-offset-2">
		<div>
			<h4>Agregar plato</h4>
		</div>
		<div>
			<form class="form-horizontal" action="addMenu" role="form"
				method="post">
				<div class="form-group">
					<label class="control-label col-sm-2" for="section">Sección:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" required name="section" id="section"
							placeholder="Ej: Entrada">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="dish">Plato:</label>
					<div class="col-sm-10">
						<input type="text-data" class="form-control" required name="dish" id="dish"
							placeholder="Ej: Pizza napolitana">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="price">Precio:</label>
					<div class="col-sm-10">
						<input type="number" min="0" required class="form-control" name="price" id="price"
							placeholder="Ej: 24.50">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="description">Descripción:</label>
					<div class="col-sm-10">
						<textarea class="form-control" rows="5" name="description" id="description" placeholder="Ingrese una descripción del plato."></textarea>
					</div>
				</div>
			</form>
		</div>
	</div>

</div>

