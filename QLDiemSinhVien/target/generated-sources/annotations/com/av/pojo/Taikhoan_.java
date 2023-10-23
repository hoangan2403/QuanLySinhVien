package com.av.pojo;

import com.av.pojo.Cauhoidiendang;
import com.av.pojo.Giangvien;
import com.av.pojo.Giaovu;
import com.av.pojo.Loaitaikhoan;
import com.av.pojo.Sinhvien;
import com.av.pojo.Traloidiendan;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-10-23T14:09:59")
@StaticMetamodel(Taikhoan.class)
public class Taikhoan_ { 

    public static volatile SingularAttribute<Taikhoan, String> tenTaiKhoan;
    public static volatile SingularAttribute<Taikhoan, String> image;
    public static volatile SingularAttribute<Taikhoan, String> matKhau;
    public static volatile SetAttribute<Taikhoan, Traloidiendan> traloidiendanSet;
    public static volatile SingularAttribute<Taikhoan, Integer> idTaiKhoan;
    public static volatile SingularAttribute<Taikhoan, Giaovu> giaovu;
    public static volatile SingularAttribute<Taikhoan, Sinhvien> sinhvien;
    public static volatile SingularAttribute<Taikhoan, Giangvien> giangvien;
    public static volatile SingularAttribute<Taikhoan, Loaitaikhoan> chucVu;
    public static volatile SetAttribute<Taikhoan, Cauhoidiendang> cauhoidiendangSet;

}