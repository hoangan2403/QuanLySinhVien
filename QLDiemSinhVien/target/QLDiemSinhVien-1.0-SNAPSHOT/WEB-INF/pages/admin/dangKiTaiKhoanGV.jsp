<%-- 
    Document   : dangKiTaiKhoanGV
    Created on : Aug 18, 2023, 1:56:58 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/giaovu/taikhoan/dangki" var="action"/>
<div class="container  " >
    <div class="row ">
        <div class=" margin-auto form-login  shadow-lg ">
            <div class="text-center " >
                <h3 class="text-center">Đăng Ký</h3>
            </div>
            <c:if test="${errMsg != null}">
                <div class="alert alert-danger text-size mt-3 ">
                    ${errMsg};
                </div>
            </c:if>
            <div class="text-form-addSV">
                <form:form modelAttribute="dangki" method="post" action="${action}">
                    <div class="form-floating mb-3 mt-3">
                        <form:input type="email" class="form-control text-size" path="tenTaiKhoan" id="username" placeholder="Email" name="username" required="required"/>
                        <label for="username">Email</label>
                    </div>
                    <div class="form-floating mb-3 mt-3">
                        <form:input type="password" class="form-control text-size" path="matKhau" id="password" placeholder="Mật khẩu" name="password" required="required"/>
                        <label for="password">Mật khẩu</label>
                    </div>
                    <div class="form-floating mb-3 mt-3">
                        <form:input type="password" class="form-control text-size" path="xacNhanMk" id="cfpw" placeholder="Nhập lại mật khẩu" name="cfpw" required="required"/>
                        <label for="cfpw">Nhập lại mật khẩu</label>
                    </div>
                    <div class="form-floating mb-3 mt-3">

                        <form:select class="form-select" id="teacher" name="teacher" path="giangvien1" required="required">
                            <c:forEach items="${giangvien}" var="gv">
                                <c:if test="${gv.idTaiKhoan == null}">
                                    <option value="${gv.idGiangVien}">${gv.hoTen}</option>
                                </c:if>
                            </c:forEach>
                        </form:select> 
                        <label for="teacher">Danh sách giảng viên</label>
                    </div>
                    <div class="form-floating">
                        <button type="submit" id="submit" class="btn btn-bg btn-submit text-size" >Đăng Ký</button>
                    </div>

                </form:form>
            </div>
        </div>
    </div>
</div>