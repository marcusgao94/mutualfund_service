<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Buy Funds</title>
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
	<h3>Buy Funds</h3>
	<form:form method="post" modelAttribute="buyFundForm">
		<table>
			<tr>
				<td><form:errors path="" cssClass="error"/></td>
			</tr>
			<tr>
				<td>Fund Ticker:</td>
				<td><input type="text" name="fundTicker" value="${buyFundForm.fundTicker}"
						   placeholder="1-5 alphabets" />
				</td>
				<td><form:errors path="fundTicker" cssClass="error"/></td>
			</tr>

			<tr>
				<td>Available Cash Amount:</td>
				<td class="price">${buyFundForm.available}</td>
				<td><input type="hidden" step="0.01" name="available"
						   value="${buyFundForm.available}"/>
				<td><form:errors path="available" cssClass="error"/></td>
			</tr>

			<tr>
				<td>Enter Buy Amount:</td>
				<td><input type="number" step="0.01" name="amount"
						   value="${buyFundForm.amount}"/></td>
				<td><form:errors path="amount" cssClass="error"/></td>
			</tr>

			<tr>
				<th colspan="2">
					<input class="btn btn-default" type="submit" name="button" value="Buy"/>
				</th>
			</tr>
			<tr>
				<th>
					<br/>
				</th>
			</tr>

		</table>
	</form:form>

	<h3>My Funds</h3>

	<div class="row">
		<div class="col-md-6">
			<table class="table">
				<thead>
				<tr>
					<th>Fund Name</th>
					<th>Fund Ticker</th>
					<th>Quantity of Shares</th>
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

	<h3>Available Funds</h3>

	<div class="row">
		<div class="col-md-6">
			<table class="table">
				<thead>
				<tr>
					<th>Fund Ticker</th>
					<th>Fund Name</th>
				</tr>
				</thead>
				<tbody>

				<c:forEach var="fund" items="${fundList}">

					<tr>
						<td>
								${fund.ticker}
						</td>
						<td>
								${fund.name}
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
