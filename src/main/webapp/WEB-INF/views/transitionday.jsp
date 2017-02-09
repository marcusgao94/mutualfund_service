<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Transition Day</title>
	<style>
		.error {
			color: red;
		}
	</style>
</head>
<body>
<div>
	<c:import url="/header"/>
</div>


<div class="container">
	<h1>Transition Day</h1>
	<form:form method="post" modelAttribute="transitionForm">
		<label> Last Transition Date is: ${transitionForm.lastDate}</label><br />
		<form:input type="hidden" path="lastDate" />
		<label> Enter New Transition Date: </label>
		<form:input type="text" path="newDate" placeholder="MM/dd/yyyy" />
		<form:errors path="newDate" cssClass="error" />
		<div class="form">
			<form:errors path="" cssClass="error" />
			<table class="table">
				<thead>
				<tr>
					<td>Fund Name</td>
					<td>Fund Ticker</td>
					<td>Current Price ($)</td>
					<td>Enter New Price</td>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="listFund" items="${transitionForm.fundList}" varStatus="status"> 
					<tr>
						<td>${listFund.fund.name}</td>
						<td>${listFund.fund.ticker}</td>						
						<td>${listFund.lastPrice}</td>
						<td>
							<form:input path="fundList[${status.index}].newPrice" type="number"
										step="0.01"
										value="${listFund.lastPrice}"/>
							<form:errors path="fundList[${status.index}].newPrice" cssClass="error" />
						</td>
						<td><form:input path="fundList[${status.index}].fund.id" type="hidden" /></td>
						<td><form:input path="fundList[${status.index}].fund.ticker" type="hidden" /></td>
						<td><form:input path="fundList[${status.index}].fund.name" type="hidden" /></td>
						<td><form:input path="fundList[${status.index}].lastPrice" type="hidden" /></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<button type="submit" class="btn btn-default">Submit</button>
	</form:form>

</div>
<div>
	<c:import url="bottom.jsp"/>
</div>

</body>
</html>

