<jsp:useBean id="home" class="ar.edu.itba.it.paw.Homepage" scope="request" />
<!--jsp:setProperty name="home" property="email" /-->

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

	<H3>Bienvenido ${email}</H3>
	<h4>Listado de restoranes:</h4>
	<c:forEach items="${weekRests}" var="rest"> 
	  <tr>
	    <td><a href="/PAWTPE/showRestaurant?name=${rest.name}">${rest.name}</a></td>
	    <c:forEach items="${rest.typesOfFood}" var="tof">
	    	<tr>
	    		<td>${tof}</td>
	    	</tr>
	    </c:forEach>
	    <td>De: ${rest.startService} a: ${rest.endService}</td>
	  </tr>
	</c:forEach>
</div>

<%@ include file="footer.jsp"%>