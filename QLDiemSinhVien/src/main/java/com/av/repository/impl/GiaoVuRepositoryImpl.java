/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.repository.impl;

import com.av.pojo.Giaovu;
import com.av.pojo.Sinhvien;
import com.av.repository.GiaoVuRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author FPTSHOP
 */
@Repository
@Transactional
public class GiaoVuRepositoryImpl implements GiaoVuRepository{

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public Giaovu getGiaovus(int idTaiKhoan) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Giaovu> q = b.createQuery(Giaovu.class);
        
        Root root = q.from(Giaovu.class);
        q.select(root);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(root.get("idTaiKhoan"), idTaiKhoan));
        
        q.where(predicates.toArray(Predicate[]::new));
        
        Query query = s.createQuery(q);
        return (Giaovu) query.getSingleResult();
    }
    
}
