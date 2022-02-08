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
    <script src="../resources/assets/js/jquery.min.js"></script>
    <script src="../resources/assets/bootstrap/js/bootstrap.min.js"></script>
    <script defer src="../resources/assets/js/validateOnProductEdit.js"></script>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="container">
        <h1 class="text-center">${sessionScope.productDetailsLabel}</h1>
        <div class="row">
            <div class="col-md-7">
                <div class="row">
                    <div class="col-md-12">
<%--                        TODO fix, Tomcat9 issue--%>
                        <img class="img-thumbnail img-fluid center-block" src="${product.thumbnail}"/>
                    </div>
                </div>
            </div>
            <div class="col-md-5">
                <c:if test="${requestScope.editing}">
                    <form id="form" action="${pageContext.request.contextPath}/pages/controller?command=SUBMIT_EDITING" method="POST" enctype="multipart/form-data">
                        <p>
                            <input type="hidden" name="id" value="${product.productId}"/>
                            <input type="hidden" name="oldThumbnail" value="${product.thumbnail}"/>
                            <label for="file">${sessionScope.thumbnailEditLabel}</label>
                            <input class="form-control" type="file" id="file" name="file"/><br/>
                            <input class="form-control to-validate" type="text" id="name" name="name" placeholder="Name" value="${product.name}"/><br/>
                            <input class="form-control to-validate" type="text" id="maker" name="maker" placeholder="${sessionScope.makerLabel}" value="${product.maker}"/><br/>
                            <input class="form-control to-validate" type="text" id="body" name="body" placeholder="${sessionScope.bodyLabel}" value="${product.body}"/><br/>
                            <input class="form-control to-validate" type="text" id="fret" name="fret" placeholder="${sessionScope.fretLabel}" value="${product.fret}"/><br/>
                            <input class="form-control to-validate" type="text" id="scale" name="scale" placeholder="${sessionScope.scaleLabel}" value="${product.scale}"/><br/>
                            <input class="form-control to-validate" type="text" id="fretAmount" name="fretAmount" placeholder="${sessionScope.fretAmountLabel}" value="${product.fretAmount}"/><br/>
                            <input class="form-control to-validate" type="text" id="picks" name="picks" placeholder="${sessionScope.picksLabel}" value="${product.picks}"/><br/>
                            <label for="beltButton">${sessionScope.beltButtonLabel}</label>
                            <input class="to-validate" type="checkbox" id="beltButton" name="beltButton" <c:if test="${product.beltButton}">checked</c:if>/><br/>
                        </p>
                        <h2 class="text-center">
<%--                            TODO currency conversion--%>
                            <input class="form-control to-validate" type="text" id="price" name="price" placeholder="Price" value="${product.price}"/><br/>
                        </h2>
                        <button class="btn btn-primary" id="button" style="background-color: rgb(38, 157, 157); margin-top: 10px;" type="submit">${sessionScope.submitLabel}</button>
                        <a class="btn btn-primary" id="button" href="${pageContext.request.contextPath}/pages/controller?command=VIEW_SINGLE_PRODUCT&id=${product.productId}" style="background-color: rgb(255, 255, 255); margin-top: 10px; color: rgb(0, 0, 0)" >${sessionScope.cancelLabel}</a>
                        <a id="error" style="color: salmon; font-size: 12px;">
                            <c:out value="${requestScope.message}"/>
                        </a>
                    </form>
                </c:if>
                <c:if test="${not requestScope.editing}">
                    <h1>${product.name}</h1>
                    <p>
                        ${sessionScope.makerLabel}: ${product.maker}<br/>
                        ${sessionScope.bodyLabel}: ${product.body}<br/>
                        ${sessionScope.fretLabel}: ${product.fret}<br/>
                        ${sessionScope.scaleLabel}: ${product.scale}mm<br/>
                        ${sessionScope.fretAmountLabel}: ${product.fretAmount}<br/>
                        ${sessionScope.picksLabel}: <c:if test="${empty product.picks}">No picks</c:if>${product.picks}<br/>
                        ${sessionScope.beltButtonLabel}: <c:if test="${product.beltButton eq true}">yes</c:if><c:if test="${product.beltButton eq false}">no</c:if><br/>
                    </p>
                    <h2 class="text-center text-success"><i class="fa fa-dollar"></i>${product.price}</h2><br/>
                </c:if>
                <c:choose>
                    <c:when test="${sessionScope.user.role == 1}">
                        <c:if test="${requestScope.alreadyInCart}">
                            <a href="${pageContext.request.contextPath}/pages/controller?command=REMOVE_FROM_CART&id=${product.productId}" class="btn btn-light action-button center-block" role="button" ><i class="fa fa-times"></i> ${sessionScope.alreadyInCartLabel}</a>
                        </c:if>
                        <c:if test="${not requestScope.alreadyInCart}">
                            <a href="${pageContext.request.contextPath}/pages/controller?command=ADD_TO_CART&id=${product.productId}" class="btn btn-light action-button center-block" role="button" ><i class="fa fa-cart-plus"></i> ${sessionScope.buyButton}</a>
                        </c:if>
                    </c:when>
                    <c:when test="${sessionScope.user.role == 4}">
                        ${sessionScope.logInWarning}
                    </c:when>
                    <c:when test="${sessionScope.user.role == 3}">
                        <c:if test="${not requestScope.editing}">
                            <a class="btn btn-primary" id="button" href="${pageContext.request.contextPath}/pages/controller?command=EDIT_CURRENT_PRODUCT&id=${product.productId}" style="background-color: rgb(255, 255, 255); margin-top: 10px; color: rgb(0, 0, 0)">${sessionScope.editLabel}</a>
                        </c:if>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
