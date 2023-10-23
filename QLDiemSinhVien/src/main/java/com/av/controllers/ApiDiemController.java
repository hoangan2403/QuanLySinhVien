/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.controllers;

import com.av.pojo.Diem;
import com.av.pojo.DiemMonHoc;
import com.av.pojo.Monhocdangky;
import com.av.service.DiemService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class ApiDiemController {

    @Autowired
    private DiemService diemService;
//
    @RequestMapping("/DSDiemSVHocKy/")
    @CrossOrigin
    public ResponseEntity<List<Object>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.diemService.getListDiemTrungBinh(params), HttpStatus.OK);
    }
//
    @RequestMapping("/TrungBinhDiem/")
    @CrossOrigin
    public ResponseEntity<Double> DiemtrungbinhSv(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.diemService.getDiemTrungBinh2(params), HttpStatus.OK);
    }

    @RequestMapping("/TrungBinhDiemHe4/")
    @CrossOrigin
    public ResponseEntity<Double> DiemtrungbinhSvHe4(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.diemService.getDiemTrungBinhHe4(params), HttpStatus.OK);
    }
//
    @RequestMapping("/DanhSachDiem/")
    @CrossOrigin
    public ResponseEntity<List<DiemMonHoc>> DanhSachDiem(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.diemService.getListDiemDangHoc(params), HttpStatus.OK);
    }

    @RequestMapping("/DanhSachDiemDaHoc/")
    @CrossOrigin
    public ResponseEntity<List<DiemMonHoc>> DanhSachDiemDaHoc(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.diemService.getListDiemDaHoc(params), HttpStatus.OK);
    }
    @PostMapping("/add-diem/")
    @CrossOrigin
    public ResponseEntity<String> addTraLoi(@RequestParam Map<String, String> params) {
        this.diemService.addDiem(params);
//       
        return new ResponseEntity<>("Successfull", HttpStatus.OK);
    }

    @PostMapping(path = "/add-List-diem/", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<String> listDiem(@RequestParam Map<String, String> params, @RequestPart MultipartFile file) {
        this.diemService.setDiemByCSV(params, file);
        return new ResponseEntity<>(this.diemService.setDiemByCSV(params, file), HttpStatus.OK);
    }
//
    @PostMapping("/diem-idDiem/")
    @CrossOrigin
    public ResponseEntity<DiemMonHoc> diemId(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.diemService.getDiemByIdDiem(params), HttpStatus.OK);
    }
     @PostMapping("/diemSV-idSV/")
    @CrossOrigin
    public ResponseEntity<List<Monhocdangky>> DiemSingVienByIdGV(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.diemService.getDiemByidGiangVien(params), HttpStatus.OK);
    }
    
    @PostMapping("/KhoaDiem/")
    @CrossOrigin
    public ResponseEntity<String> KhoaDiemByIdMonHoc(@RequestParam Map<String, String> params) {
        if(this.diemService.khoaDiem(params)){
            return new ResponseEntity<>("Thành Công", HttpStatus.OK);
            
        }
        return new ResponseEntity<>("Lỗi", HttpStatus.OK);
    }
    @PostMapping("/addCotDiem_SV/")
    @CrossOrigin
    public ResponseEntity<String> addCotDiemSv(@RequestParam Map<String, String> params) {
        if(diemService.addCotDiem(params)){
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Fail", HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteCotDiem_SV/")
    @CrossOrigin
    public ResponseEntity<String> deleteCotDiemSv(@RequestParam Map<String, String> params) {
        if(diemService.deleteCotDiem(params)){
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Fail", HttpStatus.CREATED);
    }
    @PostMapping("/TinhDiemTB_SV/")
    @CrossOrigin
    public ResponseEntity<String> tinhDiemTBSV(@RequestParam Map<String, String> params) {
        if(diemService.setDiemTB(params)){
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Fail", HttpStatus.CREATED);
    }
    
}
