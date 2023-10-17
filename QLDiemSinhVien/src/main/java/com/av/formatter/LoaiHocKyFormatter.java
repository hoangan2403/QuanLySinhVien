/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.formatter;

import com.av.pojo.Cauhoidiendang;
import com.av.pojo.Loaihocky;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author FPTSHOP
 */
public class LoaiHocKyFormatter implements Formatter<Loaihocky>{

    @Override
    public String print(Loaihocky loaiHK, Locale locale) {
        return String.valueOf(loaiHK.getIdLoaiHocKy());
    }

    @Override
    public Loaihocky parse(String loaiHKId, Locale locale) throws ParseException {
        return new Loaihocky(Integer.parseInt(loaiHKId));
    }
    
}
