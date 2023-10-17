/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.av.repository;

import com.av.pojo.Hedaotao;
import com.av.pojo.Khoa;
import com.av.pojo.Khoadaotao;
import com.av.pojo.Nganhdaotao;
import com.av.pojo.Phonghoc;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FPTSHOP
 */
public interface KhoaDaoTaoRepository {
    List<Khoa> getKhoas(Map<String, String> params);
    List<Khoadaotao> getKhoadaotaos (Map<String, String> params);
    List<Phonghoc> getPhonghocs (Map<String, String> params);
    List<Nganhdaotao> getNganhdaotaos (Map<String, String> params);
    //
    boolean addOrUpdateKhoaDT(Khoadaotao kdt);
    boolean addOrUpdateKhoa(Khoa k);
    boolean addOrUpdatePhongHoc(Phonghoc ph);
    boolean addOrUpdateNghanhDT(Nganhdaotao ndt);
    //
    boolean deleteKhoaHoc(int idKhoa); 
    boolean deletePH(int idPH);
    boolean deleteKhoaDaoTao(int id);
    //
    Khoadaotao getKhoadaotaoById(int id);
    Khoa getKhoaById(int id);
    Phonghoc getPhonghocById(int id);
    Nganhdaotao getNganhdaotaoById(int id);
    List<Nganhdaotao> getNganhDaoTaoByIdKhoa(int idKhoa);
    //
    long countNghanhDaoToaById(int id);
    long countLopHocByIdKH(int id);
    long countKhoa();
    long countKhoaDaoTao();
    long countPhongHoc();
    long countNghanhDaoTao();
    
    
}
