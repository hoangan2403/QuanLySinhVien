<%-- 
    Document   : sinhvien
    Created on : Aug 9, 2023, 11:59:40 PM
    Author     : FPTSHOP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="table-sv table-border">
    <h1 class="text-center">Danh sách sinh viên lớp ${lop.tenLopHoc}</h1>

    <table class="table ">
        <thead>
            <tr>
                <th>Mã số sinh viên</th>
                <th>Họ và tên</th>
                <th>Ngày sinh</th>
                <th>Địa chỉ</th>
                <th>Giới tính</th>
                <th>Số điện thoại</th>
                <th>Email</th>
                <th></th>
                
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listSinhVien}" var="sv">
                <tr>
                    <td>${sv.idSinhVien}</td>
                    <td>${sv.hoTen}</td>
                    <td>${sv.ngaySinh}</td>
                    <td>${sv.diaChi}</td>
                    <td><c:choose>
                            <c:when test="${sv.gioiTinh == 0}">Nữ</c:when>
                            <c:when test="${sv.gioiTinh == 1}">Nam</c:when>
                            <c:otherwise>Giới tính không xác định</c:otherwise>
                        </c:choose></td>

                    <td>${sv.soDienThoai}</td>
                    <td>${sv.email}</td>
                    <td>
                        <a href="<c:url value="/giaovu/sinhvien/${sv.idSinhVien}"/>" class="btn btn-success"> Chi tiết </a>
                    </td>
                    
                    
                </tr>
            </c:forEach>
        </tbody>
    </table> 
</div>   

