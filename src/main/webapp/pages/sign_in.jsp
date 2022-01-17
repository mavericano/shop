<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sign in</title>
    <link rel="stylesheet" href="../resources/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bitter:400,700">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Cookie">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="../resources/assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="../resources/assets/fonts/ionicons.min.css">
    <link rel="stylesheet" href="../resources/assets/css/Footer-Clean.css">
    <link rel="stylesheet" href="../resources/assets/css/Footer-Dark.css">
    <link rel="stylesheet" href="../resources/assets/css/Header-Blue.css">
    <link rel="stylesheet" href="../resources/assets/css/Header-Dark.css">
    <link rel="stylesheet" href="../resources/assets/css/Login-screen.css">
    <link rel="stylesheet" href="../resources/assets/css/Pretty-Header.css">
    <link rel="stylesheet" href="../resources/assets/css/styles.css">

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

    <div id="login-one" class="login-one" style="height: auto">
        <form class="login-one-form" action="${pageContext.request.contextPath}/controller?command=SIGN_IN" method="POST">
            <div class="col">
                <div class="login-one-ico"><i class="fa fa-unlock-alt" id="lockico" style="color: rgb(38, 157, 157);"></i></div>
                <div class="form-group">
                    <div>
                        <h3 id="heading">Log in:</h3>
                    </div>
                    <input type="text" class="form-control" name="email" placeholder="Email" />
                    <input type="password" class="form-control" name="password" placeholder="Password" />
                    <button class="btn btn-primary" id="button" style="background-color: rgb(38, 157, 157); margin-top: 10px;" type="submit">Log in</button>
                    <button class="btn btn-primary" id="button" href="${pageContext.request.contextPath}/controller?command=VIEW_HOME_PAGE" style="background-color: rgb(255, 255, 255); margin-top: 10px; color: rgb(0, 0, 0)" >Cancel</button>
                </div>
            </div>
        </form>
    </div>

<%--    <div class="login">--%>
<%--        <div class="heading">--%>
<%--            <h2>Sign in</h2>--%>
<%--            <form action="controller">--%>
<%--                <c:out value="${requestScope.message}"/><br/>--%>
<%--                <input type="hidden" name="command" value="SIGN_IN"/><br/>--%>
<%--                <div class="input-group input-group-lg">--%>
<%--                    <span class="input-group-addon"><i class="fa fa-user"></i></span>--%>
<%--                    <input type="text" class="form-control" placeholder="Username or email" name="email">--%>
<%--                </div>--%>

<%--                <div class="input-group input-group-lg">--%>
<%--                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>--%>
<%--                    <input type="password" class="form-control" placeholder="Password" name="password">--%>
<%--                </div>--%>

<%--                <button type="submit" class="float">Login</button>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>
</body>
</html>
