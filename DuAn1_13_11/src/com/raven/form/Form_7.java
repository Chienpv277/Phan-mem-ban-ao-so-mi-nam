/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import DomainModel.HoaDon;
import DomainModel.HoaDonViewModel;
import DomainModel.HoaDonChiTiet;
import DomainModel.KhachHang;
import DomainModel.NhanVien;
import DomainModel.ChiTietSanPham;
import DomainModel.SanPham;
import Repository.HoaDonRepository;
import Service.ChiTietService;
import ServiceImpl.ChiTietServiceImpl;
import ServiceImpl.HoaDonServiceImpl;
import Utilities.DBConnection;
import ViewModel.HoaDonChiTietViewModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import mode.TableMode;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author 84386
 */
public class Form_7 extends javax.swing.JPanel {

    HoaDonServiceImpl donService = new HoaDonServiceImpl();
    private DefaultTableModel model = new DefaultTableModel();
    private List<HoaDon> listHoaDon = new ArrayList<>();
    private List<KhachHang> list = new ArrayList<>();
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private ChiTietService chiTietService = new ChiTietServiceImpl();
    private ArrayList<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
    private HoaDonRepository hoaDonRespon = new HoaDonRepository();
    DefaultTableModel dtm;
    public static HoaDonViewModel hoadon = null;
//     private DefaultTableModel defaultTableModel = new DefaultTableModel();
    TableMode tbm;
    int index = 0;
    Connection cn;
    long count, soTrang, trang = 1;
    Statement st;
    ResultSet rs;
    int i = 1;
    public static String tienKhach;
    public static String tienTra;
    public static String tongTien;
    public static String tienGiam;
    public static String diemKH = "0";

    /**
     * Creates new form Form_7
     */
    public Form_7() {
        initComponents();
        loadDataCTSP(chiTietService.getListChiTiet());

//        loadtable2();
//        tb_hoaDon.setShowHorizontalLines(true);
//        tb_hoaDon.setShowVerticalLines(true);
//        loadtable();
        loadGioHang();
        txt_tenkhachhang.setText("Khách lẻ");
        txt_ma_kh.setText("1");
        cbb_trangthaihoadon.setSelectedItem("Chưa thanh toán");
        cboFilterTrangThai.setSelectedItem("Còn hàng");
        loadtableTrangThaiChuaThanhToan();
    }

    public void loadChonKH(String ten) {
        txt_tenkhachhang.setText(ten);
//        txtSDT.setText(sdt);
    }

    public void loadDataCTSP(ArrayList<ChiTietSanPham> chiTietSanPhams) {
        int i = 1;
        defaultTableModel = (DefaultTableModel) tblChiTietSanPham.getModel();
        defaultTableModel.setRowCount(0);
        for (DomainModel.ChiTietSanPham x : chiTietSanPhams) {
            defaultTableModel.addRow(new Object[]{
                i++,
                x.getId(),
                x.getSanPham().getTen(),
                x.getHang().getTen(),
                x.getMauSac().getTen(),
                x.getChatLieu().getTen(),
                x.getSize().getTen(),
                x.getDangAo().getTen(),
                x.getCoAo().getTen(),
                x.getCoTay().getTen(),
                x.getGia(),
                x.getSoLuong(),
                x.trangThai()
            });
        }
    }

    public void loadtableHoaDonChiTiet() {
        int i = 1;
        DefaultTableModel dtm = (DefaultTableModel) this.tb_giohnag.getModel();
        ArrayList<HoaDonChiTietViewModel> ds = this.donService.getListHoaDonChiTiet();
        dtm.setRowCount(0);
        for (HoaDonChiTietViewModel hv : ds) {
            Object[] rowdata = new Object[]{
                i++,
                hv.getId_chi_tiet_san_pham(),
                hv.getId_san_pham().getTen(),
                hv.getId_mau_sac().getTen(),
                hv.getId_hang().getTen(),
                hv.getId_size().getTen(),
                hv.getSo_luong(),
                hv.getGia(),
                hv.getId_hoa_don().getTrang_thai() == 1 ? "Đã thanh toán" : "Chưa thanh toán",};
            dtm.addRow(rowdata);
        }
    }

    private void fillToTable(List<HoaDon> listHoaDon) {
        model.setRowCount(0);
        for (HoaDon hv : listHoaDon) {
            Object[] rowdata = new Object[]{
                i++,
                hv.getId_hoa_don(),
                hv.getId_khach_hang().getHo_ten(),
                hv.getId_nhan_vien().getHo_ten(),
                hv.getHinh_thuc_thanh_toan(),
                hv.getCreated_at(),
                hv.getTrang_thai() == 1 ? "Đã thanh toán" : "Chưa thanh toán"
//                hv.getId_voucher().getKhuyen_mai(),
            };
            dtm.addRow(rowdata);
        }
    }

