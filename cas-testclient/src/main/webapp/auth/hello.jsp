<%@ page import="java.io.*,java.util.Locale" %>
<%@ page import="javax.servlet.*,javax.servlet.http.* "%>
<%
    Locale l = request.getLocale();    
%>

<html>
<body>
	hello auth. jsp
        
    <p><% out.println("LOCALE  :" + l); %></p>
</body>
</html>
