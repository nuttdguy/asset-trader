<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Book</title>
</head>
<body>

	<h1>Order Book</h1>

	<c:if test="${not empty orderbooks}">
		<table class="table table-hover table-bordered">
			<thead style="background-color: #ff6600;">
				<tr>
					<th>Quantity</th>
					<th>Rate</th>
					<th>Order Time</th>
					<th>Order Type</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orderbooks}" var="item">
					<tr class="text-success">
						<th><c:out value="${item.quantity}" /></th>
						<th><c:out value="${item.rate}" /></th>
						<th><c:out value="${item.orderBookDateTime}" /></th>
						<th><c:out value="${item.orderType}" /></th>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>


</body>
</html>