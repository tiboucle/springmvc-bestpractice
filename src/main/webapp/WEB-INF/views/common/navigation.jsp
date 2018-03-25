<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/news" var="newsUrl"/>
<spring:url value="/logout" var="logoutUrl"/>
<spring:url value="/admin/users" var="usersUrl"/>

<div class="header clearfix">
    <nav>
        <ul class="nav nav-pills pull-right">
            <sec:authorize access="isAuthenticated()">
                <li role="presentation"><a href="${newsUrl}">News</a></li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                <li role="presentation"><a href="${usersUrl}">User</a></li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li role="presentation"><a href="${logoutUrl}">Logout</a></li>
            </sec:authorize>
        </ul>
    </nav>
    <h3>Spring News</h3>
</div>