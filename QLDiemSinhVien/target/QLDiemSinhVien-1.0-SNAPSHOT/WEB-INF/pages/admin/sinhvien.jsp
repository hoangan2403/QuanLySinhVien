<%-- 
    Document   : sinhvien
    Created on : Aug 9, 2023, 11:59:40 PM
    Author     : FPTSHOP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url value="/giaovu/sinhvien" var="action"/>
<div class="nav-tk">
    <form class="search" action="${action}">
        <input class="search-input"  type="text" name="tensv" placeholder="Search...."/>
        <button class="search-button"><i class="fa-solid fa-magnifying-glass" style="color: #b7b7b8;"></i></button>
    </form>
</div >
<div class="table-sv">
    <div class="title-gv">
        <h1 class="text-center">Danh sách sinh viên</h1>
        <a href="<c:url value="/giaovu/sinhvien/add"/>" class="btn-xoavacn-gv bg-add-gv"> Thêm sinh viên</a>
    </div>


    <table class="table " >
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
            <c:forEach items="${sinhviens}" var="sv">
                <tr>
                    <td>${sv.idSinhVien}</td>
                    <td>${sv.hoTen}</td>
                    <td>
                        <fmt:formatDate value="${sv.ngaySinh}" pattern="dd-MM-yyyy" />
                    </td>
                    <td>${sv.diaChi}</td>
                    <td><c:choose>
                            <c:when test="${sv.gioiTinh == 0}">Nữ</c:when>
                            <c:when test="${sv.gioiTinh == 1}">Nam</c:when>
                            <c:otherwise>Giới tính không xác định</c:otherwise>
                        </c:choose></td>

                    <td>${sv.soDienThoai}</td>
                    <td>${sv.email}</td>
                    <td>
                        <c:url value="/giaovu/sinhvien/add/${sv.idSinhVien}" var="api" />
                        <a href="${api}" class="btn-xoavacn-gv bg-cn"><i class="fa-regular fa-pen-to-square"></i></a>
                        
                        <button class="btn-xoavacn-gv bg-xoa " onclick="deleteSinhVien('${api}')"><i class="fa-regular fa-trash-can"></i></button>

                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="pagination-div">
        <ul class="pagination">
                <c:forEach begin="1" end="${counter}" var="i">
                    <c:url value="/giaovu/sinhvien" var="pageAction">
                        <c:param name="page" value="${i}"/>
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageAction}">${i}</a></li>
                </c:forEach>
        </ul>
    </div>

</div>   

