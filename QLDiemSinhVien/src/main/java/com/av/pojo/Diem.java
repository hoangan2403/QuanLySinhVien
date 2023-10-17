/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FPTSHOP
 */
@Entity
@Table(name = "diem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diem.findAll", query = "SELECT d FROM Diem d"),
    @NamedQuery(name = "Diem.findByIdDiem", query = "SELECT d FROM Diem d WHERE d.idDiem = :idDiem"),
    @NamedQuery(name = "Diem.findBySoDiem", query = "SELECT d FROM Diem d WHERE d.soDiem = :soDiem"),
    @NamedQuery(name = "Diem.findByKhoaDiem", query = "SELECT d FROM Diem d WHERE d.khoaDiem = :khoaDiem")})
public class Diem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDiem")
    private Integer idDiem;

    @JoinColumn(name = "tenDiem", referencedColumnName = "idLoaiDiem")
    @ManyToOne
    private Loaidiem tenDiem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "soDiem")
    private double soDiem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "khoaDiem")
    private short khoaDiem;
    @JoinColumn(name = "idMonHoc", referencedColumnName = "idMonHocDangKy")
    @ManyToOne(optional = false)
    
    private Monhocdangky idMonHoc;

    public Diem() {
    }

    public Diem(Integer idDiem) {
        this.idDiem = idDiem;
    }

    public Diem(Integer idDiem, double soDiem, short khoaDiem) {
        this.idDiem = idDiem;
        this.soDiem = soDiem;
        this.khoaDiem = khoaDiem;
    }

    public Integer getIdDiem() {
        return idDiem;
    }

    public void setIdDiem(Integer idDiem) {
        this.idDiem = idDiem;
    }



    public double getSoDiem() {
        return soDiem;
    }

    public void setSoDiem(double soDiem) {
        this.soDiem = soDiem;
    }

    public short getKhoaDiem() {
        return khoaDiem;
    }

    public void setKhoaDiem(short khoaDiem) {
        this.khoaDiem = khoaDiem;
    }

    public Monhocdangky getIdMonHoc() {
        return idMonHoc;
    }

    public void setIdMonHoc(Monhocdangky idMonHoc) {
        this.idMonHoc = idMonHoc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDiem != null ? idDiem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diem)) {
            return false;
        }
        Diem other = (Diem) object;
        if ((this.idDiem == null && other.idDiem != null) || (this.idDiem != null && !this.idDiem.equals(other.idDiem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.av.pojo.Diem[ idDiem=" + idDiem + " ]";
    }

    /**
     * @return the tenDiem
     */
    public Loaidiem getTenDiem() {
        return tenDiem;
    }

    /**
     * @param tenDiem the tenDiem to set
     */
    public void setTenDiem(Loaidiem tenDiem) {
        this.tenDiem = tenDiem;
    }
    
}
