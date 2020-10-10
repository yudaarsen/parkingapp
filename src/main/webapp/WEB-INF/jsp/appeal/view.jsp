<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="resources/js/jquery-3.5.1.min.js" var="jquery"/>
<c:url value="resources/css/appeal/view.css" var="mainStyle"/>
<c:url value="resources/js/appeal/view.js" var="viewScript"/>
<html>
<head>
    <link rel="stylesheet" href="${mainStyle}">
    <script src="${jquery}"></script>
</head>
<body>
<%@include file="../header.jsp"%>
<section id="main">
    <div class="container">
        <div class="info">
            <div class="info_text">
                <h1>Информация об обращении</h1>
                ${appeal.getAppealText()}
                <c:forEach items="${images}" var="image">
                    <img src="/image/${image.getId()}" />
                </c:forEach>
                <h1>Материалы</h1>
            </div>
            <div class="info_image">

            </div>
            <div class="reject_button">

            </div>
            <div id="warning"></div>
        </div>
    </div>
</section>
<%@include file="../footer.jsp"%>
<script src="${viewScript}"></script>
</body>
</html>