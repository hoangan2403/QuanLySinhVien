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
@Table(name = "khoa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Khoa.findAll", query = "SELECT k FROM Khoa k"),
    @NamedQuery(name = "Khoa.findByIdkhoa", query = "SELECT k FROM Khoa k WHERE k.idkhoa = :idkhoa"),
    @NamedQuery(name = "Khoa.findByTenKhoa", query = "SELECT k FROM Khoa k WHERE k.tenKhoa = :tenKhoa")})
public class Khoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idkhoa")
    private Integer idkhoa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tenKhoa")
    private String tenKhoa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKhoaHoc")
    @JsonIgnore
    private Set<Lophoc> lophocSet;

    public Khoa() {
    }

    public Khoa(Integer idkhoa) {
        this.idkhoa = idkhoa;
    }

    public Khoa(Integer idkhoa, String tenKhoa) {
        this.idkhoa = idkhoa;
        this.tenKhoa = tenKhoa;
    }

    public Integer getIdkhoa() {
        return idkhoa;
    }

    public void setIdkhoa(Integer idkhoa) {
        this.idkhoa = idkhoa;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
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
        hash += (idkhoa != null ? idkhoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Khoa)) {
            return false;
        }
        Khoa other = (Khoa) object;
        if ((this.idkhoa == null && other.idkhoa != null) || (this.idkhoa != null && !this.idkhoa.equals(other.idkhoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.av.pojo.Khoa[ idkhoa=" + idkhoa + " ]";
    }
    
}
