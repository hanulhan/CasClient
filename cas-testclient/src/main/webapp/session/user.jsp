<%@ page import="java.io.*,java.util.*" %>
<%@ page import="org.jasig.cas.client.authentication.AttributePrincipal" %>
<%@ page import="javax.servlet.*,javax.servlet.http.* "%>
<%
    Locale l = request.getLocale();    
%>

<html>
    <head>
        <meta charset="utf-8">
        <title>CAS Testclient</title>
        <style>table, td, th {border: 1px solid black; } </style>
    </head>
    <body>
        user info
        <%--
        <%
                out.println("You are succesfully logged in as user <b>"	+ request.getRemoteUser() + "</b><br/><br/>");

                out.println("principal: "	+ request.getUserPrincipal() + "</b><br/><br/>");
        
                AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
                Map attributes = principal.getAttributes();

                if (attributes.size() > 0) {

                        out.println("You have " + attributes.size() + " attributes : <br/>");
                        Iterator keyIterator = attributes.keySet().iterator();

                        while (keyIterator.hasNext()) {

                                Object key = keyIterator.next();
                                Object value = attributes.get(key);
                                out.println("<b>" + key + "</b>" + " : " + value);
                }
                } else {
                        out.println("You have no attributes set");
                }
        
            out.println("LOCALE  :" + l);
            out.println("NAME     :" + l.getDisplayName());
            out.println("Default :" + l.getDefault());

        %>	
        --%>

        <%
        out.println("<h1>You are succesfully logged in as user <b>" + request.getRemoteUser() + "</b></h1><br/><br/>");

        out.println("<table><tr><th>key</th><th>value</th></tr>");
        out.println("<tr><td>principal</td><td>" + request.getUserPrincipal() + "</td></tr>");
	
        AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
        Map attributes = principal.getAttributes();

        if (attributes.size() > 0) {

            out.println("</tr><td>Num. of attributes</td><td>" + attributes.size() + "</td></tr>");
            Iterator keyIterator = attributes.keySet().iterator();

            while (keyIterator.hasNext()) {

                Object key = keyIterator.next();
                Object value = attributes.get(key);
                out.println("<tr><td>" + key + "</td><td>" + value + "</td></tr>");
            }
        } else {
            out.println("</tr><td>Num. of attributes</td><td>" + attributes.size() + "</td></tr>");
        }
        out.println("<tr><td>LOCALE</td><td>" + l + "</td></tr></table>");

        %>	
    </body>

</html>
