<%@ include file="header.jsp"%>

<div class="container">

	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<div class="well">
				<form role="form" action="/bin/restaurant/list" method="POST">
					<div class="form-group">
						<label for="type"><span class="glyphicon glyphicon-filter"></span>Filtrar por:</label> 
						<select
							class="form-control" name="type" id="type" onchange="this.form.submit()">
							<option value="">Elija un filtro</option>
							<option value="arabe">arabe</option>
							<option value="argentina">argentina</option>
							<option value="armenia">armenia</option>
							<option value="autor">autor</option>
							<option value="china">china</option>
							<option value="deli">deli</option>
							<option value="italiana">italiana</option>
							<option value="japonesa">japonesa</option>
							<option value="mexicana">mexicana</option>
							<option value="norteamericana">norteamericana</option>
							<option value="parrilla">parrilla</option>
							<option value="peruana">peruana</option>
							<option value="vegetariana">vegetariana</option>
						</select>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-8 col-md-offset-2">

			<c:forEach items="${rlist}" var="rest">
				<a href="/bin/restaurant/details?code=${rest.id}">
					<div class="bs-callout bs-callout-info">
						<h4>${rest.name}</h4>
						<p>
							<c:forEach items="${rest.typesOfFood}" var="tof">
								<span class="label label-primary">${tof}</span>
							</c:forEach>
						</p>
						<p class="restcard-adress">${rest.address.street} ${rest.address.number}<c:if test="${rest.address.floor}!=null">${rest.address.floor}º</c:if><c:if test="${rest.address.apartment}!=null"> ${rest.address.apartment}</c:if>, ${rest.address.neighborhood.name}, ${rest.address.province}</p>
						
						<p class="restcard-schedule">Abierto de ${rest.from} a ${rest.to} hs</p>
						<p> <span class="label label-warning">${rest.score}</span> ${rest.countComments} calificaciones </p>
					</div>
				</a>
			</c:forEach>

		</div>
	</div>


</div>

<%@ include file="footer.jsp"%>