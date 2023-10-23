package com.av.pojo;

import com.av.pojo.Loaihocky;
import com.av.pojo.Lophoc;
import com.av.pojo.MonhocHocky;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-10-23T14:09:59")
@StaticMetamodel(Hocky.class)
public class Hocky_ { 

    public static volatile SingularAttribute<Hocky, Loaihocky> tenHocKy;
    public static volatile SingularAttribute<Hocky, Integer> idHocKy;
    public static volatile SingularAttribute<Hocky, Date> ngayHetHan;
    public static volatile SingularAttribute<Hocky, Date> ngayBatDau;
    public static volatile SingularAttribute<Hocky, Lophoc> idLop;
    public static volatile SetAttribute<Hocky, MonhocHocky> monhocHockySet;
    public static volatile SingularAttribute<Hocky, Date> ngayDangKy;
    public static volatile SingularAttribute<Hocky, Date> ngayKetThuc;

}