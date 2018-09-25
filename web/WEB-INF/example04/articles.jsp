<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="col-md-12">
    <c:choose>
        <c:when test="${fn:length(articles) gt 0}">
            <c:forEach var="article" items="${articles}">
                <form class="form-inline" action="example04" method="POST">
                    <div class="panel panel-default">

                        <div class="panel-heading">
                            <h3 class="panel-title pull-left"><a href="?article=${article.id}">${article.title}</a></h3>
                            <button type="submit" class="btn btn-danger pull-right">Delete</button>
                            <div class="clearfix"></div>
                        </div>
                        <div class="panel-body">
                            <p>${article.content}</p>
                        </div>
                    </div>

                    <input type="hidden" name="operation" value="delete">
                    <input type="hidden" name="articleId" value="${article.id}">
                </form>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p>No articles!</p>
            <img src="images/t-rex-cry.png">
        </c:otherwise>
    </c:choose>

    <!--  A form letting users add new articles. -->
    <div class="panel panel-info">

        <div class="panel-heading">
            <h3 class="panel-title">New Article</h3>
        </div>
        <div class="panel-body">
            <form action="example04" method="POST">
                <div class="form-group">
                    <label for="title">Title</label>
                    <input type="text" id="title" name="title" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="content">Content</label>
                    <textarea id="content" name="content" class="form-control" rows="10" required></textarea>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Post</button>
                </div>

                <input type="hidden" name="operation" value="add">
            </form>
        </div>

    </div>
</div>