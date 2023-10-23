/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.repository.impl;

import com.av.pojo.Cauhoidiendang;
import com.av.pojo.Taikhoan;
import com.av.pojo.Traloidiendan;
import java.util.ArrayList;
import java.util.Collections;
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
import com.av.repository.DienDanRepository;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class DienDanRepositoryImpl implements DienDanRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Object getCauHoi(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root<Cauhoidiendang> rCauHoi = q.from(Cauhoidiendang.class);
        Root<Taikhoan> rTaiKhoan = q.from(Taikhoan.class);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String cateId = params.get("cauhoiId");
            if (cateId != null) {
                predicates.add(b.equal(rCauHoi.get("idCauHoiDienDan"), Integer.parseInt(cateId)));
            }
            predicates.add(b.equal(rCauHoi.get("idTaiKhoan"), rTaiKhoan.get("idTaiKhoan")));
            q.multiselect(rCauHoi.get("noiDungCauHoi"), rCauHoi.get("ngayTao"), rTaiKhoan.get("tenTaiKhoan"), rTaiKhoan.get("image"))
                    .where(predicates.toArray(Predicate[]::new));
        }
        Query query = session.createQuery(q);
        try {
            return query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {

            ex.printStackTrace();
            return Collections.emptyList();
        }

    }

    @Override
    public boolean addOrUpdateTraloi(Traloidiendan p) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (p.getNoiDungTraLoi() == null) {
                return false;
            } else {
                if (p.getIdTraLoiDienDan() == null) {
                    s.save(p);
                } else {
                    s.update(p);
                }
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public List<Object> getCauHoiDienDan() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root<Cauhoidiendang> rCauHoi = q.from(Cauhoidiendang.class);

        q.select(b.array(rCauHoi));

        Query query = s.createQuery(q);
        try {
            return query.getResultList();
        } catch (NoResultException | NonUniqueResultException ex) {

            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Object> getTraLoi(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root<Traloidiendan> rTraLoi = q.from(Traloidiendan.class);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String cateId = params.get("cauhoiId");
            if (cateId != null) {
                predicates.add(b.equal(rTraLoi.get("idCauHoiDienDan"), Integer.parseInt(cateId)));
            }
            q.select(b.array(rTraLoi))
                    .where(predicates.toArray(Predicate[]::new));
        }
        q.orderBy(b.desc(rTraLoi.get("idTraLoiDienDan")));
        Query query = session.createQuery(q);
        try {
            return query.getResultList();
        } catch (NoResultException | NonUniqueResultException ex) {

            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public boolean addOrUpdateCauHoi(Cauhoidiendang p) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (p.getNoiDungCauHoi() == null) {
                return false;
            } else {
                if (p.getIdCauHoiDienDan() == null) {
                    s.save(p);
                } else {
                    s.update(p);
                }
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCauHoi(Cauhoidiendang p) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root<Traloidiendan> rTraLoi = q.from(Traloidiendan.class);
        Root<Cauhoidiendang> rCauHoi = q.from(Cauhoidiendang.class);
        q.select(b.array(rTraLoi))
                .where(b.equal(rTraLoi.get("idCauHoiDienDan"), p.getIdCauHoiDienDan()));
        Query query = session.createQuery(q);

        List<Traloidiendan> traloi = query.getResultList();

        q.select(b.array(rCauHoi))
                .where(b.equal(rCauHoi.get("idCauHoiDienDan"), p.getIdCauHoiDienDan()));

        q.groupBy(rCauHoi.get("idCauHoiDienDan"));
        Query query1 = session.createQuery(q);
        Cauhoidiendang a = (Cauhoidiendang) query1.getSingleResult();

        for (Traloidiendan tl : traloi) {
            session.delete(tl);
        }
        session.delete(a);
        return true;
    }

    @Override
    public Cauhoidiendang getCauHoiById(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root<Cauhoidiendang> rCauHoi = q.from(Cauhoidiendang.class);
        String cateId = params.get("idCauHoiDienDan");
        q.select(b.array(rCauHoi))
                .where(b.equal(rCauHoi.get("idCauHoiDienDan"), Integer.parseInt(cateId)));

        q.groupBy(rCauHoi.get("idCauHoiDienDan"));
        Query query = s.createQuery(q);
        return (Cauhoidiendang) query.getSingleResult();
    }

    @Override
    public List<Traloidiendan> getTraLoiByTaiKhoan(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Traloidiendan[]> q = b.createQuery(Traloidiendan[].class);
        Root r = q.from(Traloidiendan.class);
        q.select(r);
        q.where(b.equal(r.get("idTaiKhoan").get("idTaiKhoan"), id));
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public boolean deleteTraloiByTaiKhoan(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        List<Traloidiendan> traLois = this.getTraLoiByTaiKhoan(id);
        try {
            for (Traloidiendan traloi : traLois) {
                s.delete(traloi);
            }
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteCauHoiByTaiKhoan(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        List<Cauhoidiendang> cauHois = this.getCauhoiByTaiKhoan(id);
        try {
            for (Cauhoidiendang cauHoi : cauHois) {
                this.deleteCauHoi(cauHoi);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public List<Cauhoidiendang> getCauhoiByTaiKhoan(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Cauhoidiendang[]> q = b.createQuery(Cauhoidiendang[].class);
        Root r = q.from(Cauhoidiendang.class);
        q.select(r);
        q.where(b.equal(r.get("idTaiKhoan").get("idTaiKhoan"), id));
        Query query = s.createQuery(q);
        return query.getResultList();
    }
}
