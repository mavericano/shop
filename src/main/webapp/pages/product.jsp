<%--
  Created by IntelliJ IDEA.
  User: haiku
  Date: 14.01.2022
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:useBean id="currentProduct" scope="request" class="by.epamtc.ivangavrilovich.shop.bean.Product"/>
    <title>${currentProduct.name}</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <c:out value="o hi!"/>
    <jsp:include page="footer.jsp"/>
</body>
</html>
