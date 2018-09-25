package ictgradschool.web.simplewebapp.example01;

import ictgradschool.web.simplewebapp.dao.ArticleDAO;
import ictgradschool.web.simplewebapp.model.Article;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Gets articles and prints out some HTML to display them.
 */
public class SimpleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Print header info
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Simple Articles Servlet</title>");
        out.println("</head>");
        out.println("<body>");

        try {
            if (request.getParameter("article") == null) {
                displayArticlesList(out);
            } else {
                displaySingleArticle(response, out, Integer.parseInt(request.getParameter("article")));
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        // Print footer info
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Displays a list of articles as HTML.
     */
    private void displayArticlesList(PrintWriter out) throws IOException, SQLException {

        try (ArticleDAO dao = new ArticleDAO()) {

            List<Article> articles = dao.getAllArticles();

            out.println("<section id=\"view\" class=\"container\">");

            for (Article article : articles) {

                out.println("<section class =\"article\">");
                out.println("<p>");

                out.println("<a href=\"?article=" + article.getId() + "\">" + article.getTitle() + "</a>");

                out.println("</p>");
                out.println("</section>");

            }

            out.println("</section>");

        }

    }

    /**
     * Displays a single article as HTML.
     */
    private void displaySingleArticle(HttpServletResponse response, PrintWriter out, int articleId) throws IOException, SQLException {

        try (ArticleDAO dao = new ArticleDAO()) {

            Article article = dao.getArticleById(articleId);
            if (article == null) {
                response.sendError(404);
                return;
            }

            out.println("<section id=\"view\" class=\"container\">");
            out.println("<section class =\"article\">");

            out.println("<h1>" + article.getTitle() + "</h1>");
            out.println("<p>" + article.getContent() + "</p>");

            out.println("</section>");
            out.println("</section>");

        }
    }
}
