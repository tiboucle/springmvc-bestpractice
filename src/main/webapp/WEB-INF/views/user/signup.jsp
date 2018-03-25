<%@taglib prefix="t" tagdir="/WEB-INF/tags/master" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:default title="Registration">
    <jsp:body>
        <spring:url value="/signup/add" var="formRegisterUrl"/>
        <spring:url value="/login" var="signInUrl"/>
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <h4>Sign Up</h4>
                <form:form method="post" modelAttribute="user" action="${formRegisterUrl}">
                    <div class="form-group">
                        <form:hidden class="form-control" id="id" path="id"/>
                    </div>
                    <div class="form-group">
                        <label for="username">Username</label>
                        <form:input class="form-control" id="username" path="username"/>
                        <form:errors path="username" cssClass="error" />
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <form:password class="form-control" id="password" path="password"/>
                        <form:errors path="password" cssClass="error" />
                    </div>
                    <div class="form-group">
                        <form:button type="submit" class="btn btn-primary">Submit</form:button>
                    </div>
                </form:form>
                <div>Do you have account? Please <a href="${signInUrl}">sign in</a></div>
            </div>
        </div>
    </jsp:body>
</t:default>