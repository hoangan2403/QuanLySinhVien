/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.formatter;

import com.av.pojo.Phonghoc;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author FPTSHOP
 */
public class PhongHocFormatter implements Formatter<Phonghoc>{

    @Override
    public String print(Phonghoc ph, Locale locale) {
        return String.valueOf(ph.getIdPhongHoc());

    }

    @Override
    public Phonghoc parse(String phId, Locale locale) throws ParseException {
        return new Phonghoc(Integer.parseInt(phId));
    }
    
}
