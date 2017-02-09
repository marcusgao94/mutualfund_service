<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success</title>
</head>
<body>

	<div>
		<c:import url="/header" />
	</div>

	<div class="container">

		<h4>Message : ${success}</h4> <br /> <br /> Go back to <a href="<c:url value="/home" />">Home</a>
	</div>
	<div>
		<c:import url="bottom.jsp" />
	</div>

</body>
</html>