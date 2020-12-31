<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.model.Boek" %>
<%@ page import="java.util.Collection" %>
<%--
  Created by IntelliJ IDEA.
  User: Sébastien
  Date: 24/11/2020
  Time: 11:19
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
    <title>Update</title>
</head>
<body>
<header>
    <jsp:include page="navigatie.jsp"/>
    <h1>Update</h1>
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

    <article>
        <p>Hier kan u het boek ${param.titel} aanpassen</p>
        <form action="BoekInfo?command=updateBevestig" method="post" novalidate>
            <p><label for="titel">titel *</label>
                <input type="text" id="titel" name="titel" value="${param.titel}"></p>

            <p><label for="auteur">auteur *</label>
                <input type="text" id="auteur" name="auteur" value="${param.auteur}"></p>

            <p><label for="genre">genre *</label>
                <input type="text" id="genre" name="genre" value="${param.genre}"></p>

            <p><label for="rating">Rating *</label>
                <input type="number" id="rating" name="rating" value="${param.rating}"></p>

            <p><label for="aantalPaginas">aantal Pagina's *</label>
                <input type="number" id="aantalPaginas" name="aantalPaginas" value="${param.aantalPaginas}"></p>

            <input type="submit" id="submit" name="submit" value="UPDATE">
        </form>
    </article>
</main>

<!--jsp:include page="footer.jsp"/>--->

</body>
</html>
