/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.controllers;

import com.av.pojo.Giangvien;
import com.av.pojo.Taikhoan;
import com.av.service.GiangVienService;
import com.av.service.GiaoVuService;
import com.av.service.MonHocService;
import com.av.service.SinhVienService;
import com.av.service.TaiKhoanService;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author FPTSHOP
 */
@Controller
@PropertySource("classpath:configs.properties")
public class AdminController {

    @Autowired
    private TaiKhoanService tkService;

    @Autowired
    private GiaoVuService gvuService;

    @Autowired
    private Environment env;

    @ModelAttribute
    public void nameGVu(Model model, Authentication auth) {
        model.addAttribute("giaovu", this.gvuService.getGiaovus(this.tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(auth))));
    }

    @RequestMapping("/giaovu")
    public String admin() {
        return "admin";
    }

    @GetMapping("/giaovu/thongtin")
    public String thongtin(Model model) {
        return "thongtintaikhoan";
    }

    @RequestMapping("/giaovu/taikhoan/dangki")
    public String dangky(Model model) {
        model.addAttribute("dangki", new Taikhoan());
        return "dangkytkgv";
    }

    @GetMapping("giaovu/taikhoan")
    public String taikhoan(Model model, @RequestParam Map<String, String> params, HttpSession session) {
        model.addAttribute("taikhoan", this.tkService.getTaiKhoan(params));
        // phan trang
        int page = 1;
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.tkService.countTaiKhoan();

        if (params.containsKey("pageTK")) {
            page = Integer.parseInt(params.get("pageTK"));
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
        List<Taikhoan> taikhoanList = this.tkService.getTaiKhoan(params);

        // Tính số trang
        int totalRecords = taikhoanList.size();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

        if (taikhoanList.isEmpty()) {
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

        List<Taikhoan> mhsForCurrentPage = taikhoanList.subList(start, end);
        model.addAttribute("taikhoann", mhsForCurrentPage);

        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "dstaikhoan";
    }

    @PostMapping("giaovu/taikhoan/dangki")
    public String add(Model model, @ModelAttribute(value = "dangki") @Valid Taikhoan t, BindingResult rs) {
        String errMsg = "";
        if (!rs.hasErrors()) {
            if (t.getMatKhau().equals(t.getXacNhanMk())) {
                if (this.tkService.addAcountGV(t) == true) {
                    return "redirect:/giaovu/taikhoan";
                } else {
                    errMsg = "Đã có lỗi!";
                }
            } else {
                errMsg = "Mật khẩu không Khớp!!!";
            }
        }
        model.addAttribute("errMsg", errMsg);
        return "dangkytkgv";
    }

}
