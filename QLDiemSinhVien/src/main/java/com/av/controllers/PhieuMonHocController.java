/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.controllers;

import com.av.pojo.PhieuMonHoc;
import com.av.pojo.Diem;
import com.av.service.DiemService;
import com.av.service.SinhVienService;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author FPTSHOP
 */
@Controller
public class PhieuMonHocController {

    @Autowired
    private DiemService diemService;

    @Autowired
    private SinhVienService svService;

    @GetMapping("/giaovu/sinhvien/addMH")
    public String dangky(Model model) {
        return "dangkymhsv";
    }

    @GetMapping("/giaovu/sinhvien/addMH/{id}")
    public String dangKyMhSv(Model model, @PathVariable(value = "id") int id, HttpSession session) {
//        Map<String, PhieuMonHoc> cart = (Map<String, PhieuMonHoc>) session.getAttribute("cart");
//        if(cart != null)
//            model.addAttribute("carts", cart.values());
        model.addAttribute("sinhvien", this.svService.getSinhVienById(id));
        return "dangkymhsv";
    }

}
