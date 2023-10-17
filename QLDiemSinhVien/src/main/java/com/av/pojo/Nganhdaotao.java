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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "nganhdaotao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nganhdaotao.findAll", query = "SELECT n FROM Nganhdaotao n"),
    @NamedQuery(name = "Nganhdaotao.findByIdNganhDaoTao", query = "SELECT n FROM Nganhdaotao n WHERE n.idNganhDaoTao = :idNganhDaoTao"),
    @NamedQuery(name = "Nganhdaotao.findByTenNganhDaoTao", query = "SELECT n FROM Nganhdaotao n WHERE n.tenNganhDaoTao = :tenNganhDaoTao")})
public class Nganhdaotao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNganhDaoTao")
    private Integer idNganhDaoTao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tenNganhDaoTao")
    private String tenNganhDaoTao;
    @JoinColumn(name = "idKhoaDaoTao", referencedColumnName = "idKhoaDaoTao")
    @ManyToOne(optional = false)
    private Khoadaotao idKhoaDaoTao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNganh")
    @JsonIgnore
    private Set<Lophoc> lophocSet;

    public Nganhdaotao() {
    }

    public Nganhdaotao(Integer idNganhDaoTao) {
        this.idNganhDaoTao = idNganhDaoTao;
    }

    public Nganhdaotao(Integer idNganhDaoTao, String tenNganhDaoTao) {
        this.idNganhDaoTao = idNganhDaoTao;
        this.tenNganhDaoTao = tenNganhDaoTao;
    }

    public Integer getIdNganhDaoTao() {
        return idNganhDaoTao;
    }

    public void setIdNganhDaoTao(Integer idNganhDaoTao) {
        this.idNganhDaoTao = idNganhDaoTao;
    }

    public String getTenNganhDaoTao() {
        return tenNganhDaoTao;
    }

    public void setTenNganhDaoTao(String tenNganhDaoTao) {
        this.tenNganhDaoTao = tenNganhDaoTao;
    }

    public Khoadaotao getIdKhoaDaoTao() {
        return idKhoaDaoTao;
    }

    public void setIdKhoaDaoTao(Khoadaotao idKhoaDaoTao) {
        this.idKhoaDaoTao = idKhoaDaoTao;
    }

    @XmlTransient
    public Set<Lophoc> getLophocSet() {
        return lophocSet;
    }

    public void setLophocSet(Set<Lophoc> lophocSet) {
        this.lophocSet = lophocSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNganhDaoTao != null ? idNganhDaoTao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nganhdaotao)) {
            return false;
        }
        Nganhdaotao other = (Nganhdaotao) object;
        if ((this.idNganhDaoTao == null && other.idNganhDaoTao != null) || (this.idNganhDaoTao != null && !this.idNganhDaoTao.equals(other.idNganhDaoTao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.av.pojo.Nganhdaotao[ idNganhDaoTao=" + idNganhDaoTao + " ]";
    }
    
}
