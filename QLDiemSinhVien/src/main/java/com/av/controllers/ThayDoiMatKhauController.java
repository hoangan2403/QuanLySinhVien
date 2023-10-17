/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.controllers;

import com.av.pojo.Taikhoan;
import com.av.service.SinhVienService;
import com.av.service.TaiKhoanService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Admin
 */
@Controller
public class ThayDoiMatKhauController {

    @Autowired
    private TaiKhoanService tkService;

    //Sua
    @GetMapping("/giaovu/thaydoimatkhau")
    public String thaydoimatkhau(Model model, Authentication authentication) {
        model.addAttribute("taikhoanAD", this.tkService.getTaiKhoan(tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(authentication))));
        model.addAttribute("taiKhoan", new Taikhoan());
        return "thaydoimatkhau";
    }

    @PostMapping("/giaovu/thaydoimatkhau")
    public String doiMatKhau(@ModelAttribute(value = "taiKhoan") Taikhoan tk, Model model) {
        String errMsg = "";
        if (tk.getMatKhau().isEmpty() || tk.getXacNhanMk().isEmpty() || tk.getMkMoi().isEmpty()) {
            errMsg = "Vui lòng điền đầy đủ thong tin !!!";
        } else if (tk.getMkMoi().equals(tk.getXacNhanMk())) {
            this.tkService.thayDoiMatKhauAD(tk);
            errMsg = "Thay Đổi Thành Công";
           
            
        } else {
            errMsg = "Mật khẩu không trùng khớp !!!";
        }
        model.addAttribute("errMsg", errMsg);
        return "thaydoimatkhau";
    }

}
