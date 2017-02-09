<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet"
	  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	  


<body>
<header role="banner" id="top" class="navbar navbar-static-top">
	<nav role="navigation" class="navbar navbar-default">


		<div class="container">

			<div class="navbar-header">
				<button data-target=".navbar-collapse" data-toggle="collapse"
						class="navbar-toggle" type="button">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<div class="animbrand"><a href="<c:url value="/home" />" class="navbar-brand"><span
						style="font-weight:bold">Singularity</span></a>
				</div>
			</div>

			<div class="collapse navbar-collapse navbar-collapse">
				<ul class="nav navbar-nav">


					<li class="">
						<a href="<c:url value="/home" />">
							<span class="glyphicon glyphicon-home"></span> Home
						</a>
					</li>
					
					
					<c:if test="${sessionUser.type == -1}">
					<li class = "dropdown">	
						<li class="">
							<a href="<c:url value="/customer_login" />">
								<span class="fa fa-sessionUser-circle"></span>
								Customer Login
							</a>
						</li>
						<li class="">
							<a href="<c:url value="/employee_login" />">
								<span class="fa fa-sessionUser-circle-o"></span>
								Employee Login
							</a>
						</li>					
					</li>
					</c:if>

					<li class="dropdown">

						<c:if test="${sessionUser.type == 0}">
							<a data-toggle="dropdown" class="dropdown-toggle"
							   href="#">
								<span class="glyphicon glyphicon glyphicon-usd"></span>
								Investing & Trading <b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li class="">
									<a href="<c:url value="/buy_fund" />">
										<span class="glyphicon glyphicon-usd"></span>
										Buy Funds
									</a>
								</li>
								<li class="">
									<a href="<c:url value="/sell_fund" />">
										<span class="glyphicon glyphicon-usd"></span>
										Sell Funds
									</a>
								</li>
							</ul>
						</c:if>
					</li>

					<c:if test="${sessionUser.type == 1}">
						<li class="dropdown">

							<a data-toggle="dropdown" class="dropdown-toggle"
							   href="#">
								<span class="glyphicon glyphicon-sessionUser"></span>
								Manage Employees <b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li class="">
									<a href="<c:url value="/employee_register" />">
										<span class="fa fa-sessionUser-plus"></span>
										Create An Employee
									</a>
								</li>
								<li class="">
									<a href="<c:url value="/employee_searchemployee" />">
										<span class="fa fa-drivers-license"></span>
										View Employee Account
									</a>
								</li>
							</ul>


						<li class="dropdown">
							<a data-toggle="dropdown" class="dropdown-toggle"
							   href="#">
								<span class="glyphicon glyphicon-sessionUser"></span>
								Manage Customers<b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="<c:url value="/customer_register" />">
										<span class="fa fa-users"></span>
										Create A Customer
									</a>
								</li>
								<li>
									<a href="<c:url value="/employee_searchcustomer" />">
										<i class="fa fa-drivers-license"></i>
										View Customer Account
									</a>
								</li>

								<li>
									<a href="<c:url value="/employee_searchtransaction" />">
										<i class="fa fa-money"></i>
										View Customer Transaction History
									</a>
								</li>

								<li>
									<a href="<c:url value="/deposit_check" />">
										<i class="fa fa-credit-card"></i>
										Deposit Check
									</a>
								</li>
							</ul>
						</li>

						<li class="dropdown">
							<a data-toggle="dropdown" class="dropdown-toggle"
							   href="#">
								<span class="glyphicon glyphicon-sessionUser"></span>
								Manage Funds<b class="caret"></b>
							</a>

							<ul class="dropdown-menu">
								<li>
									<a href="<c:url value="/employee_createfund" />">
										<i class="fa fa-plus"></i>
										Create Fund
									</a>
								</li>
							</ul>

						<li>
							<a data-toggle="dropdown" class="dropdown-toggle"
							   href="#">
								<span class="glyphicon glyphicon-sessionUser"></span>
								Set Transition Day<b class="caret"></b>
							</a>

							<ul class="dropdown-menu">
								<li>
									<a href="<c:url value="/transitionday" />">
										<i class="fa fa-legal"></i>
										Set Transition Day
									</a>
								</li>
							</ul>
						</li>

					</c:if>

				<c:if test="${sessionUser.type == 0}">
					<li class="dropdown">
							<a data-toggle="dropdown" class="dropdown-toggle"
							   href="#">
								<span class="fa fa-area-chart"></span>
								Research Funds <b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li class="">
									<a href="<c:url value="/customer_researchfund"/>">
										<span class="glyphicon glyphicon-search"></span>
										View Fund Analysis
									</a>
								</li>
							</ul>
						</li>
						
						<li class="dropdown">
							<a data-toggle="dropdown" class="dropdown-toggle"
							   href="#">
								<span class="fa fa-address-card"></span>
								My Account <b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="<c:url value="/customer_viewaccount" />">
										<i class="fa fa-drivers-license"></i>
										View My Account
									</a>
									<a href="<c:url value="/customer_changepassword" />">
										<i class="fa fa-key"></i>
										Change Password
									</a>
									<a href="<c:url value="/request_check" />">
										<i class="fa fa-credit-card"></i>
										Request Check
									</a>
									<a href="<c:url value="/customer_transactionhistory" />">
										<i class="fa fa-search"></i>
										View Transaction History
									</a>
								</li>
							</ul>
						</li>
					</c:if>
				</ul>


				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a data-toggle="dropdown" class="dropdown-toggle">
							<small>Welcome,</small>
							${sessionUser.userName} <b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
							<c:if test="${sessionUser.type == -1}">
								<li>
									<a href="<c:url value="/employee_login" />">
										<i class="fa fa-sessionUser-circle-o"></i>
										Login as employee
									</a>
								</li>
								<li>
									<a href="<c:url value="/customer_login" />">
										<i class="fa fa-sessionUser-circle"></i>
										Login as sessionUser
									</a>
								</li>
							</c:if>

						
							<c:if test="${sessionUser.type == 1}">
							<li>
								<a href="<c:url value="/employee_changepassword" />">
									<i class="fa fa-key"></i>
									Change Password
								</a>
								</c:if>


								<c:if test="${sessionUser.type != -1}">
							<li>
								<a href="<c:url value="/logout" />">
									<i class="glyphicon glyphicon-off"></i>
									Logout
								</a>
							</li>
							</c:if>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>
</header>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
