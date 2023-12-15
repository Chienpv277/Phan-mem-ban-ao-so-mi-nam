/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import DomainModel.HoaDon;
import DomainModel.KhachHang;
import DomainModel.NhanVien;
import DomainModel.Vourcher;
import Repository.HoaDonRepository;
import ServiceImpl.HoaDonServiceImpl;
import Utilities.DBConnection;
import ViewModel.HoaDonChiTietViewModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mode.TableMode;

/**
 *
 * @author 84386
 */
public class Form_1_1 extends javax.swing.JPanel {

    HoaDonServiceImpl donService = new HoaDonServiceImpl();
    private DefaultTableModel model = new DefaultTableModel();
    private List<HoaDon> listHoaDon = new ArrayList<>();
    private List<KhachHang> list = new ArrayList<>();
    private HoaDonRepository hoaDonRespon = new HoaDonRepository();
    DefaultTableModel dtm;
    TableMode tbm;
    int index = 0;
    Connection cn;
    long count, soTrang, trang = 1;
    Statement st;
    ResultSet rs;
    int i = 1;

    /**
     * Creates new form Form_1_1
     */
    public Form_1_1() {
        initComponents();
        loadtable();
        loadGioHang();
        cbb_trangthaihoadon.setSelectedItem("Đã thanh toán");
        loadtableTrangThaiDaThanhToan();
    }

    public void titleTable() {
        tbm = new TableMode();
        tb_hoaDon.setModel(tbm);
        tb_hoaDon.setShowHorizontalLines(true);
        tb_hoaDon.setShowVerticalLines(true);
    }

