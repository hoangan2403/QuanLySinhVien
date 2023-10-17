/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.av.service;

import com.av.pojo.MonhocHocky;
import com.av.pojo.PhieuMonHoc;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FPTSHOP
 */
public interface PhieuMonHocService {
//    boolean addPhieuMH(Map<String, PhieuMonHoc> carts);
//    PhieuMonHoc getPhieuMonHoc();
//    boolean deletePhieuMH(int idMonHoc, int idSinhVien);
    boolean addPhieuMHHK(MonhocHocky mh);
    List<MonhocHocky> getMonhocByHockys(int id);
    boolean updatePhieuMHHK(MonhocHocky mh);
    MonhocHocky getMonhocHocky(int id);
    boolean deleteMHHK(int id);
}
