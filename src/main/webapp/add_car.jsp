<%@ page import="ru.job4j.market.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html id="market">
<head>
    <meta charset="utf-8">
    <link href="style.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <title>Новое объявление</title>
    <script>
        function validate() {
            const msg = "Пожалуйста, заполните поле: ";
            if ($('#name').val() === '') {
                alert(msg + $('#name').attr('title'))
                return false;
            } else if ($('#price').val() === '') {
                alert(msg + $('#price').attr('title'))
                return false;
            } else if ($('#created').val() === '') {
                alert(msg + $('#price').attr('title'))
                return false;
            } else if ($('#color').val() === '') {
                alert(msg + $('#color').attr('title'))
                return false;
            } else if ($('#mileage').val() === '') {
                alert(msg + $('#mileage').attr('title'))
                return false;
            } else if ($('#mileage') === '') {
                alert(msg + $('#mileage').attr('title'))
                return false;
            } else if ($('#selectType').val() === '') {
                alert(msg + $('#selectType').attr('title'))
                return false;
            } else if ($('#selectBody').val() === '') {
                alert(msg + $('#selectBody').attr('title'))
                return false;
            } else if ($('#selectEngine').val() === '') {
                alert(msg + $('#selectEngine').attr('title'))
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<h1 id="hello">Новое объявление: </h1>
<div id="showActiveUser">
    <%User user = (User) request.getSession().getAttribute("user"); %>
    <a href="index.jsp">
        <%request.getServletContext().setAttribute("error", null);%>
        <%=user.getName()%> | Выйти...</a>
</div>
<div class="container-add">
    <form action="<%=request.getContextPath()%>/addCar"  method="post" enctype="multipart/form-data">
        <div class="container-addLine"> Введите имя авто : <input type="text"  id="name" placeholder="введите имя"
                                                                  title="имя"
                                                                  name="name"></div>
        <div class="container-addLine"> Цена : <input type="text" id="price" placeholder="введите цену" title="цена"
                                                      name="price">
            рублей.
        </div>
        <div class="container-addLine"> Год выпуска: <input type="date" id="created" placeholder="произведенно"
                                                            title="created"
                                                            name="created"
                                                            value="2020-08-17"></div>
        <div class="container-addLine"> Цвет : <input type="text" id="color" name="color" title="цвет"
                                                      placeholder="цвет"></div>
        <div class="container-addLine"> Пробег : <input type="text" id="mileage" name="mileage" title="пробег"
                                                        placeholder="пробег">
            км.
        </div>
        <div class="container-addLine"> Тип авто : <select id="selectType" name="type" title="тип авто">
            <option selected></option>
            <option>легковая</option>
            <option>внедорожник</option>
            <option>грузовая</option>
            <option>тигач</option>
            <option>бус</option>
            <option>трактор</option>
            <option>строй-техника</option>
        </select></div>
        <div class="container-addLine"> Тип кузова :<select id="selectBody" name="bodyCar" title="тип кузова">
            <option selected></option>
            <option>хетчбэк</option>
            <option>седан</option>
            <option>микроавтобус</option>
            <option>внедорожник</option>
            <option>пикап</option>
            <option>универсал</option>
            <option>минивэн</option>
        </select></div>
        <div class="container-addLine"> Тип топлива : <select id="selectEngine" name="engine" title="тип топлива">
            <option selected></option>
            <option>бензин</option>
            <option>дизель</option>
            <option>газ</option>
            <option>гибрид</option>
        </select></div>
        <div class="container-addLine">
                Добавить фото : <input type="file"  style="font-family: 'Sitka Banner'" name="file">
        </div>
        <div class="container-addLine">
            <button type="submit" class="btn btn-primary" style="color: black" onclick="return validate()">
                <b>Сохранить</b></button>
        </div>
    </form>
</div>
<div id="home">
    <form action="<%=request.getContextPath()%>/allCars" method="get">
        <button id="goHome" type="submit" class="btn btn-success">на главную страницу...</button>
    </form>
</div>
</body>
</html>
