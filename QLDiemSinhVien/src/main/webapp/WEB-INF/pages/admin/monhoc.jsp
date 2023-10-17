<%-- 
    Document   : monhoc
    Created on : Aug 18, 2023, 9:24:40 AM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/giaovu/monhoc" var="action"/>
<div class="nav-tk">
    <form class="search" action="${action}">
        <input class="search-input"  type="text" name="tenMH" placeholder="Search...."/>
        <button class="search-button"><i class="fa-solid fa-magnifying-glass" style="color: #b7b7b8;"></i></button>
    </form>
</div >
<div class="table-sv">
    <div class="title-gv">
        <h1 class="text-center">Danh sách môn học</h1>
        <a  href="<c:url value="/giaovu/monhoc/add"/>" class=" btn-xoavacn-gv bg-add-gv"  >Thêm Môn Học</a>
    </div>
    <table class="table ">
        <thead>
            <tr>
                <th>Mã môn học</th>
                <th>Tên môn học</th>
                <th>Số tín chỉ</th>
                <th>
                </th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${monhocc}" var="mh">
                <tr>
                    <td>${mh.idMonHoc}</td>
                    <td>${mh.tenMonHoc}</td>
                    <td>${mh.soTinChi}</td>
                    <td>
                        <c:url value="/giaovu/monhoc/add/${mh.idMonHoc}" var="api"/>
                        <a href="${api}"class="btn-xoavacn-gv" ><i class="fa-regular fa-pen-to-square"></i></a>
                        <button onclick="deleteMonHoc('${api}')"  class="btn-xoavacn-gv"><i class="fa-regular fa-trash-can"></i></button>
                    </td>   
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="pagination-div">
        <ul class="pagination">
            <!--<li class="page-item"><a class="page-link" href="${action}">Tất cả</a></li>-->
                <c:forEach begin="1" end="${counter}" var="i">
                    <c:url value="/giaovu/monhoc" var="pageAction">
                        <c:param name="pageMH" value="${i}"/>
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageAction}">${i}</a></li>
                </c:forEach>
        </ul>
    </div>
</div>

<script src="<c:url value="/js/main.js" />"></script>