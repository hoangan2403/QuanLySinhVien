<%-- 
    Document   : dangKiMonHocHocKi
    Created on : Oct 6, 2023, 4:35:33 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url value="/giaovu/hocki/addMH/${hocki.idHocKy}" var="action"/>
<div class="display-add-Mh">
    <div class="info-nav">
        <div class="info-hk">
            <label class="header-info-svmh"><b>${hocki.tenHocKy.tenHocKy} : </b></label>
            <div class="label-info-mh">
                <label><b>Lớp học: </b></label>
                <label>${hocki.idLop.tenLopHoc}</label>
            </div >
            <div class="label-info-mh">
                <label><b>Khóa học: </b></label>
                <label>${hocki.idLop.idKhoaHoc.tenKhoa}</label>
            </div >
            <div class="label-info-mh">
                <label><b>Nghành đào tạo: </b></label>
                <label>${hocki.idLop.idNganh.tenNganhDaoTao}</label>
            </div >
            <div class="label-info-mh">
                <label><b>Ngày bắt đầu: </b></label>
                <label>
                    <fmt:formatDate value="${hocki.ngayBatDau}" pattern="dd-MM-yyyy"/>
                </label>
            </div>
            <div class="label-info-mh">
                <label><b>Ngày kết thúc: </b></label>
                <label>
                    <fmt:formatDate value="${hocki.ngayKetThuc}" pattern="dd-MM-yyyy" />
                </label>
            </div>
        </div>
    </div>

    <div class="table-info-mh">
        <div class="title-gv">

            <div class="nav-hk">
                <form class="search" action="${action}">
                    <input class="search-input"  type="text" name="tenMH" placeholder="Search...."/>
                    <button class="search-button"><i class="fa-solid fa-magnifying-glass" style="color: #b7b7b8;"></i></button>
                </form>
            </div >

        </div>
        <table class="table">
            <thead>
                <tr>
                    <th>Mã môn học</th>
                    <th>Tên môn học</th>
                    <th>Số lượng</th>
                    <th>Ngày bắt đầu</th>
                    <th>Ngày kết thúc</th>
                    <th>Phòng học</th>
                    <th>Giảng viên</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <form:form  id = "form" method="post" modelAttribute="phieumonhoc" action="${action}">
                    <form:input type="hidden" path="idHocky" value="${hocki.idHocKy}" />
                <div class="btn-hk">
                    <button  class="btn-mhhk" type="submit">Lưu</button>
                </div>
                <c:forEach items="${monhoc}" var="mh">
                    <tr class="object-row">

                        <td>${mh.idMonHoc}</td>
                        <td>${mh.tenMonHoc}</td>
                        <td>
                            <form:input path="listSoLuong" type="number" class="form-selector" required="required"/>
                        </td>
                        <td>
                            <form:input  id="startDateField" path="listDateBatDau" type="date" class="form-selector" required="required"/>
                        </td>
                        <td>
                            <form:input  id="endDateField" path="listDateKetThuc" type="date" class="form-selector" required="required"/>
                        </td>
                        <td>
                            <form:select class="form-selector" name="class" path="listPhonghocs" id="selectPhonghoc" required="required">
                                <c:forEach items="${phonghoc}" var="ph">
                            <option value="${ph.idPhongHoc}">${ph.tenPhongHoc}</option>
                        </c:forEach>
                    </form:select>
                    </td>
                    <td>
                        
                        <form:select class="form-selector" name="class" path="listGiangviens"  required="required">
                            <c:forEach items="${giangvien}" var="gv">
                            <option value="${gv.idGiangVien}">${gv.hoTen}</option>
                        </c:forEach>
                    </form:select>
                    </td>

                    <td> 
                        <%--<form:checkbox  path="listMonhocs" value="${mh}"/>--%>
                        <c:set var="elementExists" value="false" />
                        <c:forEach var="item" items="${mondachon}">
                            <c:if test="${item.idMonHoc.idMonHoc == mh.idMonHoc}">
                                <c:set var="elementExists" value="true" />
                            </c:if>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${elementExists eq false}">
                                <form:checkbox class="form-checkbox" name="myCheckbox" path="listMonhocs" value="${mh}"/>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox"  name="myCheckbox" value="someValue" style="margin: 0 20px;" checked="true" disabled="disabled">
                            </c:otherwise>
                        </c:choose>
                    </td>
                    </tr>
                </c:forEach>
            </form:form>
            </tbody>
        </table>

    </div>
</div>
<script>

    


    function submitForm() {
        document.forms[0].submit();
    }
    function luu() {
        // Get all the input and select elements in the form
        document.getElementById('form').submit();

    }


    $(document).ready(function () {
        $('#selectPhonghoc').on('change', function () {
            var selectedValue = $(this).val();
            $('#selectedValueContainer').text('Giá trị được chọn: ' + selectedValue);
        });
    });
    document.addEventListener("DOMContentLoaded", function () {
        var startDateField = document.getElementById("startDateField");
        var endDateField = document.getElementById("endDateField");

        // Kiểm tra khi có sự thay đổi trong trường ngày bắt đầu
        startDateField.addEventListener("change", function () {
            var startDateValue = new Date(startDateField.value);
            var endDateValue = new Date(endDateField.value);
            var currentDate = new Date();
            // Kiểm tra nếu ngày kết thúc không sau ngày bắt đầu
            if (endDateValue <= startDateValue) {
                alert("Ngày kết thúc phải sau ngày bắt đầu.");
                endDateField.value = ""; // Xóa giá trị ngày kết thúc nếu không hợp lệ
            }
            // Kiểm tra nếu ngày bắt đầu không sau thời điểm hiện tại
            if (startDateValue <= currentDate) {
                alert("Ngày bắt đầu phải sau thời điểm hiện tại.");
                startDateField.value = ""; // Xóa giá trị ngày bắt đầu nếu không hợp lệ
            }

        });

        // Kiểm tra khi có sự thay đổi trong trường ngày kết thúc
        endDateField.addEventListener("change", function () {
            var startDateValue = new Date(startDateField.value);
            var endDateValue = new Date(endDateField.value);

            // Kiểm tra nếu ngày kết thúc không sau ngày bắt đầu
            if (endDateValue <= startDateValue) {
                alert("Ngày kết thúc phải sau ngày bắt đầu.");
                endDateField.value = ""; // Xóa giá trị ngày kết thúc nếu không hợp lệ
            }
        });
    });
    window.onload = function () {
        let checkboxes = document.getElementsByClassName("form-checkbox");
        let form_sect = document.getElementsByClassName("form-selector");

        console.log(form_sect);
        for (let i = 0; i < form_sect.length; i++) {
            form_sect[i].disabled = true;
        }
        for (let i = 0; i < checkboxes.length; i++) {
            checkboxes[i].addEventListener("click", function handleClick(event) {
                if (event.target.checked) {
                    console.log("Đã chọn")
                    let parent = checkboxes[i].parentNode.parentNode;
                    let child = parent.querySelectorAll("td .form-selector")
                    console.log(child)
//                    child.disabled = false 
                    for (let j = 0; j < child.length; j++) {
                        child[j].disabled = false;
                    }
                    ;
                } else {
                    console.log("Chưa chọn");
                    for (let i = 0; i < form_sect.length; i++) {
                        form_sect[i].disabled = true;
                    }
                    ;
                }
            });
        }
    };


</script>