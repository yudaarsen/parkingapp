<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="resources/js/jquery-3.5.1.min.js" var="jquery"/>
<c:url value="resources/css/lk/style.css" var="style"/>
<c:url value="resources/js/lk/lk.js" var="lkScript"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <link rel="stylesheet" href="${style}">
    <script src="${jquery}"></script>
</head>
<body>
<%@include file="../header.jsp"%>
<section id="main">
    <div class="container">
        <div class="information">
            <form:form id="form" method="POST" modelAttribute="accountInfo" action="lk/update">
                <form:errors path="*" />
                <div class="firstName">
                    <form:input path="firstName"/>
                </div>
                <div class="lastName">
                    <form:input path="lastName" />
                </div>
                <div class="midName">
                    <form:input path="midName" />
                </div>
                <div class="email">
                    <form:input path="email" readonly="true"/>
                </div>
                <div class="regDate">
                    <form:input path="regDate" readonly="true"/>
                </div>
                <div class="buttons">
                    <input type="submit" id="submit_button" value="Сохранить">
                </div>
            </form:form>
        </div>
        <div class="references" id="references">
            <h3>Ваши заявления</h3>
            <div class="list" id="list">
                <!-- Сюда помещаются ссылки -->
            </div>
            <div class="btns">
                <button class="next" id="next">След.</button>
                <button class="prev" id="prev">Пред.</button>
            </div>
            <a href="">Подать заявление</a>
        </div>
    </div>
</section>
<%@include file="../footer.jsp"%>
<script src="${lkScript}"></script>
</body>
</html>
