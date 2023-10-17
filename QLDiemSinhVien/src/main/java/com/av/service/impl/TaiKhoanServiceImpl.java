/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.service.impl;

import com.av.pojo.Giangvien;
import com.av.pojo.Loaitaikhoan;
import com.av.pojo.Sinhvien;
import com.av.pojo.Taikhoan;
import com.av.repository.GiangvienRepository;
import com.av.repository.TaiKhoanRepository;
import com.av.service.TaiKhoanService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Service("userDetailsService")
public class TaiKhoanServiceImpl implements TaiKhoanService {

    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private TaiKhoanRepository taikhoanRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Taikhoan updateImg(Map<String, String> params, MultipartFile avatar) {
        Taikhoan tk = this.getTaiKhoan(Integer.parseInt(params.get("idTaiKhoan")));

        if (!avatar.isEmpty()) {
            try {
                Map r = this.cloudinary.uploader().upload(avatar.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                tk.setImage(r.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(TaiKhoanService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Taikhoan user = this.taikhoanRepository.updateImg(tk);
        return user;
    }

    @Override
    public Taikhoan getTaiKhoan(int idTaiKhoan) {
        return taikhoanRepository.getTaiKhoan(idTaiKhoan);
    }

    @Override
    public boolean addAcount(Taikhoan t) {
        String pass = t.getMatKhau();
        t.setMatKhau(this.passwordEncoder.encode(pass));
        t.setChucVu(this.getChucVu(3));

        return taikhoanRepository.addAcount(t);
    }

    @Override
    public boolean addAcountGV(Taikhoan t) {
        String pass = t.getMatKhau();
        t.setMatKhau(this.passwordEncoder.encode(pass));
        t.setChucVu(this.getChucVu(2));
        return taikhoanRepository.addAcountGV(t);
    }

    @Override
    public List<Taikhoan> getTaiKhoan(Map<String, String> params) {
        return this.taikhoanRepository.getTaiKhoan(params);
    }

    @Override
    public Loaitaikhoan getChucVu(int id) {
        return this.taikhoanRepository.getChucVu(id);
    }

    @Override
    public Taikhoan getUserByUsername(String username) {
        return this.taikhoanRepository.getUserByUsername(username);
    }

    // Dang Nhap
    @Override
    public UserDetails loadUserByUsername(String tenTK) throws UsernameNotFoundException {
        Taikhoan taikhoans = this.getUserByUsername(tenTK);
        if (taikhoans == null) {
            throw new UsernameNotFoundException("Tài khoản không tồn tại!!!");
        }

        Set<GrantedAuthority> auth = new HashSet<>();
        auth.add(new SimpleGrantedAuthority(taikhoans.getChucVu().getTenloaitaikhoan()));
        return new org.springframework.security.core.userdetails.User(taikhoans.getTenTaiKhoan(), taikhoans.getMatKhau(), auth);
    }

    @Override
    public boolean authUser(String username, String password) {
        return this.taikhoanRepository.authUser(username, password);
    }

    @Override
    public boolean addUser(Map<String, String> params) {
        Taikhoan u = new Taikhoan();
        String maXN = params.get("maXacNhan");
        u.setTenTaiKhoan(params.get("tenTaiKhoan"));
        u.setMatKhau(this.passwordEncoder.encode(params.get("matKhau")));
        u.setChucVu(this.taikhoanRepository.getChucVu(3));
        u.setMaXacNhan(Integer.parseInt(maXN));
        
        return this.taikhoanRepository.addUser(u);
    }

    @Override
    public UserDetails getLoggedInUserDetails(Authentication authentication) {
        // Trả về thông tin UserDetails của người dùng đã đăng nhập thành công
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails;
    }

    @Override
    public int GetIdTaiKhoan(UserDetails userDetails) {
        Taikhoan taikhoans = this.getUserByUsername(userDetails.getUsername());

        return taikhoans.getIdTaiKhoan();
    }

    @Override
    public Taikhoan thayDoiMatKhau(Map<String, String> params) {
        Taikhoan tk = this.taikhoanRepository.getUserByUsername(params.get("tenTaiKhoan").toString());
        tk.setMatKhau(this.passwordEncoder.encode(params.get("matKhauMoi")));
        this.taikhoanRepository.thayDoiMatKhau(tk);
        return tk;
    }

    @Override
    public Taikhoan thayDoiMatKhauAD(Taikhoan user) {
        Taikhoan a = this.getTaiKhoan(user.getIdTaiKhoan());
        a.setMatKhau(this.passwordEncoder.encode(user.getXacNhanMk()));
        this.taikhoanRepository.thayDoiMatKhau(a);
        return user;
    }

    @Override

    public boolean sendCode(String email) {
        return this.taikhoanRepository.sendCode(email);
    }


    public List<Loaitaikhoan> getLoaitaikhoans(Map<String, String> params) {
        return this.taikhoanRepository.getLoaitaikhoans(params);
    }

    @Override
    public Loaitaikhoan getLoaiTaiKhoanById(int id) {
        return this.taikhoanRepository.getLoaiTaiKhoanById(id);
    }

    @Override
    public boolean addOrUpdateLoaiTK(Loaitaikhoan ltk) {
        return this.taikhoanRepository.addOrUpdateLoaiTK(ltk);
    }

    @Override
    public long countTaiKhoan() {
        return this.taikhoanRepository.countTaiKhoan();
    }

    @Override
    public long countLoaiTK() {
        return this.taikhoanRepository.countLoaiTK();
    }
    

}
