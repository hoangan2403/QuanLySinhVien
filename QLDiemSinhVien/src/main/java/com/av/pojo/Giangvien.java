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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "giangvien")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Giangvien.findAll", query = "SELECT g FROM Giangvien g"),
    @NamedQuery(name = "Giangvien.findByIdGiangVien", query = "SELECT g FROM Giangvien g WHERE g.idGiangVien = :idGiangVien"),
    @NamedQuery(name = "Giangvien.findByHoTen", query = "SELECT g FROM Giangvien g WHERE g.hoTen = :hoTen"),
    @NamedQuery(name = "Giangvien.findByNgaySinh", query = "SELECT g FROM Giangvien g WHERE g.ngaySinh = :ngaySinh"),
    @NamedQuery(name = "Giangvien.findByGioiTinh", query = "SELECT g FROM Giangvien g WHERE g.gioiTinh = :gioiTinh"),
    @NamedQuery(name = "Giangvien.findByEmail", query = "SELECT g FROM Giangvien g WHERE g.email = :email"),
    @NamedQuery(name = "Giangvien.findByDiaChi", query = "SELECT g FROM Giangvien g WHERE g.diaChi = :diaChi"),
    @NamedQuery(name = "Giangvien.findBySoDienThoai", query = "SELECT g FROM Giangvien g WHERE g.soDienThoai = :soDienThoai")})
public class Giangvien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGiangVien")
    private Integer idGiangVien;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "hoTen")
    private String hoTen;
    @Column(name = "ngaySinh")
    @Temporal(TemporalType.DATE)
    private Date ngaySinh;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gioiTinh")
    private short gioiTinh;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "diaChi")
    private String diaChi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "soDienThoai")
    private String soDienThoai;
    @OneToMany(mappedBy = "idGiangVien")
    @JsonIgnore
    private Set<MonhocHocky> monhocHockySet;
    @JoinColumn(name = "idTaiKhoan", referencedColumnName = "idTaiKhoan")
    @OneToOne
    private Taikhoan idTaiKhoan;

    public Giangvien() {
    }

    public Giangvien(Integer idGiangVien) {
        this.idGiangVien = idGiangVien;
    }

    public Giangvien(Integer idGiangVien, String hoTen, short gioiTinh, String email, String diaChi, String soDienThoai) {
        this.idGiangVien = idGiangVien;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
    }

    public Integer getIdGiangVien() {
        return idGiangVien;
    }

    public void setIdGiangVien(Integer idGiangVien) {
        this.idGiangVien = idGiangVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public short getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(short gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    @XmlTransient
    public Set<MonhocHocky> getMonhocHockySet() {
        return monhocHockySet;
    }

    public void setMonhocHockySet(Set<MonhocHocky> monhocHockySet) {
        this.monhocHockySet = monhocHockySet;
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
        hash += (idGiangVien != null ? idGiangVien.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Giangvien)) {
            return false;
        }
        Giangvien other = (Giangvien) object;
        if ((this.idGiangVien == null && other.idGiangVien != null) || (this.idGiangVien != null && !this.idGiangVien.equals(other.idGiangVien))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.av.pojo.Giangvien[ idGiangVien=" + idGiangVien + " ]";
    }
    
}
