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
@Table(name = "monhoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Monhoc.findAll", query = "SELECT m FROM Monhoc m"),
    @NamedQuery(name = "Monhoc.findByIdMonHoc", query = "SELECT m FROM Monhoc m WHERE m.idMonHoc = :idMonHoc"),
    @NamedQuery(name = "Monhoc.findByTenMonHoc", query = "SELECT m FROM Monhoc m WHERE m.tenMonHoc = :tenMonHoc"),
    @NamedQuery(name = "Monhoc.findBySoTinChi", query = "SELECT m FROM Monhoc m WHERE m.soTinChi = :soTinChi")})
public class Monhoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMonHoc")
    private Integer idMonHoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tenMonHoc")
    private String tenMonHoc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "soTinChi")
    private int soTinChi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMonHoc")
    @JsonIgnore
    private Set<MonhocHocky> monhocHockySet;

    public Monhoc() {
    }

    public Monhoc(Integer idMonHoc) {
        this.idMonHoc = idMonHoc;
    }

    public Monhoc(Integer idMonHoc, String tenMonHoc, int soTinChi) {
        this.idMonHoc = idMonHoc;
        this.tenMonHoc = tenMonHoc;
        this.soTinChi = soTinChi;
    }

    public Integer getIdMonHoc() {
        return idMonHoc;
    }

    public void setIdMonHoc(Integer idMonHoc) {
        this.idMonHoc = idMonHoc;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    @XmlTransient
    public Set<MonhocHocky> getMonhocHockySet() {
        return monhocHockySet;
    }

    public void setMonhocHockySet(Set<MonhocHocky> monhocHockySet) {
        this.monhocHockySet = monhocHockySet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMonHoc != null ? idMonHoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Monhoc)) {
            return false;
        }
        Monhoc other = (Monhoc) object;
        if ((this.idMonHoc == null && other.idMonHoc != null) || (this.idMonHoc != null && !this.idMonHoc.equals(other.idMonHoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.av.pojo.Monhoc[ idMonHoc=" + idMonHoc + " ]";
    }
    
}
