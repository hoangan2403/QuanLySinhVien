/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.controllers;


import com.av.service.NghanhDaoTaoService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author FPTSHOP
 */
@Controller
@ControllerAdvice
public class NghanhDaoTaoController {
    
    @Autowired
    private NghanhDaoTaoService ndtService;
    
    @ModelAttribute
    public void listNDT(Model model, Map<String, String> params){
        model.addAttribute("nghanhdaotao", this.ndtService.getNganhdaotaos(params));
    }
    
   
}
