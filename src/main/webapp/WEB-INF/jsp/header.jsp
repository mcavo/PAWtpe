<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
<jsp:useBean id="header" class="ar.edu.itba.it.paw.Homepage"
	scope="request" />
<jsp:setProperty name="header" property="email" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/navbar-fixed-top.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
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
			<a class="navbar-brand" href="#">PAW</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="#rest">Restaurants</a></li>
				<li><a href="#about">About</a></li>
				<li><a href="#contact">Contact</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${email==null}">
						<li><a href="#">Log in</a></li>
						<li><a href="#">Sign in</a></li>
					</c:when>    
    				<c:otherwise>
    					<li><a href="#">${email}</a></li>  //cambiar por un user.name o algo asi
    				</c:otherwise>
    			</c:choose>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	</nav>