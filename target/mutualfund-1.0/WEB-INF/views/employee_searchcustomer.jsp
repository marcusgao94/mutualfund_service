<%--
  Created by Eclipse IDEA.
  User: JevWang
  Date: 17/1/23
  Time: 下午6:32
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Employee view Customer account</title>
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
	<div class="row">
		<form:form method="post" modelAttribute="searchForm" cssClass="form-inline">
			<div class="form-group">
				<label for="sel1">Select a Customer:</label>
				<select name="userName" class="form-control" id="sel1">
					<option value="">Please select a name and then press the
						button
					</option>
					<c:forEach var="sessionUser" items="${userList}">
						<option>${sessionUser.userName}</option>
					</c:forEach>
				</select>
				<form:errors path="userName" cssClass="error"/>
			</div>
			<button class="btn btn-default" type="submit">
				<span class="glyphicon glyphicon-search"></span>
			</button>
		</form:form>

	</div>
</div>


<div>
	<c:import url="bottom.jsp"/>
</div>

</body>
</html>
