<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="domain.model.Boek" %><%--
  Created by IntelliJ IDEA.
  User: Sébastien
  Date: 27/09/2020
  Time: 12:29
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
    <title>Overzicht Boeken</title>
</head>
<body>
<header>
    <jsp:include page="navigatie.jsp"/>
    <h1>Overzicht boeken</h1>
</header>

<main>
    <c:choose>
        <c:when test="${empty alleBoeken}">
            <p>Er zijn geen boeken in de bibliotheek</p>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                    <tr>
                        <th><a class="sorteer" href="BoekInfo?command=sorteer&categorie=Titel">Titel</a></th>
                        <th><a href="BoekInfo?command=sorteer&categorie=Auteur">Auteur</a></th>
                        <th><a href="BoekInfo?command=sorteer&categorie=Genre">Genre</a></th>
                        <th><a href="BoekInfo?command=sorteer&categorie=Rating">Rating</a></th>
                        <th><a href="BoekInfo?command=sorteer&categorie=AantalPaginas">Aantal Pagina's</a></th>
                        <th>Verwijder</th>
                        <th>Update</th>
                    </tr>
                </thead>
                    <tbody>
                        <c:forEach var="boek" items="${alleBoeken}">
                            <tr>
                                <td>${boek.titel}</td>
                                <td>${boek.auteur}</td>
                                <td>${boek.genre}</td>
                                <td>${boek.rating}</td>
                                <td>${boek.aantalPaginas}</td>
                                <td><a href="BoekInfo?command=verwijderBevestiging&titel=${boek.titel}">Verwijder</a></td>
                                <td><a href="BoekInfo?command=update&titel=${boek.titel}&auteur=${boek.auteur}&genre=${boek.genre}&rating=${boek.rating}&aantalPaginas=${boek.aantalPaginas}">Update</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
            </table>
        </c:otherwise>
    </c:choose>

    <p>Het langste boek in de bibliotheek is <strong>${langsteBoek.titel}</strong>.</p>

    <p>Klik <a href="logboek.jsp">Hier</a> om een logboek van updates te zien</p>

</main>
<jsp:include page="footer.jsp"/>
</body>
</html>
