/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.controllers;

import com.av.pojo.Hocky;
import com.av.service.GiaoVuService;
import com.av.service.HocKyService;
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
public class HocKyController {

    @Autowired
    private HocKyService hkService;

    @Autowired
    private GiaoVuService gvuService;

    @Autowired
    private TaiKhoanService tkService;

    @Autowired
    private Environment Env;

    @ModelAttribute
    public void listHK(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("hocky", this.hkService.getHockys(params));
        model.addAttribute("loaihocki", this.hkService.getLoaihockys(params));
    }

    @RequestMapping("/giaovu/hocky")
    public String hocKy(Authentication auth, Model model, @RequestParam Map<String, String> params, HttpSession session) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));

        // phan trang
        int page = 1;
        int pageSize = Integer.parseInt(this.Env.getProperty("PAGE_SIZE"));
        long count = this.hkService.countHocKy();
        if (params.containsKey("pageHK")) {
            page = Integer.parseInt(params.get("pageHK"));
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
        List<Hocky> hockyList = this.hkService.getHockys(params);

        // Tính số trang
        int totalRecords = hockyList.size();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        if (hockyList.isEmpty()) {
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

        List<Hocky> mhsForCurrentPage = hockyList.subList(start, end);
        model.addAttribute("hocki", mhsForCurrentPage);

        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "hocky";
    }

    @GetMapping("/giaovu/hocky/add")
    public String addHocKy(Model model, Authentication auth) {
        model.addAttribute("hocki", new Hocky());
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));
        return "addhocki";
    }

    @GetMapping("/giaovu/hocky/add/{idHK}")
    public String updateHK(Authentication auth, Model model, @PathVariable(value = "idHK") int id) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));
        model.addAttribute("hocki", this.hkService.getHockyById(id));
        return "addhocki";
    }

    @PostMapping("/giaovu/hocky/add")
    public String add(@ModelAttribute(value = "hocki") Hocky hk) {
        if (this.hkService.addOrUpdateHocKy(hk) == true) {
            return "redirect:/giaovu/hocky";
        }
        return "addhocki";
    }
}
