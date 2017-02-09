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


	<h5>Reset Customer Password</h5>
	<form:form modelAttribute="changePasswordForm">
		<table class="table">
			<tr>
				<td>User Name:</td>
				<td><h4>${changePasswordForm.userName}</h4></td>
				<td><form:errors path="userName" cssClass="error"/></td>
				<td>
					<input type="hidden" name="userName" value="${changePasswordForm.userName}"/>
				</td>
			</tr>

			<tr>
				<td>New Password:</td>
				<td>
					<input type="password" name="newPassword" />
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
				<td>
					<input type="hidden" name="originPassword"
						   value="${changePasswordForm.originPassword}">
				</td>
				<td>
					<form:errors path="originPassword" cssClass="error"/>
				</td>
			</tr>
		</table>
		<input class="btn btn-default" type="submit" name="button" value="Change Password"/>
	</form:form>
</div>

<div>
	<c:import url="bottom.jsp"/>
</div>
</body>

</html>
