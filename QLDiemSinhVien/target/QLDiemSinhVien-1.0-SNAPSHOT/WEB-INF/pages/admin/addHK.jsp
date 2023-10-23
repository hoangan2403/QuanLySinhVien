<%-- 
    Document   : addHK
    Created on : Oct 3, 2023, 3:51:20 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/giaovu/hocky/add" var="action"/>
<div class="row">
    <div class=" margin-auto form-login  form-addSV ">
        <h1 class="text-center">
            <c:choose>
                <c:when test="${hocki.idHocKy != null}">
                    Cập nhật học kì
                </c:when>
                <c:otherwise>
                    Thêm học kì
                </c:otherwise>
            </c:choose>
        </h1>
        <div class="text-form-addSV">
            <form:form modelAttribute="hocki" action="${action}" method="post">
                <form:hidden path="idHocKy"/>
                <div class="form-floating mt-3 mb-3">
                    <form:select class="form-select" id="class" name="class" path="tenHocKy" required="required">
                        <c:forEach items="${loaihocki}" var="lhk">
                            <c:choose>
                                <c:when test="${lhk.idLoaiHocKy == hocki.tenHocKy.idLoaiHocKy}">
                                    <option value="${lhk.idLoaiHocKy}" selected>${lhk.tenHocKy}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${lhk.idLoaiHocKy}">${lhk.tenHocKy}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                    <label for="class">Danh mục học kì</label> 
                </div>
                <div class="form-floating mt-3 mb-3">
                    <form:input type="date" path="ngayDangKy" class="form-control" id="datedk" placeholder="Ngày đăng ký" name="date" required="required"/>
                    <label for="datedk">Ngày đăng ký</label>
                </div>
                <div class="form-floating mt-3 mb-3">
                    <form:input type="date" path="ngayHetHan" class="form-control" id="datehh" placeholder="Ngày hết hạn" name="date" required="required"/>
                    <label for="datehh">Ngày hết hạn</label>
                </div>
                <div class="form-floating mt-3 mb-3">
                    <form:input  type="date" path="ngayBatDau" class="form-control" id="datebd" placeholder="Ngày bắt đầu" name="date" required="required"/>
                    <label for="datebd">Ngày bắt đầu</label>
                </div>
                <div class="form-floating mt-3 mb-3">
                    <form:input  type="date" path="ngayKetThuc" class="form-control" id="datekt" placeholder="Ngày kết thúc" name="date" required="required"/>
                    <label for="datekt">Ngày kết thúc</label>
                </div>

                <div class="form-floating mt-3 mb-3">
                    <form:select class="form-select" id="classlh" name="class" path="idLop" required="required">
                        <c:forEach items="${listLopHoc}" var="lh">
                            <c:choose>
                                <c:when test="${lh.idLopHoc == hocki.idLop.idLopHoc}">
                                    <option value="${lh.idLopHoc}" selected>${lh.tenLopHoc}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${lh.idLopHoc}">${lh.tenLopHoc}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                    <label for="classlh">Danh mục lớp học</label> 
                </div>    
                <div class="btn-form-addsv">
                    <button class="btn input-form-addsv" type="submit" >
                        <c:choose>
                            <c:when test="${hocki.idHocKy != null}">
                                Cập nhật học kì
                            </c:when>
                            <c:otherwise>
                                Thêm học kì
                            </c:otherwise>
                        </c:choose>
                    </button>
                </div>
            </form:form>
        </div>
    </div>

</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        var startDateField = document.getElementById("datebd");
        var endDateField = document.getElementById("datekt");
        var startDateRegister = document.getElementById("datedk");
        var endDateRegister = document.getElementById("datehh");

        
        // Kiểm tra khi có sự thay đổi trong trường ngày bắt đầu
        startDateField.addEventListener("change", function () {
            //Ngay hoc
            var startDateValue = new Date(startDateField.value);
            var endDateValue = new Date(endDateField.value);
            //ngaydangky
            var startDateRGTValue = new Date(startDateRegister.value);
            var endDateRGTValue = new Date(endDateRegister.value);
            var currentDate = new Date();
            // Kiểm tra nếu ngày kết thúc không sau ngày bắt đầu
            if (endDateValue <= startDateValue) {
                alert("Ngày kết thúc phải sau ngày bắt đầu.");
                endDateField.value = ""; // Xóa giá trị ngày kết thúc nếu không hợp lệ
            }

            var endDate15DaysBefore = new Date(endDateRGTValue);
            endDate15DaysBefore.setDate(endDate15DaysBefore.getDate() + 10);
            // Kiểm tra nếu ngày bắt đầu không sau thời điểm hiện tại
            if (startDateValue <= endDate15DaysBefore) {
                alert("Ngày bắt đầu phải sau ngày kết thúc đăng ký tối thiểu 10 ngày.");
                startDateField.value = ""; // Xóa giá trị ngày bắt đầu nếu không hợp lệ
            }


        });

        // Kiểm tra khi có sự thay đổi trong trường ngày kết thúc
        endDateField.addEventListener("change", function () {
            var startDateValue = new Date(startDateField.value);
            var endDateValue = new Date(endDateField.value);

            var startDate15DaysBefore = new Date(startDateValue);
            startDate15DaysBefore.setDate(startDate15DaysBefore.getDate() + 80);
            // Kiểm tra nếu ngày kết thúc không sau ngày bắt đầu
            if (endDateValue <= startDate15DaysBefore) {
                alert("Ngày kết thúc phải sau ngày bắt đầu 2.5 tháng.");
                endDateField.value = ""; // Xóa giá trị ngày kết thúc nếu không hợp lệ
            }
        });

        // Kiểm tra khi có sự thay đổi trong trường ngày đăng kí
        startDateRegister.addEventListener("change", function () {
            //Ngay hoc
            var startDateValue = new Date(startDateField.value);
            var endDateValue = new Date(endDateField.value);
            //ngaydangky
            var startDateRGTValue = new Date(startDateRegister.value);
            var endDateRGTValue = new Date(endDateRegister.value);
            var currentDate = new Date();

            if (startDateRGTValue <= currentDate) {
                alert("Ngày bắt đầu đăng ký phải sau thời điểm hiện tại.");
                startDateRegister.value = "";
            }
        });
        endDateRegister.addEventListener("change", function () {
            //Ngay hoc
            var startDateValue = new Date(startDateField.value);
            var endDateValue = new Date(endDateField.value);
            //ngaydangky
            var startDateRGTValue = new Date(startDateRegister.value);
            var endDateRGTValue = new Date(endDateRegister.value);
            var currentDate = new Date();


            var startDate15DaysBefore = new Date(startDateRGTValue);
            startDate15DaysBefore.setDate(startDate15DaysBefore.getDate() + 5);
            if (endDateRGTValue < startDate15DaysBefore) {
                alert("Ngày kết thúc đăng ký phải sau ngày bắt đầu tối thiểu 5 ngày.");
                endDateRegister.value = "";
            }
        });
    });
</script>