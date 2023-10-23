import { useCallback, useContext, useEffect, useRef, useState } from "react";
import { GiangVienContext, MyUserConText } from "../../App";
import { AuthApis, endpoints } from "../../configs/Apis";
import { Link, useNavigate, useSearchParams } from "react-router-dom";
import { Button, Form, Alert, Col, Row, Spinner } from "react-bootstrap";
import MySpinner from "../../layout/MySpinner";
import PDFGenerator from "../../layout/PDFGenerator";

const CTMonHoc = () => {
    const [user, dispatch] = useContext(MyUserConText);
    const [giangvien] = useContext(GiangVienContext);
    const [DSSinhVien, setDSSinhVien] = useState([]);
    const [q] = useSearchParams();
    const fileDiem = useRef();
    const [loading, setLoading] = useState(false);
    const [kw, setKw] = useState("");
    const [pass, setPass] = useState(true);
    const [success, setSuccess] = useState(false);
    const [thanhCong, setThanhCong] = useState(false);
    const [checkCSV, setCheckCSV] = useState(false);
    const [ChotDiem, setChotDiem] = useState(false);
    const [checkKhoaDiem, setCheckKhoaDiem] = useState(true);

    const [addDiemSV, setAddDiemSV] = useState(false);
    const [deleDiemSV, setDeleAddDiemSV] = useState(false);
    let monHocId = q.get("monHocId");
    let nav = useNavigate();
    const [diems, setsetDiems] = useState({
        "idMonHoc": ""
    });
    useEffect(() => {
        setChotDiem(false);
        setSuccess(false);
        const loadSv = async () => {
            try {
                let e = endpoints['DSSinhVienByMonHoc'];
                monHocId = q.get("monHocId");
                let tenSinhVien = q.get("tenSinhVien");
                setsetDiems({
                    ...diems,
                    "idMonHoc": monHocId
                });
                //Loc điểm theo idMonHoc, Id SinhVien, tên Sinh Vien
                if (monHocId !== null) {
                    if (tenSinhVien !== null && tenSinhVien !=='') {
                        e = `${e}?monHocId=${monHocId}&tenSinhVien=${tenSinhVien}`;
                        let res = await AuthApis().post(e);
                        setDSSinhVien(res.data);
                        setCheckKhoaDiem(false)
                    }
                    else {
                        e = `${e}?monHocId=${monHocId}`;
                        let res = await AuthApis().post(e);
                        setDSSinhVien(res.data);
                        const data = res.data;
                        const hasKhoaDiemZero = data.some(sinhvien => sinhvien.khoaMon === 1);
                        if (hasKhoaDiemZero) {
                            setCheckKhoaDiem(false);
                        }
                        else{
                            setCheckKhoaDiem(true);
                        }
                    }

                }

            } catch (ex) {
                console.error(ex);
            }
        }
        loadSv();

    }, [q, ChotDiem, success, DSSinhVien]);


    ///tìm kiếm sinhvien
    const search = (evt) => {
        evt.preventDefault();
        nav(`/giangvien/nhapdiem?monHocId=${monHocId}&tenSinhVien=${kw}`)
    }

    ///update điểm bằng file csv
    const updateDiem = (evt) => {
        evt.preventDefault();
        setPass(true);
        setThanhCong(false);
        // setSuccess(false);


        function isCSVFile(file) {
            return file.name.endsWith('.csv');
        }
        const process = async () => {
            let form = new FormData();

            for (let field in diems)
                form.append(field, diems[field]);

            form.append("file", fileDiem.current.files[0]);

            setLoading(true)
            let res = await AuthApis().post(endpoints['addListDiem'], form);
        }
        if (fileDiem.current.files.length > 0) {

            if (isCSVFile(fileDiem.current.files[0])) {
                process();
                setLoading(false);
                setSuccess(true);
                setThanhCong(true);
            } else {
                setCheckCSV(true);
            }
        }
        else {
            setPass(false);
        }

    }

    ///chốt điểm sinh viên
    const chotDiem = (evt) => {
        evt.preventDefault();
        //kiểm tra xem điểm đã nhập chưa
        const process = async () => {
            let e = endpoints['khoaDiem'];
            if (monHocId !== null) {

                e = `${e}?idMonHoc=${monHocId}`;
                let res = await AuthApis().post(e);
            }

        }
        const confirmed = window.confirm("Bạn có chắc muốn khóa điểm?");
        if (confirmed) {
            process();
            setChotDiem(true);
        }
    }
    const addDiem = (evt) => {
        evt.preventDefault();

        const addDiemSV = async () => {
            let e = endpoints['addCotDiem'];
            e = `${e}?idMonHoc=${monHocId}`;
            setAddDiemSV(true);
            let res = await AuthApis().post(e);
            if (res.data === "Fail") {
                setDeleAddDiemSV(false);
                const confirmed = window.confirm("Chỉ thêm được tối đa 3 cột điểm!!");
            }
            else {
                let a = endpoints['tinhDiemTB'];
                a = `${a}?idMonHoc=${monHocId}`;
                let res = await AuthApis().post(a);
                setAddDiemSV(false);
            }

        }
        addDiemSV();

    }
    const deleteDiem = (evt) => {
        evt.preventDefault();

        const addDiemSV = async () => {
            let e = endpoints['deleteCotDiem'];
            e = `${e}?idMonHoc=${monHocId}`;
            setDeleAddDiemSV(true);
            let res = await AuthApis().delete(e);
            if (res.data === "Fail") {
                setDeleAddDiemSV(false);
                const confirmed = window.confirm("Không xóa được!!");

            }
            else {
                let a = endpoints['tinhDiemTB'];
                a = `${a}?idMonHoc=${monHocId}`;
                let res = await AuthApis().post(a);
                setDeleAddDiemSV(false);
            }

        }
        addDiemSV();

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
                        <p class="text-header-tong">Tổng Hợp Điểm Của Từng Học Kỳ</p>
                        <div >

                            {DSSinhVien.length > 0 ? <><table class="table" id="table-to-pdf">
                                <thead>
                                    <tr>
                                        <th>Mã sinh viên</th>
                                        <th>Họ và tên</th>
                                        {DSSinhVien[0].diemKT1 === -1 ? <></> : <th>Điểm 1(10%)</th>}
                                        {DSSinhVien[0].diemKT2 === -1 ? <></> : <th>Điểm 2(10%)</th>}
                                        {DSSinhVien[0].diemKT3 === -1 ? <></> : <th>Điểm 3(10%)</th>}
                                        <th>Điểm Giữa Kỳ</th>
                                        <th>Điểm Cuối Kỳ</th>
                                        <th>Điểm Trung Bình</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {DSSinhVien.map(sv => {
                                        let h = `/giangvien/nhapdiemsinhvien?idDiem=${sv.idMonHocDangKy}&idMonHoc=${monHocId}`;
                                        return (
                                            <tr key={sv.idMonHocDangKy}>
                                                <td>{sv.monHoc.idSinhVien.idSinhVien}</td>
                                                <td>{sv.monHoc.idSinhVien.hoTen}</td>

                                                {sv.diemKT1 === -1 ? <></> : <td>{sv.diemKT1}</td>}
                                                {sv.diemKT2 === -1 ? <></> : <td>{sv.diemKT2}</td>}
                                                {sv.diemKT3 === -1 ? <></> : <td>{sv.diemKT2}</td>}
                                                {sv.diemGK !== -1 ? <td>{parseFloat(sv.diemGK).toFixed(2)}</td> : <td>-</td>}
                                                {sv.diemCK !== -1 ? <td>{parseFloat(sv.diemCK).toFixed(2)}</td> : <td>-</td>}
                                                {sv.diemTB !== -1 ? <td>{parseFloat(sv.diemTB).toFixed(2)}</td> : <td>0</td>}
                                                {sv.monHoc.khoaMon === 1 ? <div class="btn btn-secondary mt-2">Đã khóa</div> : <Link to={h} className="btn btn-secondary mt-2">Nhập Điểm</Link>}
                                            </tr>
                                        )
                                    })}
                                </tbody>
                            </table></> : <></>}

                            {DSSinhVien.length === 0 ? <Alert variant="secondary" className="mt-3">Không có sinh viên</Alert> : <></>}
                        </div>
                        {DSSinhVien.length !== 0 && checkKhoaDiem ? <div>
                            {addDiemSV ? <Spinner></Spinner> : <Button onClick={(e) => addDiem(e)} variant="primary" className="btn-add-delete-diem" type="submit">Thêm Cột Điểm</Button>}
                            {deleDiemSV ? <Spinner></Spinner> : <Button onClick={(e) => deleteDiem(e)} className=" btn-danger btn-add-delete-diem" type="submit">Xóa cột điểm</Button>}
                        </div> : <></>}

                        {DSSinhVien.length !== 0 && checkKhoaDiem ? <><Link onClick={chotDiem} className="btn btn-secondary mt-2 mb-5 ">Chốt Điểm</Link></> : <></>}
                        <PDFGenerator data={DSSinhVien} />

                        {DSSinhVien.length !== 0 && checkKhoaDiem ? <Form onSubmit={updateDiem}>
                            <Form.Group className="mb-3">
                                <Form.Label>Chọn File Điểm</Form.Label>
                                <Form.Control type="file" ref={fileDiem} className="custom-file-input" />
                            </Form.Group>
                            <div class="form-group">
                                {loading === true ? <MySpinner /> : <Button class="btn btn-danger mt-2" type="submit">Thêm Điểm </Button>}
                                {thanhCong === true ? <Alert variant="secondary" className="mt-3">Cập nhập điểm thành công !!!</Alert> : <div></div>}
                                {pass === false ? <Alert variant="danger" className="mt-3">Vui lòng chọn file điểm</Alert> : <div></div>}
                                {checkCSV === true ? <Alert variant="danger" className="mt-3">Vui lòng chọn định dạng file .CSV</Alert> : <div></div>}
                            </div>
                        </Form> : <></>}
                    </div>
                </div>
            </div>
        </>
    );
};
export default CTMonHoc;
