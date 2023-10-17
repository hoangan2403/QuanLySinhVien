/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.av.repository;

import com.av.pojo.Hedaotao;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FPTSHOP
 */
public interface HeDaoTaoRepository {
    List<Hedaotao> getHedaotaos(Map<String, String> params);
    boolean addOrUpdateHeDT(Hedaotao hdt);
    Hedaotao getHedaotaoById(int id);
    boolean deleteHDT(int id);
    long countHeDaoTao();
}
