/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.service.impl;

import com.av.pojo.Giaovu;
import com.av.repository.GiaoVuRepository;
import com.av.service.GiaoVuService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FPTSHOP
 */
@Service
public class GiaoVuServiceImpl implements GiaoVuService{

    @Autowired
    private GiaoVuRepository giaoVuRepo;
    @Override
    public Giaovu getGiaovus(int idTaiKhoan) {
        return this.giaoVuRepo.getGiaovus(idTaiKhoan);
    }
    
}
