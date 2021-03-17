<%-- 
    Document   : checkout
    Created on : Mar 17, 2021, 8:11:17 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Order Details</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./src/main/webapp/styles.css"/>

    </head>
    <body>
        <h1>Thank you for choosing us!!!</h1><br />
        <img src="src/main/webapp/dokidoki.png" alt="Thank you"/>
        <h2>Order's details</h2><br />
        <form action="cart">
            Please fill in these below information:
            Your name: <input type="text" name="custName" value="${sessionScope.FULLNAME}" /><br />
            Your Address: <input type="text" name="custAddress" value="" /><br />
            <input type="submit" value="Order" name="btAction"/>
        </form>
        <c:if test="${not empty requestScope.EMPTY_ERROR}">
            <font color="red">${requestScope.EMPTY_ERROR}</font>
        </c:if>

    </body>
</html>
