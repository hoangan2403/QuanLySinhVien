/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.service.impl;

import com.av.pojo.Khoa;
import com.av.pojo.Khoadaotao;
import com.av.pojo.Nganhdaotao;
import com.av.pojo.Phonghoc;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.av.repository.KhoaDaoTaoRepository;
import com.av.service.KhoaDaoTaoService;

/**
 *
 * @author FPTSHOP
 */
@Service
public class KhoaDaoTaoServiceImpl implements KhoaDaoTaoService{
    
    @Autowired
    private KhoaDaoTaoRepository KhoaDaoTaoRepository;

    @Override
    public List<Khoa> getKhoas(Map<String, String> params) {
        return this.KhoaDaoTaoRepository.getKhoas(params);
    }

    @Override
    public List<Khoadaotao> getKhoadaotaos(Map<String, String> params) {
        return this.KhoaDaoTaoRepository.getKhoadaotaos(params);
    }

    @Override
    public List<Phonghoc> getPhonghocs(Map<String, String> params) {
        return  this.KhoaDaoTaoRepository.getPhonghocs(params);
    }

    @Override
    public List<Nganhdaotao> getNganhdaotaos(Map<String, String> params) {
        return  this.KhoaDaoTaoRepository.getNganhdaotaos(params);
    }

    @Override
    public boolean addOrUpdateKhoaDT(Khoadaotao kdt) {
        return this.KhoaDaoTaoRepository.addOrUpdateKhoaDT(kdt);
    }

    @Override
    public boolean addOrUpdateKhoa(Khoa k) {
        return this.KhoaDaoTaoRepository.addOrUpdateKhoa(k);
    }

    @Override
    public boolean addOrUpdatePhongHoc(Phonghoc ph) {
        return this.KhoaDaoTaoRepository.addOrUpdatePhongHoc(ph);
    }

    @Override
    public boolean addOrUpdateNghanhDT(Nganhdaotao ndt) {
        return this.KhoaDaoTaoRepository.addOrUpdateNghanhDT(ndt);
    }

    @Override
    public Khoadaotao getKhoadaotaoById(int id) {
        return this.KhoaDaoTaoRepository.getKhoadaotaoById(id);
    }

    @Override
    public Khoa getKhoaById(int id) {
        return this.KhoaDaoTaoRepository.getKhoaById(id);
    }

    @Override
    public Phonghoc getPhonghocById(int id) {
        return this.KhoaDaoTaoRepository.getPhonghocById(id);
    }

    @Override
    public Nganhdaotao getNganhdaotaoById(int id) {
        return this.KhoaDaoTaoRepository.getNganhdaotaoById(id);
    }

    @Override
    public boolean deleteKhoaDaoTao(int id) {
        return this.KhoaDaoTaoRepository.deleteKhoaDaoTao(id);
    }

    @Override
    public boolean deleteKhoaHoc(int idKhoa) {
        return this.KhoaDaoTaoRepository.deleteKhoaHoc(idKhoa);
    }

    @Override
    public boolean deletePH(int idPH) {
        return this.KhoaDaoTaoRepository.deletePH(idPH);
    }

    @Override
    public long countKhoa() {
        return this.KhoaDaoTaoRepository.countKhoa();
    }

    @Override
    public long countKhoaDaoTao() {
        return this.KhoaDaoTaoRepository.countKhoaDaoTao();
    }

    @Override
    public long countPhongHoc() {
        return this.KhoaDaoTaoRepository.countPhongHoc();
    }

    @Override
    public long countNghanhDaoTao() {
        return this.KhoaDaoTaoRepository.countNghanhDaoTao();
    }

    
}
