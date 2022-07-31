<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h1>New Ninja</h1>
<form:form action="/ninjas/new" method="post" modelAttribute="ninja">
    <div>
        <form:label path="dojo">Dojo :</form:label>
        <form:select path="dojo">
            <c:forEach var="d" items="${dojos}">
                <form:option value="${d.id}" path="dojo">
                    <c:out value="${d.name}"/>
                </form:option>
            </c:forEach>
        </form:select>
    </div>
    <div>
        <form:label path="firstName">First Name :</form:label>
        <form:input path="firstName"/>
    </div>
    <div>
        <form:label path="lastName">Last Name :</form:label>
        <form:input path="lastName"/>
    </div>
    <div>
        <form:label path="age">Age :</form:label>
        <form:input type="number" path="age"/>
    </div>
    <input type="submit" value="Create">
</form:form> 
</body>
</html>