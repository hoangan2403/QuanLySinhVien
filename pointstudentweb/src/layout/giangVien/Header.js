import { useContext, useState } from "react";
import { Button, Form, Alert, Col, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { GiangVienContext, MyUserConText } from "../../App";


const Header = () => {
    let nav = useNavigate();
    const [kw, setKw] = useState("");
    const [user, dispatch] = useContext(MyUserConText);
    const [giangvien] = useContext(GiangVienContext);

    const search = (evt) => {
        evt.preventDefault();
        nav(`/giangvien/timsinhvien/?idGiangVien=${giangvien.idGiangVien}&tenSinhVien=${kw}`)
    }

    return (
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
                                {user.tenTaiKhoan}</a>
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
    )
}
export default Header;

