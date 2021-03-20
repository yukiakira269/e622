<%-- 
    Document   : manageTag
    Created on : Mar 19, 2021, 7:03:49 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./src/main/webapp/styles.css"/>

        <title>Manage Tag</title>
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
                        <a href="ADD_TAG_PAGE">Add a new Tag</a>
                        <a href="VIEW_ORDERS_PAGE">View past orders</a>

                    </nav>
                </div>
            </header>

            <h1>Welcome admin ${sessionScope.FULLNAME}</h1>
            <jsp:useBean id="tags" class="anhnt.tag.TagDAO" scope="session"/>
            <c:set var="tagList" value="${tags.allTag}"/>
            <c:if test="${not empty requestScope.DESC_LENGTH_ERROR}">
                <font style="color: red; font-weight: bold">
                ${requestScope.DESC_LENGTH_ERROR}
                </font>
            </c:if>
            <table border="1">
                <thead>
                    <tr>
                        <th>Tag ID</th>
                        <th>Description</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${tagList}" var="tag">
                    <form action="updateTag">
                        <tr>
                            <td>${tag.tagId}
                                <input type="hidden" name="tagId" 
                                       value="${tag.tagId}" />
                            </td>
                            <td>
                                <input type="text" name="Description" 
                                       value="${tag.description}" />
                            </td>
                            <td>
                                <input type="submit" value="Update" name="btAction"/>
                            </td>
                            <td>
                                <c:url var="del" value="deleteTag" scope="request">
                                    <c:param name="tagId" value="${tag.tagId}"/>
                                </c:url>
                                <a href="${del}">Delete</a>
                            </td>
                        </tr>
                    </form>

                </c:forEach>
            </table>
        </c:if>

    </body>
</html>
