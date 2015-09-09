<%@ include file="header.jsp" %>

<H3>Por favor, ingrese sus datos</H3>

<form action="login" method="post">
  E-mail: 
  <input type="text" name="email" required>
  <br/>
  Contraseña: 
  <input type="text" name="pwd" required>
  <input type="submit" value="Submit">
</form>


<%@ include file="footer.jsp" %>