/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.service.impl;

import com.av.pojo.Diem;
import com.av.pojo.DiemMonHoc;
import com.av.pojo.Loaidiem;
import com.av.pojo.MonhocHocky;
import com.av.pojo.Monhocdangky;
import com.av.repository.DiemRepository;
import com.av.repository.MonHocRepository;
import com.av.service.DiemService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Service
public class DiemServiceImpl implements DiemService {

    @Autowired
    private DiemRepository diemRepository;
    @Autowired
    private MonHocRepository monHocRepository;
//
//
//
//
//

    @Override
    public List<Object> getListDiemTrungBinh(Map<String, String> params) {
        return diemRepository.getListDiemTrungBinh(params);
    }
//

    @Override
    public double getDiemTrungBinh2(Map<String, String> params) {
        return diemRepository.getDiemTrungBinh2(params);
    }

    @Override
    public double getDiemTrungBinhHe4(Map<String, String> params) {
        return diemRepository.getDiemTrungBinhHe4(params);
    }
//

    @Override
    public List<DiemMonHoc> getListDiemDangHoc(Map<String, String> params) {
        return diemRepository.getListDiemDangHoc(params);
    }

    @Override
    public List<DiemMonHoc> getListDiemDaHoc(Map<String, String> params) {
        return this.diemRepository.getListDiemDaHoc(params);
    }

    @Override
    public DiemMonHoc addDiem(Map<String, String> params) {
        int idMonHocDangKy = Integer.parseInt(params.get("idMonHocDangKy"));
        Double DiemGK = Double.parseDouble(params.get("DiemGK"));
        Double DiemCK =  Double.parseDouble(params.get("DiemCK"));
        String KT1 = params.get("DiemKT1");
        String KT2 = params.get("DiemKT2");
        String KT3 = params.get("DiemKT3");
        Double DiemKT1 = -1.0, DiemKT2 = -1.0, DiemKT3 = -1.0;
        if (!KT1.isEmpty()) {
            DiemKT1 =  Double.parseDouble(KT1);
        }
        if (!KT2.isEmpty()) {
            DiemKT2 =  Double.parseDouble(KT2);
        }
        if (!KT3.isEmpty()) {
            DiemKT3 =  Double.parseDouble(KT3);
        }
        DiemMonHoc diem1 = new DiemMonHoc(idMonHocDangKy, DiemGK, DiemCK, DiemKT1, DiemKT2, DiemKT3);
        Monhocdangky monhoc = this.diemRepository.getMonHocDangKyById(idMonHocDangKy);
        List<Diem> diem = this.diemRepository.getListDiemByIdMonHocDangKy(idMonHocDangKy);
        if (monhoc.getKhoaMon() == 0) {
            Double diemTB = 0.0;
            Double heSoGK = 0.5;
            Double diemKT = 0.0;
            String xepLoai;
            Short trangThai;

            if (diem1.getDiemKT1() >= 0) {
                heSoGK -= 0.1;
                diemKT = diemKT + diem1.getDiemKT1();
            }
            if (diem1.getDiemKT2() >= 0) {
                heSoGK -= 0.1;
                diemKT = diemKT + diem1.getDiemKT2();
            }
            if (diem1.getDiemKT3() >= 0) {
                heSoGK -= 0.1;
                diemKT = diemKT + diem1.getDiemKT3();
            }
            diemTB = (diem1.getDiemGK() * heSoGK) + (diemKT * 0.1) + (diem1.getDiemCK() * 0.5);
            // Tính trạng thái dựa trên điểm trung bình
            DecimalFormat decimalFormat = new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.US));
            diemTB = Double.valueOf(decimalFormat.format(diemTB));
            if (diemTB >= 5) {
                trangThai = 1;
            } else {
                trangThai = 0;
            }
            if (diemTB >= 9) {
                xepLoai = "Giỏi";
            } else if (diemTB >= 6.5) {
                xepLoai = "Khá";
            } else if (diemTB >= 5) {
                xepLoai = "Trung Bình";
            } else {
                xepLoai = "Yếu";
            }
            for (Diem diemm : diem) {
                if (diemm.getTenDiem().getIdLoaiDiem() == 6) {
                    diemm.setSoDiem(diemTB);
                } else if (diemm.getTenDiem().getIdLoaiDiem() == 1) {
                    diemm.setSoDiem(diem1.getDiemGK());
                } else if (diemm.getTenDiem().getIdLoaiDiem() == 2) {
                    diemm.setSoDiem(diem1.getDiemCK());
                }
            }
            monhoc.setTrangThai(trangThai);
            monhoc.setXepLoai(xepLoai);

