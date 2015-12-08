<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Food on Board</title>
<link href="image/favicon.ico" rel="icon" type="image/x-icon" />
<link href="../../css/common.css" type="text/css" rel="stylesheet">
<link href="../../css/restcard.css" type="text/css" rel="stylesheet">
<link href="../../css/comments.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="../../css/bootstrap.min.css" />
</head>
<body class="mybody">

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/bin/homepage">Food on Board</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<c:choose>
					<c:when test="${user.isAdmin}">
						<li><a href="/bin/addManager">Agregar manager</a></li>
						<li><a href="/bin/restaurant/register">Agregar
								restaurante</a></li>
					</c:when>
					<c:when test="${user.isManager}">
						<li><a href="/bin/restaurant/addDish">Agregar menu</a></li>
						<li><a href="/bin/showOrders">Ver historial</a></li>
					</c:when>
				</c:choose>
				<li><a href="/bin/restaurant/list">Restaurantes</a></li>
				<li><a href="/bin/restaurant/popular">Mas pedidos</a></li>
				<li><a href="#contact">Contacto</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${user.firstName==null}">
						<li><a href="/bin/login">Ingresa</a></li>
						<li><a href="/bin/signup">Regístrate</a></li>
					</c:when>
					<c:otherwise>
						<!-- >li><a href="/bin/restaurant/status">Pedido</a></li -->
						<li><a href="/bin/profile/edit">${user.firstName}</a></li>
						<!-- li><a href="/bin/user/ask">Cambiar contraseña</a></li-->
						<li><a href="/bin/logout">Salir</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	</nav>
	<div class="container">
		<c:if test="${message != null}">
			<br>
			<br>
			<div class="alert alert-${message.type} alert-dismissible"
				role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				${message.text}
			</div>
		</c:if>