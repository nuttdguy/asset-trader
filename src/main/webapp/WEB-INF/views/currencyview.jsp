<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Currencies</title>
</head>
<body>

	<c:if test="${not empty currencyList }">
		<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th>#</th>
					<th>currency long</th>
					<th>min confirmation</th>
					<th>transaction fee</th>
					<!-- <th>is active</th> -->
					<th>coin type</th>
					<th>base address</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${currencyList}" var="item" varStatus="i" >
					<tr class="text-success">
						<th><c:out value="${i.index}" /></th>
						<th><c:out value="${item.currencyLong}" /></th>
						<th><c:out value="${item.minConfirmation}" /></th>
						<th><c:out value="${item.txFee}" /></th>
						<%-- <th><c:out value="${item.isActive}" /></th> --%>
						<th><c:out value="${item.coinType}" /></th>
						<th><c:out value="${item.baseAddress}" /></th>
					</tr>				
				</c:forEach>				
			</tbody>
		</table>
	</c:if>

</body>
</html>