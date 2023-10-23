<%-- 
    Document   : addLopHoc
    Created on : Oct 3, 2023, 3:51:03 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>

<c:url value="/giaovu/lophoc/add" var="action"/>
<c:if test="${errMsg != null}">
    <div class="alert alert-danger text-size mt-3 ">
        ${errMsg};
    </div>
</c:if>
<div class="row">
    <div class=" margin-auto form-login  form-addSV ">
        <h1 class="text-center">
            <c:choose>
                <c:when test="${lophoc.idLopHoc != null}">
                    Cập nhật lớp học
                </c:when>
                <c:otherwise>
                    Thêm lớp học    
                </c:otherwise>
            </c:choose>

        </h1>
        <div class="text-form-addSV">
            <form:form method="post" modelAttribute="lophoc" action="${action}" >
                <form:hidden path="idLopHoc"/>
                <div class="form-floating mb-3 mt-3">
                    <form:input type="text" path="tenLopHoc" class="form-control" id="name" placeholder="Tên lớp học" name="name" required="required"/>
                    <label for="name">Tên lớp học</label>
                </div>

                <div class="form-floating mt-3 mb-3">

                    <form:select class="form-select" id="class" name="class" path="idKhoaHoc" required="true">
                        <c:forEach items="${khoahoc}" var="kh">
                            <c:choose>
                                <c:when test="${kh.idkhoa == lophoc.idKhoaHoc.idkhoa}">
                                    <option value="${kh.idkhoa}" selected>${kh.tenKhoa}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${kh.idkhoa}">${kh.tenKhoa}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                    <label for="class">Danh mục lớp học</label> 
                </div>
                <div class="form-floating mt-3 mb-3">

                    <form:select class="form-select" id="class" name="class" path="idHeDaoTao" required="required">
                        <c:forEach items="${hedaotao}" var="hdt">
                            <c:choose>
                                <c:when test="${hdt.idhedaotao == lophoc.idHeDaoTao.idhedaotao}">
                                    <option value="${hdt.idhedaotao}" selected>${hdt.tenHeDaoTao}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${hdt.idhedaotao}">${hdt.tenHeDaoTao}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                    <label for="class">Hệ đào tạo </label> 
                </div>
                <div class="form-floating mt-3 mb-3">

                    <form:select class="form-select" id="class" name="class" path="idNganh" required="required">
                        <c:forEach items="${nghanhdaotao}" var="ndt">
                            <c:choose>
                                <c:when test="${ndt.idNganhDaoTao == lophoc.idNganh.idNganhDaoTao}">
                                    <option value="${ndt.idNganhDaoTao}" selected>${ndt.tenNganhDaoTao}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${ndt.idNganhDaoTao}">${ndt.tenNganhDaoTao}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                    <label for="class">Nghành đào tạo  </label> 
                </div>   
                <div class="btn-form-addsv">
                    <button class="btn input-form-addsv" type="submit" >
                        <c:choose>
                            <c:when test="${lophoc.idLopHoc != null}">
                                Cập nhật
                            </c:when>
                            <c:otherwise>
                                Thêm     
                            </c:otherwise>
                        </c:choose>
                    </button>
                </div>
            </form:form>
        </div>
    </div>

</div>
