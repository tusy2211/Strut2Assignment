<%-- 
    Document   : welcome
    Created on : 24-Oct-2016, 18:25:13
    Author     : khanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table,thead,tbody,td {
                border: 1px solid black;
            }
            table{
                text-align: center;
                margin: auto;
            }
        </style>
    </head>
    <body>
        <h4>Welcome <s:property value="#session.username" /></h4>
        <h4><a href="logout">Logout</a></h4>
        <h1 style="text-align: center">List Product</h1>
        <table>
            <thead>
                <tr>
                    <td>Id</td>
                    <td>Name</td>
                    <td>Price</td>
                    <td>Amount</td>
                    <td>detail</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listsp}" var="sp">
                    <tr>
                        <td>${sp.id}</td>
                        <td>${sp.name}</td>
                        <td>${sp.price}</td>
                        <td>${sp.amount}</td>
                        <td>${sp.details}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br/>
    </body>
</html>
