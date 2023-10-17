/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FPTSHOP
 */
@Entity
@Table(name = "hocky")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hocky.findAll", query = "SELECT h FROM Hocky h"),
    @NamedQuery(name = "Hocky.findByIdHocKy", query = "SELECT h FROM Hocky h WHERE h.idHocKy = :idHocKy"),
    @NamedQuery(name = "Hocky.findByNgayBatDau", query = "SELECT h FROM Hocky h WHERE h.ngayBatDau = :ngayBatDau"),
    @NamedQuery(name = "Hocky.findByNgayKetThuc", query = "SELECT h FROM Hocky h WHERE h.ngayKetThuc = :ngayKetThuc"),
    @NamedQuery(name = "Hocky.findByNgayDangKy", query = "SELECT h FROM Hocky h WHERE h.ngayDangKy = :ngayDangKy"),
    @NamedQuery(name = "Hocky.findByNgayHetHan", query = "SELECT h FROM Hocky h WHERE h.ngayHetHan = :ngayHetHan")})
public class Hocky implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHocKy")
    private Integer idHocKy;
    @JoinColumn(name = "tenHocKy", referencedColumnName = "idLoaiHocKy")
    @ManyToOne
    private Loaihocky tenHocKy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ngayBatDau")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayBatDau;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ngayKetThuc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayKetThuc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ngayDangKy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayDangKy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ngayHetHan")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayHetHan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHocky")
    @JsonIgnore
    private Set<MonhocHocky> monhocHockySet;
    @JoinColumn(name = "idLop", referencedColumnName = "idLopHoc")
    @ManyToOne(optional = false)
    private Lophoc idLop;

    public Hocky() {
    }

    public Hocky(Integer idHocKy) {
        this.idHocKy = idHocKy;
    }

    public Hocky(Integer idHocKy, Date ngayBatDau, Date ngayKetThuc, Date ngayDangKy, Date ngayHetHan) {
        this.idHocKy = idHocKy;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.ngayDangKy = ngayDangKy;
        this.ngayHetHan = ngayHetHan;
    }

    public Integer getIdHocKy() {
        return idHocKy;
    }

    public void setIdHocKy(Integer idHocKy) {
        this.idHocKy = idHocKy;
    }

    

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Date getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(Date ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    @XmlTransient
    public Set<MonhocHocky> getMonhocHockySet() {
        return monhocHockySet;
    }

    public void setMonhocHockySet(Set<MonhocHocky> monhocHockySet) {
        this.monhocHockySet = monhocHockySet;
    }

    public Lophoc getIdLop() {
        return idLop;
    }

    public void setIdLop(Lophoc idLop) {
        this.idLop = idLop;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHocKy != null ? idHocKy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hocky)) {
            return false;
        }
        Hocky other = (Hocky) object;
        if ((this.idHocKy == null && other.idHocKy != null) || (this.idHocKy != null && !this.idHocKy.equals(other.idHocKy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.av.pojo.Hocky[ idHocKy=" + idHocKy + " ]";
    }

    /**
     * @return the tenHocKy
     */
    public Loaihocky getTenHocKy() {
        return tenHocKy;
    }

    /**
     * @param tenHocKy the tenHocKy to set
     */
    public void setTenHocKy(Loaihocky tenHocKy) {
        this.tenHocKy = tenHocKy;
    }
    
}
