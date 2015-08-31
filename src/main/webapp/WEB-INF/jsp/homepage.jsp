<%@ include file="header.jsp" %>


<jsp:useBean id="home" class="ar.edu.itba.it.paw.Homepage" scope="request"/>
<jsp:setProperty name="home" property="email" />
<jsp:setProperty name="home" property="pwd" />


<H3>Bienvenido ${email}</H3>


<%@ include file="footer.jsp" %>