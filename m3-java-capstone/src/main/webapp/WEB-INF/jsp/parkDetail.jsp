<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/jsp/common/header.jsp" />
<c:url value="/css/parkDetail.css" var="css" />
<link rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="${css}" />
<section>

	<div class="container">


			<c:set var="imgName" value="${fn:toLowerCase(park.parkCode)}" />
			<c:url value="/img/parks/${imgName}.jpg" var="img" />
			<c:url value="/parkDetail?parkCode=${imgName}" var="detail" />


			<div class="parkImgContainer">
				<img class="parkImg" src="${img}" />
			</div>
			<div class="quote">
				<blockquote>
					"${park.inspirationalQuote}"
					<footer>
						<cite>-${park.inspirationalQuoteSource}</cite>
					</footer>
				</blockquote>
			</div>
			
			<div id="content">
				<div id="info-box">
					<div id="info">
						<h1>${park.parkName}</h1>
						<p>${park.parkDescription}</p>
						<ul class="parkStats">
							<li><strong>Year Founded: </strong>${park.yearFounded}</li>
							<li><strong>State: </strong>${park.state}</li>
							<li><strong>Entry Fee: </strong>$${park.entryFee}</li>
							<li><strong>Acreage: </strong>
							<fmt:formatNumber type="number" groupingUsed="true"
									value="${park.acreage}" /></li>
							<li><strong>Elevation: </strong>
							<fmt:formatNumber type="number" groupingUsed="true"
									value="${park.elevationInFeet}" /></li>
							<li><strong>Miles of Trail: </strong>
							<fmt:formatNumber type="number" groupingUsed="true"
									value="${park.milesOfTrail}" /></li>
							<li><strong>Campsites: </strong>${park.numberOfCampsites}</li>
							<li><strong>Climate: </strong> ${park.climate}</li>
							<li><strong>Annual Visitor Counter: </strong>
							<fmt:formatNumber type="number" groupingUsed="true"
									value="${park.annualVisitorCount}" /></li>
							<li><strong>Number of Animal Species: </strong>
							<fmt:formatNumber type="number" groupingUsed="true"
									value="${park.numberOfAnimalSpecies}" /></li>


						</ul>

					</div>

					<div id="form">
						<p>Select Fahrenheight or Celcius:</p>

						<c:url value="/parkDetail?parkCode=${park.parkCode}" var="temp" />
						<form id="scale" method="POST" action="${temp}">
							<select name="scale">
								<option value="f" selected>Fahrenheit</option>
								<option value="c">Celcius</option>
							</select> <input class="formSubmitButton" type="submit" value="Submit" />
						</form>
					</div>
				</div>

				<div id="weather">
					<div id="todayWeather">

						<h1>Today</h1>
						<c:forEach begin="0" end="0" items="${weather}" var="day">


							<c:if test="${scale == 'f' || empty scale}">
								<c:set var="high" value="${day.tempHigh}" />
								<c:set var="low" value="${day.tempLow}" />
								<c:set var="scaleFormat" value="ºF" />
							</c:if>


							<c:if test="${scale == 'c' }">
								<c:set var="highDec" value="${(day.tempHigh - 32) * .5556}" />
								<c:set var="lowDec" value="${(day.tempLow - 32) * .5556}" />

								<fmt:formatNumber var="high" value="${highDec}"
									maxFractionDigits="0" />
								<fmt:formatNumber var="low" value="${highDec}"
									maxFractionDigits="0" />

								<c:set var="scaleFormat" value="ºC" />
							</c:if>


							<c:set var="forecast" value="${day.forecast}" />

							<c:url value="/img/weather/${day.imageName}" var="weatherImg" />
							<img id="weatherImg" src="${weatherImg}" />
							
							<div class="todayText">
							<p>High: ${high} ${scaleFormat}</p>
							<p>Low: ${low} ${scaleFormat}</p>
							</div>


							<div id="advisory">

								<h2>Advisory</h2>

								<c:if test="${forecast == 'snow'}">
									<p>Pack snowshoes</p>
								</c:if>

								<c:if test="${forecast == 'rain'}">
									<p>Pack rain gear and wear waterproof shoes</p>
								</c:if>

								<c:if test="${forecast == 'thunderstorms'}">
									<p>Seek shelter and avoid hiking on exposed ridges</p>
								</c:if>

								<c:if test="${forecast == 'sunny'}">
									<p>Pack sunblock</p>
								</c:if>

								<c:if test="${forecast == 'partly cloudy'}">
									<p>Pack sunblock</p>
								</c:if>

								<c:if test="${day.tempHigh > 75}">
									<p>Bring an extra gallon of water</p>
								</c:if>

								<c:if test="${(day.tempHigh - day.tempLow) > 20}">
									<p>Wear breathable layers</p>
								</c:if>

								<c:if test="${day.tempLow < 20}">
									<p>It is dangerous to be exposed to frigid temperatures</p>
								</c:if>



							</div>
						</c:forEach>
					</div>






					<c:forEach begin="1" items="${weather}" var="day">
						<div class="advisoryGroup">
							<div class="advisoryImg">
								<c:url value="/img/weather/${day.imageName}" var="weatherImg" />
								<img id="weekImg" src="${weatherImg}" />

							</div>

							<div class="advisoryHighTemp">
								<c:if test="${scale == 'f' || empty scale}">
									<c:set var="high" value="${day.tempHigh}" />
									<c:set var="low" value="${day.tempLow}" />
									<c:set var="scaleFormat" value="ºF" />
								</c:if>

								<c:if test="${scale == 'c' }">
									<c:set var="highDec" value="${(day.tempHigh - 32) * .5556}" />
									<c:set var="lowDec" value="${(day.tempLow - 32) * .5556}" />

									<fmt:formatNumber var="high" value="${highDec}"
										maxFractionDigits="0" />
									<fmt:formatNumber var="low" value="${highDec}"
										maxFractionDigits="0" />

									<c:set var="scaleFormat" value="ºC" />
								</c:if>

								High: ${high} ${scaleFormat}
							</div>

							<div class="advisoryLowTemp">
								<c:if test="${scale == 'f' || empty scale}">
									<c:set var="high" value="${day.tempHigh}" />
									<c:set var="low" value="${day.tempLow}" />
									<c:set var="scaleFormat" value="ºF" />
								</c:if>


								<c:if test="${scale == 'c' }">
									<c:set var="highDec" value="${(day.tempHigh - 32) * .5556}" />
									<c:set var="lowDec" value="${(day.tempLow - 32) * .5556}" />

									<fmt:formatNumber var="high" value="${highDec}"
										maxFractionDigits="0" />
									<fmt:formatNumber var="low" value="${highDec}"
										maxFractionDigits="0" />

									<c:set var="scaleFormat" value="ºC" />
								</c:if>

								Low: ${low} ${scaleFormat}
							</div>

						</div>
					</c:forEach>


				</div>
			</div>
		</div>
</section>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />