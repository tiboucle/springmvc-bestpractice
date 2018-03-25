<%@taglib prefix="t" tagdir="/WEB-INF/tags/master" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<spring:url var="firstUrl" value="/news/pages/1"/>
<spring:url var="lastUrl" value="/news/pages/${newsPage.totalPages}"/>
<spring:url var="prevUrl" value="/news/pages/${currentIndex - 1}"/>
<spring:url var="nextUrl" value="/news/pages/${currentIndex + 1}"/>
<spring:url var="searchUrl" value="/news/search"/>
<spring:url value="/news/add" var="newsAddUrl"/>

<t:default title="List of News">
    <jsp:body>
        <div class="row news-details">
            <c:if test="${param.newsCreated != null}">
                <div class="alert alert-success" role="alert">
                    <p>Add News Success</p>
                </div>
            </c:if>
            <c:if test="${param.newsDeleted != null}">
                <div class="alert alert-success" role="alert">
                    <p>Delete News Success</p>
                </div>
            </c:if>
            <div class="col-lg-9">
                <ul class="nav nav-pills pull-left">
                    <li>
                        <form method=get action="${searchUrl}" class="nav nav-pills pull-right">
                            <ul class="nav nav-pills pull-left">
                                <li><input type="hidden" name="action" value="search"></li>
                                <li><input type="text" class="form-control" name="searchText"></li>
                                <li><input type="submit" class="btn btn-primary" value="Search">
                                </li>

                            </ul>
                        </form>
                    </li>
                </ul>
            </div>
            <div class="col-lg-3">
                <sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                    <button class="btn btn-primary pull-right"
                            onclick="location.href='${newsAddUrl}'">Add News
                    </button>
                </sec:authorize>
            </div>
        </div>
        <div class="col-lg-12">
            <c:if test="${fn:length(newsList) == 0}">
                <h4>No News Result, Try search another..</h4>
            </c:if>
            <c:forEach items="${newsList}" var="news">
                <div class="row news-item">
                    <h3><a href="/news/${news.id}">${news.title}</a></h3>
                    <p>${fn:substring(news.content, 0, 120)}...</p>
                    <p class="text-muted small">
                        <strong>${news.author}</strong>, ${news.publishedDate}
                    </p>
                </div>
            </c:forEach>
            <div class="pagination">
                <ul class="nav nav-pills pull-right">
                    <c:if test="${fn:length(newsList) > 0}">
                        <c:choose>
                            <c:when test="${currentIndex == 1}">
                                <li class="disabled"><a href="#">&lt;&lt;</a></li>
                                <li class="disabled"><a href="#">&lt;</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="${firstUrl}">&lt;&lt;</a></li>
                                <li><a href="${prevUrl}">&lt;</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
                            <spring:url var="pageUrl" value="/news/pages/${i}"/>
                            <c:choose>
                                <c:when test="${i == currentIndex}">
                                    <li class="active"><a href="${pageUrl}"><c:out
                                            value="${i}"/></a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="${pageUrl}"><c:out value="${i}"/></a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${currentIndex == newsPage.totalPages}">
                                <li class="disabled"><a href="#">&gt;</a></li>
                                <li class="disabled"><a href="#">&gt;&gt;</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="${nextUrl}">&gt;</a></li>
                                <li><a href="${lastUrl}">&gt;&gt;</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </ul>
            </div>
        </div>
    </jsp:body>
</t:default>