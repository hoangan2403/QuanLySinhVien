/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.service.impl;

import com.av.pojo.Hedaotao;
import com.av.repository.HeDaoTaoRepository;
import com.av.service.HeDaoTaoService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FPTSHOP
 */
@Service
public class HeDaoTaoServiceImpl implements HeDaoTaoService{
    
    @Autowired
    private HeDaoTaoRepository hdtRepo;

    @Override
    public List<Hedaotao> getHedaotaos(Map<String, String> params) {
        return this.hdtRepo.getHedaotaos(params);
    }

    @Override
    public boolean addOrUpdateHeDT(Hedaotao hdt) {
        return this.hdtRepo.addOrUpdateHeDT(hdt);
    }

    @Override
    public Hedaotao getHedaotaoById(int id) {
        return this.hdtRepo.getHedaotaoById(id);
    }

    @Override
    public boolean deleteHDT(int id) {
        return this.hdtRepo.deleteHDT(id);
    }

    @Override
    public long countHeDaoTao() {
        return this.hdtRepo.countHeDaoTao();
   }
    
}
