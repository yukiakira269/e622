<%-- 
    Document   : gallery
    Created on : Mar 15, 2021, 7:50:03 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gallery</title>
    </head>
    <body>
        <jsp:useBean id="registration" class="anhnt.registration.RegistrationDAO"
                     scope="session"/>
        <!--Retrieve username from the Request object's parameter-->
        <c:set var="userId" value="${param.txtUsername}"/>
        <h1>Welcome, ${registration.getFullname(userId)}</h1>

        <form action="tagSearch">
            Search for a specific tag:
            <input type="text" name="txtTag" value="" />
            <input type="submit" value="Search" />
        </form>
    </body>
</html>
