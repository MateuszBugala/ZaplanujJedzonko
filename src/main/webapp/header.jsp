<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: unlock
  Date: 28.01.19
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">

    <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">

</head>
<body>

<header class="page-header">
    <nav class="navbar navbar-expand-lg justify-content-around">
        <a href="/" class="navbar-brand main-logo">
            Zaplanuj <span>Jedzonko</span>
        </a>
        <ul class="nav nounderline text-uppercase">
            <li class="nav-item ml-4">
                <a class="nav-link color-header" href="login.html">logowanie</a>
            </li>
            <li class="nav-item ml-4">
                <a class="nav-link color-header" href="registration.html">rejestracja</a>
            </li>
            <li class="nav-item ml-4">
                <a class="nav-link" href="#about">o aplikacji</a>
            </li>
            <li class="nav-item ml-4">
                <a class="nav-link disabled" href="recipes.html">Przepisy</a>
            </li>
            <li class="nav-item ml-4">
                <a class="nav-link disabled" href="#contact">Kontakt</a>
            </li>
        </ul>
    </nav>
</header>



</body>
</html>
