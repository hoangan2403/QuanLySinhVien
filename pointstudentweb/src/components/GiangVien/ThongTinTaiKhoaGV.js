import { Button, Form, Alert } from "react-bootstrap";
import { GiangVienContext, MyUserConText } from "../../App";
import { useContext, useRef, useState } from "react";
import MySpinner from "../../layout/MySpinner";
import { AuthApis, endpoints } from "../../configs/Apis";
import Header from "../../layout/giangVien/Header";
import cookie from "react-cookies";

const ThongTinTaiKhoanGV = () => {
    const [user, dispatch] = useContext(MyUserConText);
    const [giangvien] = useContext(GiangVienContext);
    const [loading, setLoading] = useState(false);
    const [pass, setPass] = useState(true);
    const [success, setSuccess] = useState(false);
    const avatar = useRef();


    const [taiKhoan, setTaiKhoan] = useState({
        "idTaiKhoan": user.idTaiKhoan,
        "tenTaiKhoan": user.tenTaiKhoan,
        "matKhau": user.matKhau,
        "chucVu": user.chucVu.tenloaitaikhoan,
    });

    const updateImage = (evt) => {
        evt.preventDefault();
        setPass(true);
        setSuccess(false);
        const process = async () => {
            let form = new FormData();

            for (let field in taiKhoan)

                form.append(field, taiKhoan[field]);

            form.append("avatar", avatar.current.files[0]);

            setLoading(true)
            let res = await AuthApis().post(endpoints['udateImage'], form);

            let { data } = await AuthApis().get(endpoints['current-user']);
            cookie.remove("user");
            cookie.save("user", data);
            dispatch({
                "type": "login",
                "payLoad": data
              })

        }
        if (avatar.current.files.length > 0) {
            process();
            setLoading(false);
            setSuccess(true);
        }
        else {
            setPass(false);
        }
    }
    return (
        <>
            <div class="gv-contend">
                <Header />
                <div class="info-user">
                    <div class="info-title-user">
                        {user.image === null ? <p class="info-user-image"><i class="fa-solid fa-user icon-padding"></i></p> : <div class="info-user-image-2" ><img class="img-user-avatar" src={user.image} alt="Ảnh đại diện" /></div>}

                        <Form onSubmit={updateImage}>
                            <Form.Group className="mb-3">
                                <Form.Label>Ảnh đại diện</Form.Label>
                                <Form.Control type="file" ref={avatar} />
                            </Form.Group>
                            <div class="form-group">

                                {loading === true ? <MySpinner /> : <Button class="btn btn-danger mt-2" type="submit">Thêm Ảnh </Button>}
                            </div>
                            {pass === false ? <Alert variant="secondary">Vui lòng chọn ảnh</Alert> : <div></div>}
                            {success === true ? <Alert variant="secondary">Cập nhập ảnh thành công !!!</Alert> : <div></div>}
                        </Form>
                        <p class="info-private">Thông tin Cá Nhân</p>
                    </div>
                    <div class="info-user-text-reply">
                        <div class="info-user-texts">
                            <span class="info-user-text">Họ Tên:</span>
                            <span class="info-user-text2">{giangvien.hoTen}</span>
                        </div >
                        <div class="info-user-texts">
                            <span class="info-user-text">Email</span>
                            <span class="info-user-text2">{giangvien.email}</span>
                        </div>
                        <div class="info-user-texts">
                            <span class="info-user-text">Địa Chỉ</span>
                            <span class="info-user-text2">{giangvien.diaChi}</span>
                        </div>
                        <div class="info-user-texts">
                            <span class="info-user-text">Số Điện Thoại</span>
                            <span class="info-user-text2">{giangvien.soDienThoai}</span>
                        </div>
                    </div>

                </div>
            </div>
        </>
    );
};
export default ThongTinTaiKhoanGV;