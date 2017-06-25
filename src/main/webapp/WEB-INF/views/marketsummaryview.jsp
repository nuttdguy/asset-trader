<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Market Summary</title>
</head>
<body>

	<c:if test="${not empty marketSummary }">
		<table class="table table-hover table-bordered">
			<thead>
				<tr>
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
				<tr class="text-success">
					<th>${marketSummary.marketName}</th>
					<th>${marketSummary.ask}</th>
					<th>${marketSummary.bid}</th>
					<th>${marketSummary.created}</th>
					<th>${marketSummary.high}</th>
					<th>${marketSummary.low}</th>
					<th>${marketSummary.openBuyOrders}</th>
					<th>${marketSummary.openSellOrders}</th>
					<th>${marketSummary.prevDay}</th>
					<th>${marketSummary.timeStamp}</th>
					<th>${marketSummary.volume}</th>
				</tr>
			</tbody>
		</table>
	</c:if>


</body>
</html>