    public void countDb() {
        try {
            String query = "Select count(*) from hoa_don";
            cn = DBConnection.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                count = rs.getLong(1);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (Exception ex) {
            Logger.getLogger(Form_1_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadData(long trang) {
        int i = 1;
        titleTable();
        this.tbm.getDataVector().removeAllElements();
        try {
            String sql = " select top 5 hoa_don.id_hoa_don,\n"
                    + "       hoa_don.created_at,\n"
                    + "       hoa_don.ngay_thanh_toan,\n"
                    + "       khach_hang.ho_ten,  \n"
                    + "	   khach_hang.id_khach_hang,   \n"
                    + "       hoa_don.tong_tien,\n"
                    + "       hoa_don.hinh_thuc_thanh_toan,\n"
                    + "       hoa_don.trang_thai,\n"
                    + "       nhan_vien.ho_ten,\n"
                    + "	   nhan_vien.id_nhan_vien\n"
                    + "      from hoa_don \n"
                    + "	  join khach_hang on hoa_don.id_khach_hang = khach_hang.id_khach_hang \n"
                    + "      join nhan_vien on hoa_don.id_nhan_vien = nhan_vien.id_nhan_vien "
                    + "where id_hoa_don not in (select top " + (trang * 5 - 5) + " id_hoa_don from hoa_don )";
            cn = DBConnection.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Vector v = new Vector();
                int id = rs.getInt(1);
                Date cretedat = rs.getDate(2);
                Date que = rs.getDate(3);
                String hoten = rs.getString(4);
                int id_khachHang = rs.getInt(5);
                Float tongtien = rs.getFloat(6);
                String hinhthucthanhtoan = rs.getString(7);
                int trangthai = rs.getInt(8);
                String tennv = rs.getString(9);
                int id_nhan_vien = rs.getInt(10);
                int khuyenmai = rs.getInt(11);
                int id_khuyenmai = rs.getInt(12);

                v.add(i++);
//                v.add(id);
                v.add(hoten);
                v.add(tennv);
                v.add(tongtien);
                v.add(hinhthucthanhtoan);
//                v.add(khuyenmai);
                v.add(cretedat);
                v.add(que);
                v.add(trangthai == 1 ? "Đã thanh toán" : "Chưa thanh toán");

                this.tbm.addRow(v);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (Exception ex) {
            Logger.getLogger(Form_1_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadtableHoaDonChiTiet() {
        int i = 1;
        DefaultTableModel dtm = (DefaultTableModel) this.tb_hoadonchitiet.getModel();
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
                //                hv.getId_hoa_don(),
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

    //load table
//    public void loadtable() {
//        int i = 1;
//        DefaultTableModel dtm = (DefaultTableModel) this.tb_hoaDon.getModel();
//        ArrayList<HoaDon> ds = this.hoaDonRespon.findbyHD();
//        dtm.setRowCount(0);
//        for (HoaDon hv : ds) {
//            Object[] rowdata = new Object[]{
//                i++,
//                //                hv.getId_hoa_don(),
//
//                hv.getId_khach_hang().getHo_ten(),
//                hv.getId_nhan_vien().getHo_ten(),
//                hv.getTong_tien(),
//                hv.getHinh_thuc_thanh_toan(),
//                hv.getCreated_at(),
//                hv.getNgay_thanh_toan(),
//                hv.getTrang_thai() == 1 ? "Đã thanh toán" : "Chưa thanh toán"
////                hv.getId_voucher().getKhuyen_mai(),
//            };
//            dtm.addRow(rowdata);
//        }
//    }
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

    public void loadtable2() {
        int i = 1;
        DefaultTableModel dtm = (DefaultTableModel) this.tb_hoaDon.getModel();
        ArrayList<HoaDon> ds = this.donService.getListHoaDon();
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
            };
            dtm.addRow(rowdata);
        }
    }

    public void loadtableChuyenKhoan() {
        int i = 1;
        DefaultTableModel dtm = (DefaultTableModel) this.tb_hoaDon.getModel();
        ArrayList<HoaDon> ds = this.donService.getListHoaDonChuyenKhoan();
        dtm.setRowCount(0);
        for (HoaDon hv : ds) {
            Object[] rowdata = new Object[]{
                i++,
                //                hv.getId_hoa_don(),
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

    public void loadtableTienMat() {
        int i = 1;
        DefaultTableModel dtm = (DefaultTableModel) this.tb_hoaDon.getModel();
        ArrayList<HoaDon> ds = this.donService.getListHoaDonTienMat();
        dtm.setRowCount(0);
        for (HoaDon hv : ds) {
            Object[] rowdata = new Object[]{
                i++,
                //                hv.getId_hoa_don(),
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

    public void loadtableTrangThaiChuaThanhToan() {
        int i = 1;
        DefaultTableModel dtm = (DefaultTableModel) this.tb_hoaDon.getModel();
        ArrayList<HoaDon> ds = this.donService.findbytrangthaiChuaThanhToan();
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

    public void loadGioHang() {
        DefaultTableModel dtm = (DefaultTableModel) this.tb_hoadonchitiet.getModel();
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        cbb_thanhtoan = new javax.swing.JComboBox<>();
        cbb_trangthaihoadon = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_hoaDon = new javax.swing.JTable();
        txt_timkiem = new javax.swing.JTextField();
        btn_PDF = new javax.swing.JButton();
        cbb_tim = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_hoadonchitiet = new javax.swing.JTable();

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 18), new java.awt.Color(255, 0, 51))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(51, 255, 204));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Hình thức thanh toán");

        cbb_thanhtoan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbb_thanhtoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Chuyển khoản", "Tiền mặt" }));
        cbb_thanhtoan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_thanhtoanItemStateChanged(evt);
            }
        });

        cbb_trangthaihoadon.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbb_trangthaihoadon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đã thanh toán", "Chưa thanh toán" }));
        cbb_trangthaihoadon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_trangthaihoadonItemStateChanged(evt);
            }
        });
        cbb_trangthaihoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_trangthaihoadonActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Trạng thái hóa đơn");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Tìm kiếm hóa đơn");

        tb_hoaDon.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tb_hoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hóa đơn", "Khách hàng", "Nhân viên", "Tổng tiền", "Hình thức ", "Ngày tạo", "Ngày thanh toán", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, true, false
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

        txt_timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timkiemActionPerformed(evt);
            }
        });
        txt_timkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timkiemKeyReleased(evt);
            }
        });

        btn_PDF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_PDF.setText("PDF");
        btn_PDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PDFActionPerformed(evt);
            }
        });

        cbb_tim.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbb_tim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Khách hàng", "Id", "Nhân viên", " " }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(7, 7, 7))
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cbb_trangthaihoadon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(cbb_tim, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(cbb_thanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_PDF, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 881, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_PDF, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txt_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbb_tim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbb_trangthaihoadon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14)
                                .addComponent(cbb_thanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 18), new java.awt.Color(255, 0, 0))); // NOI18N
        jPanel4.setForeground(new java.awt.Color(51, 255, 204));

        tb_hoadonchitiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã", "Sản phẩm", "Số lượng", "Giá", "Thành tiền"
            }
        ));
        jScrollPane3.setViewportView(tb_hoadonchitiet);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 913, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 968, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbb_thanhtoanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_thanhtoanItemStateChanged
        // TODO add your handling code here:
        String selecteditem = (String) cbb_thanhtoan.getSelectedItem();
        if (selecteditem.equals("Chuyển khoản")) {
            loadtableChuyenKhoan();
        } else if (selecteditem.equals("Tiền mặt")) {
            loadtableTienMat();
        } else if (selecteditem.equals("Tất cả")) {
            loadtable();
        }
    }//GEN-LAST:event_cbb_thanhtoanItemStateChanged

    private void cbb_trangthaihoadonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_trangthaihoadonItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_trangthaihoadonItemStateChanged

    private void cbb_trangthaihoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_trangthaihoadonActionPerformed
        // TODO add your handling code here:
        String selecteditem = (String) cbb_trangthaihoadon.getSelectedItem();
        if (selecteditem.equals("Đã thanh toán")) {
            loadtableTrangThaiDaThanhToan();
        } else if (selecteditem.equals("Chưa thanh toán")) {
            loadtableTrangThaiChuaThanhToan();
        } else if (selecteditem.equals("Tất cả")) {
            loadtable();
        }
    }//GEN-LAST:event_cbb_trangthaihoadonActionPerformed

    private void tb_hoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_hoaDonMouseClicked
