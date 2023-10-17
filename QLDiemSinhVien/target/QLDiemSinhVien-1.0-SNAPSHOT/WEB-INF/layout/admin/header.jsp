<%-- 
    Document   : header
    Created on : Aug 9, 2023, 10:38:53 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-sm     navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="javascript:void(0)">Admin</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="mynavbar">
            <ul class="navbar-nav  menu-dady">
                <li class="nav-item ">
                    <a class="nav-link "  href="#">Tài khoản</a>
                    <ul class="menu-1">
                        <li><a href="<c:url value="/giaovu/taikhoan"/>">Danh sách tài khoản</a></li>
                        <li><a href="<c:url value="/giaovu/taikhoan/dangki"/>">Cấp tài khoản</a></li>
                        <li >
                            <a class=""   href="#">Loại tài khoản</a>
                            <ul class="menu-2">
                                <li><a class="" href="<c:url value="/giaovu/loaitaikhoan"/>">Danh sách loại tài khoản</a></li>
                                <li><a class="" href="#">Thêm loại tài khoản</a></li>
                            </ul>

                        </li>
                    </ul>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="<c:url value="/giaovu/sinhvien"/>">Sinh viên</a>
                    
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="<c:url value="/giaovu/giangvien"/>">Giảng viên</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/giaovu/monhoc"/>">Môn học</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/giaovu/lophoc"/>">Lớp học</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/giaovu/hocky"/>">Học kỳ</a>
                </li>

                <li class="nav-item ">
                    <a class="nav-link" href="<c:url value="/giaovu/khoadaotao"/>">Khoa đào tạo</a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="<c:url value="/giaovu/phonghoc"/>">Phòng học</a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="<c:url value="/giaovu/khoahoc"/>">Khóa Học</a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="<c:url value="/giaovu/hedaotao"/>">Hệ đào tạo</a>
                </li>
                <li class="nav-item dropdown last-item">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"><i class="fa-solid fa-user" style="color: #f2f2f2;"></i>Chào ${giaovu.tenGiaoVu}</a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="<c:url value="/giaovu/thongtin"/>">Thông tin tài khoản</a></li>
                        <li><a class="dropdown-item" href="<c:url value="/giaovu/thaydoimatkhau/${taiKh}"/>">Thay đổi mật khẩu</a></li>
                        <li><a class="dropdown-item" href="<c:url value="/logout" />">Đăng xuất</a></li>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</nav>
<style>
    .bg-dark {
        background-color: #05386B!important;
    }
</style>

