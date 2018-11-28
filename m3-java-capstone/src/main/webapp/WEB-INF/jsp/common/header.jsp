<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Roboto">
<title>Park Geek</title>

<c:url value="/css/headerFooter.css" var="css" />
<c:url value="/" var="homePage" />

<link rel="stylesheet" href="${css}" />
<c:url value="/survey" var="survey" />
<c:url value="/favoriteParks" var="favParks" />

</head>
<body>

	<div class="headerContainer">
		<div class="outer">
			<div class="inner">
				<div class="header">
					<c:url value="/img/logo.png" var="logo" />
					<a href="${homePage}"><img class="logo" src="${logo}" /></a>

					<nav>
						<ul class="navUl">
							<li class="links"><a href="${homePage}">Home</a></li>
							<li><a href="${survey}">Survey</a></li>
							<li><a href="${favParks}">Favorite Parks</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>