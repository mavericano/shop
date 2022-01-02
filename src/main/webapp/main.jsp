<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css">
</head>
<body>
<div class="content">
    <div class="col-1">z gbljhfc</div>
    <div class="col-2">
        <div class="header">
            <a class="sign_in" href="sign_in.jsp">Sign in</a><br/>
            <a href="register.jsp">Sign up</a>
        </div>
        <div class="lox">
            <c:out value="${sessionScope.currentUser}"/>
        </div>
        <div class="lox">2</div>
        <div class="lox">3</div>
        <div class="lox">4</div>
        <div class="lox">5</div>
        <div class="lox">6</div>
        <div class="lox">7</div>
    </div>
</div>
</body>
</html>
