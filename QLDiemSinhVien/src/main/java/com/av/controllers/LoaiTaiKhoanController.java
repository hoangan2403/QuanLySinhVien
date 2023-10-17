/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.controllers;

import com.av.pojo.Loaitaikhoan;
import com.av.service.GiaoVuService;
import com.av.service.TaiKhoanService;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author FPTSHOP
 */
@Controller
@PropertySource("classpath:configs.properties")
public class LoaiTaiKhoanController {

    @Autowired
    private TaiKhoanService tkService;

    @Autowired
    private GiaoVuService gvuService;

    @Autowired
    private Environment env;

    @ModelAttribute
    public void generalClass(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("loaitaikhoan", this.tkService.getLoaitaikhoans(params));
    }

    @RequestMapping("/giaovu/loaitaikhoan")
    public String loaiTaiKhoan(Model model, @RequestParam  Map<String, String> params, Authentication auth, HttpSession session) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));
        model.addAttribute("loaitaikhoann", new Loaitaikhoan());
        return "loaitaikhoan";
    }

    @GetMapping("/giaovu/loaitaikhoan/{id}")
    public String update(Model model, @PathVariable(value = "id") int id, Authentication auth) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));
        model.addAttribute("loaitaikhoann", this.tkService.getLoaiTaiKhoanById(id));
        return "loaitaikhoan";
    }

    @PostMapping("/giaovu/loaitaikhoan")
    public String add(@ModelAttribute(value = "loaitaikhoann") Loaitaikhoan ltk) {
        if (this.tkService.addOrUpdateLoaiTK(ltk) == true) {
            return "redirect:/giaovu/loaitaikhoan";
        }
        return "loaitaikhoan";
    }
}
