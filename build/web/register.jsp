<%-- 
    Document   : register
    Created on : Mar 14, 2021, 10:07:50 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>SIGN UP HERE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./src/main/webapp/styles.css"/>

    </head>
    <body>
        <form action="register">
            <c:set var="error" value="${requestScope.ERROR}"/>
            Username <input type="text" name="txtUsername" value="${param.txtUsername}" /> 
            5 - 15 characters<br />
            <c:if test="${not empty error.usernameLengthErr}">
                <font color="red">${error.usernameLengthErr}</font><br />
            </c:if>
            Password <input type="password" name="txtPassword" value="" />
            5 - 15 characters<br />
            <c:if test="${not empty error.passwordLengthErr}">
                <font color="red">${error.passwordLengthErr}</font><br />
            </c:if>

            Confirm password <input type="password" name="txtConfirm"
                                    value="" /><br />
            <c:if test="${not empty error.confirmNotMatchErr}">
                <font color="red">${error.confirmNotMatchErr}</font><br />
            </c:if>

            Full Name <input type="text" name="txtFullname" value="${param.txtFullname}" />
            5 - 40 characters<br />
            <c:if test="${not empty error.fullnameLengthErr}">
                <font color="red">${error.fullnameLengthErr}</font><br />
            </c:if>
            <c:if test="${not empty error.usernameDuplicatedErr}">
                <font color="red">${error.usernameDuplicatedErr}</font><br />
            </c:if>

            <input type="submit" value="Register" name="btAction"/>
            <input type="reset" value="Reset" />


        </form>

    </body>
</html>
