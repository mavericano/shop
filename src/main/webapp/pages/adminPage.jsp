<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/taglib.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Control panel</title>
    <link rel="stylesheet" href="../resources/assets/bootstrap/css/bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
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
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="col12">
        <div class="accordion" id="accordionPanelsStayOpenExample">
            <div class="accordion-item">
                <h2 class="accordion-header" id="panelsStayOpen-headingOne">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">
                        ${sessionScope.userListLabel}
                    </button>
                </h2>
                <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show" aria-labelledby="panelsStayOpen-headingOne">
                    <div class="accordion-body">
                        <div class="row">
                            <div class="table-responsive">
                                <form method="POST" action="${pageContext.request.contextPath}/pages/controller?command=SUBMIT_USER_CHANGES">
                                    <ct:pagination numberOfPages="${requestScope.numberOfUserPages}" currentPage="${requestScope.pageUsers}" path="/pages/controller?command=VIEW_ADMIN_SCREEN&pageProducts=${requestScope.pageProducts}&pageOrders=${requestScope.pageOrders}&pageUsers="/>
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="p-2 px-3 text-uppercase">Phone number</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Email</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Role</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Banned</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Deleted</div>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:set var="users" scope="session" value="${requestScope.users}"/>
                                        <c:forEach var="user" items="${requestScope.users}">
                                            <tr>
                                                <th scope="row" class="border-0"><c:out value="${user.number}"/></th>
                                                <td class="border-0 align-middle"><strong><c:out value="${user.email}"/></strong></td>
                                                <td class="border-0 align-middle">
                                                    <select size="1" name="role">
                                                        <option <c:if test="${user.role == 3}">selected</c:if> value="${user.userId} 3">Admin</option>
                                                        <option <c:if test="${user.role == 2}">selected</c:if> value="${user.userId} 2">Courier</option>
                                                        <option <c:if test="${user.role == 1}">selected</c:if> value="${user.userId} 1">Basic user</option>
                                                    </select>
                                                </td>
                                                <td class="border-0 align-middle">
                                                    <input <c:if test="${user.banned == true}">checked</c:if> name="banned" type="checkbox" value="${user.userId}"/>
                                                </td>
                                                <td class="border-0 align-middle">
                                                    <input <c:if test="${user.deleted == true}">checked</c:if> name="deleted" type="checkbox" value="${user.userId}"/>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <button type="submit" style="background: #208F8F; color: white;" class="btn btn-light action-button">Save</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseTwo" aria-expanded="false" aria-controls="panelsStayOpen-collapseTwo">
                        ${sessionScope.productsListLabel}
                    </button>
                </h2>
                <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse show" aria-labelledby="panelsStayOpen-headingTwo">
                    <div class="accordion-body">
                        <div class="row">
                            <div class="table-responsive">
                                <form method="POST" action="${pageContext.request.contextPath}/pages/controller?command=SUBMIT_PRODUCT_CHANGES">
                                    <ct:pagination numberOfPages="${requestScope.numberOfProductPages}" currentPage="${requestScope.pageProducts}" path="/pages/controller?command=VIEW_ADMIN_SCREEN&pageUsers=${requestScope.pageUsers}&pageOrders=${requestScope.pageOrders}&pageProducts="/>
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="p-2 px-3 text-uppercase">Name</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Stock</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Add to stock</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Deleted</div>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:set var="products" scope="session" value="${requestScope.products}"/>
                                        <c:forEach var="product" items="${requestScope.products}">
                                            <tr>
                                                <th scope="row" class="border-0">
                                                    <a class="text-dark d-inline-block align-middle" href="${pageContext.request.contextPath}/pages/controller?command=VIEW_SINGLE_PRODUCT&id=${product.productId}"><c:out value="${product.name}"/></a>
                                                </th>
                                                <td class="border-0 align-middle"><strong><c:out value="${product.stock}"/></strong></td>
                                                <td class="border-0 align-middle">
                                                    <input style="width:40px;" value="0" min="0" type="number" name="${product.productId}toAddToStock"/><strong> will be added</strong>
                                                </td>
                                                <td class="border-0 align-middle">
                                                    <input <c:if test="${product.deleted == true}">checked</c:if> name="deleted" value="${product.productId}" type="checkbox"/>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <button type="submit" style="background: #208F8F; color: white;" class="btn btn-light action-button">Save</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="panelsStayOpen-headingThree">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseThree" aria-expanded="false" aria-controls="panelsStayOpen-collapseThree">
                        ${sessionScope.ordersListLabel}
                    </button>
                </h2>
                <div id="panelsStayOpen-collapseThree" class="accordion-collapse collapse show" aria-labelledby="panelsStayOpen-headingThree">
                    <div class="accordion-body">
                        <div class="row">
                            <div class="table-responsive">
                                <form method="POST" action="${pageContext.request.contextPath}/pages/controller?command=SUBMIT_ORDER_CHANGES">
                                    <ct:pagination numberOfPages="${requestScope.numberOfOrderPages}" currentPage="${requestScope.pageOrders}" path="/pages/controller?command=VIEW_ADMIN_SCREEN&pageUsers=${requestScope.pageUsers}&pageProducts=${requestScope.pageProducts}&pageOrders="/>
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="p-2 px-3 text-uppercase">Phone number</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Address</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Status</div>
                                            </th>
