<%@ include file="header.jsp"%>

	<!-- Main component for a primary marketing message or call to action -->
	<div class="jumbotron">
		<h2>Pedir comida nunca fue tan f�cil</h2>
		<p>Est�s a un click de lo que busc�s.</p>
		<p>
			<a class="btn btn-lg btn-primary" href="/bin/signup" role="button">Sign in
				�</a>
		</p>
	</div>

	<br>
	<h4>Restoranes de la Semana:</h4>
	<c:forEach items="${weekRests}" var="rest">


		<a href="/bin/restaurant/menu?code=${rest.id}">
					<div class="bs-callout bs-callout-info">
						<h4>${rest.name}</h4>
						<p>
							<c:forEach items="${rest.typesOfFood}" var="tof">
								<span class="label label-primary">${tof}</span>
							</c:forEach>
						</p>
						<p class="restcard-adress">${rest.address.street} ${rest.address.number}<c:if test="${rest.address.floor}!=null">${rest.address.floor}�</c:if><c:if test="${rest.address.apartment}!=null"> ${rest.address.apartment}</c:if>, ${rest.address.neighborhood.name}, ${rest.address.province}</p>
						
						<p class="restcard-schedule">Abierto de ${rest.from} a ${rest.to} hs</p>
						<p> <span class="label label-warning">${rest.score}</span> ${rest.countComments} calificaciones </p>
					</div>
				</a>

	</c:forEach>
</div>

<%@ include file="footer.jsp"%>