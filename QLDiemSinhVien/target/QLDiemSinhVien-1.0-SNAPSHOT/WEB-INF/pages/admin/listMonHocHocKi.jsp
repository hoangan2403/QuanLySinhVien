<%-- 
    Document   : listMonHocHocKi
    Created on : Oct 9, 2023, 8:59:21 AM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="text-form-addSV">
    <div class="table-sv">
        <div class="title-gv">
            <h3 class="text">Danh sách môn học</h3>
        </div>
        <table class="table ">
            <thead>
                <tr>
                    <th>Mã</th>
                    <th>Tên môn học</th>
                    <th>Số lượng </th>
                    <th>Phòng học</th>
                    <th>Giảng viên</th>
                    <th>Ngày bắt đầu</th>
                    <th>Ngày kết thúc</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${mondachon}" var="hk">
                    <tr>
                        <td>${hk.idMonHocHocKy}</td>
                        <td>${hk.idMonHoc.tenMonHoc}</td>
                        <td>${hk.soLuong}</td>
                        <td>${hk.phongHoc.tenPhongHoc}</td>
                        <td>${hk.idGiangVien.hoTen}</td>
                        <td>
                            <fmt:formatDate value="${hk.ngayBatDau}" pattern="dd-MM-yyyy" />
                        </td>
                        <td>
                            <fmt:formatDate value="${hk.ngayKetThuc}" pattern="dd-MM-yyyy" />
                        </td>
                        <td>
                            <c:url value="/giaovu/hocki/chitiethocki/update/${hk.idMonHocHocKy}" var="api">

                            </c:url>
                            <a href="${api}"class="btn-xoavacn-gv bg-cn " alt="Cập nhật"> <i class="fa-regular fa-pen-to-square"></i></a>
                            <button onclick="deleteSinhVien('${api}')" alt="Xóa" class="btn-xoavacn-gv bg-xoa"><i class="fa-regular fa-trash-can"></i></button>
                        </td>  
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    


</div>
