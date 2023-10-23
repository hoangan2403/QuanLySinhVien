/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.repository.impl;

import com.av.controllers.KhoaHocController;
import com.av.pojo.Hocky;
import com.av.pojo.Khoa;
import com.av.pojo.Khoadaotao;
import com.av.pojo.Lophoc;
import com.av.pojo.Monhoc;
import com.av.pojo.MonhocHocky;
import com.av.pojo.Nganhdaotao;
import com.av.pojo.Phonghoc;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.av.repository.KhoaDaoTaoRepository;
import com.av.repository.PhieuMonHocRepository;
import java.util.ArrayList;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import sun.awt.www.content.audio.x_aiff;

/**
 *
 * @author FPTSHOP
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class KhoaDaoTaoRepositoryImpl implements KhoaDaoTaoRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private PhieuMonHocRepository pmhRepository;
    
    @Autowired
    private Environment Env;

    @Override
    public List<Khoa> getKhoas(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Khoa> q = b.createQuery(Khoa.class);

        Root r = q.from(Khoa.class);
        q.select(r);
        List<Predicate> predicates = new ArrayList<>();
        if (params != null) {
            String ten = params.get("tenKH");
            if (ten != null && !ten.isEmpty()) {
                predicates.add(b.like(r.get("tenKhoa"), String.format("%%%s%%", ten)));
            }
        }
        q.where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(q);
        if (params != null) {
            String p = params.get("pageKH");
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
    public List<Khoadaotao> getKhoadaotaos(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Khoadaotao> q = b.createQuery(Khoadaotao.class);

        Root r = q.from(Khoadaotao.class);
        q.select(r);
        List<Predicate> predicates = new ArrayList<>();
        if (params != null) {
            String ten = params.get("tenKDT");
            if (ten != null && !ten.isEmpty()) {
                predicates.add(b.like(r.get("tenKhoaDaoTao"), String.format("%%%s%%", ten)));
            }
        }
        q.where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(q);
        if (params != null) {
            String p = params.get("pageKDT");
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
    public List<Phonghoc> getPhonghocs(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Phonghoc> q = b.createQuery(Phonghoc.class);

        Root r = q.from(Phonghoc.class);
        q.select(r);
        List<Predicate> predicates = new ArrayList<>();
        if (params != null) {
            String ten = params.get("tenPH");
            if (ten != null && !ten.isEmpty()) {
                predicates.add(b.like(r.get("tenPhongHoc"), String.format("%%%s%%", ten)));
            }
        }
        q.where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(q);
        if (params != null) {
            String p = params.get("pagePH");
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
    public List<Nganhdaotao> getNganhdaotaos(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Nganhdaotao");
        return q.getResultList();
    }

    @Override
    public boolean addOrUpdateKhoaDT(Khoadaotao kdt) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (kdt.getIdKhoaDaoTao() == null) {
                s.save(kdt);
            } else {
                s.update(kdt);
            }
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean addOrUpdateKhoa(Khoa k) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (k.getIdkhoa() == null) {
                s.save(k);
            } else {
                s.update(k);
            }
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean addOrUpdatePhongHoc(Phonghoc ph) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (ph.getIdPhongHoc() == null) {
                s.save(ph);
            } else {
                s.update(ph);
            }
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean addOrUpdateNghanhDT(Nganhdaotao ndt) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (ndt.getIdNganhDaoTao() == null) {
                s.save(ndt);
            } else {
                s.update(ndt);
            }
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public Khoadaotao getKhoadaotaoById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Khoadaotao> q = b.createQuery(Khoadaotao.class);

        Root r = q.from(Khoadaotao.class);
        q.select(r);

        q.where(b.equal(r.get("idKhoaDaoTao"), id));

        Query query = session.createQuery(q);
        return (Khoadaotao) query.getSingleResult();

    }

    @Override
    public Khoa getKhoaById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Khoa> q = b.createQuery(Khoa.class);

        Root r = q.from(Khoa.class);
        q.select(r);

        q.where(b.equal(r.get("idkhoa"), id));

        Query query = session.createQuery(q);
        return (Khoa) query.getSingleResult();
    }

    @Override
    public Phonghoc getPhonghocById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Phonghoc> q = b.createQuery(Phonghoc.class);

        Root r = q.from(Phonghoc.class);
        q.select(r);

        q.where(b.equal(r.get("idPhongHoc"), id));

        Query query = session.createQuery(q);
        return (Phonghoc) query.getSingleResult();
    }

    @Override
    public Nganhdaotao getNganhdaotaoById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Nganhdaotao> q = b.createQuery(Nganhdaotao.class);

        Root r = q.from(Nganhdaotao.class);
        q.select(r);

        q.where(b.equal(r.get("idNganhDaoTao"), id));

        Query query = session.createQuery(q);
        return (Nganhdaotao) query.getSingleResult();
    }

    @Override
    public long countNghanhDaoToaById(int idKhoaDaoTao) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);

        Root r = q.from(Nganhdaotao.class);
        q.select(b.count(r)).where(b.equal(r.get("idKhoaDaoTao").get("idKhoaDaoTao"), idKhoaDaoTao));

        long countNDT = session.createQuery(q).uniqueResult();

        return countNDT;
    }

    @Override
    public List<Nganhdaotao> getNganhDaoTaoByIdKhoa(int idKhoa) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Nganhdaotao> q = b.createQuery(Nganhdaotao.class);
        Root<Nganhdaotao> rNganh = q.from(Nganhdaotao.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rNganh.get("idKhoaDaoTao").get("idKhoaDaoTao"), idKhoa));
        q.select(rNganh);
        Query query = s.createQuery(q);
        return query.getResultList();
//        try {
//            List<Nganhdaotao> nganhDaoTao = query.getResultList();
//            if (nganhDaoTao.isEmpty()) {
//                return null;
//            } else {
//                // Process the non-empty lopHoc list here if needed
//                return nganhDaoTao;
//            }
//        } catch (NoResultException | NonUniqueResultException ex) {
//            ex.printStackTrace();
//            return null;
//        }
    }

    @Override
    public boolean deleteKhoaDaoTao(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Khoadaotao kdt = this.getKhoadaotaoById(id);
        long countNDT = this.countNghanhDaoToaById(id);
        List<Nganhdaotao> ndts = this.getNganhDaoTaoByIdKhoa(id);
        try {
            if (countNDT == 0) {
                s.delete(kdt);
                return true;
            } 
            
            return false;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public long countLopHocByIdKH(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);

        Root r = q.from(Lophoc.class);
        q.select(b.count(r)).where(b.equal(r.get("idKhoaHoc").get("idkhoa"), id));

        long countKH = session.createQuery(q).uniqueResult();

        return countKH;
    }

    @Override
    public boolean deleteKhoaHoc(int idKhoa) {
        Session s = this.factory.getObject().getCurrentSession();
        long count = this.countLopHocByIdKH(idKhoa);
        Khoa k = this.getKhoaById(idKhoa);
        try {
            if (count == 0) {
                s.delete(k);
                return true;
            }
            return false;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePH(int idPH) {
        Session session = this.factory.getObject().getCurrentSession();
        Phonghoc ph = this.getPhonghocById(idPH);
        Long countPh = this.pmhRepository.countMonHocHKByIdPH(idPH);
        try {
            if (countPh == 0) {
                session.delete(ph);
                return true;
            }
            return false;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public long countKhoa() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);
        
        Root r = q.from(Khoa.class);
        q.select(b.count(r));
        
        long countKH = session.createQuery(q).uniqueResult();
        
        return countKH;
    }

    @Override
    public long countKhoaDaoTao() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);
        
        Root r = q.from(Khoadaotao.class);
        q.select(b.count(r));
        
        long countKDT = session.createQuery(q).uniqueResult();
        
        return countKDT;
    }

    @Override
    public long countPhongHoc() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);
        
        Root r = q.from(Phonghoc.class);
        q.select(b.count(r));
        
        long countPH = session.createQuery(q).uniqueResult();
        
        return countPH;
    }

    @Override
    public long countNghanhDaoTao() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);
        
        Root r = q.from(Nganhdaotao.class);
        q.select(b.count(r));
        
        long countNDT = session.createQuery(q).uniqueResult();
        
        return countNDT;
    }

}
