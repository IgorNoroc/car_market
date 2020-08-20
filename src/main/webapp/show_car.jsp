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
</body>
<%
    SimpleDateFormat format = new SimpleDateFormat("dd-MMMM-yyyy");
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

<div id="list" class="container">
    <% for (Car car : (Collection<Car>) request.getServletContext().getAttribute("cars")) { %>
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
        Название авто : <%=car.getName()%>
        <br/>
        Цена : <%=car.getPrice()%> рублей
        <br/>
        дата публикации : <%=format.format(car.getPublish())%>
    </div>
    <%}%>
</div>
</form>
</html>
