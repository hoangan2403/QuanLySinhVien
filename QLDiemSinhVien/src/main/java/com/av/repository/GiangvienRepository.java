/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.av.repository;

import com.av.pojo.Giangvien;
import com.av.pojo.Taikhoan;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FPTSHOP
 */
public interface GiangvienRepository {
    List<Giangvien> getGiangviens(Map<String, String> params);
    boolean addOrUpdateGiangVien(Giangvien sv);
    Giangvien getGiangVienById(int idGiangVien);
    Giangvien getGiangVienByIdTaiKhoan(int idTaiKhoan);
    boolean deleteGV(int idGiangVien);
    long countGiangVien();
//    Taikhoan getTaikhoanByGV(int idGiangVien);
    
}
