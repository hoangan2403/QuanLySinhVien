/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.av.repository;

import com.av.pojo.Diem;
import com.av.pojo.DiemMonHoc;
import com.av.pojo.Loaidiem;
import com.av.pojo.MonhocHocky;
import com.av.pojo.Monhocdangky;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
public interface DiemRepository {

    double getDiemTrungBinh2(Map<String, String> params);

    double getDiemTrungBinhHe4(Map<String, String> params);
//        

    List<Object> getListDiemTrungBinh(Map<String, String> params);

    List<DiemMonHoc> getListDiemDangHoc(Map<String, String> params);
    List<DiemMonHoc> getListDiemDaHoc(Map<String, String> params);
//        

    Diem addDiem(Monhocdangky monhoc, List<Diem> diem, DiemMonHoc diem1);

    List<Diem> setDiemByCSV(Map<String, String> params);

    Monhocdangky getDiemByIdMonHoc(int idMonHoc, int idSinhVien);

    Monhocdangky getMonHocDangKyById(int idDiem);

    Loaidiem getLoaiDiemKT1();

    Loaidiem getLoaiDiemKT2();

    Loaidiem getLoaiDiemKT3();
    Loaidiem getLoaiDiemGK();
    Loaidiem getLoaiDiemCK();
    Loaidiem getLoaiDiemTB();

    DiemMonHoc getDiemMonHocByIdDiem(int id);

    List<Monhocdangky> getDiemByidGiangVien(Map<String, String> params);

    boolean khoaDiem(Map<String, String> params);
//        List<Diem> getDiemBySinhVien(int idSinhVien);
//        boolean deleteDiemBySinhVien(int idSinhVien);
//        boolean deleteDiem(int idMonHoc, int idSinhVien);

    List<Diem> getListDiemByIdMonHocDangKy(int id);

    List<Monhocdangky> getListMonHocDangKy(Map<String, String> params);
    
    Monhocdangky dangKyMonHoc(Monhocdangky monHoc, MonhocHocky monHocHocKy);
    Monhocdangky huyDangKy(Monhocdangky monHoc);

    boolean addCotDiem(int idMonHoc);    
    boolean deleteCotDiem(int idMonHoc); 
    
    boolean setDiemTB(int idMonHoc);

}
