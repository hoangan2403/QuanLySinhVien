<%-- 
    Document   : sinhvien
    Created on : Aug 9, 2023, 10:32:02 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="demo" class="carousel slide" data-bs-ride="carousel">

  <!-- Indicators/dots -->
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
    <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
    <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
  </div>

  <!-- The slideshow/carousel -->
  <div class="carousel-inner">
    <div class="carousel-item active">
        <img src="<c:url value="https://res.cloudinary.com/dhcvsbuew/image/upload/v1693030473/h6_vnmhrs.jpg"/>" alt="Los Angeles" class="d-block w-100">
    </div>
    <div class="carousel-item"> 
        <img src="<c:url value="https://res.cloudinary.com/dhcvsbuew/image/upload/v1693029931/h2_rxfaqf.png" />" alt="Chicago" class="d-block w-100">
    </div>
    <div class="carousel-item">
        <img src="<c:url value="https://res.cloudinary.com/dhcvsbuew/image/upload/v1693029930/h3_slbx97.jpg"/>" alt="New York" class="d-block w-100">
    </div>
  </div>

  <!-- Left and right controls/icons -->
  <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
    <span class="carousel-control-next-icon"></span>
  </button>
</div>

