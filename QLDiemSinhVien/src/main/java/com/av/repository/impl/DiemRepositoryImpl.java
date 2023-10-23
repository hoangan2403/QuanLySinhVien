/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.repository.impl;

import com.av.pojo.Diem;
import com.av.pojo.DiemMonHoc;
import com.av.pojo.DiemMonHocComparator;
import com.av.pojo.Loaidiem;
import com.av.pojo.MonhocHocky;
import com.av.pojo.Monhocdangky;
import com.av.pojo.Sinhvien;
import com.av.repository.DiemRepository;
import com.av.repository.MonHocRepository;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class DiemRepositoryImpl implements DiemRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private MonHocRepository monHocRepository;

    @Override
    public List<Object> getListDiemTrungBinh(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root<Diem> rDiem = q.from(Diem.class);
        Root<Monhocdangky> rMonhocDangKy = q.from(Monhocdangky.class);
        Root<MonhocHocky> rMonHocHocKy = q.from(MonhocHocky.class);
        List<Predicate> predicates = new ArrayList<>();
        String sinhvienId = params.get("SinhVienId");
        predicates.add(b.equal(rDiem.get("idMonHoc"), rMonhocDangKy.get("idMonHocDangKy")));
        predicates.add(b.equal(rMonhocDangKy.get("idSinhVien"), Integer.parseInt(sinhvienId)));
        predicates.add(b.equal(rDiem.get("tenDiem"), 6));
        predicates.add(b.equal(rMonHocHocKy.get("idMonHocHocKy"), rMonhocDangKy.get("idMonHoc")));
        Expression<Double> averageDiemTrungBinh = b.avg(rDiem.get("soDiem"));
        Expression<Double> averageDiemTrungBinh4 = b.prod(b.avg(rDiem.get("soDiem")), 0.4);
        q.multiselect(
                rMonHocHocKy.get("idHocky").get("tenHocKy").get("tenHocKy"),
                averageDiemTrungBinh,
                averageDiemTrungBinh4
        )
                .where(predicates.toArray(new Predicate[0]));
//                .groupBy(rMonHocHocKy.get("idMonHocHocKy").get("idHocKY"));

        Query query = session.createQuery(q);
        try {
            return query.getResultList();
        } catch (NoResultException | NonUniqueResultException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public double getDiemTrungBinh2(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Double> q = b.createQuery(Double.class);
        Root<Diem> rDiem = q.from(Diem.class);
        Root<Monhocdangky> rMonHocDangKy = q.from(Monhocdangky.class);
        String sinhvienId = params.get("SinhVienId");
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rDiem.get("idMonHoc"), rMonHocDangKy.get("idMonHocDangKy")));
        predicates.add(b.equal(rMonHocDangKy.get("idSinhVien"), Integer.parseInt(sinhvienId)));
        predicates.add(b.equal(rDiem.get("tenDiem"), 6));
        q.select(b.avg(rDiem.get("soDiem")))
                .where(predicates.toArray(Predicate[]::new));

        Query query = s.createQuery(q);

        DecimalFormat decimalFormat = new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.US));
        try {
            Double averageValue = (Double) query.getSingleResult();
            if (averageValue != null) {
                averageValue = Double.valueOf(decimalFormat.format(averageValue));
            } else {
                averageValue = 0.0;
            }
            return averageValue;
        } catch (NoResultException e) {
            return 0.0; // Không tìm thấy sinh viên với ID tương ứng
        } catch (NonUniqueResultException e) {
            // Xử lý nếu có nhiều hơn một kết quả trả về (nếu ID không duy nhất)
            return 0.0;
        }
    }

    
    @Override
    public double getDiemTrungBinhHe4(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Double> q = b.createQuery(Double.class);
        Root<Diem> rDiem = q.from(Diem.class);
        Root<Monhocdangky> rMonHocDangKy = q.from(Monhocdangky.class);
        String sinhvienId = params.get("SinhVienId");
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rDiem.get("idMonHoc"), rMonHocDangKy.get("idMonHocDangKy")));
        predicates.add(b.equal(rMonHocDangKy.get("idSinhVien"), Integer.parseInt(sinhvienId)));
        predicates.add(b.equal(rDiem.get("tenDiem"), 6));
        q.select(b.avg(rDiem.get("soDiem")))
                .where(predicates.toArray(Predicate[]::new));

        Query query = s.createQuery(q);

        DecimalFormat decimalFormat = new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.US));
        try {
            Double averageValue = (Double) query.getSingleResult();
            if (averageValue != null) {
                averageValue = averageValue * 0.4;
                averageValue = Double.valueOf(decimalFormat.format(averageValue));
            } else {
                averageValue = 0.0;
            }
            return averageValue;
        } catch (NoResultException e) {
            return 0.0; // Không tìm thấy sinh viên với ID tương ứng
        } catch (NonUniqueResultException e) {
            // Xử lý nếu có nhiều hơn một kết quả trả về (nếu ID không duy nhất)
            return 0.0;
        }
    }