    //load table
    public void loadtable() {
        int i = 1;
        DefaultTableModel dtm = (DefaultTableModel) this.tb_hoaDon.getModel();
//        ArrayList<HoaDon> ds = this.donService.getListHoaDon();
        ArrayList<HoaDon> ds = this.hoaDonRespon.findbyHD();
        dtm.setRowCount(0);
        for (HoaDon hv : ds) {
            Object[] rowdata = new Object[]{
                i++,
                hv.getId_hoa_don(),
                hv.getId_khach_hang().getHo_ten(),
                hv.getId_nhan_vien().getHo_ten(),
                hv.getHinh_thuc_thanh_toan(),
                hv.getCreated_at(),
                hv.getTrang_thai() == 1 ? "Đã thanh toán" : "Chưa thanh toán"
//                hv.getId_voucher().getKhuyen_mai(),
            };
            dtm.addRow(rowdata);
        }
    }

    public void loadGioHang() {
        DefaultTableModel dtm = (DefaultTableModel) this.tb_giohnag.getModel();
        dtm.setRowCount(0);
        int row = this.tb_hoaDon.getSelectedRow();
        if (row == -1) {
            return;
        }
        int id = Integer.parseInt(tb_hoaDon.getValueAt(row, 1).toString());
        ArrayList<HoaDonChiTietViewModel> hv1 = this.hoaDonRespon.getListHoaDonChiietByIdGioHang(id);
        for (HoaDonChiTietViewModel hv : hv1) {
            Object[] rowdata = new Object[]{
                //                i++,
                hv.getId_hoa_don().getId_hoa_don(),
                hv.getId_san_pham().getTen(),
                hv.getSo_luong(),
                hv.getGia(),
                hv.getThanh_tien()
            };
            dtm.addRow(rowdata);
        }
    }

    public void loadtableTrangThaiDaThanhToan() {
        int i = 1;
        DefaultTableModel dtm = (DefaultTableModel) this.tb_hoaDon.getModel();
        ArrayList<HoaDon> ds = this.donService.findbytrangthaiDaThanhToan();
        dtm.setRowCount(0);
        for (HoaDon hv : ds) {
            Object[] rowdata = new Object[]{
                i++,
                hv.getId_hoa_don(),
                hv.getId_khach_hang().getHo_ten(),
                hv.getId_nhan_vien().getHo_ten(),
                hv.getHinh_thuc_thanh_toan(),
                hv.getCreated_at(),
                hv.getTrang_thai() == 1 ? "Đã thanh toán" : "Chưa thanh toán"
//                hv.getId_voucher().getKhuyen_mai(),
            };
            dtm.addRow(rowdata);
        }
    }

    public void loadtableTrangThaiChuaThanhToan() {
        int i = 1;
        DefaultTableModel dtm = (DefaultTableModel) this.tb_hoaDon.getModel();
//        ArrayList<HoaDon> ds = this.hoaDonRespon.findbytrangthaiChuaThanhToanHD();
        ArrayList<HoaDon> ds = this.hoaDonRespon.findbyHDchuatt();
        dtm.setRowCount(0);
//        Collections.sort(list, Comparator.comparing(HoaDon -> HoaDon.getId_khach_hang()));
        for (HoaDon hv : ds) {
            Object[] rowdata = new Object[]{
                i++,
                hv.getId_hoa_don(),
                hv.getId_khach_hang().getHo_ten(),
                hv.getId_nhan_vien().getHo_ten(),
                hv.getHinh_thuc_thanh_toan(),
                hv.getCreated_at(),
                hv.getTrang_thai() == 1 ? "Đã thanh toán" : "Chưa thanh toán"
//                hv.getId_voucher().getKhuyen_mai(),
            };
            dtm.addRow(rowdata);
        }
    }

    public void loadtableTrangThaiChuaThanhToanHoaDonMoi() {
        int i = 1;
        DefaultTableModel dtm = (DefaultTableModel) this.tb_hoaDon.getModel();
        ArrayList<HoaDon> ds = this.hoaDonRespon.findbytrangthaiChuaThanhToanHDNull();
        dtm.setRowCount(0);
        for (HoaDon hv : ds) {
            Object[] rowdata = new Object[]{
                i++,
                hv.getId_hoa_don(),
                hv.getId_khach_hang().getHo_ten(),
                hv.getId_nhan_vien().getHo_ten(),
                hv.getTong_tien(),
                hv.getHinh_thuc_thanh_toan(),
                hv.getCreated_at(),
                hv.getNgay_thanh_toan(),
                hv.getTrang_thai() == 1 ? "Đã thanh toán" : "Chưa thanh toán"
//                hv.getId_voucher().getKhuyen_mai(),
            };
            dtm.addRow(rowdata);
        }
    }

