/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.formatter;

import com.av.pojo.Lophoc;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author FPTSHOP
 */
public class LopHocFormatter implements Formatter<Lophoc>{

    @Override
    public String print(Lophoc lh, Locale locale) {
        return String.valueOf(lh.getIdLopHoc());
    }

    @Override
    public Lophoc parse(String lhID, Locale locale) throws ParseException {
        return new Lophoc(Integer.parseInt(lhID));
    }
    
}
