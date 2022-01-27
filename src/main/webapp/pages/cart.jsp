<%@ taglib prefix="ct" uri="/WEB-INF/tld/taglib.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${sessionScope.cartLabel}</title>
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
<body>
<c:if test="${sessionScope.user.role != 1}">
    <c:redirect url="/pages/controller?command=VIEW_SIGN_IN"/>
</c:if>
<jsp:include page="header.jsp"/>
<ct:pagination numberOfPages="${requestScope.numberOfPages}" currentPage="${requestScope.page}" path="/pages/controller?command=VIEW_CART&page="/>
<ct:cartGrid products="${requestScope.products}"/>
<div class="row py-5 p-4 bg-white rounded shadow-sm">
    <div class="col-lg-6">
        <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">${sessionScope.cartAddressLabel}</div>
        <div class="p-4">
            <p class="font-italic mb-4">${sessionScope.cartAddressLabel}</p><textarea name cols="30" rows="2" class="form-control"></textarea><br/>
            <p class="font-italic mb-4">${sessionScope.cartInfoLabel}</p><textarea name cols="30" rows="2" class="form-control"></textarea></div>
    </div>
    <div class="col-lg-6">
        <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">${sessionScope.cartOrderSummaryLabel}</div>
        <div class="p-4">
            <ul class="list-unstyled mb-4">
                <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">${sessionScope.cartTotalLabel}</strong>
                    <h5 class="font-weight-bold">$${requestScope.totalPrice}</h5>
                </li>
            </ul>
<%--            TODO don't forget--%>
            <a href="#" class="btn btn-dark rounded-pill py-2 btn-block">${sessionScope.cartConfirm}</a></div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
