<%@ include file="header.jsp"%>

<div class="container">

	<!-- Main component for a primary marketing message or call to action -->
	<div class="jumbotron">
		<h2>Pedir comida nunca fue tan fácil</h2>
		<p>Estás a un click de lo que buscás.</p>
		<p>
			<a class="btn btn-lg btn-primary" href="/PAWTPE/bin/signup/" role="button">Sign in
				»</a>
		</p>
	</div>

	<br>
	<h4>Restoranes de la Semana:</h4>
	<c:forEach items="${weekRests}" var="rest">


		<a href="/PAWTPE/bin/restaurant/menu?name=${rest.name}&srt=${rest.address.street}&numb=${rest.address.number}&neigh=${rest.address.neighborhood}&city=${rest.address.city}&prov=${rest.address.province}&flr=${rest.address.floor}&apt=${rest.address.apartment}"">
					<div class="bs-callout bs-callout-info">
						<h4>${rest.name}</h4>
						<p>
							<c:forEach items="${rest.typesOfFood}" var="tof">
								<span class="label label-primary">${tof}</span>
							</c:forEach>
						</p>
						<p class="restcard-adress">${rest.address.street} ${rest.address.number}<c:if test="${rest.address.floor}!=null">${rest.address.floor}º</c:if><c:if test="${rest.address.apartment}!=null"> ${rest.address.apartment}</c:if>, ${rest.address.neighborhood}, ${rest.address.province}</p>
						
						<p class="restcard-schedule">Abierto de ${rest.startService} a ${rest.endService} hs</p>
						<p> <span class="label label-warning">${rest.score}</span> ${rest.countComments} calificaciones </p>
					</div>
				</a>

	</c:forEach>
</div>

<%@ include file="footer.jsp"%>