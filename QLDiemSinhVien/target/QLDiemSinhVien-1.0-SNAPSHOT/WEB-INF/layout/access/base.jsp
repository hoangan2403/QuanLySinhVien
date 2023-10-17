<%-- 
    Document   : base
    Created on : Jul 22, 2023, 9:07:48 AM
    Author     : FPTSHOP
--%>

 <%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <tiles:insertAttribute name="title" />
        </title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </head>
    <body>
        <% request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8"); %>
        <tiles:insertAttribute name="header" />
        <div class="container">
            <tiles:insertAttribute name="content"/>
        </div>
        <tiles:insertAttribute name="footer"/>
    </body>

    <style>
        body {
            background-color: #DDDDDD;
        }
        .form-login{
            font-family: initial;
            font-size: 20px;
            background-color: #EEEEEE;
            padding: 30px;
            border: 1px;
            border-radius: 5px;
            width: 550px;
            margin: auto;
            margin-top: 30px;
        }
        .text-login{
            margin: 30px;
            font-size: 40px;
            font-weight: bold;
        }
        .btn-submit{
            display: block;
            margin-top: 10px;
        }
        .form-control{
            border: 1px solid black;
            padding: 10px
        }

        .btn-bg{
            background: #235680;
            color: white;
        }
        #submit {
            text-decoration: none;
            border: 1px solid;
            background: #235680;
            color: white;
            padding: 10px;
            border-radius: 10px;
            width:  100%;
            text-align: center;
        }

        .text-center{
            text-align: center;
        }
        .text-sign-in{
            text-decoration: none;
        }

        .text-content{
            text-align: justify;
        }

        .text-size {
            font-size: 20px;
        }
        .nav-item {
            font-family: cursive;
            font-size: 25px
        }

        .text-login{
            margin: 30px;
            font-family: initial;
            font-size: 30px;
        }

        .margin-auto{
            margin-left: auto;
            margin-right: auto;
        }

        .text-sign-in{
            text-decoration: none;
        }

    </style>
</html>

