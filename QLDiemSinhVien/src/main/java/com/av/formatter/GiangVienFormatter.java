/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.formatter;

import com.av.pojo.Giangvien;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author FPTSHOP
 */
public class GiangVienFormatter implements Formatter<Giangvien>{

    @Override
    public String print(Giangvien gv, Locale locale) {
        return String.valueOf(gv.getIdGiangVien());
    }
    
    @Override
    public Giangvien parse(String text, Locale locale) throws ParseException {
        return new Giangvien(Integer.parseInt(text));
    }
    
    
}
