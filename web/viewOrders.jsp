<%-- 
    Document   : viewOrders
    Created on : Mar 19, 2021, 7:46:35 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All past Orders</title>
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
                        <a href="inventory">Manage inventory</a>
                        <a href="tag">Manage Tags</a>
                    </nav>
                </div>
            </header>
            <h1>All committed orders:</h1>
            <jsp:useBean id="orderList" class="anhnt.orders.OrdersDAO" scope="session"/>
            `<c:set var="list" value="${orderList.orderListAll}"/>
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Order ID</th>
                        <th>Customer's name</th>
                        <th>Customer's address</th>
                        <th>Date committed</th>
                        <th>Details</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="order" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${order.orderId}</td>
                            <td>${order.custName}</td>
                            <td>${order.custAddress}</td>
                            <td>${order.date}</td>
                            <td>
                                <c:url var="viewDetail" value="ORDER_DETAILS_PAGE" scope="request">
                                    <c:param name="orderId" value="${order.orderId}"/>
                                </c:url>
                                <a href="${viewDetail}" target="_blank">View Details</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

    </body>
</html>
