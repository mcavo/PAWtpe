<%@ include file="header.jsp"%>

<div class="container">
	<div class="row">
		<div>
			<H3>${rest.name}</H3>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
		</div>
		<div class="pull-right">
			<H4>Puntaje: ${rest.score}</H4>
		</div>
	</div>

	<div>
		<ul class="nav nav-tabs nav-justified">
			<li><a href="/PAWTPE/showRestaurant?name=${rest.name}&srt=${rest.address.street}&numb=${rest.address.number}&neigh=${rest.address.neighborhood}&city=${rest.address.city}&prov=${rest.address.province}&flr=${rest.address.floor}&apt=${rest.address.apartment}">Pedir</a></li>
			<li class="active"><a href="#">Información</a></li>
		</ul>
	</div>
	<br>
	<br>


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
								<textarea class="form-control animated" cols="50" id="new-review"
									name="comment" placeholder="Ingrese su opinión aquí..." rows="5"></textarea>
	
								<div class="text-right">
									<div class="stars starrr" data-rating="0"></div>
									<a class="btn btn-danger btn-sm" href="#" id="close-review-box"
										style="display: none; margin-right: 10px;"> Cancelar
									</a>
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

<%@ include file="footer.jsp"%>