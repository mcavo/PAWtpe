<jsp:useBean id="home" class="ar.edu.itba.it.paw.servlets.Homepage" scope="request" />

<%@ include file="header.jsp"%>

<div class="container">

	<div class="row">
		<div class="col-md-8 col-md-offset-2"><div class="well">...</div></div>
	</div>

	<div class="row">
		<div class="col-md-8 col-md-offset-2">

	<c:forEach items="${rlist}" var="r">	
		
			<div class="bs-callout bs-callout-info">
				<h4>${r.name}</h4> <br>
				<p>
					
					<c:forEach items="${r.typesOfFood}" var="tof"> <label> ${tof} </label> </c:forEach>
				</p>
				<p class="restcard-schedule">Abierto de ${r.startService} a ${r.endService} hs</p>
			</div>
		</a>
	</c:forEach>

	</div></div>


</div>

<%@ include file="footer.jsp"%>