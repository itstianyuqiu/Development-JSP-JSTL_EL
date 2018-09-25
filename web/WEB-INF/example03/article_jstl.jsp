<!DOCTYPE html>
<%@page contentType="text/html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Simple Articles JSTL</title>
</head>

<body>

<section id="view" class="container">
    <section class="article">
        <h1>${Article.title}</h1>
        <p>${Article.content}</p>
    </section>
</section>
</body>
</html>
