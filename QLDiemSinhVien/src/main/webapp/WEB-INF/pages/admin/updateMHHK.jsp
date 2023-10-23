<%-- 
    Document   : updateMHHK
    Created on : Oct 10, 2023, 12:37:32 AM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/giaovu/hocki/chitiethocki/update" var = "action"/>
    <div class=" margin-auto form-login form-addSV ">
        <h1 class="text-center">
            Cập nhật môn học
        </h1>
        <div id="form" class="text-form-addSV">
            <form:form method="post" modelAttribute="phieumonhoc" action="${action}">
                <form:hidden path="idMonHocHocKy"/>
                <form:hidden path="idMonHoc"/>
                <form:hidden path="idHocky"/>
                <form:hidden path="listMonhocs"/>
                <form:hidden path="listDateBatDau"/>
                <form:hidden path="listDateKetThuc"/>
                <form:hidden path="listGiangviens"/>
                <form:hidden path="listSoLuong"/>
                <form:hidden path="listPhonghocs"/>
                <div class="form-floating mb-3 mt-3">
                    <form:input type="number" path="soLuong" class="form-control" id="name" placeholder="Số lượng" name="name" required="required"/>
                    <label for="name">Số lượng</label>
                </div>
                <div class="form-floating mt-3 mb-3">
                    <form:select class="form-select" id="class" name="class" path="phongHoc" required="true">
                        <c:forEach items="${phonghoc}" var="ph">
                            <c:choose>
                                <c:when test="${ph.idPhongHoc == phieumonhoc.phongHoc.idPhongHoc}">
                                    <option value="${ph.idPhongHoc}" selected>${ph.tenPhongHoc}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${ph.idPhongHoc}">${ph.tenPhongHoc}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                    <label for="class">Phòng học</label>                
                </div>
                <div class="form-floating mt-3 mb-3">
                    <form:select class="form-select" id="classGV" name="classGV" path="idGiangVien" required="required">
                        <c:forEach items="${giangvien}" var="gv">
                            <c:choose>
                                <c:when test="${gv.idGiangVien == phieumonhoc.idGiangVien.idGiangVien}">
                                    <option value="${gv.idGiangVien}" selected>${gv.hoTen}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${gv.idGiangVien}">${gv.hoTen}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                    <label for="classGV">Giảng viên</label>                
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:input type="date" path="ngayBatDau" class="form-control" id="startdate" placeholder="Ngày bắt đầu" name="startdate" required="required"/>
                    <label for="startdate">Ngày bắt đầu</label>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:input type="date" path="ngayKetThuc" class="form-control" id="enddate" placeholder="Ngày kết thúc" name="enddate" required="required"/>
                    <label for="enddate">Ngày kết thúc</label>
                </div>
                <div class="btn-form-addsv">
                    <button class="btn input-form-addsv" type="submit" >
                        Cập nhật
                    </button>
                </div>  
            </form:form>
        </div>
    </div>