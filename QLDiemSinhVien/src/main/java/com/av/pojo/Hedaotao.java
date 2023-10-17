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
@Table(name = "hedaotao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hedaotao.findAll", query = "SELECT h FROM Hedaotao h"),
    @NamedQuery(name = "Hedaotao.findByIdhedaotao", query = "SELECT h FROM Hedaotao h WHERE h.idhedaotao = :idhedaotao"),
    @NamedQuery(name = "Hedaotao.findByTenHeDaoTao", query = "SELECT h FROM Hedaotao h WHERE h.tenHeDaoTao = :tenHeDaoTao")})
public class Hedaotao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idhedaotao")
    private Integer idhedaotao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tenHeDaoTao")
    private String tenHeDaoTao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHeDaoTao")
    @JsonIgnore
    private Set<Lophoc> lophocSet;

    public Hedaotao() {
    }

    public Hedaotao(Integer idhedaotao) {
        this.idhedaotao = idhedaotao;
    }

    public Hedaotao(Integer idhedaotao, String tenHeDaoTao) {
        this.idhedaotao = idhedaotao;
        this.tenHeDaoTao = tenHeDaoTao;
    }

    public Integer getIdhedaotao() {
        return idhedaotao;
    }

    public void setIdhedaotao(Integer idhedaotao) {
        this.idhedaotao = idhedaotao;
    }

    public String getTenHeDaoTao() {
        return tenHeDaoTao;
    }

    public void setTenHeDaoTao(String tenHeDaoTao) {
        this.tenHeDaoTao = tenHeDaoTao;
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
        hash += (idhedaotao != null ? idhedaotao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hedaotao)) {
            return false;
        }
        Hedaotao other = (Hedaotao) object;
        if ((this.idhedaotao == null && other.idhedaotao != null) || (this.idhedaotao != null && !this.idhedaotao.equals(other.idhedaotao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.av.pojo.Hedaotao[ idhedaotao=" + idhedaotao + " ]";
    }
    
}
