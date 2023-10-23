<%-- 
    Document   : addMH
    Created on : Aug 18, 2023, 10:34:01 AM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/giaovu/monhoc/add" var="action"/>
<div class="row">
    <div class=" margin-auto form-login  form-addSV ">
        <h1 class="text-center">
            <c:choose>
                <c:when test="${monhocc.idMonHoc != null}">
                    Cập nhật môn học
                </c:when>
                <c:otherwise>
                    Thêm môn học
                </c:otherwise>
            </c:choose>
        </h1>
        <div class="text-form-addSV">
            <form:form modelAttribute="monhocc" method="post" action="${action}">
                <form:hidden path="idMonHoc"/>
                <%--<form:hidden path="idGiangVien"/>--%>
                <div class="form-floating mb-3 mt-3">
                    <form:input type="text" class="form-control" path="tenMonHoc"  id="name" placeholder="Tên Môn học" name="name" required="required"/>
                    <label for="name">Tên môn học</label>
                </div>
                <!--                <div class="form-floating mb-3 mt-3">
                <%--<form:input type="text" class="form-control" path="hinhThucThi"  id="examform" placeholder="Hình thức thi" name="examform" />--%>
                <label for="examform">Hình thức thi</label>
            </div>-->
                <div class="form-floating mb-3 mt-3">
                    <form:input type="number" class="form-control" path="soTinChi"  id="number" placeholder="Số tín chỉ" name="number" required="required"/>
                    <label for="number">Số tín chỉ</label>
                </div>
                <!--                <div class="form-floating mb-3 mt-3">
                <%--<form:input type="number" class="form-control" path="hocKy" id="study" placeholder="Học kỳ" name="study" />--%>
                <label for="study">Học Kỳ</label>
            </div>-->
                <!--                <div class="form-floating mb-3 mt-3">
                <%--<form:input type="text" class="form-control" path="phongHoc"  id="classroom" placeholder="Phòng học" name="classroom" />--%>
                <label for="classroom">Phòng học</label>
            </div>-->
                <!--                <div class="form-floating mt-3 mb-3">
                <%--<form:select class="form-select" id="teacher" name="teacher" path="idGiangVien">--%>
                <%--<c:forEach items="${giangvien}" var="gv">--%>
                <%--<c:choose>--%>
                <%--<c:when test="${gv.idGiangVien == monhocc.idGiangVien.idGiangVien}">--%>
                    <option value="${gv.idGiangVien}" selected>${gv.hoTen}</option>
                <%--</c:when>--%>
                <%--<c:otherwise>--%>
                    <option value="${gv.idGiangVien}">${gv.hoTen}</option>
                <%--</c:otherwise>--%>
                <%--</c:choose>--%>

                <%--</c:forEach>--%>
                <%--</form:select>--%>
                 <label for="teacher">Danh mục giảng viên</label>
            </div>    -->
                <div class="btn-form-addsv">
                    <button class="btn input-form-addsv" type="submit" >
                        <c:choose>
                            <c:when test="${monhocc.idMonHoc != null}">
                                Cập nhật môn học
                            </c:when>
                            <c:otherwise>
                                Thêm môn học
                            </c:otherwise>
                        </c:choose>
                    </button>
                </div>
            </form:form>
        </div>
    </div>
</div>
