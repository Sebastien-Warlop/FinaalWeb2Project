<%@ page import="domain.model.Boek" %>
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
    <title>Home</title>
  </head>
  <body>
  <header id="index">
    <nav id="indexNav">
      <ul>
        <li><a id="current" href="BoekInfo?command=home">Home</a></li>
        <li><a href="voegToe.jsp">Voeg Toe</a></li>
        <li><a href="BoekInfo?command=overzicht">Overzicht</a></li>
        <li><a href="zoek.jsp">Zoek</a></li>
      </ul>
    </nav>
    <h1>Bibliotheek</h1>
  </header>
  <img class="bib" src="Afbeeldingen/cover.jpg" alt="bibliotheek afbeelding"/>

  <main>
    <article>
      <h2>Welkom</h2>
      <p>Dag boekenliefhebbers! Hier kunnen jullie je favoriete boeken toevoegen en beoordelen.</p>
      </article>
  </main>

  <footer class="footer">
    <p>Sebastien Warlop - Webontwikkeling 2 - 2020-2021</p>
  </footer>
  </body>
</html>