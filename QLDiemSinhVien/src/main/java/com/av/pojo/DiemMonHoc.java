/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Admin
 */
@Data
public class DiemMonHoc implements Serializable {

    public void setDiemTB(Double DiemTB) {
        this.DiemTB = DiemTB;
    }
    private Monhocdangky monHoc;
    private int idMonHocDangKy;
    private String tenMonHoc;
    private Double DiemGK = -1.0;
    private Double DiemCK = -1.0;
    private Double DiemKT1 = -1.0;
    private Double DiemKT2 = -1.0;
    private Double DiemKT3 = -1.0;
    private Double DiemTB = -1.0;
    private short khoaMon;
    private short trangThai;
    private String lopHoc;
    private String xepLoai;
    private String hocKy;
    private int hocKySinhVien;

    public DiemMonHoc(Monhocdangky monHoc) {
        this.monHoc = monHoc;
        this.lopHoc = monHoc.getIdMonHoc().getIdHocky().getIdLop().getTenLopHoc();
        this.khoaMon = monHoc.getKhoaMon();
        this.trangThai = monHoc.getTrangThai();
        this.xepLoai = monHoc.getXepLoai();
        this.idMonHocDangKy = monHoc.getIdMonHocDangKy();
        this.hocKySinhVien = monHoc.getIdMonHoc().getIdHocky().getIdHocKy();
        this.hocKy = monHoc.getIdMonHoc().getIdHocky().getTenHocKy().getTenHocKy();
        this.tenMonHoc = monHoc.getIdMonHoc().getIdMonHoc().getTenMonHoc();
    }

    public DiemMonHoc(int idMonHocDangKy, Double DiemGK, Double DiemCK, Double DiemKT1, Double DiemKT2, Double DiemKT3) {
        this.idMonHocDangKy = idMonHocDangKy;
        this.DiemGK = DiemGK;
        this.DiemCK = DiemCK;
        this.DiemKT1 = DiemKT1;
        this.DiemKT2 = DiemKT2;
        this.DiemKT3 = DiemKT3;
    }

    public DiemMonHoc() {
        // Constructor mặc định
    }
//    public Monhocdangky getMonHoc() {
//        return monHoc;
//    }

    public void addDiem(Diem diem) {
        switch (diem.getTenDiem().getIdLoaiDiem()) {
            case 1:
                this.setDiemGK((Double) diem.getSoDiem());
                break;
            case 2:
                this.setDiemCK((Double) diem.getSoDiem());
                break;
            case 3:
                this.setDiemKT1((Double) diem.getSoDiem());
                break;
            case 4:
                this.setDiemKT2((Double) diem.getSoDiem());
                break;
            case 5:
                this.setDiemKT3((Double) diem.getSoDiem());
                break;
            default:
                this.setDiemTB((Double) diem.getSoDiem());
                break;
        }
    }

    /**
     * @return the tenMonHoc
     */
    public String getTenMonHoc() {
        return tenMonHoc;
    }

    /**
     * @param tenMonHoc the tenMonHoc to set
     */
    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    /**
     * @return the DiemGK
     */
    public Double getDiemGK() {
        return DiemGK;
    }

    /**
     * @param DiemGK the DiemGK to set
     */
    public void setDiemGK(Double DiemGK) {
        this.DiemGK = DiemGK;
    }

    /**
     * @return the DiemCK
     */
    public Double getDiemCK() {
        return DiemCK;
    }

    /**
     * @param DiemCK the DiemCK to set
     */
    public void setDiemCK(Double DiemCK) {
        this.DiemCK = DiemCK;
    }

    /**
     * @return the DiemKT1
     */
    public Double getDiemKT1() {
        return DiemKT1;
    }

    /**
     * @param DiemKT1 the DiemKT1 to set
     */
    public void setDiemKT1(Double DiemKT1) {
        this.DiemKT1 = DiemKT1;
    }

    /**
     * @return the DiemKT2
     */
    public Double getDiemKT2() {
        return DiemKT2;
    }

    /**
     * @param DiemKT2 the DiemKT2 to set
     */
    public void setDiemKT2(Double DiemKT2) {
        this.DiemKT2 = DiemKT2;
    }

    /**
     * @return the DiemKT3
     */
    public Double getDiemKT3() {
        return DiemKT3;
    }

    /**
     * @param DiemKT3 the DiemKT3 to set
     */
    public void setDiemKT3(Double DiemKT3) {
        this.DiemKT3 = DiemKT3;
    }

    /**
     * @return the DiemTB
     */
    public Double getDiemTB() {
        return DiemTB;
    }

    /**
     * @return the monHoc
     */
    public Monhocdangky getMonHoc() {
        return monHoc;
    }

    /**
     * @param monHoc the monHoc to set
     */
    public void setMonHoc(Monhocdangky monHoc) {
        this.monHoc = monHoc;
    }

    /**
     * @param DiemTB the DiemTB to set
     */
}
