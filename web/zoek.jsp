<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Sébastien
  Date: 24/10/2020
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="style/style.css">
    <title>Zoek een boek</title>
</head>
<body>
<header>
    <jsp:include page="navigatie.jsp"/>
    <h1>Zoek boek</h1>
</header>
<main>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">
            <ul>
                <li>${error}</li>
            </ul>
        </div>
    </c:if>

    <form method="get" action="BoekInfo" novalidate>
        <p>Welk boek zoek je?</p>
        <label for="titel">Titel:</label>
        <input type="text" id="titel" name="titel" value="">

        <input type="hidden" name="command" value="zoek">

        <label for="submit"></label>
        <input type="submit" id="submit" value="Zoek Boek">
    </form>

</main>

<jsp:include page="footer.jsp"/>

</body>
</html>
