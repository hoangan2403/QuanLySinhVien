/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.av.service;

import com.av.pojo.Giangvien;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FPTSHOP
 */
public interface GiangVienService {
    List<Giangvien> getGiangviens(Map<String, String> params);
    boolean addOrUpdateGiangVien(Giangvien gv);
    Giangvien getGiangVienById(int idGiangVien);
    boolean deleteGV(int idGiangVien);
    Giangvien getGiangVienByIdTaiKhoan(int idTaiKhoan);
    long countGiangVien();

}
