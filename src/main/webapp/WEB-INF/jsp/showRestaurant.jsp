<%@ include file="header.jsp"%>

<div class="container">
	<div class="col-md-10 col-md-offset-1">
		<div class="row">
			<div>
				<H3>${rest.nombre}</H3>
				<c:if test="${newOrderId}">
					<p>
						Su numero de pedido es: ${orderId}
					</p>
				</c:if>
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
					<li ><a href="/bin/restaurant/menu?code=${rest.id}">Menú</a></li>
					<li class="active"><a
						href="#">Información</a></li>
				</ul>
			</div>
		</div>
		<br> <br>

		${rest.descripcion}

		<!-- INFORMACION DEL RESTAURANTE -->

		<c:if test="${okToQualify}">
			<div class="row" style="margin-top: 40px;">
				<div class="col-md-6">
					<div class="well well-sm">
						<div class="text-right">
							<a class="btn btn-success btn-green" href="#reviews-anchor"
								id="open-review-box">Comentar</a>
						</div>

						<div class="row" id="post-review-box" style="display: none;">
							<div class="col-md-12">
								<form accept-charset="UTF-8" action="" method="post">
									<input id="ratings-hidden" name="rating" type="hidden">
									<textarea class="form-control animated" cols="50"
										id="new-review" name="comment"
										placeholder="Ingrese su opinión aquí..." rows="5"></textarea>

									<div class="text-right">
										<div class="stars starrr" data-rating="0"></div>
										<a class="btn btn-danger btn-sm" href="#"
											id="close-review-box"
											style="display: none; margin-right: 10px;"> Cancelar </a>
										<button class="btn btn-success" type="submit">Enviar</button>
									</div>
								</form>
							</div>
						</div>
					</div>

				</div>
			</div>
		</c:if>

		<!-- COMENTARIOS -->

	</div>
</div>
<%@ include file="footer.jsp"%>