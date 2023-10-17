/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.pojo;

/**
 *
 * @author Admin
 */
import java.util.Comparator;

public class DiemMonHocComparator implements Comparator<DiemMonHoc> {
    @Override
    public int compare(DiemMonHoc monHoc1, DiemMonHoc monHoc2) {
        // So sánh theo thuộc tính hocKySinhVien
        return Integer.compare(monHoc1.getHocKySinhVien(), monHoc2.getHocKySinhVien());
    }
}