<%@ page import="ru.job4j.market.model.Car" %>
<%@ page import="ru.job4j.market.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html id="market">
<head>
    <title>Описание авто</title>
    <link href="style.css" rel="stylesheet">
    <%
        Car car = (Car) request.getServletContext().getAttribute("car");
        User currentUser = (User) request.getSession().getAttribute("user");
    %>
</head>
<div id="showActiveUser">
    <a href="index.jsp">
        <%request.getServletContext().setAttribute("error", null);%>
        <%=currentUser.getName()%> | Выйти...</a>
</div>
<body>
<% if (car.getUser().getId() == currentUser.getId()) {%>
<form action="<%=request.getContextPath()%>/changeStatus?id=<%=car.getId()%>" method="post">
    <button id="statusDescButton" type="submit" class="btn btn-success">изменить статус продажи авто</button>
</form>
<% }%>
<div id="carDescription">
    <img id="img" src="<c:url value='/download?photo=${car.photo}'/>">
    <br/>
    <br/>
    <div class="desc"> артикол : <c:out value="${car.id}"/></div>
    <div class="desc"> имя : <c:out value="${car.name}"/></div>
    <div class="desc"> цена : <c:out value="${car.price}"/>рублей</div>
    <div class="desc"> дата производтства : <c:out value="${car.created}"/></div>
    <div class="desc"> цвет : <c:out value="${car.color}"/></div>
    <div class="desc"> пробег : <c:out value="${car.mileage}"/>км</div>
    <div class="desc"> тип авто : <c:out value="${car.type}"/></div>
    <div class="desc"> кузов : <c:out value="${car.bodyCar}"/></div>
    <div class="desc"> топливо : <c:out value="${car.engine}"/></div>
    <div class="desc"> статус :
        <% if (car.getStatus()) {%>
        продаётся
        <% } else { %>
        продано!
        <% } %>
    </div>
</div>
<div id="home">
    <form action="<%=request.getContextPath()%>/allCars" method="get">
        <button id="goHome" type="submit" class="btn btn-success">на главную страницу...</button>
    </form>
</div>
</body>
</html>
