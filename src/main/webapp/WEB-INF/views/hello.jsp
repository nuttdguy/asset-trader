<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Hello, template resolver is configured!</h1>

	<c:if test="${not empty coinList}">
		<table class="table table-hover table-bordered">
			<thead style="background-color: #ff6600;">
				<tr>
					<th>Market Name</th>
					<th>Base Currency</th>
					<th>Base Currency Long</th>
					<th>Market Currency</th>
					<th>Market Currency Long</th>
					<th>Create Date</th>
					<th>Logo</th>
					<th>Min Trade Size</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${coinList}" var="item">
					<tr class="text-success">
						<th><c:out value="${item.marketName}" /></th>
						<th><c:out value="${item.baseCurrency}" /></th>
						<th><c:out value="${item.baseCurrencyLong}" /></th>
						<th><c:out value="${item.marketCurrency}" /></th>
						<th><c:out value="${item.marketCurrencyLong}" /></th>
						<th><c:out value="${item.createDate}" /></th>
						<th><img 
							style="height: 44px; width: 44px;" 
							src=<c:out value="${item.logoUrl}"/> />
						</th>
						<th><c:out value="${item.minTradeSize}" /></th>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>


</body>
</html>