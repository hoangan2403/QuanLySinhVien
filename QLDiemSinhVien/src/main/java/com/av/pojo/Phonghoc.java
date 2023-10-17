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
@Table(name = "phonghoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Phonghoc.findAll", query = "SELECT p FROM Phonghoc p"),
    @NamedQuery(name = "Phonghoc.findByIdPhongHoc", query = "SELECT p FROM Phonghoc p WHERE p.idPhongHoc = :idPhongHoc"),
    @NamedQuery(name = "Phonghoc.findByTenPhongHoc", query = "SELECT p FROM Phonghoc p WHERE p.tenPhongHoc = :tenPhongHoc")})
public class Phonghoc implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPhongHoc")
    private Integer idPhongHoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tenPhongHoc")
    private String tenPhongHoc;
    @OneToMany(mappedBy = "phongHoc")
    @JsonIgnore

    private Set<MonhocHocky> monhocHockySet;

    public Phonghoc() {
    }

    public Phonghoc(Integer idPhongHoc) {
        this.idPhongHoc = idPhongHoc;
    }

    public Phonghoc(Integer idPhongHoc, String tenPhongHoc) {
        this.idPhongHoc = idPhongHoc;
        this.tenPhongHoc = tenPhongHoc;
    }

    public Integer getIdPhongHoc() {
        return idPhongHoc;
    }

    public void setIdPhongHoc(Integer idPhongHoc) {
        this.idPhongHoc = idPhongHoc;
    }

    public String getTenPhongHoc() {
        return tenPhongHoc;
    }

    public void setTenPhongHoc(String tenPhongHoc) {
        this.tenPhongHoc = tenPhongHoc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPhongHoc != null ? idPhongHoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Phonghoc)) {
            return false;
        }
        Phonghoc other = (Phonghoc) object;
        if ((this.idPhongHoc == null && other.idPhongHoc != null) || (this.idPhongHoc != null && !this.idPhongHoc.equals(other.idPhongHoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.av.pojo.Phonghoc[ idPhongHoc=" + idPhongHoc + " ]";
    }



    @XmlTransient
    public Set<MonhocHocky> getMonhocHockySet() {
        return monhocHockySet;
    }

    public void setMonhocHockySet(Set<MonhocHocky> monhocHockySet) {
        this.monhocHockySet = monhocHockySet;

    }
    
}
