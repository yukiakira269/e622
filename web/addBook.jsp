<%-- 
    Document   : addBook
    Created on : Mar 19, 2021, 7:45:41 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./src/main/webapp/styles.css"/>
        <title>Adding an item</title>
    </head>
    <body>



        <c:if test="${not sessionScope.ADMIN_STATUS}">
            <h1>You are not an admin</h1>
            <a href="logout">Log out</a>
        </c:if>
        <c:if test="${sessionScope.ADMIN_STATUS}">

            <c:set var="addBookObject" value="${requestScope.ADD_BOOK_ERROR}"/>
            <h1>Adding a new entry</h1>


            <jsp:useBean id="product" class="anhnt.product.ProductDAO" scope="session"/>
            <font style="color: red; font-weight: bold">
            <c:if test="${not empty requestScope.NOT_ADDED}">
                ${ requestScope.NOT_ADDED}<br />
            </c:if>
            <c:if test="${not empty addBookObject}">
                ${addBookObject.emptyFieldsError}<br />
            </c:if>   
            <c:if test="${not empty requestScope.NUMBER_ERROR}">
                ${requestScope.NUMBER_ERROR}<br />
            </c:if>
            </font>
            <form action="addBook" method="POST" enctype="multipart/form-data">
                Product's ID (given): ${product.lastBookId + 1}<br />
                <input type="hidden" name="txtProductId" value="${product.lastBookId + 1}" /><br />
                Quantity: (Less than 1000)<br />
                <input type="text" name="txtStoreQuantity" value="${param.txtStoreQuantity}" /><br />
                <font style="color: red; font-weight: bold">
                <c:if test="${not empty addBookObject}">
                    ${addBookObject.numberFormatError}<br />
                </c:if>        
                </font>
                Tag:
                <a href="MANAGE_TAG_PAGE" target="_blank">(Please refer to the tags list)</a><br />
                <input type="text" name="txtTagId" value="${param.txtTagId}" /><br />
                <font style="color: red; font-weight: bold">
                <c:if test="${not empty addBookObject}">
                    ${addBookObject.idNotFoundError}<br />
                </c:if>        
                </font>
                Product's Description: <br />
                <input type="text" name="txtProductDescription" value="${param.txtProductDescription}" /><br />
                <font style="color: red; font-weight: bold">
                <c:if test="${not empty addBookObject}">
                    ${addBookObject.descLengthError}<br />
                </c:if>        
                </font>
                <input type="file" name="imageFile"/><br />
                <input type="submit" value="Add" name="btAction"/>
            </form>

            <a href="MANAGE_SHOP_PAGE">Back</a>
        </c:if>

    </body>
</html>
