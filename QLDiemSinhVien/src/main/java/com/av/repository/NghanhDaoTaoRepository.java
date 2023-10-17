/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.av.repository;


import com.av.pojo.Hedaotao;
import com.av.pojo.Hocky;
import com.av.pojo.Khoa;
import com.av.pojo.Khoadaotao;
import com.av.pojo.Lophoc;

import com.av.pojo.Nganhdaotao;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FPTSHOP
 */


public interface NghanhDaoTaoRepository {
    List<Nganhdaotao> getNganhdaotaos(Map<String, String> params);

}
