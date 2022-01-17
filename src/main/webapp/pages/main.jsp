<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Welcome</title>

<%--    <link rel="stylesheet" href="styles/bootstrap.min.css">--%>
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
<jsp:include page="header.jsp"/>
<div class="container" style="height: 500px;">
    <c:out value="${sessionScope.user}"/><br/>
<%--    <c:set var="includedPage" scope="request" value="${'singleProductPage'}"/>--%>
<%--    <c:set target="${requestScope.includedPage}" value="${'singleProductPage'}"/>--%>
    <c:choose>
        <c:when test="${requestScope.includedPage eq 'popularProducts'}">
            <c:out value="${sessionScope.popularProducts}"/>
        </c:when>
        <c:when test="${requestScope.includedPage eq 'userPage'}">
            <c:out value="user page"/>
        </c:when>
        <c:when test="${requestScope.includedPage eq 'productsPage'}">
            <c:out value="products page"/>
        </c:when>
        <c:when test="${requestScope.includedPage eq 'singleProductPage'}">
            <jsp:include page="product.jsp"/>
        </c:when>
    </c:choose>
</div>
<jsp:include page="footer.jsp"/>
<%--<div class="content-creator">--%>
<%--    <div class="col-1">z gbljhfc</div>--%>
<%--    <div class="col-2">--%>
<%--        <div class="header">--%>
<%--&lt;%&ndash;            <c:if test="${empty sessionScope.user}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                <jsp:useBean id="user" class="by.epamtc.ivangavrilovich.shop.bean.User" scope="session"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <c:set target="${user}" property="role" value="${4}"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;            </c:if>&ndash;%&gt;--%>
<%--            <c:choose>--%>
<%--                <c:when test="${sessionScope.user.role == 4}">--%>
<%--                    <a href="sign_in.jsp" class="btn btn-sm text-uppercase itd_sign_in">sign in</a>--%>
<%--                    <a class="sign_in" href="sign_in.jsp">Sign in</a><br/>--%>
<%--                    <a href="register.jsp">Sign up</a>--%>
<%--                </c:when>--%>
<%--            </c:choose>--%>
<%--            <c:out value="${sessionScope.user.role}"/>--%>
<%--        </div>--%>
<%--        <div class="lox">--%>
<%--            <c:out value="${sessionScope.user}"/><br/>--%>
<%--            <c:out value="${sessionScope.popularProducts}"/>--%>
<%--        </div>--%>
<%--        <div class="lox">2</div>--%>
<%--        <div class="lox">3</div>--%>
<%--        <div class="lox">4</div>--%>
<%--        <div class="lox">5</div>--%>
<%--        <div class="lox">6</div>--%>
<%--        <div class="lox">7</div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--<script src="js/bootstrap.bundle.min.js"></script>--%>
</body>
</html>
