<%@ include file="header.jsp"%>

<div class="container">
	<div class="col-md-10 col-md-offset-1">
		<form action="addmanager" method="post">

			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Mail del manager</h3>
				</div>
				<div class="panel-body">
					<form id="addm" method="post" action="addManager">
						<br>
						<div class="row">
							<div class="col-sm-8 col-sm-offset-2">
								<div class="form-group">
									<select name="manager-mail" required>
										<c:forEach items="${clist}" var="managerWannaBe">
											<option value="${managerWannaBe.mail}">
												${managerWannaBe.mail}</option>
										</c:forEach>
									</select>
								</div>


								<div class="form-group">
									<select name="restaurant-id" required>
										<c:forEach items="${rlist}" var="rest">
											<option value="${rest.id}">${rest.name} - 
												${rest.address.street} ${rest.address.number}<c:if test="${rest.address.floor}!=0"> ${rest.address.floor}º</c:if>
												<c:if test="${rest.address.apartment}!=null"> ${rest.address.apartment}</c:if>,
												${rest.address.neighborhood}, ${rest.address.city},
												${rest.address.province}
											</option>
										</c:forEach>
									</select>
								</div>

							</div>
						</div>
						<div class="row">
					<div class="form-group col-md-2 col-md-offset-5">
						<input type="submit" class="btn btn-info" value="Confirmar">
					</div>
				</div>
					</form>
				</div>
			</div>
		</form>
	</div>
</div>

<%@ include file="footer.jsp"%>