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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FPTSHOP
 */
@Entity
@Table(name = "traloidiendan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Traloidiendan.findAll", query = "SELECT t FROM Traloidiendan t"),
    @NamedQuery(name = "Traloidiendan.findByIdTraLoiDienDan", query = "SELECT t FROM Traloidiendan t WHERE t.idTraLoiDienDan = :idTraLoiDienDan"),
    @NamedQuery(name = "Traloidiendan.findByNoiDungTraLoi", query = "SELECT t FROM Traloidiendan t WHERE t.noiDungTraLoi = :noiDungTraLoi")})
public class Traloidiendan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTraLoiDienDan")
    private Integer idTraLoiDienDan;
    @Size(max = 400)
    @Column(name = "noiDungTraLoi")
    private String noiDungTraLoi;
    @JoinColumn(name = "idCauHoiDienDan", referencedColumnName = "idCauHoiDienDan")
    @ManyToOne
    private Cauhoidiendang idCauHoiDienDan;
    @JoinColumn(name = "idTaiKhoan", referencedColumnName = "idTaiKhoan")
    @ManyToOne
    private Taikhoan idTaiKhoan;

    public Traloidiendan() {
    }

    public Traloidiendan(Integer idTraLoiDienDan) {
        this.idTraLoiDienDan = idTraLoiDienDan;
    }

    public Integer getIdTraLoiDienDan() {
        return idTraLoiDienDan;
    }

    public void setIdTraLoiDienDan(Integer idTraLoiDienDan) {
        this.idTraLoiDienDan = idTraLoiDienDan;
    }

    public String getNoiDungTraLoi() {
        return noiDungTraLoi;
    }

    public void setNoiDungTraLoi(String noiDungTraLoi) {
        this.noiDungTraLoi = noiDungTraLoi;
    }

    public Cauhoidiendang getIdCauHoiDienDan() {
        return idCauHoiDienDan;
    }

    public void setIdCauHoiDienDan(Cauhoidiendang idCauHoiDienDan) {
        this.idCauHoiDienDan = idCauHoiDienDan;
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
        hash += (idTraLoiDienDan != null ? idTraLoiDienDan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Traloidiendan)) {
            return false;
        }
        Traloidiendan other = (Traloidiendan) object;
        if ((this.idTraLoiDienDan == null && other.idTraLoiDienDan != null) || (this.idTraLoiDienDan != null && !this.idTraLoiDienDan.equals(other.idTraLoiDienDan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.av.pojo.Traloidiendan[ idTraLoiDienDan=" + idTraLoiDienDan + " ]";
    }
    
}
