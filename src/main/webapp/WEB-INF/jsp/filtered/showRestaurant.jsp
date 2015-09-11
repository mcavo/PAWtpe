<%@ include file="./../header.jsp" %>
		
<H3>Restaurant ${name}</H3>
<H4>Puntaje: ${score}</H4>

<a class="btn btn-lg btn-primary" href="#" role="button">Menu</a>

<c:if test="${okToQualify}">
	<a class="btn btn-lg btn-primary" href="#" role="button">Calificar</a>
</c:if>


<div>
	<h5>Menu</h5>
	<c:forEach items="${sections}" var="sect"> 
	  <tr>
	    <td>${sect.name} :</br></td>
	  </tr>
	  <c:forEach items="${sect.dishes}" var="dish">
		  <tr>
		  	<td>${dish.product} - </td>
		  	<td>${dish.description} -</td>
		  	<td>${dish.price}</td>
		  </tr>
		</c:forEach>
	</c:forEach>
</div>
<%@ include file="./../footer.jsp" %>