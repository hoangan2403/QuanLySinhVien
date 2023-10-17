/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FPTSHOP
 */
@Entity
@Table(name = "khoadaotao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Khoadaotao.findAll", query = "SELECT k FROM Khoadaotao k"),
    @NamedQuery(name = "Khoadaotao.findByIdKhoaDaoTao", query = "SELECT k FROM Khoadaotao k WHERE k.idKhoaDaoTao = :idKhoaDaoTao"),
    @NamedQuery(name = "Khoadaotao.findByTenKhoaDaoTao", query = "SELECT k FROM Khoadaotao k WHERE k.tenKhoaDaoTao = :tenKhoaDaoTao")})
public class Khoadaotao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idKhoaDaoTao")
    private Integer idKhoaDaoTao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tenKhoaDaoTao")
    private String tenKhoaDaoTao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKhoaDaoTao")
    @JsonIgnore
    private Set<Nganhdaotao> nganhdaotaoSet;

    public Khoadaotao() {
    }

    public Khoadaotao(Integer idKhoaDaoTao) {
        this.idKhoaDaoTao = idKhoaDaoTao;
    }

    public Khoadaotao(Integer idKhoaDaoTao, String tenKhoaDaoTao) {
        this.idKhoaDaoTao = idKhoaDaoTao;
        this.tenKhoaDaoTao = tenKhoaDaoTao;
    }

    public Integer getIdKhoaDaoTao() {
        return idKhoaDaoTao;
    }

    public void setIdKhoaDaoTao(Integer idKhoaDaoTao) {
        this.idKhoaDaoTao = idKhoaDaoTao;
    }

    public String getTenKhoaDaoTao() {
        return tenKhoaDaoTao;
    }

    public void setTenKhoaDaoTao(String tenKhoaDaoTao) {
        this.tenKhoaDaoTao = tenKhoaDaoTao;
    }

    @XmlTransient
    public Set<Nganhdaotao> getNganhdaotaoSet() {
        return nganhdaotaoSet;
    }

    public void setNganhdaotaoSet(Set<Nganhdaotao> nganhdaotaoSet) {
        this.nganhdaotaoSet = nganhdaotaoSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKhoaDaoTao != null ? idKhoaDaoTao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Khoadaotao)) {
            return false;
        }
        Khoadaotao other = (Khoadaotao) object;
        if ((this.idKhoaDaoTao == null && other.idKhoaDaoTao != null) || (this.idKhoaDaoTao != null && !this.idKhoaDaoTao.equals(other.idKhoaDaoTao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.av.pojo.Khoadaotao[ idKhoaDaoTao=" + idKhoaDaoTao + " ]";
    }
    
}
