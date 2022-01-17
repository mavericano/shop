<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Sign up</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--    <form method="post" action="controller">--%>
<%--        <c:out value="${requestScope.message}"/><br/>--%>
<%--        <input type="hidden" name="command" value="REGISTER"/>--%>
<%--        <label for="email">Email</label>--%>
<%--        <input id="email" type="text" name="email"/><br/>--%>
<%--        <label for="password">Password</label>--%>
<%--        <input id="password" type="password" name="password"/><br/>--%>
<%--        <label for="address">Address</label>--%>
<%--        <input id="address" type="text" name="defaultAddress"/><br/>--%>
<%--        <input type="submit" value="Register"/>--%>
<%--    </form>--%>
<%--</body>--%>
<%--</html>--%>
<html>

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no" />
    <title>Sign up</title>
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
    <div id="login-one" class="login-one">
        <form class="login-one-form" style="height: 471px;" action="${pageContext.request.contextPath}/controller?command=REGISTER" method="POST">
            <div class="col">
                <div class="login-one-ico"><i class="fa fa-pencil" id="lockico" style="color: rgb(38, 157, 157);"></i></div>
                <h3 id="heading" style="margin: 10px;">Sign up:</h3>
                <input type="text" class="form-control" name="number" placeholder="Phone number" />
                <input type="text" class="form-control" name="email" placeholder="Email" />
                <div class="form-group">
                    <div></div>
                    <input type="password" class="form-control" name="password" placeholder="Password" />
                    <button class="btn btn-primary" id="button" style="background-color: rgb(38, 157, 157); margin-top: 10px;" type="submit">Log in</button>
                    <button class="btn btn-primary" id="button" href="${pageContext.request.contextPath}/controller?command=VIEW_HOME_PAGE" style="background-color: rgb(255, 255, 255); margin-top: 10px; color: rgb(0, 0, 0)" >Cancel</button>
                </div>
            </div>
        </form>
    </div>
</body>

</html>
