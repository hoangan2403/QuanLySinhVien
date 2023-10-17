/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.av.repository;

import com.av.pojo.Cauhoidiendang;
import com.av.pojo.Traloidiendan;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface DienDanRepository {
    Object getCauHoi(Map<String, String> params);
    boolean addOrUpdateTraloi(Traloidiendan p);
    boolean addOrUpdateCauHoi(Cauhoidiendang p);
    List<Object> getCauHoiDienDan();
    List<Object> getTraLoi(Map<String, String> params);
    boolean deleteCauHoi(Cauhoidiendang p);
    Cauhoidiendang getCauHoiById(Map<String, String> params);
    List<Traloidiendan> getTraLoiByTaiKhoan(int id);
    boolean deleteTraloiByTaiKhoan(int id);
    List<Cauhoidiendang> getCauhoiByTaiKhoan(int id);
    boolean deleteCauHoiByTaiKhoan(int id);
}
