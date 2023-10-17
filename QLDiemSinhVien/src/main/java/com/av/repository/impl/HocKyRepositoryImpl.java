/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.repository.impl;

import com.av.pojo.Hocky;
import com.av.pojo.Loaihocky;
import com.av.pojo.Sinhvien;
import com.av.repository.HocKyRepository;
import com.av.repository.PhieuMonHocRepository;
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
public class HocKyRepositoryImpl implements HocKyRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private PhieuMonHocRepository pmhRepository;
    
    @Autowired
    private Environment env;

    @Override
    public List<Hocky> getHockys(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Hocky> q = b.createQuery(Hocky.class);
        
        Root<Hocky> r = q.from(Hocky.class);
        q.select(r);
        
        List<Predicate> predicates = new ArrayList<>();
        String tenHKi = params.get("tenHK");
        if (tenHKi != null && !tenHKi.isEmpty()) {
            predicates.add(b.like(r.get("tenHocKy").get("tenHocKy"), String.format("%%%s%%", tenHKi)));
        }
        q.where(predicates.toArray(new Predicate[0]));
//        q.distinct(true);
        Query query = s.createQuery(q);
        if (params != null) {
            String p = params.get("pageHK");
            if (p != null && !p.isEmpty()) {
                int page = Integer.parseInt(p);
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
                query.setMaxResults(pageSize);
                query.setFirstResult((page - 1) * pageSize);
            }
        }
        return query.getResultList();
    }

    @Override
    public boolean addOrUpdateHocKy(Hocky hk) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (hk.getIdHocKy() != null) {
                s.update(hk);
            } else {
                s.save(hk);
            }
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<Loaihocky> getLoaihockys(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Loaihocky");
        return q.getResultList();
    }

    @Override
    public Hocky getHockyById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Hocky> q = b.createQuery(Hocky.class);

        Root<Hocky> root = q.from(Hocky.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(root.get("idHocKy"), id));
        q.where(predicates.toArray(new Predicate[0]));
        Query query = s.createQuery(q);
        return (Hocky) query.getSingleResult();
    }

    @Override
    public List<Hocky> getHocKyByIdLop(int idLop) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root<Hocky> rHocKy = q.from(Hocky.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rHocKy.get("idLop"), idLop));
        q.select(b.array(rHocKy));
        Query query = s.createQuery(q);
        try {
            List<Hocky> hocKy = query.getResultList();
            if (hocKy.isEmpty()) {
                return null;
            } else {
                // Process the non-empty lopHoc list here if needed
                return hocKy;
            }
        } catch (NoResultException | NonUniqueResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Long CountHocKyByIdLop(int idLop) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);

        Root r = q.from(Hocky.class);
        q.select(b.count(r)).where(b.equal(r.get("idLop").get("idLopHoc"), idLop));

        long countHocKy = session.createQuery(q).uniqueResult();

        return countHocKy;
    }
    @Override
    public boolean deleteHK(int idHK) {
        Session session = this.factory.getObject().getCurrentSession();
        Hocky hk = this.getHockyById(idHK);
        long countHK = this.pmhRepository.countMonHocHKByIdHK(idHK);
        
        try {
            if(countHK == 0){
                session.delete(hk);
            }
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public long countHocKy() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);
        
        Root r = q.from(Hocky.class);
        q.select(b.count(r));
        
        long countHK = s.createQuery(q).uniqueResult();
        
        return countHK;
    }

}
