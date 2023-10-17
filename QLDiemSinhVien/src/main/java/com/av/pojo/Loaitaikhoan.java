/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
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
@Table(name = "loaitaikhoan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Loaitaikhoan.findAll", query = "SELECT l FROM Loaitaikhoan l"),
    @NamedQuery(name = "Loaitaikhoan.findByIdloaitaikhoan", query = "SELECT l FROM Loaitaikhoan l WHERE l.idloaitaikhoan = :idloaitaikhoan"),
    @NamedQuery(name = "Loaitaikhoan.findByTenloaitaikhoan", query = "SELECT l FROM Loaitaikhoan l WHERE l.tenloaitaikhoan = :tenloaitaikhoan")})
public class Loaitaikhoan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idloaitaikhoan")
    private Integer idloaitaikhoan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "tenloaitaikhoan")
    private String tenloaitaikhoan;
    @OneToMany(mappedBy = "chucVu")
    @JsonIgnore
    private Set<Taikhoan> taikhoanSet;

    public Loaitaikhoan() {
    }

    public Loaitaikhoan(Integer idloaitaikhoan) {
        this.idloaitaikhoan = idloaitaikhoan;
    }

    public Loaitaikhoan(Integer idloaitaikhoan, String tenloaitaikhoan) {
        this.idloaitaikhoan = idloaitaikhoan;
        this.tenloaitaikhoan = tenloaitaikhoan;
    }

    public Integer getIdloaitaikhoan() {
        return idloaitaikhoan;
    }

    public void setIdloaitaikhoan(Integer idloaitaikhoan) {
        this.idloaitaikhoan = idloaitaikhoan;
    }

    public String getTenloaitaikhoan() {
        return tenloaitaikhoan;
    }

    public void setTenloaitaikhoan(String tenloaitaikhoan) {
        this.tenloaitaikhoan = tenloaitaikhoan;
    }

    @XmlTransient
    public Set<Taikhoan> getTaikhoanSet() {
        return taikhoanSet;
    }

    public void setTaikhoanSet(Set<Taikhoan> taikhoanSet) {
        this.taikhoanSet = taikhoanSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idloaitaikhoan != null ? idloaitaikhoan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Loaitaikhoan)) {
            return false;
        }
        Loaitaikhoan other = (Loaitaikhoan) object;
        if ((this.idloaitaikhoan == null && other.idloaitaikhoan != null) || (this.idloaitaikhoan != null && !this.idloaitaikhoan.equals(other.idloaitaikhoan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.av.pojo.Loaitaikhoan[ idloaitaikhoan=" + idloaitaikhoan + " ]";
    }
    
}
