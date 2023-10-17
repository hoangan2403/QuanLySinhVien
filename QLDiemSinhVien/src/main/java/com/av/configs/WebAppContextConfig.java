/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av.configs;

import com.av.formatter.CauHoiFormatter;
import com.av.formatter.GiangVienFormatter;
import com.av.formatter.HeDaoTaoFormatter;
import com.av.formatter.HocKiFormatter;
import com.av.formatter.KhoaHocFormatter;
import com.av.formatter.LoaiHocKyFormatter;
import com.av.formatter.LopHocFormatter;
import com.av.formatter.MonHocFormatter;
import com.av.formatter.NghanhDaoTaoFormatter;
import com.av.formatter.PhongHocFormatter;
import com.av.formatter.SinhVienFormatter;
import com.av.formatter.TaiKhoanFormatter;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.format.FormatterRegistry;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author FPTSHOP
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.av.controllers",
    "com.av.repository",
    "com.av.service"
})

public class WebAppContextConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new CauHoiFormatter());
        registry.addFormatter(new TaiKhoanFormatter());
        registry.addFormatter(new LopHocFormatter());
        registry.addFormatter(new GiangVienFormatter());
        registry.addFormatter(new MonHocFormatter());
        registry.addFormatter(new SinhVienFormatter());
        registry.addFormatter(new NghanhDaoTaoFormatter());
        registry.addFormatter(new KhoaHocFormatter());
        registry.addFormatter(new HeDaoTaoFormatter());
        registry.addFormatter(new LoaiHocKyFormatter());
        registry.addFormatter(new HocKiFormatter());
        registry.addFormatter(new PhongHocFormatter());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/resources/js/");
    }
//    @Bean
//    public InternalResourceViewResolver internalResourceViewResolver() {
//        InternalResourceViewResolver r = new InternalResourceViewResolver();
//        r.setViewClass(JstlView.class);
//        r.setPrefix("/WEB-INF/pages/");
//        r.setSuffix(".jsp");
//
//        return r;
//    }

    @Bean
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver
                = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
                = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", "dhcvsbuew",
                        "api_key", "127245518483839",
                        "api_secret", "1CExekjHALzqnQGG7Hr-FoOWlk8",
                        "secure", true));
        return cloudinary;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("2051052150vi@ou.edu.vn");
        mailSender.setPassword("0985334013");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        mailSender.setJavaMailProperties(properties);

        return mailSender;

    }


}
