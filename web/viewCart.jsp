<%-- 
    Document   : viewCart
    Created on : Mar 16, 2021, 10:40:37 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Cart</title>
    </head>
    <body>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:set var="keySet" value="${sessionScope.KEY_SET}" />
        <jsp:useBean id="dao" class="anhnt.product.ProductDAO" scope="session"/>
        <c:if test="${not empty keySet}">
            <h1>The cart currently has: </h1>
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Product ID</th>
                        <th>Description</th>
                        <th>Quantity in Cart</th>
                        <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                <form action="cart">
                    <c:forEach items="${keySet}" var="key" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${key}</td>
                            <td>${dao.getProductDesc(key)}</td>
                            <td>
                                ${cart.items[key]}
                                <input type="hidden" name="quantity" 
                                       value="${cart.items[key]}" />
                            </td>
                            <td>
                                <input type="checkbox" name="chkRemoved" 
                                       value="${key}" />
                            </td>
                        </tr>
                    </c:forEach>
                    <input type="submit" value="Remove Selected" name="btAction"/>
                    <input type="submit" value="Commit Purchase" name="btAction"/>
                </form>

            </tbody>
        </table>
    </c:if>
    <c:if test="${empty keySet}">
        <h1>The cart is currently empty!!!</h1>
    </c:if>
    <a href="SHOP_PAGE">Back to shopping page</a>


</body>
</html>
