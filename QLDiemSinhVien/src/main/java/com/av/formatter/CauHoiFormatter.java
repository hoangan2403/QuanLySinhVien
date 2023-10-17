/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.formatter;

import com.av.pojo.Cauhoidiendang;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Admin
 */
public class CauHoiFormatter implements Formatter<Cauhoidiendang>{

    @Override
    public String print(Cauhoidiendang cauhoi, Locale locale) {
        return String.valueOf(cauhoi.getIdCauHoiDienDan());
    }

    @Override
    public Cauhoidiendang parse(String cauhoiId, Locale locale) throws ParseException {
       return new Cauhoidiendang(Integer.parseInt(cauhoiId));
    }
    
}
