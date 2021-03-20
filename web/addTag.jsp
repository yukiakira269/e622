<%-- 
    Document   : addTag
    Created on : Mar 19, 2021, 7:45:49 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./src/main/webapp/styles.css"/>
        <title>Adding Tag</title>
    </head>
    <body>

    <c:if test="${not sessionScope.ADMIN_STATUS}">
        <h1>You are not an admin</h1>
        <a href="logout">Log out</a>
    </c:if>
    <c:if test="${sessionScope.ADMIN_STATUS}">

        <form action="addTag">
            <h1>Please enter the following: </h1>
            TagId: <input type="text" name="txtTagId" value="" />
            Description: <input type="text" name="txtDescription" value="" />
            <input type="submit" value="Add" name="btAction"/>
        </form>

        <font style="color: red; font-weight: bold">
        <c:if test="${not empty requestScope.UNAVAILABLE_ERROR}">
            ${requestScope.UNAVAILABLE_ERROR}
        </c:if>
        <c:if test="${not empty requestScope.NUMBER_ERROR}">
            ${requestScope.NUMBER_ERROR}
        </c:if>
        <c:if test="${not empty requestScope.DESC_LENGTH_ERROR}">
            ${requestScope.DESC_LENGTH_ERROR}
        </c:if>
        </font>
        <br /> <a href="MANAGE_TAG_PAGE">Back</a>
    </c:if>

</body>
</html>
