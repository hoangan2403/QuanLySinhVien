import { SinhVienContext } from "../App";
import { useContext, useEffect, useState } from "react";
import { AuthApis, endpoints } from "../configs/Apis";
import HeaderSV from "../layout/sinhvien/HeaderSV"


const XemDiem = () => {
    const [sinhvien] = useContext(SinhVienContext);
    const [DSDiem, setDSDiem] = useState([], null);
    const [DSDiemDaHoc, setDSDiemDaHoc] = useState([], null);
    const [DSDiem1, setDSDiem1] = useState([]);
    const [Diem, setDiem] = useState([], null);
    const [DiemHe4, setDiemHe4] = useState([]);
    useEffect(() => {
        const loadloadDSDiem = async () => {
            try {
                let c = endpoints['DSDiemTrungBinhHocKy'];
                let e = endpoints['DSDiem'];
                let d = endpoints['DSDiemDaHoc'];
                let a = endpoints['DiemTrungBinhHe10'];
                let b = endpoints['DiemTrungBinhHe4'];
                c = `${c}?SinhVienId=${sinhvien.idSinhVien}`;
                d = `${d}?SinhVienId=${sinhvien.idSinhVien}`;
                e = `${e}?SinhVienId=${sinhvien.idSinhVien}`;
                a = `${a}?SinhVienId=${sinhvien.idSinhVien}`;
                b = `${b}?SinhVienId=${sinhvien.idSinhVien}`;
                let res5 = await AuthApis().get(d);
                let res4 = await AuthApis().get(c);
                let res1 = await AuthApis().get(e);
                let res2 = await AuthApis().get(a);
                let res3 = await AuthApis().get(b);
                setDSDiemDaHoc(res5.data);
                setDSDiem1(res4.data);
                setDSDiem(res1.data);
                setDiem(res2.data);
                setDiemHe4(res3.data);
            } catch (ex) {
                console.error(ex);
            }

        }
        loadloadDSDiem();
    }, []);


    return (
        <><div class="contend">
            <HeaderSV />
            <div class="point">
                <h4>Tổng quan</h4>
                <h6 class="text-header-tong">Tổng Hợp Nhanh Các Thông Tin </h6>
                <div class="container mt-3">
                   
                    <p class="text-header-tong-1">Tổng Hợp Điểm & Xếp Loại Học Tập</p>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Điểm Hệ 10</th>
                                <th>Điểm Hệ 4</th>
                                <th>Xếp Loại</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>{Diem}</td>
                                <td>{DiemHe4}</td>
                                <td>
                                    {(() => {
                                        switch (true) {
                                            case Diem >= 8.5:
                                                return 'Giỏi';
                                            case Diem >= 7:
                                                return 'Khá';
                                            case Diem >= 5:
                                                return 'Trung bình';
                                            default:
                                                return 'Yếu';
                                        }
                                    })()}
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div><div class=" point-ky">
                <div class="container mt-3 mb-5">
                    <p class="text-header-tong-1">Tổng Hợp Điểm Của Từng Học Kỳ</p>
                    <table class="table">
                        <thead>
                            <tr>
                                <th >Học Kỳ</th>
                                <th>Điểm Hệ 10</th>
                                <th>Điểm hệ 4</th>
                            </tr>
                        </thead>
                        <tbody >
                            {DSDiem1.map(c => <tr key={c[2]}>
                                <td>{c[0]}</td>
                                <td>{parseFloat(c[1]).toFixed(2)}</td>
                                <td>{parseFloat(c[2]).toFixed(2)}</td>

                            </tr>
                            )}
                        </tbody>
                    </table>
                </div>
                <div class="container div-xemdiem-padding">
                    <p class="text-header-tong-1">Tổng Hợp Điểm Của Từng Môn Đang Học</p>
                    <table class="table table-striped">
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
                                        <td>{c.diemGK}</td>
                                        <td>{c.diemCK}</td>
                                        {c.diemTB !== null ? <td>{parseFloat(c.diemTB).toFixed(2)}</td> : <td>chưa có</td>}
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
                    <table class="table table-striped   ">
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
                            {DSDiemDaHoc.map(c => {

                                return (<tr key={c[5]}>
                                    <td>{c.hocKy}</td>
                                    <td>{c.tenMonHoc}</td>
                                    <td>{c.lopHoc}</td>
                                    {c.diemTK1 !== -1 ? <td>{parseFloat(c.diemKT1).toFixed(2)}</td> : <td>-</td>}
                                    {c.diemKT2 !== -1 ? <td>{parseFloat(c.diemKT2).toFixed(2)}</td> : <td>-</td>}
                                    {c.diemKT3 !== -1 ? <td>{parseFloat(c.diemKT3).toFixed(2)}</td> : <td>-</td>}
                                    <td>{c.diemGK}</td>
                                    <td>{c.diemCK}</td>
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
        </div></>
    )
}
export default XemDiem;