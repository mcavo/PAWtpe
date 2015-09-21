<%@ include file="header.jsp"%>

<div class="container">
	<div class="col-md-8 col-md-offset-2">
		<div class="title">
			<h3>Pedidos a ${rest.name}</h3>
		</div>

		<div class="orders-container">
			<c:forEach items="${olist}" var="order">
				<div class="bs-callout bs-callout-info">
					<div class="raw">
						<div class="col-md-12">Nombre: ${order.user.firstName}
							${order.user.lastName}</div>
						<div class="col-md-12">
							Dirección: ${order.user.address.street}
							${order.user.address.number}
							<c:if test="${order.user.address.floor}!=0">${order.user.address.floor}º</c:if>
							<c:if test="${order.user.address.apartment}!=null"> ${order.user.address.apartment}</c:if>
							, ${order.user.address.neighborhood}
						</div>
						<div class="col-md-6">Estado: ${order.status}</div>
						<div class="col-md-6">Total: $ ${order.total}</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>

<%@ include file="footer.jsp"%>