/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.av.repository;

import com.av.pojo.Cauhoidiendang;
import com.av.pojo.Diem;
import com.av.pojo.DiemMonHoc;
import com.av.pojo.Lophoc;
import com.av.pojo.Monhocdangky;
import com.av.pojo.Sinhvien;
import com.av.pojo.Taikhoan;
import com.av.pojo.Traloidiendan;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface SinhVienRepository {
    Sinhvien getSinhVien(int idTaiKhoan);   
    List<Sinhvien> getSinhviens(Map<String, String> params);
    // di chuyen lay danh sach lop hoc qua class lop hoc
    boolean addOrUpdateSinhVien(Sinhvien sv);
    Sinhvien getSinhVienById(int idSinhVien);
    Object getSinhVienByIdAPI(int idSinhVien);
    
    boolean deleteSinhVien(int idSinhVien);
     List<DiemMonHoc> getSinhvienByMonHoc(Map<String, String> params);
     Long countSinhVien();
     //update 26/9 danh sach sinh vien theo ma lop
     List<Sinhvien> getSinhVienByIdLop(int idLop);
     // update 11/10
     Long countSinhVienByIdLop(int idLop);
   

}
