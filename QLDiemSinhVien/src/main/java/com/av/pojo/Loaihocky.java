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
@Table(name = "loaihocky")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Loaihocky.findAll", query = "SELECT l FROM Loaihocky l"),
    @NamedQuery(name = "Loaihocky.findByIdLoaiHocKy", query = "SELECT l FROM Loaihocky l WHERE l.idLoaiHocKy = :idLoaiHocKy"),
    @NamedQuery(name = "Loaihocky.findByTenHocKy", query = "SELECT l FROM Loaihocky l WHERE l.tenHocKy = :tenHocKy")})
public class Loaihocky implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLoaiHocKy")
    private Integer idLoaiHocKy;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tenHocKy")
    private String tenHocKy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tenHocKy")
    @JsonIgnore
    private Set<Hocky> hockySet;

    public Loaihocky() {
    }

    public Loaihocky(Integer idLoaiHocKy) {
        this.idLoaiHocKy = idLoaiHocKy;
    }

    public Loaihocky(Integer idLoaiHocKy, String tenHocKy) {
        this.idLoaiHocKy = idLoaiHocKy;
        this.tenHocKy = tenHocKy;
    }

    public Integer getIdLoaiHocKy() {
        return idLoaiHocKy;
    }

    public void setIdLoaiHocKy(Integer idLoaiHocKy) {
        this.idLoaiHocKy = idLoaiHocKy;
    }

    public String getTenHocKy() {
        return tenHocKy;
    }

    public void setTenHocKy(String tenHocKy) {
        this.tenHocKy = tenHocKy;
    }

    @XmlTransient
    public Set<Hocky> getHockySet() {
        return hockySet;
    }

    public void setHockySet(Set<Hocky> hockySet) {
        this.hockySet = hockySet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLoaiHocKy != null ? idLoaiHocKy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Loaihocky)) {
            return false;
        }
        Loaihocky other = (Loaihocky) object;
        if ((this.idLoaiHocKy == null && other.idLoaiHocKy != null) || (this.idLoaiHocKy != null && !this.idLoaiHocKy.equals(other.idLoaiHocKy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.av.pojo.Loaihocky[ idLoaiHocKy=" + idLoaiHocKy + " ]";
    }
    
}
