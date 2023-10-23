<%-- 
Document   : base
Created on : Aug 9, 2023, 10:38:44 PM
Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>
            <tiles:insertAttribute name="title" />
        </title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        
        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    </head>
    <body>
        <tiles:insertAttribute name="header" />
        <div class="container">
            <tiles:insertAttribute name="content"/>
        </div>
        <tiles:insertAttribute name="footer"/>
        <script src="<c:url value="/resources/js/main.js" />"></script>

    </body>

</html>
<style>
    body {
        font-family: initial;
        background: #F0F8FF;
        text-shadow: none;
    }
    /**Thanh menu**/
    .menu-1 li{
        list-style-type: none;

    }
    .menu-1 li a{
        text-decoration: none;
        padding: 5px;
        color: black;
    }
    ul.menu-dady li{
        position: relative;
    }
    ul.menu-dady li ul.menu-1{
        display: none;
        position: absolute;
        width: 160px;
        background: white;
        box-shadow: rgba(0, 0, 0, 0.05) 0px 6px 24px 0px, rgba(0, 0, 0, 0.08) 0px 0px 0px 1px;
        border-radius: 6px;
    }

    ul.menu-1 li{
        position: relative
    }
    ul.menu-1 li ul.menu-2{
        display: none;
        position: absolute;
        width: 182px;
    }
    ul.navbar-nav ul.menu-1 {
        display: none;
    }
    ul.navbar-nav li:hover ul.menu-1{
        display: block;
        z-index: 10;
        padding: 5px;
    }
    ul.menu-1 ul.menu-2{
        display: none
    }
    ul.menu-1 li:hover ul.menu-2{
        display: block;
        padding: 5px;
        margin-left: 154px;
        background: white;
        border-radius: 7px;
        margin-top: -24px;
        box-shadow: rgba(50, 50, 93, 0.25) 0px 13px 27px -5px, rgba(0, 0, 0, 0.3) 0px 8px 16px -8px;
    }
    .input-button-tktk{
        margin-left: auto;
    }
    .btn-xoavacn-gv{
        border: 1px solid black;
        padding: 5px;
        height: 30px;
        width: 31px;
        color: black;
        text-decoration: none;
        border-radius: 5px
    }
    .btn-xoavacn {
        border: 1px solid black;
        width: 31px;
        color: black;
        text-decoration: none;
        border-radius: 5px
    }
    .bg-cn {
        padding: 6px;
        margin-right: 7px;
    }
    .bg-xoa {
        padding: 1px;
    }
    .bg-add-gv{
        display: inline-block;
        width: 144px;
        height: 37px;
        text-align: center;
        background-color: #006df0;
        margin-left: auto;
        color: white;
        border-radius: 4px
    }
    .bg-add {
        display: inline-block;
        width: 144px;
        text-align: center;
        background-color: #66FF99;
    }
    h1.text {
        margin-top: 10px;
        margin-bottom: 20px;
    }
    .title-gv {
        display: flex;
        align-items: center;
    }
    .form-addSV{
        max-width: 600px;
        box-shadow: -5px -5px 10px -1px rgba(0, 0, 0, 0.3),
            5px 5px 10px 2px rgba(0, 0, 0, 0.3);
        border-radius: 15px;
        padding: 39px;
        margin: 15px auto;
    }
    .text-form-addSV{
        font-size: 20px;
    }
    .btn-form-addsv{
        text-align: center;
        margin-top: 30px;
    }
    .input-form-addsv{
        width: 85px;
        padding: 5px;
        background-color: #33CC99;
        border-radius: 5px;
        border: 1px
    }
    .form-login {
        width: 600px;
        margin: 10px auto;
        border-radius: 25px;
        padding: 45px
    }
    .btn-bg {
        background: lightblue;
        width: 100%;
        margin-top: 10px
    }
    .table-infoTK{
        margin: 0px auto;
    }
    .table-tk{
        box-shadow: rgba(0, 0, 0, 0.05) 0px 6px 24px 0px, rgba(0, 0, 0, 0.08) 0px 0px 0px 1px;
        border-radius: 15px;
        padding-top: 1px;
        padding-bottom: 40px;
        padding: 1px 50px 40px;
        width: 1015px;
        margin: 50px auto;
    }
    .text-tk{
        margin: 20px auto;
        text-align: center;
    }
    .nav-tk{
        margin: 12px 0;
        display: flex;
        box-shadow: rgba(17, 17, 26, 0.05) 0px 1px 0px, rgba(17, 17, 26, 0.1) 0px 0px 8px;
        background: #ffffff;
        height: 59px;
        padding: 13px;
        font-size: 18px;
    }
    .input-button-tk{
        margin: 0px auto;
    }
    a.a-tk{
        box-shadow: rgba(9, 30, 66, 0.25) 0px 1px 1px, rgba(9, 30, 66, 0.13) 0px 0px 1px 1px;
        text-decoration: none;
        background: #1E90FF;
        color: white;
        border-radius: 3px;
        text-align: center;
        padding: 6px;
    }
    .table-sv{
        box-shadow: rgba(17, 17, 26, 0.1) 0px 1px 0px, rgba(17, 17, 26, 0.1) 0px 8px 24px, rgba(17, 17, 26, 0.1) 0px 16px 48px;
        margin-top: 19px;
        background: white;
        padding: 20px 40px 25px;
    }
    .display-add-MhSV{
        display: flex;
        margin-top:60px;
    }
    .table-info-svmh{
        box-shadow: rgba(0, 0, 0, 0.05) 0px 6px 24px 0px, rgba(0, 0, 0, 0.08) 0px 0px 0px 1px;
        padding: 20px;
        margin-left: 5px;
        width: 1000px;
    }
    .info-svmh{
        box-shadow: rgba(0, 0, 0, 0.05) 0px 6px 24px 0px, rgba(0, 0, 0, 0.08) 0px 0px 0px 1px;
        padding: 20px;
        width: 400px;
    }
    .header-info-svmh{
        font-size: 23px;
        text-align: center;
    }
    .label-info-svmh{
        font-size: 18px;
        margin-bottom: 5px;
    }
    /*    li.dropdown{
            margin-left: 650px;
            display: inline-block;
            padding-left: 100px;
        }*/
    .navbar-nav {
        list-style: none;
        padding: 0;
        margin: 0;
        display: flex;
    }

    .nav-item {
        margin-right: 10px; /* Khoảng cách giữa các phần tử <li> */
    }

    li.last-item {
        float: right;
        display: inline-block;/* Đẩy phần tử cuối cùng ra góc phải */
    }
    .center-text-addmh{
        width: 130px;
        font-weight: bold;
    }

    #customers {
        font-family: Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        width: 100%;
    }

    #customers td, #customers th {
        border: 1px solid #ddd;
        padding: 8px;
    }

    #customers tr:nth-child(even){
        background-color: #f2f2f2;
    }

    #customers tr:hover {
        background-color: #ddd;
    }

    #customers th {
        padding-top: 12px;
        padding-bottom: 12px;
        text-align: left;
        background-color: #87CEFA;
        color: black;
    }
    @keyframes modelFaceIn {
        from {
            transform: translateY(-130px);
            opacity: 0;
        }

        to {
            transform: translateY(0);
            opacity: 1;
        }
    }
    .show {
        display: block !important;
    }
    .close-dangki-taikhoan {
        background-color: #05386B;
        position: absolute;
        right: 0;
        top: 0;
        color: #fff;
        padding: 8px;
        border-top-right-radius: 10px;
        border-bottom-left-radius: 10px;
        cursor: pointer;
    }

    .btn-add-teacher {
        background-color: #05386B;
        color: aliceblue;
        font-size: small;
        font-weight: 500;
        border-radius: 10px;
        width: 100px;
        height: 50px;
    }

    .form-dangki-taikhoan {
        background-color: aliceblue;
        position: relative;
        width: 40%;
        padding: 25px;
        border-radius: 10px;
        top: 10%;
        margin: auto;
        max-height: 600px;
        overflow-x: auto;
        scroll-snap-type: x mandatory;
        animation: modelFaceIn ease .5s;

    }

    .dangki-taikhoan-none {
        display: none;
        position: fixed;
        top: 56px;
        right: 0;
        left: 0;
        bottom: 0;
        background-color: rgba(0, 0, 0, 0.5);
        align-items: center;
        justify-content: center;

    }
    /* Thong Tin Tai Khoan */
    .icon-image-header{
        width: 45px;
        height: 45px;
        border-radius: 100%;
        padding: 5px;
    }

    .info-user-image-2{
        display: inline-block;
        background-color: black;
        padding: 20px 5px 20px 5px;
        width: 100px;
        height: 100px;
        margin: auto 20px;
        overflow: hidden;
        border-radius: 100%;
    }
    .info-user-text-reply{
        padding:0px 50px 50px 50px;
    }
    .info-title-user{
        background-color: cadetblue;
        padding: 15px;
        margin-bottom: 40px;
        border-radius: 10px;
    }
    .info-private{
        display: inline-block;
        position: relative;
        font-size: 28px;
        font-weight: 500;
        margin: 0px 0px 0px 130px;
    }
    .info-user-text2{
        font-weight: 470;
    }
    .info-user-texts{
        border-bottom: 1px solid darkgrey;
        margin: 10px 0px;
    }
    .info-user-text{
        font-size: 17px;
        font-weight: 600 ;
        margin: 10px 5px;
        width: 35%;
        display: inline-block;
    }
    .info-user-image{
        font-size: 40px;
        background-color: antiquewhite;
        display: inline-block;
        padding: 20px;
        margin: auto 20px;
        border-radius: 50%;
    }
    .info-user{
        border: 1px solid gray;
        position: relative;
        width: 60%;
        border-radius: 10px;
        margin:50px auto;
        border-radius: 10px;
        -moz-box-shadow: -3px -3px 5px 0px #666;
        -webkit-box-shadow: -3px -3px 5px 0px #666;
        box-shadow: -3px -3px 5px 0px #666;
    }
    /* Thay doi mat khau */
    .btn-change-password-div {
        display: flex;
        justify-content: center;
    }

    .btn-change-password {
        margin-top: 10px;
        width: 100px;
        position: relative;
    }

    .change-password-form {
        width: 70%;
        position: relative;
        margin: 0px auto;
    }

    .change-password-title {
        font-size: 30px;
        font-weight: 400;
        text-align: center;

    }

    .change-password {
        padding: 30px 20px;
        width: 60%;
        background-color: cadetblue;
        position: relative;
        margin: 70px auto;
        border-radius: 10px;
        border-radius: 10px;
        -moz-box-shadow: -3px -3px 5px 0px #666;
        -webkit-box-shadow: -3px -3px 5px 0px #666;
        box-shadow: -3px -3px 5px 0px #666;
    }
    .table {
        font-size: 15px;
    }
    .search{
        width: 300px;
        background: rgba(255,255,255,.1);
        border-radius: 30px 30px 30px 30px;
        border: 1px solid #707070;
        display: flex;
    }
    .search-input{
        border: none;
        outline: none;
        background: none;
        padding: 15px 20px;
        color: black;
    }
    .search-button {
        background: none;
        border: none;
        outline: none;
    }
    .pagination{
        justify-content: center;
    }
    .table-form{
        display: flex
    }
    .form-addltk{
        background: white;
        box-shadow: rgba(17, 17, 26, 0.1) 0px 1px 0px, rgba(17, 17, 26, 0.1) 0px 8px 24px, rgba(17, 17, 26, 0.1) 0px 16px 48px;
        padding: 39px;
        width: 340px;
        margin: 19px 32px 19px 11px;
    }
    .table-ltk{
        width: 1000px;
    }
    .info-hk{
        box-shadow: rgba(0, 0, 0, 0.05) 0px 6px 24px 0px, rgba(0, 0, 0, 0.08) 0px 0px 0px 1px;
        padding: 18px;
        display: flex;
        background: white;
        margin-bottom: 10px;
        margin-top: 10px;

    }
    .table-info-mh{
        box-shadow: rgba(0, 0, 0, 0.05) 0px 6px 24px 0px, rgba(0, 0, 0, 0.08) 0px 0px 0px 1px;
        padding: 20px;
        background: white;
    }
    .form-selector{
        height: 28px;
        padding: 5px;
    }
    .label-info-mh{
        border: 1px solid black;
        padding: 5px;
        font-size: 18px;
        margin-left: 10px;
        border-radius: 10px;
    }
    .btn-mhhk{
        border: none;
        width: 80px;
        padding: 6px;
        font-size: 20px;
        font-weight: 600;
        background: #006df0;
        color: white;
        border-radius: 10px
    }
    .btn-hk{
        margin-left: 1150px;
    }
    .form-hk{
        width: 387px;
        margin-left: 17px;
        box-shadow: rgba(17, 17, 26, 0.1) 0px 1px 0px, rgba(17, 17, 26, 0.1) 0px 8px 24px, rgba(17, 17, 26, 0.1) 0px 16px 48px;
        background: white;
        margin-top: 19px;
        padding: 15px;
    }
    .table-form-mhhk{
        display: flex;
    }
    .table-border{
        border-radius: 30px 
    }
   
</style>

