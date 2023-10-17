/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.service.impl;

import com.av.pojo.Giangvien;
import com.av.pojo.Monhoc;
import com.av.pojo.Taikhoan;
import com.av.repository.DienDanRepository;
import com.av.repository.GiangvienRepository;
import com.av.repository.MonHocRepository;
import com.av.repository.TaiKhoanRepository;
import com.av.service.GiangVienService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FPTSHOP
 */
@Service
public class GiangVienServiceImpl implements GiangVienService {

    @Autowired
    private GiangvienRepository giangVienRepository;
    
    @Autowired
    private TaiKhoanRepository tkRepo;
    
    @Autowired
    private MonHocRepository mhRepo;

    @Autowired
    private DienDanRepository dienDanRepository;

    @Override
    public List<Giangvien> getGiangviens(Map<String, String> params) {
        return this.giangVienRepository.getGiangviens(params);
    }

    @Override
    public boolean addOrUpdateGiangVien(Giangvien gv) {
        return this.giangVienRepository.addOrUpdateGiangVien(gv);
    }

    @Override
    public Giangvien getGiangVienById(int idGiangVien) {
        return this.giangVienRepository.getGiangVienById(idGiangVien);
    }
    
    @Override
    public boolean deleteGV(int idGiangVien) {
        long countMonhoc = this.mhRepo.CountMonHocInGV(idGiangVien);
        Giangvien gv = this.getGiangVienById(idGiangVien);
        if (countMonhoc == 0 && gv.getIdTaiKhoan() != null) {
            this.dienDanRepository.deleteCauHoiByTaiKhoan(gv.getIdTaiKhoan().getIdTaiKhoan());
            this.dienDanRepository.deleteCauHoiByTaiKhoan(gv.getIdTaiKhoan().getIdTaiKhoan());           
        }
        return this.giangVienRepository.deleteGV(idGiangVien);

    }

    @Override
    public Giangvien getGiangVienByIdTaiKhoan(int idTaiKhoan) {
        return this.giangVienRepository.getGiangVienByIdTaiKhoan(idTaiKhoan);
    }


    @Override
    public long countGiangVien() {
        return this.giangVienRepository.countGiangVien();
    }


}
