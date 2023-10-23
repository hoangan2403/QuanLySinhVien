<%-- 
    Document   : thaydoimatkhau
    Created on : Jul 25, 2023, 7:46:14 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="change-password">
    <c:url value="/giaovu/thaydoimatkhau" var="action" />
    <p class="change-password-title">Thay Đổi Mật Khẩu</p>
    <c:if test="${errMsg!=null}"><div class="alert alert-success">
            ${errMsg}
        </div></c:if>
    <form:form modelAttribute="taiKhoan" action="${action}" method="post"  class="change-password-form">
        <form:input path="idTaiKhoan" type="hidden" value="${taikhoanAD.idTaiKhoan}" />
        <div class="mb-3 mt-3">
            <label for="oldpw" class="form-label">Nhập Mật Khẩu Cũ:</label>
            <form:input path="matKhau" type="password" class="form-control" id="oldpw" placeholder="Nhập Mật Khẩu Cũ" name="oldpw" required="required"
                        />
        </div>
        <div class="mb-3">
            <label for="pwd" class="form-label">Nhập Mật Khẩu Mới:</label>
            <form:input path="mkMoi" type="password" class="form-control" id="pwd" placeholder="Nhập Mật Khẩu Mới" name="matKhauMoi" required="required"
                        />
        </div>
        <div class="mb-3">
            <label for="cfpwd" class="form-label">Nhập Lại Mật Khẩu:</label>
            <form:input path="xacNhanMk" type="password" class="form-control" id="cfpwd" placeholder="Nhập Lại Mật Khẩu" name="cfpwd" required="required"
                        />
        </div>
        <div class="btn-change-password-div">
            <button type="submit" class="btn btn-primary btn-change-password ">Thay Đổi</button>
        </div>

    </form:form>
</div>
