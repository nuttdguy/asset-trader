<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Market History</title>
</head>
<body>

	<c:if test="${not empty marketHistory }">
		<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th>id</th>
					<th>fill type</th>
					<th>order type</th>
					<th>quantity</th>
					<th>price</th>
					<th>total</th>
					<th>time stamp</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${marketHistory}" var="item" varStatus="i" >
					<tr class="text-success">
						<th><c:out value="${item.id}" /></th>
						<th><c:out value="${item.fillType}" /></th>
						<th><c:out value="${item.orderType}" /></th>
						<th><c:out value="${item.quantity}" /></th>
						<th><c:out value="${item.price}" /></th>
						<th><c:out value="${item.total}" /></th>
						<th><c:out value="${item.timeStamp}" /></th>
					</tr>				
				</c:forEach>				
			</tbody>
		</table>
	</c:if>

</body>
</html>