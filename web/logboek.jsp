<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Sébastien
  Date: 8/12/2020
  Time: 11:59
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
    <meta charset="UTF-8">
    <title>Logboek zoekresultaten</title>
</head>
<body>

<header>
    <jsp:include page="navigatie.jsp"/>
    <h1>Boek Logboek</h1>
</header>

<main>
    <c:choose>
        <c:when test="${empty versiegeschiedenis}">
            <p>Er is nog geen versiegeschiedenis</p>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                <tr>
                    <th>Titel</th>
                    <th>Auteur</th>
                    <th>Genre</th>
                    <th>Rating</th>
                    <th>Aantal Pagina's</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="boek" items="${versiegeschiedenis}">
                    <tr>
                        <td>${boek.titel}</td>
                        <td>${boek.auteur}</td>
                        <td>${boek.genre}</td>
                        <td>${boek.rating}</td>
                        <td>${boek.aantalPaginas}</td>
                        </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</main>

<jsp:include page="footer.jsp"/>

</body>
</html>
