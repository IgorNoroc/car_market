<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="ru.job4j.market.model.Car" %>
<%@ page import="java.util.Collection" %>
<%@ page import="ru.job4j.market.model.User" %>

<html id="market">
<head>
    <meta charset="utf-8">
    <link href="style.css" rel="stylesheet">
    <title>Рынок Автомобилей</title>
</head>
<body>
<h1 id="hello">Рынок Автомобилей</h1>
<%
    SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
    Collection<Car> cars = (Collection<Car>) request.getServletContext().getAttribute("cars");
%>

<div id="showActiveUser">
    <%User user = (User) request.getSession().getAttribute("user"); %>
    <a href="index.jsp">
        <%request.getServletContext().setAttribute("error", null);%>
        <%=user.getName()%> | Выйти...</a>
</div>

<div id="add">
    <form action="<%=request.getContextPath()%>/add_car.jsp">
        <button id="addCarButton" type="submit" class="btn btn-success">Добавить объявление</button>
    </form>
</div>

<div id="selectContainer">
    <form action="<%=request.getContextPath()%>/paramSearch" method="get">
        <div class="select">
           < за текущий день :<input type="checkbox" name="lastDay" title="last"> >
        </div>
        <div class="select">
           < только с фото : <input type="checkbox" name="onlyWithPhoto" title="onlyPhoto"> >
        </div>
        <div class="select">
           < поиск по названию : <input type="text" name="search" title="search"> >
        </div>
        <div class="select">
            <button type="submit" class="btn btn-success" style="background-color: grey">Выполнить</button>
        </div>
    </form>
</div>

<div id="list" class="container">
    <% for (Car car : cars) { %>
    <div id="listItem">
        <h5 id="status">
            <% if (car.getStatus()) {%>
            продаётся
            <% } else if (!car.getStatus()) { %>
            продано!
            <% } %>
        </h5>
        <a href='<%=request.getContextPath()%>/description?id=<%=car.getId()%>'>
            <img src="<%=request.getContextPath()%>/download?photo=<%=car.getPhoto()%>" width="100%" height="20%"
                 alt="фото автомобиля"/> </a>
        <br/>
        <b>авто</b> : <%=car.getName()%>
        <br/>
        <b>цена</b> : <%=car.getPrice()%> рублей
        <br/>
        <b>дата публикации</b> : <%=format.format(car.getPublish())%>
    </div>
    <%}%>
</div>
</body>
</html>
