<%-- 
    Document   : 500error
    Created on : Mar 11, 2021, 9:07:29 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Maintenance warning</title>
    </head>
    <body>
        <h1>This segment is currently under maintenance!</h1>
        <font color="red">Please try again later!!!</font>
        <!--
        <%= exception.getMessage()%>  
        -->
        <img src="src/main/webapp/timtim.png" alt="Sorry for the inconvenience"/>
        <a href="login.html">Click here to return to the login page!!!</a>
    </body>
</html>
