/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.controllers;

import com.av.pojo.Sinhvien;
import com.av.service.GiaoVuService;
import com.av.service.MonHocService;

import com.av.service.SinhVienService;
import com.av.service.TaiKhoanService;

import java.util.Date;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.av.service.LopHocService;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author FPTSHOP
 */
@Controller
@PropertySource("classpath:configs.properties")
public class SinhVienController {

    @Autowired
    private SinhVienService svService;
    @Autowired
    private MonHocService mhService;
    @Autowired
    private LopHocService dtService;
    @Autowired
    private GiaoVuService gvuService;

    @Autowired
    private TaiKhoanService tkService;

    @Autowired
    private CustomDateEditor customDateEditor;

    @Autowired
    private Environment env;

    @ModelAttribute
    public void lopHoc(Model model, Authentication auth, Map<String, String> params) {
        model.addAttribute("lophoc", dtService.listLopHoc(params));
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));
        model.addAttribute("sinhvien", this.svService.getSinhviens(params));

    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, customDateEditor);
    }

    @RequestMapping("/giaovu/sinhvien")
    public String list(Model model, @RequestParam Map<String, String> params, HttpSession session) {

        // phan trang
        int page = 1;
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.svService.countSinhVien();

        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
            // Lưu trang hiện tại vào session
            session.setAttribute("currentPage", page);
        } else {
            // Nếu không có tham số trang trong request, thử lấy trang từ session (nếu có)
            Integer currentPageFromSession = (Integer) session.getAttribute("currentPage");
            if (currentPageFromSession != null) {
                page = currentPageFromSession;
            }
        }

        // Lấy danh sách sinh viên cho trang hiện tại
        List<Sinhvien> sinhvienList = this.svService.getSinhviens(params);

        // Tính số trang
        int totalRecords = sinhvienList.size();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        if (sinhvienList.isEmpty()) {
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

        List<Sinhvien> mhsForCurrentPage = sinhvienList.subList(start, end);
        model.addAttribute("sinhviens", mhsForCurrentPage);

        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "dssinhvien";
    }

    @GetMapping("/giaovu/sinhvien/add")
    public String addsinhVien(Model model) {
        model.addAttribute("sinhvienn", new Sinhvien());
        return "add";
    }

    @GetMapping("/giaovu/sinhvien/add/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("sinhvienn", svService.getSinhVienById(id));
        return "add";
    }

    @PostMapping("/giaovu/sinhvien/add")
    public String add(@ModelAttribute(value = "sinhvienn") @Valid Sinhvien sv, BindingResult rs) {
        if (!rs.hasErrors()) {
            if (this.svService.addOrUpdateSinhVien(sv) == true) {
                return "redirect:/giaovu/sinhvien";
            }
        }

        return "add";
    }

    @GetMapping("/giaovu/sinhvien/{id}")
    public String chiTietSinhVien(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("sinhvienn", svService.getSinhVienById(id));
        model.addAttribute("monHocDaHoc", this.mhService.getMonHocByIdSinhVien(id));
        model.addAttribute("monHocDangHoc", this.mhService.getMonHocByIdSinhVienDangHoc(id));
        return "chitietsinhvien";
    }
}
