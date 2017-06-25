<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ticker | Current Market</title>
</head>
<body>

	<c:if test="${not empty ticker }">
		<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th>Market Name</th>
					<th>Ask</th>
					<th>Bid</th>
					<th>Last</th>
					<th>Date/Time</th>
				</tr>
			</thead>
			<tbody>
				<tr class="text-success">
					<th>${ticker.coin.marketName}</th>
					<th>${ticker.ask}</th>
					<th>${ticker.bid}</th>
					<th>${ticker.last}</th>
					<th>${ticker.timeStamp}</th>
				</tr>
			</tbody>
		</table>
	</c:if>


</body>
</html>