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
	<title>Employee view account</title>
	<style>
		.error {
			color: red;
		}

		.shares {
		}

		.price {
		}
	</style>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<%--<link rel="stylesheet"--%>
	<%--href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">--%>
	<%--<link rel="stylesheet"--%>
	<%--href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">--%>

	<%--<!-- link href="../css/searchresults.css" rel="stylesheet" type="text/css"/ -->--%>
	<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>--%>
	<%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>--%>

	<%--<link rel="stylesheet"--%>
	<%--href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">--%>

</head>


<body>
<div>
	<c:import url="/header"/>
</div>

<div class="container">
	<div class="row">
		<form:form method="post" modelAttribute="searchForm" cssClass="form-inline">
			<div class="form-group">
				<label for="sel1">Select User Name:</label>
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

<div class="container">
	<div class="row-fluid">
		<div class="col-sm-12">
			<h2>${employee_customeraccount.firstName} ${employee_customeraccount.lastName}</h2>
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
									<td> ${employee_customeraccount.id}</td>
								</tr>
								<tr>
									<td>First Name:</td>
									<td> ${employee_customeraccount.firstName}</td>
								</tr>
								<tr>
									<td>Last Name:</td>
									<td> ${employee_customeraccount.lastName}</td>
								</tr>
								<tr>
									<td>User Name:</td>
									<td> ${employee_customeraccount.userName}</td>
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
									<td> ${employee_customeraccount.addr_line1} ${employee_customeraccount.addr_line2}</td>
								</tr>
								<tr>
									<td>State:</td>
									<td> ${employee_customeraccount.state}</td>
								</tr>
								<tr>
									<td>City:</td>
									<td> ${employee_customeraccount.city}</td>
								</tr>
								<tr>
									<td>ZIP:</td>
									<td> ${employee_customeraccount.zip}</td>
								</tr>
								</tbody>
							</table>
							<div class="text-right">
								<!--   <button type="button" class="btn btn-primary">Edit</button> -->
								<a href="<c:url value="/employee_changecuspassword/${employee_customeraccount.userName}" />">
									<button class="btn btn-primary">
										Reset password
									</button>
								</a>
								<a href="<c:url value="/deposit_check?un=${employee_customeraccount.userName}" />">
									<button class="btn btn-primary">
										Deposit Check
									</button>
								</a>
								<a href="<c:url value="/employee_searchtransaction?un=${employee_customeraccount.userName}" />">
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
				<h4>Cash Balance: <span class="price">${employee_customeraccount.cash}</span></h4>
				<h4>Available Cash:
					<span class="price">
						${employee_customeraccount.cash - employee_customeraccount.pendingCashDecrease}
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
							<c:forEach var="employee_cpv" items="${employee_customerpositionvalue}">
								<tr>
									<td>
											${employee_cpv.fund.name}
									</td>
									<td>
											${employee_cpv.fund.ticker}
									</td>
									<td class="shares">
											${employee_cpv.shares}
									</td>
									<td class="price">
											${employee_cpv.price}
									</td>
									<td class="price">
											${employee_cpv.value}
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div><!--table-responsive close-->
				</div><!--col-md-6 close-->
				<!-- pie chart -->
				<div class="col-md-4">

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
                            <c:forEach var="employee_cpv" items="${employee_customerpositionvalue}">
                            data.addRows([
                                ['${employee_cpv.fund.name}', ${employee_cpv.shares}]
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