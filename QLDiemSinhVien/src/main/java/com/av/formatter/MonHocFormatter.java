/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.formatter;

import com.av.pojo.Monhoc;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author FPTSHOP
 */
public class MonHocFormatter implements Formatter<Monhoc>{

    @Override
    public String print(Monhoc mh, Locale locale) {
        return String.valueOf(mh.getIdMonHoc());
    }

    @Override
    public Monhoc parse(String text, Locale locale) throws ParseException {
        return new  Monhoc(Integer.parseInt(text));
    }
    
}
