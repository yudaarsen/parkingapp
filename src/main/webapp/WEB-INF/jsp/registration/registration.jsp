<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="forn" uri="http://www.springframework.org/tags/form" %>
<c:url value="resources/js/jquery-3.5.1.min.js" var="jquery"/>
<c:url value="resources/css/registration/style.css" var="style"/>
<c:url value="resources/js/registration/registration.js" var="registrationScript"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <link rel="stylesheet" href="${style}">
    <script src="${jquery}"></script>
</head>
<body>
<%@ include file="../header.jsp"%>
<section id="main">
    <div class="container">
        <div class="main">
            <div class="title">
                <h1>Создать аккаунт</h1>
                <h2>Быстро и легко.</h2>
            </div>
           <form:form modelAttribute="account">
               <form:errors />
               Имя:<form:input path="firstName" />
               <form:errors path="firstName" /><br/>
               Фамилия:<form:input path="lastName" />
               <form:errors path="lastName" /><br/>
               Отчество:<form:input path="midName" />
               <forn:errors path="midName" /><br/>
               Адрес электронной почты:<form:input path="email" />
               <form:errors path="email"/><br/>
               Пароль:<form:input path="password" type="password" />
               <form:errors path="password" /> <br/>
               <button type="submit">Отправить</button>
           </form:form>
            <div class="log_part">
                <p>Есть аккаунт?</p>
                <a href="/">Войти</a>
            </div>
        </div>
    </div>
</section>
<%@ include file="../footer.jsp"%>
<script src="${registrationScript}"></script>
</body>