            this.diemRepository.addDiem(monhoc, diem, diem1);
            return diem1;
        }
        return null;

    }

    @Override
    public String setDiemByCSV(Map<String, String> params, MultipartFile file) {

        if (!file.isEmpty()) {
            String idMonHoc = params.get("idMonHoc");
            try (CSVParser parser = CSVParser.parse(file.getInputStream(), StandardCharsets.UTF_8, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
                for (CSVRecord record : parser) {
                    String idSinhVien = record.get("idSinhVien");
                    String diemGiuaKy = record.get("diemGiuaKy");
                    String diemCuoiKy = record.get("diemCuoiKy");
                    String diemKT1 = record.get("diemTK1");
                    String diemKT2 = record.get("diemTK2");
                    String diemKT3 = record.get("diemTK3");
                    Double DiemTK1 = Double.parseDouble(diemKT1);
                    Double DiemTK2 = Double.parseDouble(diemKT2);
                    Double DiemTK3 = Double.parseDouble(diemKT3);
                    if (diemKT1 == null) {
                        DiemTK1 = -1.0;
                    }
                    if (diemKT2 == null) {
                        DiemTK2 = -1.0;
                    }
                    if (diemKT3 == null) {
                        DiemTK3 = -1.0;
                    }
                    Monhocdangky monHoc = this.diemRepository.getDiemByIdMonHoc(Integer.parseInt(idMonHoc), Integer.parseInt(idSinhVien));
                    if (monHoc != null && monHoc.getKhoaMon() == 0) {
                        DiemMonHoc diem1 = new DiemMonHoc(monHoc.getIdMonHocDangKy(), Double.parseDouble(diemGiuaKy), Double.parseDouble(diemCuoiKy), DiemTK1, DiemTK2, DiemTK3);
                        List<Diem> diem = this.diemRepository.getListDiemByIdMonHocDangKy(monHoc.getIdMonHocDangKy());
                        Double diemTB = 0.0;
                        String xepLoai;
                        Short trangThai;
                        if (diem1.getDiemKT1() == -1) {
                            diemTB = (diem1.getDiemGK() + diem1.getDiemCK()) * 0.5;
                        } else if (diem1.getDiemKT2() == -1) {
                            diemTB = (diem1.getDiemGK() * 0.4 + diem1.getDiemKT1() * 0.1 + diem1.getDiemCK() * 0.5);
                        } else if (diem1.getDiemKT3() == -1) {
                            diemTB = (diem1.getDiemGK() * 0.3 + diem1.getDiemKT1() * 0.1 + diem1.getDiemKT2() * 0.1 + diem1.getDiemCK() * 0.5);
                        } else {
                            diemTB = (diem1.getDiemGK() * 0.2 + (diem1.getDiemKT1() + diem1.getDiemKT2() + diem1.getDiemKT3()) * 0.1 + diem1.getDiemCK() * 0.5);
                        }
                        // Tính trạng thái dựa trên điểm trung bình
                        DecimalFormat decimalFormat = new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.US));
                        diemTB = Double.valueOf(decimalFormat.format(diemTB));
                        if (diemTB >= 5) {
                            trangThai = 1;
                        } else {
                            trangThai = 0;
                        }
                        if (diemTB >= 9) {
                            xepLoai = "Giỏi";
                        } else if (diemTB >= 6.5) {
                            xepLoai = "Khá";
                        } else if (diemTB >= 5) {
                            xepLoai = "Trung Bình";
                        } else {
                            xepLoai = "Yếu";
                        }
                        for (Diem diemm : diem) {
                            if (diemm.getTenDiem().getIdLoaiDiem() == 6) {
                                diemm.setSoDiem(diemTB);
                            } else if (diemm.getTenDiem().getIdLoaiDiem() == 1) {
                                diemm.setSoDiem(diem1.getDiemGK());
                            } else if (diemm.getTenDiem().getIdLoaiDiem() == 2) {
                                diemm.setSoDiem(diem1.getDiemCK());
                            }
                        }
                        monHoc.setTrangThai(trangThai);
                        monHoc.setXepLoai(xepLoai);
                        this.diemRepository.addDiem(monHoc, diem, diem1);
                    }
                }

                return "oke";
            } catch (IOException ex) {
                Logger.getLogger(DiemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
        return "oke";
    }

//    @Override
//    public Diem getDiemByIdMonHoc(int idMonHoc, int idSinhVien
//    ) {
//
//        return this.diemRepository.getDiemByIdMonHoc(idMonHoc, idSinhVien);
//    }
    @Override
    public DiemMonHoc getDiemByIdDiem(Map<String, String> params) {
        String idDiem = params.get("idMonHocDangKy");
        DiemMonHoc diem = this.diemRepository.getDiemMonHocByIdDiem(Integer.parseInt(idDiem));
        return diem;
    }

    @Override
    public List<Monhocdangky> getDiemByidGiangVien(Map<String, String> params) {
        return this.diemRepository.getDiemByidGiangVien(params);
    }
//

    @Override
    public boolean khoaDiem(Map<String, String> params) {
        return this.diemRepository.khoaDiem(params);
    }
//
//    @Override
//    public boolean deleteDiem(int idMonHoc, int idSinhVien) {
//        return this.diemRepository.deleteDiem(idMonHoc, idSinhVien);
//    }

    @Override
    public List<Monhocdangky> getListMonHocDangKy(Map<String, String> params) {
        return this.diemRepository.getListMonHocDangKy(params);
    }

    @Override
    public List<Diem> getListDiemByIdMonHocDangKy(int id) {
        return this.diemRepository.getListDiemByIdMonHocDangKy(id);
    }

    @Override
    public Boolean dangKyMonHoc(Monhocdangky monHoc, Map<String, String> params) {
        int IdMonHoc = Integer.parseInt(params.get("IdMonHoc"));
        int IdSinhVien = Integer.parseInt(params.get("IdSinhVien"));
        Monhocdangky monHocDK = this.diemRepository.getDiemByIdMonHoc(IdMonHoc, IdSinhVien);
        MonhocHocky monHocHocKy = this.monHocRepository.getMonHocHocKyDate(IdMonHoc);
        if (monHocHocKy == null || monHocHocKy.getSoLuongConLai() <= 0) {
            return false;
        }
        if (monHocDK != null) {
            return false;
        }
        int soLuong = monHocHocKy.getSoLuongConLai() - 1;
        monHocHocKy.setSoLuongConLai(soLuong);
        Short a = 0;
        monHoc.setKhoaMon(a);
        monHoc.setTrangThai(a);
        monHoc.setThanhToan(a);
        this.diemRepository.dangKyMonHoc(monHoc, monHocHocKy);
        return true;

    }

    @Override
    public Boolean huyDangKy(Map<String, String> params) {
        int IdMonHoc = Integer.parseInt(params.get("IdMonHoc"));
        int IdSinhVien = Integer.parseInt(params.get("IdSinhVien"));
        Monhocdangky monHocDK = this.diemRepository.getDiemByIdMonHoc(IdMonHoc, IdSinhVien);
        MonhocHocky monHocHocKy = this.monHocRepository.getMonHocHocKyDate(IdMonHoc);
        if (monHocHocKy == null) {
            return false;
        }
        if (monHocDK != null) {

            this.diemRepository.huyDangKy(monHocDK);
            return true;
        }
        return false;
    }
    @Override
    public boolean addCotDiem(Map<String, String> params) {
        String idMonHoc = params.get("idMonHoc");
        return this.diemRepository.addCotDiem(Integer.parseInt(idMonHoc));
    }

    @Override
    public boolean deleteCotDiem(Map<String, String> params) {
        String idMonHoc = params.get("idMonHoc");
        return this.diemRepository.deleteCotDiem(Integer.parseInt(idMonHoc));
    }

    @Override
    public boolean setDiemTB(Map<String, String> params) {
        String idMonHoc = params.get("idMonHoc");      
        return this.diemRepository.setDiemTB(Integer.parseInt(idMonHoc));
    }
}
