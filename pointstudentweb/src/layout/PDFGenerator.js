import React from 'react';
import pdfMake from 'pdfmake/build/pdfmake';
import pdfFonts from 'pdfmake/build/vfs_fonts';

pdfMake.vfs = pdfFonts.pdfMake.vfs;

const PDFGenerator = ({ data }) => {
  const createPDF = (TenMonHoc) => {

    const documentDefinition = {
      content: [
        {
          text: 'Bảng Điểm Sinh Viên',
          style: 'header',
        },
        {
          table: {
            headerRows: 2,
            widths: ['auto', 'auto', 'auto', 'auto', 'auto', 'auto', 'auto', 'auto', 'auto'],
            body: [
              [
                'ID sinh viên',
                'Họ và tên',
                'Điểm 1',
                'Điểm 2',
                'Điểm 3',
                'Điểm Giữa Kỳ',
                'Điểm Cuối Kỳ',
                'Điểm Trung Bình',
                'Tình Trạng',
              ],
              ...data.map((sv) => [
                sv.monHoc.idSinhVien.idSinhVien,
                sv.monHoc.idSinhVien.hoTen,
                sv.diemKT1 !== -1 ? parseFloat(sv.diemKT1).toFixed(2) : '-',
                sv.diemKT2 !== -1 ? parseFloat(sv.diemKT2).toFixed(2) : '-',
                sv.diemKT3 !== -1 ? parseFloat(sv.diemKT3).toFixed(2) : '-',
                sv.diemGK !== -1 ? parseFloat(sv.diemGK).toFixed(2) : '-',
                sv.diemCK !== -1 ? parseFloat(sv.diemCK).toFixed(2) : '-',
                sv.diemTB !== -1 ? parseFloat(sv.diemTB).toFixed(2) : '0',
                sv.trangThai === 1 ? 'Đậu' : 'Rớt',
              ]),
            ],
          },
        },
      ],
      styles: {
        header: {
          fontSize: 18,
          bold: true,
          alignment: 'center',
          margin: [0, 0, 0, 10],
        },
      },
    };

    pdfMake.createPdf(documentDefinition).download(`${TenMonHoc}table-pdf.pdf`);
  };

  return (
    <button onClick={() =>createPDF(data.length > 0 ? data[0].tenMonHoc : "Tên")} className="btn btn-secondary mt-2 mb-5 btn-creat-pdf">
      Xuất File PDF
    </button>
  );
};

export default PDFGenerator;