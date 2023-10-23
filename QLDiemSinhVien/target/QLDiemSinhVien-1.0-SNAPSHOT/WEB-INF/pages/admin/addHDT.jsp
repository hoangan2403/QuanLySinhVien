<%-- 
    Document   : addHDT
    Created on : Oct 3, 2023, 5:23:42 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/giaovu/hedaotao/add" var="action"/>
<div class="row">
    <div class=" margin-auto form-login  form-addSV ">
        <h1 class="text-center">
            <c:choose>
                <c:when test="${hedaotaoo.idhedaotao != null}">
                    Cập nhật hệ đào tạo
                </c:when>
                <c:otherwise>
                    Thêm hệ đào tạo
                </c:otherwise>    
            </c:choose>
        </h1>
        <div class="text-form-addSV">
            <form:form modelAttribute="hedaotaoo" method="post" action="${action}" >
                <form:hidden path="idhedaotao"/>
                <div class="form-floating mb-3 mt-3">
                    <form:input type="text" path="tenHeDaoTao" class="form-control" id="name" placeholder="Tên hệ đào tạo" name="name" required="required"/>
                    <label for="name">Tên hệ đào tạo</label>
                </div>
                <div class="btn-form-addsv">
                    <button class="btn input-form-addsv" type="submit" >
                        <c:choose>
                            <c:when test="${hedaotaoo.idhedaotao != null}">
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
