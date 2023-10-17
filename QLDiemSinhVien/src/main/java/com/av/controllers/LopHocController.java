/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.controllers;

import com.av.pojo.Lophoc;
import com.av.service.GiaoVuService;
import com.av.service.SinhVienService;
import com.av.service.TaiKhoanService;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import com.av.service.LopHocService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
@ControllerAdvice
@PropertySource("classpath:configs.properties")
public class LopHocController {

    @Autowired
    private LopHocService daoTaoService;

    @Autowired
    private GiaoVuService gvuService;

    @Autowired
    private TaiKhoanService tkService;

    @Autowired
    private SinhVienService svService;

    @Autowired
    private Environment Env;

    @Autowired
    private CustomDateEditor customDateEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, customDateEditor);
    }

    @ModelAttribute
    public void lophoc(Model model,@RequestParam Map<String, String> params) {
        model.addAttribute("listLopHoc", this.daoTaoService.listLopHoc(params));

    }

    @GetMapping("/giaovu/lophoc")
    public String dslophoc(Authentication auth, Model model,@RequestParam Map<String, String> params, HttpSession session) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));

        // phan trang
        int page = 1;
        int pageSize = Integer.parseInt(this.Env.getProperty("PAGE_SIZE"));
        long count = this.daoTaoService.countLopHoc();

        if (params.containsKey("pageLH")) {
            page = Integer.parseInt(params.get("pageLH"));
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
        List<Lophoc> lophocList = this.daoTaoService.listLopHoc(params);

        // Tính số trang
        int totalRecords = lophocList.size();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        if (lophocList.isEmpty()) {
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

        List<Lophoc> mhsForCurrentPage = lophocList.subList(start, end);
        model.addAttribute("lophocc", mhsForCurrentPage);
        
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));

        return "lophoc";

    }

    @GetMapping("/giaovu/lophoc/{id}")
    public String lophoc(Authentication auth, Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));
        model.addAttribute("listSinhVien", this.svService.getSinhVienByIdLop(id));
        model.addAttribute("lop", this.daoTaoService.getLopById(id));
        return "listsinhvienbyidlop";

    }

    @GetMapping("/giaovu/lophoc/add")
    public String addLopHoc(Authentication auth, Model model) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));
        model.addAttribute("lophoc", new Lophoc());
        return "addlophoc";
    }

    @GetMapping("/giaovu/lophoc/add/{id}")
    public String updateLH(Authentication auth, Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));
        model.addAttribute("lophoc", this.daoTaoService.getLopById(id));
        return "addlophoc";
    }

    @PostMapping("/giaovu/lophoc/add")
    public String add(Model model, @ModelAttribute(value = "lophoc") Lophoc lh) {
        String errMsg = "";
        if (this.daoTaoService.addOrUpdateLopHoc(lh) == true) {
            return "redirect:/giaovu/lophoc";
        } else {
            errMsg = "Tên lớp này đã có. Vui lòng nhập tên khác!!!";
        }
        model.addAttribute("errMsg", errMsg);
        return "addlophoc";
    }
}
