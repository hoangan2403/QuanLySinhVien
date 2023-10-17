/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.av.service;

import com.av.pojo.Diem;
import com.av.pojo.DiemMonHoc;
import com.av.pojo.Monhocdangky;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
public interface DiemService {

    double getDiemTrungBinh2(Map<String, String> params);

    double getDiemTrungBinhHe4(Map<String, String> params);
//
//
//

    List<Object> getListDiemTrungBinh(Map<String, String> params);
//

    List<DiemMonHoc> getListDiemDangHoc(Map<String, String> params);

    List<DiemMonHoc> getListDiemDaHoc(Map<String, String> params);
//

    DiemMonHoc addDiem(Map<String, String> params);
//

    String setDiemByCSV(Map<String, String> params, MultipartFile file);
//
//    Diem getDiemByIdMonHoc(int idMonHoc, int idSinhVien);
//

    DiemMonHoc getDiemByIdDiem(Map<String, String> params);
//

    List<Monhocdangky> getDiemByidGiangVien(Map<String, String> params);
//

    boolean khoaDiem(Map<String, String> params);
//    
//    boolean deleteDiem(int idMonHoc, int idSinhVien);

    List<Monhocdangky> getListMonHocDangKy(Map<String, String> params);

    List<Diem> getListDiemByIdMonHocDangKy(int id);
    
    Boolean dangKyMonHoc(Monhocdangky monHoc,Map<String, String> params);
    
    Boolean huyDangKy( Map<String, String> params);
    
    boolean addCotDiem(Map<String, String> params); 
    
    boolean deleteCotDiem(Map<String, String> params); 
    boolean setDiemTB(Map<String, String> params);

}
