/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.controllers;

import com.av.pojo.Cauhoidiendang;
import com.av.pojo.Traloidiendan;
import com.av.service.DienDanService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class ApiDienDanController {

    @Autowired
    public DienDanService diendanservice;

    @GetMapping("/cauhoi/")
    @CrossOrigin
    public ResponseEntity<List<Object>> list() {
        return new ResponseEntity<>(this.diendanservice.getCauHoiDienDan(), HttpStatus.OK);
    }

    @RequestMapping("/traloi/")
    @CrossOrigin
    public ResponseEntity<List<Object>> listtraloi(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.diendanservice.getTraloi(params), HttpStatus.OK);
    }

    @RequestMapping("/cauhoiid/")
    @CrossOrigin
    public ResponseEntity<Object> cauhoi(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.diendanservice.getCauHoi(params), HttpStatus.OK);
    }

    @PostMapping("/add-cauhoi/")
    @CrossOrigin
    public ResponseEntity<String> addCauHoi(@RequestBody Cauhoidiendang cauhoi) {
        this.diendanservice.addOrUpdateCauHoi(cauhoi);
        return new ResponseEntity<>(cauhoi.getNoiDungCauHoi(), HttpStatus.OK);
    }

    @PostMapping("/add-traloi/")
    @CrossOrigin
    public ResponseEntity<String> addTraLoi(@RequestBody Traloidiendan traloi) {
        this.diendanservice.addOrUpdateTraloi(traloi);
        return new ResponseEntity<>(traloi.getNoiDungTraLoi(), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete-cauhoi/")
    @CrossOrigin
    public ResponseEntity<String> deleteCauHoi(@RequestParam Map<String, String> params) {
        this.diendanservice.deleteCauHoi(params);
        return new ResponseEntity<>("Successfull", HttpStatus.OK);
    }
}