<%--                                            <th scope="col" class="border-0 bg-light">--%>
<%--                                                <div class="py-2 text-uppercase">Courier</div>--%>
<%--                                            </th>--%>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Price</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Additional info</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Ordered products</div>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:set var="orders" scope="session" value="${requestScope.orders}"/>
                                        <c:forEach var="order" items="${requestScope.orders}">
                                            <tr>
                                                <th scope="row" class="border-0"><c:out value="${order.user.number}"/></th>
                                                <td class="border-0 align-middle">
                                                    <textarea name="${order.order.orderId}address" cols="23" rows="0"><c:out value="${order.order.address}"/></textarea>
                                                </td>
                                                <td class="border-0 align-middle">
                                                    <select size="1" name="status">
                                                        <option <c:if test="${order.order.status == 1}">selected</c:if> value="${order.order.orderId} 1">In process</option>
                                                        <option <c:if test="${order.order.status == 2}">selected</c:if> value="${order.order.orderId} 2">Reviewed by admin</option>
                                                        <option <c:if test="${order.order.status == 3}">selected</c:if> value="${order.order.orderId} 3">In delivery</option>
                                                        <option <c:if test="${order.order.status == 4}">selected</c:if> value="${order.order.orderId} 4">Delivered</option>
                                                    </select>
                                                </td>
<%--                                                <td class="border-0 align-middle"><strong>courier id?</strong></td>--%>
                                                <td class="border-0 align-middle"><strong><c:out value="${order.order.price}"/></strong></td>
                                                <td class="border-0 align-middle"><strong><c:out value="${order.order.info}"/></strong></td>
                                                <td class="border-0 align-middle">
                                                    <button style="background: #208F8F; color: white;" class="btn btn-light action-button" type="button" data-bs-toggle="collapse" data-bs-target="#id${order.order.orderId}" aria-expanded="false">
                                                        Ordered products
                                                    </button>
                                                </td>
                                            </tr>
                                            <tr>
<%--                                                <td class="border-0 align-middle"></td>--%>
                                                <td class="border-0 align-middle"></td>
                                                <td class="border-0 align-middle"></td>
                                                <td class="border-0 align-middle"></td>
                                                <td class="border-0 align-middle"></td>
                                                <td class="border-0 align-middle"></td>
                                                <td class="border-0 align-middle">
                                                    <div class="collapse" id="id${order.order.orderId}" style="width: ">
                                                        <div class="card card-body">
                                                            <c:forEach var="product" items="${order.orderedProducts}">
                                                                <c:out value="${product.product.name} (${product.quantity} pcs.)"/><br/>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <button type="submit" style="background: #208F8F; color: white;" class="btn btn-light action-button">Save</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
