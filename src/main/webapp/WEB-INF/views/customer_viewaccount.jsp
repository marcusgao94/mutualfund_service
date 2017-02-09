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
	<title>Customer view account</title>
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
	<div class="row-fluid">
		<div class="col-sm-12">
			<h2>${customer_account.firstName} ${customer_account.lastName}</h2>
			<div class="panel panel-default">
				<div class="panel-heading">
					<span itemscope itemtype="http://schema.org/Review">
            			<h3 class="panel-title" itemprop="name">Basic Info</h3>
					</span>
				</div><!--/panel-heading-->
				<div class="panel-body" itemprop="reviewBody">

					<div class="col-md-6">

						<div class="table-responsive responsiv-table">
							<table class="table bio-table">
								<tbody>
								<tr>
									<td>ID:</td>
									<td> ${customer_account.id}</td>
								</tr>
								<tr>
									<td>First Name:</td>
									<td> ${customer_account.firstName}</td>
								</tr>
								<tr>
									<td>Last Name:</td>
									<td> ${customer_account.lastName}</td>
								</tr>
								<tr>
									<td>User Name:</td>
									<td> ${customer_account.userName}</td>
								</tr>
								</tbody>
							</table>
						</div><!--table-responsive close-->
					</div><!--col-md-6 close-->

					<div class="col-md-6">
						<div class="table-responsive responsiv-table">
							<table class="table bio-table">
								<tbody>
								<tr>
									<td>Address:</td>
									<td> ${customer_account.addr_line1} ${customer_account.addr_line2}</td>
								</tr>
								<tr>
									<td>State:</td>
									<td> ${customer_account.state}</td>
								</tr>
								<tr>
									<td>City:</td>
									<td> ${customer_account.city}</td>
								</tr>
								<tr>
									<td>ZIP:</td>
									<td> ${customer_account.zip}</td>
								</tr>
								</tbody>
							</table>
							<div class="text-right">
								<!--   <button type="button" class="btn btn-primary">Edit</button> -->
								<a href="<c:url value="/customer_changepassword" />">
									<button class="btn btn-primary">
										Change Password
									</button>
								</a>
								<a href="<c:url value="/customer_transactionhistory" />">
									<button class="btn btn-primary">
										View Transaction History
									</button>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="col-sm-12">
		<div class="panel panel-default">
			<div class="panel-heading">
                    <span itemscope itemtype="http://schema.org/Review">
                    <h3 class="panel-title" itemprop="name">Account Info</h3>
					</span>
			</div><!--/panel-heading-->
			<div class="panel-body" itemprop="reviewBody">
				<h4>Date of the last trading day: ${date} </h4>
				<h4>Cash Balance: <span class="price"> ${customer_account.cash} </span></h4>
				<h4>Available Cash:
					<span class="price">
						${customer_account.cash - customer_account.pendingCashDecrease}
					</span>
				</h4>

				<div class="col-md-6">
					<div class="table-responsive responsiv-table">
						<table class="table bio-table">
							<thead>
							<tr>
								<td>Fund Name</td>
								<td>Fund Ticker</td>
								<td>Quantity of Shares</td>
								<td>Price per Share</td>
								<td>Total Value</td>
							</tr>
							</thead>
							<c:forEach var="customer_cpv" items="${customerPosition}">
								<tr>
									<td>
											${customer_cpv.fund.name}
									</td>
									<td>
											${customer_cpv.fund.ticker}
									</td>
									<td class="shares">
											${customer_cpv.shares}
									</td>
									<td class="price">
											${customer_cpv.price}
									</td>
									<td class="price">
											${customer_cpv.value}
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div><!--table-responsive close-->
				</div><!--col-md-6 close-->
				<!-- pie chart -->
				<div class="col-md-4">
					<head>
						<script type="text/javascript"
								src="https://www.gstatic.com/charts/loader.js"></script>
						<script type="text/javascript">
                            google.charts.load('current', {'packages': ['corechart']});
                            google.charts.setOnLoadCallback(drawChart);
                            function drawChart() {
                                // Define the chart to be drawn.
                                var data = new google.visualization.DataTable();
                                data.addColumn('string', 'Element');
                                data.addColumn('number', 'Percentage');
                                <c:forEach var="customer_pv" items="${customerPosition}">
                                data.addRows([


                                    ['${customer_pv.fund.name}', ${customer_pv.shares}]

                                ]);
                                </c:forEach>
                                var options = {
                                    title: 'My Fund Collection'
                                };
                                // Instantiate and draw the chart.
                                var chart = new google.visualization.PieChart(document.getElementById('pieChart'));
                                chart.draw(data, options);
                            }

						</script>
					</head>
					<body>
					<div id="pieChart" style="width: 530px; height: 300px;"></div>
					</body>

				</div>
			</div>
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