/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.pojo;

import lombok.Data;

/**
 *
 * @author FPTSHOP
 */
@Data
public class PhieuMonHoc {
    private int idMonHoc;
    private String tenMonHoc;
    private String phongHoc;
    private int idSinhVien;
    private int dem;

    /**
     * @return the idMonHoc
     */
    public int getIdMonHoc() {
        return idMonHoc;
    }

    /**
     * @return the tenMonHoc
     */
    public String getTenMonHoc() {
        return tenMonHoc;
    }

    /**
     * @return the phongHoc
     */
    public String getPhongHoc() {
        return phongHoc;
    }

    /**
     * @return the idSinhVien
     */
    public int getIdSinhVien() {
        return idSinhVien;
    }

    /**
     * @return the dem
     */
    public int getDem() {
        return dem;
    }
}
