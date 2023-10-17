/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.repository.impl;

import com.av.pojo.Nganhdaotao;
import com.av.repository.NghanhDaoTaoRepository;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
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
public class NghanhDaoTaoRepositoryImpl implements NghanhDaoTaoRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Nganhdaotao> getNganhdaotaos(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Nganhdaotao");
        return q.getResultList();
    }
    
}
