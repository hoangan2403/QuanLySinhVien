<%-- 
    Document   : phonghoc
    Created on : Sep 26, 2023, 5:01:51 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="table-sv">
    <div class="title-gv">
        <h1 class="text-center">Danh sách phòng học</h1>
        <a href="<c:url value="/giaovu/phonghoc/add"/>" class="btn-xoavacn-gv bg-add-gv"> Thêm phòng học</a>
    </div>
    

    <table class="table " >
        <thead>
            <tr>
                <th>Mã phòng học</th>
                <th>Tên phòng học</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${phong}" var="ph" >
                <tr>
                    <td>${ph.idPhongHoc}</td>
                    <td>${ph.tenPhongHoc}</td>
                    <td>
                        <c:url value="/giaovu/phonghoc/add/${ph.idPhongHoc}" var="api"/>
                        <a href="${api}" class="btn-xoavacn-gv bg-cn"><i class="fa-regular fa-pen-to-square"></i> </a>
                        <button onclick="deletePhongHoc('${api}')"  class="btn-xoavacn-gv bg-xoa "><i class="fa-regular fa-trash-can"></i></button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="pagination-div">
        <ul class="pagination">
                <c:forEach begin="1" end="${counter}" var="i">
                    <c:url value="/giaovu/phonghoc" var="pageAction">
                        <c:param name="pagePH" value="${i}"/>
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageAction}">${i}</a></li>
                </c:forEach>
        </ul>
    </div>
</div>
<script src="<c:url value="/js/main.js" />"></script>