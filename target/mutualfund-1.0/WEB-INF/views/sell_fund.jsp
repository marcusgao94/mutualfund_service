<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Sell Funds</title>
	<style>
		.error {
			color: red;
		}
		.shares {
		}

		.price {
		}
	</style>
</head>

<body>
<div>
	<c:import url="/header"/>
</div>

<div class="container">
	<h3>Sell Funds</h3>
	<form:form method="post" modelAttribute="sellFundForm">
		<table>
			<tr>
				<td> <form:errors path="" cssClass="error" /> </td>
			</tr>
			<tr>
				<td>Fund Ticker:</td>
				<td><input type="text" name="fundTicker"
						   value="${sellFundForm.fundTicker}" autofocus/>
				</td>
				<td><form:errors path="fundTicker" cssClass="error"/></td>
			</tr>

			<tr>
				<td>Enter Quantity of Shares:</td>
				<td><input type="number" step="0.001" name="share"
						   value="${sellFundForm.share}"/></td>
				<td><form:errors path="share" cssClass="error"/></td>
			</tr>

			<tr>
				<th colspan="2">
					<input class="btn btn-default" type="submit" name="button" value="Sell"/>
				</th>
			</tr>
			
			<tr>
				<th colspan="2">
					<br/>
				</th>
			</tr>

		</table>
	</form:form>





<div class="row">
		<div class="col-md-6">
			<table class="table">
				<thead>
				<tr>
					<th>Fund Name</th>
					<th>Fund Ticker</th>
					<th>Quantity of Shares</th>
					<th>Available Shares Quantity</th>
					<th>Price per Share</th>
					<th>Total Value</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="customer_pv" items="${customerPosition}">
					<tr>
					    <td>
					            ${customer_pv.fund.name}
					    </td>
						<td>
								${customer_pv.fund.ticker}
						</td>
						<td class="shares">
								${customer_pv.shares}
						</td>
						<td class="shares">
								${customer_pv.available}
						</td>
						<td class="price">
								${customer_pv.price}
						</td>
						<td class="price">
								${customer_pv.value}
						</td>
					</tr>
				</c:forEach>
				</tbody>
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
    var n = 0;
    for (i = 0; i < p.length; i++) {
        var pr = p[i].innerHTML.trim();
        if (pr)
            p[i].innerHTML = parseFloat(pr).toFixed(2);
        else {
            p[i].innerHTML = n.toFixed(2);
        }
    }
    var s = document.getElementsByClassName("shares");
    for (i = 0; i < s.length; i++) {
        var sh = s[i].innerHTML.trim();
        if (sh)
            s[i].innerHTML = parseFloat(sh).toFixed(3);
        else {
            s[i].innerHTML = n.toFixed(3);
        }
    }
</script>

</body>
</html>
