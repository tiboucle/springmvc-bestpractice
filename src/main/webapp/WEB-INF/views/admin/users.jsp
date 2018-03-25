<%@taglib prefix="t" tagdir="/WEB-INF/tags/master" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<spring:url var="searchUrl" value="/admin/users/search"/>
<spring:url var="firstUrl" value="/admin/users/pages/1"/>
<spring:url var="lastUrl" value="/admin/users/pages/${userPage.totalPages}"/>
<spring:url var="prevUrl" value="/admin/users/pages/${currentIndex - 1}"/>
<spring:url var="nextUrl" value="/admin/users/pages/${currentIndex + 1}"/>
<spring:url var="userAddUrl" value="/admin/users/add"/>

<t:default title="Users">
    <jsp:body>
        <spring:url value="/admin/users/delete" var="deleteUrl"/>
        <div class="row news-details">
            <c:if test="${param.userCreated != null}">
                <div class="alert alert-success" role="alert">
                    <p>Add User Success</p>
                </div>
            </c:if>
            <c:if test="${param.userUpdated != null}">
                <div class="alert alert-success" role="alert">
                    <p>Update User Success</p>
                </div>
            </c:if>
            <c:if test="${param.userDeleted != null}">
                <div class="alert alert-success" role="alert">
                    <p>Delete User Success</p>
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
                <button class="btn btn-primary pull-right"
                        onclick="location.href='${userAddUrl}'">Add User
                </button>
            </div>
        </div>
        <div class="col-lg-12">
            <table class="table">
                <thead align="center">
                <tr align="center">
                    <th scope="col">Id</th>
                    <th scope="col">Username</th>
                    <th scope="col">Enabled</th>
                    <th scope="col" colspan="2">Action</th>
                </tr>
                </thead>
                <tbody>
                <sec:authentication var="userLogin" property="principal"/>
                <c:forEach items="${userList}" var="user" varStatus="counter">
                    <tr>
                        <th scope="row"><c:out value="${counter.index + 1}"/></th>
                        <td>${user.username}</td>
                        <td>
                            <input type="checkbox" name="enable"
                                   <c:if test="${user.enabled}">checked</c:if> disabled>
                        </td>
                        <td><a href="/admin/users/${user.id}">Edit</a></td>
                        <td>
                            <c:if test="${userLogin.username != user.username}">
                                <a href="/admin/users/delete/${user.id}">Delete</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="pagination">
                <ul class="nav nav-pills pull-right">
                    <c:if test="${fn:length(userList) > 0}">
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
                            <spring:url var="pageUrl" value="/admin/users/pages/${i}"/>
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
                            <c:when test="${currentIndex == userPage.totalPages}">
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