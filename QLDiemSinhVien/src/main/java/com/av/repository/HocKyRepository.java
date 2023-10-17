/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.av.repository;

import com.av.pojo.Hocky;
import com.av.pojo.Loaihocky;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FPTSHOP
 */
public interface HocKyRepository {
    List<Hocky> getHockys(Map<String, String> params);
    boolean addOrUpdateHocKy(Hocky hk);
    List<Loaihocky> getLoaihockys(Map<String, String> params);
    Hocky getHockyById(int id);
    List<Hocky> getHocKyByIdLop(int idLop);
    Long CountHocKyByIdLop(int idLop);
    boolean deleteHK(int idHK);
    long countHocKy();
    
}
