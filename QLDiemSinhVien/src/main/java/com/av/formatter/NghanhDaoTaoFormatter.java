/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.formatter;

import com.av.pojo.Cauhoidiendang;
import com.av.pojo.Nganhdaotao;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author FPTSHOP
 */
public class NghanhDaoTaoFormatter implements Formatter<Nganhdaotao>{

    @Override
    public String print(Nganhdaotao ndt, Locale locale) {
        return String.valueOf(ndt.getIdNganhDaoTao());    
    }

    @Override
    public Nganhdaotao parse(String ndtId, Locale locale) throws ParseException {
        return new Nganhdaotao(Integer.parseInt(ndtId));    
    }
    
}
