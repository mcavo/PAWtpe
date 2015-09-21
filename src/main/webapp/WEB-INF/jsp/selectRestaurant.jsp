<%@ include file="header.jsp"%>

<div class="row">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Restaurant para asignar de manager a:
				${manager}</h3>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-8 col-md-offset-2">
		<jsp:useBean id="managerCredential" scope="request"
			class="ar.edu.itba.it.paw.models.Credential"></jsp:useBean>
		<jsp:setProperty name="managerCredential" property="id" value="${cred.id}" />
		<jsp:setProperty name="managerCredential" property="rol" value="${cred.rol}" />
		<c:forEach items="${rlist}" var="rest">
			<a
				href="/PAWTPE/confirmmanager?name=${rest.name}&srt=${rest.address.street}&numb=${rest.address.number}&neigh=${rest.address.neighborhood}&city=${rest.address.city}&prov=${rest.address.province}&flr=${rest.address.floor}&apt=${rest.address.apartment}"">
				<div class="bs-callout bs-callout-info">
					<!--<form action="confirmmanager" method="get">
					<div class="col-md-6">-->

					<h4>${rest.name}</h4>

					<p class="restcard-adress">${rest.address.street}
						${rest.address.number}
						<c:if test="${rest.address.floor}!=null">${rest.address.floor}º</c:if>
						<c:if test="${rest.address.apartment}!=null"> ${rest.address.apartment}</c:if>
						, ${rest.address.neighborhood}, ${rest.address.province}
					</p>

					<!--<jsp:useBean id="restaurant" scope="session"
							class="ar.edu.itba.it.paw.models.Restaurant"></jsp:useBean>
						<jsp:setProperty name="restaurant" property="name"
							value="${rest.name}" />
						<jsp:useBean id="resAddress" scope="session"
							class="ar.edu.itba.it.paw.models.Address"></jsp:useBean>
						<jsp:setProperty name="resAddress" property="street"
							value="${rest.address.street}" />
						<jsp:setProperty name="resAddress" property="number"
							value="${rest.address.number}" />
						<jsp:setProperty name="resAddress" property="floor"
							value="${rest.address.floor}" />
						<jsp:setProperty name="resAddress" property="apartment"
							value="${rest.address.apartment}" />
						<jsp:setProperty name="resAddress" property="province"
							value="${rest.address.province}" />
						<jsp:setProperty name="resAddress" property="province"
							value="${rest.address.province}" />
						<jsp:setProperty name="resAddress" property="city"
							value="${rest.address.city}" />-->
					<!--</div>
					<div class="col-md-6">
						<div class="form-group col-md-2 col-md-offset-5">
							<button type="submit" class="btn btn-primary disabled">Seleccionar</button>
						</div>
					</div>
				</form>-->
				</div>
			</a>
		</c:forEach>

	</div>
</div>

<%@ include file="footer.jsp"%>
