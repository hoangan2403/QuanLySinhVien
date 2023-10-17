/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.service.impl;

import com.av.pojo.Lophoc;
import com.av.pojo.Nganhdaotao;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.av.repository.LopHocRepository;
import com.av.service.LopHocService;

/**
 *
 * @author Admin
 */
@Service
public class LopHocServiceImpl implements LopHocService{

    @Autowired
    private LopHocRepository daoTao;
    
    @Override
    public List<Lophoc> listLopHoc(Map<String,String> params) {
        return this.daoTao.listLopHoc(params);
    }

    @Override
    public Lophoc getLopById(int idLop) {
        return this.daoTao.getLopById(idLop);
    }
    @Override
    public boolean addOrUpdateLopHoc(Lophoc lh) {
        return this.daoTao.addOrUpdateLopHoc(lh);
    }

    @Override
    public long checkTenLop(String tenLop) {
        return this.daoTao.checkTenLop(tenLop);
    }

    @Override
    public List<Lophoc> getLopByIdHeDaoTao(int id) {
        return this.daoTao.getLopByIdHeDaoTao(id);
    }

    @Override
    public List<Lophoc> getLopByIdKhoaHoc(int id) {
        return this.daoTao.getLopByIdKhoaHoc(id);
    }

    @Override
    public Boolean deleteLopHoc(int idLop) {
        return this.daoTao.deleteLopHoc(idLop);
    }

    @Override
    public long countLopHoc() {
        return this.daoTao.countLopHoc();
    }
    
}
