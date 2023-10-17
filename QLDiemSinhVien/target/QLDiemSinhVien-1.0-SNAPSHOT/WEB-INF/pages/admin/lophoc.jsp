<%-- 
    Document   : sinhvien
    Created on : Aug 9, 2023, 11:59:40 PM
    Author     : FPTSHOP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url value="/giaovu/lophoc" var="action"/>
<div class="nav-tk">
    <form class="search" action="${action}">
        <input class="search-input"  type="text" name="tenLH" placeholder="Search...."/>
        <button class="search-button"><i class="fa-solid fa-magnifying-glass" style="color: #b7b7b8;"></i></button>
    </form>
        
</div >
<div class="table-sv">
    <div class="title-gv">
        <h1 class="text-center">Danh sách lớp học</h1>
        <a href="<c:url value="/giaovu/lophoc/add"/>" class="btn-xoavacn-gv bg-add-gv"> Thêm Lớp Học</a>
    </div>
    <table class="table " >
        <thead>
            <tr>
                <th>Mã Lớp Học</th>
                <th>Tên Lớp Học</th>
                <th>Sĩ Số</th>
                <th>Ngành Đào Tạo</th>
                <th>Khóa Học</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${lophocc}" var="lh">
                <c:url value="/giaovu/lophoc/${lh.idLopHoc}" var="dsSinhVien"/>
                <tr>
                    <td>${lh.idLopHoc}</td>
                    <td>${lh.tenLopHoc}</td>
                    <td>1</td>
                    <td>${lh.idNganh.tenNganhDaoTao}</td>
                    <td>${lh.idKhoaHoc.tenKhoa}</td>
                    <td>
                        <c:url value="/giaovu/lophoc/add/${lh.idLopHoc}" var="api"/>
                        <a href="${api}" class="btn-xoavacn-gv bg-cn"><i class="fa-regular fa-pen-to-square"></i> </a>
                        <button onclick="deleteLopHoc('${api}')" class="btn-xoavacn-gv bg-xoa"><i class="fa-regular fa-trash-can"></i></button>
                    </td>
                    <td>
                        <a href="${dsSinhVien}"class="a-tk" > Xem danh sách</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="pagination-div">
        <ul class="pagination">
                <c:forEach begin="1" end="${counter}" var="i">
                    <c:url value="/giaovu/lophoc" var="pageAction">
                        <c:param name="pageLH" value="${i}"/>
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageAction}">${i}</a></li>
                </c:forEach>
        </ul>
    </div>
</div>   

