package com.av.pojo;

import com.av.pojo.Diem;
import com.av.pojo.MonhocHocky;
import com.av.pojo.Sinhvien;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-10-17T19:19:26")
@StaticMetamodel(Monhocdangky.class)
public class Monhocdangky_ { 

    public static volatile SingularAttribute<Monhocdangky, Short> thanhToan;
    public static volatile SingularAttribute<Monhocdangky, Short> khoaMon;
    public static volatile SingularAttribute<Monhocdangky, Integer> idMonHocDangKy;
    public static volatile SingularAttribute<Monhocdangky, MonhocHocky> idMonHoc;
    public static volatile SingularAttribute<Monhocdangky, Short> trangThai;
    public static volatile SingularAttribute<Monhocdangky, Sinhvien> idSinhVien;
    public static volatile SetAttribute<Monhocdangky, Diem> diemSet;
    public static volatile SingularAttribute<Monhocdangky, String> xepLoai;

}