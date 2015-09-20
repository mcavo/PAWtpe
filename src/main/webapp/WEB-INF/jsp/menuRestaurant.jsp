<%@ include file="header.jsp"%>
<div class="container">


	<div class="row">
		<div>
			<H3>${rest.name}</H3>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span> <span
				class="glyphicon glyphicon-star" aria-hidden="true"></span> <span
				class="glyphicon glyphicon-star" aria-hidden="true"></span> <span
				class="glyphicon glyphicon-star" aria-hidden="true"></span> <span
				class="glyphicon glyphicon-star" aria-hidden="true"></span>
		</div>
		<div class="pull-right">
			<H4>Puntaje: ${rest.score}</H4>
		</div>
	</div>

	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<div class="col-md-8">
				<div class="col-md-12">
					<ul class="nav nav-pills nav-justified">
						<li class="active"><a href="#">Menú</a></li>
						<li><a
							href="/PAWTPE/showRestaurant?name=${rest.name}&srt=${rest.address.street}&numb=${rest.address.number}&neigh=${rest.address.neighborhood}&city=${rest.address.city}&prov=${rest.address.province}&flr=${rest.address.floor}&apt=${rest.address.apartment}">Información</a></li>
					</ul>
				</div>

				<div class="col-md-12 sections">
					<form>
						<c:forEach items="${rest.menu.sections}" var="sect">
							<h4>${sect.name}</h4>
							<c:forEach items="${sect.dishes}" var="dish">
								<div class="bs-callout bs-callout-info">
									<div class="form-group">
										<h4>${dish.product}</h4>
										<div class="row">
											<div class="col-xs-9">
												<p>${dish.description}</p>
											</div>
											<div class="col-xs-3 line-vert-left">
												<div class="col-xs-12">$ ${dish.price}</div>
												<br> <br>
												<div class="col-xs-12">
													<select id="${dish.price}" name="${dish.product}" form="order">
														<option class="selected" value="0">0</option>
														<option value="1">1</option>
														<option value="2">2</option>
														<option value="3">3</option>
														<option value="4">4</option>
														<option value="5">5</option>
														<option value="6">6</option>
														<option value="7">7</option>
														<option value="8">8</option>
														<option value="9">9</option>
														<option value="10">10</option>
													</select>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:forEach>
					</form>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						Mi Pedido <span class="glyphicon glyphicon-shopping-cart"></span>
					</div>
					<div class="panel-body">
					
					<form action="#" method="POST" id="order">
						<input type="submit" class="btn btn-info" value="Confirmar pedido">
					</form>
					</div>
				</div>
			</div>
		</div>

	</div>

</div>

<%@ include file="footer.jsp"%>