//        DefaultTableModel dtm = (DefaultTableModel) this.tb_hoadonchitiet.getModel();
//        dtm.setRowCount(0);
//        //        txt_test.setText("");
//
//        int row = this.tb_hoaDon.getSelectedRow();
//        if (row == -1) {
//            return;
//        }
//        int id = Integer.parseInt(tb_hoaDon.getValueAt(row, 0).toString());
//        ArrayList<HoaDonChiTietViewModel> hv1 = this.donService.getListHoaDonChiietById(id);
//        for (HoaDonChiTietViewModel hv : hv1) {
//            Object[] rowdata = new Object[]{
//                i++,
//                //            hv.getId_chi_tiet_san_pham(),
//                hv.getId_san_pham().getTen(),
//                hv.getId_mau_sac().getTen(),
//                hv.getId_hang().getTen(),
//                hv.getId_size().getTen(),
//                hv.getSo_luong(),
//                hv.getGia(),
//                hv.getId_hoa_don().getTrang_thai() == 1 ? "Đã thanh toán" : "Chưa thanh toán"
//            };
//            dtm.addRow(rowdata);
//        }
//        loadGioHang();
        DefaultTableModel dtm = (DefaultTableModel) this.tb_hoadonchitiet.getModel();
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
                //                i++,
                hv.getId_hoa_don().getId_hoa_don(),
                hv.getId_san_pham().getTen(),
                hv.getSo_luong(),
                hv.getGia(),
                hv.getThanh_tien()
            };
            dtm.addRow(rowdata);
        }

    }//GEN-LAST:event_tb_hoaDonMouseClicked

    private void txt_timkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timkiemKeyReleased
        // TODO add your handling code here:
//        listNhanVien.clear();
        DefaultTableModel defaultTableModel = (DefaultTableModel) tb_hoaDon.getModel();
        defaultTableModel.setRowCount(0);
        if (cbb_tim.getSelectedItem().equals("Khách hàng")) {
            String kh = txt_timkiem.getText();
            HoaDon hv = this.donService.getByHoaDonTenKhachHang(kh);
            defaultTableModel.addRow(new Object[]{
                hv.getId_khach_hang().getHo_ten(),
                hv.getId_nhan_vien().getHo_ten(),
                hv.getTong_tien(),
                hv.getHinh_thuc_thanh_toan(),
                hv.getCreated_at(),
                hv.getNgay_thanh_toan(),
                hv.getTrang_thai() == 1 ? "Đã thanh toán" : "Chưa thanh toán"
            });

        }
        if (cbb_tim.getSelectedItem().equals("Id")) {
            int id = Integer.parseInt(txt_timkiem.getText());
            HoaDon hv = this.donService.getByHoaDonId(id);
            defaultTableModel.addRow(new Object[]{
                hv.getId_khach_hang().getHo_ten(),
                hv.getId_nhan_vien().getHo_ten(),
                hv.getTong_tien(),
                hv.getHinh_thuc_thanh_toan(),
                hv.getCreated_at(),
                hv.getNgay_thanh_toan(),
                hv.getTrang_thai() == 1 ? "Đã thanh toán" : "Chưa thanh toán"});
        }
        if (cbb_tim.getSelectedItem().equals("Nhân viên")) {
            String nv = txt_timkiem.getText();
            HoaDon hv = this.donService.getByHoaDonNhanVien(Integer.parseInt(nv));
            defaultTableModel.addRow(new Object[]{
                hv.getId_khach_hang().getHo_ten(),
                hv.getId_nhan_vien().getHo_ten(),
                hv.getTong_tien(),
                hv.getHinh_thuc_thanh_toan(),
                hv.getCreated_at(),
                hv.getNgay_thanh_toan(),
                hv.getTrang_thai() == 1 ? "Đã thanh toán" : "Chưa thanh toán"});
        }

