<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="resources/js/jquery-3.5.1.min.js" var="jquery"/>
<c:url value="resources/css/appeal/check.css" var="mainStyle"/>
<c:url value="resources/js/appeal/check.js" var="checkScript"/>
<html>
<head>
    <script src="${jquery}"></script>
    <link rel="stylesheet" href="${mainStyle}">
</head>
<body>
<c:if test="${error}">
    <c:out value="${error}"/>
</c:if>

Обращение номер: ${appeal.getId()} <br/>
Дата подачи: ${appeal.getSendDate()}<br/>
Тип нарушения: ${type.getDescription()}<br/>
Адрес: ${appeal.getAddress()}<br/>
Текст обращения: ${appeal.getAppealText()}<br/>

Доказательства:<br/>
<c:forEach items="${images}" var="image">
    <img src="/image/${image.getId()}" /><br/>
</c:forEach>

<form method="post" action="/appeal/save/${appeal.getId()}">
    <textarea name="answer" placeholder="Введите ответ на обращение"></textarea><br/>
    <select name="status">
        <c:forEach items="${statuses}" var="st">
            <option value="${st.getId()}">${st.getDescription()}</option>
        </c:forEach>
    </select><br/>
    <input type="submit" value="Сохранить"/>
</form>

<script src="${checkScript}"></script>
</body>
</html>