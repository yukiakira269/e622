<%-- 
    Document   : search
    Created on : Mar 14, 2021, 7:13:05 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="registration" class="anhnt.registration.RegistrationDAO" 
                     scope="session"/>
        <c:set var="userId" value="${param.txtUsername}"/>
        <h1>Welcome, ${registration.getFullname(userId)}</h1>
        Search for an account <br />
        <form action="search">
            <input type="text" name="txtSearch" value="${param.txtSearch}" />
            <input type="submit" value="Search" name="btAction"/>
        </form>

        <a href="logout">Log out</a>
    </body>
</html>
