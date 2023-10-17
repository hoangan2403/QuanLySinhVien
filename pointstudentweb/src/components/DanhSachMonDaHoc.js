import { MyUserConText, SinhVienContext } from "../App";
import { useContext, useEffect, useState } from "react";
import { AuthApis, endpoints } from "../configs/Apis";
import MySpinner from "../layout/MySpinner";
import HeaderSV from "../layout/sinhvien/HeaderSV"



const DanhSachMonDaHoc = () => {

    const [user, dispatch] = useContext(MyUserConText);
    const [sinhvien] = useContext(SinhVienContext);

    const [DSDiemDaHoc, setDSDiemDaHoc] = useState([], null);

    useEffect(() => {
        const loadloadDSDiem = async () => {
            try {

                let d = endpoints['DSDiemDaHoc'];


                d = `${d}?SinhVienId=${sinhvien.idSinhVien}`;

                let res5 = await AuthApis().get(d);

                setDSDiemDaHoc(res5.data);

            } catch (ex) {
                console.error(ex);
            }

        }
        loadloadDSDiem();
    }, []);
    return (
        <div class="contend">
            <HeaderSV />
            <div class="point mt-5">
                <h2>Các Môn Đã Hoàn Thành</h2>
                <table class="table table-striped mt-5">
                    <thead>
                        <tr>
                            <th>Học Kỳ</th>
                            <th>Tên Môn Học</th>
                            <th>Lớp/Nhóm</th>
                            <th>Điểm Trung Bình</th>
                            <th>Xếp Loại</th>
                        </tr>
                    </thead>
                    <tbody>
                    {DSDiemDaHoc.map(c => {
                                    return (<tr key={c[5]}>
                                        <td>{c.hocKy}</td>
                                        <td>{c.tenMonHoc}</td>
                                        <td>{c.lopHoc}</td>
                                        <td>{c.diemTB}</td>
                                        <td>{c.xepLoai}</td>
                                    </tr>
                                    )
                                }


                            )
                            }
                            
                        </tbody>
                </table>
            </div>
        </div>
    )
}

export default DanhSachMonDaHoc;