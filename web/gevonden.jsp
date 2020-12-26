<%@ page import="domain.model.Boek" %><%--
  Created by IntelliJ IDEA.
  User: Sébastien
  Date: 24/10/2020
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Boek boek = (Boek) request.getAttribute("boek"); %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="style/style.css">
    <title>Zoek resultaten</title>
</head>
<body>
<header>
    <jsp:include page="navigatie.jsp"/>
    <h1>Zoek resulaten</h1>
</header>
<main>
    <p>Je vroeg naar volgende gegevens: ${boekInfo}</p>
</main>

<jsp:include page="footer.jsp"/>

</body>
</html>
