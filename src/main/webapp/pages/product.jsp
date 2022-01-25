<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:useBean id="product" scope="request" class="by.epamtc.ivangavrilovich.shop.bean.Product"/>
    <title>${product.name}</title>
    <link rel="stylesheet" href="../resources/assets/css/Animated-Pretty-Product-List-v12.css" />
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
<%--TODO expand--%>
<body>
    <jsp:include page="header.jsp"/>
    <c:out value="${product.productId}"/>
    <div class="container">
        <h1 class="text-center">${sessionScope.productDetailsLabel}</h1>
        <div class="row">
            <div class="col-md-7">
                <div class="row">
                    <div class="col-md-12"><img class="img-thumbnail img-fluid center-block" src="../resources/assets/img/iphone6.jpg" /></div>
                </div>
            </div>
            <div class="col-md-5">
                <h1>${product.name}</h1>
                <p></p>
                <h2 class="text-center text-success"><i class="fa fa-dollar"></i>${product.price}$<br/><br/><br/></h2>
                <button class="btn btn-danger btn-lg center-block" type="button"><i class="fa fa-cart-plus"></i> ${sessionScope.buyButton}</button>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