//

    @Override
    public List<DiemMonHoc> getListDiemDangHoc(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Diem> q = b.createQuery(Diem.class);
        
        List<Monhocdangky> monHocList = this.getListMonHocDangKy(params);
        List<DiemMonHoc> monHocDiemList = new ArrayList<>();

        for (Monhocdangky monHoc : monHocList) {
            if (monHoc.getKhoaMon() == 0) {
                DiemMonHoc monHocDiem = new DiemMonHoc(monHoc);
                // Lấy danh sách điểm cho môn học cụ thể
                List<Diem> diemList = this.getListDiemByIdMonHocDangKy(monHoc.getIdMonHocDangKy());
                // Thêm điểm vào danh sách MonHocDiem
                for (Diem diem : diemList) {
                    monHocDiem.addDiem(diem); // Diem.getDiem() là phương thức lấy giá trị điểm
                }
                // Thêm MonHocDiem vào danh sách chung
                monHocDiemList.add(monHocDiem);
            }
        }
        try {
            Collections.sort(monHocDiemList, new DiemMonHocComparator());
            return monHocDiemList;
        } catch (NoResultException | NonUniqueResultException ex) {

            ex.printStackTrace();
            return Collections.emptyList();
        }

    }

    @Override
    public List<DiemMonHoc> getListDiemDaHoc(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Diem> q = b.createQuery(Diem.class);
        List<Monhocdangky> monHocList = this.getListMonHocDangKy(params);
        List<DiemMonHoc> monHocDiemList = new ArrayList<>();

        for (Monhocdangky monHoc : monHocList) {
            if (monHoc.getKhoaMon() == 1) {
                DiemMonHoc monHocDiem = new DiemMonHoc(monHoc);
                // Lấy danh sách điểm cho môn học cụ thể
                List<Diem> diemList = this.getListDiemByIdMonHocDangKy(monHoc.getIdMonHocDangKy());
                // Thêm điểm vào danh sách MonHocDiem
                for (Diem diem : diemList) {
                    monHocDiem.addDiem(diem); // Diem.getDiem() là phương thức lấy giá trị điểm
                }
                // Thêm MonHocDiem vào danh sách chung
                monHocDiemList.add(monHocDiem);
            }

        }
        try {
            Collections.sort(monHocDiemList, new DiemMonHocComparator());
            return monHocDiemList;
        } catch (NoResultException | NonUniqueResultException ex) {

            ex.printStackTrace();
            return Collections.emptyList();
        }

    }
