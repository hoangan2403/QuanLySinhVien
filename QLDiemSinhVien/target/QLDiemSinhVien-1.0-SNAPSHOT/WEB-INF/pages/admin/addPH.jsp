<%-- 
    Document   : addPH
    Created on : Oct 3, 2023, 3:51:46 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/giaovu/phonghoc/add" var="action"/>
<div class="row">
    <div class=" margin-auto form-login  form-addSV ">
        <h1 class="text-center">
            Thêm phòng học
        </h1>
        <div class="text-form-addSV">
            <form:form method="post" modelAttribute="phonghocc" action="${action}">
                <form:hidden path="idPhongHoc"/>
                <div class="form-floating mb-3 mt-3">
                    <form:input type="text" path="tenPhongHoc" class="form-control" id="name" placeholder="Tên phòng học" name="name" required="required"/>
                    <label for="name">Tên phòng học</label>
                </div>
                <div class="btn-form-addsv">
                    <button class="btn input-form-addsv" type="submit" >Thêm</button>
                </div>
            </form:form>
        </div>
    </div>

</div>
