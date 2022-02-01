<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/taglib.tld"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SoundWave</title>
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
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const flag = document.querySelector('#flag');
            if (flag.value !== '') {
                $('#staticBackdrop').modal('show')
            }
        });
    </script>
</head>
<body>
<input type="hidden" id="flag" value="<c:if test="${requestScope.viewModal}">flag</c:if>"/>
<div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">${sessionScope.modalTitle}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <c:out value="${requestScope.modalMessage}"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">${sessionScope.confirmModalLabel}</button>
            </div>
        </div>
    </div>
</div>
    <jsp:include page="header.jsp"/>
    <h1 style="text-align: center">${sessionScope.popularLabel}</h1>
    <ct:productsGrid products="${sessionScope.popularProducts}"/>
    <jsp:include page="footer.jsp"/>
</body>
</html>
