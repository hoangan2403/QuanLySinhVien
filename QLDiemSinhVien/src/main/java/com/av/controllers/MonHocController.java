/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.controllers;

import com.av.pojo.Monhoc;
import com.av.service.GiaoVuService;
import com.av.service.MonHocService;
import com.av.service.TaiKhoanService;
import java.util.ArrayList;
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
public class MonHocController {

    @Autowired
    private MonHocService mhService;

    @Autowired
    private TaiKhoanService tkService;

    @Autowired
    private GiaoVuService gvuService;

    @Autowired
    private Environment env;

    @ModelAttribute
    public void listMH(Model model, Authentication auth, @RequestParam Map<String, String> params) {
        model.addAttribute("monhoc", this.mhService.getMonHocs(params));

    }

    @RequestMapping("/giaovu/monhoc")
    public String monhoc(Model model, Authentication auth, @RequestParam Map<String, String> params, HttpSession session) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));
        // phan trang
        int page = 1;
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.mhService.countMonHoc();

        if (params.containsKey("pageMH")) {
            page = Integer.parseInt(params.get("pageMH"));
            // Lưu trang hiện tại vào session
            session.setAttribute("currentPage", page);
        } else {
            // Nếu không có tham số trang trong request, thử lấy trang từ session (nếu có)
            Integer currentPageFromSession = (Integer) session.getAttribute("currentPage");
            if (currentPageFromSession != null) {
                page = currentPageFromSession;
            }
        }

        // Lấy danh sách môn học cho trang hiện tại
        List<Monhoc> monhocList = this.mhService.getMonHocs(params);

        // Tính số trang
        int totalRecords = monhocList.size();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        if (monhocList.isEmpty()) {
            page = 1;
        } else {
            if (page < 1) {
                page = totalPages;
            }
            if (page > totalPages) {
                page = totalPages;
            }
        }

        // Trích xuất danh sách môn học cho trang hiện tại
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, totalRecords);
        if (start < totalRecords) {
            // Nếu danh sách không rỗng, thực hiện subList
            List<Monhoc> mhsForCurrentPage = monhocList.subList(start, end);
            model.addAttribute("monhocc", mhsForCurrentPage);
        } else {
            // Nếu danh sách rỗng (không có kết quả), hiển thị trang 1
            List<Monhoc> mhsForCurrentPage = monhocList.subList(0, Math.min(pageSize, totalRecords));
            model.addAttribute("monhocc", mhsForCurrentPage);
        }
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "dsmonhoc";
    }

    @GetMapping("/giaovu/monhoc/add")
    public String addMonHoc(Model model, Authentication auth) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));
        model.addAttribute("monhocc", new Monhoc());
        return "addmh";
    }

    @GetMapping("/giaovu/monhoc/add/{id}")
    public String updateMH(Model model, @PathVariable(value = "id") int id, Authentication auth) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));
        model.addAttribute("monhocc", this.mhService.getMonHocById(id));
        return "addmh";
    }

    @PostMapping("/giaovu/monhoc/add")
    public String add(@ModelAttribute(value = "monhocc") Monhoc mh) {
        if (this.mhService.addOrUpdateMonHoc(mh) == true) {
            return "redirect:/giaovu/monhoc";
        }
        return "addmh";
    }
}
