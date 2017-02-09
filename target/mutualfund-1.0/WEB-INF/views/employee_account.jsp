<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Employee account</title>
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
	<div class="row-fluid">
		<div class="col-sm-12">
			<h2>${employee.userName}</h2>
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
									<td>First Name:</td>
									<td> ${employee.firstName}</td>
								</tr>
								<tr>
									<td>Last Name:</td>
									<td> ${employee.lastName}</td>
								</tr>
								<tr>
									<td>User Name:</td>
									<td> ${employee.userName}</td>
								</tr>
								<tr>
									<td>Position: &nbsp;</td>
									<td> Employee </td>
								</tr>
								</tbody>
							</table>
						</div><!--table-responsive close-->
					</div><!--col-md-6 close-->
					</div>
					</div>
					</div>
					</div>
					</div>
					

</body>
</html>