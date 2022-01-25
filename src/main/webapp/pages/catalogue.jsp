<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/taglib.tld"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
    <link rel="stylesheet" href="../resources/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bitter:400,700">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Cookie">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="../resources/assets/css/Animated-Pretty-Product-List-v12.css" />
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
    <ct:pagination numberOfPages="${requestScope.numberOfPages}" currentPage="${requestScope.page}"/>
    <ct:productsGrid products="${requestScope.products}"/>
    <jsp:include page="footer.jsp"/>
</body>
</html>
