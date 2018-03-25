<%@taglib prefix="t" tagdir="/WEB-INF/tags/master" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:url var="addUserUrl" value="/admin/users/add"/>
<spring:url var="updateUserUrl" value="/admin/users/update"/>

<t:default title="Form News">
    <jsp:body>
        <div class="row news-add">
            <c:if test="${userCreated eq false or userUpdated eq false}">
                <div class="alert alert-danger" role="alert">
                    <p>Something error in fields</p>
                </div>
            </c:if>

            <div class="col-lg-6">
                <div class="row">
                    <h3>User Form</h3>
                    <c:choose>
                        <c:when test="${user.id == null}">
                            <c:set var="formAction" value="${addUserUrl}"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="formAction" value="${updateUserUrl}"/>
                        </c:otherwise>
                    </c:choose>
                    <form:form method="post" modelAttribute="user" action="${formAction}">
                    <div class="form-group">
                        <form:input type="hidden" class="form-control" id="id" path="id"/>
                    </div>
                    <div class="form-group">
                        <label for="username">Username</label>
                        <form:input type="text" class="form-control" id="username"
                                    path="username"/>
                        <form:errors path="username" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <form:password class="form-control" id="password" path="password"/>
                        <form:errors path="password" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label for="roles">Roles</label>
                        <form:select path="roles" class="form-control" items="${roleList}"
                                     itemLabel="code" size="2"
                                     multiple="true">
                        </form:select>
                        <form:errors path="roles" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <form:checkbox id="enabled" path="enabled" label="enabled"/>
                    </div>
                </div>
                <form:button type="submit" class="btn btn-primary">Submit</form:button>
                </form:form>
            </div>
        </div>
    </jsp:body>
</t:default>