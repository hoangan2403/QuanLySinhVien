import { useContext, useEffect, useState } from "react";
import { MyUserConText } from "../../App";
import { AuthApis, endpoints } from "../../configs/Apis";
import { Link, useNavigate } from "react-router-dom";
import Header from "../../layout/giangVien/Header";

const DanhSachMonChuaDay = () => {
  const [user, dispatch] = useContext(MyUserConText);
  const [monHoc, setMonHoc] = useState([]);


  useEffect(() => {
    const loadMonHoc = async () => {
      let e = endpoints['DSMHChuaDay'];
      e = `${e}?taiKhoanId=${user.idTaiKhoan}`;
      let res = await AuthApis().get(e);
      setMonHoc(res.data);
    }
    loadMonHoc();
  })
  return (
    <>
      <div class="contend">
        <Header />
        <div class="gv-monhoc-giaovien">
          {monHoc.map(mh => {
            let h = `/giangvien/danhsachsinhvien?monHocId=${mh.idMonHocHocKy}`;
            return (<div class="gv-items-monhoc-giaovien" key={mh.idMonHocHocKy}>
              <Link to={h}>
                <div class="gv-item-monhoc-giaovien">
                  <img
                    src="https://res.cloudinary.com/dhcvsbuew/image/upload/v1693123925/mon_bk4slc.png"
                    alt="Image description"
                    class="gv-image-monhoc-giaovien"
                  />
                </div>
                <div class="gv-item-monhoc-giaovien-text">
                  {mh.idMonHoc.tenMonHoc} - {mh.idHocky.idLop.tenLopHoc}
                </div>
              </Link>
            </div>)

          }

          )}
        </div>
      </div>
    </>
  );
};
export default DanhSachMonChuaDay;