//

    @Override
    public Diem addDiem(Monhocdangky monhoc, List<Diem> diem, DiemMonHoc diemMonHoc) {
        Session session = this.factory.getObject().getCurrentSession();

        Loaidiem loaiDiemKt1 = this.getLoaiDiemKT1();
        Loaidiem loaiDiemKt2 = this.getLoaiDiemKT2();
        Loaidiem loaiDiemKt3 = this.getLoaiDiemKT3();
        short khoaDiem = 0;
        List<Diem> diemToBeDeleted = new ArrayList<>();
        for (Diem diemm : diem) {
            if (diemm.getTenDiem().getIdLoaiDiem() == 3 || diemm.getTenDiem().getIdLoaiDiem() == 4 || diemm.getTenDiem().getIdLoaiDiem() == 5) {
                diemToBeDeleted.add(diemm);
            }
        }
        for (Diem diemm : diemToBeDeleted) {
            session.delete(diemm);
            diem.remove(diemm);
        }

        if (diemMonHoc.getDiemKT1() != -1) {
            Diem diemTk1 = new Diem();
            diemTk1.setIdMonHoc(monhoc);
            diemTk1.setTenDiem(loaiDiemKt1);
            diemTk1.setSoDiem(diemMonHoc.getDiemKT1());
            diemTk1.setKhoaDiem(khoaDiem);
            session.save(diemTk1);
        }
        if (diemMonHoc.getDiemKT2() != -1) {

            Diem diemTk2 = new Diem();
            diemTk2.setIdMonHoc(monhoc);
            diemTk2.setTenDiem(loaiDiemKt2);
            diemTk2.setSoDiem(diemMonHoc.getDiemKT2());
            diemTk2.setKhoaDiem(khoaDiem);
            session.save(diemTk2);
        }
        if (diemMonHoc.getDiemKT3() != -1) {
            Diem diemTk3 = new Diem();
            diemTk3.setIdMonHoc(monhoc);
            diemTk3.setTenDiem(loaiDiemKt3);
            diemTk3.setSoDiem(diemMonHoc.getDiemKT3());
            diemTk3.setKhoaDiem(khoaDiem);
            session.save(diemTk3);
        }
        session.update(monhoc);
        for (Diem diemm : diem) {
            session.update(diemm);
        }

        return null;
    }
