<%@ include file="./../header.jsp"%>
<div class="container">


	<div class="row">
		<div>
			<H3>${name}</H3>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
		</div>
		<div class="pull-right">
			<H4>Puntaje: ${score}</H4>
		</div>
	</div>

	<div>
		<ul class="nav nav-tabs nav-justified">
			<li class="active"><a href="#">Pedir</a></li>
			<li><a href="/PAWTPE/menuRestaurant?name=${name}&addr=${add}">Información</a></li>
		</ul>
	</div>
	
	
	<br><br>
	<div>
		<c:forEach items="${sections}" var="sect">
			<h4>${sect.name}</h4>
			<c:forEach items="${sect.dishes}" var="dish">
				<div class="bs-callout bs-callout-info">
					<h4>${dish.product}</h4>
					<div class="row">
						<div class="col-xs-9">
							<p>${dish.description}</p>
						</div>
						<div class="col-xs-3 line-vert-left">
							<div class="col-xs-12">$ ${dish.price}</div>
							<br> <br>
							<div class="col-xs-12">
								<select>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
									<option value="13">13</option>
									<option value="14">14</option>
									<option value="15">15</option>
									<option value="16">16</option>
									<option value="17">17</option>
									<option value="18">18</option>
									<option value="19">19</option>
									<option value="20">20</option>
									<option value="21">21</option>
									<option value="22">22</option>
									<option value="23">23</option>
									<option value="24">24</option>
									<option value="25">25</option>
									<option value="26">26</option>
									<option value="27">27</option>
									<option value="28">28</option>
									<option value="29">29</option>
									<option value="30">30</option>
								</select>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:forEach>
	</div>
</div>

<%@ include file="./../footer.jsp"%>

