<%-- 
    Document   : thongtintaikhoan
    Created on : Jul 25, 2023, 7:41:55 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="info-user">
    <div class="info-title-user">
        <p class="info-private">Thông tin Cá Nhân</p>
    </div>
    <div class="info-user-text-reply">
        <div class="info-user-texts">
            <span class="info-user-text">Họ Tên:</span>
            <span class="info-user-text2">${giaovu.tenGiaoVu}</span>
        </div >
        <div class="info-user-texts">
            <span class="info-user-text">Giới Tính:</span>
            <c:choose>
                <c:when test="${giaovu.gioiTinh == 1}">
                    <span class="info-user-text2">Nam</span>
                </c:when>
                <c:otherwise>
                    <span class="info-user-text2">Nữ</span>
                </c:otherwise>
            </c:choose>
            
        </div >
        <div class="info-user-texts">
            <span class="info-user-text">Số Điện Thoại</span>
            <span class="info-user-text2">${giaovu.soDienThoai}</span>
        </div>
       
    </div>

</div>
