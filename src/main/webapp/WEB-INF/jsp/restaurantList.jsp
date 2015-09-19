<jsp:useBean id="home" class="ar.edu.itba.it.paw.servlets.Homepage" scope="request" />

<%@ include file="header.jsp"%>

<div class="container">

	<div class="row">
		<div class="col-ms-6 col-ms-offset-3"><div class="well">...</div></div>
	</div>

	

	<c:forEach items="${rlist}" var="rest">	
		<a
			href="/PAWTPE/showRestaurant?name=${rest.name}&srt=${rest.address.street}&numb=${rest.address.number}&neigh=${rest.address.neighborhood}&city=${rest.address.city}&prov=${rest.address.province}&flr=${rest.address.floor}&apt=${rest.address.apartment}"">
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