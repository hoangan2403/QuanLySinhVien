import { Link } from "react-router-dom";
import { GiangVienContext, MyUserConText } from "../App";
import { useContext } from "react";

const HeaderTeacher = () => {
  const [user, dispatch] = useContext(MyUserConText);
  const [giangvien, dispatchgv] = useContext(GiangVienContext);


  const logout = () => {

    dispatch({
      "type": "logout",
    });
    dispatchgv({
      "type": "logout_gv",
    })

  };
  return (
    <>
      <div class="gv-vertical-menu">
        <div class="active gv-logo-menu">
          <a href="#">
            <i class="fa-solid fa-book-open "></i>
            Trường Đại Học Mở
          </a>
        </div>
        <div class="menu-items">
          <p class="text-menu ">Dịch Vụ, Tiện Ích</p>
          <Link class="menu-item" to="/giangvien/home">
            <i class="fa-solid fa-house gv-icon-padding"></i>Tổng Quan
          </Link>
          <Link class="menu-item" to="/giangvien/diendan">
            <i class="fa-solid fa-message gv-icon-padding"></i>Diễn Đàn
          </Link>
          <Link class="menu-item" to="/giangvien/danhsachmonchuanbi">
            <i class="fa-solid fa-gift gv-icon-padding"></i>Môn Sắp Giảng Dạy
          </Link>
          <Link class="menu-item" to="/giangvien/danhmonhocdakhoa">
            <i class="fa-solid fa-gift gv-icon-padding"></i>Môn Đã Qua
          </Link>
        </div>
        <div class="menu-items">
          <p class="gv-text-menu">Thông Tin tài Khoản</p>
          <Link class="menu-item" to="/giangvien/thongtintaikhoan">
            <i class="fa-solid fa-user gv-icon-padding"></i>Thông Tin Tài Khoản
          </Link>
          <Link class="menu-item" to="/giangvien/thaydoimatkhau">
            <i class="fa-solid fa-key gv-icon-padding"></i>Thay Đổi Mật Khẩu
          </Link>
          <Link class="menu-item" onClick={logout} to="/">
            <i class="fa-solid fa-right-to-bracket icon-padding"></i>Thoát Quyền
            Sử Dụng
          </Link>
        </div>
      </div>
    </>
  );
};
export default HeaderTeacher;
