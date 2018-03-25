<%@taglib prefix="t" tagdir="/WEB-INF/tags/master" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:default title="List of News">
    <jsp:body>
        <spring:url value="/news/update/${news.id}" var="updateUrl"/>
        <spring:url value="/news/delete" var="deleteUrl"/>
        <spring:url value="/comment/add" var="addCommentUrl"/>
        <div class="row news-details">
            <c:choose>
                <c:when test="${param.commentCreated != null}">
                    <div class="alert alert-success" role="alert">
                        <p>Comment created!</p>
                    </div>
                </c:when>
                <c:when test="${param.commentNotCreated != null}">
                    <div class="alert alert-danger" role="alert">
                        <p>Comment can not created!</p>
                    </div>
                </c:when>
            </c:choose>
            <div class="col-lg-12">
                <div class="row news-item">
                    <h2>${news.title}</h2>
                    <p class="text-muted">
                        Written by <strong>${news.author}</strong>, ${news.publishedDate}
                    </p>
                    <p>${news.content}</p>
                    <sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                        <div class="col-lg-12">
                            <ul class="nav nav-pills pull-right">
                                <li>
                                    <button class="btn btn-primary"
                                            onclick="location.href='${updateUrl}'">Update
                                    </button>
                                </li>
                                <li>
                                    <form:form method="post" modelAttribute="news"
                                               action="${deleteUrl}">
                                        <form:input type="hidden" class="form-control" id="id"
                                                    path="id"/>
                                        <form:button type="submit"
                                                     class="btn btn-danger">Delete</form:button>
                                    </form:form>
                                </li>
                            </ul>
                        </div>
                    </sec:authorize>
                    <div class="row">
                        <div class="col-lg-6">
                            <h3>POST COMMENT</h3>
                            <form:form method="post" modelAttribute="comment"
                                       action="${addCommentUrl}">
                                <div class="form-group">
                                    <label for="text">Comment</label>
                                    <form:textarea class="form-control" rows="5" id="text"
                                                   path="text"/>
                                    <form:errors cssClass="error" path="text"/>
                                </div>
                                <div class="form-group">
                                    <label for="postedBy">Your Name</label>
                                    <form:input type="text" class="form-control" id="postedBy"
                                                path="postedBy"/>
                                    <form:errors cssClass="error" path="postedBy"/>
                                </div>
                                <form:input type="hidden" class="form-control" id="news"
                                            path="news"/>
                                <form:input type="hidden" class="form-control" id="postedAt"
                                            path="postedAt"/>
                                <form:button type="submit"
                                             class="btn btn-primary">Submit</form:button>
                            </form:form>
                        </div>
                    </div>
                    <div class="row comment-list">
                        <div class="col-lg-6">
                            <h3>ALL COMMENTS</h3>
                            <c:if test="${comments != null}">
                                <c:forEach items="${comments}" var="comment">
                                    <div>
                                        <p>${comment.text}</p>
                                        <p class="text-muted small">
                                            <strong>${comment.postedBy}</strong>, ${comment.postedAt}
                                        </p>
                                        <hr>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:default>
