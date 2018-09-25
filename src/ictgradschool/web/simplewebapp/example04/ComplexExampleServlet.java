package ictgradschool.web.simplewebapp.example04;

import ictgradschool.web.simplewebapp.dao.ArticleDAO;
import ictgradschool.web.simplewebapp.model.Article;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Shows off web-MVC pattern. The associated JSPs also use JSTL.
 */
public class ComplexExampleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            if (request.getParameter("article") == null) {
                displayArticlesList(request, response);
            } else {
                displaySingleArticle(request, response, Integer.parseInt(request.getParameter("article")));
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

    }

    /**
     * Gets a single article and forwards it to the appropriate JSP for display.
     */
    private void displaySingleArticle(HttpServletRequest request, HttpServletResponse response, int articleId) throws ServletException, IOException, SQLException {

        try (ArticleDAO dao = new ArticleDAO()) {

            Article article = dao.getArticleById(articleId);
            if (article == null) {
                response.sendError(404);
                return;
            }

            // Adding the article to the request object
            request.setAttribute("article", article);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/example04/article-detail.jsp");
            dispatcher.forward(request, response);

        }
    }

    /**
     * Gets a list of articles and forwards them to the appropriate JSP for display.
     */
    private void displayArticlesList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        try (ArticleDAO dao = new ArticleDAO()) {

            List<Article> articles = dao.getAllArticles();

            // Adding the article list to the request object
            request.setAttribute("articles", articles);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/example04/articles.jsp");
            dispatcher.forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String op = request.getParameter("operation");

        try (ArticleDAO dao = new ArticleDAO()) {

            if ("add".equals(op)) {

                String title = request.getParameter("title");
                String content = request.getParameter("content");
                dao.addArticle(new Article(title, content));

            }

            else if ("delete".equals(op)) {

                int id = Integer.parseInt(request.getParameter("articleId"));
                dao.deleteArticle(id);

            }

        } catch (SQLException e) {
            throw new ServletException(e);
        }

        doGet(request, response);
    }

}
