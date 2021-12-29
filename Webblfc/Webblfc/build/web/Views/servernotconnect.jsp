<%-- 
    Document   : servernotconnect
    Created on : Oct 27, 2021, 8:27:26 PM
    Author     : teguh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>server ot connect</title>
    </head>
    <body>
        
          <% String Status = (String)request.getAttribute("status"); %>
          <h1><%= Status%></h1>
    </body>
</html>
