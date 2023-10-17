/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.service.impl;

import com.av.pojo.Monhoc;
import com.av.pojo.MonhocHocky;
import com.av.pojo.Monhocdangky;
import com.av.repository.MonHocRepository;
import com.av.service.MonHocService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FPTSHOP
 */
@Service
public class MonHocServiceImpl  implements MonHocService{
    
    @Autowired
    private MonHocRepository mhRepository;

    @Override
    public List<Monhoc> getMonHocs(Map<String, String> params) {
        return this.mhRepository.getMonHocs(params);
    }

    @Override
    public boolean addOrUpdateMonHoc(Monhoc mh) {
        return  this.mhRepository.addOrUpdateMonHoc(mh);
    }

    @Override
    public Monhoc getMonHocById(int id) {
        return this.mhRepository.getMonHocById(id);
    }

    @Override
    public boolean deleteMonHoc(int id) {
        return this.mhRepository.deleteMonHoc(id);
    }

    @Override
    public List<MonhocHocky> getMonHocByGiangVien(Map<String, String> params) {
        return this.mhRepository.getMonHocByGiangVien(params);
    }

    @Override
    public List<Monhoc> getMonHocByIdSinhVien(int idSinhvien) {
        return this.mhRepository.getMonHocByIdSinhVien(idSinhvien);
    }

    @Override
    public List<Monhoc> getMonHocByIdSinhVienDangHoc(int idSinhvien) {
        return this.mhRepository.getMonHocByIdSinhVienDangHoc(idSinhvien);
    }

    @Override
    public List<MonhocHocky> getMonHocHocKy(Map<String, String> params) {
        return this.mhRepository.getMonHocHocKy(params);
    }

    @Override
    public List<Monhocdangky> getMonHocSinhVienDangKy(Map<String, String> params) {
        return this.mhRepository.getMonHocSinhVienDangKy(params);
    }

    @Override
    public List<MonhocHocky> getMonHocByGiangVienChuaDay(Map<String, String> params) {
        return this.mhRepository.getMonHocByGiangVienChuaDay(params);
    }

    @Override
    public List<MonhocHocky> getMonHocByGiangVienDaDay(Map<String, String> params) {
        return this.mhRepository.getMonHocByGiangVienDaDay(params);
    }

    @Override
    public boolean thanhToanHocPhi(Map<String, String> params) {
        return this.mhRepository.thanhToanHocPhi(params);
    }
   
    @Override
    public Long countMonHoc() {
        return this.mhRepository.countMonHoc();
    }
    
}
