<%@ page import="java.io.*,java.util.Locale" %>
<%@ page import="javax.servlet.*,javax.servlet.http.* "%>
<%
    Locale l = request.getLocale();    
%>

<html>
<body>
<h2>Hello World! CAS TEST</h2>

<ul>
  <li> 
    <a href="../session/hello.jsp">login</a>
    </li>
  <li> 
    <a href="../session/hello.jsp">test seite mit cas gesichert</a>
    </li>
  <li> 
    <a href="../session/logout.jsp">logout</a>
    </li>
</ul>

<p><% out.println("LOCALE  :" + l); %></p>

</body>
</html>
