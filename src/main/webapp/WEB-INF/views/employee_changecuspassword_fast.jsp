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
	<div class="col-sm-8">
		<h3>Reset Customer Password</h3>
		<form:form modelAttribute="changePasswordForm">

			<table class="table">
				<tr>
					<td>Customer User Name:</td>
					<td>
						<div class="col-sm-7">
							<select name="userName" class="form-control" id="sel1">
								<option value="">Please select a name
								</option>
								<c:forEach var="sessionUser" items="${userList}">
									<option>${sessionUser.userName}</option>
								</c:forEach>
							</select>
						</div>
					</td>
					<td><form:errors path="userName" cssClass="error"/></td>
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

				<tr>
					<td>New Password:</td>
					<td><input type="password" name="newPassword" value=""/></td>
					<td><form:errors path="newPassword" cssClass="error"/></td>
				</tr>

				<tr>
					<td>Confirm Password:</td>
					<td><input type="password" name="confirmNewPassword" value=""/></td>
					<td><form:errors path="confirmNewPassword" cssClass="error"/></td>
				</tr>

				<tr>
					<td><br/><br/></td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="Reset Password"/>
					</td>
					<td>
						<input type="hidden" name="fast" value="fast"/>
					</td>
				</tr>
			</table>


		</form:form>
	</div>
</div>


<div>
	<c:import url="bottom.jsp"/>
</div>
</body>

</html>
