<%-- 
    Document   : OmniError
    Created on : Mar 16, 2021, 3:38:58 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
        <link rel="stylesheet" href="./src/main/webapp/styles.css"/>

    </head>
    <body>
        <h1>An unexpected error has happened...</h1>
        <img src="src/main/webapp/chettronglong.png" alt="Something is wrong"><br />
        <a href="logout">Click here to return to the home page</a><br />
        <!--        Or do that to view the error
        ${requestScope.OMNI_ERROR}
        -->
    </body>
</html>
