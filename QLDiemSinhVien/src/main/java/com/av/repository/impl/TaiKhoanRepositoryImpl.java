/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.repository.impl;

import com.av.pojo.Giangvien;
import com.av.pojo.Loaitaikhoan;
import com.av.pojo.Sinhvien;
import com.av.pojo.Taikhoan;
import com.av.repository.GiangvienRepository;
import com.av.repository.SinhVienRepository;
import com.av.repository.TaiKhoanRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpSession;
import static jdk.internal.joptsimple.internal.Messages.message;
import org.eclipse.persistence.annotations.Array;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class TaiKhoanRepositoryImpl implements TaiKhoanRepository {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Autowired
    private GiangvienRepository gvRepo;

    @Autowired
    private SinhVienRepository svRepo;
    
    @Autowired
    private Environment Env;
    
    @Override
    public Taikhoan updateImg(Taikhoan s) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.update(s);
            return s;

        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // Sua
    @Override
    public Taikhoan getTaiKhoan(int idTaiKhoan) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Taikhoan> q = b.createQuery(Taikhoan.class);

        Root<Taikhoan> root = q.from(Taikhoan.class);
        q.select(root);
        q.where(b.equal(root.get("idTaiKhoan"), idTaiKhoan));
        Query query = session.createQuery(q);
        try {
            return (Taikhoan) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {

            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addAcount(Taikhoan t) {
        if (t.getXacNhanMk() == null || t.getTenTaiKhoan() == null || t.getMatKhau() == null) {
            return false;
        }
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Sinhvien> q = b.createQuery(Sinhvien.class);
        Root<Sinhvien> root = q.from(Sinhvien.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(root.get("email"), t.getTenTaiKhoan()));
        predicates.add(b.equal(root.get("maXacNhan"), t.getMaXacNhan()));
        q.select(root)
                .where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(q);
        try {
            Sinhvien p = (Sinhvien) query.getSingleResult();
            if (p.getIdTaiKhoan() == null && p.getEmail() != null) {
                s.save(t);
                p.setIdTaiKhoan(t);
                s.update(p);
                return true;
            }
        } catch (NoResultException ex) {
            // Không tìm thấy kết quả
            return false;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    // Sửa gửi mail cho giảng viên
    @Override
    public boolean addAcountGV(Taikhoan t) {
        SimpleMailMessage message = new SimpleMailMessage();
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();

        // Kiểm tra xem tên tài khoản có bị trùng không
        CriteriaQuery<Long> countQuery = b.createQuery(Long.class);
        Root<Taikhoan> countRoot = countQuery.from(Taikhoan.class);
        countQuery.select(b.count(countRoot)).where(b.equal(countRoot.get("tenTaiKhoan"), t.getTenTaiKhoan()));
        long duplicateCount = s.createQuery(countQuery).uniqueResult();

        //Lưu id tài khoản vào khóa ngoại của giảng viên 
        CriteriaQuery<Giangvien> q = b.createQuery(Giangvien.class);
        Root<Giangvien> root = q.from(Giangvien.class);
        q.select(root).where(b.equal(root.get("idGiangVien"), t.getGiangvien1().getIdGiangVien()));
        Query query = s.createQuery(q);
        Giangvien gv = (Giangvien) query.getSingleResult();

        try {
            if (duplicateCount == 0) {
                if (t.getIdTaiKhoan() == null && gv != null) {
                    s.save(t);
                    gv.setIdTaiKhoan(t);
                    message.setTo(gv.getEmail().toString());
                    message.setSubject("Thong bao tai khoan");
                    message.setText("Ten tai khoan: " + gv.getIdTaiKhoan().getTenTaiKhoan() + "\nMat khau: " + gv.getIdTaiKhoan().getXacNhanMk());
                    emailSender.send(message);
                }
                return true;
            } else {
                return false;
            }
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            return false;
        }

    }

    @Override
    public List<Taikhoan> getTaiKhoan(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Taikhoan> q = b.createQuery(Taikhoan.class);
        Root root = q.from(Taikhoan.class);
        q.select(root);
        List<Predicate> predicates = new ArrayList<>();
        String ten = params.get("tenTK");
        if (ten != null && !ten.isEmpty()) {
            predicates.add(b.like(root.get("tenTaiKhoan"), String.format("%%%s%%", ten)));
        }
        q.where(predicates.toArray(Predicate[]::new));

        Query query = s.createQuery(q);

        if (params != null) {
            String p = params.get("pageTK");
            if (p != null && !p.isEmpty()) {
                int page = Integer.parseInt(p);
                int pageSize = Integer.parseInt(this.Env.getProperty("PAGE_SIZE"));
                query.setMaxResults(pageSize);
                query.setFirstResult((page - 1) * pageSize);
            }
        }
        return query.getResultList();
    }

    @Override
    public Loaitaikhoan getChucVu(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Loaitaikhoan where idloaitaikhoan=:id");
        q.setParameter("id", id);
        return (Loaitaikhoan) q.getSingleResult();
    }

    @Override
    public Taikhoan getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Taikhoan Where tenTaiKhoan=:un");
        q.setParameter("un", username);
        return (Taikhoan) q.getSingleResult();
    }

    @Override
    public boolean authUser(String username, String password) {
        Taikhoan u = this.getUserByUsername(username);
        return this.passEncoder.matches(password, u.getMatKhau());
    }

    @Override
    public boolean addUser(Taikhoan u) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Sinhvien> q = b.createQuery(Sinhvien.class);
        Root<Sinhvien> root = q.from(Sinhvien.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(root.get("email"), u.getTenTaiKhoan()));
        q.select(root)
                .where(predicates.toArray(Predicate[]::new));

        Query query = s.createQuery(q);
        try {
            Sinhvien p = (Sinhvien) query.getSingleResult();

            if (p.getIdTaiKhoan() == null && p.getEmail() != null && p.getMaXacNhan() == u.getMaXacNhan()) {
                s.save(u);
                p.setIdTaiKhoan(u);
                s.update(p);
                return true;
            }

            return false;
        } catch (NoResultException ex) {
            // Không tìm thấy kết quả
            return false;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @Override
    public boolean kiemTraTaiKhoan(Taikhoan user) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Sinhvien> q = b.createQuery(Sinhvien.class);
        Root<Sinhvien> root = q.from(Sinhvien.class);
        q.select(root)
                .where(b.equal(root.get("email"), user.getTenTaiKhoan()));
        Query query = s.createQuery(q);
        List<Sinhvien> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return false; // Truy vấn không trả về kết quả
        }
        try {
            Sinhvien p = (Sinhvien) query.getSingleResult();

            if (p.getIdTaiKhoan() == null && p.getEmail() != null) {
                return true;
            }

        } catch (HibernateException ex) {
            return false;

        }

        return false;
    }

    //Sua
    @Override
    public Taikhoan thayDoiMatKhau(Taikhoan a) {
        Session s = this.factory.getObject().getCurrentSession();
        if (a != null) {
            s.update(a);
        }

        return a;
    }

    // update xoa 
//    @Override
//    public boolean deleteTaiKhoanBySinhVien(int idSinhVien) {
//        Session s = this.factory.getObject().getCurrentSession();
//        Taikhoan tk = this.getTaikhoanBySinhVien(idSinhVien);
//        try {
//            if(tk != null){
//                s.delete(tk);
//            }
//            return true;
//        } catch (HibernateException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
    //update lay
//     @Override
//    public Taikhoan getTaikhoanBySinhVien(int idSinhVien) {
//        Session s = this.factory.getObject().getCurrentSession();
//        Sinhvien sv = this.svRepo.getSinhVienById(idSinhVien);
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<Taikhoan> q = b.createQuery(Taikhoan.class);
//        Root r = q.from(Taikhoan.class);
//        if (sv.getIdTaiKhoan() != null) {
//            q.select(r).where(b.equal(r.get("idTaiKhoan"), sv.getIdTaiKhoan().getIdTaiKhoan()));
//            Query query = s.createQuery(q);
//            return (Taikhoan) query.getSingleResult();
//        }else
//            return null;
//    }
    //update xoa 
    @Override
    public boolean deleteTK(Taikhoan tk) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            if (tk != null) {
                session.delete(tk);
                return true;
            }
            return false;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean sendCode(String email) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Sinhvien> q = b.createQuery(Sinhvien.class);
        Root<Sinhvien> root = q.from(Sinhvien.class);
        q.select(root)
                .where(b.equal(root.get("email"), email));
        Query query = s.createQuery(q);
        try {
            Sinhvien p = (Sinhvien) query.getSingleResult();
            SimpleMailMessage message = new SimpleMailMessage();
//            HttpSession session = request.getSession();

            if (p.getIdTaiKhoan() == null && p.getEmail() != null) {
                Random random = new Random();
                int confirmationCode = 100_000 + random.nextInt(900_000);
                p.setMaXacNhan(confirmationCode);
                message.setTo(email.toString());
                message.setSubject("Ma Xac Nhan");
                message.setText("Ma xac nhan cua ban la: " + confirmationCode);
                emailSender.send(message);
                return true;
            }

            return false;
        } catch (NoResultException ex) {
            // Không tìm thấy kết quả
            return false;
        } catch (HibernateException ex) {
            return false;
        }
    }

    public List<Loaitaikhoan> getLoaitaikhoans(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Loaitaikhoan> q = b.createQuery(Loaitaikhoan.class);
        Root root = q.from(Loaitaikhoan.class);
        q.select(root);
        List<Predicate> predicates = new ArrayList<>();
        String tenSV = params.get("tenLTK");
        if (tenSV != null && !tenSV.isEmpty()) {
            predicates.add(b.like(root.get("tenloaitaikhoan"), String.format("%%%s%%", tenSV)));
        }
        q.where(predicates.toArray(Predicate[]::new));

        Query query = s.createQuery(q);

        if (params != null) {
            String p = params.get("pageLTK");
            if (p != null && !p.isEmpty()) {
                int page = Integer.parseInt(p);
                int pageSize = Integer.parseInt(this.Env.getProperty("PAGE_SIZE"));
                query.setMaxResults(pageSize);
                query.setFirstResult((page - 1) * pageSize);
            }
        }
        return query.getResultList();
    }

    @Override
    public Loaitaikhoan getLoaiTaiKhoanById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Loaitaikhoan> q = b.createQuery(Loaitaikhoan.class);

        Root<Loaitaikhoan> root = q.from(Loaitaikhoan.class);
        q.select(root);
        q.where(b.equal(root.get("idloaitaikhoan"), id));
        Query query = session.createQuery(q);
        try {
            return (Loaitaikhoan) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {

            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addOrUpdateLoaiTK(Loaitaikhoan ltk) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (ltk.getIdloaitaikhoan() != null) {
                s.update(ltk);
            }else{
                s.save(ltk);
            }
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public long countTaiKhoan() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);
        Root r = q.from(Taikhoan.class);
        q.select(b.count(r));
        long countTK = s.createQuery(q).uniqueResult();
        return countTK;
    }

    @Override
    public long countLoaiTK() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);
        Root r = q.from(Loaitaikhoan.class);
        q.select(b.count(r));
        long countLTK = s.createQuery(q).uniqueResult();
        return countLTK;
    }
}
