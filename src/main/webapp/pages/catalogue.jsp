<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%--    <title>Title</title> TODO fix --%>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="container" style="height: 500px;">
        <c:out value="products list"/>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
