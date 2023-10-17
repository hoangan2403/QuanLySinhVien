/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.service.impl;

import com.av.pojo.Hocky;
import com.av.pojo.Loaihocky;
import com.av.repository.HocKyRepository;
import com.av.service.HocKyService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FPTSHOP
 */
@Service
public class HocKyServiceImpl implements HocKyService{
    
    @Autowired
    private HocKyRepository hkRepo;
    
    @Override
    public List<Hocky> getHockys(Map<String, String> params) {
        return this.hkRepo.getHockys(params);
    }

    @Override
    public boolean addOrUpdateHocKy(Hocky hk) {
        return this.hkRepo.addOrUpdateHocKy(hk);
    }

    @Override
    public List<Loaihocky> getLoaihockys(Map<String, String> params) {
        return this.hkRepo.getLoaihockys(params);
    }

    @Override
    public Hocky getHockyById(int id) {
        return this.hkRepo.getHockyById(id);
    }

    @Override
    public boolean deleteHK(int idHK) {
        return this.hkRepo.deleteHK(idHK);
    }

    @Override
    public long countHocKy() {
        return this.hkRepo.countHocKy();
    }
    
}
