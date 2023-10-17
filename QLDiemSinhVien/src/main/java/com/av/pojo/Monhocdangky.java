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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FPTSHOP
 */
@Entity
@Table(name = "monhocdangky")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Monhocdangky.findAll", query = "SELECT m FROM Monhocdangky m"),
    @NamedQuery(name = "Monhocdangky.findByIdMonHocDangKy", query = "SELECT m FROM Monhocdangky m WHERE m.idMonHocDangKy = :idMonHocDangKy"),
    @NamedQuery(name = "Monhocdangky.findByXepLoai", query = "SELECT m FROM Monhocdangky m WHERE m.xepLoai = :xepLoai"),
    @NamedQuery(name = "Monhocdangky.findByKhoaMon", query = "SELECT m FROM Monhocdangky m WHERE m.khoaMon = :khoaMon"),
    @NamedQuery(name = "Monhocdangky.findByThanhToan", query = "SELECT m FROM Monhocdangky m WHERE m.thanhToan = :thanhToan"),
    @NamedQuery(name = "Monhocdangky.findByTrangThai", query = "SELECT m FROM Monhocdangky m WHERE m.trangThai = :trangThai")})
public class Monhocdangky implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMonHocDangKy")
    private Integer idMonHocDangKy;
    @Size(max = 20)
    @Column(name = "xepLoai")
    private String xepLoai;
    @Column(name = "khoaMon")
    private Short khoaMon;
    @Column(name = "thanhToan")
    private Short thanhToan;
    @Column(name = "trangThai")
    private Short trangThai;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMonHoc")
    @JsonIgnore
    private Set<Diem> diemSet;
    @JoinColumn(name = "idMonHoc", referencedColumnName = "idMonHoc_HocKy")
    @ManyToOne(optional = false)
    private MonhocHocky idMonHoc;
    @JoinColumn(name = "idSinhVien", referencedColumnName = "idSinhVien")
    @ManyToOne(optional = false)
    private Sinhvien idSinhVien;

    public Monhocdangky() {
    }

    public Monhocdangky(Integer idMonHocDangKy) {
        this.idMonHocDangKy = idMonHocDangKy;
    }

    public Integer getIdMonHocDangKy() {
        return idMonHocDangKy;
    }

    public void setIdMonHocDangKy(Integer idMonHocDangKy) {
        this.idMonHocDangKy = idMonHocDangKy;
    }

    public String getXepLoai() {
        return xepLoai;
    }

    public void setXepLoai(String xepLoai) {
        this.xepLoai = xepLoai;
    }

    public Short getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Short trangThai) {
        this.trangThai = trangThai;
    }

    @XmlTransient
    public Set<Diem> getDiemSet() {
        return diemSet;
    }

    public void setDiemSet(Set<Diem> diemSet) {
        this.diemSet = diemSet;
    }

    public MonhocHocky getIdMonHoc() {
        return idMonHoc;
    }

    public void setIdMonHoc(MonhocHocky idMonHoc) {
        this.idMonHoc = idMonHoc;
    }

    public Sinhvien getIdSinhVien() {
        return idSinhVien;
    }

    public void setIdSinhVien(Sinhvien idSinhVien) {
        this.idSinhVien = idSinhVien;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMonHocDangKy != null ? idMonHocDangKy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Monhocdangky)) {
            return false;
        }
        Monhocdangky other = (Monhocdangky) object;
        if ((this.idMonHocDangKy == null && other.idMonHocDangKy != null) || (this.idMonHocDangKy != null && !this.idMonHocDangKy.equals(other.idMonHocDangKy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.av.pojo.Monhocdangky[ idMonHocDangKy=" + idMonHocDangKy + " ]";
    }

    /**
     * @return the khoaMon
     */
    public Short getKhoaMon() {
        return khoaMon;
    }

    /**
     * @param khoaMon the khoaMon to set
     */
    public void setKhoaMon(Short khoaMon) {
        this.khoaMon = khoaMon;
    }

    /**
     * @return the thanhToan
     */
    public Short getThanhToan() {
        return thanhToan;
    }

    /**
     * @param thanhToan the thanhToan to set
     */
    public void setThanhToan(Short thanhToan) {
        this.thanhToan = thanhToan;
    }
    
}
