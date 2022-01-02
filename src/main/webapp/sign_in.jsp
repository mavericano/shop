<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
    <form class="form-sign-in" action="controller" method="POST">
        <c:out value="${requestScope.message}"/><br/>
        <input type="hidden" name="command" value="SIGN_IN"/><br/>
        <label for="email">Email:</label>
        <input id="email" type="text" name="email"/><br/>
        <label for="password">Password:</label>
        <input id="password" type="password" name="password"/>
        <input type="submit" value="Sign in"/>
    </form>
</body>
</html>
