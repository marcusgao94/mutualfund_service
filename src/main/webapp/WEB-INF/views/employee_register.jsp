<%--
  Created by IntelliJ IDEA.
  User: marcusgao
  Date: 17/1/19
  Time: 下午9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Employee register</title>
	<style>
		.error {
			color: red;
		}
		.red-star {
    	color: red;
		}
	</style>
</head>
<body>
<div>
	<c:import url="/header"/>
</div>
<div class="container">

	<h3>Employee Register</h3>

	<form:form method="POST" modelAttribute="employeeRegisterForm">
		<table>


			<tr>
				<td><span class="red-star">*</span>User Name:</td>
				<td>
					<input type="text" name="userName"
						   value="${employeeRegisterForm.getUserName()}"/>
				</td>
				<td>
					<form:errors path="userName" cssClass="error"/>
				</td>
			</tr>

			<tr>
				<td><span class="red-star">*</span>Password:</td>
				<td>
					<input type="password" name="password" value=""/>
				</td>
				<td>
					<form:errors path="password" cssClass="error"/>
				</td>
			</tr>

			<tr>
				<td><span class="red-star">*</span>Confirm Password:</td>
				<td>
					<input type="password" name="confirmPassword" value=""/>
				</td>
				<td>
					<form:errors path="confirmPassword" cssClass="error"/>
				</td>
			</tr>

			<tr>
				<td><span class="red-star">*</span>First Name:</td>
				<td>
					<input type="text" name="firstName"
						   value="${employeeRegisterForm.getFirstName()}" autofocus/>
				</td>
				<td>
					<form:errors path="firstName" cssClass="error"/>
				</td>
			</tr>

			<tr>
				<td><span class="red-star">*</span>Last Name:</td>
				<td>
					<input type="text" name="lastName"
						   value="${employeeRegisterForm.getLastName()}"/>
				</td>
				<td>
					<form:errors path="lastName" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td><br/></td>
				<td><br/></td>
			</tr>
			<tr>
					<td><span class="red-star">*</span> is required</td>
			</tr>
			<tr>
				<td><br/></td>
				<td><br/></td>
			</tr>
			<tr>
				<th colspan="2">
					<input class="btn btn-default" type="submit" name="button" value="Register"/>
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
