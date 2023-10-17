/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.repository.impl;

import com.av.pojo.PhieuMonHoc;
import com.av.pojo.Diem;
import com.av.pojo.Monhoc;
import com.av.pojo.MonhocHocky;
import com.av.repository.GiangvienRepository;
import com.av.repository.HocKyRepository;
import com.av.repository.MonHocRepository;
import com.av.repository.PhieuMonHocRepository;
import com.av.repository.SinhVienRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author FPTSHOP
 */
@Repository
@Transactional
public class PhieuMonHocRepositoryImpl implements PhieuMonHocRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
//
//    @Autowired
//    private SinhVienRepository svRepository;
//
    @Autowired
    private MonHocRepository mhRepository;

    @Autowired
    private GiangvienRepository gvReposiory;

    @Autowired
    private HocKyRepository hkRepository;
//
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
//    public boolean addPhieuMH(Map<String, PhieuMonHoc> carts) {
//        Session s = this.factory.getObject().getCurrentSession();
//        try {
//            for (PhieuMonHoc c : carts.values()) {
//                Diem d = new Diem();
//                d.setIdMonHoc(mhRepository.getMonHocById(c.getIdMonHoc()));
//                d.setIdSinhVien(svRepository.getSinhVienById(c.getIdSinhVien()));
//                short a = 0;
//                d.setKhoaDiem(a);
//                s.save(d);
//            }
//            return true;
//        } catch (HibernateException ex) {
//            ex.printStackTrace();
//            return false;
//        }
//    }
//
//    @Override
//    public PhieuMonHoc getPhieuMonHoc() {
//        Session s = this.factory.getObject().getCurrentSession();
//        Query q = s.createQuery("From PhieuMonHoc");
//        return (PhieuMonHoc) q.getSingleResult();
//    }
//
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
//    public boolean deletePhieuMH(PhieuMonHoc pmh) {
//        Session s = this.factory.getObject().getCurrentSession();
//        try {
//            if (pmh != null) {
//                s.delete(pmh);
//            }
//            return true;
//        } catch (HibernateException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    @Override
//    public PhieuMonHoc getPhieuMonHocByIdMh(int idMonHoc, int idSinhVien) {
//        Session s = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<PhieuMonHoc> q = b.createQuery(PhieuMonHoc.class);
//        Root<PhieuMonHoc> r = q.from(PhieuMonHoc.class);
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(b.equal(r.get("idMonHoc"), idMonHoc));
//        predicates.add(b.equal(r.get("idSinhVien"), idSinhVien));
//        q.select(r).where(predicates.toArray(Predicate[]::new));
//
//        Query query = s.createQuery(q);
//        PhieuMonHoc a =  (PhieuMonHoc) query.getSingleResult();
//        try {
//            return a;
//        } catch (NoResultException ex) {
//            // Xử lý nếu không tìm thấy kết quả, có thể trả về null hoặc throw một exception tùy theo logic ứng dụng của bạn.
//            return null;
//        }
//    }
//
//    @Override
//    public PhieuMonHoc getPhieuMonHocByIdSv(int idSinhVien) {
//        Session s = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<PhieuMonHoc> q = b.createQuery(PhieuMonHoc.class);
//
//        Root r = q.from(PhieuMonHoc.class);
//        q.select(r);
//
//        q.where(b.equal(r.get("idSinhVien"), idSinhVien));
//
//        Query query = s.createQuery(q);
//        return (PhieuMonHoc) query.getSingleResult();
//    }

    @Override
    public boolean addPhieuMHHK(MonhocHocky mh) {
        Session s = this.factory.getObject().getCurrentSession();
//        
        try {
            if (mh.getIdMonHocHocKy() == null) {
                mh.setSoLuongConLai(mh.getSoLuong());
                s.save(mh);
            }
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<MonhocHocky> getMonhocByHockys(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<MonhocHocky> q = b.createQuery(MonhocHocky.class);

        Root<MonhocHocky> r = q.from(MonhocHocky.class);
        q.select(r).where(b.equal(r.get("idHocky").get("idHocKy"), id));
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public boolean updatePhieuMHHK(MonhocHocky mh) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (mh.getIdMonHocHocKy() != null) {
                s.update(mh);
            }
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public MonhocHocky getMonhocHocky(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<MonhocHocky> q = b.createQuery(MonhocHocky.class);
        
        Root<MonhocHocky> r = q.from(MonhocHocky.class);
        q.select(r).where(b.equal(r.get("idMonHocHocKy"), id));
        Query query = s.createQuery(q);
        return (MonhocHocky) query.getSingleResult();
    }

    @Override
    public boolean deleteMHHK(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        MonhocHocky mh = this.getMonhocHocky(id);
        try {
            s.delete(mh);
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }

    }
    @Override
    public Long countMonHocHKByIdHK(int idHK) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);

        Root r = q.from(MonhocHocky.class);
        q.select(b.count(r)).where(b.equal(r.get("idHocky").get("idHocKy"), idHK));

        long countMHHK = session.createQuery(q).uniqueResult();

        return countMHHK;
    }
    @Override
    public Long countMonHocHKByIdPH(int idPH) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);

        Root r = q.from(MonhocHocky.class);
        q.select(b.count(r)).where(b.equal(r.get("phongHoc").get("idPhongHoc"), idPH));

        long countMHHK = session.createQuery(q).uniqueResult();

        return countMHHK;
    }

}
