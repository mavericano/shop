<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div style="position: absolute; top: 50%; left: 50%;">
    <h1><%= exception.getMessage() %></h1>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
