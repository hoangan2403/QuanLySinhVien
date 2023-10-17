<%-- 
    Document   : giangvien
    Created on : Aug 10, 2023, 12:56:15 AM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url value="/giaovu/giangvien" var="action"/>
<c:if test="${errMsg != null}">
    <div class="alert alert-secondary">
        <strong>${errMsg}</strong> 
    </div>
</c:if>
<div class="nav-tk">
    <form class="search" action="${action}">
        <input class="search-input"  type="text" name="tenGV" placeholder="Search...."/>
        <button class="search-button"><i class="fa-solid fa-magnifying-glass" style="color: #b7b7b8;"></i></button>
    </form>
</div >
<div class="table-sv">
    <div class="title-gv">
        <h1 class="text">Danh sách giảng viên</h1>
        <a   href="<c:url value="/giaovu/giangvien/add"/>" class=" btn-xoavacn-gv bg-add-gv"  >Thêm Giảng viên</a>
    </div>

    <table class="table ">
        <thead>
            <tr>
                <th>Mã giảng viên</th>
                <th>Họ và tên</th>
                <th>Ngày sinh</th>
                <th>Giới tính</th>
                <th>Email</th>
                <th>Địa chỉ</th>
                <th>Số điện thoại</th>
                <th>
                </th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${giangvienn}" var="gv">
                <tr>
                    <td>${gv.idGiangVien}</td>
                    <td>${gv.hoTen}</td>
                    <td>
                        <fmt:formatDate value="${gv.ngaySinh}" pattern="dd-MM-yyyy" />
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${gv.gioiTinh == 0}">Nữ</c:when>
                            <c:when test="${gv.gioiTinh == 1}">Nam</c:when>
                            <c:otherwise>Giới tính không xác định</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${gv.email}</td>
                    <td>${gv.diaChi}</td>
                    <td>${gv.soDienThoai}</td>
                    <td>
                        <c:url value="/giaovu/giangvien/add/${gv.idGiangVien}" var="api"/>
                        <a href="${api}"class="btn-xoavacn-gv bg-cn " alt="Cập nhật"> <i class="fa-regular fa-pen-to-square"></i></a>
                        <button onclick="deleteGiangVien('${api}')" alt="Xóa" class="btn-xoavacn-gv bg-xoa"><i class="fa-regular fa-trash-can"></i></button>
                    </td>
                </tr>   
            </c:forEach>
        </tbody>
    </table>
    <div class="pagination-div">
        <ul class="pagination">
                <c:forEach begin="1" end="${counter}" var="i">
                    <c:url value="/giaovu/giangvien" var="pageAction">
                        <c:param name="pageGV" value="${i}"/>
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageAction}">${i}</a></li>
                </c:forEach>
        </ul>
    </div>
</div>
<script src="<c:url value="/js/main.js" />"></script>
