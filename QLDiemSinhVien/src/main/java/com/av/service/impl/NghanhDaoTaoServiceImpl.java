/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.service.impl;

import com.av.pojo.Nganhdaotao;
import com.av.repository.NghanhDaoTaoRepository;
import com.av.service.NghanhDaoTaoService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FPTSHOP
 */
@Service
public class NghanhDaoTaoServiceImpl implements NghanhDaoTaoService{
    
    @Autowired
    private NghanhDaoTaoRepository ndtRepository;

    @Override
    public List<Nganhdaotao> getNganhdaotaos(Map<String, String> params) {
        return this.ndtRepository.getNganhdaotaos(params);
    }

   
       
}