//        listHoaDon.clear();
//        List<HoaDon> hoaDons = new ArrayList<>();
//        float gia = 0;
//        if (txt_timkiem.getText().isEmpty()) {
//            gia = 0;
//        } else if (txt_timkiem.getText().chars().allMatch(Character::isDigit)) {
//            gia = Float.parseFloat(txt_timkiem.getText());
//        }
//        String sql = "select hoa_don.id_hoa_don,"
//                + "hoa_don.created_at,"
//                + "hoa_don.ngay_thanh_toan,"
//                + "khach_hang.ho_ten,"
//                + "hoa_don.id_khach_hang, "
//                + "hoa_don.tong_tien,"
//                + "hoa_don.hinh_thuc_thanh_toan,"
//                + "hoa_don.trang_thai,"
//                + "                nhan_vien.ho_ten, \n"
//                + "		   hoa_don.id_nhan_vien, \n"
//                + "                vourcher.[%_khuyen_mai] ,\n"
//                + "                hoa_don.id_voucher \n"
//                + " from hoa_don "
//                + "join khach_hang on hoa_don.id_khach_hang = khach_hang.id_khach_hang \n"
//                + "                join nhan_vien on hoa_don.id_nhan_vien = nhan_vien.id_nhan_vien \n"
//                + "                join vourcher on hoa_don.id_voucher = vourcher.id_voucher\n"
//                + "				where hoa_don.created_at like N'%" + txt_timkiem.getText() + "%' \n"
//                + "				or hoa_don.ngay_thanh_toan like N'%" + txt_timkiem.getText() + "%'\n"
//                + "				or khach_hang.ho_ten like N'%" + txt_timkiem.getText() + "%' \n"
//                + "				or hoa_don.tong_tien = \n" + gia
//                + "				or hoa_don.hinh_thuc_thanh_toan like N'%" + txt_timkiem.getText() + "%'\n"
//                + "				or hoa_don.trang_thai = ?\n"
//                + "				or hoa_don.id_hoa_don = ? \n"
//                + "				or nhan_vien.ho_ten  like N'%" + txt_timkiem.getText() + "%'";
//
//        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
//
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                HoaDon list = new HoaDon();
//                KhachHang khachHang = new KhachHang();
//                NhanVien nhanVien = new NhanVien();
//                Vourcher vourcher = new Vourcher();
//
//                list.setId_hoa_don(rs.getInt(1));
//                list.setCreated_at(rs.getDate(2));
//                list.setNgay_thanh_toan(rs.getDate(3));
//
//                khachHang.setHo_ten(rs.getString(4));
//                khachHang.setId_khach_hang(rs.getInt(5));
//                list.setId_khach_hang(khachHang);
//
//                list.setId_khach_hang(khachHang);
//                list.setTong_tien(rs.getFloat(6));
//                list.setHinh_thuc_thanh_toan(rs.getString(7));
//                list.setTrang_thai(rs.getInt(8));
//
//                nhanVien.setHo_ten(rs.getString(9));
//                nhanVien.setId_nhan_vien(rs.getInt(10));
//                list.setId_nhan_vien(nhanVien);
//
//                vourcher.setKhuyen_mai(rs.getInt(11));
//                vourcher.setId_voucher(rs.getInt(12));
//                list.setId_voucher(vourcher);
//
//                hoaDons.add(list);
//
////                vourcher.setKhuyen_mai(rs.getInt(10));
////                list.setId_voucher(vourcher);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        for (HoaDon hv : hoaDons) {
//            Object[] rowdata = new Object[]{
//                i++,
//                hv.getId_hoa_don(),
//                hv.getCreated_at(),
//                hv.getNgay_thanh_toan(),
//                hv.getId_khach_hang().getHo_ten(),
//                hv.getTong_tien(),
//                hv.getHinh_thuc_thanh_toan(),
//                hv.getTrang_thai() == 1 ? "Đã thanh toán" : "Chưa thanh toán",
//                hv.getId_nhan_vien().getHo_ten(),
//                hv.getId_voucher().getKhuyen_mai(),};
//            dtm.addRow(rowdata);
//        }
//

    }//GEN-LAST:event_txt_timkiemKeyReleased

    private void btn_PDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PDFActionPerformed
        // TODO add your handling code here:
        MessageFormat header = new MessageFormat("Form title");
        MessageFormat footer = new MessageFormat("(0, number, interger)");
        try {
            PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
            set.add(OrientationRequested.LANDSCAPE);
            tb_hoaDon.print(JTable.PrintMode.FIT_WIDTH, header, footer, true, set, true);
            JOptionPane.showMessageDialog(null, "Printf succefully");
        } catch (java.awt.print.PrinterException e) {
            JOptionPane.showMessageDialog(null, "failed");
        }
    }//GEN-LAST:event_btn_PDFActionPerformed

    private void txt_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timkiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_PDF;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbb_thanhtoan;
    private javax.swing.JComboBox<String> cbb_tim;
    private javax.swing.JComboBox<String> cbb_trangthaihoadon;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tb_hoaDon;
    private javax.swing.JTable tb_hoadonchitiet;
    private javax.swing.JTextField txt_timkiem;
    // End of variables declaration//GEN-END:variables
}
