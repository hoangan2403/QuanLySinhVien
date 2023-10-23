<%-- 
    Document   : add
    Created on : Aug 10, 2023, 11:17:34 AM
    Author     : FPTSHOP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="/giaovu/sinhvien/add" var="action"/>
<div class="row">
    <div class=" margin-auto form-login  form-addSV ">
        <h1 class="text-center">
            <c:choose>
                <c:when test="${sinhvienn.idSinhVien != null}">
                    Cập nhật sinh viên
                </c:when>
                <c:otherwise>
                    Thêm sinh viên
                </c:otherwise>
            </c:choose>
        </h1>
        <div class="text-form-addSV">
            <form:form method="post" modelAttribute="sinhvienn" action="${action}" >
                <form:hidden path="idSinhVien"/>
                <form:hidden path="idTaiKhoan"/>
                <div class="form-floating mb-3 mt-3">
                    <form:input type="text" class="form-control" path="hoTen" id="name" placeholder="Họ và tên" name="name"  required="required"/>
                    <label for="name">Họ và tên</label>
                </div>
                
                <div class="form-floating mt-3 mb-3">
                    <form:input type="date" class="form-control" path="ngaySinh" id="dateofbirth" placeholder="Ngày sinh" name="dateofbirth"  required="required"/>
                    <label for="dateofbirth">Ngày sinh</label>
                </div>
                <div class="form-floating mt-3 mb-3">
                    <form:input type="text" class="form-control" path="diaChi" id="address" placeholder="Địa chỉ " name="address" required="required"/>
                    <label for="address">Địa chỉ</label>
                </div>
                <div class="form-floating mt-3 mb-3">
                    <form:select path="gioiTinh" id="sex" class="form-select" cssErrorClass="is-invalid">
                        <form:option value="-1" label="Chọn giới tính" />
                        <form:option value="1" label="Nam" />
                        <form:option value="0" label="Nữ" />
                    </form:select>
                    <label for="sex">Giới tính</label>
                </div>
                <div class="form-floating mt-3 mb-3">
                    <form:select class="form-select" id="class" name="class" path="maLop" required="required">
                        <c:forEach items="${listLopHoc}" var="lh">
                            <c:choose>
                                <c:when test="${lh.idLopHoc == sinhvienn.maLop.idLopHoc}">
                                    <option value="${lh.idLopHoc}" selected>${lh.tenLopHoc}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${lh.idLopHoc}">${lh.tenLopHoc}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                    <label for="class">Danh sách lớp học</label>                
                </div>    
                <div class="form-floating mt-3 mb-3">
                    <form:input type="text" class="form-control" path="soDienThoai" id="phonenumber" placeholder="Số điện thoại" name="phonenumber"  required="required"/>
                    <label for="phonenumber">Số điện thoại</label>
                </div>
                <div class="form-floating mt-3 mb-3">
                    <form:input type="email" class="form-control" path="email" id="email" placeholder="Email" name="email"  required="required"/>
                    <label for="email">Email</label>
                </div>
                <div class="btn-form-addsv">
                    <button class="btn input-form-addsv" type="submit" >
                        <c:choose>
                            <c:when test="${sinhvienn.idSinhVien != null}">
                                Cập nhật sinh viên
                            </c:when>
                            <c:otherwise>
                                Thêm sinh viên
                            </c:otherwise>
                        </c:choose>
                    </button>
                </div>
            </form:form>
        </div>
    </div>
</div>