//

    @Override
    public Monhocdangky getDiemByIdMonHoc(int idMonHoc, int idSinhVien) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root<Monhocdangky> rDiem = q.from(Monhocdangky.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rDiem.get("idSinhVien"), idSinhVien));
        predicates.add(b.equal(rDiem.get("idMonHoc"), idMonHoc));
        q.select(b.array(rDiem))
                .where(predicates.toArray(Predicate[]::new));
        Query query = session.createQuery(q);

        try {
            Monhocdangky diem = (Monhocdangky) query.getSingleResult();

            // Kiểm tra nếu kết quả trả về là null
            if (diem == null) {
                // Không tìm thấy bản ghi nào thỏa mãn điều kiện tìm kiếm
                return null;
            }

            return diem;
        } catch (NoResultException | NonUniqueResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Diem> setDiemByCSV(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        String idMonHoc = params.get("idMonHoc");
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Diem> q = b.createQuery(Diem.class);
        Root<Diem> rDiem = q.from(Diem.class);

        q.select(rDiem)
                .where(b.equal(rDiem.get("idMonHoc"), Integer.parseInt(idMonHoc)));
        Query query = session.createQuery(q);
        return (List<Diem>) query.getResultList();
    }

//    // lay diem theo sinh vien
//    @Override
//    public List<Diem> getDiemBySinhVien(int idSinhVien) {
//        Session s = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<Diem[]> q = b.createQuery(Diem[].class);
//
//        Root r = q.from(Diem.class);
//        q.select(r);
//        q.where(b.equal(r.get("idSinhVien").get("idSinhVien"), idSinhVien));
//        Query query = s.createQuery(q);
//        return query.getResultList();
//    }
//
//    // xoa diem theo sinh vien
//    @Override
//    public boolean deleteDiemBySinhVien(int idSinhVien) {
//        Session s = this.factory.getObject().getCurrentSession();
//        List<Diem> diems = this.getDiemBySinhVien(idSinhVien);
//        try {
//            for (Diem diem : diems) {
//                s.delete(diem);
//            }
//            return true;
//        } catch (HibernateException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
    @Override
    public Monhocdangky getMonHocDangKyById(int idDiem) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Monhocdangky> q = b.createQuery(Monhocdangky.class);
        Root<Monhocdangky> rDiem = q.from(Monhocdangky.class);
        q.select(rDiem)
                .where(b.equal(rDiem.get("idMonHocDangKy"), idDiem));
        Query query = session.createQuery(q);
        return (Monhocdangky) query.getSingleResult();
    }

    @Override
    public List<Monhocdangky> getDiemByidGiangVien(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root rSinhVien = q.from(Sinhvien.class);
        Root rDiem = q.from(Monhocdangky.class);
        Root rMonHoc = q.from(MonhocHocky.class);
        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String giangVien = params.get("idGiangVien");
            if (giangVien != null) {
                predicates.add(b.equal(rMonHoc.get("idGiangVien"), Integer.parseInt(giangVien)));
                predicates.add(b.equal(rDiem.get("idMonHoc"), rMonHoc.get("idMonHocHocKy")));
            }
            String tenSinhVien = params.get("tenSinhVien");
            if (tenSinhVien != null) {
                try {
                    int parsedIdSinhVien = Integer.parseInt(tenSinhVien);
                    predicates.add(b.equal(rDiem.get("idSinhVien"), parsedIdSinhVien));
                } catch (NumberFormatException e) {

                    predicates.add(b.equal(rDiem.get("idSinhVien"), rSinhVien.get("idSinhVien")));
                    predicates.add(b.like(rSinhVien.get("hoTen"), String.format("%%%s%%", tenSinhVien)));
                }
            }

            q.groupBy(rDiem.get("idMonHocDangKy"));
            q.select(rDiem).where(predicates.toArray(new Predicate[0]));
            Query query = s.createQuery(q);
            return query.getResultList();
        }
        return null;
    }

    @Override
    public boolean khoaDiem(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Monhocdangky[]> q = b.createQuery(Monhocdangky[].class);
        Root rDiem = q.from(Monhocdangky.class);
        if (params != null) {
            String idMonHoc = params.get("idMonHoc");
            q.select(rDiem).where(b.equal(rDiem.get("idMonHoc"), Integer.parseInt(idMonHoc)));
            Query query = s.createQuery(q);
            List<Monhocdangky> DiemSV = query.getResultList();
            SimpleMailMessage message = new SimpleMailMessage();
            short khoaDiem = 1;
            for (Monhocdangky monhoc : DiemSV) {
                List<Diem> diemList = this.getListDiemByIdMonHocDangKy(monhoc.getIdMonHocDangKy());
                // Thêm điểm vào danh sách MonHocDiem
                for (Diem diem : diemList) {
                    // Diem.getDiem() là phương thức lấy giá trị điểm
                    diem.setKhoaDiem(khoaDiem);
                    s.update(diem);
                }
                monhoc.setKhoaMon(khoaDiem);
                s.update(monhoc);
//                message.setTo(monhoc.getIdSinhVien().getEmail().toString());
//                message.setSubject("Thong bao diem");
//                message.setText("Diem cua mon " + monhoc.getIdMonHoc().getIdMonHoc().getTenMonHoc().toString() + " co ma mon " + monhoc.getIdMonHoc().getIdMonHoc().toString() + " Đã có");
//                emailSender.send(message);
            }
            return true;
        }
        return false;
    }

