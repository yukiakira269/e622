<%-- 
    Document   : manageStore
    Created on : Mar 19, 2021, 3:01:57 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Current Inventory</title>
        <link rel="stylesheet" href="./src/main/webapp/styles.css"/>

    </head>
    <body>
        <c:if test="${not sessionScope.ADMIN_STATUS}">
            <h1>You are not an admin</h1>
            <a href="logout">Log out</a>
        </c:if>
        <c:if test="${sessionScope.ADMIN_STATUS}">
            <header>
                <div>
                    <nav>
                        <a href="logout">Log out</a>
                        <a href="SEARCH_PAGE">Manage accounts</a>
                        <a href="tag">Manage Tags</a>
                        <a href="ADD_BOOK_PAGE">Add a new Entry</a>
                        <a href="VIEW_ORDERS_PAGE">View past orders</a>

                    </nav>
                </div>
            </header>


            <h1>Welcome admin ${sessionScope.FULLNAME}</h1>
            <form action="bookSearch">
                Search for a specific entry (by tag or name):
                <input type="text" name="txtTag" value="${param.txtTag}" />
                <!--Distinguish with a user's search-->
                <input type="hidden" name="txtAdmin" value="isAdmin"/>
                <input type="submit" value="Search" name="btAction"/>
            </form>
            <jsp:useBean id="product" class="anhnt.product.ProductDAO" 
                         scope="session"/>
            <jsp:useBean id="tag" class="anhnt.tag.TagDAO" scope="session"/>

            <c:if test="${empty requestScope.TAG_SEARCH}">
                <c:set var="inventory" value="${product.allList}"/>

                <c:if test="${not empty inventory}">
                    <h2>Current asset in the inventory: </h2><br /> 
                    <font style="color: red; font-weight: bold">
                    <c:if test="${not empty requestScope.EMPTY_ERROR}">
                        ${requestScope.EMPTY_ERROR}
                    </c:if>        
                    <c:if test="${not empty requestScope.NUMBER_ERROR}">
                        ${requestScope.NUMBER_ERROR}
                    </c:if>
                    <c:if test="${not empty requestScope.NUMBER_ERROR}">
                        ${requestScope.LENGTH_ERROR}
                    </c:if>
                    </font>

                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                    <c:forEach items="${product.columnNames}" var="columnName">
                                    <th>${columnName}</th>
                                    </c:forEach>
                                <th>Image</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--Show all list-->
                            <c:forEach items="${inventory}" var="book" varStatus="counter">
                            <form action="inventory">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>
                                        ${book.productId}
                                        <input type="hidden" name="productId"
                                               value="${book.productId}" />
                                    </td>
                                    <td>
                                        <input type="text" name="storeQuantity"
                                               value="${book.storeQuantity}" />

                                    </td>
                                    <td>
                                        <c:set var="tagId" value="${book.tags}"/>
                                        ${tag.matchTag(tagId)}
                                    </td>
                                    <td>
                                        <input type="text" name="productDesc" 
                                               value="${book.productDesc}" />
                                    </td>
                                    <td>
                                        <img src="./src/main/webapp/books/${book.productId}.png"
                                             alt="thumbnail"/>
                                    </td>
                                    <td>
                                        <input type="submit" value="Update Book" name="btAction"/>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>   
            <c:if test="${empty inventory}">
                The inventory is currently empty!!!
            </c:if>
        </c:if>

        <c:if test="${not empty requestScope.TAG_SEARCH}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                            <c:forEach items="${product.columnNames}" var="columnName">
                            <th>${columnName}</th>
                            </c:forEach>
                        <th>Image</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <!--Show only searched list-->
                    <c:set var="inventory" value="${requestScope.TAG_SEARCH}"/>
                    <c:forEach items="${inventory}" var="book" varStatus="counter">
                    <form action="inventory">
                        <tr>
                            <td>${counter.count}</td>
                            <td>
                                ${book.productId}
                                <input type="hidden" name="productId"
                                       value="${book.productId}" />
                            </td>
                            <td>
                                <input type="text" name="storeQuantity"
                                       value="${book.storeQuantity}" />

                            </td>
                            <td>
                                <c:set var="tagId" value="${book.tags}"/>
                                ${tag.matchTag(tagId)}
                            </td>
                            <td>
                                <input type="text" name="productDesc" 
                                       value="${book.productDesc}" />
                            </td>
                            <td>
                                <img src="./src/main/webapp/books/${book.productId}.png"
                                     alt="thumbnail"/>
                            </td>
                            <td>
                                <input type="submit" value="Update Book" name="btAction"/>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</c:if>

</body>
</html>
