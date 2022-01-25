<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--TODO admin screen--%>
<div>
    <div class="header-dark">
        <nav class="navbar navbar-dark navbar-expand-lg navigation-clean-search" style="background-color: #5b5b5b;">
            <div class="container"><a class="navbar-brand" href="${pageContext.request.contextPath}/pages/controller?command=VIEW_HOME_PAGE">SoundWave</a><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse"
                     id="navcol-1">
                    <ul class="nav navbar-nav">
                        <li class="nav-item" role="presentation"><a class="nav-link" href="${pageContext.request.contextPath}/pages/controller?command=VIEW_ALL_PRODUCTS">${sessionScope.gotoCatalogue}</a></li>
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
                            <span class="navbar-text">
                                <a class="login" href="sign_in.jsp">${sessionScope.logInLabel}</a>
                            </span>
                            <a class="btn btn-light action-button" role="button" href="register.jsp">${sessionScope.registerLabel}</a>
                        </c:when>
                        <c:when test="${sessionScope.user.role == 3}">
                            <a class="btn btn-light action-button" role="button" style="color: red; background: transparent; border: 1px solid red;" href="${pageContext.request.contextPath}/pages/controller?command=SIGN_OUT">${sessionScope.logOutLabel}</a>
                            <c:out value="admin"/>
                        </c:when>
                        <c:when test="${sessionScope.user.role == 2}">
                            <a class="btn btn-light action-button" role="button" style="color: red; background: transparent; border: 1px solid red;" href="${pageContext.request.contextPath}/pages/controller?command=SIGN_OUT">${sessionScope.logOutLabel}</a>
                            <c:out value="courier"/>
                        </c:when>
                        <c:when test="${sessionScope.user.role == 1}">
                            <a class="btn btn-danger action-button" role="button" style="color: red; background: transparent; border: 1px solid red;" href="${pageContext.request.contextPath}/pages/controller?command=SIGN_OUT">${sessionScope.logOutLabel}</a>
                            <c:out value="basic user"/>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </nav>
    </div>
</div>
<script src="../resources/assets/js/jquery.min.js"></script>
<script src="../resources/assets/bootstrap/js/bootstrap.min.js"></script>