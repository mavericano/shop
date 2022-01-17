<%--
  Created by IntelliJ IDEA.
  User: haiku
  Date: 14.01.2022
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="../resources/styles/main.css" type="text/css">
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
    <link rel="stylesheet" href="../resources/assets/css/Pretty-Header.css">
    <link rel="stylesheet" href="../resources/assets/css/styles.css">
</head>
<body>
<div>
    <div class="header-dark">
        <nav class="navbar navbar-dark navbar-expand-lg navigation-clean-search" style="background-color: #5b5b5b;">
            <div class="container"><a class="navbar-brand" href="${pageContext.request.contextPath}/controller?command=VIEW_HOME_PAGE">SoundWave</a><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse"
                     id="navcol-1">
                    <ul class="nav navbar-nav">
                        <li class="nav-item" role="presentation"><a class="nav-link" href="${pageContext.request.contextPath}/controller?command=VIEW_ALL_PRODUCTS">Каталог</a></li>
                        <li class="nav-item dropdown"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#">Язык</a>
                            <div class="dropdown-menu" role="menu"><a class="dropdown-item" role="presentation" href="#">Русский</a><a class="dropdown-item" role="presentation" href="#">Английский</a></div>
                        </li>
                    </ul>
                    <form class="form-inline mr-auto" target="_self">
                        <div class="form-group"><label for="search-field"></label>
                            <div class="form-row padMar">
                                <div class="col padMar">
                                    <div class="input-group">
                                        <div class="input-group-prepend"></div>
                                        <input id="search-field" class="form-control autocomplete" type="text" style="margin-left: 5px; margin-top: 10px; height: 30px;">
                                        <div class="input-group-append">
                                            <button class="btn" type="button" style="background-color: rgb(32,143,143); margin-top: 10px; height: 30px;">
                                                <i class="fa fa-search" style="color: rgb(255,255,255);"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <form style="width: 100px;" method="GET">
                        <a class="btn-language" style="padding:0;" href="${pageContext.request.contextPath}/controller?command=CHANGE_LANGUAGE&language=russian&address=${pageContext.request.requestURI}">
                            <c:choose>
                                <c:when test="${sessionScope.language eq 'russian'}">
                                    <img class="btn-language" src="../resources/assets/img/russia.png" alt="russian_flag">
                                </c:when>
                                <c:otherwise>
                                    <img class="btn-language" src="../resources/assets/img/russia-off.png" alt="russian_flag">
                                </c:otherwise>
                            </c:choose>
                        </a>
                        <a class="btn-language" style="padding:0;" href="${pageContext.request.contextPath}/controller?command=CHANGE_LANGUAGE&language=english&address=${pageContext.request.requestURI}">
                            <c:choose>
                                <c:when test="${sessionScope.language eq 'english'}">
                                    <img class="btn-language" src="../resources/assets/img/america.png" alt="american_flag">
                                </c:when>
                                <c:otherwise>
                                    <img class="btn-language" src="../resources/assets/img/america-off.png" alt="american_flag">
                                </c:otherwise>
                            </c:choose>
                        </a>
                    </form>
                    <c:if test="${empty sessionScope.user}">
                        <jsp:useBean id="user" class="by.epamtc.ivangavrilovich.shop.bean.User" scope="session"/>
                        <c:set target="${user}" property="role" value="${4}"/>
                    </c:if>
                    <c:choose>
                        <c:when test="${sessionScope.user.role == 4}">
                            <span class="navbar-text">
                                <a class="login" href="sign_in.jsp">Log In</a>
                            </span>
                            <a class="btn btn-light action-button" role="button" href="register.jsp">Sign Up</a>
                        </c:when>
                        <c:when test="${sessionScope.user.role == 3}">
                            <c:out value="admin"/>
                        </c:when>
                        <c:when test="${sessionScope.user.role == 2}">
                            <c:out value="courier"/>
                        </c:when>
                        <c:when test="${sessionScope.user.role == 1}">
                            <c:out value="basic user"/>
                        </c:when>
                    </c:choose>
                    <%--                    <c:out value="${sessionScope.user.role}"/>--%>
                    <%--                    TODO remove--%>
                    <%--                    <span class="navbar-text"><a class="login" href="sign_in.jsp">Log In</a></span><a class="btn btn-light action-button" role="button" href="register.jsp">Sign Up</a>--%>
                </div>
            </div>
        </nav>
    </div>
</div>
<script src="../resources/assets/js/jquery.min.js"></script>
<script src="../resources/assets/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
