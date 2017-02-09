<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Employee Login</title>
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
	<h3>Employee Login</h3>
	<br>
	<form:form method="post" modelAttribute="loginForm">
		<table>
			<c:if test="${not empty error}">
				<tr>
					<td class="error">${error}</td>
				</tr>
			</c:if>
			<tr>
				<td>User Name: &nbsp;</td>
				<td><input type="text" name="userName"
						   value="${loginForm.getUserName()}" autofocus/>
				</td>
				<td><form:errors path="userName" cssClass="error"/></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" value=""/></td>
				<td><form:errors path="password" cssClass="error"/></td>
			</tr>

			<!-- <tr>
				<td>
					<label><input type="checkbox"> Remember me</label>
				</td>

			</tr> -->
	
			<tr>
			<td><br/><td>
			<td><br/><td>
			</tr>
			
			<tr>
				<th colspan="2">
					<input class="btn btn-default" type="submit" name="button" value="Login"/>
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
