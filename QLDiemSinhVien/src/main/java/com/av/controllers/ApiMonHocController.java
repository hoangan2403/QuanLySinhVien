/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.controllers;

import com.av.pojo.DiemMonHoc;
import com.av.pojo.Lophoc;
import com.av.pojo.Monhoc;
import com.av.pojo.MonhocHocky;
import com.av.pojo.Monhocdangky;

import com.av.service.DiemService;
import com.av.service.LopHocService;
import com.av.service.MonHocService;
import com.av.service.SinhVienService;
import java.util.List;
import java.util.Map;
import javax.ws.rs.POST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FPTSHOP
 */
@RestController
public class ApiMonHocController {
    
    @Autowired
    private MonHocService mhService;
    @Autowired
    private SinhVienService svService;
    @Autowired
    private DiemService diemService;
    @Autowired
    private LopHocService daotaoService;
    

    @GetMapping("/api/monhocgiangvien/")
    @CrossOrigin
    public ResponseEntity<List<MonhocHocky>> listMHSV(@RequestParam Map<String, String> params){
        return new ResponseEntity<>(this.mhService.getMonHocByGiangVien(params), HttpStatus.OK);
    }
    @GetMapping("/api/monhocgiangvienchuaday/")
    @CrossOrigin
    public ResponseEntity<List<MonhocHocky>> listMHSVCD(@RequestParam Map<String, String> params){
        return new ResponseEntity<>(this.mhService.getMonHocByGiangVienChuaDay(params), HttpStatus.OK);
    }
    @GetMapping("/api/monhocgiangviendaday/")
    @CrossOrigin
    public ResponseEntity<List<MonhocHocky>> listMHSVDD(@RequestParam Map<String, String> params){
        return new ResponseEntity<>(this.mhService.getMonHocByGiangVienDaDay(params), HttpStatus.OK);
    }
       
    @PostMapping("/api/monhocsinhvien/")
    @CrossOrigin
    public ResponseEntity<List<DiemMonHoc>> listSVMH(@RequestParam Map<String, String> params){
        return new ResponseEntity<>(this.svService.getSinhvienByMonHoc(params), HttpStatus.OK);
    }
    @PostMapping("/api/monhochocky/")
    @CrossOrigin
    public ResponseEntity<List<MonhocHocky>> listMHHK(@RequestParam Map<String, String> params){
        return new ResponseEntity<>(this.mhService.getMonHocHocKy(params), HttpStatus.OK);
    }
    
    @GetMapping("/api/monhocSVdangky/")
    @CrossOrigin
    public ResponseEntity<List<Monhocdangky>> listMHSVDK(@RequestParam Map<String, String> params){
        return new ResponseEntity<>(this.mhService.getMonHocSinhVienDangKy(params), HttpStatus.OK);
    }
    
    @PostMapping("/api/dangkymonhoc/")
    @CrossOrigin
    public ResponseEntity<String> DangkyMH(@RequestBody Monhocdangky monHoc, @RequestParam Map<String, String> params){
        if(this.diemService.dangKyMonHoc( monHoc, params)){
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Fail", HttpStatus.OK);
    }
    
    @DeleteMapping("/api/huydangkymonhoc/")
    @CrossOrigin
    public ResponseEntity<String> HuyDangkyMH( @RequestParam Map<String, String> params){
        if(this.diemService.huyDangKy(params)){
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("fail", HttpStatus.CREATED);
    }
    
    @GetMapping("/api/dslophoc/")
    @CrossOrigin
    public ResponseEntity<List<Lophoc>> DSLopHoc(@RequestParam Map<String, String> params){

       return new ResponseEntity<>(this.daotaoService.listLopHoc(params), HttpStatus.OK);
    }
    
}
