import { useContext, useEffect, useState } from "react";
import { Button, Form, Alert } from "react-bootstrap";
import { Link, useNavigate, useSearchParams } from "react-router-dom";
import { GiangVienContext, MyUserConText } from "../../App";
import { AuthApis, endpoints } from "../../configs/Apis";
import MySpinner from "../../layout/MySpinner";
import Header from "../../layout/giangVien/Header";

const NhapDiemSinhVien = () => {
    const [loading, setLoading] = useState(false);
    const [success, setSuccess] = useState(false);
    const [showCotDiem1, setShowCotDiem1] = useState(false);
    const [showCotDiem2, setShowCotDiem2] = useState(false);
    const [showCotDiem3, setShowCotDiem3] = useState(false);
    const [q] = useSearchParams();
    let idDiem = q.get("idDiem");
    let idMonHoc = q.get("idMonHoc");
    const [user, dispatch] = useContext(MyUserConText);
    const [giangvien] = useContext(GiangVienContext);
    const [diem1, setDiem1] = useState([]);
    const [diem, setDiem] = useState({
        "idMonHocDangKy": idDiem,
        "DiemGK": '',
        "DiemCK": '',
        "DiemKT1": '',
        "DiemKT2": '',
        "DiemKT3": '',
    });
    // const addCotDiem1 = () => {
    //     setShowCotDiem1(!showCotDiem1);
    //     const updatedDiem = { ...diem };
    //     updatedDiem.DiemKT1 = '';
    //     setDiem(updatedDiem);
    // };
    // const addCotDiem2 = () => {
    //     setShowCotDiem2(!showCotDiem2);
    //     const updatedDiem = { ...diem };
    //     updatedDiem.DiemKT2 = '';
    //     setDiem(updatedDiem);
    // };
    // const addCotDiem3 = () => {
    //     setShowCotDiem3(!showCotDiem3);
    //     const updatedDiem = { ...diem };
    //     updatedDiem.DiemKT3 = '';
    //     setDiem(updatedDiem);
    // };


    useEffect(() => {
        const loadDiem = async () => {
            try {
                let e = endpoints['getdiemById'];
                idDiem = q.get("idDiem");
                if (idDiem !== null) {
                    e = `${e}?idMonHocDangKy=${idDiem}`;
                    let res = await AuthApis().post(e);
                    setDiem1(res.data);
                    const updatedDiem = { ...diem };
                    updatedDiem.DiemKT1 = res.data.diemKT1;
                    updatedDiem.DiemKT2 = res.data.diemKT2;
                    updatedDiem.DiemKT3 = res.data.diemKT3;
                    updatedDiem.DiemGK = res.data.diemGK;
                    updatedDiem.DiemCK = res.data.diemCK;
                    setDiem(updatedDiem);

                    if (res.data.diemKT1 !== -1.0) {
                        setShowCotDiem1(true);
                    }
                    if (res.data.diemKT2 !== -1.0) {
                        setShowCotDiem2(true);
                    }
                    if (res.data.diemKT3 !==-1.0) {
                        setShowCotDiem3(true);
                    }
                }
            } catch (ex) {
                console.error(ex);
            }

        }
        loadDiem();
    }, [q]);

    let nav = useNavigate();

    const addDiem = (evt) => {
        setSuccess(false);

        evt.preventDefault();
        const process = async () => {
            let form = new FormData();
            for (let field in diem)

                form.append(field, diem[field]);
            let res = await AuthApis().post(endpoints['addDiem'], form);
            setLoading(true);
            if (res.status === 200) {
                nav(`/giangvien/nhapdiem?monHocId=${idMonHoc}`);
            }

        };
        process();

    }


    const change = (evt, field) => {
        setDiem(current => {
            return { ...current, [field]: evt.target.value }
        })
    }

    let h = `/giangvien/nhapdiem?monHocId=${idMonHoc}`;

    return (
        <div class="gv-contend">
            <Header />
            <div class="traloi-diendan-none roll-nhapdiem" id="container2">
                <Form className="mt-5 mb-5" onSubmit={addDiem}>

                    <div class="form-themcauhoi-diendan">
                        <Link to={h} className="close-traloi-diendan ">Đóng </Link>
                        <h5><i class="fa-solid fa-user icon-padding mb-3"></i>Nhập điểm sinh viên</h5>

                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label htmlFor="diemCK">Điểm Giữa Kỳ<span class="text-danger">*</span></Form.Label>
                            <Form.Control type="number" min="0" max="10" step="0.1" required value={diem.DiemGK}
                                onChange={e => change(e, "DiemGK")} placeholder="Điểm Giữa Kỳ" />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label htmlFor="diemCK">Điểm Cuối Kỳ<span class="text-danger">*</span></Form.Label>
                            <Form.Control type="number" min="0" max="10" step="0.1" required value={diem.DiemCK}
                                onChange={e => change(e, "DiemCK")} placeholder="Điểm cuối Kỳ" />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <div className="row">
                                <div className="col">
                                    {showCotDiem1 ? <><Form.Label htmlFor="diemCK">Điểm Khác 1</Form.Label>
                                        <Form.Control type="number" min="0" max="10" step="0.1" required value={diem.DiemKT1}
                                            onChange={e => change(e, "DiemKT1")} placeholder="Điểm Khác 1" /></> : <></>}
                                </div>
                              
                            </div>


                        </Form.Group>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <div className="row">
                                <div className="col">
                                    {showCotDiem2 ? <><Form.Label htmlFor="diemCK">Điểm Khác 2</Form.Label>
                                        <Form.Control type="number" min="0" max="10" step="0.1" required value={diem.DiemKT2}
                                            onChange={e => change(e, "DiemKT2")} placeholder="Điểm Khác 2" /></> : <></>}
                                </div>
                                
                            </div>




                        </Form.Group>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <div className="row">
                                <div className="col">
                                    {showCotDiem3 ? <><Form.Label htmlFor="diemCK">Điểm Khác 3</Form.Label>
                                        <Form.Control type="number" min="0" max="10" step="0.1" required value={diem.DiemKT3}
                                            onChange={e => change(e, "DiemKT3")} placeholder="Điểm Khác 3" /></> : <></>}
                                </div>
                               
                            </div>
                        </Form.Group>

                        {loading === true ? <MySpinner /> : <Button className="btn-traloi-diendan mt-3" type="submit"><i class="fa-solid fa-reply icon-padding"></i>Thêm điểm</Button>}
                        {success === true ? <Alert variant="secondary">Thêm điểm thành công !!!</Alert> : <div></div>}
                    </div>
                </Form>
            </div>
        </div>
    )
}
export default NhapDiemSinhVien;