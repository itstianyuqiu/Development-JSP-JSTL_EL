<!DOCTYPE html>
<%@page contentType="text/html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Simple Articles JSTL</title>
</head>

<body>

<section id="view" class="container">
    <c:forEach var="article" items="${Articles}">
        <section class="article">
            <p>
                <a href="?article=${article.id}">${article.title}</a>
            </p>
        </section>
    </c:forEach>
</section>
</body>
</html>