    private void tongTien() {
        float tong = 0;
        ArrayList<HoaDonChiTiet> listHDCTM = hoaDonRespon.getListHoaDonChiTietThanhToan();
        ArrayList<HoaDonChiTiet> listNew = new ArrayList<>();
        for (HoaDonChiTiet x : listHDCTM) {
            if (x.getId_hoa_don() != null && String.valueOf(x.getId_hoa_don().getId_hoa_don()).equals(txt_mahoadon.getText())) {
                listNew.add(x);
            }
        }

        for (HoaDonChiTiet hoaDonChiTiet : listNew) {
            tong += hoaDonChiTiet.getThanh_tien();
        }
        txt_tongtien.setText(String.valueOf(tong));
        hoadon.setTong_tien(tong);
        tongTien = String.valueOf(tong);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblChiTietSanPham = new javax.swing.JTable();
        cboSearch = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        cboFilterTrangThai = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_giohnag = new javax.swing.JTable();
        btnXoaGioHang = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_hoaDon = new javax.swing.JTable();
        cbb_trangthaihoadon = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txt_tenkhachhang = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_mahoadon = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_tongtien = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_tienkhachdua = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_tienthua = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbb_phuongthucthanhtoan = new javax.swing.JComboBox<>();
        btn_taohoadon = new javax.swing.JButton();
        btn_thanhtoan = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btn_huyhoadon = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_ma_kh = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Sản phẩm ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 14), new java.awt.Color(255, 0, 0))); // NOI18N

        tblChiTietSanPham.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tblChiTietSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã CTSP", "Tên sản phẩm", "Hãng", "Màu sắc", "Chất liệu", "Size", "Dáng áo", "Cổ áo", "Cổ tay", "Giá", "Số lượng tồn", "Trạng thái"
            }
        ));
        tblChiTietSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietSanPhamMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblChiTietSanPham);

        cboSearch.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cboSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm kiếm", "Sản phẩm", "Hãng", "Màu sắc", "Chất liệu", "Size", "Dáng áo", "Cổ áo", "Cổ tay", "Trạng thái" }));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        cboFilterTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cboFilterTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trạng thái", "Còn hàng", "Hết hàng" }));
        cboFilterTrangThai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboFilterTrangThaiItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(cboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addComponent(cboFilterTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboFilterTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Giỏ hàng ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 14), new java.awt.Color(255, 0, 0))); // NOI18N

        tb_giohnag.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tb_giohnag.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Sản phẩm", "Số lượng", "Giá", "Thành tiền"
            }
        ));
        tb_giohnag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_giohnagMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tb_giohnag);

        btnXoaGioHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Remove from basket.png"))); // NOI18N
        btnXoaGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaGioHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnXoaGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Hóa đơn ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 14), new java.awt.Color(255, 0, 0))); // NOI18N

        tb_hoaDon.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tb_hoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hóa đơn", "Khách hàng", "Nhân viên", "Hình thức thanh toán", "Ngày tạo", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_hoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_hoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_hoaDon);

        cbb_trangthaihoadon.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbb_trangthaihoadon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Chưa thanh toán", "Đã thanh toán" }));
        cbb_trangthaihoadon.setSelectedIndex(1);
        cbb_trangthaihoadon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_trangthaihoadonItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbb_trangthaihoadon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(cbb_trangthaihoadon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 14), new java.awt.Color(255, 0, 0))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel7.setText("Tên khách hàng");

        txt_tenkhachhang.setEditable(false);
        txt_tenkhachhang.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_tenkhachhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tenkhachhangActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton1.setText("Chon");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(78, 78, 78))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_tenkhachhang)))
                .addGap(18, 18, 18))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tenkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Thanh toán ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 14), new java.awt.Color(255, 0, 0))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel1.setText("Mã hóa đơn");

        txt_mahoadon.setEditable(false);
        txt_mahoadon.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setText("Tổng tiền");

        txt_tongtien.setEditable(false);
        txt_tongtien.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setText("Tiền khách đưa");

        txt_tienkhachdua.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_tienkhachdua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_tienkhachduaCaretUpdate(evt);
            }
        });
        txt_tienkhachdua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tienkhachduaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setText("Tiền thừa");

        txt_tienthua.setEditable(false);
        txt_tienthua.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel5.setText("Thanh toán");

        cbb_phuongthucthanhtoan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbb_phuongthucthanhtoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản" }));
        cbb_phuongthucthanhtoan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_phuongthucthanhtoanItemStateChanged(evt);
            }
        });

        btn_taohoadon.setText("Tạo hóa đơn");
        btn_taohoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_taohoadonActionPerformed(evt);
            }
        });

        btn_thanhtoan.setText("Thanh toán");
        btn_thanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thanhtoanActionPerformed(evt);
            }
        });

        jButton2.setText("Thanh toán + In");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btn_huyhoadon.setText("Hủy hóa đơn");
        btn_huyhoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huyhoadonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setText("Mã nhân viên");

        txt_ma_kh.setEditable(false);
        txt_ma_kh.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_taohoadon, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(btn_thanhtoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_huyhoadon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addGap(9, 9, 9)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(44, 44, 44)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_tienkhachdua)
                                            .addComponent(txt_tienthua)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbb_phuongthucthanhtoan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txt_tongtien)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_ma_kh)
                                    .addComponent(txt_mahoadon))))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_ma_kh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_mahoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_tongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbb_phuongthucthanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txt_tienkhachdua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txt_tienthua, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(btn_thanhtoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_huyhoadon, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(btn_taohoadon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblChiTietSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSanPhamMouseClicked
        // lấy ra index của bảng hóa đơn
        int indexHD = tb_hoaDon.getSelectedRow();
        if (indexHD < 0) {
            JOptionPane.showMessageDialog(null, "Mời bạn chọn hóa đơn !");
            return;
        }
        String maHD = tb_hoaDon.getValueAt(indexHD, 1).toString();
        HoaDonChiTiet h = new HoaDonChiTiet();
        String soLuong = JOptionPane.showInputDialog(this, "Mời bạn chọn số lượng");
        if (soLuong.isEmpty()) {
            return;
        }
        int indexSP = tblChiTietSanPham.getSelectedRow();
        int slTon = Integer.valueOf(tblChiTietSanPham.getValueAt(indexSP, 11).toString());
        if (slTon <= 0) {
            JOptionPane.showMessageDialog(null, "Hết hàng");
            return;
        }
        if (Integer.valueOf(soLuong) > slTon) {
            JOptionPane.showMessageDialog(null, "Số lượng tồn không đủ");
            return;
        }

        String idCTSP = tb_hoaDon.getValueAt(indexHD, 1).toString();
        int idCTSP1 = Integer.parseInt(tb_hoaDon.getValueAt(indexHD, 1).toString() + "");
        int ihHD = Integer.parseInt(tblChiTietSanPham.getValueAt(indexSP, 1).toString() + "");
        String tenSP = tblChiTietSanPham.getValueAt(indexSP, 2).toString();
        String donGia = tblChiTietSanPham.getValueAt(indexSP, 10).toString();
        h.setGia(Float.valueOf(Integer.valueOf(donGia.replace(".", "")) * Integer.valueOf(soLuong)));
        HoaDon newHD = hoaDonRespon.gethdBymaHD(Integer.parseInt(idCTSP));
        ChiTietSanPham newCTSP = hoaDonRespon.gethdBymaCTSP(ihHD);
        int idnewHD = newHD.getId_hoa_don();
        h.setId_hoa_don(newHD);
        if (h == null) {
            return;
        }
        ArrayList<HoaDonChiTiet> listHDCT = donService.getListHoaDonChiietByIdSP(idnewHD);
        System.out.println(listHDCT);
//        int dem = 0;
//        if (listHDCT.size() > 0) {
//
//            for (HoaDonChiTiet y1 : listHDCT) {
//                if (String.valueOf(y1.getId_chi_tiet_san_pham().getId()).equals(idCTSP)) {
//                    if (y1.getSo_luong() == slTon || ((y1.getSo_luong()) + Integer.valueOf(soLuong)) > slTon) {
//                        JOptionPane.showMessageDialog(null, "Không thể vượt quá số lượng đang có");
//                        return;
//                    }
//                    h.setSo_luong(y1.getSo_luong() + Integer.valueOf(soLuong));
//                    float thanhtien = Float.valueOf(Integer.valueOf(donGia.replace(".", "")) * (y1.getSo_luong() + Integer.valueOf(soLuong)));
//                    h.setThanh_tien(thanhtien);
//                    txt_tongtien.setText(String.valueOf(thanhtien));
//                    donService.updateHDCT(y1);
//
//                    loadGioHang();
//                    dem++;
//                }
//            }
//        }

//        int dem = 0;
//        if (listHDCT.size() > 0) {
//            for (HoaDonChiTiet y1 : listHDCT) {
//                if (Integer.valueOf(y1.getId_chi_tiet_san_pham().getId()).equals(idCTSP)) {
//                    // Item with the same ID found in the shopping cart
//                    if (y1.getSo_luong() == slTon || ((y1.getSo_luong()) + Integer.valueOf(soLuong)) > slTon) {
//                        JOptionPane.showMessageDialog(null, "Không thể vượt quá số lượng đang có");
//                        return;
//                    }
//
//                    // Update the quantity and total price
//                    h.setSo_luong(y1.getSo_luong() + Integer.valueOf(soLuong));
//                    float thanhtien = Float.valueOf(Integer.valueOf(donGia.replace(".", "")) * (y1.getSo_luong() + Integer.valueOf(soLuong)));
//                    h.setThanh_tien(thanhtien);
//                    txt_tongtien.setText(String.valueOf(thanhtien));
//
//                    // Update the existing item in the shopping cart
//                    y1.setSo_luong(h.getSo_luong());
//                    y1.setThanh_tien(h.getThanh_tien());
//                    donService.updateHDCT(y1);
//
//                    loadGioHang();
//                    dem++;
//                }
//            }
//        }
//
//        if (dem == 0) {
//            h.setSo_luong(Integer.valueOf(soLuong));
//            long millis = System.currentTimeMillis();
//            java.sql.Date date = new java.sql.Date(millis);
//            System.out.println(date);
//            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(newHD, newCTSP, Integer.parseInt(soLuong), Float.parseFloat(donGia));
//            float thanhtien = Float.parseFloat(soLuong) * Float.parseFloat(donGia);
//            HoaDonChiTiet chiTiet = new HoaDonChiTiet(newHD, newCTSP, Integer.parseInt(soLuong), Float.parseFloat(donGia), thanhtien);
//            donService.add(chiTiet);
//            loadGioHang();
//            tongTien();
//
//        }
        int dem = 0;
        String idChiTiet = tblChiTietSanPham.getValueAt(indexSP, 1).toString();
        if (listHDCT.size() > 0) {
            for (HoaDonChiTiet y1 : listHDCT) {
                if (Integer.valueOf(y1.getId_chi_tiet_san_pham().getId()).equals(idCTSP)) {
                    // Item with the same ID found in the shopping cart
                    if (y1.getSo_luong() == slTon || ((y1.getSo_luong()) + Integer.valueOf(soLuong)) > slTon) {
                        JOptionPane.showMessageDialog(null, "Không thể vượt quá số lượng đang có");
                        return;
                    }

                    // Update the quantity and total price
                    h.setSo_luong(y1.getSo_luong() + Integer.valueOf(soLuong));
                    float thanhtien = Float.valueOf(Integer.valueOf(donGia.replace(".", "")) * (y1.getSo_luong() + Integer.valueOf(soLuong)));
                    h.setThanh_tien(thanhtien);
                    txt_tongtien.setText(String.valueOf(thanhtien));

                    // Update the existing item in the shopping cart
                    y1.setSo_luong(h.getSo_luong());
                    y1.setThanh_tien(h.getThanh_tien());
                    donService.updateHDCT(y1);

                    loadGioHang();
                    dem++;
                }
            }
        }

        if (dem == 0) {
            h.setSo_luong(Integer.valueOf(soLuong));
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            System.out.println(date);
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(newHD, newCTSP, Integer.parseInt(soLuong), Float.parseFloat(donGia));
            float thanhtien = Float.parseFloat(soLuong) * Float.parseFloat(donGia);
            HoaDonChiTiet chiTiet = new HoaDonChiTiet(newHD, newCTSP, Integer.parseInt(soLuong), Float.parseFloat(donGia), thanhtien);
            donService.add(chiTiet);
            loadGioHang();
            tongTien();

        }
    }//GEN-LAST:event_tblChiTietSanPhamMouseClicked

    private void tb_giohnagMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_giohnagMouseClicked
//        int indexHD = tb_giohnag.getSelectedRow();
//        if (indexHD < 0) {
//            JOptionPane.showMessageDialog(null, "Mời bạn chọn sản phẩm !");
//            return;
//        }
//        String maHD = tb_hoaDon.getValueAt(indexHD, 1).toString();
//        HoaDonChiTiet h = new HoaDonChiTiet();
//        String soLuong = JOptionPane.showInputDialog(this, "Mời bạn chọn số lượng");
//        if (soLuong.isEmpty()) {
//            return;
//        }
    }//GEN-LAST:event_tb_giohnagMouseClicked

    private void tb_hoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_hoaDonMouseClicked
        int i = 1;
        DefaultTableModel dtm = (DefaultTableModel) this.tb_giohnag.getModel();
        dtm.setRowCount(0);
        int row = this.tb_hoaDon.getSelectedRow();
        if (row == -1) {
            return;
        }
        int id = Integer.parseInt(tb_hoaDon.getValueAt(row, 1).toString());
