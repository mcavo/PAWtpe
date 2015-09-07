<jsp:useBean id="home" class="ar.edu.itba.it.paw.Homepage"
	scope="request" />
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
</div>

<%@ include file="footer.jsp"%>