<%@taglib prefix="t" tagdir="/WEB-INF/tags/master" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:default title="Form News">
    <jsp:body>
        <div class="row news-add">
            <c:if test="${newsCreated eq false or newsUpdated eq false}">
                <div class="alert alert-danger" role="alert">
                    <p>Something error in fields</p>
                </div>
            </c:if>

            <div class="col-lg-12">
                <div class="row">
                    <h3>News Form</h3>
                    <c:choose>
                        <c:when test="${newsForm.id == null}">
                            <c:set var="formAction" value="/news/add" />
                        </c:when>
                        <c:otherwise>
                            <c:set var="formAction" value="/news/update" />
                        </c:otherwise>
                    </c:choose>
                    <form:form method="post" modelAttribute="newsForm" action="${formAction}">
                        <div class="form-group">
                            <form:input type="hidden" class="form-control" id="id" path="id" />
                        </div>
                        <div class="form-group">
                            <label for="title">Title</label>
                            <form:input type="text" class="form-control" id="title" path="title" />
                            <form:errors path="title" cssClass="error" />
                        </div>
                        <div class="form-group">
                            <label for="content">Content</label>
                            <form:textarea class="form-control" rows="5" id="content" path="content" />
                            <form:errors path="content" cssClass="error" />
                        </div>
                        <div class="form-group">
                            <label for="author">Author</label>
                            <form:input type="text" class="form-control" id="author" path="author" />
                            <form:errors path="author" cssClass="error" />
                        </div>
                        <div class="form-group">
                            <label for="publishedDate">Published Date</label>
                            <form:input type="date" class="form-control" id="publishedDate" path="publishedDate" />
                            <form:errors path="publishedDate" cssClass="error" />
                        </div>
                        <form:button type="submit" class="btn btn-primary">Submit</form:button>
                    </form:form>
                </div>
            </div>
        </div>
    </jsp:body>
</t:default>