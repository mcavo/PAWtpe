<%@ include file="header.jsp"%>
<div class="container">

	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<div class="row">
				<div>
					<H3>${rest.nombre}</H3>
					<h4>
						<p>
							<span class="label label-warning">${rest.score}</span>
							${rest.countComments} calificaciones
						</p>
						<br> <br>
					</h4>
				</div>
			</div>
			<div class="col-md-8">
				<div class="col-md-12">
					<ul class="nav nav-pills nav-justified">
						<li class="active"><a href="#">Menú</a></li>
						<li><a
							href="/PAWTPE/bin/restaurant/details?code=${rest.id}">Información</a></li>
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
												<div class="col-xs-12 price">$ ${dish.price}</div>
												<br> <br>
												<div class="col-xs-12">
													<select id="${dish.price}" class="select-dish"
														name="${dish.product}" form="order">
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
						<div class="cart">
							<div class="productContainer"></div>
							<div class="cartresume hide">
								<div class="subtotal">
									<br> <br>
									<div class="col-sm-8">Subtotal:</div>
									<div class='col-sm-4' id="subprice"></div>
								</div>
								<div class="costoEnvio">
									<div class="col-sm-8">Envío:</div>
									<div class='col-sm-4' id="sprice">$ ${rest.costoenvio}</div>
								</div>
								<div class="total">
									<br> <br>
									<div class="col-sm-8">
										<b>Total:</b>
									</div>
									<div class='col-sm-4' id="tprice">
										<br> <br>
									</div>
									<c:if test="${user != null}">
										<form action="sendOrder" method="POST" id="order">
											<input type="hidden"  name="restId" value="${rest.id}">
											<input type="submit" class="btn btn-info"
												value="Confirmar pedido">
										</form>
									</c:if>
								</div>
							</div>


						</div>
					</div>
				</div>
			</div>

		</div>

	</div>
</div>
<%@ include file="footer.jsp"%>