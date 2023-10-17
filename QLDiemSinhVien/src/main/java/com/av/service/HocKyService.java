/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.av.service;

import com.av.pojo.Hocky;
import com.av.pojo.Loaihocky;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FPTSHOP
 */
public interface HocKyService {
    List<Hocky> getHockys(Map<String, String> params);
    boolean addOrUpdateHocKy(Hocky hk);
    List<Loaihocky> getLoaihockys(Map<String, String> params);
    Hocky getHockyById(int id);
    boolean deleteHK(int idHK);
    long countHocKy();
}
