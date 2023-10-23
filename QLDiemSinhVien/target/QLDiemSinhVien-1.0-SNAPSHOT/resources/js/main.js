function deleteSinhVien(path) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "delete"
        }).then(res => {
            if (res.status === 204)
                location.reload();
        });
    }
}
function deleteGiangVien(path) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "delete"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Xóa không thành công vì giang viên vẫn đang dạy một số môn !!!");
        });
    }
}
function deleteMonHoc(path) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "delete"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Xóa môn học không thành công vì một số lớp đang mở môn này !! ");
        });
    }
}
function deleteLopHoc(path) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "delete"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Xóa thất bại vì lớp học còn một số sinh viên đang học !!");
        });
    }
}
function deleteHocKi(path) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "delete"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Xóa thất bại vì học kì còn đang học");
        });
    }
}
function deleteKhoaDaoTao(path) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "delete"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Xóa thất bại vì khoa còn nghành đang học");
        });
    }
}
function deleteKhoaHoc(path) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "delete"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Xóa thất bại vi khóa học này còn sinh viên đang học");
        });
    }
}
function deletePhongHoc(path) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "delete"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Xóa thất bại vì phòng học có lớp đang học");
        });
    }
}
function deleteHeDaoTao(path) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "delete"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Xóa thất bại vì hệ đào tạo còn sinh viên đang học");
        });
    }
}
//function addToCart(idMonHoc) {
//    const idSinhVien = document.getElementById("idSinhVien").textContent
//    fetch(`/QLDiemSinhVien/giaovu/api/monhoc/${idMonHoc}?idSinhVien=${idSinhVien}`).then(res => {
//        if (!res.ok) {
//            throw new Error(`Network response was not ok: ${res.status}`);
//        }
//        return res.json();
//    }).then(data => {
//        var d = document.getElementById("cart-counter");
//        if (d !== null) {
//            d.innerText = data;
//        }
//
//        loadData();
//
//    })
//
//}

function addToCart(idMonHoc) {
    const idSinhVien = document.getElementById("idSinhVien").textContent;
    const buttonElement = event.currentTarget;

    // Gọi API để thêm môn học vào giỏ hàng
    if (buttonElement.innerText === "Chọn") {
        fetch(`/QLDiemSinhVien/giaovu/api/monhoc/${idMonHoc}?idSinhVien=${idSinhVien}`)
                .then(res => {
                    if (!res.ok) {
                        throw new Error(`Network response was not ok: ${res.status}`);
                    }
                    return res.json();
                })
                .then(data => {
                    var d = document.getElementById("cart-counter");
                    if (d !== null) {
                        d.innerText = data;
                    }

                    // Gọi API để lưu dữ liệu xuống server
                    const dataToSave = {
                        [idMonHoc]: {
                            idMonHoc: idMonHoc,
                            idSinhVien: idSinhVien
                        }
                    };

                    return fetch(`/QLDiemSinhVien/giaovu/api/phieuMH`, {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        body: JSON.stringify(dataToSave)
                    });
                })
                .then(response => {
                    if (response.ok) {
                        return response.text();
                    } else {
                        throw new Error(`Lưu thất bại: ${response.statusText}`);
                    }
                })
                .then(responseText => {
                    alert(responseText);
                    buttonElement.innerText = "Đã chọn";
                    buttonElement.onclick = function () {
                        unselectItem(idMonHoc);
                    };
                    loadData(); // Cập nhật lại dữ liệu sau khi lưu thành công
                })
                .catch(error => {
                    console.error(error);
                    alert("Lỗi hệ thống.");
                });
    } else if (buttonElement.innerText === "Hủy chọn") {

        unselectItem(idMonHoc)

    }
}
function unselectItem(idMonHoc) {
    const idSinhVien = document.getElementById("idSinhVien").textContent;
    const buttonElement = event.currentTarget;

    if (buttonElement.innerText === "Hủy chọn") {
        fetch(`/QLDiemSinhVien/giaovu/api/phieuMH/delete/${idMonHoc}?idSinhVien=${idSinhVien}`, {
            method: "DELETE",
        })
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`Xóa thất bại: ${response.statusText}`);
                    }
                    // If the response is OK, return it for further processing
                    return response;
                })
                .then(response => response.text())
                .then(responseText => {
                    var d = document.getElementById("cart-counter");
                    if (d !== null) {
                        var currentValue = parseInt(d.innerText, 10); // Parse current value as an integer
                        if (!isNaN(currentValue)) {
                            // Check if parsing was successful
                            var newValue = currentValue - 1; // Decrement by 1
                            d.innerText = newValue;
                        }
                    }
                    alert("Sản phẩm đã được xóa khỏi giỏ hàng.");


                    // Cập nhật nút thành "Chọn" và cập nhật sự kiện onclick

                    buttonElement.innerText = "Chọn";
                    buttonElement.onclick = function () {
                        addToCart(idMonHoc);
                    };
                    loadData();
                    // Cập nhật lại dữ liệu sau khi xóa thành công
                })
                .catch(error => {
                    console.error(error);
                    alert("Lỗi hệ thống khi xóa sản phẩm khỏi giỏ hàng.");
                });
    } else if (buttonElement.innerText === "Chọn") {
        addToCart(idMonHoc);
    }

}
function loadData() {
    console.log("Loading data...");
    var dataContainer = document.getElementById("table-monhoc");
    var spinner = document.getElementById("spinner");


    dataContainer.innerHTML = ""; // Clear existing content
    spinner.style.display = "block"; // Display spinner

    fetch("/QLDiemSinhVien/giaovu/api/cart")
            .then(response => response.json()).then(data => {
        console.log("API response:", data);
        spinner.style.display = "none"; // Hide spinner
        if (data.cart !== null) {
            var tableHtml = '<table id="customers"><thead><tr><th>Mã môn học</th><th>Tên môn học</th><th>Phòng học</th></tr></thead><tbody>';

            data.cart.forEach(cart => {
                tableHtml += `<tr><td>${cart.idMonHoc}</td><td>${cart.tenMonHoc}</td><td>${cart.phongHoc}</td></tr>`;
            });
            
            tableHtml += '</tbody></table>';
            dataContainer.innerHTML = tableHtml;
        } else {
            dataContainer.innerHTML = '<p>No carts available.</p>';
        }
    });

}
function luuPhieu() {

    fetch(`/QLDiemSinhVien/giaovu/api/phieuMH`, {
        method: "POST"
    })
            .then(response => response.text())
            .then(data => {
                if (data.includes("Đã lưu thành công.")) {
                    alert("Lưu thành công.");
                } else {
                    alert("Lưu thất bại.");
                }
            })
            .catch(error => {
                console.error(error);
                alert("Lỗi hệ thống.");
            });
}





