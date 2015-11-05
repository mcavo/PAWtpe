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
<link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css" />
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
			<a class="navbar-brand" href="/PAWTPE/bin/homepage">Food on Board</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<c:choose>
					<c:when test="${user.isAdmin}">
						<li><a href="/PAWTPE/bin/addManager">Agregar manager</a></li>
						<li><a href="/PAWTPE/bin/restaurant/register">Agregar restaurante</a></li>
					</c:when>
					<c:when test="${user.isManager}">
						<li><a href="/PAWTPE/bin/restaurant/addDish">Agregar menu</a></li>
						<li><a href="/PAWTPE/bin/showOrders">Ver historial</a></li>
					</c:when> 
				</c:choose>
				<li><a href="/PAWTPE/bin/restaurant/list">Restaurantes</a></li>
				<li><a href="/PAWTPE/bin/restaurant/popular">Mas pedidos</a></li>
				<li><a href="#contact">Contacto</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${user.firstName==null}">
						<li><a href="../login">Ingresa</a></li>
						<li><a href="../signup">Regístrate</a></li>
					</c:when>    
    				<c:otherwise>
    					<li><a href="/PAWTPE/bin/restaurant/status">Pedido</a></li>
    					<li><a href="../profile/edit">${user.firstName}</a></li> 
    					<!-- li><a href="../user/ask">Cambiar contraseña</a></li-->
    					<li><a href="../logout">Salir</a></li>
    				</c:otherwise>
    			</c:choose>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	</nav>