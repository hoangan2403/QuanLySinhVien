///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.av.repository.impl;
//
//import com.av.pojo.Hedaotao;
//import com.av.pojo.Hocky;
//import com.av.pojo.Khoa;
//import com.av.pojo.Khoadaotao;
//import com.av.pojo.Lophoc;
//import com.av.pojo.Nganhdaotao;
//import com.av.pojo.Sinhvien;
//import com.av.repository.DaoTaoRepository;
//import com.av.repository.SinhVienRepository;
//import java.util.ArrayList;
//import java.util.List;
//import javax.persistence.NoResultException;
//import javax.persistence.NonUniqueResultException;
//import javax.persistence.Query;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author Admin
// */
//@Repository
//@Transactional
//public class DaoTaoRepositoryImpl implements DaoTaoRepository {
//
//    @Autowired
//    private LocalSessionFactoryBean factory;
//    @Autowired
//    private SinhVienRepository sinhVienRepo;
//
//    @Override
//    public List<Lophoc> listLopHoc() {
//        Session s = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
//
//        Root<Lophoc> rLop = q.from(Lophoc.class);
//
//        q.select(b.array(rLop));
//        q.orderBy(b.asc(rLop.get("idNganh")));
//
//        Query query = s.createQuery(q);
//        try {
//            return query.getResultList();
//        } catch (NoResultException | NonUniqueResultException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    public Lophoc getLopById(int idLop) {
//        Session s = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
//
//        Root<Lophoc> rLop = q.from(Lophoc.class);
//
//        q.select(b.array(rLop));
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(b.equal(rLop.get("idLopHoc"), idLop));
//        q.where(predicates.toArray(Predicate[]::new));
//        Query query = s.createQuery(q);
//        try {
//            return (Lophoc) query.getSingleResult();
//        } catch (NoResultException | NonUniqueResultException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
//
//    ////delete----------------------------------------
//    
//    // tìm các thành phần liên quan
//    @Override
//    public List<Lophoc> getLopByIdHeDaoTao(int id) {
//        Session s = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
//        Root<Lophoc> rLop = q.from(Lophoc.class);
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(b.equal(rLop.get("idHeDaoTao"), id));
//        q.select(b.array(rLop));
//        Query query = s.createQuery(q);
//        try {
//            List<Lophoc> lopHoc = query.getResultList();
//            if (lopHoc.isEmpty()) {
//                return null;
//            } else {
//                // Process the non-empty lopHoc list here if needed
//                return lopHoc;
//            }
//        } catch (NoResultException | NonUniqueResultException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    public List<Nganhdaotao> getNganhDaoTaoByIdKhoa(int idKhoa) {
//
//        Session s = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
//        Root<Nganhdaotao> rNganh = q.from(Nganhdaotao.class);
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(b.equal(rNganh.get("idKhoaDaoTao"), idKhoa));
//        q.select(b.array(rNganh));
//        Query query = s.createQuery(q);
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
//    }
//
//    @Override
//    public List<Lophoc> getLopByIdKhoaHoc(int id) {
//        Session s = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
//        Root<Lophoc> rLop = q.from(Lophoc.class);
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(b.equal(rLop.get("idKhoaHoc"), id));
//        q.select(b.array(rLop));
//        Query query = s.createQuery(q);
//        try {
//            List<Lophoc> lopHoc = query.getResultList();
//            if (lopHoc.isEmpty()) {
//                return null;
//            } else {
//                // Process the non-empty lopHoc list here if needed
//                return lopHoc;
//            }
//        } catch (NoResultException | NonUniqueResultException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
//    @Override
//    public List<Hocky> getHocKyByIdLop(int idLop) {
//        Session s = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
//        Root<Hocky> rHocKy = q.from(Hocky.class);
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(b.equal(rHocKy.get("idLop"), idLop));
//        q.select(b.array(rHocKy));
//        Query query = s.createQuery(q);
//        try {
//            List<Hocky> hocKy = query.getResultList();
//            if (hocKy.isEmpty()) {
//                return null;
//            } else {
//                // Process the non-empty lopHoc list here if needed
//                return hocKy;
//            }
//        } catch (NoResultException | NonUniqueResultException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
//
//    //Tìm các thành phần đẻ xóa
//    @Override
//    public Hedaotao getHeDaoTaoById(int id) {
//        Session s = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
//        Root<Hedaotao> rHeDaoTao = q.from(Hedaotao.class);
//        q.select(b.array(rHeDaoTao));
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(b.equal(rHeDaoTao.get("idhedaotao"), id));
//        q.where(predicates.toArray(Predicate[]::new));
//        Query query = s.createQuery(q);
//        try {
//            return (Hedaotao) query.getSingleResult();
//        } catch (NoResultException | NonUniqueResultException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    public Khoa getKhoaHocById(int id) {
//        Session s = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
//        Root<Khoa> rKhoa = q.from(Khoa.class);
//        q.select(b.array(rKhoa));
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(b.equal(rKhoa.get("idkhoa"), id));
//        q.where(predicates.toArray(Predicate[]::new));
//        Query query = s.createQuery(q);
//        try {
//            return (Khoa) query.getSingleResult();
//        } catch (NoResultException | NonUniqueResultException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    public Khoadaotao getKhoaDaoTaoById(int idKhoa) {
//        Session s = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
//        Root<Khoadaotao> rKhoa = q.from(Khoadaotao.class);
//        q.select(b.array(rKhoa));
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(b.equal(rKhoa.get("idKhoaDaoTao"), idKhoa));
//        q.where(predicates.toArray(Predicate[]::new));
//        Query query = s.createQuery(q);
//        try {
//            return (Khoadaotao) query.getSingleResult();
//        } catch (NoResultException | NonUniqueResultException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
//    @Override
//    public Lophoc getLopHocByIdLop(int idLop) {
//        Session s = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
//        Root<Lophoc> rLopHoc = q.from(Lophoc.class);
//        q.select(b.array(rLopHoc));
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(b.equal(rLopHoc.get("idLopHoc"), idLop));
//        q.where(predicates.toArray(Predicate[]::new));
//        Query query = s.createQuery(q);
//        try {
//            return (Lophoc) query.getSingleResult();
//        } catch (NoResultException | NonUniqueResultException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
//
//    
//    /// Xóa các thành phần.
//    @Override
//    public Boolean deleteHeDaoTao(int id) {
//        Session s = this.factory.getObject().getCurrentSession();
//        Hedaotao heDaoTao = this.getHeDaoTaoById(id);
//        if (heDaoTao == null) {
//            return false;
//        }
//        List<Lophoc> lopHoc = this.getLopByIdHeDaoTao(id);
//        if (lopHoc == null) {
//            s.delete(heDaoTao);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public Boolean deleteKhoaHoc(int idKhoa) {
//        Session s = this.factory.getObject().getCurrentSession();
//        Khoa khoa = this.getKhoaHocById(idKhoa);
//        if (khoa == null) {
//            return false;
//        }
//        List<Lophoc> lopHoc = this.getLopByIdKhoaHoc(idKhoa);
//        if (lopHoc == null) {
//            s.delete(khoa);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public Boolean deleteKhoaDaoTao(int id) {
//        Session s = this.factory.getObject().getCurrentSession();
//        Khoadaotao khoa = this.getKhoaDaoTaoById(id);
//        if (khoa == null) {
//            return false;
//        }
//        List<Nganhdaotao> nganhDaoTao = this.getNganhDaoTaoByIdKhoa(id);
//        if (nganhDaoTao == null) {
//            s.delete(khoa);
//            return true;
//        }
//        return false;
//    }
//
//   
//    @Override
//    public Boolean deleteLopHoc(int idLop) {
//        Session s = this.factory.getObject().getCurrentSession();
//        Lophoc lop = this.getLopById(idLop);
//        if (lop == null) {
//            return false;
//        }
//        List<Hocky> hocKy = this.getHocKyByIdLop(idLop);
//        List<Sinhvien> sinhVien = this.sinhVienRepo.getSinhVienByIdLop(idLop);
//        if (hocKy == null && sinhVien == null) {
//            s.delete(lop);
//            return true;
//        }
//        return false;
//
//    }
//
//}
