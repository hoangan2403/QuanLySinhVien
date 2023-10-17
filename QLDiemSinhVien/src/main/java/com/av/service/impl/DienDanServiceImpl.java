/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.service.impl;

import com.av.pojo.Cauhoidiendang;
import com.av.pojo.Traloidiendan;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.av.repository.DienDanRepository;
import com.av.service.DienDanService;

/**
 *
 * @author Admin
 */
@Service
public class DienDanServiceImpl implements DienDanService {

    @Autowired
    private DienDanRepository loRepository;

    @Override
    public Object getCauHoi(Map<String, String> params) {
        return loRepository.getCauHoi(params);
    }

    @Override
    public boolean addOrUpdateTraloi(Traloidiendan p) {
        return loRepository.addOrUpdateTraloi(p);
    }

    @Override
    public List<Object> getCauHoiDienDan() {
        return loRepository.getCauHoiDienDan();
    }

    @Override
    public List<Object> getTraloi(Map<String, String> params) {
        return loRepository.getTraLoi(params);
    }

    @Override
    public boolean addOrUpdateCauHoi(Cauhoidiendang p) {
        return loRepository.addOrUpdateCauHoi(p);
    }
    @Override
    public boolean deleteCauHoi(Map<String, String> params) {
        Cauhoidiendang cauhoi = this.loRepository.getCauHoiById(params);
        this.loRepository.deleteCauHoi(cauhoi);
        return this.loRepository.deleteCauHoi(cauhoi);
    }
}
