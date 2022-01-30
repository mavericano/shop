<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sign in</title>
    <script defer src="../resources/assets/js/validateOnRegister.js"></script>
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
    <div id="login-one" class="login-one" style="height: auto">
        <form id="form" class="login-one-form" action="${pageContext.request.contextPath}/pages/controller?command=SIGN_IN" method="POST">
            <div class="col">
                <div class="login-one-ico"><i class="fa fa-unlock-alt" id="lockico" style="color: rgb(38, 157, 157);"></i></div>
                <div class="form-group">
                    <div>
                        <h3 id="heading">${sessionScope.logInLabel}:</h3>
                    </div>
                    <input type="text" class="form-control" name="email" id="email" value="<c:out value="${cookie['rememberMe'].value}"/>" placeholder="Email"/>
                    <input type="password" class="form-control" name="password" id="password" placeholder="Password" />
                    <input class="form-check-input" type="checkbox" value="rememberMe" id="flexCheckDefault" name="rememberMe">
                    <label class="form-check-label" for="flexCheckDefault">
                        ${sessionScope.rememberMeLabel}
                    </label>
                    <button class="btn btn-primary" id="button" style="background-color: rgb(38, 157, 157); margin-top: 10px;" type="submit">${sessionScope.logInLabel}</button>
                    <a class="btn btn-primary" id="button" href="${pageContext.request.contextPath}/pages/controller?command=VIEW_HOME_PAGE" style="background-color: rgb(255, 255, 255); margin-top: 10px; color: rgb(0, 0, 0)" >${sessionScope.cancelLabel}</a>
                    <a id="error" style="color: salmon; font-size: 12px;">
                        <c:out value="${requestScope.message}"/>
                    </a>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
