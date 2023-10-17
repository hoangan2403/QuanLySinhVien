import axios from "axios";
import cookie from "react-cookies";
const SERVER_CONTEXT = "/QLDiemSinhVien";

export const endpoints = {
    "cauHois": `${SERVER_CONTEXT}/api/cauhoi/`,
    "cauTraLoi": `${SERVER_CONTEXT}/api/traloi/`,
    "cauhoi": `${SERVER_CONTEXT}/api/cauhoiid/`,
    "login": `${SERVER_CONTEXT}/api/login/`,
    "current-user": `${SERVER_CONTEXT}/api/current-user/`,
    "DSDiemTrungBinhHocKy":`${SERVER_CONTEXT}/api/DSDiemSVHocKy/`,
    "DiemTrungBinhHe10":`${SERVER_CONTEXT}/api/TrungBinhDiem/`,
    "DiemTrungBinhHe4":`${SERVER_CONTEXT}/api/TrungBinhDiemHe4/`,
    "DSDiem":`${SERVER_CONTEXT}/api/DanhSachDiem/`,
    "DSDiemDaHoc":`${SERVER_CONTEXT}/api/DanhSachDiemDaHoc/`,
    "DSMonHocById":`${SERVER_CONTEXT}/api/monhocsinhvien/`,
    "signup":`${SERVER_CONTEXT}/api/users/`,
    "changePassword":`${SERVER_CONTEXT}/api/change-password/`,
    "current-sinhvien":`${SERVER_CONTEXT}/api/current-sinhvien/`,
    "current-giangvien":`${SERVER_CONTEXT}/api/current-user-gv/`,
    "themCauHoi":`${SERVER_CONTEXT}/api/add-cauhoi/`,
    "themTraLoi":`${SERVER_CONTEXT}/api/add-traloi/`,
    "udateImage":`${SERVER_CONTEXT}/api/change-avt/`,
    "deleteCauHoi":`${SERVER_CONTEXT}/api/delete-cauhoi/`,
    "monHocIdTK":`${SERVER_CONTEXT}/api/monhocgiangvien/`,
    "DSSinhVienByMonHoc":`${SERVER_CONTEXT}/api/monhocsinhvien/`,
    "addDiem":`${SERVER_CONTEXT}/api/add-diem/`,
    "addListDiem":`${SERVER_CONTEXT}/api/add-List-diem/`,
    "getdiemById":`${SERVER_CONTEXT}/api/diem-idDiem/`,
    "getSinhVienByIdOrTen":`${SERVER_CONTEXT}/api/diemSV-idSV/`,
    "khoaDiem":`${SERVER_CONTEXT}/api/KhoaDiem/`,
    "listMonHocDangKi":`${SERVER_CONTEXT}/api/monhochocky/`,
    "listMonHocSVDangKy":`${SERVER_CONTEXT}/api/monhocSVdangky/`,
    "dangKyMonHoc":`${SERVER_CONTEXT}/api/dangkymonhoc/`,
    "huyDangKyMonHoc":`${SERVER_CONTEXT}/api/huydangkymonhoc/`,
    "DSLopHoc":`${SERVER_CONTEXT}/api/dslophoc/`,
    "sendCode":`${SERVER_CONTEXT}/api/send-code/`,
    "DSMHChuaDay":`${SERVER_CONTEXT}/api/monhocgiangvienchuaday/`,
    "DSMHDaDay":`${SERVER_CONTEXT}/api/monhocgiangviendaday/`,
    "getSinhVienById":`${SERVER_CONTEXT}/api/SinhVien-Id/`,
    "thanhToanHocPhi":`${SERVER_CONTEXT}/api/create_payment/`,
    "xacnhanthanhtoan":`${SERVER_CONTEXT}/api/queryTransactionStatus/`,
    "addCotDiem":`${SERVER_CONTEXT}/api/addCotDiem_SV/`,
    "deleteCotDiem":`${SERVER_CONTEXT}/api/deleteCotDiem_SV/`,
    "tinhDiemTB":`${SERVER_CONTEXT}/api/TinhDiemTB_SV/`,
    

}

export const AuthApis = () => {
    return axios.create({
        baseURL: "http://localhost:8086",
        headers: {
            "Authorization": cookie.load("token")
        }
    })
}

export default axios.create({
    baseURL: "http://localhost:8086"
})