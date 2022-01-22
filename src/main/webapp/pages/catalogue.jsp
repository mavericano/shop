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
<>
<jsp:include page="header.jsp"/>
<a href="${pageContext.request.contextPath}/pages/controller?command=VIEW_ALL_PRODUCTS&page=3" class="btn">oi</a><br/>
<c:out value="${requestScope.page}"/><br/>
<ct:productsGrid products="${requestScope.products}" numberOfPages="${requestScope.numberOfPages}" currentPage="${requestScope.page}"/>
<%--<ul class="pagination">--%>
<%--    <li class="page-item"><a class="page-link" href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>--%>
<%--    <li class="page-item"><a class="page-link" href="#">1</a></li>--%>
<%--    <li class="page-item"><a class="page-link" href="#">2</a></li>--%>
<%--    <li class="page-item"><a class="page-link" href="#">3</a></li>--%>
<%--    <li class="page-item"><a class="page-link" href="#">4</a></li>--%>
<%--    <li class="page-item"><a class="page-link" href="#">5</a></li>--%>
<%--    <li class="page-item"><a class="page-link" href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>--%>
<%--</ul>--%>
<%--TODO margin--%>
<%--<div class="container" style="margin-bottom: 120px;" >--%>
<%--    <div class="row product-list dev">--%>
<%--        <div class="col-sm-6 col-md-4 product-item">--%>
<%--            <div class="product-container">--%>
<%--                <div class="row">--%>
<%--                    <!--                    TODO href-->--%>
<%--                    <div class="col-md-12"><a class="product-image" href="#"><img src="../resources/assets/img/iphone6.jpg"/></a></div>--%>
<%--                </div>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-8">--%>
<%--                        <!--                        TODO href and name-->--%>
<%--                        <h2><a href="#">iPhone 6s</a></h2>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-12">--%>
<%--                        <!--                        TODO desc-->--%>
<%--                        <p class="product-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ornare sem sed nisl dignissim, facilisis dapibus lacus vulputate. Sed lacinia lacinia magna. </p>--%>
<%--                        <div class="row">--%>
<%--                            <!--                            TODO text-->--%>
<%--                            <div class="col-6"><button class="btn btn-light" type="button">Buy Now!</button></div>--%>
<%--                            <div class="col-6">--%>
<%--                                <!--                                TODO price-->--%>
<%--                                <p class="product-price">$599.00 </p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="col-sm-6 col-md-4 product-item">--%>
<%--            <div class="product-container">--%>
<%--                <div class="row">--%>
<%--                    <!--                    TODO href-->--%>
<%--                    <div class="col-md-12"><a class="product-image" href="#"><img src="../resources/assets/img/iphone6.jpg"/></a></div>--%>
<%--                </div>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-8">--%>
<%--                        <!--                        TODO href and name-->--%>
<%--                        <h2><a href="#">iPhone 6s</a></h2>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-12">--%>
<%--                        <!--                        TODO desc-->--%>
<%--                        <p class="product-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ornare sem sed nisl dignissim, facilisis dapibus lacus vulputate. Sed lacinia lacinia magna. </p>--%>
<%--                        <div class="row">--%>
<%--                            <!--                            TODO text-->--%>
<%--                            <div class="col-6"><button class="btn btn-light" type="button">Buy Now!</button></div>--%>
<%--                            <div class="col-6">--%>
<%--                                <!--                                TODO price-->--%>
<%--                                <p class="product-price">$599.00 </p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="col-sm-6 col-md-4 product-item">--%>
<%--            <div class="product-container">--%>
<%--                <div class="row">--%>
<%--                    <!--                    TODO href-->--%>
<%--                    <div class="col-md-12"><a class="product-image" href="#"><img src="../resources/assets/img/iphone6.jpg"/></a></div>--%>
<%--                </div>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-8">--%>
<%--                        <!--                        TODO href and name-->--%>
<%--                        <h2><a href="#">iPhone 6s</a></h2>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-12">--%>
<%--                        <!--                        TODO desc-->--%>
<%--                        <p class="product-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ornare sem sed nisl dignissim, facilisis dapibus lacus vulputate. Sed lacinia lacinia magna. </p>--%>
<%--                        <div class="row">--%>
<%--                            <!--                            TODO text-->--%>
<%--                            <div class="col-6"><button class="btn btn-light" type="button">Buy Now!</button></div>--%>
<%--                            <div class="col-6">--%>
<%--                                <!--                                TODO price-->--%>
<%--                                <p class="product-price">$599.00 </p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="col-sm-6 col-md-4 product-item">--%>
<%--            <div class="product-container">--%>
<%--                <div class="row">--%>
<%--                    <!--                    TODO href-->--%>
<%--                    <div class="col-md-12"><a class="product-image" href="#"><img src="../resources/assets/img/iphone6.jpg"/></a></div>--%>
<%--                </div>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-8">--%>
<%--                        <!--                        TODO href and name-->--%>
<%--                        <h2><a href="#">iPhone 6s</a></h2>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-12">--%>
<%--                        <!--                        TODO desc-->--%>
<%--                        <p class="product-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ornare sem sed nisl dignissim, facilisis dapibus lacus vulputate. Sed lacinia lacinia magna. </p>--%>
<%--                        <div class="row">--%>
<%--                            <!--                            TODO text-->--%>
<%--                            <div class="col-6"><button class="btn btn-light" type="button">Buy Now!</button></div>--%>
<%--                            <div class="col-6">--%>
<%--                                <!--                                TODO price-->--%>
<%--                                <p class="product-price">$599.00 </p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="col-sm-6 col-md-4 product-item">--%>
<%--            <div class="product-container">--%>
<%--                <div class="row">--%>
<%--                    <!--                    TODO href-->--%>
<%--                    <div class="col-md-12"><a class="product-image" href="#"><img src="../resources/assets/img/iphone6.jpg"/></a></div>--%>
<%--                </div>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-8">--%>
<%--                        <!--                        TODO href and name-->--%>
<%--                        <h2><a href="#">iPhone 6s</a></h2>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-12">--%>
<%--                        <!--                        TODO desc-->--%>
<%--                        <p class="product-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ornare sem sed nisl dignissim, facilisis dapibus lacus vulputate. Sed lacinia lacinia magna. </p>--%>
<%--                        <div class="row">--%>
<%--                            <!--                            TODO text-->--%>
<%--                            <div class="col-6"><button class="btn btn-light" type="button">Buy Now!</button></div>--%>
<%--                            <div class="col-6">--%>
<%--                                <!--                                TODO price-->--%>
<%--                                <p class="product-price">$599.00 </p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="col-sm-6 col-md-4 product-item">--%>
<%--            <div class="product-container">--%>
<%--                <div class="row">--%>
<%--                    <!--                    TODO href-->--%>
<%--                    <div class="col-md-12"><a class="product-image" href="#"><img src="../resources/assets/img/iphone6.jpg"/></a></div>--%>
<%--                </div>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-8">--%>
<%--                        <!--                        TODO href and name-->--%>
<%--                        <h2><a href="#">iPhone 6s</a></h2>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-12">--%>
<%--                        <!--                        TODO desc-->--%>
<%--                        <p class="product-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ornare sem sed nisl dignissim, facilisis dapibus lacus vulputate. Sed lacinia lacinia magna. </p>--%>
<%--                        <div class="row">--%>
<%--                            <!--                            TODO text-->--%>
<%--                            <div class="col-6"><button class="btn btn-light" type="button">Buy Now!</button></div>--%>
<%--                            <div class="col-6">--%>
<%--                                <!--                                TODO price-->--%>
<%--                                <p class="product-price">$599.00 </p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="col-sm-6 col-md-4 product-item">--%>
<%--            <div class="product-container">--%>
<%--                <div class="row">--%>
<%--                    <!--                    TODO href-->--%>
<%--                    <div class="col-md-12"><a class="product-image" href="#"><img src="../resources/assets/img/iphone6.jpg"/></a></div>--%>
<%--                </div>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-8">--%>
<%--                        <!--                        TODO href and name-->--%>
<%--                        <h2><a href="#">iPhone 6s</a></h2>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-12">--%>
<%--                        <!--                        TODO desc-->--%>
<%--                        <p class="product-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ornare sem sed nisl dignissim, facilisis dapibus lacus vulputate. Sed lacinia lacinia magna. </p>--%>
<%--                        <div class="row">--%>
<%--                            <!--                            TODO text-->--%>
<%--                            <div class="col-6"><button class="btn btn-light" type="button">Buy Now!</button></div>--%>
<%--                            <div class="col-6">--%>
<%--                                <!--                                TODO price-->--%>
<%--                                <p class="product-price">$599.00 </p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="col-sm-6 col-md-4 product-item">--%>
<%--            <div class="product-container">--%>
<%--                <div class="row">--%>
<%--                    <!--                    TODO href-->--%>
<%--                    <div class="col-md-12"><a class="product-image" href="#"><img src="../resources/assets/img/iphone6.jpg"/></a></div>--%>
<%--                </div>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-8">--%>
<%--                        <!--                        TODO href and name-->--%>
<%--                        <h2><a href="#">iPhone 6s</a></h2>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-12">--%>
<%--                        <!--                        TODO desc-->--%>
<%--                        <p class="product-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ornare sem sed nisl dignissim, facilisis dapibus lacus vulputate. Sed lacinia lacinia magna. </p>--%>
<%--                        <div class="row">--%>
<%--                            <!--                            TODO text-->--%>
<%--                            <div class="col-6"><button class="btn btn-light" type="button">Buy Now!</button></div>--%>
<%--                            <div class="col-6">--%>
<%--                                <!--                                TODO price-->--%>
<%--                                <p class="product-price">$599.00 </p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="col-sm-6 col-md-4 product-item">--%>
<%--            <div class="product-container">--%>
<%--                <div class="row">--%>
<%--                    <!--                    TODO href-->--%>
<%--                    <div class="col-md-12"><a class="product-image" href="#"><img src="../resources/assets/img/iphone6.jpg"/></a></div>--%>
<%--                </div>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-8">--%>
<%--                        <!--                        TODO href and name-->--%>
<%--                        <h2><a href="#">iPhone 6s</a></h2>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-12">--%>
<%--                        <!--                        TODO desc-->--%>
<%--                        <p class="product-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ornare sem sed nisl dignissim, facilisis dapibus lacus vulputate. Sed lacinia lacinia magna. </p>--%>
<%--                        <div class="row">--%>
<%--                            <!--                            TODO text-->--%>
<%--                            <div class="col-6"><button class="btn btn-light" type="button">Buy Now!</button></div>--%>
<%--                            <div class="col-6">--%>
<%--                                <!--                                TODO price-->--%>
<%--                                <p class="product-price">$599.00 </p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<jsp:include page="footer.jsp"/>
</body>
</html>
