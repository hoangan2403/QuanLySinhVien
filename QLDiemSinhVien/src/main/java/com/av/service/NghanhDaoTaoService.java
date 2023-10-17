/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.av.service;

import com.av.pojo.Nganhdaotao;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FPTSHOP
 */


public interface NghanhDaoTaoService {
    List<Nganhdaotao> getNganhdaotaos(Map<String, String> params);

}
