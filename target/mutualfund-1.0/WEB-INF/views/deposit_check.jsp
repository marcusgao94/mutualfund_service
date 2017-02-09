<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Deposit Check</title>
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
	<h3>Deposit Check</h3>
	<form:form modelAttribute="depositCheckForm">
		<table>
			<tr>
				<td>Customer User Name: &nbsp;</td>
				<td><h4>${depositCheckForm.getUserName()}</h4></td>
				<td><input type="hidden" name="userName"
						   value="${depositCheckForm.getUserName()}" autofocus/>
				</td>
				<td><form:errors path="userName" cssClass="error"/></td>
			</tr>

			<tr>
				<td>Amount:</td>
				<td><input type="number" step="0.01" name="amount" value="${depositCheckForm.getAmount()}"/></td>
				<td><form:errors path="amount" cssClass="error"/></td>
			</tr>
			<tr>
			<td><br/></td>
			</tr>
			<tr>
				<th colspan="2">
					<input class="btn btn-default" type="submit" name="button" value="Deposit"/>
				</th>
			</tr>

		</table>
	</form:form>
</div>
<div>
	<c:import url="bottom.jsp"/>
</div>

</body>
</html>
