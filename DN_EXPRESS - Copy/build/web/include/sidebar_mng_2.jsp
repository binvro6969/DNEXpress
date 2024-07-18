<%-- 
    Document   : sidebar_mng_2
    Created on : Jul 17, 2024, 9:42:25 PM
    Author     : dangq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

    <c:if test="${not empty drType}">
        <ul>
            <c:forEach var="dr_type" items="${drType}">
                <li>
                    <ul>
                        <li><a href="Manager_ListDriverStaff_Servlet?dtId=${dr_type.driv_type_id}">${dr_type.driv_type_value}</a></li>
                    </ul>
                </li>
            </c:forEach>
        </ul>
    </c:if>

</html>
