<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Hello, template resolver is configured! </h1>
	
		<c:if test="${not empty coins}">
		<table class="table table-hover table-bordered">
                    <thead style="background-color: #ff6600;">
                    <tr > 
                        <th>Market Name</th>
                        <th>Base Currency Long</th>
						                  	
                    </tr>
                    </thead>
                    <tbody  >
                     <c:forEach items="${coins.result}" var="item">
                        <tr class="text-success">
							<th><c:out value="${item.MarketName}"/></th>
							<th><c:out value="${item.BaseCurrencyLong}"/></th> 
							 					                	
                           </tr>
                     </c:forEach>
                    </tbody>
                </table>
		</c:if>
	
	
</body>
</html>