<%-- 
    Document   : login
    Created on : Mar 18, 2021, 3:57:13 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./src/main/webapp/styles.css"/>
    </head>
    <body>
        <div>WELCOME!!!  LOGIN HERE!!!</div>
        <div>
            <img src="src/main/webapp/timtim.png" alt="Thank you for chooosing us"/>
            <form action="login" method="POST">
                Username <input type="text" name="txtUsername" value="" /> <br />  
                Password <input type="password" name="txtPassword" value="" /><br />
                Remember password? <input type="checkbox" name="checkRemember"
                                          value="Remember" /><br />
                <input type="submit" value="Login" name="btAction"/>
                <input type="reset" value="Reset" />
            </form>
            <a href="REGISTER_PAGE">Don't have an account? Click here to register</a><br />
            <a href="SHOP_PAGE">Or click here to start shopping</a><br />
            <font style="color:red;">${requestScope.ERROR}</font>
        </div>
    </body>
</html>
