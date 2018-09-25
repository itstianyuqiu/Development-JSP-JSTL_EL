<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ictgradschool.web.simplewebapp.db.*" %>
<%@ page import="ictgradschool.web.simplewebapp.dao.*" %>
<%@ page import="ictgradschool.web.simplewebapp.model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Simple Articles JSP</title>
</head>
<body>

<%

    try (ArticleDAO dao = new ArticleDAO()) {

        // A scriptlet - we can stick any Java code in here.
        if (request.getParameter("article") == null) {

            List<Article> articles = dao.getAllArticles();%>

            <section id="view" class="container">

                <%for (Article article : articles) {%>

                    <section class="article">

                        <p>
                           <a href="?article=<%=article.getId()%>"><%=article.getTitle()%></a>
                        </p>

                    </section>

                <%}%>

            </section>

        <%} else {

            Article article = dao.getArticleById(Integer.parseInt(request.getParameter("article")));%>

            <section id="view" class="container">

                <section class ="article">

                    <h1><%=article.getTitle()%></h1>
                    <p><%=article.getContent()%></p>


                </section>

            </section>

        <%}
    }%>

</body>
</html>
