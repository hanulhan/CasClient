<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
    <head>
        <title>acentic cloud service - login</title>
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>
            <decorator:title default="ACS Login" />
        </title>        


    </head>
    <body>

        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">

                <div class="x_panel">

                    <div class="x_title">
                    </div>
                </div>
            </div>

            <content tag="footlines">
                <!-- jQuery 2.2.4 -->
                <script type="text/javascript" src="../js/vendors/jquery/jquery.min.js"></script>
                <!-- Bootstrap 3.3.6 -->
                <script type="text/javascript" src="../js/vendors/bootstrap/js/bootstrap.min.js"></script>
                <script>
                    var CAS_LOGIN_URL = "https://cas.acentic.com/CasServer/v1/tickets"
                    var GET_URL = "https://acs.acentic.com/CloudServices"

                    $.ajax({
                        url: CAS_LOGIN_URL + "?username=uhansen01&password=Ava030374Lon_",
                        type: "POST",
                        dataType: 'text',
                        contentType: "application/x-www-form-urlencoded",
                        data: "",
                        headers: {
                            "Access-Control-Allow-Origin: ": "*",
                            "Access-Control-Allow-Methods: ": "POST",
                            "Access-Control-Allow-Headers: ": "Authorization",
                        },
                        success: function (html) {
                            if (html == null) {
                                alert("ERRROR");
                                return;
                            } else {
                                alert("SUCCESS");
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            alert("Error");
                            return;
                        }




                    });
                </script>
                <script>

                    function doLogin() {

                    }
                </script>

            </content>

    </body>
</html>