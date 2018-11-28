<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>
<c:url value="/css/favoriteParks.css" var="css"/>
<link rel="stylesheet" href="${css}"/>

<section>
	<div id="container">	
	<div id="title">
		<h1>Favorite Parks</h1>
	</div>

    <table id="parks">
        <tr class="rowTitle">
        		<td></td>
            <td>	Park Name</td>
            <td>Reviews</td>
        </tr>
        
        <c:forEach items="${surveys}" var="survey">
            <tr class="rowItems">
            		<td>
            			<c:set var="imgName" value="${fn:toLowerCase(survey.park.parkCode)}"/>
    					<c:url value="/img/parks/${imgName}.jpg" var="img"/>	
    					<img id="parkImg" src="${img}"/>
    				</td>
                <td>	
                		${survey.park.parkName}
                </td>
                <td id="count">
                		${survey.surveyCount}
                </td>
            </tr>
        </c:forEach>
    </table>
	</div>
</section>
<c:import url="/WEB-INF/jsp/common/footer.jsp"/>