//    @Override
//    public boolean deleteDiem(int idMonHoc, int idSinhVien) {
//        Session s = this.factory.getObject().getCurrentSession();
//        Diem d = this.getDiemByIdMonHoc(idMonHoc, idSinhVien);
//        try {
//            if(d != null)
//                s.delete(d);
//            return true;
//        } catch (HibernateException e) {
//            return false;
//        }
//    }
    @Override
    public List<Diem> getListDiemByIdMonHocDangKy(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root<Diem> rDiem = q.from(Diem.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rDiem.get("idMonHoc").get("idMonHocDangKy"), id));
        q.select(b.array(rDiem))
                .where(predicates.toArray(Predicate[]::new));
        Query query = session.createQuery(q);
        try {
            return query.getResultList();
        } catch (NoResultException | NonUniqueResultException ex) {

            ex.printStackTrace();
            return Collections.emptyList();
        }

    }

    @Override
    public List<Monhocdangky> getListMonHocDangKy(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Monhocdangky[]> q = b.createQuery(Monhocdangky[].class);
        String sinhvienId = params.get("SinhVienId");
        Root rDiem = q.from(Monhocdangky.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rDiem.get("idSinhVien"), Integer.parseInt(sinhvienId)));
        q.select(rDiem)
                .where(predicates.toArray(Predicate[]::new));
        Query query = session.createQuery(q);
        try {
            return query.getResultList();
        } catch (NoResultException | NonUniqueResultException ex) {

            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Loaidiem getLoaiDiemKT1() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Loaidiem[]> q = b.createQuery(Loaidiem[].class);
        Root rDiem = q.from(Loaidiem.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rDiem.get("idLoaiDiem"), 3));
        q.select(rDiem)
                .where(predicates.toArray(Predicate[]::new));
        Query query = session.createQuery(q);
        try {
            return (Loaidiem) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {

            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Loaidiem getLoaiDiemKT2() {

        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Loaidiem[]> q = b.createQuery(Loaidiem[].class);
        Root rDiem = q.from(Loaidiem.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rDiem.get("idLoaiDiem"), 4));
        q.select(rDiem)
                .where(predicates.toArray(Predicate[]::new));
        Query query = session.createQuery(q);
        try {
            return (Loaidiem) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {

            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Loaidiem getLoaiDiemKT3() {

        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Loaidiem[]> q = b.createQuery(Loaidiem[].class);
        Root rDiem = q.from(Loaidiem.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rDiem.get("idLoaiDiem"), 5));
        q.select(rDiem)
                .where(predicates.toArray(Predicate[]::new));
        Query query = session.createQuery(q);
        try {
            return (Loaidiem) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {

            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Loaidiem getLoaiDiemGK() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Loaidiem[]> q = b.createQuery(Loaidiem[].class);
        Root rDiem = q.from(Loaidiem.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rDiem.get("idLoaiDiem"), 1));
        q.select(rDiem)
                .where(predicates.toArray(Predicate[]::new));
        Query query = session.createQuery(q);
        try {
            return (Loaidiem) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {

            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Loaidiem getLoaiDiemCK() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Loaidiem[]> q = b.createQuery(Loaidiem[].class);
        Root rDiem = q.from(Loaidiem.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rDiem.get("idLoaiDiem"), 2));
        q.select(rDiem)
                .where(predicates.toArray(Predicate[]::new));
        Query query = session.createQuery(q);
        try {
            return (Loaidiem) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {

            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Loaidiem getLoaiDiemTB() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Loaidiem[]> q = b.createQuery(Loaidiem[].class);
        Root rDiem = q.from(Loaidiem.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rDiem.get("idLoaiDiem"), 6));
        q.select(rDiem)
                .where(predicates.toArray(Predicate[]::new));
        Query query = session.createQuery(q);
        try {
            return (Loaidiem) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {

            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public DiemMonHoc getDiemMonHocByIdDiem(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();

        Monhocdangky monhoc = this.getMonHocDangKyById(id);
        DiemMonHoc monHocDiem = new DiemMonHoc(monhoc);
        // Lấy danh sách điểm cho môn học cụ thể
        List<Diem> diemList = this.getListDiemByIdMonHocDangKy(monhoc.getIdMonHocDangKy());
        // Thêm điểm vào danh sách MonHocDiem
        for (Diem diem : diemList) {
            monHocDiem.addDiem(diem); // Diem.getDiem() là phương thức lấy giá trị điểm
        }

        return monHocDiem;
    }

    @Override
    public Monhocdangky dangKyMonHoc(Monhocdangky monHoc, MonhocHocky monHocHocKy) {
        Session session = this.factory.getObject().getCurrentSession();

        Loaidiem LoaiGK = this.getLoaiDiemGK();
        Loaidiem LoaiCK = this.getLoaiDiemCK();
        Loaidiem LoaiTB = this.getLoaiDiemTB();
        Diem GK = new Diem();
        Short a = 0;
        GK.setIdMonHoc(monHoc);
        GK.setKhoaDiem(a);
        GK.setTenDiem(LoaiGK);

        Diem CK = new Diem();
        CK.setKhoaDiem(a);
        CK.setIdMonHoc(monHoc);
        CK.setTenDiem(LoaiCK);

        Diem TB = new Diem();
        TB.setIdMonHoc(monHoc);
        TB.setKhoaDiem(a);
        TB.setTenDiem(LoaiTB);
        TB.setSoDiem(0);

        session.save(monHoc);
        session.save(GK);
        session.save(CK);
        session.save(TB);
        session.update(monHocHocKy);
        return null;
    }

    @Override
    public Monhocdangky huyDangKy(Monhocdangky monHoc) {
        Session session = this.factory.getObject().getCurrentSession();
        List<Diem> Diems = this.getListDiemByIdMonHocDangKy(monHoc.getIdMonHocDangKy());
        Monhocdangky monHoctest = this.getMonHocDangKyById(monHoc.getIdMonHocDangKy());
        MonhocHocky monHocHocKy = this.monHocRepository.getMonHocHocKyDate(monHoc.getIdMonHoc().getIdMonHocHocKy());
        int soLuong = monHocHocKy.getSoLuongConLai() + 1;
        monHocHocKy.setSoLuongConLai(soLuong);
        for (Diem diem : Diems) {
            session.delete(diem);
        }
        session.delete(monHoctest);
        session.update(monHocHocKy);
        return null;
    }

    @Override
    public boolean addCotDiem(int idMonHoc) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Monhocdangky[]> q = b.createQuery(Monhocdangky[].class);;
        Root rMonHoc = q.from(Monhocdangky.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rMonHoc.get("idMonHoc"), idMonHoc));
        q.select(rMonHoc)
                .where(predicates.toArray(Predicate[]::new));
        Query query = session.createQuery(q);
        List<Monhocdangky> monHocs = query.getResultList();
        Loaidiem LoaiKT1 = this.getLoaiDiemKT1();
        Loaidiem LoaiKT2 = this.getLoaiDiemKT2();
        Loaidiem LoaiKT3 = this.getLoaiDiemKT3();
        for (Monhocdangky monHoc : monHocs) {
            boolean KT1 = true;
            boolean KT2 = true;
            boolean KT3 = true;
            short a = 0;
            List<Diem> diems = this.getListDiemByIdMonHocDangKy(monHoc.getIdMonHocDangKy());
            for (Diem diem : diems) {
                if (diem.getTenDiem().getIdLoaiDiem() == 5) {
                    KT3 = false;
                    KT2 = false;
                    KT1 = false;
                    return false;
                } else if (diem.getTenDiem().getIdLoaiDiem() == 4) {
                    KT2 = false;
                    KT1 = false;
                } else if (diem.getTenDiem().getIdLoaiDiem() == 3) {
                    KT1 = false;
                }

            }
            if (KT1) {
                Diem Kt1 = new Diem();
                Kt1.setIdMonHoc(monHoc);
                Kt1.setKhoaDiem(a);
                Kt1.setTenDiem(LoaiKT1);
                session.save(Kt1);
            } else if (KT2) {
                Diem Kt2 = new Diem();
                Kt2.setIdMonHoc(monHoc);
                Kt2.setKhoaDiem(a);
                Kt2.setTenDiem(LoaiKT2);
                session.save(Kt2);
            } else if (KT3) {
                Diem Kt3 = new Diem();
                Kt3.setIdMonHoc(monHoc);
                Kt3.setKhoaDiem(a);
                Kt3.setTenDiem(LoaiKT3);
                session.save(Kt3);
            }

        }
        return true;
    }

    @Override
    public boolean deleteCotDiem(int idMonHoc) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Monhocdangky[]> q = b.createQuery(Monhocdangky[].class);
        Root rMonHoc = q.from(Monhocdangky.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rMonHoc.get("idMonHoc"), idMonHoc));
        q.select(rMonHoc)
                .where(predicates.toArray(Predicate[]::new));
        Query query = session.createQuery(q);
        List<Monhocdangky> monHocs = query.getResultList();
        for (Monhocdangky monHoc : monHocs) {
            boolean KT1 = false;
            boolean KT2 = false;
            boolean KT3 = false;

            Diem diem1 = null, diem2 = null, diem3 = null;
            List<Diem> diems = this.getListDiemByIdMonHocDangKy(monHoc.getIdMonHocDangKy());
            for (Diem diem : diems) {
                if (diem.getTenDiem().getIdLoaiDiem() == 5) {
                    diem3 = diem;
                    KT3 = true;
                } else if (diem.getTenDiem().getIdLoaiDiem() == 4) {
                    diem2 = diem;
                    KT2 = true;
                } else if (diem.getTenDiem().getIdLoaiDiem() == 3) {
                    diem1 = diem;
                    KT1 = true;
                }

            }
            if (KT3) {
                session.delete(diem3);
            } else if (KT2) {
                session.delete(diem2);
            } else if (KT1) {
                session.delete(diem1);
            } else {
                return false;
            }

        }

        return true;
    }

    @Override
    public boolean setDiemTB(int idMonHoc) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Monhocdangky[]> q = b.createQuery(Monhocdangky[].class);;
        Root rMonHoc = q.from(Monhocdangky.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rMonHoc.get("idMonHoc"), idMonHoc));
        q.select(rMonHoc)
                .where(predicates.toArray(Predicate[]::new));
        Query query = session.createQuery(q);
        List<Monhocdangky> monHocs = query.getResultList();
        for (Monhocdangky monHoc : monHocs) {
            boolean KT1 = false;
            boolean KT2 = false;
            boolean KT3 = false;
            double DiemTB = 0.0;
            Diem GK = null, CK = null, Kt1 = null, Kt2 = null, Kt3 = null, TB = null;
            List<Diem> diems = this.getListDiemByIdMonHocDangKy(monHoc.getIdMonHocDangKy());
            for (Diem diem : diems) {
                if (diem.getTenDiem().getIdLoaiDiem() == 5) {
                    Kt3 = diem;
                    KT3 = true;
                } else if (diem.getTenDiem().getIdLoaiDiem() == 4) {
                    Kt2 = diem;
                    KT2 = true;
                } else if (diem.getTenDiem().getIdLoaiDiem() == 3) {
                    Kt1 = diem;
                    KT1 = true;
                } else if (diem.getTenDiem().getIdLoaiDiem() == 2) {
                    CK = diem;
                } else if (diem.getTenDiem().getIdLoaiDiem() == 1) {
                    GK = diem;
                } else if (diem.getTenDiem().getIdLoaiDiem() == 6) {
                    TB = diem;
                }
            }

            if (KT3) {
                DiemTB = GK.getSoDiem() * 0.2 + (Kt1.getSoDiem() + Kt2.getSoDiem() + Kt3.getSoDiem()) * 0.1 + CK.getSoDiem() * 0.5;
            } else if (KT2) {
                DiemTB = GK.getSoDiem() * 0.3 + (Kt1.getSoDiem() + Kt2.getSoDiem()) * 0.1 + CK.getSoDiem() * 0.5;
            } else if (KT1) {
                DiemTB = GK.getSoDiem() * 0.4 + (Kt1.getSoDiem()) * 0.1 + CK.getSoDiem() * 0.5;
            } else {
                DiemTB = GK.getSoDiem() * 0.5 + CK.getSoDiem() * 0.5;
            }
            TB.setSoDiem(DiemTB);
            session.save(TB);
        }
        return true;
    }

}
