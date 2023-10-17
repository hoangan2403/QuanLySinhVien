/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FPTSHOP
 */
@Entity
@Table(name = "giaovu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Giaovu.findAll", query = "SELECT g FROM Giaovu g"),
    @NamedQuery(name = "Giaovu.findByIdGiaoVu", query = "SELECT g FROM Giaovu g WHERE g.idGiaoVu = :idGiaoVu"),
    @NamedQuery(name = "Giaovu.findByTenGiaoVu", query = "SELECT g FROM Giaovu g WHERE g.tenGiaoVu = :tenGiaoVu"),
    @NamedQuery(name = "Giaovu.findByGioiTinh", query = "SELECT g FROM Giaovu g WHERE g.gioiTinh = :gioiTinh"),
    @NamedQuery(name = "Giaovu.findBySoDienThoai", query = "SELECT g FROM Giaovu g WHERE g.soDienThoai = :soDienThoai")})
public class Giaovu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGiaoVu")
    private Integer idGiaoVu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tenGiaoVu")
    private String tenGiaoVu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gioiTinh")
    private short gioiTinh;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "soDienThoai")
    private String soDienThoai;
    @JoinColumn(name = "idTaiKhoan", referencedColumnName = "idTaiKhoan")
    @OneToOne
    private Taikhoan idTaiKhoan;

    public Giaovu() {
    }

    public Giaovu(Integer idGiaoVu) {
        this.idGiaoVu = idGiaoVu;
    }

    public Giaovu(Integer idGiaoVu, String tenGiaoVu, short gioiTinh, String soDienThoai) {
        this.idGiaoVu = idGiaoVu;
        this.tenGiaoVu = tenGiaoVu;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
    }

    public Integer getIdGiaoVu() {
        return idGiaoVu;
    }

    public void setIdGiaoVu(Integer idGiaoVu) {
        this.idGiaoVu = idGiaoVu;
    }

    public String getTenGiaoVu() {
        return tenGiaoVu;
    }

    public void setTenGiaoVu(String tenGiaoVu) {
        this.tenGiaoVu = tenGiaoVu;
    }

    public short getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(short gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public Taikhoan getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(Taikhoan idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGiaoVu != null ? idGiaoVu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Giaovu)) {
            return false;
        }
        Giaovu other = (Giaovu) object;
        if ((this.idGiaoVu == null && other.idGiaoVu != null) || (this.idGiaoVu != null && !this.idGiaoVu.equals(other.idGiaoVu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.av.pojo.Giaovu[ idGiaoVu=" + idGiaoVu + " ]";
    }
    
}