//        HoaDonChiTietViewModel hv = this.hoaDonRespon.getListHoaDonChiietByIdGioHang(id);
        ArrayList<HoaDonChiTietViewModel> hv1 = this.hoaDonRespon.getListHoaDonChiietByIdGioHang(id);
        for (HoaDonChiTietViewModel hv : hv1) {
            Object[] rowdata = new Object[]{
                i++,
                hv.getId_hoa_don().getId_hoa_don(),
                hv.getId_san_pham().getTen(),
                hv.getSo_luong(),
                hv.getGia(),
                hv.getThanh_tien()
            };
            dtm.addRow(rowdata);
        }

        //load textfile
        txt_mahoadon.setText(tb_hoaDon.getValueAt(row, 1).toString());
        txt_tenkhachhang.setText(tb_hoaDon.getValueAt(row, 2).toString());

        loadGioHang();
        tongTien();
    }//GEN-LAST:event_tb_hoaDonMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
//        KhachHangBanHang vp = new KhachHangBanHang();
//        vp.setVisible(true);
        new KhachHangBanHang(this, tb_giohnag, tb_hoaDon).setVisible(true);
//        this.setDefaultC;
//        KhachHangBanHang khachHangBanHang = new KhachHangBanHang();
//        khachHangBanHang.setVisible(true);
//        khachHangBanHang.pack();
//        khachHangBanHang.setLocationRelativeTo(null);
//        khachHangBanHang.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_tenkhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tenkhachhangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenkhachhangActionPerformed

    private void btn_taohoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_taohoadonActionPerformed

        //moi
        HoaDon hdNew = new HoaDon();
        hdNew.setTrang_thai(0);
        //        hdNew.setId_khach_hang(39);
        //        ArrayList<HoaDon> listHD = hoaDonRespon.findbyHD();
        //        int so = listHD.size() + 1;
        //        int nv = Integer.parseInt(txt_nhanvien.getText());
        //        HoaDon idnv = this.donService.getByHoaDonNhanVien(nv);
        //        NhanVien idnvv = kh.getId_nhan_vien();
        //        hdNew.setId_nhan_vien(idnvv);
        if (hdNew == null) {
            JOptionPane.showMessageDialog(null, "Tạo thất bại");
            return;

        }
        ArrayList<HoaDon> listCho = hoaDonRespon.findbytrangthaiChuaThanhToanHDNull();

        if (listCho.size() >= 5) {
            JOptionPane.showMessageDialog(null, "Đã đạt tối đa 5 hóa đơn. Vui lòng thanh toán hóa đơn chưa thanh toán");
            return;
        }
        System.out.println(hdNew);
        if (hoaDonRespon.addHoaDonMoi(hdNew) != null) {
            JOptionPane.showMessageDialog(null, "Tạo hóa đơn thành công");
            //            loadtableTrangThaiChuaThanhToanHoaDonMoi();
            loadtableTrangThaiChuaThanhToan();
        } else {
            JOptionPane.showMessageDialog(null, "Tạo thất bại");
        }
        txt_tenkhachhang.setText("Khách lẻ");
    }//GEN-LAST:event_btn_taohoadonActionPerformed
    private void clearThanhToan() {
        txt_mahoadon.setText("");
        txt_tongtien.setText("");
        txt_tienkhachdua.setText("");
        txt_tienthua.setText("");
        cbb_phuongthucthanhtoan.setSelectedItem("Tiền mặt");

        DefaultTableModel defaultTableModell = (DefaultTableModel) tb_giohnag.getModel();
        defaultTableModell.setRowCount(0);
    }
    private void btn_thanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thanhtoanActionPerformed
        if (txt_tienkhachdua.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Tiền khách đưa không được để trống");
        }

        String txtkh = txt_tenkhachhang.getText();
        //        HoaDon kh = this.donService.getByHoaDonTenKhachHang(txtkh);
        HoaDon kh = this.hoaDonRespon.getByHoaDonTenKhachHang1(txtkh);
        KhachHang idkh = kh.getId_khach_hang();
        int nv = Integer.parseInt(txt_ma_kh.getText());
        HoaDon idnv = this.donService.getByHoaDonNhanVien(nv);
        NhanVien idnvv = idnv.getId_nhan_vien();

        String tienThua = txt_tienthua.getText();
        String tongTien = txt_tongtien.getText();
        String maHD = txt_mahoadon.getText();

        float tong = Float.valueOf(tongTien);
        float tienKH = Float.valueOf(txt_tienkhachdua.getText());
        float tienThua_number = 0;
        if (tienThua != null) {
            tienThua_number = Float.parseFloat(tienThua);
        }
        if (tienKH < tong) {
            JOptionPane.showMessageDialog(null, "Chua du dieu kien thanh toan");
            return;
        }

        //        String hinhThucThanhToan = cbb_phuongthucthanhtoan.getSelectedItem();
        String hinhThucThanhToan = String.valueOf(cbb_phuongthucthanhtoan.getSelectedItem());
        //        String ghichu = txtGhiChu.getText();
        ArrayList<HoaDonChiTietViewModel> listHDCT = donService.getListHoaDonChiTiet();
        ArrayList<HoaDonChiTietViewModel> listNewHDCT = new ArrayList<>();
        HoaDonChiTietViewModel hdctvm = new HoaDonChiTietViewModel();
        HoaDon hoadon = null;
        ChiTietSanPham ctsp = new ChiTietSanPham();
        HoaDon h = new HoaDon();
        //        Float thanhTien = new Float(tong);
        h.setTong_tien(tong);
        h.setId_hoa_don(Integer.parseInt(maHD));
        h.setHinh_thuc_thanh_toan(hinhThucThanhToan);
        h.setId_khach_hang(idkh);
        h.setId_nhan_vien(idnvv);

        System.out.println(" 1" + tong);
        System.out.println(" 2" + Integer.parseInt(maHD));
        System.out.println(" 3" + hinhThucThanhToan);
        System.out.println(" 4" + idkh);
        System.out.println(" 5" + idnvv);
        System.out.println(" 5" + h);
        hoaDonRespon.updatehoadon_thanhtoan(h);

        hoaDonRespon.updatehoadon_thanhtoan(h);
        // Hiển thị hộp thoại xác nhận
        int confirmResult = JOptionPane.showConfirmDialog(
                this,
                "Bạn chắc chắn muốn thanh toán hóa đơn : " + h.getId_hoa_don() + " ?",
                "Xác nhận cập nhật",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmResult == JOptionPane.YES_OPTION) {
            if (hoaDonRespon.updatehoadon_thanhtoan(h) != null) {
                for (HoaDonChiTietViewModel x : listHDCT) {
                    if (x.getId_hoa_don() != null && String.valueOf(x.getId_hoa_don().getId_hoa_don()).equals(txt_mahoadon.getText())) {
                        listNewHDCT.add(x);
                    }
                }
                for (HoaDonChiTietViewModel y : listNewHDCT) {
                    ctsp.setSoLuong(y.getSo_luong());
                    ctsp.setId(y.getId_chi_tiet_san_pham().getId());
                    hoaDonRespon.updatehoadon_thanhtoan_soluong(ctsp);
                }
                JOptionPane.showMessageDialog(null, "Thanh toán thành công");
                loadtable();
                clearThanhToan();
            }
        } else {
            // Người dùng không xác nhận cập nhật
            JOptionPane.showMessageDialog(this, "Thanh toán thất bại");
        }
        tienKhach = txt_tienkhachdua.getText();
        tienTra = txt_tienthua.getText();
        //        tienGiam = txtGiamGia.getText();

        loadtableTrangThaiChuaThanhToan();
        loadDataCTSP(chiTietService.getListChiTiet());
    }//GEN-LAST:event_btn_thanhtoanActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        MessageFormat header = new MessageFormat("Hoa don");
        MessageFormat footer = new MessageFormat("(0, number, interger)");
        try {
            PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
            set.add(OrientationRequested.LANDSCAPE);
            tb_giohnag.print(JTable.PrintMode.FIT_WIDTH, header, footer, true, set, true);
            JOptionPane.showMessageDialog(null, "Printf succefully");
        } catch (java.awt.print.PrinterException e) {
            JOptionPane.showMessageDialog(null, "failed");
        }

        btn_thanhtoanActionPerformed(evt);
        System.out.println(hoadon.getId_hoa_don());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_huyhoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyhoadonActionPerformed
        // TODO add your handling code here:
        String maHD = txt_mahoadon.getText();
        HoaDon h = new HoaDon();
        h.setId_hoa_don(Integer.parseInt(maHD));

        // Hiển thị hộp thoại xác nhận
        int confirmResult = JOptionPane.showConfirmDialog(
                this,
                "Bạn chắc chắn muốn hủy hóa đơn : " + txt_mahoadon.getText() + " ?",
                "Xác nhận cập nhật",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmResult == JOptionPane.YES_OPTION) {
            if (hoaDonRespon.updatehoadon_huy(h) != null) {
                JOptionPane.showMessageDialog(null, "Hủy hóa đơn thành công");

            }
        } else {
            // Người dùng không xác nhận cập nhật
            JOptionPane.showMessageDialog(this, "Hủy hóa đơn thất bại");
        }

        loadtable();
    }//GEN-LAST:event_btn_huyhoadonActionPerformed

    private void cbb_trangthaihoadonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_trangthaihoadonItemStateChanged
        String selecteditem = (String) cbb_trangthaihoadon.getSelectedItem();
        if (selecteditem.equals("Đã thanh toán")) {
            loadtableTrangThaiDaThanhToan();
        } else if (selecteditem.equals("Chưa thanh toán")) {
            loadtableTrangThaiChuaThanhToan();
        } else if (selecteditem.equals("Tất cả")) {
            loadtable();
        }
    }//GEN-LAST:event_cbb_trangthaihoadonItemStateChanged

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        String selected = cboSearch.getSelectedItem().toString();
        if (selected.equals("Chất liệu")) {
            chiTietSanPhams.clear();
            String strFind = "";
            strFind = txtSearch.getText();
            ArrayList<ChiTietSanPham> listSearch = chiTietService.searchByChatLieu(strFind);
            loadData(listSearch);
        } else if (selected.equals("Sản phẩm")) {
            chiTietSanPhams.clear();
            String strFind = "";
            strFind = txtSearch.getText();
            ArrayList<ChiTietSanPham> listSearch = chiTietService.searchByTen(strFind);
            loadData(listSearch);
        } else if (selected.equals("Hãng")) {
            chiTietSanPhams.clear();
            String strFind = "";
            strFind = txtSearch.getText();
            ArrayList<ChiTietSanPham> listSearch = chiTietService.searchByHang(strFind);
            loadData(listSearch);
        } else if (selected.equals("Dáng áo")) {
            chiTietSanPhams.clear();
            String strFind = "";
            strFind = txtSearch.getText();
            ArrayList<ChiTietSanPham> listSearch = chiTietService.searchByDangAo(strFind);
            loadData(listSearch);
        } else if (selected.equals("Màu sắc")) {
            chiTietSanPhams.clear();
            String strFind = "";
            strFind = txtSearch.getText();
            ArrayList<ChiTietSanPham> listSearch = chiTietService.searchByMauSac(strFind);
            loadData(listSearch);
        } else if (selected.equals("Cổ tay")) {
            chiTietSanPhams.clear();
            String strFind = "";
            strFind = txtSearch.getText();
            ArrayList<ChiTietSanPham> listSearch = chiTietService.searchByCoTay(strFind);
            loadData(listSearch);
        } else if (selected.equals("Cổ áo")) {
            chiTietSanPhams.clear();
            String strFind = "";
            strFind = txtSearch.getText();
            ArrayList<ChiTietSanPham> listSearch = chiTietService.searchByCoAo(strFind);
            loadData(listSearch);
        } else if (selected.equals("Size")) {
            chiTietSanPhams.clear();
            String strFind = "";
            strFind = txtSearch.getText();
            ArrayList<ChiTietSanPham> listSearch = chiTietService.searchBySize(strFind);
            loadData(listSearch);
        } else {
            loadDataCTSP(chiTietService.getListChiTiet());
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void cboFilterTrangThaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboFilterTrangThaiItemStateChanged
        String selected = cboFilterTrangThai.getSelectedItem().toString();

// Lấy tên cột cần lọc trong table
        String key = tblChiTietSanPham.getColumnName(12);
        ArrayList<ChiTietSanPham> list = new ArrayList<>();

        if (selected.equals("Trạng thái")) {
            loadDataCTSP(chiTietService.getListChiTiet());
        } else {
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblChiTietSanPham.getModel());
            tblChiTietSanPham.setRowSorter(sorter);

            RowFilter<TableModel, Object> filter;
            if ("Còn hàng".equals(selected) || "Hết hàng".equals(selected)) {
                filter = RowFilter.regexFilter(selected, tblChiTietSanPham.getColumnModel().getColumnIndex(key));
            } else {
                // Nếu không phải trạng thái cụ thể, hiển thị tất cả các dòng
                filter = null;
            }
            sorter.setRowFilter(filter);

            // Tính lại STT và cập nhật bảng hiển thị
            updateSTTColumn();
        }

