<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Change Password</title>
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

	<h4>Change Password</h4>
	<form:form modelAttribute="changePasswordForm">
		<table>
			<tr>
			<td><br/></td>
			</tr>
			<tr>
				<td>
					<input type="hidden" name="userName" value="${changePasswordForm.userName}">
				</td>
				<form:errors path="userName" cssClass="error" />
			</tr>

			<tr>
				<td>Old Password:</td>
				<td>
					<input type="password" name="originPassword" value="" autofocus/>
				</td>
				<td><form:errors path="originPassword" cssClass="error"/></td>
			</tr>

			<tr>
				<td>New Password:</td>
				<td>
					<input type="password" name="newPassword" value="" autofocus/>
				</td>
				<td><form:errors path="newPassword" cssClass="error"/></td>
			</tr>

			<tr>
				<td>Confirm Password:</td>
				<td>
					<input type="password" name="confirmNewPassword" value=""/>
				</td>
				<td><form:errors path="confirmNewPassword" cssClass="error"/></td>
			</tr>
			
			<tr>
				<th>
				<br/>
				<th>
			<tr>
				<th colspan="2">
					<input type="submit" name="button" value="Change Password"/>
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
