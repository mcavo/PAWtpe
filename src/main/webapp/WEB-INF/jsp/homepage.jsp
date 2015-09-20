<%@ include file="header.jsp"%>

<div class="container">

	<!-- Main component for a primary marketing message or call to action -->
	<div class="jumbotron">
		<h2>Pedir comida nunca fue tan fácil</h2>
		<p>Estás a un click de lo que buscás.</p>
		<p>
			<a class="btn btn-lg btn-primary" href="#" role="button">Sign in
				»</a>
		</p>
	</div>

	<br>
	<h4>Restoranes de la Semana:</h4>
	<c:forEach items="${weekRests}" var="rest">
		
		<a
			href="/PAWTPE/showRestaurant?name=${rest.name}&srt=${rest.address.street}&numb=${rest.address.number}&neigh=${rest.address.neighborhood}&city=${rest.address.city}&prov=${rest.address.province}&flr=${rest.address.floor}&apt=${rest.address.apartment}">
			<div class="bs-callout bs-callout-info">
				<h4>${rest.name}</h4> <br>
				<p>
					Comida:
					<c:forEach items="${rest.typesOfFood}" var="tof"> ${tof} </c:forEach>
				</p>
				<p>Horario: ${rest.startService} - ${rest.endService}</p>
			</div>
		</a>
	</c:forEach>
</div>

<%@ include file="footer.jsp"%>