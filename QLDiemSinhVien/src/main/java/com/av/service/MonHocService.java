/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.av.service;

import com.av.pojo.Monhoc;
import com.av.pojo.MonhocHocky;
import com.av.pojo.Monhocdangky;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FPTSHOP
 */
public interface MonHocService {
    List<Monhoc> getMonHocs(Map<String, String> params);
    boolean addOrUpdateMonHoc(Monhoc mh);
    Monhoc getMonHocById(int id);
    boolean deleteMonHoc(int id);

    List<MonhocHocky> getMonHocByGiangVien(Map<String, String> params);
    
    List<MonhocHocky> getMonHocByGiangVienChuaDay(Map<String, String> params);
    List<MonhocHocky> getMonHocByGiangVienDaDay(Map<String, String> params);

    Long countMonHoc();

    List<Monhoc> getMonHocByIdSinhVien(int idSinhvien);
    List<Monhoc> getMonHocByIdSinhVienDangHoc(int idSinhvien);
    
    List<MonhocHocky> getMonHocHocKy(Map<String, String> params);
     List<Monhocdangky> getMonHocSinhVienDangKy(Map<String, String> params);
     
     boolean thanhToanHocPhi(Map<String, String> params);
     
    
}
