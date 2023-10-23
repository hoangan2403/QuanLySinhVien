/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.repository.impl;

import com.av.pojo.Diem;
import com.av.pojo.DiemMonHocComparator;
import com.av.pojo.Giangvien;
import com.av.pojo.Monhoc;
import com.av.pojo.MonhocHocky;
import com.av.pojo.Monhocdangky;
import com.av.repository.MonHocRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

/**
 *
 * @author FPTSHOP
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class MonHocRepositoryImpl implements MonHocRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private Environment Env;

    @Override
    public List<Monhoc> getMonHocs(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Monhoc> q = b.createQuery(Monhoc.class);
        Root root = q.from(Monhoc.class);
        q.select(root);
        List<Predicate> predicates = new ArrayList<>();
        String ten = params.get("tenMH");
        if (ten != null && !ten.isEmpty()) {
            predicates.add(b.like(root.get("tenMonHoc"), String.format("%%%s%%", ten)));
        }
        q.where(predicates.toArray(Predicate[]::new));

        Query query = session.createQuery(q);

        if (params != null) {
            String p = params.get("pageMH");
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
    public boolean addOrUpdateMonHoc(Monhoc mh) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (mh.getIdMonHoc() == null) {
                s.save(mh);
            } else {
                s.update(mh);
            }
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public Monhoc getMonHocById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Monhoc> q = b.createQuery(Monhoc.class);

        Root r = q.from(Monhoc.class);
        q.select(r);

        q.where(b.equal(r.get("idMonHoc"), id));

        Query query = session.createQuery(q);
        return (Monhoc) query.getSingleResult();

    }

    @Override
    public boolean deleteMonHoc(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        Monhoc mh = this.getMonHocById(id);
        try {
            if (this.getMonHocHocKyByIdMonHoc(id)) {
                session.delete(mh);
                return true;
            }
            return false;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //Lay danh sach mon hoc dang giang day
    @Override
    public List<MonhocHocky> getMonHocByGiangVien(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rMonHoc = q.from(MonhocHocky.class);
        Root rGiangVien = q.from(Giangvien.class);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String idTaiKhoan = params.get("taiKhoanId");
            if (idTaiKhoan != null) {
                predicates.add(b.equal(rGiangVien.get("idTaiKhoan"), Integer.parseInt(idTaiKhoan)));
                predicates.add(b.equal(rMonHoc.get("idGiangVien"), rGiangVien.get("idGiangVien")));
            }
            Date currentDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
            predicates.add(b.and(
                    b.lessThanOrEqualTo(rMonHoc.get("idHocky").get("ngayBatDau"), currentDate),
                    b.greaterThanOrEqualTo(rMonHoc.get("idHocky").get("ngayKetThuc"), currentDate)
            ));
            q.select(rMonHoc).where(predicates.toArray(Predicate[]::new));
            q.groupBy(rMonHoc.get("idMonHocHocKy"));
            Query query = s.createQuery(q);
            return query.getResultList();
        }
        return null;

    }

    @Override
    public List<MonhocHocky> getMonHocByGiangVienChuaDay(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root rMonHoc = q.from(MonhocHocky.class);
        Root rGiangVien = q.from(Giangvien.class);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String idTaiKhoan = params.get("taiKhoanId");
            if (idTaiKhoan != null) {
                predicates.add(b.equal(rGiangVien.get("idTaiKhoan"), Integer.parseInt(idTaiKhoan)));
                predicates.add(b.equal(rMonHoc.get("idGiangVien"), rGiangVien.get("idGiangVien")));
            }
            Date currentDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
            predicates.add(
                    b.greaterThan(rMonHoc.get("idHocky").get("ngayBatDau"), currentDate)
            );
            q.select(rMonHoc).where(predicates.toArray(Predicate[]::new));
            q.groupBy(rMonHoc.get("idMonHocHocKy"));
            Query query = s.createQuery(q);
            return query.getResultList();
        }
        return null;
    }

    @Override
    public List<MonhocHocky> getMonHocByGiangVienDaDay(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rMonHoc = q.from(MonhocHocky.class);
        Root rGiangVien = q.from(Giangvien.class);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String idTaiKhoan = params.get("taiKhoanId");
            if (idTaiKhoan != null) {
                predicates.add(b.equal(rGiangVien.get("idTaiKhoan"), Integer.parseInt(idTaiKhoan)));
                predicates.add(b.equal(rMonHoc.get("idGiangVien"), rGiangVien.get("idGiangVien")));
            }
            Date currentDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
            predicates.add(b.lessThan(rMonHoc.get("idHocky").get("ngayKetThuc"), currentDate));
            q.select(rMonHoc).where(predicates.toArray(Predicate[]::new));
            q.groupBy(rMonHoc.get("idMonHocHocKy"));
            Query query = s.createQuery(q);
            return query.getResultList();
        }
        return null;
    }

    //Update
    @Override
    public Long CountMonHocInGV(int idGiangVien) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);

        Root r = q.from(MonhocHocky.class);
        q.select(b.count(r)).where(b.equal(r.get("idGiangVien").get("idGiangVien"), idGiangVien));

        long countMonHoc = session.createQuery(q).uniqueResult();

        return countMonHoc;

    }

    @Override
    public List<Monhoc> getMonHocByIdSinhVien(int idSinhvien) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rMonHoc = q.from(Monhocdangky.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(b.equal(rMonHoc.get("idSinhVien"), idSinhvien));
        predicates.add(b.equal(rMonHoc.get("trangThai"), 1));
        predicates.add(b.equal(rMonHoc.get("khoaMon"), 1));
        q.select(rMonHoc).where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Monhoc> getMonHocByIdSinhVienDangHoc(int idSinhvien) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rMonHoc = q.from(Monhocdangky.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(b.equal(rMonHoc.get("idSinhVien"), idSinhvien));
        predicates.add(b.equal(rMonHoc.get("khoaMon"), 0));
        q.select(rMonHoc).where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<MonhocHocky> getMonHocHocKy(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        String idLopHoc = params.get("idLopHoc");
        String tenMonHoc = params.get("tenMonHoc");
        Root rMonHocHocKy = q.from(MonhocHocky.class);
        List<Predicate> predicates = new ArrayList<>();
        if (idLopHoc != null) {
            predicates.add(b.equal(rMonHocHocKy.get("idHocky").get("idLop"), Integer.parseInt(idLopHoc)));
        }
        if (tenMonHoc != null) {
            predicates.add(b.like(rMonHocHocKy.get("idMonHoc").get("tenMonHoc"), String.format("%%%s%%", tenMonHoc)));
        }

        Date currentDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        predicates.add(b.and(
                b.lessThanOrEqualTo(rMonHocHocKy.get("idHocky").get("ngayDangKy"), currentDate),
                b.greaterThanOrEqualTo(rMonHocHocKy.get("idHocky").get("ngayHetHan"), currentDate)
        ));

        q.select(rMonHocHocKy).where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(q);
        try {
            return query.getResultList();
        } catch (NoResultException | NonUniqueResultException ex) {

            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Monhocdangky> getMonHocSinhVienDangKy(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        String idSinhVien = params.get("idSinhVien");
        Root rMonHocHocKy = q.from(MonhocHocky.class);
        Root rMonHocDangKy = q.from(Monhocdangky.class);
        List<Predicate> predicates = new ArrayList<>();
        if (idSinhVien != null) {
            predicates.add(b.equal(rMonHocHocKy.get("idMonHocHocKy"), rMonHocDangKy.get("idMonHoc")));
            predicates.add(b.equal(rMonHocDangKy.get("idSinhVien"), Integer.parseInt(idSinhVien)));
        }

        Date currentDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        predicates.add(b.and(
                b.lessThanOrEqualTo(rMonHocHocKy.get("idHocky").get("ngayDangKy"), currentDate),
                b.greaterThanOrEqualTo(rMonHocHocKy.get("idHocky").get("ngayHetHan"), currentDate)
        ));

        q.select(rMonHocDangKy).where(predicates.toArray(Predicate[]::new));
        q.distinct(true);
        Query query = s.createQuery(q);
        try {
            return query.getResultList();
        } catch (NoResultException | NonUniqueResultException ex) {

            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public MonhocHocky getMonHocHocKyDate(int idMonHoc) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root rMonHocHocKy = q.from(MonhocHocky.class);
        List<Predicate> predicates = new ArrayList<>();
        Date currentDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        predicates.add(b.equal(rMonHocHocKy.get("idMonHocHocKy"), idMonHoc));
        predicates.add(b.and(
                b.lessThanOrEqualTo(rMonHocHocKy.get("idHocky").get("ngayDangKy"), currentDate),
                b.greaterThanOrEqualTo(rMonHocHocKy.get("idHocky").get("ngayHetHan"), currentDate)
        ));
        q.select(rMonHocHocKy).where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(q);
        try {
            return (MonhocHocky) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {

            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean thanhToanHocPhi(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        List<Monhocdangky> monHocs = this.getMonHocSinhVienDangKy(params);
        if (!monHocs.isEmpty() && monHocs != null) {
            short thanhToan = 1;
            for (Monhocdangky monhoc : monHocs) {
                monhoc.setThanhToan(thanhToan);
                s.update(monhoc);
            }
            return true;
        }
        return false;
    }

    @Override
    public Long countMonHoc() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);

        Root r = q.from(Monhoc.class);
        q.select(b.count(r));

        long countMH = s.createQuery(q).uniqueResult();

        return countMH;

    }

    @Override
    public boolean getMonHocHocKyByIdMonHoc(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object> q = b.createQuery(Object.class);
        Root rMonHoc = q.from(MonhocHocky.class);
        q.select(rMonHoc);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rMonHoc.get("idMonHoc"), id));
        q.where(predicates.toArray(new Predicate[0]));
        Query query = s.createQuery(q);
        try {
            List<MonhocHocky> monHoc = query.getResultList();
            if (monHoc.isEmpty()) {
                return true;
            } else {
                // Process the non-empty lopHoc list here if needed
                return false;
            }
        } catch (NoResultException | NonUniqueResultException ex) {
            ex.printStackTrace();
            return false;
        }

    }

}
