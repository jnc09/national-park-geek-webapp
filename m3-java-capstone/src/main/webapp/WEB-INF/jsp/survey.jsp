<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>
<c:url value="/css/survey.css" var="css"/>
<link rel="stylesheet" href="${css}"/>

<section>
	<div id="container">
	<div id="title">
		<h1>Park Survey</h1>
	</div>

    <c:url value="/survey" var="post"/>
    <form id="survey" method="POST" action="${post}">
	<div id="pName">
        <p>Park Name: </p>
        <select name="parkCode" required>
            <c:forEach items="${parks}" var="park">
                <option value="${park.parkCode}">${park.parkName}</option>	
            </c:forEach>
        </select>
    </div>

	<div id="email">
        <p>Email: </p>
        <input type="email" name="email" required>
    </div>

	<div id="state">
        <p>State: </p>
        <select name="state" required>
            <c:forEach items="${states}" var="state">
                <option value="${state}">${state}</option>	
            </c:forEach>
        </select>
    </div>

	<div id="activity">
        <p>Activity Level: </p>
        <c:forEach items="${activeLevels}" var="activeLevel" varStatus="count">
            <input type="radio" value="${count.index + 1}" name="activeLevel"/ required>${activeLevel}<br>	
        </c:forEach>
    </div>
	
	<div id="submit">
    		<input class="formSubmitButton" type="submit" value="Submit" />
    </div>

    </form>
	</div>
</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp"/>