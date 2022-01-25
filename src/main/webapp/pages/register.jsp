<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <script defer src="validateOnRegister.js"></script>
</head>

<body>
<%--TODO i18n--%>
    <div id="login-one" class="login-one">
        <form id="form" novalidate class="login-one-form" style="height: 471px;" action="${pageContext.request.contextPath}/pages/controller?command=REGISTER" method="POST">
            <div class="col">
                <div class="login-one-ico"><i class="fa fa-pencil" id="lockico" style="color: rgb(38, 157, 157);"></i></div>
                <h3 id="heading" style="margin: 10px;">Sign up:</h3>
                <input type="text" class="form-control" name="number" id="number" placeholder="Phone number" />
                <input type="text" class="form-control" name="email" id="email" placeholder="Email" />
                <div class="form-group">
                    <div></div>
                    <input type="password" class="form-control" name="password" id="password" placeholder="Password" />
                    <input type="password" class="form-control" name="second-password" id="second-password" placeholder="Confirm password" />
                    <button class="btn btn-primary" id="button" style="background-color: rgb(38, 157, 157); margin-top: 10px;" type="submit">Sign up</button>
                    <a class="btn btn-primary" id="button" href="${pageContext.request.contextPath}/pages/controller?command=VIEW_HOME_PAGE" style="background-color: rgb(255, 255, 255); margin-top: 10px; color: rgb(0, 0, 0)" >Cancel</a>
                    <a id="error" style="color: salmon; font-size: 12px;">
                        <c:out value="${requestScope.message}"/>
                    </a>
                </div>
            </div>
        </form>
    </div>
</body>

</html>
