<%--
  Created by IntelliJ IDEA.
  User: KaizerSX
  Date: 18.06.2018
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<style>
    h2 {text-align: center}
</style>
<head>
    <title>Login page</title>
</head>
<body>

<h2>Login page: </h2>

<form:form  action="/check" commandName="usera" method="post" >

    <fieldset>
        <form:label path="name"> Name: </form:label> <br><br>
        <form:input path="name"/>
        <form:errors path="name" cssStyle="color: brown"/><br><br>

        <form:label path="password">Password: </form:label><br><br>
        <form:password path="password" />
        <form:errors path="password"  cssStyle="color: brown"/>

    </fieldset>
<footer>

    <input type="submit" value="login">
</footer>


</form:form>

</body>
</html>
