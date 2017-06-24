<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Market Summaries</title>
</head>
<body>

	<c:if test="${not empty marketSummaries }">
		<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th>#</th>
					<th>Market Name</th>
					<th>Ask</th>
					<th>Bid</th>
					<th>Created</th>
					<th>High</th>
					<th>Low</th>
					<th>Open Buy Orders</th>
					<th>Open Sell Orders</th>
					<th>Previous Day</th>
					<th>Time Stamp</th>
					<th>Volume</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${marketSummaries}" var="item" varStatus="i">
					<tr class="text-success">
						<th><c:out value="${i.index}" /></th>
						<th><c:out value="${item.marketName}" /></th>
						<th><c:out value="${item.ask}" /></th>
						<th><c:out value="${item.bid}" /></th>
						<th><c:out value="${item.created}" /></th>
						<th><c:out value="${item.high}" /></th>
						<th><c:out value="${item.low}" /></th>
						<th><c:out value="${item.openBuyOrders}" /></th>
						<th><c:out value="${item.openSellOrders}" /></th>
						<th><c:out value="${item.prevDay}" /></th>
						<th><c:out value="${item.timeStamp}" /></th>
						<th><c:out value="${item.volume}" /></th>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>


</body>
</html>