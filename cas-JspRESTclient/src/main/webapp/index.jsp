<%@ page import="java.io.*,java.util.Locale" %>
<%@ page import="javax.servlet.*,javax.servlet.http.* "%>
<%
    Locale l = request.getLocale();
%>

<html>
    <head>
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>
            <decorator:title default="ACS CAS-REST Login" />
        </title>        
    </head>
    <body>
        <h2>Hello World! CAS TEST</h2>
        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">

                <div class="x_panel">

                    <div class="x_title">
                        <ul>
                            <li> 
                            <li><a id="btn_login"><i class="fa fa-plus"></i> Login</a></li>
                            </li>
                            <li> 
                                <a href="public/doLoginCasRest.action">test seite mit cas gesichert</a>
                            </li>
                            <li> 
                                <a href="cas/logout.jsp/logout.jsp">logout</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <p><% out.println("LOCALE  :" + l);%></p>

            <content tag="footlines">
                <!-- jQuery 2.2.4 -->
                <script type="text/javascript" src="js/vendors/jquery/jquery.min.js"></script>
                <!-- Bootstrap 3.3.6 -->
                <script type="text/javascript" src="js/vendors/bootstrap/js/bootstrap.min.js"></script>
                <script>
                    $(document).ready(function () {
                        $("#btn_login").bind("click", function (event, data) {
                            doLogin();
                        });
                    })
                </script>
                <script>

                    function doLogin() {
                        $.ajax({
                            url: "public/doLoginCasRest.action",
                            type: "POST",
                            dataType: 'json',
                            contentType: "application/x-www-form-urlencoded",
                            data: "",
//                            headers: {
//                                "Access-Control-Allow-Origin: ": "*",
//                                "Access-Control-Allow-Methods: ": "POST",
//                                "Access-Control-Allow-Headers: ": "Authorization",
//                            },
                            success: function (html) {
                                if ((html == null) || (html.jsonStatus == null) || (html.serviceUrl == null) || (html.jsonStatus.status != "OK")) {
                                    alert("ERRROR");
                                    return;
                                } else if (html.serviceUrl.length > 0) {
                                    window.location.href=html.serviceUrl;
                                }
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                alert("Error");
                                return;
                            }




                        });
                    }
                </script>

            </content>
    </body>
</html>
