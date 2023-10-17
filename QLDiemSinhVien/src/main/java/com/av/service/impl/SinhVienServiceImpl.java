/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.service.impl;

import com.av.pojo.DiemMonHoc;
import com.av.pojo.Lophoc;
import com.av.pojo.Monhocdangky;
import com.av.pojo.Sinhvien;
import com.av.pojo.Taikhoan;
import com.av.repository.DiemRepository;
import com.av.repository.DienDanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.av.repository.SinhVienRepository;
import com.av.repository.TaiKhoanRepository;
import com.av.service.DienDanService;
import com.av.service.SinhVienService;
import com.av.service.TaiKhoanService;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
@Service
public class SinhVienServiceImpl implements SinhVienService {

    @Autowired
    private SinhVienRepository sinhvienRepository;
    
    @Autowired
    private DienDanRepository dienDanRepository;
    
    @Autowired
    private  DiemRepository diemRepository;
    
    @Autowired
    private TaiKhoanRepository tkRepository;

    @Override
    public Sinhvien getSinhvien(int idTaiKhoan) {

        return sinhvienRepository.getSinhVien(idTaiKhoan);

    }

    @Override
    public List<Sinhvien> getSinhviens(Map<String, String> params) {
        return sinhvienRepository.getSinhviens(params);
    }
    // di chuyen lay danh sach lop hoc qua class lop hoc
    @Override
    public boolean addOrUpdateSinhVien(Sinhvien sv) {
        return sinhvienRepository.addOrUpdateSinhVien(sv);
    }

    @Override
    public Sinhvien getSinhVienById(int idSinhVien) {
        return sinhvienRepository.getSinhVienById(idSinhVien);
    }

//    xoa sinh vien voi khoa ngoai
    @Override
    public boolean deleteSinhVien(int idSinhVien) {
        Sinhvien sv = this.sinhvienRepository.getSinhVienById(idSinhVien);
        if (sv.getIdTaiKhoan() != null) {
            this.dienDanRepository.deleteCauHoiByTaiKhoan(sv.getIdTaiKhoan().getIdTaiKhoan());
            this.dienDanRepository.deleteTraloiByTaiKhoan(sv.getIdTaiKhoan().getIdTaiKhoan());
        }       
//        this.diemRepository.deleteDiemBySinhVien(idSinhVien);
        return sinhvienRepository.deleteSinhVien(idSinhVien);
    }
    @Override
    public List<DiemMonHoc> getSinhvienByMonHoc(Map<String, String> params) {
        return sinhvienRepository.getSinhvienByMonHoc(params);
    }
    
    // dem sv
    @Override
    public Long countSinhVien() {
        return this.sinhvienRepository.countSinhVien();
    }

    
    
    //update 26/9 danh sach sinh vien theo ma lop
    @Override
    public List<Sinhvien> getSinhVienByIdLop(int idLop) {
      return this.sinhvienRepository.getSinhVienByIdLop(idLop);
    }

    @Override
    public Object getSinhVienByIdAPI(int idSinhVien) {
        return this.sinhvienRepository.getSinhVienByIdAPI(idSinhVien);
    }
}
