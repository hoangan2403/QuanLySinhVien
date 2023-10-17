/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.repository.impl;

import com.av.pojo.Cauhoidiendang;
import com.av.pojo.Diem;
import com.av.pojo.DiemMonHoc;
import com.av.pojo.Lophoc;
import com.av.pojo.MonhocHocky;
import com.av.pojo.Monhocdangky;
import com.av.pojo.Sinhvien;
import com.av.pojo.Taikhoan;
import com.av.pojo.Traloidiendan;
import com.av.repository.DiemRepository;
import com.av.repository.DienDanRepository;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.av.repository.SinhVienRepository;
import com.av.repository.TaiKhoanRepository;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Propagation;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class SinhVienRepositoryImpl implements SinhVienRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private DiemRepository diemRepository;
    @Autowired
    private TaiKhoanRepository tkRepo;
    @Autowired
    private Environment Env;

    @Override
    public Sinhvien getSinhVien(int idTaiKhoan) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Sinhvien> q = b.createQuery(Sinhvien.class);

        Root<Sinhvien> root = q.from(Sinhvien.class);
        q.select(root);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(b.equal(root.get("idTaiKhoan"), idTaiKhoan));
        q.where(predicates.toArray(new Predicate[0]));
        Query query = session.createQuery(q);
        try {
            return (Sinhvien) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {

            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Sinhvien> getSinhviens(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Sinhvien> q = b.createQuery(Sinhvien.class);

        Root<Sinhvien> root = q.from(Sinhvien.class);
        q.select(root);
        List<Predicate> predicates = new ArrayList<>();

        String tenSV = params.get("tensv");
        if (tenSV != null && !tenSV.isEmpty()) {
            predicates.add(b.like(root.get("hoTen"), String.format("%%%s%%", tenSV)));
        }
        q.where(predicates.toArray(Predicate[]::new));

        Query query = s.createQuery(q);

        if (params != null) {
            String p = params.get("page");
            if (p != null && !p.isEmpty()) {
                int page = Integer.parseInt(p);
                int pageSize = Integer.parseInt(this.Env.getProperty("PAGE_SIZE"));
                query.setMaxResults(pageSize);
                query.setFirstResult((page - 1) * pageSize);
            }
        }
        return query.getResultList();
    }
    // di chuyen lay danh sach lop hoc qua class lop hoc

    @Override
    public boolean addOrUpdateSinhVien(Sinhvien sv) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (sv.getIdSinhVien() == null) {
                s.save(sv);
            } else {
                s.update(sv);
            }

            return true;
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public Sinhvien getSinhVienById(int idSinhVien) {
       Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Sinhvien> q = b.createQuery(Sinhvien.class);

        Root<Sinhvien> root = q.from(Sinhvien.class);
        q.select(root);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(b.equal(root.get("idSinhVien"), idSinhVien));
        q.where(predicates.toArray(new Predicate[0]));
        Query query = session.createQuery(q);
        try {
            return (Sinhvien) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {

            ex.printStackTrace();
            return null;
        }

    }

    //xoa sinh vien
    @Override
    public boolean deleteSinhVien(int idSinhVien) {
        Session s = this.factory.getObject().getCurrentSession();
        Sinhvien sv = this.getSinhVienById(idSinhVien);
        this.tkRepo.deleteTK(sv.getIdTaiKhoan());
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<Taikhoan> q = b.createQuery(Taikhoan.class);
//        Root r = q.from(Taikhoan.class);
//        q.select(r).where(b.equal(r.get("idTaiKhoan"), sv.getIdTaiKhoan().getIdTaiKhoan()));
//        Query query = s.createQuery(q);
        try {
//            Taikhoan tk = (Taikhoan) query.getSingleResult();
            s.delete(sv);
//            s.remove(tk);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override

    public List<DiemMonHoc> getSinhvienByMonHoc(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rSinhVien = q.from(Sinhvien.class);
        Root rDiem = q.from(Monhocdangky.class);
        Root rMonHoc = q.from(MonhocHocky.class);
        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String idMonHoc = params.get("monHocId");
            if (idMonHoc != null) {
                predicates.add(b.equal(rMonHoc.get("idMonHocHocKy"), Integer.parseInt(idMonHoc)));
                predicates.add(b.equal(rDiem.get("idMonHoc"), rMonHoc.get("idMonHocHocKy")));
            }

            String tenSinhVien = params.get("tenSinhVien");
            if (tenSinhVien != null) {
                try {
                    int parsedIdSinhVien = Integer.parseInt(tenSinhVien);
                    predicates.add(b.equal(rDiem.get("idSinhVien"), parsedIdSinhVien));
                } catch (NumberFormatException e) {
                    predicates.add(b.equal(rDiem.get("idSinhVien"), rSinhVien.get("idSinhVien")));
                    predicates.add(b.like(rSinhVien.get("hoTen"), String.format("%%%s%%", tenSinhVien)));
                }
            }

            q.multiselect(rDiem);
            q.where(predicates.toArray(new Predicate[0]));
            q.distinct(true);
            Query query = s.createQuery(q);
            List<Monhocdangky> monHocList = query.getResultList();
            List<DiemMonHoc> monHocDiemList = new ArrayList<>();

            for (Monhocdangky monHoc : monHocList) {
                DiemMonHoc monHocDiem = new DiemMonHoc(monHoc);
                // Lấy danh sách điểm cho môn học cụ thể
                List<Diem> diemList = this.diemRepository.getListDiemByIdMonHocDangKy(monHoc.getIdMonHocDangKy());
                // Thêm điểm vào danh sách MonHocDiem
                for (Diem diem : diemList) {
                    monHocDiem.addDiem(diem); // Diem.getDiem() là phương thức lấy giá trị điểm
                }
                // Thêm MonHocDiem vào danh sách chung
                monHocDiemList.add(monHocDiem);
            }
            return monHocDiemList;
        }
        return null;
    }

    // dem sinh vien
    @Override
    public Long countSinhVien() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);

        Root r = q.from(Sinhvien.class);
        q.select(b.count(r));

        long countSV = s.createQuery(q).uniqueResult();

        return countSV;
    }

    //update 26/9 danh sach sinh vien theo ma lop
    @Override
    public List<Sinhvien> getSinhVienByIdLop(int idLop) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Sinhvien> q = b.createQuery(Sinhvien.class);

        Root<Sinhvien> root = q.from(Sinhvien.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(root.get("maLop"), idLop));
        q.where(predicates.toArray(new Predicate[0]));
        Query query = s.createQuery(q);
        try {
            List<Sinhvien> sinhVien = query.getResultList();
            if (sinhVien.isEmpty()) {
                return null;
            } else {
                // Process the non-empty lopHoc list here if needed
                return sinhVien;
            }
        } catch (NoResultException | NonUniqueResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Object getSinhVienByIdAPI(int idSinhVien) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object> q = b.createQuery(Object.class);

        Root<Sinhvien> root = q.from(Sinhvien.class);
        q.multiselect(root.get("hoTen"), root.get("idSinhVien"), root.get("ngaySinh"), 
                root.get("maLop").get("tenLopHoc"), root.get("maLop").get("idNganh").get("tenNganhDaoTao"), root.get("diaChi"), root.get("email"));
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(b.equal(root.get("idSinhVien"), idSinhVien));
        q.where(predicates.toArray(new Predicate[0]));
        Query query = session.createQuery(q);
        try {
            return (Object) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {

            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Long countSinhVienByIdLop(int idLop) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);

        Root r = q.from(Sinhvien.class);
        q.select(b.count(r)).where(b.equal(r.get("maLop").get("idLopHoc"), idLop));

        long countMonHoc = session.createQuery(q).uniqueResult();

        return countMonHoc;
    }

}
