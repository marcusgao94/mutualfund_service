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
	<title>Customer register</title>
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

	<h3>Customer Register</h3>

	<form:form method="POST" modelAttribute="createCustomerForm">
		<table>


			<tr>
				<td><span class="red-star">*</span>User Name:</td>
				<td>
					<input type="text" name="userName"
						   value="${createCustomerForm.userName}"/>
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
				<td><input type="password" name="confirmPassword" value=""/>
				</td>
				<td>
					<form:errors path="confirmPassword" cssClass="error"/>
				</td>
			</tr>

			<tr>
				<td><span class="red-star">*</span>First Name:</td>
				<td>
					<input type="text" name="firstName"
						   value="${createCustomerForm.firstName}" autofocus/>
				</td>
				<td>
					<form:errors path="firstName" cssClass="error"/>
				</td>
			</tr>

			<tr>
				<td><span class="red-star">*</span>Last Name:</td>
				<td>
					<input type="text" name="lastName"
						   value="${createCustomerForm.lastName}"/>
				</td>
				<td>
					<form:errors path="lastName" cssClass="error"/>
				</td>
			</tr>

			<tr>
				<td><span class="red-star">*</span>Address Line1:</td>
				<td>
					<input type="text" name="addr_line1"
						   value="${createCustomerForm.addr_line1}"/>
				</td>
				<td>
					<form:errors path="addr_line1" cssClass="error"/>
				</td>
			</tr>

			<tr>
				<td>Address Line2:</td>
				<td>
					<input type="text" name="addr_line2"
						   value="${createCustomerForm.addr_line2}"/>
				</td>
			</tr>

			<tr>
				<td><span class="red-star">*</span>City:</td>
				<td>
					<input type="text" name="city"
						   value="${createCustomerForm.city}"/>
				</td>
				<td>
					<form:errors path="city" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td><span class="red-star">*</span>State:</td>
				<td>
					<input type="text" name="state"
						   value="${createCustomerForm.getState()}"/>
				</td>
				<td>
					<form:errors path="state" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td><span class="red-star">*</span>Zip code:</td>
				<td>
					<input type="number" name="zip" step="1"
						   value="${createCustomerForm.zip}"/>
				</td>
				<td>
					<form:errors path="zip" cssClass="error"/>
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
			<tr>
				<td><br/></td>
				<td><br/></td>
			</tr>
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
