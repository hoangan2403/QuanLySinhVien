<%-- 
    Document   : hedaotao
    Created on : Oct 3, 2023, 5:04:07 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<c:url value="/giaovu/hedaotao" var="action"/>
<div class="nav-tk">
    <form class="search" action="${action}">
        <input class="search-input"  type="text" name="tenHDT" placeholder="Search...."/>
        <button class="search-button"><i class="fa-solid fa-magnifying-glass" style="color: #b7b7b8;"></i></button>
    </form>
</div >
<div class="table-sv">
    <div class="title-gv">
        <h1 class="text-center">Danh sách hệ đào tạo</h1>
        <a  href="<c:url value="/giaovu/hedaotao/add"/>" class=" btn-xoavacn-gv bg-add-gv"  >Thêm hệ đào tạo</a>
    </div>
    <table class="table ">
        <thead>
            <tr>
                <th>Mã hệ đào tạo </th>
                <th>Tên hệ đào tạo</th>
                <th>


                </th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${hedt}" var="hdt">
                <tr>
                    <td>${hdt.idhedaotao}</td>
                    <td>${hdt.tenHeDaoTao}</td>
                    <td>
                        <c:url value="/giaovu/hedaotao/add/${hdt.idhedaotao}" var="api"/>
                        <a href="${api}"class="btn-xoavacn-gv bg-cn" ><i class="fa-regular fa-pen-to-square"></i></a>
                        <button onclick="deleteHeDaoTao('${api}')"  class="btn-xoavacn-gv bg-xoa"><i class="fa-regular fa-trash-can"></i></button>
                    </td>   
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="pagination-div">
        <ul class="pagination">
                <c:forEach begin="1" end="${counter}" var="i">
                    <c:url value="/giaovu/hedaotao" var="pageAction">
                        <c:param name="pageHDT" value="${i}"/>
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageAction}">${i}</a></li>
                </c:forEach>
        </ul>
    </div>
</div>
<script src="<c:url value="/js/main.js" />"></script>
