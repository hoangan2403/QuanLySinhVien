import { useContext, useEffect, useState } from "react";
import { MyUserConText } from "../../App";
import { AuthApis, endpoints } from "../../configs/Apis";
import { useNavigate, useSearchParams } from "react-router-dom";
import { Alert } from "react-bootstrap";
import Header from "../../layout/giangVien/Header";

const ChiTietSinhVien = () => {
    
    const [DSDiem, setDSDiem] = useState([], null);
    const [sinhVien, setSinhVien] =useState([]);
    const [q] = useSearchParams();
    const [DSDiemDaHoc, setDSDiemDaHoc] = useState([], null);
    useEffect(() => {
        const loadloadDSDiem = async () => {
            let idSinhVien = q.get("idSinhVien");
            
            try {
                let e = endpoints['DSDiem'];
                let d = endpoints['DSDiemDaHoc'];
                let c = endpoints['getSinhVienById'];
                d = `${d}?SinhVienId=${idSinhVien}`;
                e = `${e}?SinhVienId=${idSinhVien}`;
                c = `${c}?idSinhVien=${idSinhVien}`
                let res5 = await AuthApis().get(d);
                let res1 = await AuthApis().get(e);
                let res2 = await AuthApis().get(c);
                setDSDiemDaHoc(res5.data);
                setDSDiem(res1.data);
                setSinhVien(res2.data);
            } catch (ex) {
                console.error(ex);
            }

        }
        loadloadDSDiem();
    }, []);


    return (

        <div class="contend">
            <Header />
            <div class=" point-ky">
                <h4 class="text-header-tong-1">Thông tin Sinh viên</h4>
                <div class="container-info-stundent">

                    <div class="student-info-ad ">
                        <h5>Họ và tên: {sinhVien[0]}</h5>
                        <p>Mã sinh viên: {sinhVien[1]}</p>
                        <p>Lớp Học: {sinhVien[3]}</p>
                        <p>Ngành Học: {sinhVien[4]}</p>
                        <p>Địa chỉ: {sinhVien[5]} </p>
                        <p>Email: {sinhVien[6]}</p>
                    </div>
                    <div class="container mt-3">
                        <p class="text-header-tong-1">Tổng Hợp Điểm Của Từng Môn Đang Học</p>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Học Kỳ</th>
                                    <th>Môn Học</th>
                                    <th>Lớp/Nhóm</th>
                                    <th>Điểm KT1</th>
                                    <th>Điểm KT2</th>
                                    <th>Điểm KT3</th>
                                    <th>Điểm Giữa Kì</th>
                                    <th>Điểm Cuối Kỳ</th>
                                    <th>Điểm Trung Bình</th>
                                    <th>Tình Trạng</th>
                                </tr>
                            </thead>
                            <tbody>
                                {DSDiem.map(c => {
                                    if (c.khoaMon == 0) {
                                        return (<tr key={c[5]}>
                                            <td>{c.hocKy}</td>
                                            <td>{c.tenMonHoc}</td>
                                            <td>{c.lopHoc}</td>
                                            {c.diemKT1 !== -1 ? <td>{parseFloat(c.diemKT1).toFixed(2)}</td> : <td>-</td>}
                                            {c.diemKT2 !== -1 ? <td>{parseFloat(c.diemKT2).toFixed(2)}</td> : <td>-</td>}
                                            {c.diemKT3 !== -1 ? <td>{parseFloat(c.diemKT3).toFixed(2)}</td> : <td>-</td>}
                                            {c.diemGK !== -1 ? <td>{parseFloat(c.diemGK).toFixed(2)}</td> : <td>-</td>}
                                            {c.diemCK !== -1 ? <td>{parseFloat(c.diemCK).toFixed(2)}</td> : <td>-</td>}
                                            
                                            {c.diemTB !== null && c.diemTB !==-1 ? <td>{parseFloat(c.diemTB).toFixed(2)}</td> : <td>chưa có</td>}
                                            <td>Đang Học</td>
                                        </tr>
                                        )
                                    }
                                })
                                }

                            </tbody>
                        </table>
                    </div>
                    <div class="container mt-5">
                    <p class="text-header-tong-1">Tổng Hợp Điểm Các Môn Đã Hoàn Thành</p>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Học Kỳ</th>
                                <th>Môn Học</th>
                                <th>Lớp/Nhóm</th>
                                
                                <th>Điểm Trung Bình</th>
                                <th>Tình Trạng</th>
                            </tr>
                        </thead>
                        <tbody>
                            {DSDiemDaHoc.map(c => {

                                return (<tr key={c[5]}>
                                    <td>{c.hocKy}</td>
                                    <td>{c.tenMonHoc}</td>
                                    <td>{c.lopHoc}</td>
                                    
                                    {c.diemTB !== null ? <td>{parseFloat(c.diemTB).toFixed(2)}</td> : <td>chưa có</td>}

                                    <td>{c.trangThai === 1 ? 'Đậu' : 'Rớt'}</td>
                                </tr>
                                )
                            })
                            }


                        </tbody>
                    </table>
                </div>
                </div>
            </div>
        </div>
    )

};
export default ChiTietSinhVien;
