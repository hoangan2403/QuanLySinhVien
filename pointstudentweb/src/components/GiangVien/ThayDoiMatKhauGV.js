import { Button, Form, Alert } from "react-bootstrap";
import { GiangVienContext, MyUserConText } from "../../App";
import { useContext, useState } from "react";
import Apis, { AuthApis, endpoints } from "../../configs/Apis";
import MySpinner from "../../layout/MySpinner";


const ThayDoiMatKhauGV = () => {
    const [loading, setLoading] = useState(false);
    const [duplicatePass, setDuplicatePass] = useState(false);
    const [faild, setFaild] = useState(false);
    const [changeSuccess, setChangeSuccess] = useState(false);
    const [user, dispatch] = useContext(MyUserConText);
    const [giangvien] = useContext(GiangVienContext);
    const [pass, setPass] = useState(true);

    const [taiKhoan, setTaiKhoan] = useState({
        "matKhau": "",
        "matKhauMoi": "",
        "xacNhanMKMoi": ""
    });

    const change = (evt, field) => {
        setTaiKhoan(current => {
            return {...current, [field]: evt.target.value}
        })
    }


    const changePass = (evt) => {
        evt.preventDefault();
        setPass(true);
        setDuplicatePass(false);
        setChangeSuccess(false);
        setFaild(false);


        const process = async () => {
            let formData = new FormData();
            

            formData.append("tenTaiKhoan", user.tenTaiKhoan);
            formData.append("matKhau", taiKhoan.matKhau);
            formData.append("matKhauMoi", taiKhoan.matKhauMoi);
            setLoading(true);
            let res = await AuthApis().post(endpoints['changePassword'], formData);
            if (res.status === 200) {
                setChangeSuccess(true);
            }
            else {
                setFaild(true);
            }
        }

        if(taiKhoan.matKhauMoi !== taiKhoan.xacNhanMKMoi){
            setPass(false);
        }
        else if(taiKhoan.matKhauMoi === taiKhoan.matKhau){
            setDuplicatePass(true);
        }
        else {
            process();
            setLoading(false);
        }
         
    }
    return (
        <div class="contend">
            <nav class="navbar navbar-1 navbar-expand-sm navbar-dark nav-menu">
                <div class="container-fluid">
                    <a class="navbar-brand dark-color header-logo " href="#"><i class="fa-solid fa-bell icon-padding"></i></a>
                    <div class="collapse navbar-collapse" id="collapsibleNavbar">
                        <ul class="navbar-nav">
                            <li class="nav-item user-name-img">
                                <a class="nav-link dark-color" href="#"><i class="fa-solid fa-user icon-padding" ></i></a>
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
            <div class="change-password">
                <p class="change-password-title">Thay Đổi Mật Khẩu</p>
                <Form onSubmit={changePass} class="change-password-form">
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                        <Form.Label>Mật Khẩu Hiện Tại</Form.Label>
                        <Form.Control type="password" value={taiKhoan.matKhau}
                            onChange={e => change(e, "matKhau")} placeholder="Mật Khẩu Hiện Tại" />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                        <Form.Label>Mật Khẩu Mới</Form.Label>
                        <Form.Control type="password" value={taiKhoan.matKhauMoi}
                            onChange={e => change(e, "matKhauMoi")} placeholder="Mật Khẩu Mới" />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                        <Form.Label>Nhập Lại Mật Khẩu</Form.Label>
                        <Form.Control type="password" value={taiKhoan.xacNhanMKMoi}
                            onChange={e => change(e, "xacNhanMKMoi")} placeholder="Nhập Lại Mật Khẩu" />
                    </Form.Group>
                    <Form.Group className="mb-3 btn-change-password-div">
                    {loading === true?<MySpinner />:<Button className="btn-change-password" type="submit">Thay Đổi</Button>}
                    
                    </Form.Group>
                    {pass===false ? <Alert variant="danger">Mật khẩu không trùng khớp</Alert>: <div></div>}
                    {duplicatePass===true ? <Alert variant="danger">Mật khẩu trùng với mật khẩu hiện tại</Alert>: <div></div>}
                    {changeSuccess===true ? <Alert variant="secondary">Thay Đổi Thành Công</Alert>: <div></div>}
                    {faild===true ? <Alert variant="danger">Thay đổi thất bại</Alert>: <div></div>}
                </Form>
            </div>

        </div>
    )
}
export default ThayDoiMatKhauGV;