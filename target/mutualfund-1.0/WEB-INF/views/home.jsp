<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Singularity MutualFund</title>
	<style>
	/* img {
    position: relative;
    left: 400px;
	} */
	
	</style>
</head>
<body>
<div>
	<c:import url="/header" />
</div>

<div class="container">

			<h1> Welcome to Singularity Mutual Fund </h1>
			<P>
				<font style = "font-size: large">Current time</font> is
				<%
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					Date date = new Date();
					out.println(df.format(date));
				%>
			</P>
	</div>
	<div class= "container">
		<a><img src="<c:url value="https://techraptor.net/wp-content/uploads/2015/02/singularity.jpg" />" alt="TestDisplay"/></a>
	</div>
	
	

<div>
	<c:import url="bottom.jsp" />
</div>

</body>
</html>
