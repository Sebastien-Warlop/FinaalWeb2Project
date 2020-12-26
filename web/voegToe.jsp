<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Sébastien
  Date: 27/09/2020
  Time: 12:25
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
    <title>Boek toevoegen</title>
</head>
<body>

<header>
    <jsp:include page="navigatie.jsp"/>
    <h1>Voeg een Boek toe</h1>
</header>

<main>
    <c:if test="${not empty errors}">
        <div class="alert alert-danger">
            <ul>
                <c:forEach items="${errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>

    <form method="post" action="BoekInfo?command=voegToe" novalidate>
        <label for="titel">Titel: *</label>
        <input type="text" name="titel" id="titel" value="${titelPreviousValue}">

        <label for="auteur">Auteur: *</label>
        <input type="text" name="auteur" id="auteur" value="${auteurPreviousValue}">

        <label for="genre">Genre: *</label>
        <input type="text" name="genre" id="genre" value="${genrePreviousValue}">

        <label for="rating">Rating: * (tussen 0 en 10)</label>
        <input type="number" id="rating" name="rating" max="10" min="0" value="${ratingPreviousValue}">

        <label for="aantalPaginas">Aantal pagina's: *</label>
        <input type="number" name="aantalPaginas" value="${aantalPaginasPreviousValue}" id="aantalPaginas">

        <input type="submit" id="submit" value="Voeg toe">
    </form>
</main>

<jsp:include page="footer.jsp"/>
</body>
</html>