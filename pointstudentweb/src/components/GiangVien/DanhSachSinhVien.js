import {  useContext, useEffect, useState } from "react";
import { GiangVienContext, MyUserConText } from "../../App";
import { AuthApis, endpoints } from "../../configs/Apis";
import { Link, useNavigate, useSearchParams } from "react-router-dom";
import { Button, Form, Alert, Col, Row } from "react-bootstrap";


const DanhSachSinhVien = () => {
    const [user, dispatch] = useContext(MyUserConText);
    const [giangvien] = useContext(GiangVienContext);
    const [DSSinhVien, setDSSinhVien] = useState([]);
    const [q] = useSearchParams();
    const [kw, setKw] = useState("");
    let monHocId = q.get("monHocId");
    let nav = useNavigate();
    
    useEffect(() => {
        const loadSv = async () => {
            try {
                let e = endpoints['DSSinhVienByMonHoc'];
                monHocId = q.get("monHocId");
                let tenSinhVien = q.get("tenSinhVien");
                
                //Loc điểm theo idMonHoc, Id SinhVien, tên Sinh Vien
                if (monHocId !== null) {
                    if (tenSinhVien !== null) {
                        e = `${e}?monHocId=${monHocId}&tenSinhVien=${tenSinhVien}`;
                        let res = await AuthApis().post(e);
                        setDSSinhVien(res.data);
                       
                    }
                    else {
                        e = `${e}?monHocId=${monHocId}`;
                        let res = await AuthApis().post(e);
                        setDSSinhVien(res.data);
                    }
                }
            } catch (ex) {
                console.error(ex);
            }
        }
        loadSv();
    }, [q]);


    ///tìm kiếm sinhvien
    const search = (evt) => {
        evt.preventDefault();
        nav(`/giangvien/danhsachsinhvien?monHocId=${monHocId}&tenSinhVien=${kw}`)
    }

    return (
        <>
            <div class="contend">
                <nav class="navbar navbar-1 navbar-expand-sm navbar-dark nav-menu">
                    <div class="container-fluid">
                        <a class="navbar-brand dark-color header-logo " href="#"><i class="fa-solid fa-bell icon-padding"></i></a>
                        <div class="collapse navbar-collapse" id="collapsibleNavbar">
                            <ul class="navbar-nav">
                                <li class="nav-item gv-Search-student">
                                    <Form onSubmit={search} className="d-flex align-items-center Search-student">
                                        <Form.Control
                                            type="text"
                                            value={kw}
                                            onChange={e => setKw(e.target.value)}
                                            placeholder="Nhập tên sinh viên"
                                            name="kw"
                                            className="mr-2"
                                        />
                                        <Button type="submit" className="btn-search-student">Tìm</Button>
                                    </Form>
                                </li>
                                <li class="nav-item gv-user-name-img ">
                                    {user.image === null ? <a class="nav-link dark-color" href="#"><i class="fa-solid fa-user icon-padding" ></i></a> : <div class="info-user-image-3" ><img class="img-user-avatar-header" src={user.image} alt="Ảnh đại diện" /></div>}
                                </li>

                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle dark-color" href="#" role="button" data-bs-toggle="dropdown">Chào,
                                        {giangvien.hoTen}</a>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item dark-color " href="#"><i class="fa-solid fa-user icon-padding"></i>Thông Tin Tài Khoản</a></li>
                                        <li><a class="dropdown-item dark-color" href="#"><i class="fa-solid fa-key icon-padding"></i>Thay Đổi Mật Khẩu</a></li>
                                        <li><a class="dropdown-item dark-color" href="#"><i class="fa-solid fa-right-to-bracket icon-padding"></i>Đăng Xuất</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
                <div class=" point-ky">
                    <div class="container mt-3 " >
                     
                        <div >
                            <table class="table" id="table-to-pdf">
                                <thead>
                                    <tr>
                                        <th>Mã sinh viên</th>
                                        <th>Họ và tên</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {DSSinhVien.map(sv => {
                                        let h = `/giangvien/chitietsinhvien/?idSinhVien=${sv.monHoc.idSinhVien.idSinhVien}`;

                                        return (
                                            <tr key={sv.idMonHocDangKy}>
                                                <td>{sv.monHoc.idSinhVien.idSinhVien}</td>
                                                <td>{sv.monHoc.idSinhVien.hoTen}</td>
                                                <td><Link to={h}><button type="button" class="btn btn-info" >Chi Tiết</button></Link></td>
                                            </tr>
                                        )
                                    })}
                                </tbody>
                            </table>
                            {DSSinhVien.length === 0 ? <Alert variant="secondary" className="mt-3">Không có sinh viên</Alert> : <></>}
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
};
export default DanhSachSinhVien;
