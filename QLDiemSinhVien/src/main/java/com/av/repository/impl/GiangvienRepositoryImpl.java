/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.repository.impl;

import com.av.pojo.Giangvien;
import com.av.pojo.Monhoc;
import com.av.pojo.Sinhvien;
import com.av.pojo.Taikhoan;
import com.av.repository.GiangvienRepository;
import com.av.repository.MonHocRepository;
import com.av.repository.TaiKhoanRepository;
import java.util.ArrayList;
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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author FPTSHOP
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class GiangvienRepositoryImpl implements GiangvienRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private TaiKhoanRepository tkRepo;
    
    @Autowired
    private MonHocRepository mhRepo;
    
    @Autowired
    private Environment Env;
    @Override
    public List<Giangvien> getGiangviens(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Giangvien> q = b.createQuery(Giangvien.class);
        Root root = q.from(Giangvien.class);
        q.select(root);
        List<Predicate> predicates = new ArrayList<>();
        String tenSV = params.get("tenGV");
        if (tenSV != null && !tenSV.isEmpty()) {
            predicates.add(b.like(root.get("hoTen"), String.format("%%%s%%", tenSV)));
        }
        q.where(predicates.toArray(Predicate[]::new));

        Query query = session.createQuery(q);

        if (params != null) {
            String p = params.get("pageGV");
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
    public boolean addOrUpdateGiangVien(Giangvien gv) {
        Session s = this.factory.getObject().getCurrentSession();

        try {
            if (gv.getIdGiangVien() == null) {
                s.save(gv);
            } else {
                s.update(gv);
            }
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public Giangvien getGiangVienById(int idGiangVien) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Giangvien> q = b.createQuery(Giangvien.class);

        Root r = q.from(Giangvien.class);
        q.select(r);

        q.where(b.equal(r.get("idGiangVien"), idGiangVien));

        Query query = session.createQuery(q);
        return (Giangvien) query.getSingleResult();
    }
    
    //update xoa
    @Override
    public boolean deleteGV(int idGiangVien) {
        Session session = this.factory.getObject().getCurrentSession();
        Giangvien gv = this.getGiangVienById(idGiangVien);
        long countMH = this.mhRepo.CountMonHocInGV(idGiangVien);
        this.tkRepo.deleteTK(gv.getIdTaiKhoan());
        try {
            if (countMH == 0) {
                session.delete(gv);
                return true;
            }
            return false;

        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public Giangvien getGiangVienByIdTaiKhoan(int idTaiKhoan) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Giangvien> q = b.createQuery(Giangvien.class);

        Root<Giangvien> root = q.from(Giangvien.class);
        q.select(root);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(b.equal(root.get("idTaiKhoan"), idTaiKhoan));
        q.where(predicates.toArray(new Predicate[0]));
        Query query = session.createQuery(q);
        try {
            return (Giangvien) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {

            ex.printStackTrace();
            return null;
        }
    }
    
    

    

//    @Override
//    public Taikhoan getTaikhoanByGV(int idGiangVien) {
//        Session s = this.factory.getObject().getCurrentSession();
//        Giangvien gv = this.getGiangVienById(idGiangVien);
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<Taikhoan> q = b.createQuery(Taikhoan.class);
//        Root r = q.from(Taikhoan.class);
//        if (gv.getIdTaiKhoan() != null) {
//            q.select(r).where(b.equal(r.get("idTaiKhoan"), gv.getIdTaiKhoan().getIdTaiKhoan()));
//            Query query = s.createQuery(q);
//            return (Taikhoan) query.getSingleResult();
//        } else {
//            return null;
//        }
//    }
    
    //Dem giang vien de phan trang
    @Override
    public long countGiangVien() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);

        Root r = q.from(Giangvien.class);
        q.select(b.count(r));

        long countGV = s.createQuery(q).uniqueResult();

        return countGV;
    }

}
