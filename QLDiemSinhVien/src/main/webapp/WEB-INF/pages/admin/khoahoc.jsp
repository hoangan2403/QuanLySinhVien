<%-- 
    Document   : khoahoc
    Created on : Sep 26, 2023, 1:06:03 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="table-sv">
    <div class="title-gv">
        <h1 class="text-center">Danh sách khóa học</h1>
        <a href="<c:url value="/giaovu/khoahoc/add"/>" class="btn-xoavacn-gv bg-add-gv"> Thêm Khóa </a>
    </div>
    

    <table class="table " >
        <thead>
            <tr>
                <th>Mã Khóa</th>
                <th>Tên khóa</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${khoa}" var="kh">
                <tr>
                    <td>${kh.idkhoa}</td>
                    <td>${kh.tenKhoa}</td>
                    <td>
                        <c:url value="/giaovu/khoahoc/add/${kh.idkhoa}" var="api"/>
                        <a href="${api}" class="btn-xoavacn-gv bg-cn"><i class="fa-regular fa-pen-to-square"></i> </a>
                        <button onclick="deleteKhoaHoc('${api}')" class="btn-xoavacn bg-xoa-gv "><i class="fa-regular fa-trash-can"></i></button>
                        
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="pagination-div">
        <ul class="pagination">
                <c:forEach begin="1" end="${counter}" var="i">
                    <c:url value="/giaovu/khoahoc" var="pageAction">
                        <c:param name="pageKH" value="${i}"/>
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageAction}">${i}</a></li>
                </c:forEach>
        </ul>
    </div>
</div>
<script src="<c:url value="/js/main.js" />"></script>