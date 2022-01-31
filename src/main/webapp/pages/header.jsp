<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <div class="header-dark">
        <nav class="navbar navbar-dark navbar-expand-lg navigation-clean-search" style="background-color: #5b5b5b;">
            <div class="container"><a class="navbar-brand" href="${pageContext.request.contextPath}/pages/controller?command=VIEW_HOME_PAGE">SoundWave</a><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse"
                     id="navcol-1">
                    <ul class="nav navbar-nav">
                        <li class="nav-item" role="presentation"><a class="nav-link" href="${pageContext.request.contextPath}/pages/controller?command=VIEW_ALL_PRODUCTS">${sessionScope.gotoCatalogue}</a></li>
                    </ul>
                    <form class="form-inline mr-auto" action="${pageContext.request.contextPath}/pages/controller?command=SEARCH">
                        <input type="hidden" name="command" value="SEARCH"/>
                        <div class="form-group"><label for="search-field"></label>
                            <div class="form-row padMar">
                                <div class="col padMar">
                                    <div class="input-group">
                                        <div class="input-group-prepend"></div>
                                        <input id="search-field" name="searchText" class="form-control autocomplete" type="text" style="margin-left: 5px; margin-top: 10px; height: 30px;">
                                        <div class="input-group-append">
                                            <button class="btn" type="submit" style="background-color: rgb(32,143,143); margin-top: 10px; height: 30px;">
                                                <i class="fa fa-search" style="color: rgb(255,255,255);"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <form style="width: 100px;" method="GET">
                        <a class="btn-language" style="padding:0;" href="${pageContext.request.contextPath}/pages/controller?command=CHANGE_LANGUAGE&language=ru_RU">
                            <c:choose>
                                <c:when test="${sessionScope.language eq 'ru_RU'}">
                                    <img class="btn-language" src="../resources/assets/img/russia.png" alt="russian_flag">
                                </c:when>
                                <c:otherwise>
                                    <img class="btn-language" src="../resources/assets/img/russia-off.png" alt="russian_flag">
                                </c:otherwise>
                            </c:choose>
                        </a>
                        <a class="btn-language" style="padding:0;" href="${pageContext.request.contextPath}/pages/controller?command=CHANGE_LANGUAGE&language=en_US">
                            <c:choose>
                                <c:when test="${(sessionScope.language eq 'en_US') || (sessionScope.language eq 'en_GB')}">
                                    <img class="btn-language" src="../resources/assets/img/america.png" alt="american_flag">
                                </c:when>
                                <c:otherwise>
                                    <img class="btn-language" src="../resources/assets/img/america-off.png" alt="american_flag">
                                </c:otherwise>
                            </c:choose>
                        </a>
                    </form>
                    <c:choose>
                        <c:when test="${sessionScope.user.role == 4}">
<%--                            guest--%>
                            <span class="navbar-text">
                                <a class="login" href="${pageContext.request.contextPath}/pages/controller?command=VIEW_SIGN_IN">${sessionScope.logInLabel}</a>
                            </span>
                            <a class="btn btn-light action-button" role="button" href="${pageContext.request.contextPath}/pages/controller?command=VIEW_REGISTER">${sessionScope.registerLabel}</a>
                        </c:when>
                        <c:when test="${sessionScope.user.role == 3}">
<%--                            admin--%>
                            <a href="${pageContext.request.contextPath}/pages/controller?command=VIEW_ADMIN_SCREEN" class="btn btn-light action-button" role="button"><i class="fa fa-5px fa-wrench" style="margin-left: 2px;"></i> ${sessionScope.adminScreenLabel}</a>
                            <a class="btn btn-light action-button" role="button" style="color: red; background: transparent; border: 1px solid red;" href="${pageContext.request.contextPath}/pages/controller?command=SIGN_OUT">${sessionScope.logOutLabel}</a>
                        </c:when>
                        <c:when test="${sessionScope.user.role == 2}">
                            <a class="btn btn-light action-button" role="button" style="color: red; background: transparent; border: 1px solid red;" href="${pageContext.request.contextPath}/pages/controller?command=SIGN_OUT">${sessionScope.logOutLabel}</a>
                        </c:when>
                        <c:when test="${sessionScope.user.role == 1}">
<%--                            basic user--%>
                            <a href="${pageContext.request.contextPath}/pages/controller?command=VIEW_CART" class="btn btn-light action-button" role="button"><i class="fa fa-5px fa-shopping-cart" style="margin-left: 2px;"></i> ${sessionScope.cartLabel}</a>
                            <a class="btn btn-danger action-button" role="button" style="color: red; background: transparent; border: 1px solid red;" href="${pageContext.request.contextPath}/pages/controller?command=SIGN_OUT">${sessionScope.logOutLabel}</a>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </nav>
    </div>
</div>
<script src="../resources/assets/js/jquery.min.js"></script>
<script src="../resources/assets/bootstrap/js/bootstrap.min.js"></script>