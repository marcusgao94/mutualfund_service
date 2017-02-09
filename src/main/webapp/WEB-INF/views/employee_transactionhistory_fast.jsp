<%--
  Created by Eclipse IDEA.
  User: jev
  Date: 17/1/23
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Employee transaction history</title>
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

	<h3>Transaction History for ${userName}</h3>
	
	<br/><br/>
<h4> Pending </h4>

	<div class="row">
		<div class="col-md-12">
			<table class="table">
	<thead>
		<tr>
					<th>Transaction ID</th>
					<th>Fund Ticker</th>
					<th>Fund Name</th>
					<th>Price Per Share</th>
					<th>Quantity of Shares</th>
					<th>Total Amount</th>
          			<th>Operations</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="employee_pendingtransaction" items="${employee_pendingTransaction}">
			<tr>
<td>
							${employee_pendingtransaction.id}
					</td>
					<td>
							${employee_pendingtransaction.fund.ticker}
					</td>
					<td>
							${employee_pendingtransaction.fund.name}
					</td>
					<td class="price">
	 						${employee_pendingtransaction.price}
					</td>
					<td class="shares">
							${employee_pendingtransaction.shares}
					</td>
					<td class="price">
							${employee_pendingtransaction.amount}
					</td>
					<td>
							${employee_pendingtransaction.type}
					</td>
			</tr>
		</c:forEach>
	<tbody>
</table>
</div>
</div>

<h4> Finished </h4>
	<div class="row">
		<div class="col-md-12">
			<table class="table">
	<thead>
		<tr>
					<th>Transition Date</th>
					<th>ID</th>
					<th>Fund Ticker</th>
					<th>Fund Name</th>
					<th>Price Per Share</th>
					<th>Quantity of Shares</th>
					<th>Total Amount</th>
          			<th>Operations</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="employee_finishtransaction" items="${employee_finishTransaction}">
			<tr>
					<td>
							${employee_finishtransaction.executeDate}
					</td>
					<td>
	 						${employee_finishtransaction.id}
					</td>
					<td>
							${employee_finishtransaction.fund.ticker}
					</td>					
					<td>
							${employee_finishtransaction.fund.name}
					</td>
					<td class="price">
	 						${employee_finishtransaction.price}
					</td>
					<td class="shares">
							${employee_finishtransaction.shares}
					</td>
					<td class="price">
							${employee_finishtransaction.amount}
					</td>
					<td>
							${employee_finishtransaction.type}
					</td>
			</tr>
		</c:forEach>
	<tbody>
</table>
</div>
</div>

</div>
<div>
	<c:import url="bottom.jsp"/>
</div>

<script type="text/javascript">
    var p = document.getElementsByClassName("price");
    var i;
    for (i = 0; i < p.length; i++) {
        var pr = p[i].innerHTML.trim();
        if (pr)
            p[i].innerHTML = parseFloat(pr).toFixed(2);
        /*
        else {
            var n = 0;
            p[i].innerHTML = n.toFixed(2);
        }
        */
    }

    var s = document.getElementsByClassName("shares");
    for (i = 0; i < s.length; i++) {
        var sh = s[i].innerHTML.trim();
        if (sh)
            s[i].innerHTML = parseFloat(sh).toFixed(3);
        /*
        else {
            var n = 0;
            s[i].innerHTML = n.toFixed(3);
        }
        */
    }
</script>
</body>
</html>
