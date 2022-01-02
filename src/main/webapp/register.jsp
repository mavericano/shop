<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
    <form method="post" action="controller">
        <c:out value="${requestScope.message}"/><br/>
        <input type="hidden" name="command" value="REGISTER"/>
        <label for="email">Email</label>
        <input id="email" type="text" name="email"/><br/>
        <label for="password">Password</label>
        <input id="password" type="password" name="password"/><br/>
        <label for="address">Address</label>
        <input id="address" type="text" name="defaultAddress"/><br/>
        <input type="submit" value="Register"/>
    </form>
</body>
</html>
