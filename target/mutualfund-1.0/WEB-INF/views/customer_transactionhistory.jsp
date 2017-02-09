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
	<title>Customer transaction history</title>
	<style>
		.error {
			color: red;
		}
		.shares {}
		.price {}
	</style>
</head>
<body>
<div>
	<c:import url="/header"/>
</div>


<div class="container">

	<h3>Customer Transaction History</h3>
<h4> Pending </h4>
	<div class="row">
		<div class="col-md-12">
			<table class="table">
					<thead>
					<tr>
			<th>Transaction ID</th>
			<th>Fund Ticker</th>
			<th>Fund Name</th>
			<th>Price per Sare</th>
			<th>Quantity of Shares</th>
			<th>Total Amount</th>
          	<th>Operations</th>
					</tr>
					</thead>
					<tbody>
<c:forEach var="customer_pendingtransaction" items="${customer_pendingTransaction}">
			<tr>
				<td>
	 				${customer_pendingtransaction.id}
				</td>
				<td>
	 				${customer_pendingtransaction.fund.ticker}
				</td>
				<td>
	 				${customer_pendingtransaction.fund.name}
				</td>
				<td class="price">
	 				${customer_pendingtransaction.price}
				</td>
				<td class="shares">
	 				${customer_pendingtransaction.shares}
				</td>
				<td class="price">
	 				${customer_pendingtransaction.amount}
				</td>
				<td>
	 				${customer_pendingtransaction.type}
				</td>
			</tr>
		</c:forEach>
					</tbody>
				</table>
</div>
</div>

<h4> Finished </h4>
	<div class="row">
		<div class="col-md-12">
			<table class="table">
	<thead>
		<tr>
			<th>Transaction Date</th>
			<th>Transaction ID</th>
			<th>Fund Ticker</th>
			<th>Fund Name</th>
			<th>Price per Share</th>
			<th>Quantity of Shares</th>
			<th>Total Amount</th>
          	<th>Operations</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="customer_finishtransaction" items="${customer_finishTransaction}">
			<tr>
				<td>
	 				${customer_finishtransaction.executeDate}
	 			
				</td>
				<td>
	 				${customer_finishtransaction.id}
				</td>
				<td>
	 				${customer_finishtransaction.fund.ticker}
				</td>
				<td>
	 				${customer_finishtransaction.fund.name}
				</td>
				<td class="price">
	 				${customer_finishtransaction.price}
				</td>
				<td class="shares">
	 				${customer_finishtransaction.shares}
				</td>
				<td class="price">
	 				${customer_finishtransaction.amount}
				</td>
				<td>
	 				${customer_finishtransaction.type}
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
        	s[i].innerHTML = s[i].innerHTML = parseFloat(sh).toFixed(3);
        /*
        else {
            var n = 0;
            s[i].innerHTML = s[i].innerHTML = parseFloat(n).toFixed(3);
		}
		*/
    }
</script>

</body>
</html>
