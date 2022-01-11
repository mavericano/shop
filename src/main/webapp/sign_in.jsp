<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sign in</title>
    <link rel="stylesheet" href="styles/sign_in.css" type="text/css">
</head>
<body>
<%--    <form class="form-sign-in" action="controller" method="POST">--%>
<%--        <c:out value="${requestScope.message}"/><br/>--%>
<%--        <input type="hidden" name="command" value="SIGN_IN"/><br/>--%>
<%--        <label for="email">Email:</label>--%>
<%--        <input id="email" type="text" name="email"/><br/>--%>
<%--        <label for="password">Password:</label>--%>
<%--        <input id="password" type="password" name="password"/>--%>
<%--        <input type="submit" value="Sign in"/>--%>
<%--    </form>--%>

    <div class="login">
        <div class="heading">
            <h2>Sign in</h2>
            <form action="controller">
                <c:out value="${requestScope.message}"/><br/>
                <input type="hidden" name="command" value="SIGN_IN"/><br/>
                <div class="input-group input-group-lg">
                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                    <input type="text" class="form-control" placeholder="Username or email" name="email">
                </div>

                <div class="input-group input-group-lg">
                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                    <input type="password" class="form-control" placeholder="Password" name="password">
                </div>

                <button type="submit" class="float">Login</button>
            </form>
        </div>
    </div>
</body>
</html>
