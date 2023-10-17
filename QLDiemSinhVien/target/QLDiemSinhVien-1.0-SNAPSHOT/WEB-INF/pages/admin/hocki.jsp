<%-- 
    Document   : hocki
    Created on : Oct 2, 2023, 12:29:39 PM
    Author     : FPTSHOP
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url value="/giaovu/hocky" var="action"/>
<div class="nav-tk">
    <form class="search" action="${action}">
        <input class="search-input"  type="text" name="tenHK" placeholder="Search...."/>
        <button class="search-button"><i class="fa-solid fa-magnifying-glass" style="color: #b7b7b8;"></i></button>
    </form>
</div >
<div class="table-sv">
    <div class="title-gv">
        <h3 class="text">Danh sách học kỳ</h3>
        <a   href="<c:url value="/giaovu/hocky/add"/>" class=" btn-xoavacn-gv bg-add-gv"  >Thêm học kỳ</a>
    </div>

    <table class="table ">
        <thead>
            <tr>
                <th>Mã học kì </th>
                <th>Tên học kì</th>
                <th>Ngày bắt đầu </th>
                <th>Ngày kết thúc</th>
                <th>Ngày đăng ký</th>
                <th>Ngày hết hạn</th>
                <th>Tên lớp</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>

            <c:forEach items="${hocki}" var="hk">
                <tr>
                    <td>${hk.idHocKy}</td>
                    <td>${hk.tenHocKy.tenHocKy}</td>
                    <td>
                        <fmt:formatDate value="${hk.ngayBatDau}" pattern="dd-MM-yyyy" />
                    </td>
                    <td>
                        <fmt:formatDate value="${hk.ngayKetThuc}" pattern="dd-MM-yyyy" />
                    </td>
                    <td>
                        <fmt:formatDate value="${hk.ngayDangKy}" pattern="dd-MM-yyyy" />
                    </td>
                    <td>
                        <fmt:formatDate value="${hk.ngayHetHan}" pattern="dd-MM-yyyy" />
                    </td>
                    <td>${hk.idLop.tenLopHoc}</td>
                    <td>
                        <c:url value="/giaovu/hocky/add/${hk.idHocKy}" var="api"/>
                        <a href="${api}"class="btn-xoavacn-gv bg-cn " alt="Cập nhật"> <i class="fa-regular fa-pen-to-square"></i></a>
                        <button onclick="deleteHocKi('${api}')" alt="Xóa" class="btn-xoavacn-gv bg-xoa"><i class="fa-regular fa-trash-can"></i></button>
                    </td>
                    <td>
                        <a href="<c:url value="/giaovu/hocki/addMH/${hk.idHocKy}"/>" class="a-tk">Cấp môn học</a>
                    </td>
                    <td>
                        <a href="<c:url value="/giaovu/hocki/chitiethocki/${hk.idHocKy}"/>" class="a-tk">Xem chi tiết</a>
                    </td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
    <div class="pagination-div">
        <ul class="pagination">
                <c:forEach begin="1" end="${counter}" var="i">
                    <c:url value="/giaovu/hocky" var="pageAction">
                        <c:param name="pageHK" value="${i}"/>
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageAction}">${i}</a></li>
                </c:forEach>
        </ul>
    </div>
</div>
<script src="<c:url value="/js/main.js" />"></script>