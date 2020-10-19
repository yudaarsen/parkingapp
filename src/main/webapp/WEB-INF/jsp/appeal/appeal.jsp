<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="resources/js/jquery-3.5.1.min.js" var="jquery"/>
<c:url value="resources/css/appeal/style.css" var="mainStyle"/>
<c:url value="resources/js/appeal/appeal.js" var="appealScript"/>
<html>
<head>
    <link rel="stylesheet" href="${mainStyle}">
    <script src="${jquery}"></script>
    <meta charset="UTF-8">
</head>
<body>
<%@include file="../header.jsp"%>
<section id="main">
    <div class="container">
        <form id="form" enctype="multipart/form-data" method="post">
            <select name="typeId">
                <option selected disabled>Выберите тип нарушения</option>
                <c:forEach items="${types}" var="type">
                    <option value="${type.getId()}">${type.getName()}</option>
                </c:forEach>
            </select>
            <input type="text" size=33 placeholder="Введите адрес нарушения" name="address"/>
            <textarea rows=10 cols=35 placeholder="Опишите суть нарушения" name="appeal_text"></textarea>
            <input type="file" class="files" name="files" accept="image/*" title="Загрузите фотографии" size="25" multiple/>
            <input type="submit">
        </form>
    </div>
</section>
<%@include file="../footer.jsp"%>
<script src="${appealScript}"></script>
</body>
</html>