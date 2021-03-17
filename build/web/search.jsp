<%-- 
    Document   : search
    Created on : Mar 14, 2021, 7:13:05 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <link rel="stylesheet" href="./src/main/webapp/styles.css"/>

    </head>
    <body>
        <jsp:useBean id="registration" class="anhnt.registration.RegistrationDAO" 
                     scope="session"/>
        <!-- The user may persist after searching (i.e update,delete), 
        thus session scope is used -->
        <!--Retrieve username from the Request object's parameter-->
        <h1>Welcome admin ${sessionScope.FULLNAME}</h1>
        Search for an account <br />
        <form action="search">
            <input type="text" name="txtSearch" value="${param.txtSearch}" />
            <input type="submit" value="Search" name="btAction"/><br />
        </form>


        <c:if test="${not empty param.txtSearch}">
            <c:if test="${not empty requestScope.SEARCH_RESULT}">
                <table border="1">
                    <thead>
                        <tr>
                            <!-- Call the getter methods of column names in DAO-->
                            <c:set var="columnNames" 
                                   value="${registration.columnNames}"/>
                            <th>No.</th>
                                <c:forEach items="${columnNames}" var="col">
                                <th>${col}</th>
                                </c:forEach>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="searchResult" 
                               value="${requestScope.SEARCH_RESULT}"/>
                        <c:forEach items="${searchResult}" var="dto"
                                   varStatus="counter">
                        <form action="update">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.userId}
                                    <input type="hidden" name="txtUserId"
                                           value="${dto.userId}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword"
                                           value="${dto.password}" />

                                </td>
                                <td>
                                    ${dto.fullname}
                                </td>
                                <td>
                                    <!-- If isAdmin is true, the checkbox is checked-->
                                    <input type="checkbox" name="checkAdmin" 
                                           value="isAdmin" 
                                           <c:if test="${dto.isAdmin}">
                                               checked
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <input type="submit" value="Update"
                                           name="btAction"/>
                                    <input type="hidden" name="lastSearchValue" 
                                           value="${param.txtSearch}" />
                                </td>
                                <td>
                                    <c:url scope="request" var="del" value="delete">
                                        <c:param name="txtUserId"
                                                 value="${dto.userId}"/>
                                        <c:param name="txtLastSearchValue"
                                                 value="${param.txtSearch}"/>
                                    </c:url>
                                    <a href="${del}">Delete</a>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                    <c:if test="${not empty requestScope.PASS_LENGTH_ERR}">
                        <font color="red">
                        ${requestScope.PASS_LENGTH_ERR}
                        </font>
                    </c:if>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty requestScope.SEARCH_RESULT}">
            <font color="red">No matching entry of "${param.txtSearch}" found!
            Please make sure your query was correct</font><br />
        </c:if>
    </c:if>

    <a href="logout">Log out</a>
</body>
</html>