// ...

    }//GEN-LAST:event_cboFilterTrangThaiItemStateChanged

    private void btnXoaGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaGioHangActionPerformed

        int indexGH = tb_giohnag.getSelectedRow();
        if (indexGH < 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm muốn xóa");
            return;
        }

        int indexHD = tb_giohnag.getSelectedRow();
        int maHD = Integer.parseInt(tb_giohnag.getValueAt(indexHD, 0).toString());

        String tenSP = tb_giohnag.getValueAt(indexGH, 1).toString();
        System.out.println(tenSP);

        // Hiển thị hộp thoại xác nhận
        int confirmResult = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa sản phẩm khỏi giỏ hàng không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

        if (confirmResult == JOptionPane.YES_OPTION) {
            int rowsAffected = hoaDonRespon.deleteHoaDonChiTietByQuery(maHD, tenSP);

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Xóa thành công");
                loadGioHang();
                tongTien();
            } else {
                JOptionPane.showMessageDialog(null, "Xóa không thành công hoặc không có dữ liệu được xóa");
            }
        }

    }//GEN-LAST:event_btnXoaGioHangActionPerformed

    private void txt_tienkhachduaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tienkhachduaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tienkhachduaActionPerformed

    private void txt_tienkhachduaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_tienkhachduaCaretUpdate
        try {
            String tienKH = "";
            float tienThanhToan = 0;
            //            String tienKH;
            //            float tienThanhToan;
            if (!txt_tienkhachdua.getText().trim().isEmpty()) {
                tienKH = txt_tienkhachdua.getText();
                tienThanhToan = Float.valueOf(tienKH);
            }
            float tong = Float.valueOf(txt_tongtien.getText());
            //            System.out.println(tienThanhToan);
            if (tienThanhToan >= tong) {
                txt_tienthua.setText(String.valueOf(tienThanhToan - tong));
            } else {
                txt_tienthua.setText("0");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Không tính được");
        }
    }//GEN-LAST:event_txt_tienkhachduaCaretUpdate

    private void cbb_phuongthucthanhtoanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_phuongthucthanhtoanItemStateChanged
        String ten = cbb_phuongthucthanhtoan.getSelectedItem().toString();
        if (ten.equals("Chuyển khoản")) {
            String tienKH = txt_tongtien.getText();
            txt_tienkhachdua.setText(tienKH);
        } else {
            txt_tienkhachdua.enable();
        }
    }//GEN-LAST:event_cbb_phuongthucthanhtoanItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXoaGioHang;
    private javax.swing.JButton btn_huyhoadon;
    private javax.swing.JButton btn_taohoadon;
    private javax.swing.JButton btn_thanhtoan;
    private javax.swing.JComboBox<String> cbb_phuongthucthanhtoan;
    private javax.swing.JComboBox<String> cbb_trangthaihoadon;
    private javax.swing.JComboBox<String> cboFilterTrangThai;
    private javax.swing.JComboBox<String> cboSearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tb_giohnag;
    private javax.swing.JTable tb_hoaDon;
    private javax.swing.JTable tblChiTietSanPham;
    public javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txt_ma_kh;
    private javax.swing.JTextField txt_mahoadon;
    private javax.swing.JTextField txt_tenkhachhang;
    private javax.swing.JTextField txt_tienkhachdua;
    private javax.swing.JTextField txt_tienthua;
    private javax.swing.JTextField txt_tongtien;
    // End of variables declaration//GEN-END:variables

    private void loadData(ArrayList<ChiTietSanPham> listSearch) {
        int i = 1;
        defaultTableModel = (DefaultTableModel) tblChiTietSanPham.getModel();
        defaultTableModel.setRowCount(0);
        for (ChiTietSanPham x : listSearch) {

            defaultTableModel.addRow(new Object[]{
                i++,
                x.getId(),
                x.getSanPham().getTen(),
                x.getHang().getTen(),
                x.getMauSac().getTen(),
                x.getChatLieu().getTen(),
                x.getSize().getTen(),
                x.getDangAo().getTen(),
                x.getCoAo().getTen(),
                x.getCoTay().getTen(),
                x.getGia(),
                x.getSoLuong(),
                x.trangThai()
            });

        }
    }

    private void updateSTTColumn() {
        int rowCount = tblChiTietSanPham.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            tblChiTietSanPham.setValueAt(i + 1, i, 0); // Giả sử cột STT là cột đầu tiên (index 0)
        }
    }

}
