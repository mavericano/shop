<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Welcome</title>

    <link rel="stylesheet" href="styles/bootstrap.min.css">
    <link rel="stylesheet" href="styles/main.css" type="text/css">
</head>
<body>
<div class="content">
    <div class="col-1">z gbljhfc</div>
    <div class="col-2">
        <div class="header">
            <c:if test="${empty sessionScope.user}">
                <jsp:useBean id="user" class="by.epamtc.ivangavrilovich.shop.bean.User" scope="session"/>
                <c:set target="${user}" property="role" value="${4}"/>
            </c:if>
            <c:choose>
                <c:when test="${sessionScope.user.role == 4}">
                    <a href="sign_in.jsp" class="btn btn-sm text-uppercase itd_sign_in">sign in</a>
                    <a class="sign_in" href="sign_in.jsp">Sign in</a><br/>
                    <a href="register.jsp">Sign up</a>
                </c:when>
            </c:choose>
            <c:out value="${sessionScope.user.role}"/>
        </div>
        <div class="lox">
            <c:out value="${sessionScope.user}"/>
        </div>
        <div class="lox">2</div>
        <div class="lox">3</div>
        <div class="lox">4</div>
        <div class="lox">5</div>
        <div class="lox">6</div>
        <div class="lox">7</div>
    </div>
</div>
<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
