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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FPTSHOP
 */
@Entity
@Table(name = "loaidiem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Loaidiem.findAll", query = "SELECT l FROM Loaidiem l"),
    @NamedQuery(name = "Loaidiem.findByIdLoaiDiem", query = "SELECT l FROM Loaidiem l WHERE l.idLoaiDiem = :idLoaiDiem"),
    @NamedQuery(name = "Loaidiem.findByTenDiem", query = "SELECT l FROM Loaidiem l WHERE l.tenDiem = :tenDiem")})
public class Loaidiem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLoaiDiem")
    private Integer idLoaiDiem;
    @Size(max = 500)
    @Column(name = "tenDiem")
    private String tenDiem;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tenDiem")
    @JsonIgnore
    private Set<Diem> diemSet;

    public Loaidiem() {
    }

    public Loaidiem(Integer idLoaiDiem) {
        this.idLoaiDiem = idLoaiDiem;
    }

    public Integer getIdLoaiDiem() {
        return idLoaiDiem;
    }

    public void setIdLoaiDiem(Integer idLoaiDiem) {
        this.idLoaiDiem = idLoaiDiem;
    }

    public String getTenDiem() {
        return tenDiem;
    }

    public void setTenDiem(String tenDiem) {
        this.tenDiem = tenDiem;
    }

    @XmlTransient
    public Set<Diem> getDiemSet() {
        return diemSet;
    }

    public void setDiemSet(Set<Diem> diemSet) {
        this.diemSet = diemSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLoaiDiem != null ? idLoaiDiem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Loaidiem)) {
            return false;
        }
        Loaidiem other = (Loaidiem) object;
        if ((this.idLoaiDiem == null && other.idLoaiDiem != null) || (this.idLoaiDiem != null && !this.idLoaiDiem.equals(other.idLoaiDiem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.av.pojo.Loaidiem[ idLoaiDiem=" + idLoaiDiem + " ]";
    }
    
}
