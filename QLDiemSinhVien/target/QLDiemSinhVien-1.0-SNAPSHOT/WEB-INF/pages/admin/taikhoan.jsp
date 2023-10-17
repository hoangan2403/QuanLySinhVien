<%-- 
    Document   : taikhoan
    Created on : Aug 19, 2023, 12:18:13 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/giaovu/taikhoan" var="action"/>
<div class="nav-tk">
    <form class="search" action="${action}">
        <input class="search-input"  type="text" name="tenTK" placeholder="Search...."/>
        <button class="search-button"><i class="fa-solid fa-magnifying-glass" style="color: #b7b7b8;"></i></button>
    </form>
</div >
<div class="table-sv">
    <div class="title-gv">
        <h1 class="text-center">Danh sách tài khoản</h1>
        <a href="<c:url value="/giaovu/taikhoan/dangki"/>"  class="btn-xoavacn-gv bg-add-gv">Cấp tài khoản</a>
    </div>

    <table class="table table-infoTK">
        <thead>
            <tr>
                <th>Mã tài khoản</th>
                <th>Tên tài khoản</th>
                <th>Chức vụ</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${taikhoann}" var="tk">
                <tr>
                    <td>${tk.idTaiKhoan}</td>
                    <td>${tk.tenTaiKhoan}</td>
                    <td>
                        <c:choose>
                            <c:when test="${tk.chucVu.idloaitaikhoan== 1}">
                                Giáo vụ
                            </c:when>
                            <c:when test="${tk.chucVu.idloaitaikhoan == 2}">
                                Giảng viên
                            </c:when>
                            <c:when test="${tk.chucVu.idloaitaikhoan == 3}">
                                Sinh viên
                            </c:when>
                        </c:choose>

                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="pagination-div">
        <ul class="pagination">
            <c:forEach begin="1" end="${counter}" var="i">
                <c:url value="/giaovu/taikhoan" var="pageAction">
                    <c:param name="pageTK" value="${i}"/>
                </c:url>
                <li class="page-item"><a class="page-link" href="${pageAction}">${i}</a></li>
                </c:forEach>
        </ul>
    </div>
</div>
<style>
    .selected-page-text{
        font-weight: bold;
        color: red;
    }
</style>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const pageLinks = document.querySelectorAll(".page-link");
        // Kiểm tra xem trạng thái trang đã lưu trong localStorage chưa
        const currentPage = localStorage.getItem("currentPage");

        if (currentPage) {
            // Tìm liên kết trang hiện tại và áp dụng lớp CSS
            pageLinks.forEach(function (link) {
                if (link.textContent === currentPage) {
                    link.classList.add("selected-page-text");
                }
            });
        }
        pageLinks.forEach(function (link) {
            link.addEventListener("click", function (event) {
                // Loại bỏ lớp CSS "selected-page-text" khỏi tất cả các liên kết trang
                pageLinks.forEach(function (link) {
                    link.classList.remove("selected-page-text");
                });

                // Thêm lớp CSS "selected-page-text" cho liên kết trang vừa được nhấn
                this.classList.add("selected-page-text");
            });
        });
    });
</script>
