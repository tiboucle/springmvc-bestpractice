<%@taglib prefix="t" tagdir="/WEB-INF/tags/master" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:default title="List of User">
    <jsp:body>
        <sec:authentication var="user" property="principal"/>
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <H4>Welcome,
                    <sec:authorize access="isAuthenticated()">
                        ${user.username}!
                    </sec:authorize>
                </H4>
            </div>
        </div>
    </jsp:body>
</t:default>