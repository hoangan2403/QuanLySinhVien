/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.controllers;

import com.av.pojo.Phonghoc;
import com.av.service.GiaoVuService;
import com.av.service.KhoaDaoTaoService;
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
import org.springframework.web.bind.annotation.ControllerAdvice;
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
@ControllerAdvice
@PropertySource("classpath:configs.properties")
public class PhongHocController {
    
    @Autowired
    private KhoaDaoTaoService khoaDTService;
    
    @Autowired
    private TaiKhoanService tkService;
    
    @Autowired
    private GiaoVuService gvuService;
    
    @Autowired
    private Environment env;

    
    @ModelAttribute
    public void listPhongHoc(Model model,@RequestParam Map<String, String> params){
        model.addAttribute("phonghoc", this.khoaDTService.getPhonghocs(params));
    }
    
    @RequestMapping("/giaovu/phonghoc")
    public String phongHoc(Authentication auth,Model model,@RequestParam Map<String, String> params, HttpSession session){
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));

        // phan trang
        int page = 1;
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.khoaDTService.countPhongHoc();
        
        if (params.containsKey("pagePH")) {
            page = Integer.parseInt(params.get("pagePH"));
            // Lưu trang hiện tại vào session
            session.setAttribute("currentPage", page);
        } else {
            // Nếu không có tham số trang trong request, thử lấy trang từ session (nếu có)
            Integer currentPageFromSession = (Integer) session.getAttribute("currentPage");
            if (currentPageFromSession != null) {
                page = currentPageFromSession;
            }
        }

        // Lấy danh sách khoa dap tao cho trang hiện tại
        List<Phonghoc> phList = this.khoaDTService.getPhonghocs(params);

        // Tính số trang
        int totalRecords = phList.size();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

        if (page < 1) {
            page = 1;
        }
        if (page > totalPages) {
            page = totalPages;
        }

        // Trích xuất danh sách môn học cho trang hiện tại
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, totalRecords);
        List<Phonghoc> mhsForCurrentPage = phList.subList(start, end);

        model.addAttribute("phong", mhsForCurrentPage);

        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "phonghoc";
    }
    
    @GetMapping("/giaovu/phonghoc/add")
    public String addPH(Model model, Authentication auth){
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));

        model.addAttribute("phonghocc", new Phonghoc());
        return "addphonghoc";
    }
    
    @GetMapping("/giaovu/phonghoc/add/{id}")
    public String update(Authentication auth,Model model, @PathVariable(value = "id") int id){
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));

        model.addAttribute("phonghocc", this.khoaDTService.getPhonghocById(id));
        return "addphonghoc";
    }
    
    @PostMapping("/giaovu/phonghoc/add")
    public String add(@ModelAttribute(value = "phonghocc") Phonghoc ph){
        if(this.khoaDTService.addOrUpdatePhongHoc(ph)){
            return "redirect:/giaovu/phonghoc";
        }
        return "addphonghoc";
    }
}
