<%-- 
    Document   : addKH
    Created on : Oct 3, 2023, 3:51:58 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/giaovu/khoahoc/add" var="action"/>
<div class="row">
    <div class=" margin-auto form-login  form-addSV ">
        <h1 class="text-center">
            <c:choose>
                <c:when test="${khoahocc.idkhoa != null}">
                    Cập nhật khóa học
                </c:when>
                <c:otherwise>
                    Thêm khóa học
                </c:otherwise>
            </c:choose>
        </h1>
        <div class="text-form-addSV">
            <form:form method="post" modelAttribute="khoahocc" action="${action}">
                <form:hidden path="idkhoa"/>
                <div class="form-floating mb-3 mt-3">
                    <form:input type="text" path="tenKhoa" class="form-control" id="name" placeholder="Tên khóa học" name="name" required="required"/>
                    <label for="name">Tên khóa học</label>
                </div>
                <div class="btn-form-addsv">
                    <button class="btn input-form-addsv" type="submit" >
                        <c:choose>
                            <c:when test="${khoahocc.idkhoa != null}">
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