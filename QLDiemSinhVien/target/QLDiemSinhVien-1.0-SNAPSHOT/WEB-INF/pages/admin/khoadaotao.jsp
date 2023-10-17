<%-- 
    Document   : khoadaotao
    Created on : Sep 26, 2023, 4:32:07 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="table-sv">
    <div class="title-gv">
        <h1 class="text-center">Danh sách sinh viên</h1>
        <a href="<c:url value="/giaovu/khoadaotao/add"/>" class="btn-xoavacn-gv bg-add-gv"> Thêm khoa đào tạo</a>
    </div>
    

    <table class="table ">
        <thead>
            <tr>
                <th>Mã khoa đào tạo</th>
                <th>Tên khoa đào tạo</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${khoadt}" var="kdt">
                <tr>
                    <td>${kdt.idKhoaDaoTao}</td>
                    <td>${kdt.tenKhoaDaoTao}</td>
                    <td>
                        <c:url value="/giaovu/khoadaotao/add/${kdt.idKhoaDaoTao}" var="api"/>
                        <a href="${api}" class="btn-xoavacn-gv bg-cn"> <i class="fa-regular fa-pen-to-square"></i> </a>
                        <button onclick="deleteKhoaDaoTao('${api}')"  class="btn-xoavacn-gv bg-xoa "><i class="fa-regular fa-trash-can"></i></button>
                    </td>
                    
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="pagination-div">
        <ul class="pagination">
                <c:forEach begin="1" end="${counter}" var="i">
                    <c:url value="/giaovu/khoadaotao" var="pageAction">
                        <c:param name="pageKDT" value="${i}"/>
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageAction}">${i}</a></li>
                </c:forEach>
        </ul>
    </div>
</div>
<script src="<c:url value="/js/main.js" />"></script>