<%-- 
    Document   : addKDT
    Created on : Oct 3, 2023, 3:51:32 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/giaovu/khoadaotao/add" var="action"/>
<div class="row">
    <div class=" margin-auto form-login  form-addSV ">
        <h1 class="text-center">
            <c:choose>
                <c:when test="${khoadaotao.idKhoaDaoTao != null}">
                    Cập nhật khoa đào tạo
                </c:when>
                <c:otherwise>
                    Thêm khoa đào tạo
                </c:otherwise>
            </c:choose>
        </h1>
        <div class="text-form-addSV">
            <form:form modelAttribute="khoadaotao" method="post" action="${action}">
                <form:hidden path="idKhoaDaoTao"/>
                <div class="form-floating mb-3 mt-3">
                    <form:input path="tenKhoaDaoTao" type="text" class="form-control" id="name" placeholder="Tên khoa" name="name" required="required"/>
                    <label for="name">Tên khoa</label>
                </div>
                <div class="btn-form-addsv">
                    <button class="btn input-form-addsv" type="submit" >
                        <c:choose>
                            <c:when test="${khoadaotao.idKhoaDaoTao != null}">
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
