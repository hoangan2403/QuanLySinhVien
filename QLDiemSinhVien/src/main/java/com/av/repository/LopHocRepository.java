/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.av.repository;

import com.av.pojo.Lophoc;
import com.av.pojo.Nganhdaotao;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface LopHocRepository {
    List<Lophoc> listLopHoc(Map<String,String> params);
    Lophoc getLopById(int idLop);
    boolean addOrUpdateLopHoc(Lophoc lh);
    long checkTenLop(String tenLop);
    List<Lophoc> getLopByIdHeDaoTao(int id);
    List<Lophoc> getLopByIdKhoaHoc(int id);
    boolean deleteLopHoc(int idLop);
    long countLopHocByIdHDT(int idHDT);
    long countLopHoc();
}
