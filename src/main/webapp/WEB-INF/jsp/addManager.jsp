<%@ include file="header.jsp"%>

<div class="container">
	<div class="col-md-10 col-md-offset-1">
		<form action="addmanager" method="post">

			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Mail del manager</h3>
				</div>
				<div class="panel-body">
					<br>
					<div class="row">
						<div class="col-sm-8 col-sm-offset-2">
							<div class="form-group">
								<input type="email" class="form-control" id="inputEmail"
									maxlength=40 placeholder="Email"
									data-error="El email no es válido" name="email" required>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-2 col-md-offset-5">
						<button type="submit" class="btn btn-primary">Confirmar</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>

<%@ include file="footer.jsp"%>