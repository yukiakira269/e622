<%-- 
    Document   : viewOrdersDetails
    Created on : Mar 20, 2021, 10:10:13 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail</title>
        <link rel="stylesheet" href="./src/main/webapp/styles.css"/>
    </head>
    <body>

        <c:if test="${not sessionScope.ADMIN_STATUS}">
            <h1>You are not an admin</h1>
            <a href="logout">Log out</a>
        </c:if>
        <c:if test="${sessionScope.ADMIN_STATUS}">

            <h1>The order's detail is as followed: </h1>
            ${param.orderId}
            <c:set var="id" value="${param.orderId}"/>
            <jsp:useBean id="detail" class="anhnt.ordersDetail.OrdersDetailDAO"
                         scope="request"/>
            <jsp:useBean id="product" class="anhnt.product.ProductDAO" 
                         scope="request"/>
            <table border="1">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Product ID</th>
                        <th>Product Description</th> 
                        <th>Ordered Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${detail.getOrderDetails(id)}" var="ord">
                        <tr>
                            <td>
                                ${ord.orderId}
                            </td>
                            <td>
                                ${ord.productId}
                            </td>
                            <td>
                                <c:set var="productId" value="${ord.productId}"/>
                                ${product.getProductDesc(productId)}
                            </td>
                            <td>
                                ${ord.orderQuantity}
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="VIEW_ORDERS_PAGE">Back</a>
        </c:if>

    </body>
</html>
