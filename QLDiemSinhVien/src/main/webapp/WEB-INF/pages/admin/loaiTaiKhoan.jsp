<%-- 
    Document   : loaiTaiKhoan
    Created on : Oct 6, 2023, 9:57:16 AM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/giaovu/loaitaikhoan" var="action"/>
<div class="nav-tk">
    <form class="search" action="${action}">
        <input class="search-input"  type="text" name="tenLTK" placeholder="Search...."/>
        <button class="search-button"><i class="fa-solid fa-magnifying-glass" style="color: #b7b7b8;"></i></button>
    </form>
</div >
<div class="table-form">
    <div class="row">
        <div class=" margin-auto  form-addltk ">
            <h3 class="text-center">
                <c:choose>
                    <c:when test="${loaitaikhoann.idloaitaikhoan != null}">
                        Cập nhật loại tài khoản
                    </c:when>
                    <c:otherwise>
                        Thêm loại tài khoản
                    </c:otherwise>
                </c:choose>
            </h3>
            <div class="text-form-addSV">
                <form:form method="post" modelAttribute="loaitaikhoann" action="${action}">
                    <form:hidden path="idloaitaikhoan"/>
                    <div class="form-floating mb-3 mt-3">
                        <form:input type="text" path="tenloaitaikhoan" class="form-control" id="name" placeholder="Tên loại tài khoản" name="name" required="required"/>
                        <label for="name">Tên loại tài khoản</label>
                    </div>
                    <div class="btn-form-addsv">
                        <button class="btn input-form-addsv" type="submit" >
                            <c:choose>
                                <c:when test="${loaitaikhoann.idloaitaikhoan != null}">
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
    <div class="table-sv table-ltk">
        <div class="title-gv">
            <h3 class="text-center">Danh sách khóa học</h3>
            
        </div>


        <table class="table " >
            <thead>
                <tr>
                    <th>Mã loại tài khoản</th>
                    <th>Tên loại tài khoản</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${loaitaikhoan}" var="ltk">
                    <tr>
                        <td>${ltk.idloaitaikhoan}</td>
                        <td>${ltk.tenloaitaikhoan}</td>
                        <td>
                            <c:url value="/giaovu/loaitaikhoan/${ltk.idloaitaikhoan}" var="api"/>
                            <a href="${api}" class="btn-xoavacn-gv bg-cn"><i class="fa-regular fa-pen-to-square"></i> </a>
                            <button class="btn-xoavacn bg-xoa-gv "><i class="fa-regular fa-trash-can"></i></button>

                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>