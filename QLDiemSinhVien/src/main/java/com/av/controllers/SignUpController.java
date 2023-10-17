/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.controllers;

import com.av.pojo.Sinhvien;
import com.av.pojo.Taikhoan;
import com.av.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author FPTSHOP
 */
@Controller
public class SignUpController {

    @Autowired
    private TaiKhoanService taikhoanService;

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("account", new Taikhoan());

        return "signup";
    }

    @PostMapping("/signup")
    public String add(Model model, @ModelAttribute(value = "account") Taikhoan t) {
        String errMsg = "";
        if (t.getMatKhau().isEmpty() || t.getXacNhanMk().isEmpty() || t.getTenTaiKhoan().isEmpty()) {
            errMsg = "Vui lòng điền đầy đủ thong tin !!!";
        } else if (t.getMatKhau().equals(t.getXacNhanMk())) {
            if (this.taikhoanService.addAcount(t) == true) {
                return "redirect:/";
            } else {
                errMsg = "Đã có lỗi!";
            }

        } else {
            errMsg = "Mật khẩu không trùng khớp !!!";
        }

        model.addAttribute("errMsg", errMsg);
        return "signup";
    }

}
