/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.controllers;

import com.av.pojo.Khoa;
import com.av.pojo.Khoadaotao;
import com.av.service.GiaoVuService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.av.service.KhoaDaoTaoService;
import com.av.service.TaiKhoanService;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author FPTSHOP
 */
@Controller
@ControllerAdvice
@PropertySource("classpath:configs.properties")
public class KhoaHocController {

    @Autowired
    private KhoaDaoTaoService KhoaHocService;

    @Autowired
    private GiaoVuService gvuService;

    @Autowired
    private TaiKhoanService tkService;

    @Autowired
    private Environment env;

    @ModelAttribute
    public void listKH(Model model, Map<String, String> params) {
        model.addAttribute("khoahoc", this.KhoaHocService.getKhoas(params));
    }

    @RequestMapping("/giaovu/khoahoc")
    public String khoaHoc(Authentication auth, Model model, @RequestParam Map<String, String> params, HttpSession session) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));

        // phan trang
        int page = 1;
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.KhoaHocService.countKhoa();

        if (params.containsKey("pageKH")) {
            page = Integer.parseInt(params.get("pageKH"));
            // Lưu trang hiện tại vào session
            session.setAttribute("currentPage", page);
        } else {
            // Nếu không có tham số trang trong request, thử lấy trang từ session (nếu có)
            Integer currentPageFromSession = (Integer) session.getAttribute("currentPage");
            if (currentPageFromSession != null) {
                page = currentPageFromSession;
            }
        }

        // Lấy danh sách khoa hoc cho trang hiện tại
        List<Khoa> khList = this.KhoaHocService.getKhoas(params);

        // Tính số trang
        int totalRecords = khList.size();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        if (khList.isEmpty()) {
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

        List<Khoa> mhsForCurrentPage = khList.subList(start, end);
        model.addAttribute("khoa", mhsForCurrentPage);

        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "khoahoc";
    }

    @GetMapping("/giaovu/khoahoc/add")
    public String addKH(Model model, Authentication auth) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));

        model.addAttribute("khoahocc", new Khoa());
        return "addkhoahoc";
    }

    @GetMapping("/giaovu/khoahoc/add/{id}")
    public String update(Model model, @PathVariable(value = "id") int id, Authentication auth) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));

        model.addAttribute("khoahocc", this.KhoaHocService.getKhoaById(id));
        return "addkhoahoc";
    }

    @PostMapping("/giaovu/khoahoc/add")
    public String add(@ModelAttribute(value = "khoahocc") Khoa k) {
        if (this.KhoaHocService.addOrUpdateKhoa(k) == true) {
            return "redirect:/giaovu/khoahoc";
        }
        return "addkhoahoc";
    }
}
