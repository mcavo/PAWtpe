<%@ include file="header.jsp"%>

<div class="container">
	<h3 class="text-center">Ingrese el identificador de pedido:</h3>
	<form role="form" action="status" method="post">
		<br>
		<div class="form-group row">
			<div class="col-sm-offset-4 col-sm-4">
				<div class="col-sm-offset-1 col-sm-10 focus">
					<input type="text" name="orderID">
				</div>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-sm-offset-4 col-sm-4"> 
				<div class="col-sm-offset-1 col-sm-10">
					<select name="estado">
						<option value="1">Entregado</option>
						<option value="2">Cancelado</option>
					</select>
				</div>
			</div>
		</div>
		<div class="col-sm-offset-4 col-sm-4">
			<div class="col-sm-offset-2 col-sm-8">
				<button class="btn btn-lg btn-primary btn-block" type="submit">Actualizar</button>
			</div>
		</div>
	</form>
</div>

<%@ include file="footer.jsp"%>