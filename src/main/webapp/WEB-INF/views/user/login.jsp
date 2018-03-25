<%@taglib prefix="t" tagdir="/WEB-INF/tags/master" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:default title="Login">
    <jsp:body>
        <spring:url value="/login" var="loginUrl"/>
        <spring:url value="/signup" var="signupUrl"/>
        <c:if test="${param.error != null}">
            <div class="alert alert-danger" role="alert">
                <p>Invalid username and password</p>
            </div>
        </c:if>

        <c:if test="${param.logout != null}">
            <div class="alert alert-success" role="alert">
                <p>You have been logged out</p>
            </div>
        </c:if>
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <h4>Please Login</h4>
                <form method="POST" action="${loginUrl}">
                    <div class="form-group">
                        <input name="username" type="text" placeholder="Username"
                               class="form-control"/>
                    </div>
                    <div class="form-group">
                        <input name="password" type="password" placeholder="Password"
                               class="form-control"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Log In</button>
                    </div>
                </form>
                <div>Do not have account? Please <a href="${signupUrl}">signup</a></div>
            </div>
        </div>
    </jsp:body>
</t:default>