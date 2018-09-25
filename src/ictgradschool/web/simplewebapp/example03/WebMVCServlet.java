package ictgradschool.web.simplewebapp.example03;

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
public class WebMVCServlet extends HttpServlet {

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

            // Adding the article to the request object so that our article.jsp page can access it
            request.setAttribute("Article", article);

            // Redirect to /WEB-INF/example03/article_jstl.jsp, which will now have access to the article through its
            // request object, due to the line above.
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/example03/article_jstl.jsp");
            dispatcher.forward(request, response);

        }
    }

    /**
     * Gets a list of articles and forwards them to the appropriate JSP for display.
     */
    private void displayArticlesList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        try (ArticleDAO dao = new ArticleDAO()) {

            List<Article> articles = dao.getAllArticles();

            // Adding the article list to the request object so that our home_jstl.jsp page can access it
            request.setAttribute("Articles", articles);

            // Redirect to /WEB-INF/example03/home_jstl.jsp, which will now have access to the articles list through
            // its request object, due to the line above.
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/example03/home_jstl.jsp");
            dispatcher.forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
