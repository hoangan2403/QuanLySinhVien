/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.controllers;

import com.av.pojo.Hedaotao;
import com.av.service.GiaoVuService;
import com.av.service.HeDaoTaoService;
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
public class HeDaoTaoController {

    @Autowired
    private HeDaoTaoService hdtService;

    @Autowired
    private GiaoVuService gvuService;

    @Autowired
    private TaiKhoanService tkService;

    @Autowired
    private Environment env;

    @ModelAttribute
    public void listHDT(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("hedaotao", this.hdtService.getHedaotaos(params));
    }

    @RequestMapping("/giaovu/hedaotao")
    public String heDaoTao(Authentication auth, Model model, @RequestParam Map<String, String> params, HttpSession session) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));

        // phan trang
        int page = 1;
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.hdtService.countHeDaoTao();

        if (params.containsKey("pageHDT")) {
            page = Integer.parseInt(params.get("pageHDT"));
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
        List<Hedaotao> hdtList = this.hdtService.getHedaotaos(params);

        // Tính số trang
        int totalRecords = hdtList.size();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        if (hdtList.isEmpty()) {
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

        List<Hedaotao> mhsForCurrentPage = hdtList.subList(start, end);
        model.addAttribute("hedt", mhsForCurrentPage);

        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "hedaotao";
    }

    @GetMapping("/giaovu/hedaotao/add")
    public String addHDT(Model model, Authentication auth) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));
        model.addAttribute("hedaotaoo", new Hedaotao());
        return "addhedaotao";
    }

    @GetMapping("/giaovu/hedaotao/add/{id}")
    public String update(Model model, @PathVariable(value = "id") int id, Authentication auth) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));
        model.addAttribute("hedaotaoo", this.hdtService.getHedaotaoById(id));
        return "addhedaotao";
    }

    @PostMapping("/giaovu/hedaotao/add")
    public String add(@ModelAttribute(value = "hedaotaoo") Hedaotao hdt) {
        if (this.hdtService.addOrUpdateHeDT(hdt) == true) {
            return "redirect:/giaovu/hedaotao";
        }
        return "addhedaotao";
    }

}
