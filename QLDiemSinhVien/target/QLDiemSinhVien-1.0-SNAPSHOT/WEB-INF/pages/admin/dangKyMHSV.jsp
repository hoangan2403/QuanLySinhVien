<%-- 
    Document   : dangKyMHSV
    Created on : Aug 20, 2023, 3:03:59 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/giaovu/sinhvien/addMH/${sinhvien.idSinhVien}" var="action"/>
<body onload="loadData()">
    <div class="display-add-MhSV">
        <div class="info-svmh">
            <p class="header-info-svmh">Thông tin sinh viên</p>

            <div class="label-info-svmh">
                <label>Mã số sinh viên: </label>
                <label id="idSinhVien" >${sinhvien.idSinhVien}</label>
            </div>
            <div class="label-info-svmh">
                <label>Họ và tên: </label>
                <label>${sinhvien.hoTen}</label>
            </div >
            <div class="label-info-svmh">
                <label>Ngày sinh: </label>
                <label>${sinhvien.ngaySinh}</label>
            </div>
            <div class="label-info-svmh">
                <label>Hệ đào tạo: </label>
                <label>${sinhvien.heDaoTao}</label>
            </div>
        </div>
        <div class="table-info-svmh">
            <table id="customers">
                <thead>
                    <tr>
                        <th>Mã môn học</th>
                        <th>Tên môn học</th>
                        <th>Phòng học</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${monhoc}" var="mh">
                        <tr>
                            <td>${mh.idMonHoc}</td>
                            <td>${mh.tenMonHoc}</td>
                            <td>${mh.phongHoc}</td>
                            <td> 
                            
                                <a href="javascript:;" onclick="addToCart(${mh.idMonHoc})" >Chọn</a>
                                <a href="javascript:;" onclick="unselectItem(${mh.idMonHoc})" style="display: none;">Hủy chọn</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <h4>Môn học: <span id="cart-counter" class="badge bg-secondary">0</span></h4>
            <div id="spinner" style="display: none;">Loading...</div>
            <div id="table-monhoc">                
            </div>
        </div>
    </div>
</body>
