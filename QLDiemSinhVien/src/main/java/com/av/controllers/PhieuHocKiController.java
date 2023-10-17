/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.controllers;

import com.av.pojo.Hocky;
import com.av.pojo.MonhocHocky;
import com.av.service.GiangVienService;
import com.av.service.GiaoVuService;
import com.av.service.HocKyService;
import com.av.service.PhieuMonHocService;
import com.av.service.TaiKhoanService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
public class PhieuHocKiController {

    @Autowired
    private HocKyService hkService;

    @Autowired
    private TaiKhoanService tkService;
    
    @Autowired GiaoVuService gvuService;

    @Autowired
    private PhieuMonHocService pmhService;

    @Autowired
    private CustomDateEditor customDateEditor;

    @RequestMapping("/giaovu/hocki/addMH")
    public String dangki(Authentication auth, Model model) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));

        return "phieuhocki";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, customDateEditor);
    }

    @GetMapping("/giaovu/hocki/addMH/{id}")
    public String add(Authentication auth,Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));
        model.addAttribute("hocki", this.hkService.getHockyById(id));
        model.addAttribute("phieumonhoc", new MonhocHocky());
        model.addAttribute("mondachon", this.pmhService.getMonhocByHockys(id));
        return "phieuhocki";
    }

    @PostMapping("/giaovu/hocki/addMH/{id}")
    public String addMH(Model model, @PathVariable(value = "id") int id, @ModelAttribute(value = "phieumonhoc") MonhocHocky mh) {
        model.addAttribute("hocki", this.hkService.getHockyById(id));
        if (this.pmhService.addPhieuMHHK(mh) == true) {
            return "redirect:/giaovu/hocki/addMH/" + id;
        }
        return "phieuhocki";
    }

    @GetMapping("/giaovu/hocki/chitiethocki/{id}")
    public String chiTietHk(Authentication auth,Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));
        model.addAttribute("mondachon", this.pmhService.getMonhocByHockys(id));
//        model.addAttribute("monhoc", this.pmhService.getMonhocHocky(id));
        return "listmonhochk";
    }

    @GetMapping("/giaovu/hocki/chitiethocki/update/{id}")
    public String updateCTHK(Authentication auth,Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));
        model.addAttribute("phieumonhoc", this.pmhService.getMonhocHocky(id));
        return "updateMHHK";
    }

    @PostMapping("/giaovu/hocki/chitiethocki/update")
    public String update(Model model,@ModelAttribute(value = "phieumonhoc") MonhocHocky mh) {
//        model.addAttribute("mondachon", this.pmhService.getMonhocByHockys(id));
        if (this.pmhService.updatePhieuMHHK(mh) == true) {
            return "redirect:/giaovu/hocki/chitiethocki/" + mh.getIdHocky().getIdHocKy();
        }
        return "updateMHHK";
    }
    
}
