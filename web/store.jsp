<%-- 
    Document   : store
    Created on : Mar 16, 2021, 3:37:22 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="error"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
        <link rel="stylesheet" href="./src/main/webapp/styles.css"/>

    </head>
    <body>
        <jsp:useBean id="registration" class="anhnt.registration.RegistrationDAO"
                     scope="session"/>
        <!--Retrieve username from the Request object's parameter-->
        <h1>Welcome ${sessionScope.FULLNAME}</h1>

        <form action="tagSearch">
            Looking for something specific?
            <input type="text" name="txtTag" value="${param.txtTag}" />
            <input type="submit" value="Search" name="btAction"/>
        </form>
        <form action="cart">
            <input type="submit" value="View Cart" name="btAction"/>
        </form>
        <a href="logout">Log out</a>

        <jsp:useBean id="product" class="anhnt.product.ProductDAO" 
                     scope="session"/>
        <c:set var="searchTag" value="${param.txtTag}"/>
        <jsp:useBean id="tag" class="anhnt.tag.TagDAO" scope="session"/>
        <c:if test="${empty searchTag}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                            <c:forEach items="${product.columnNames}" var="columnName">
                            <th>${columnName}</th>
                            </c:forEach>
                        <th>Add to Cart</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach items="${product.bookList}" var="book" varStatus="counter">
                        <!--Each form is a row, a user can 
                        add individual books per addition--> 

                    <form action="cart">
                        <tr>
                            <td>${counter.count}</td>
                            <td>
                                ${book.productId}
                                <input type="hidden" name="txtProductId" 
                                       value="${book.productId}" />
                            </td>
                            <td>
                                <!--
                                ${book.storeQuantity}
                                <input type="hidden" name="txtStoreQuantity"
                                       value="${book.storeQuantity}" />
                                -->
                                <c:if test="${book.storeQuantity gt 0}">
                                    AVAILABLE
                                </c:if>
                                <c:if test="${book.storeQuantity eq 0}">
                                    <font color="red">OUT OF STOCK</font
                                </c:if>
                                <input type="hidden" name="txtStoreQuantity"
                                       value="${book.storeQuantity}"/>
                            </td>
                            <td>
                                <c:set var="tagId" value="${book.tags}"/>
                                ${tag.matchTag(tagId)}
                            </td>
                            <td>
                                ${book.productDesc}
                            </td>
                            <td>
                                <input type="hidden" name="lastTagValue" 
                                       value="${searchTag}" />
                                <input type="submit" value="Add To Cart" 
                                       name="btAction"/>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                <tr>
                    <td colspan="6" style="text-align: center; 
                        font-weight: bold; color: red">
                        BEST SELLERS
                    </td>
                </tr>
            </tbody>
        </table>
    </c:if>

    <c:if test="${not empty searchTag}">
        <c:if test="${not empty requestScope.TAG_SEARCH}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                            <c:forEach items="${product.columnNames}" var="columnName">
                            <th>${columnName}</th>
                            </c:forEach>
                        <th>Add to Cart</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.TAG_SEARCH}" var="book"
                               varStatus="counter">
                    <form action="add">
                        <tr>
                            <td>${counter.count}</td>
                            <td>
                                ${book.productId}
                                <input type="hidden" name="txtProductId" 
                                       value="${book.productId}" />
                            </td>
                            <td>
                                <!--
                                ${book.storeQuantity}
                                <input type="hidden" name="txtStoreQuantity"
                                       value="${book.storeQuantity}" />
                                -->
                                <c:if test="${book.storeQuantity gt 0}">
                                    AVAILABLE
                                </c:if>
                                <c:if test="${book.storeQuantity lt 0}">
                                    <font color="red">OUT OF STOCK</font>
                                </c:if>
                                <input type="hidden" name="txtStoreQuantity"
                                       value="${book.storeQuantity}" />
                            </td>
                            <td>
                                <c:set var="tagId" value="${book.tags}"/>
                                ${tag.matchTag(tagId)}
                            </td>
                            <td>
                                ${book.productDesc}
                            </td>
                            <td>
                                <input type="hidden" name="lastTagValue" 
                                       value="${searchTag}" />
                                <input type="submit" value="Add To Cart" 
                                       name="btAction"/>
                            </td>
                        </tr>
                    </form>

                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty requestScope.TAG_SEARCH}">
        <h2>Category not found!</h2>
    </c:if>
</c:if>
</body>
</html>

