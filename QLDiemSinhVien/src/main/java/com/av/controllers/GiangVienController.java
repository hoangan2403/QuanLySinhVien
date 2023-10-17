/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.controllers;

import com.av.pojo.Giangvien;
import com.av.pojo.Monhoc;
import com.av.service.GiangVienService;
import com.av.service.GiaoVuService;
import com.av.service.TaiKhoanService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
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
@ControllerAdvice
@PropertySource("classpath:configs.properties")
public class GiangVienController {

    @Autowired
    private GiangVienService gvService;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TaiKhoanService tkService;

    @Autowired
    private GiaoVuService gvuService;

    @Autowired
    private CustomDateEditor customDateEditor;

    @Autowired
    private Environment env;

    @ModelAttribute
    public void listGV(Model model, Authentication auth, @RequestParam Map<String, String> params) {
        model.addAttribute("giangvien", this.gvService.getGiangviens(params));
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, customDateEditor);
    }

    @GetMapping("/giaovu/giangvien")
    public String giangvien(Model model, Authentication auth, @RequestParam Map<String, String> params, HttpSession session) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));

        // phan trang
        int page = 1;
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.gvService.countGiangVien();

        if (params.containsKey("pageGV")) {
            page = Integer.parseInt(params.get("pageGV"));
            // Lưu trang hiện tại vào session
            session.setAttribute("currentPage", page);
        } else {
            // Nếu không có tham số trang trong request, thử lấy trang từ session (nếu có)
            Integer currentPageFromSession = (Integer) session.getAttribute("currentPage");
            if (currentPageFromSession != null) {
                page = currentPageFromSession;
            }
        }

        // Lấy danh sách giảng viên cho trang hiện tại
        List<Giangvien> giangvienList = this.gvService.getGiangviens(params);

        // Tính số trang
        int totalRecords = giangvienList.size();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

        if (giangvienList.isEmpty()) {
            page = 1;
        } else {
            if (page < 1) {
                page = 1;
            }
            if (page > totalPages) {
                page = totalPages;
            }
        }

        // Trích xuất danh sách môn học cho trang hiện tại
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, totalRecords);

        List<Giangvien> mhsForCurrentPage = giangvienList.subList(start, end);
        model.addAttribute("giangvienn", mhsForCurrentPage);

        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "dsgiangvien";
    }

    @GetMapping("/giaovu/giangvien/add")
    public String addGiangVien(Model model, Authentication auth) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));
        model.addAttribute("giangvienn", new Giangvien());
        return "addgv";
    }

    @GetMapping("/giaovu/giangvien/add/{id}")
    public String updateGV(Model model, @PathVariable(value = "id") int id, Authentication auth) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));
        model.addAttribute("giangvienn", this.gvService.getGiangVienById(id));
        return "addgv";
    }

    @PostMapping("/giaovu/giangvien/add")
    public String add(@ModelAttribute(value = "giangvienn") Giangvien gv) {
        if (gvService.addOrUpdateGiangVien(gv) == true) {
            return "redirect:/giaovu/giangvien";
        }
        return "addgv";
    }

}
