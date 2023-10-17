/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.service.impl;

import com.av.pojo.MonhocHocky;
import com.av.pojo.PhieuMonHoc;
import com.av.repository.MonHocRepository;
import com.av.repository.PhieuMonHocRepository;
import com.av.service.PhieuMonHocService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FPTSHOP
 */
@Service
public class PhieuMonHocServiceImpl implements PhieuMonHocService {

    @Autowired
    private PhieuMonHocRepository phieuMHRepo;
//
//    @Override
//    public boolean addPhieuMH(Map<String, PhieuMonHoc> carts) {
//        return this.phieuMHRepo.addPhieuMH(carts);
//    }
//
//    @Override
//    public PhieuMonHoc getPhieuMonHoc() {
//        return this.phieuMHRepo.getPhieuMonHoc();
//    }
//
//    @Override
//    public boolean deletePhieuMH(int idMonHoc, int idSinhVien) {
//        PhieuMonHoc pmh = this.phieuMHRepo.getPhieuMonHocByIdMh(idMonHoc, idSinhVien);
//        return this.phieuMHRepo.deletePhieuMH(pmh);
//    }

    @Override
    public boolean addPhieuMHHK(MonhocHocky mh) {
        if(mh.getListSoLuong().length<=0){
            return false;
        }
        for (int i = 0; i < mh.getListSoLuong().length; i++) {
            MonhocHocky mhmoi = new MonhocHocky();
            mhmoi.setIdHocky(mh.getIdHocky());
            mhmoi.setIdMonHoc(mh.getListMonhocs()[i]);
            mhmoi.setSoLuong(mh.getListSoLuong()[i]);
            mhmoi.setNgayBatDau(mh.getListDateBatDau()[i]);
            mhmoi.setNgayKetThuc(mh.getListDateKetThuc()[i]);
            mhmoi.setIdGiangVien(mh.getListGiangviens()[i]);
            mhmoi.setPhongHoc(mh.getListPhonghocs()[i]);
            if (this.phieuMHRepo.addPhieuMHHK(mhmoi) == false) {
                return false;
            }
        }

        return true;
    }

    @Override
    public List<MonhocHocky> getMonhocByHockys(int id) {
        return this.phieuMHRepo.getMonhocByHockys(id);
    }

    @Override
    public boolean updatePhieuMHHK(MonhocHocky mh) {
        return this.phieuMHRepo.updatePhieuMHHK(mh);
    }

    @Override
    public MonhocHocky getMonhocHocky(int id) {
        return this.phieuMHRepo.getMonhocHocky(id);
    }

    @Override
    public boolean deleteMHHK(int id) {
        return this.phieuMHRepo.deleteMHHK(id);
    }

}
