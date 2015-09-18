<jsp:useBean id="home" class="ar.edu.itba.it.paw.servlets.Homepage" scope="request" />

<%@ include file="header.jsp"%>

<div class="container">

	<div class="row">
		<div class="col-ms-6 col-ms-offset-3"><div class="well">...</div></div>
	</div>

	

	<c:forEach items="${rlist}" var="r">	
		<a
			href="/PAWTPE/showRestaurant?name=${r.name}&addr=${r.address}">
			<div class="bs-callout bs-callout-info">
				<h4>${r.name}</h4> <br>
				<p>
					Comida:
					<c:forEach items="${r.typesOfFood}" var="tof"> ${tof} </c:forEach>
				</p>
				<p>Horario: ${r.startService} - ${r.endService}</p>
			</div>
		</a>
	</c:forEach>




</div>

<%@ include file="footer.jsp"%>