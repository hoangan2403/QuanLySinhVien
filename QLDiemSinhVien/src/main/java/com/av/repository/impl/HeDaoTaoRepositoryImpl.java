/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.repository.impl;

import com.av.pojo.Hedaotao;
import com.av.pojo.Khoadaotao;
import com.av.repository.HeDaoTaoRepository;
import com.av.repository.LopHocRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author FPTSHOP
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class HeDaoTaoRepositoryImpl implements HeDaoTaoRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private Environment Env;
    
    @Autowired
    private LopHocRepository lhRepo;

    @Override
    public List<Hedaotao> getHedaotaos(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Hedaotao> q = b.createQuery(Hedaotao.class);

        Root r = q.from(Hedaotao.class);
        q.select(r);
        List<Predicate> predicates = new ArrayList<>();
        if (params != null) {
            String ten = params.get("tenHDT");
            if (ten != null && !ten.isEmpty()) {
                predicates.add(b.like(r.get("tenHeDaoTao"), String.format("%%%s%%", ten)));
            }
        }
        q.where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(q);
        if (params != null) {
            String p = params.get("pageHDT");
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
    public boolean addOrUpdateHeDT(Hedaotao hdt) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (hdt.getIdhedaotao() == null) {
                s.save(hdt);
            } else {
                s.update(hdt);
            }
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        
    }

    @Override
    public Hedaotao getHedaotaoById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Hedaotao> q = b.createQuery(Hedaotao.class);

        Root r = q.from(Hedaotao.class);
        q.select(r);

        q.where(b.equal(r.get("idhedaotao"), id));

        Query query = session.createQuery(q);
        return (Hedaotao) query.getSingleResult();
    }

    @Override
    public boolean deleteHDT(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        Hedaotao hdt = this.getHedaotaoById(id);
        long countHDT = this.lhRepo.countLopHocByIdHDT(id);
        try {
            if (countHDT == 0) {
                session.delete(hdt);
                return true;
            }
            return false;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public long countHeDaoTao() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);
        
        Root r = q.from(Hedaotao.class);
        q.select(b.count(r));
        
        long countHDT = session.createQuery(q).uniqueResult();
        
        return countHDT;
    }
    
}
