<%-- 
    Document   : login
    Created on : 24-Oct-2016, 18:23:14
    Author     : khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOGIN</title>
        <style type="text/css">
            .errorMessage{
                color: red;
                font-size: 13px;
            }
        </style>
    </head>
    <body>
        <s:form method="post" action="login">
            <s:textfield name="username" label="Username" errorPosition="bottom"></s:textfield>
            <s:password name="password" label="Password" errorPosition="bottom"></s:password>
            <s:submit value="Login" align="left"></s:submit>
            
        </s:form>
        <h3 style="color: red"><s:property value="message" /></h3>
        
    </body>
</html>
