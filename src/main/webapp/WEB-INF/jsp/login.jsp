<%@ include file="header.jsp" %>

<H3>Por favor, ingrese sus datos</H3>

<form action="login" method="post">
  E-mail: 
  <input type="text" name="email">
  <br/>
  Contraseña: 
  <input type="text" name="pwd">
  <input type="submit" value="Submit">
</form>


<%@ include file="footer.jsp" %>