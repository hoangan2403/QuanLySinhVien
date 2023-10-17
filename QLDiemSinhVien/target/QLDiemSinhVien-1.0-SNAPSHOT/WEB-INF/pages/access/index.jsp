<%-- 
    Document   : login
    Created on : Jul 20, 2023, 4:35:21 PM
    Author     : FPTSHOP
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">
    <div class="row ">
        <div class=" form-login  shadow-lg ">
            <c:if test="${param.error != null}">
                <div class="alert alert-danger text-size">
                    Da xay ra loi!
                </div>
            </c:if>
            <c:if test="${param.accessDenied != null}">
                <div class="alert alert-danger text-size">
                    Bạn không có quyền truy cập!!!!
                </div>
            </c:if>
            <div class="text-center " >
                <h4 class="text-login">Đăng nhập</h4>
            </div>
            <c:url value="/" var="action" />
            <form method="post"  action="${action}">
                <div class="mb-3 mt-3">
                    <input type="text" class="form-control text-size" id="username" placeholder="Nhập email" name="username" required/>
                </div>
                <div class="mb-3">
                    <input type="password" class="form-control text-size" id="password" placeholder="Nhập password" name="password" required/>
                </div>

                <div >
                    <input type="submit" class="btn btn-bg btn-submit text-size" value="Đăng Nhập"/>
                 
                    <hr  width="100%" size="3px" align="center" color="#9C9C9C" />
                    <div class="btn-submit">
                        <p>Sinh viên chưa có tài khoản <a href="<c:url value="/signup" />" class=" text-center text-sign-in">Đăng ký</a></p>
                    </div>
                </div>
                <div>
                    <P>Nếu sinh viên lần đầu đăng nhập:</p>
                    <p class="text-content">Vui lòng chọn đăng ký, và đăng kí tài khoản bằng email trường cung cấp để có thể truy cập,
                        trong trường hợp quên mật khẩu đăng nhập, 
                        vui lòng liên hệ phòng quản lý sinh viên để được hỗ trợ. </p>
                </div>
            </form>
        </div>
    </div>
</div>
                    


