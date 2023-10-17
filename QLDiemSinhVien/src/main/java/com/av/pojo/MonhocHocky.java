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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FPTSHOP
 */
@Entity
@Table(name = "monhoc_hocky")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonhocHocky.findAll", query = "SELECT m FROM MonhocHocky m"),
    @NamedQuery(name = "MonhocHocky.findByIdMonHocHocKy", query = "SELECT m FROM MonhocHocky m WHERE m.idMonHocHocKy = :idMonHocHocKy"),
    @NamedQuery(name = "MonhocHocky.findBySoLuong", query = "SELECT m FROM MonhocHocky m WHERE m.soLuong = :soLuong"),
    @NamedQuery(name = "MonhocHocky.findBySoLuongConLai", query = "SELECT m FROM MonhocHocky m WHERE m.soLuongConLai = :soLuongConLai"),
    @NamedQuery(name = "MonhocHocky.findByNgayBatDau", query = "SELECT m FROM MonhocHocky m WHERE m.ngayBatDau = :ngayBatDau"),
    @NamedQuery(name = "MonhocHocky.findByNgayKetThuc", query = "SELECT m FROM MonhocHocky m WHERE m.ngayKetThuc = :ngayKetThuc")})
public class MonhocHocky implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMonHoc_HocKy")
    private Integer idMonHocHocKy;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "soLuong")
    private int soLuong;
    @Basic(optional = false)
    @Column(name = "soLuongConLai")
    private int soLuongConLai;
    @Basic(optional = false)
//    @NotNull

    @Column(name = "ngayBatDau")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayBatDau;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ngayKetThuc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayKetThuc;
    @JoinColumn(name = "idGiangVien", referencedColumnName = "idGiangVien")
    @ManyToOne
    private Giangvien idGiangVien;
    @JoinColumn(name = "idHocky", referencedColumnName = "idHocKy")
    @ManyToOne(optional = false)
    private Hocky idHocky;
    @JoinColumn(name = "idMonHoc", referencedColumnName = "idMonHoc")
    @ManyToOne(optional = false)
    private Monhoc idMonHoc;
    @JoinColumn(name = "phongHoc", referencedColumnName = "idPhongHoc")
    @ManyToOne
    private Phonghoc phongHoc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMonHoc")
    @JsonIgnore
    private Set<Monhocdangky> monhocdangkySet;
    @Transient
    private Monhoc[] listMonhocs;
    @Transient
    private Date[] listDateBatDau;
    @Transient
    private Date[] listDateKetThuc;
    @Transient
    private Integer[] listSoLuong;
    @Transient
    private Giangvien[] listGiangviens;
    @Transient
    private Phonghoc[] listPhonghocs;
    

    public MonhocHocky() {
    }

    public MonhocHocky(Integer idMonHocHocKy) {
        this.idMonHocHocKy = idMonHocHocKy;
    }

    public MonhocHocky(Integer idMonHocHocKy, int soLuong, Date ngayBatDau, Date ngayKetThuc) {
        this.idMonHocHocKy = idMonHocHocKy;
        this.soLuong = soLuong;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public Integer getIdMonHocHocKy() {
        return idMonHocHocKy;
    }

    public void setIdMonHocHocKy(Integer idMonHocHocKy) {
        this.idMonHocHocKy = idMonHocHocKy;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
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

    public Giangvien getIdGiangVien() {
        return idGiangVien;
    }

    public void setIdGiangVien(Giangvien idGiangVien) {
        this.idGiangVien = idGiangVien;
    }

    public Hocky getIdHocky() {
        return idHocky;
    }

    public void setIdHocky(Hocky idHocky) {
        this.idHocky = idHocky;
    }

    public Monhoc getIdMonHoc() {
        return idMonHoc;
    }

    public void setIdMonHoc(Monhoc idMonHoc) {
        this.idMonHoc = idMonHoc;
    }

    @XmlTransient
    public Set<Monhocdangky> getMonhocdangkySet() {
        return monhocdangkySet;
    }

    public void setMonhocdangkySet(Set<Monhocdangky> monhocdangkySet) {
        this.monhocdangkySet = monhocdangkySet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMonHocHocKy != null ? idMonHocHocKy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonhocHocky)) {
            return false;
        }
        MonhocHocky other = (MonhocHocky) object;
        if ((this.idMonHocHocKy == null && other.idMonHocHocKy != null) || (this.idMonHocHocKy != null && !this.idMonHocHocKy.equals(other.idMonHocHocKy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.av.pojo.MonhocHocky[ idMonHocHocKy=" + idMonHocHocKy + " ]";
    }


    /**
     * @return the soLuongConLai
     */
    public int getSoLuongConLai() {
        return soLuongConLai;
    }

    /**
     * @param soLuongConLai the soLuongConLai to set
     */
    public void setSoLuongConLai(int soLuongConLai) {
        this.soLuongConLai = soLuongConLai;

    }

    /**
     * @return the listDateBatDau
     */
    public Date[] getListDateBatDau() {
        return listDateBatDau;
    }

    /**
     * @param listDateBatDau the listDateBatDau to set
     */
    public void setListDateBatDau(Date[] listDateBatDau) {
        this.listDateBatDau = listDateBatDau;
    }

    /**
     * @return the listDateKetThuc
     */
    public Date[] getListDateKetThuc() {
        return listDateKetThuc;
    }

    /**
     * @param listDateKetThuc the listDateKetThuc to set
     */
    public void setListDateKetThuc(Date[] listDateKetThuc) {
        this.listDateKetThuc = listDateKetThuc;
    }

    /**
     * @return the listSoLuong
     */
    public Integer[] getListSoLuong() {
        return listSoLuong;
    }

    /**
     * @param listSoLuong the listSoLuong to set
     */
    public void setListSoLuong(Integer[] listSoLuong) {
        this.listSoLuong = listSoLuong;
    }

    /**
     * @return the listGiangviens
     */
    public Giangvien[] getListGiangviens() {
        return listGiangviens;
    }

    /**
     * @param listGiangviens the listGiangviens to set
     */
    public void setListGiangviens(Giangvien[] listGiangviens) {
        this.listGiangviens = listGiangviens;

    }

    /**
     * @return the phongHoc
     */
    public Phonghoc getPhongHoc() {
        return phongHoc;
    }

    /**
     * @param phongHoc the phongHoc to set
     */
    public void setPhongHoc(Phonghoc phongHoc) {
        this.phongHoc = phongHoc;
    }

    /**

     * @param phongHoc the phongHoc to set
     */


//     * @return the lisPhonghocs
//     */
    public Phonghoc[] getListPhonghocs() {
        return listPhonghocs;
    }

    /**
     * @param lisPhonghocs the lisPhonghocs to set
     */
    public void setListPhonghocs(Phonghoc[] lisPhonghocs) {
        this.listPhonghocs = lisPhonghocs;
    }

    /**
     * @return the listMonhocs
     */
    public Monhoc[] getListMonhocs() {
        return listMonhocs;
    }

    /**
     * @param listMonhocs the listMonhocs to set
     */
    public void setListMonhocs(Monhoc[] listMonhocs) {
        this.listMonhocs = listMonhocs;
    }

    

}
