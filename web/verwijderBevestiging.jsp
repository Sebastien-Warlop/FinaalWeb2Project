<%--
  Created by IntelliJ IDEA.
  User: Sébastien
  Date: 19/10/2020
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="style/style.css">
    <title>Bevestiging</title>
</head>
<body>
<header>
    <jsp:include page="navigatie.jsp"/>
    <h1>Bibliotheek</h1>
</header>
<main>
    <article>
        <h2>verwijder boek</h2>
        <p>Ben je zeker dat je het boek: "${param.titel}" wil verwijderen?</p>
            <form action="BoekInfo?command=delete&titel=${param.titel}" method="POST">
                <input type="submit" value="Zeker">
            </form>
        <p><a href="BoekInfo?command=overzicht">Toch niet</a></p>
    </article>
</main>

<jsp:include page="footer.jsp"/>

</body>
</html>
