<%@ include file="header.jsp"%>

<div class="container">
	<div class="col-md-8 col-md-offset-2">
		<div class="title">
			<h3>Pedidos a ${rest.name}</h3>
		</div>


		<div class="orders-container">
			<c:forEach items="${olist}" var="order">
				<div class="bs-callout bs-callout-info">
					<p>Nombre: ${order.user.firsName}
						${order.user.lastName}<p>
					<p>
						Dirección: ${order.user.address.street}
						${order.user.address.number}
						<c:if test="${rest.address.floor}!=null">${rest.address.floor}º</c:if>
						<c:if test="${rest.address.apartment}!=null"> ${rest.address.apartment}</c:if>
						, ${rest.address.neighborhood}
					<p>
					<div class="col-md-6">Estado: ${order.status}</div>
					<div class="col-md-6">Total: $ ${order.total}</div>
				</div>
		</div>
		</c:forEach>
	</div>
</div>
<%@ include file="footer.jsp"%>