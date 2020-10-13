<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="resources/js/jquery-3.5.1.min.js" var="jquery"/>
<c:url value="resources/css/main.css" var="mainStyle"/>
<c:url value="resources/js/loginPage.js" var="loginScript"/>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>Название</title>
    <link rel="stylesheet" type="text/css" href="${mainStyle}">
    <script src="${jquery}"></script>
</head>
<body>
<%@ include file="header.jsp"%>
<section id="main">
    <div class="container">
        <div class="main">
            <c:if test="${errorMessage.length() > 0}">
                <c:out value="${errorMessage}"/>
            </c:if>
            <c:choose>
                <c:when test="${accountId == null || accountId <= 0}">
                    <div class="login_form">
                        <form:form action="/login" method="POST">
                            <form:errors path="*"/>
                            <div class="fields">
                                <input type="text" id="email"  placeholder="Адрес электронной почты" size="35" name="email"/><br/>
                                <input type="password" id="password" placeholder="Пароль" size="35" name="password"/>
                            </div>
                            <div class="buttons">
                                <input class="button" id="login_button" value="Вход" type="submit" />
                                <p>или</p>
                                <a href="/registration">
                                    <button class="button" id="register_button" name="register_button">Регистрация</button>
                                </a>
                            </div>
                        </form:form>
                    </div>
                </c:when>
                <c:otherwise>
                    Logged in!
                </c:otherwise>
            </c:choose>
            <div class="about">
                <h3>О нас</h3>
                <p>Система обработки обращений автоматизирует процесс подачи и дальнейшей обработки обращений о нарушениях правил парковки в городе. Система позволяет своевременно сообщать о нарушениях правил парковки в ведомства, ответственные за организацию дорожного движения города. <br> Целью создания системы является увеличение процента выявленных нарушений правил парковки в городе. Критерием достижения цели является увеличение среднего показателя выявления нарушений правил парковки.</p>
            </div>
        </div>
    </div>
</section>
<%@ include file="footer.jsp"%>
<script src="${loginScript}"></script>
</body>