-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: studentdb
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cauhoidiendang`
--

DROP TABLE IF EXISTS `cauhoidiendang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cauhoidiendang` (
  `idCauHoiDienDan` int NOT NULL AUTO_INCREMENT,
  `noiDungCauHoi` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `idTaiKhoan` int NOT NULL,
  `ngayTao` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  PRIMARY KEY (`idCauHoiDienDan`),
  KEY `idTaiKhoan_idx` (`idTaiKhoan`) /*!80000 INVISIBLE */,
  CONSTRAINT `idTaiKhoan` FOREIGN KEY (`idTaiKhoan`) REFERENCES `taikhoan` (`idTaiKhoan`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cauhoidiendang`
--

LOCK TABLES `cauhoidiendang` WRITE;
/*!40000 ALTER TABLE `cauhoidiendang` DISABLE KEYS */;
INSERT INTO `cauhoidiendang` VALUES (56,'Hôm nay trường có lễ không ạ....',54,'2023-10-05 18:27'),(57,'Sự khác biệt giữa phần mềm và ứng dụng web?',56,'2023-10-05 18:27');
/*!40000 ALTER TABLE `cauhoidiendang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diem`
--

DROP TABLE IF EXISTS `diem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diem` (
  `idDiem` int NOT NULL AUTO_INCREMENT,
  `tenDiem` int NOT NULL,
  `soDiem` double DEFAULT NULL,
  `idMonHoc` int NOT NULL,
  `khoaDiem` tinyint NOT NULL,
  PRIMARY KEY (`idDiem`),
  KEY `diem_monhoc_idx` (`idMonHoc`),
  KEY `loaiDiem_idx` (`tenDiem`),
  CONSTRAINT `diem_monhoc` FOREIGN KEY (`idMonHoc`) REFERENCES `monhocdangky` (`idMonHocDangKy`),
  CONSTRAINT `loaiDiem` FOREIGN KEY (`tenDiem`) REFERENCES `loaidiem` (`idLoaiDiem`)
) ENGINE=InnoDB AUTO_INCREMENT=408 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diem`
--

LOCK TABLES `diem` WRITE;
/*!40000 ALTER TABLE `diem` DISABLE KEYS */;
INSERT INTO `diem` VALUES (316,1,9,55,0),(317,2,9,55,0),(318,6,6.3,55,0),(319,1,9,56,0),(320,2,9,56,0),(321,6,6.3,56,0),(322,1,9,57,0),(323,2,9,57,0),(324,6,6.3,57,0),(325,1,9,58,0),(326,2,9,58,0),(327,6,6.3,58,0),(328,1,9,59,0),(329,2,9,59,0),(330,6,6.3,59,0),(331,1,9,60,0),(332,2,9,60,0),(333,6,6.3,60,1),(334,1,9,61,1),(335,2,9,61,1),(336,6,6.3,61,1),(337,1,9,62,0),(338,2,9,62,0),(339,6,9,62,0),(340,1,9,63,0),(341,2,9,63,0),(342,6,9,63,0),(343,1,9,64,0),(344,2,9,64,0),(345,6,9,64,0),(346,1,9,65,0),(347,2,9,65,0),(348,6,9,65,0),(349,1,9,66,0),(350,2,9,66,0),(351,6,9,66,0),(352,1,9,67,0),(353,2,9,67,0),(354,6,9,67,0),(378,3,0,55,0),(379,3,0,56,0),(380,3,0,57,0),(381,3,0,58,0),(382,3,0,59,0),(383,3,0,60,0),(384,3,0,61,0),(385,4,0,55,0),(386,4,0,56,0),(387,4,0,57,0),(388,4,0,58,0),(389,4,0,59,0),(390,4,0,60,0),(391,4,0,61,0),(392,5,0,55,0),(393,5,0,56,0),(394,5,0,57,0),(395,5,0,58,0),(396,5,0,59,0),(397,5,0,60,0),(398,5,0,61,0),(405,1,0,73,0),(406,2,0,73,0),(407,6,0,73,0);
/*!40000 ALTER TABLE `diem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giangvien`
--

DROP TABLE IF EXISTS `giangvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giangvien` (
  `idGiangVien` int NOT NULL AUTO_INCREMENT,
  `hoTen` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `ngaySinh` date DEFAULT NULL,
  `gioiTinh` tinyint NOT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `diaChi` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `soDienThoai` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `idTaiKhoan` int DEFAULT NULL,
  PRIMARY KEY (`idGiangVien`),
  UNIQUE KEY `idTaiKhoan_UNIQUE` (`idTaiKhoan`),
  KEY `id_idx` (`idTaiKhoan`),
  CONSTRAINT `id` FOREIGN KEY (`idTaiKhoan`) REFERENCES `taikhoan` (`idTaiKhoan`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giangvien`
--

LOCK TABLES `giangvien` WRITE;
/*!40000 ALTER TABLE `giangvien` DISABLE KEYS */;
INSERT INTO `giangvien` VALUES (19,'Dương Hữu Thành','1990-03-07',1,'huuthanh@gmail.com','37/46 Phan Văn Trị, P12, Gò Vấp','0377221375',54),(20,'Phan Thị Dương','1985-11-12',0,'duong@gmail.com','450 Lê ai, Tân Kỳ, Tân Phú','0255715246',NULL),(21,'Phạm Hoàng Thiên Ân','1995-07-09',0,'thienan@gmail.com','235 Gò Dầu, Tân Quý, tân Phú','0776655789',62),(22,'Phạm Hoàng Gia Khang','1989-03-04',1,'giakhoang@gmail.com','205 Năm Kỳ Khởi Nghĩa, P5, Quận 3','0724556785',55);
/*!40000 ALTER TABLE `giangvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giaovu`
--

DROP TABLE IF EXISTS `giaovu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giaovu` (
  `idGiaoVu` int NOT NULL AUTO_INCREMENT,
  `tenGiaoVu` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `gioiTinh` tinyint NOT NULL,
  `soDienThoai` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `idTaiKhoan` int DEFAULT NULL,
  PRIMARY KEY (`idGiaoVu`),
  UNIQUE KEY `idTaiKhoan_UNIQUE` (`idTaiKhoan`),
  KEY `id_idx` (`idTaiKhoan`),
  CONSTRAINT `idTaiKhoanGiaoVu` FOREIGN KEY (`idTaiKhoan`) REFERENCES `taikhoan` (`idTaiKhoan`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giaovu`
--

LOCK TABLES `giaovu` WRITE;
/*!40000 ALTER TABLE `giaovu` DISABLE KEYS */;
INSERT INTO `giaovu` VALUES (4,'Phạm Hoàng Ân',1,'0397769522',52),(5,'Phan Yến Vi',0,'0377765521',53);
/*!40000 ALTER TABLE `giaovu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hedaotao`
--

DROP TABLE IF EXISTS `hedaotao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hedaotao` (
  `idhedaotao` int NOT NULL AUTO_INCREMENT,
  `tenHeDaoTao` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`idhedaotao`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hedaotao`
--

LOCK TABLES `hedaotao` WRITE;
/*!40000 ALTER TABLE `hedaotao` DISABLE KEYS */;
INSERT INTO `hedaotao` VALUES (2,'Đại Trà'),(3,'Chất Lượng Cao');
/*!40000 ALTER TABLE `hedaotao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hocky`
--

DROP TABLE IF EXISTS `hocky`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hocky` (
  `idHocKy` int NOT NULL AUTO_INCREMENT,
  `tenHocKy` int NOT NULL,
  `ngayBatDau` datetime NOT NULL,
  `ngayKetThuc` datetime NOT NULL,
  `idLop` int NOT NULL,
  `ngayDangKy` datetime NOT NULL,
  `ngayHetHan` datetime NOT NULL,
  PRIMARY KEY (`idHocKy`),
  KEY `hocky_lophoc_idx` (`idLop`),
  KEY `hocKy_loaiHocKY_idx` (`tenHocKy`),
  CONSTRAINT `hocKy_loaiHocKY` FOREIGN KEY (`tenHocKy`) REFERENCES `loaihocky` (`idLoaiHocKy`),
  CONSTRAINT `hocky_lophoc` FOREIGN KEY (`idLop`) REFERENCES `lophoc` (`idLopHoc`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hocky`
--

LOCK TABLES `hocky` WRITE;
/*!40000 ALTER TABLE `hocky` DISABLE KEYS */;
INSERT INTO `hocky` VALUES (3,2,'2024-01-30 00:00:00','2024-04-30 00:00:00',9,'2023-10-18 00:00:00','2023-10-23 00:00:00'),(4,1,'2023-10-18 00:00:00','2024-01-20 00:00:00',9,'2023-10-10 00:00:00','2023-10-15 00:00:00'),(5,1,'2023-10-18 00:00:00','2024-01-20 00:00:00',10,'2023-10-10 00:00:00','2023-10-15 00:00:00');
/*!40000 ALTER TABLE `hocky` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khoa`
--

DROP TABLE IF EXISTS `khoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khoa` (
  `idkhoa` int NOT NULL AUTO_INCREMENT,
  `tenKhoa` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`idkhoa`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khoa`
--

LOCK TABLES `khoa` WRITE;
/*!40000 ALTER TABLE `khoa` DISABLE KEYS */;
INSERT INTO `khoa` VALUES (1,'2020-2024'),(2,'2021-2025');
/*!40000 ALTER TABLE `khoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khoadaotao`
--

DROP TABLE IF EXISTS `khoadaotao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khoadaotao` (
  `idKhoaDaoTao` int NOT NULL AUTO_INCREMENT,
  `tenKhoaDaoTao` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`idKhoaDaoTao`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khoadaotao`
--

LOCK TABLES `khoadaotao` WRITE;
/*!40000 ALTER TABLE `khoadaotao` DISABLE KEYS */;
INSERT INTO `khoadaotao` VALUES (1,'Công Nghệ Thông Tin'),(2,'Kinh Tế'),(4,'Ngôn Ngữ'),(5,'Thể Dục');
/*!40000 ALTER TABLE `khoadaotao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaidiem`
--

DROP TABLE IF EXISTS `loaidiem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loaidiem` (
  `idLoaiDiem` int NOT NULL AUTO_INCREMENT,
  `tenDiem` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`idLoaiDiem`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaidiem`
--

LOCK TABLES `loaidiem` WRITE;
/*!40000 ALTER TABLE `loaidiem` DISABLE KEYS */;
INSERT INTO `loaidiem` VALUES (1,'Điểm Giữa Kỳ'),(2,'Điểm Cuối Kỳ'),(3,'Điểm KT1'),(4,'Điểm KT2'),(5,'Điểm KT3'),(6,'Điểm Trung Bình');
/*!40000 ALTER TABLE `loaidiem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaihocky`
--

DROP TABLE IF EXISTS `loaihocky`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loaihocky` (
  `idLoaiHocKy` int NOT NULL AUTO_INCREMENT,
  `tenHocKy` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`idLoaiHocKy`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaihocky`
--

LOCK TABLES `loaihocky` WRITE;
/*!40000 ALTER TABLE `loaihocky` DISABLE KEYS */;
INSERT INTO `loaihocky` VALUES (1,'Học Kỳ 1'),(2,'Học Kỳ 2'),(3,'Học Kỳ 3'),(4,'Học Kỳ 4'),(5,'Học Kỳ 5'),(6,'Học Kỳ 6'),(7,'Học Kỳ 7'),(8,'Học Kỳ 8'),(9,'Học Kỳ 9'),(10,'Học Kỳ 10'),(11,'Học Kỳ 11');
/*!40000 ALTER TABLE `loaihocky` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaitaikhoan`
--

DROP TABLE IF EXISTS `loaitaikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loaitaikhoan` (
  `idloaitaikhoan` int NOT NULL AUTO_INCREMENT,
  `tenloaitaikhoan` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`idloaitaikhoan`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaitaikhoan`
--

LOCK TABLES `loaitaikhoan` WRITE;
/*!40000 ALTER TABLE `loaitaikhoan` DISABLE KEYS */;
INSERT INTO `loaitaikhoan` VALUES (1,'ROLE_GVU'),(2,'ROLE_GV'),(3,'ROLE_SV');
/*!40000 ALTER TABLE `loaitaikhoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lophoc`
--

DROP TABLE IF EXISTS `lophoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lophoc` (
  `idLopHoc` int NOT NULL AUTO_INCREMENT,
  `tenLopHoc` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `idKhoaHoc` int DEFAULT NULL,
  `idNganh` int DEFAULT NULL,
  `idHeDaoTao` int DEFAULT NULL,
  PRIMARY KEY (`idLopHoc`),
  KEY `lophoc_nganh_idx` (`idNganh`),
  KEY `khoahoc_idx` (`idKhoaHoc`),
  KEY `lophoc_hedaotao_idx` (`idHeDaoTao`),
  CONSTRAINT `khoahoc` FOREIGN KEY (`idKhoaHoc`) REFERENCES `khoa` (`idkhoa`),
  CONSTRAINT `lophoc_hedaotao` FOREIGN KEY (`idHeDaoTao`) REFERENCES `hedaotao` (`idhedaotao`),
  CONSTRAINT `lophoc_nganh` FOREIGN KEY (`idNganh`) REFERENCES `nganhdaotao` (`idNganhDaoTao`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lophoc`
--

LOCK TABLES `lophoc` WRITE;
/*!40000 ALTER TABLE `lophoc` DISABLE KEYS */;
INSERT INTO `lophoc` VALUES (9,'DH20IT01',1,1,2),(10,'DH20CS01',1,2,2),(11,'DH20IT02',1,1,2),(12,'DH20IT03',1,1,2),(13,'DH20CS02',1,2,2);
/*!40000 ALTER TABLE `lophoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monhoc`
--

DROP TABLE IF EXISTS `monhoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `monhoc` (
  `idMonHoc` int NOT NULL AUTO_INCREMENT,
  `tenMonHoc` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `soTinChi` int NOT NULL,
  PRIMARY KEY (`idMonHoc`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monhoc`
--

LOCK TABLES `monhoc` WRITE;
/*!40000 ALTER TABLE `monhoc` DISABLE KEYS */;
INSERT INTO `monhoc` VALUES (30,'Khoa Học Máy Tính',4),(31,'Lập Trình Hiện Đại',4),(32,'Cơ Sở Lập Trình',4),(33,'Kiến Trúc Lập Trình',4),(34,'Lập Trình Hướng Đối Tượng',4),(36,'Kiến Trúc Máy Tính',4),(37,'Thiết Kế Web',4),(38,'Công Nghệ Phần Mềm',4),(39,'Toán Cao Cấp',4),(40,'Mạng Máy Tính',4),(41,'Tiếng Anh Nâng Cao 1',3),(42,'Tiếng Anh Nâng Cao 2',3),(43,'Tiếng Anh Nâng Cao 3',3),(44,'Cấu Trúc Dữ Liệu Và Giải Thuật',4);
/*!40000 ALTER TABLE `monhoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monhoc_hocky`
--

DROP TABLE IF EXISTS `monhoc_hocky`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `monhoc_hocky` (
  `idMonHoc_HocKy` int NOT NULL AUTO_INCREMENT,
  `idMonHoc` int DEFAULT NULL,
  `idHocky` int DEFAULT NULL,
  `soLuong` int DEFAULT NULL,
  `ngayBatDau` datetime DEFAULT NULL,
  `ngayKetThuc` datetime DEFAULT NULL,
  `idGiangVien` int DEFAULT NULL,
  `phongHoc` int DEFAULT NULL,
  `soLuongConLai` int DEFAULT NULL,
  PRIMARY KEY (`idMonHoc_HocKy`),
  KEY `monhoc_hocKy_idx` (`idMonHoc`),
  KEY `monhoc_GiangVien_idx` (`idGiangVien`),
  KEY `monhoc_hockyy` (`idHocky`),
  KEY `monhoc_phonghoc_idx` (`phongHoc`),
  CONSTRAINT `monhoc_GiangVien` FOREIGN KEY (`idGiangVien`) REFERENCES `giangvien` (`idGiangVien`),
  CONSTRAINT `monhoc_hocKy` FOREIGN KEY (`idMonHoc`) REFERENCES `monhoc` (`idMonHoc`),
  CONSTRAINT `monhoc_hockyy` FOREIGN KEY (`idHocky`) REFERENCES `hocky` (`idHocKy`),
  CONSTRAINT `monhoc_phonghoc` FOREIGN KEY (`phongHoc`) REFERENCES `phonghoc` (`idPhongHoc`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monhoc_hocky`
--

LOCK TABLES `monhoc_hocky` WRITE;
/*!40000 ALTER TABLE `monhoc_hocky` DISABLE KEYS */;
INSERT INTO `monhoc_hocky` VALUES (28,30,3,80,'2024-01-30 00:00:00','2024-04-30 00:00:00',19,1,78),(29,31,3,80,'2024-01-30 00:00:00','2024-04-30 00:00:00',19,1,80),(46,32,4,80,'2023-10-18 00:00:00','2023-01-20 00:00:00',19,2,73),(47,33,4,80,'2023-10-18 00:00:00','2024-01-20 00:00:00',19,2,73),(48,34,4,80,'2023-10-18 00:00:00','2024-01-20 00:00:00',19,2,80),(49,36,4,80,'2023-10-18 00:00:00','2024-01-20 00:00:00',19,3,80),(50,32,5,80,'2023-10-18 00:00:00','2024-01-20 00:00:00',19,4,80),(51,33,5,80,'2023-10-18 00:00:00','2024-01-20 00:00:00',19,5,80),(52,34,5,80,'2023-10-18 00:00:00','2024-01-20 00:00:00',19,6,80),(53,36,5,80,'2023-10-18 00:00:00','2024-01-20 00:00:00',19,7,80),(54,32,3,80,'2024-01-30 00:00:00','2024-04-30 00:00:00',19,8,80),(55,33,3,80,'2024-01-30 00:00:00','2024-04-30 00:00:00',19,1,80),(56,34,3,80,'2024-01-30 00:00:00','2024-04-30 00:00:00',19,2,80);
/*!40000 ALTER TABLE `monhoc_hocky` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monhocdangky`
--

DROP TABLE IF EXISTS `monhocdangky`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `monhocdangky` (
  `idMonHocDangKy` int NOT NULL AUTO_INCREMENT,
  `idMonHoc` int NOT NULL,
  `idSinhVien` int NOT NULL,
  `xepLoai` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `trangThai` tinyint DEFAULT NULL,
  `khoaMon` tinyint DEFAULT NULL,
  `thanhToan` tinyint DEFAULT NULL,
  PRIMARY KEY (`idMonHocDangKy`),
  KEY `monhocdangky_sinhvien_idx` (`idSinhVien`),
  KEY `monhocdangky_monhochocky_idx` (`idMonHoc`),
  CONSTRAINT `monhocdangky_monhochocky` FOREIGN KEY (`idMonHoc`) REFERENCES `monhoc_hocky` (`idMonHoc_HocKy`),
  CONSTRAINT `monhocdangky_sinhvien` FOREIGN KEY (`idSinhVien`) REFERENCES `sinhvien` (`idSinhVien`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monhocdangky`
--

LOCK TABLES `monhocdangky` WRITE;
/*!40000 ALTER TABLE `monhocdangky` DISABLE KEYS */;
INSERT INTO `monhocdangky` VALUES (55,46,52,'Giỏi',0,0,1),(56,46,53,'Giỏi',0,0,1),(57,46,54,'Giỏi',0,0,1),(58,46,55,'Giỏi',0,0,1),(59,46,59,'Giỏi',0,0,1),(60,46,57,'Giỏi',0,0,1),(61,46,58,'Giỏi',0,0,1),(62,47,52,'Giỏi',0,0,1),(63,47,53,'Giỏi',0,0,1),(64,47,54,'Giỏi',0,0,1),(65,47,55,'Giỏi',0,0,1),(66,47,59,'Giỏi',0,0,1),(67,47,57,'Giỏi',0,0,1),(73,28,52,NULL,0,0,1);
/*!40000 ALTER TABLE `monhocdangky` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nganhdaotao`
--

DROP TABLE IF EXISTS `nganhdaotao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nganhdaotao` (
  `idNganhDaoTao` int NOT NULL AUTO_INCREMENT,
  `tenNganhDaoTao` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `idKhoaDaoTao` int NOT NULL,
  PRIMARY KEY (`idNganhDaoTao`),
  KEY `nganh_khoa_idx` (`idKhoaDaoTao`),
  CONSTRAINT `nganh_khoa` FOREIGN KEY (`idKhoaDaoTao`) REFERENCES `khoadaotao` (`idKhoaDaoTao`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nganhdaotao`
--

LOCK TABLES `nganhdaotao` WRITE;
/*!40000 ALTER TABLE `nganhdaotao` DISABLE KEYS */;
INSERT INTO `nganhdaotao` VALUES (1,'Công Nghệ Thông Tin',1),(2,'Khoa Học Máy Tính',1);
/*!40000 ALTER TABLE `nganhdaotao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phonghoc`
--

DROP TABLE IF EXISTS `phonghoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phonghoc` (
  `idPhongHoc` int NOT NULL AUTO_INCREMENT,
  `tenPhongHoc` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  PRIMARY KEY (`idPhongHoc`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phonghoc`
--

LOCK TABLES `phonghoc` WRITE;
/*!40000 ALTER TABLE `phonghoc` DISABLE KEYS */;
INSERT INTO `phonghoc` VALUES (1,'NK-001'),(2,'NK-002'),(3,'NK-003'),(4,'NK-004'),(5,'NK-005'),(6,'NK-006'),(7,'NK-007'),(8,'NK-101'),(9,'NK-102'),(10,'NK-103'),(11,'NK-104');
/*!40000 ALTER TABLE `phonghoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sinhvien`
--

DROP TABLE IF EXISTS `sinhvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sinhvien` (
  `idSinhVien` int NOT NULL AUTO_INCREMENT,
  `hoTen` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `ngaySinh` date DEFAULT NULL,
  `diaChi` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `gioiTinh` tinyint DEFAULT NULL,
  `soDienThoai` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `maLop` int DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `idTaiKhoan` int DEFAULT NULL,
  PRIMARY KEY (`idSinhVien`),
  UNIQUE KEY `idTaiKhoan_UNIQUE` (`idTaiKhoan`),
  KEY `idLopHoc_idx` (`maLop`),
  KEY `idTaiKhoan_idx` (`idTaiKhoan`),
  KEY `idTaiKhoanSinhVien_idx` (`idTaiKhoan`),
  CONSTRAINT `idLopHoc` FOREIGN KEY (`maLop`) REFERENCES `lophoc` (`idLopHoc`),
  CONSTRAINT `idTaiKhoanSinhVien` FOREIGN KEY (`idTaiKhoan`) REFERENCES `taikhoan` (`idTaiKhoan`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sinhvien`
--

LOCK TABLES `sinhvien` WRITE;
/*!40000 ALTER TABLE `sinhvien` DISABLE KEYS */;
INSERT INTO `sinhvien` VALUES (52,'Nguyễn Thanh Thuyền','2002-04-22','200/14 Gò Dầu, Tân Quý, Tân Phú',1,'0371251727',9,'thanhthuyen@ou.edu.vn',56),(53,'Lê Anh Khoa','2002-03-21','37 Lê Thánh Tông, P11, Tân Bình',1,'0277650217',9,'cua2432002@gmail.com',63),(54,'Phan Quế Thanh','2002-03-05','41 Tân Hải, P13, Tân Bình',0,'0722451702',9,'quethanh@ou.edu.vn',NULL),(55,'Phan Anh Thư','2002-04-06','31 Lê Lai, p14, Gò Vấp',0,'0211745022',9,'anhthu@ou.edu.vn',NULL),(57,'Phan Thanh Hải','2002-04-06','41 Tân Hải, P13, Tân Bình',1,'0123456789',9,'thanhhai@ou.edu.vn',NULL),(58,'Nguyễn Hải Ninh','2002-03-21','37 Lê Thánh Tông, P11, Tân Bình',1,'1234565678',10,'haininh@ou.edu.vn',NULL),(59,'Lâm Thi Hạnh','2002-03-05','200/14 Gò Dầu, Tân Quý, Tân Phú',0,'0124711246',10,'thihanh@ou.edu.vn',NULL),(60,'Nguyễn Mỹ Chi','2002-03-05','37 Lê Thánh Tông, P11, Tân Bình',0,'2725241359',10,'mychi@ou.edu.vn',NULL),(61,'Hàn Thanh Lâm','2002-03-05','31 Lê Lai, p14, Gò Vấp',1,'7112583146',10,'thanhlam@ou.edu.vn',NULL),(62,'Tăng Thị Mỹ','2002-03-05','41 Tân Hải, P13, Tân Bình',0,'7120311245',10,'thimy@ou.edu.vn',NULL);
/*!40000 ALTER TABLE `sinhvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoan` (
  `idTaiKhoan` int NOT NULL AUTO_INCREMENT,
  `TenTaiKhoan` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `MatKhau` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `ChucVu` int DEFAULT NULL,
  `image` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  PRIMARY KEY (`idTaiKhoan`),
  KEY `idloaitk_idx` (`ChucVu`),
  CONSTRAINT `idloaitk` FOREIGN KEY (`ChucVu`) REFERENCES `loaitaikhoan` (`idloaitaikhoan`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES (52,'hoangan2432002@gmail.com','$2y$06$qELmOrioZqbIrVfFih6IL.AbeWu4MqI8oPI55essINeY.ciHTEuTC',1,NULL),(53,'yenvi@gmail.com','$2y$06$qELmOrioZqbIrVfFih6IL.AbeWu4MqI8oPI55essINeY.ciHTEuTC',1,NULL),(54,'huuthanh@gmail.com','$2y$06$qELmOrioZqbIrVfFih6IL.AbeWu4MqI8oPI55essINeY.ciHTEuTC',2,'https://res.cloudinary.com/dhcvsbuew/image/upload/v1697104804/ihv6h8jxle6xhnvtchuv.jpg'),(55,'giakhoang@gmail.com','$2y$06$qELmOrioZqbIrVfFih6IL.AbeWu4MqI8oPI55essINeY.ciHTEuTC',2,NULL),(56,'thanhthuyen@ou.edu.vn','$2a$10$9JiiyH.N9GUBSVd4ktk4M.ideQWERB2BdwwLEoFMfVhyVXJheh4e2',3,'https://res.cloudinary.com/dhcvsbuew/image/upload/v1697662181/kyxsf60npwxl8dltsw2h.jpg'),(62,'cua24032002@gmail.com','$2a$10$fm8.Bcgo9ynH3/p47haieOBDba0i6iVym2gORZZVSdS0PbpOdqDWG',2,NULL),(63,'cua2432002@gmail.com','$2a$10$LANgJxLQbkcE8onbjuZYeuf.e.5tT001TBgVp9Fv53poGlk5iUuei',3,NULL);
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `traloidiendan`
--

DROP TABLE IF EXISTS `traloidiendan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `traloidiendan` (
  `idTraLoiDienDan` int NOT NULL AUTO_INCREMENT,
  `noiDungTraLoi` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `idTaiKhoan` int DEFAULT NULL,
  `idCauHoiDienDan` int DEFAULT NULL,
  PRIMARY KEY (`idTraLoiDienDan`),
  KEY `idCauHoi_idx` (`idCauHoiDienDan`),
  KEY `idTaiKhoan_idx` (`idTaiKhoan`),
  CONSTRAINT `idCauHoi` FOREIGN KEY (`idCauHoiDienDan`) REFERENCES `cauhoidiendang` (`idCauHoiDienDan`),
  CONSTRAINT `idTaiKhoanCauTraLoi` FOREIGN KEY (`idTaiKhoan`) REFERENCES `taikhoan` (`idTaiKhoan`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `traloidiendan`
--

LOCK TABLES `traloidiendan` WRITE;
/*!40000 ALTER TABLE `traloidiendan` DISABLE KEYS */;
INSERT INTO `traloidiendan` VALUES (34,'Có TOD nha bạn.',54,56);
/*!40000 ALTER TABLE `traloidiendan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-23 14:09:32
