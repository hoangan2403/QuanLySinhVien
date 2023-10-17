/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.repository.impl;

import com.av.pojo.Hocky;
import com.av.pojo.Lophoc;
import com.av.pojo.Nganhdaotao;
import com.av.pojo.Sinhvien;
import com.av.repository.HocKyRepository;
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
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.av.repository.LopHocRepository;
import com.av.repository.SinhVienRepository;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class LopHocRepositoryImpl implements LopHocRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private HocKyRepository hkRepository;

    @Autowired
    private Environment env;

    @Autowired
    private SinhVienRepository sinhVienRepo;

    @Override
    public List<Lophoc> listLopHoc(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Lophoc> q = b.createQuery(Lophoc.class);

        Root<Lophoc> rLop = q.from(Lophoc.class);

        q.select(rLop);

        List<Predicate> predicates = new ArrayList<>();
        String tenSV = params.get("tenLH");
        if (tenSV != null && !tenSV.isEmpty()) {
            predicates.add(b.like(rLop.get("tenLopHoc"), String.format("%%%s%%", tenSV)));
        }
        q.where(predicates.toArray(Predicate[]::new));

        Query query = s.createQuery(q);
        if (params != null) {
            String p = params.get("pageLH");
            if (p != null && !p.isEmpty()) {
                int page = Integer.parseInt(p);
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
                query.setMaxResults(pageSize);
                query.setFirstResult((page - 1) * pageSize);
            }
        }

//        try {
            return query.getResultList();
//        } catch (NoResultException | NonUniqueResultException ex) {
//            ex.printStackTrace();
//            return null;
//        }
    }

    @Override
    public Lophoc getLopById(int idLop) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root<Lophoc> rLop = q.from(Lophoc.class);

        q.select(b.array(rLop));
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rLop.get("idLopHoc"), idLop));
        q.where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(q);
        try {
            return (Lophoc) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addOrUpdateLopHoc(Lophoc lh) {
        Session s = this.factory.getObject().getCurrentSession();
        long countLH = this.checkTenLop(lh.getTenLopHoc());
        try {
            if (countLH == 0) {
                if (lh.getIdLopHoc() == null) {
                    s.save(lh);
                } else {
                    s.update(lh);
                }
                return true;
            } else {
                return false;
            }
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public long checkTenLop(String tenLop) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();

        CriteriaQuery<Long> countQuery = b.createQuery(Long.class);
        Root<Lophoc> countRoot = countQuery.from(Lophoc.class);
        countQuery.select(b.count(countRoot)).where(b.equal(countRoot.get("tenLopHoc"), tenLop));
        long duplicateCount = s.createQuery(countQuery).uniqueResult();

        return duplicateCount;
    }

    @Override
    public List<Lophoc> getLopByIdHeDaoTao(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root<Lophoc> rLop = q.from(Lophoc.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rLop.get("idHeDaoTao"), id));
        q.select(b.array(rLop));
        Query query = s.createQuery(q);
        try {
            List<Lophoc> lopHoc = query.getResultList();
            if (lopHoc.isEmpty()) {
                return null;
            } else {
                // Process the non-empty lopHoc list here if needed
                return lopHoc;
            }
        } catch (NoResultException | NonUniqueResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Lophoc> getLopByIdKhoaHoc(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root<Lophoc> rLop = q.from(Lophoc.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rLop.get("idKhoaHoc"), id));
        q.select(b.array(rLop));
        Query query = s.createQuery(q);
        try {
            List<Lophoc> lopHoc = query.getResultList();
            if (lopHoc.isEmpty()) {
                return null;
            } else {
                // Process the non-empty lopHoc list here if needed
                return lopHoc;
            }
        } catch (NoResultException | NonUniqueResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteLopHoc(int idLop) {
        Session s = this.factory.getObject().getCurrentSession();
        Lophoc lop = this.getLopById(idLop);
        if (lop == null) {
            return false;
        }
        long countHK = this.hkRepository.CountHocKyByIdLop(idLop);
        long countSV = this.sinhVienRepo.countSinhVienByIdLop(idLop);
        if (countHK == 0 && countSV == 0 && lop.getIdLopHoc() != null) {
            s.delete(lop);
            return true;
        }
        return false;

    }

    @Override
    public long countLopHocByIdHDT(int idHDT) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);

        Root r = q.from(Lophoc.class);
        q.select(b.count(r)).where(b.equal(r.get("idHeDaoTao").get("idhedaotao"), idHDT));

        long countHDT = session.createQuery(q).uniqueResult();

        return countHDT;
    }

    @Override
    public long countLopHoc() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);
        Root r = q.from(Lophoc.class);
        q.select(b.count(r));
        long countLH = session.createQuery(q).uniqueResult();
        return countLH;
    }

}
