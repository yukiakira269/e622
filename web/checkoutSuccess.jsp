<%-- 
    Document   : checkoutSuccess
    Created on : Mar 17, 2021, 3:38:51 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="error"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order is successful</title>
        <link rel="stylesheet" href="./src/main/webapp/styles.css"/>

        <jsp:useBean id="product" class="anhnt.product.ProductDAO" scope="session"/> 
    </head>
    <body>
        <h1>The order has been made!!!</h1><br />
        <c:set var="order" value="${requestScope.RECEIPT}"/>
        <c:if test="${not empty order}">
            <h2>Order's information: </h2><br />
            Order No. ${order.orderId}<br />
            Customer's Name ${order.custName}<br />
            Customer's Address ${order.custAddress}<br />
            Date purchased: ${order.date}<br />
            Items included:

            <table border="1">
                <thead>
                    <tr>
                        <th>Item No.</th>
                        <th>Product ID</th>
                        <th>Quantity purchased</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.RECEIPT_DETAIL}" var="detail"
                               varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>${detail.productId}</td>
                            <td>${detail.orderQuantity}</td>
                            <td>${product.getProductDesc(detail.productId)}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty order}">
            Empty order! There is nothing to account for!!!
        </c:if>
        <a href="SHOP_PAGE">Back to shopping page</a>
        <a href="logout">Exit</a>
    </body>
</html>
