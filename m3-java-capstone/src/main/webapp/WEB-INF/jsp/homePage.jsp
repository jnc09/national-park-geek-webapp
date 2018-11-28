<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url value="/css/homePage.css" var="css" />
<link rel="stylesheet" href="${css}" />
<link rel="stylesheet"
	href="//fonts.googleapis.com/css?family=Roboto">

<section>
	<div class="container">
		<c:forEach items="${parks}" var="park">

			<c:set var="imgName" value="${fn:toLowerCase(park.parkCode)}" />
			<c:url value="/img/parks/${imgName}.jpg" var="img" />
			<c:url value="/parkDetail?parkCode=${imgName}" var="detail" />

			<div class="park debug">
				<a href="${detail}" class="parkImg debug"> <img class="parkPicture"
					src="${img}" />
				</a>
				<div class="parkText">
					<h1>
						<a href="${detail}">${park.parkName}</a>
					</h1>
					<p>${park.parkDescription}</p>
				</div>
			</div>
		</c:forEach>
	</div>
</section>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />