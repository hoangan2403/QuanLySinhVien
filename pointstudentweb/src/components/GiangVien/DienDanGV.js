import { useEffect, useState } from "react";
import { Link, useSearchParams, useNavigate } from "react-router-dom";
import { AuthApis, endpoints } from "../../configs/Apis";
import { MyUserConText } from "../../App";
import { useContext } from "react";
import MySpinner from "../../layout/MySpinner";
import Header from "../../layout/giangVien/Header";

const DienDanGV = () => {
    const [user, dispatch] = useContext(MyUserConText);
    const [cauHois, setCauhois] = useState([]);
    let nav = useNavigate();
    const [q] = useSearchParams();
    const [kw, setKw] = useState("");
    const [deletionComplete, setDeletionComplete] = useState(false); // Track deletion status
    let cauhoiid = q.get("cauhoiId");
    useEffect(() => {
        const loadCauHoi = async () => {

            let res = await AuthApis().get(endpoints['cauHois']);

            setCauhois(res.data);
        }

        const process = async () => {
            try {
                if (cauhoiid !== null && !deletionComplete) { // Check deletionComplete flag
                    const confirmed = window.confirm(
                        "Are you sure you want to delete this question?"
                    );
                    if (confirmed) {
                        let ch = endpoints["deleteCauHoi"];
                        ch = `${ch}?idCauHoiDienDan=${cauhoiid}`;
                        nav("/giangvien/diendan");
                        let res = await AuthApis().delete(ch);

                        setDeletionComplete(true); // Set deletion status to true
                    } else {
                        nav("/giangvien/diendan");
                    }
                }
            } catch (ex) {
                console.error(ex);
            }
        };
        process();
        loadCauHoi();
    }, [q])

    if (cauHois === null) {
        return <MySpinner />
    }

    const currentDate = new Date();
    const day = currentDate.getDate();
    const month = currentDate.getMonth() + 1;
    const year = currentDate.getFullYear();
    return (
        <div class="contend">
            <Header />
            <div class="content-diendan">
                <h3 >Diễn Đàng Trao Đổi</h3>
                <p>Ngày hiện tại: {day}/{month}/{year}</p>
                <Link to="/giangvien/themchude" class="text-contend-link add-contend-question" id="themcauhoi">Thêm Chủ Đề</Link>
                {cauHois.map(c => {
                    let h = `/giangvien/traloidiendan?cauhoiId=${c.idCauHoiDienDan}`;
                    let k = `/giangvien/themchude?cauhoiId=${c.idCauHoiDienDan}`;
                    let a = `/giangvien/diendan?cauhoiId=${c.idCauHoiDienDan}`;
                    return (<div class="content-question" key={c.idCauHoiDienDan}>
                        <h5><i class="fa-solid fa-user icon-padding"></i>{c.idTaiKhoan.tenTaiKhoan}{c.idTaiKhoan.chucVu.tenloaitaikhoan=="ROLE_GV"?" - Giảng Viên":""}</h5>
                        <p class="content-question-day"><i class="fa-solid fa-calendar-days icon-padding"></i>{c.ngayTao}</p>
                        <p class="content-question-noidung">{c.noiDungCauHoi}</p>

                        <Link to={h} class="text-contend-link"><i class="fa-solid fa-comment-dots icon-padding"></i>Xem Câu Trả Lời</Link>
                        {user.idTaiKhoan === c.idTaiKhoan.idTaiKhoan ? <Link to={k} class="text-contend-link update-text-diendan"><i class="fa-solid fa-pen-to-square icon-padding"></i>Chỉnh Sửa</Link> : null}
                        {user.idTaiKhoan === c.idTaiKhoan.idTaiKhoan ? <Link to={a} class="text-contend-link update-text-diendan"><i class="fa-solid fa-trash icon-padding"></i>Xóa</Link> : null}
                        <Link to={h} class="text-contend-link update-text-diendan"><i class="fa-solid fa-reply icon-padding"></i>Trả Lời</Link>

                    </div>)

                })}
            </div>
        </div>
    )
}
export default DienDanGV;