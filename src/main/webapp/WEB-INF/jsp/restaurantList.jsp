<jsp:useBean id="home" class="ar.edu.itba.it.paw.servlets.Homepage"
	scope="request" />

<%@ include file="header.jsp"%>

<div class="container">

	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<div class="well">
				<form role="form" action="restaurantList" method="GET">
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
				<a
					href="/PAWTPE/menuRestaurant?name=${rest.name}&srt=${rest.address.street}&numb=${rest.address.number}&neigh=${rest.address.neighborhood}&city=${rest.address.city}&prov=${rest.address.province}&flr=${rest.address.floor}&apt=${rest.address.apartment}"">
					<div class="bs-callout bs-callout-info">
						<h4>${rest.name}</h4>
						<br>
						<p>
							<c:forEach items="${rest.typesOfFood}" var="tof">
								<label> ${tof} </label>
							</c:forEach>
						</p>
						<p class="restcard-schedule">Abierto de ${rest.startService} a
							${rest.endService} hs</p>

					</div>
				</a>
			</c:forEach>

		</div>
	</div>


</div>

<%@ include file="footer.jsp"%>