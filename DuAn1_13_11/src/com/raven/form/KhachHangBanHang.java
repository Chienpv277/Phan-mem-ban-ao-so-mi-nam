/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.raven.form;

import DomainModel.HoaDon;
import DomainModel.HoaDonChiTiet;
import DomainModel.HoaDonViewModel;
import DomainModel.KhachHang;
import DomainModel.Model_form;
import DomainModel.ChiTietSanPham;
import DomainModel.KhachHang;
import Repository.HoaDonRepository;
import Service.KhachHangService;
import Service.KhachHangService_cuong;
import ServiceImpl.KhachHangServiceImpl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JOptionPane;

/**
 *
 * @author 84386
 */
public class KhachHangBanHang extends javax.swing.JFrame {

    DefaultTableModel dtm;
    ArrayList<KhachHang> lst = new ArrayList<>();
    KhachHangService khachHangService = new KhachHangServiceImpl();
    ArrayList<KhachHang> list = khachHangService.getListKhachHang();
    private HoaDonRepository hoaDonRespon = new HoaDonRepository();
    public HoaDonViewModel hoadon = null;
    public KhachHang khach;
    Form_7 banHangfr;
    JTable tblGioHang;
    JTable tblHoaDon;
    private KhachHangService service = new KhachHangServiceImpl();

    /**
     * Creates new form KhachHangBanHang
     */
    public KhachHangBanHang(Form_7 banHangfr, JTable tblGioHang, JTable tblHoaDon) {
        initComponents();
        setLocationRelativeTo(this);
        this.tblGioHang = tblGioHang;
        this.tblHoaDon = tblHoaDon;
        this.banHangfr = banHangfr;

        dtm = (DefaultTableModel) tblkhachhang.getModel();
        loadDataToTable(service.getListKhachHang());
    }

    public void loadtable() {
        int i = 1;
        DefaultTableModel dtm = (DefaultTableModel) this.tblHoaDon.getModel();
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
                hv.getTrang_thai() == 1 ? "da thanh toan" : "chua thanh toan"
//                hv.getId_voucher().getKhuyen_mai(),
            };
            dtm.addRow(rowdata);
        }
    }

    public KhachHangBanHang() {
        initComponents();

        dtm = (DefaultTableModel) tblkhachhang.getModel();
        loadDataToTable(service.getListKhachHang());
    }

    private void loadDataToTable(ArrayList<KhachHang> khachHang) {
        int i = 1;
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblkhachhang.getModel();
        defaultTableModel.setRowCount(0);
        for (KhachHang kh : khachHang) {
            defaultTableModel.addRow(new Object[]{
                i++, kh.getMa(), kh.getHo_ten(), kh.getSdt(), kh.getEmail(), kh.getDia_chi()
            });
        }
    }

    public void showdetail() {
        TableModel table = tblkhachhang.getModel();
        int index = tblkhachhang.getSelectedRow();

        txtma.setText(tblkhachhang.getValueAt(index, 1).toString());
        txthoten.setText(tblkhachhang.getValueAt(index, 2).toString());
        boolean gt = tblkhachhang.getValueAt(index, 3).toString().equalsIgnoreCase("nam") ? true : false;
        rdonam.setSelected(gt);
        rdonu.setSelected(!gt);
        try {
            int srow = tblkhachhang.getSelectedRow();
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse((String) table.getValueAt(srow, 4));
            txtngaysinh.setDate(date);
        } catch (Exception e) {
        }
        txtsdt.setText(tblkhachhang.getValueAt(index, 5).toString());
        txtdiachi.setText(tblkhachhang.getValueAt(index, 6).toString());
        txtemail.setText(tblkhachhang.getValueAt(index, 7).toString());
    }

    private KhachHang readform() {
        KhachHang k = new KhachHang();
        String ngay = Model_form.layNgayString(txtngaysinh.getDate());
        k.setMa(txtma.getText());
        k.setHo_ten(txthoten.getText());
        k.setGioi_tinh(rdonam.isSelected() ? "Nam" : "Nữ");
        k.setNgay_sinh(ngay);
        k.setSdt(txtsdt.getText());
        k.setDia_chi(txtdiachi.getText());
        k.setEmail(txtemail.getText());

        return k;
    }

    public void clear() {
        txtma.setText("");
        txthoten.setText("");
        rdonam.setSelected(true);
        txtngaysinh.setDateFormatString(null);
        txtsdt.setText("");
        txtdiachi.setText("");
        txtemail.setText("");
    }

    public void fillter(String ma) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dtm);
        tblkhachhang.setRowSorter(tr);
        if (ma != "tất cả") {
            tr.setRowFilter(RowFilter.regexFilter(ma));
        } else {
            tblkhachhang.setRowSorter(tr);
        }
    }

    public void loatdate(ArrayList<KhachHang> list) {
        dtm = (DefaultTableModel) tblkhachhang.getModel();
        dtm.setRowCount(0);
        // int i = 0;
        for (KhachHang kh : list) {
            dtm.addRow(new Object[]{
                tblkhachhang.getRowCount(),
                kh.getMa(), kh.getHo_ten(), kh.getSdt(), kh.getEmail(), kh.getDia_chi()
            });
        }
    }

    private boolean validateFormData() {
        // Thực hiện kiểm tra hợp lệ cho các trường dữ liệu (ví dụ: txtTen, txtsdt, ...)
        // Nếu có lỗi, hiển thị thông báo và trả về false

        // Kiểm tra hợp lệ cho tên
        String ten = txthoten.getText();
        String sdt = txtsdt.getText();
        String email = txtemail.getText();
        String diaChi = txtdiachi.getText();
        if (ten.isEmpty()
                || sdt.isEmpty()
                || email.isEmpty()
                || diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra hợp lệ cho số điện thoại
        if (!sdt.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ. Vui lòng nhập 10 chữ số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra hợp lệ cho định dạng email
        if (!email.matches("\\b[A-Za-z0-9._%+-]+@gmail\\.com\\b")) {
            JOptionPane.showMessageDialog(this, "Định dạng email không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Thêm các điều kiện kiểm tra hợp lệ cho các trường khác (địa chỉ, ...)
        // ...
        return true; // Nếu không có lỗi, trả về true
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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblkhachhang = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnthem = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtma = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txthoten = new javax.swing.JTextField();
        txtdiachi = new javax.swing.JTextField();
        rdonam = new javax.swing.JRadioButton();
        rdonu = new javax.swing.JRadioButton();
        txtngaysinh = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtsdt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jTabbedPane4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        tblkhachhang.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblkhachhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Email", "Địa chỉ"
            }
        ));
        tblkhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkhachhangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblkhachhang);

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jTabbedPane4.addTab("Danh sách khách hàng", jPanel6);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 14), new java.awt.Color(255, 51, 51))); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btnthem.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnthem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btnthem.setText("THÊM");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel15.setText("HỌ TÊN");

        txtma.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtma.setEnabled(false);

        txtemail.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel16.setText("SDT");

        txthoten.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        txtdiachi.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        buttonGroup1.add(rdonam);
        rdonam.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        rdonam.setSelected(true);
        rdonam.setText("Nam");
        rdonam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdonamActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdonu);
        rdonu.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        rdonu.setText("Nữ");
        rdonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdonuActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel17.setText("NGÀY SINH");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel18.setText("EMAIL");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel19.setText("ĐỊA CHỈ");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel20.setText("GIỚI TÍNH");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel21.setText("MÃ KH");

        txtsdt.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(37, 37, 37)
                        .addComponent(rdonam)
                        .addGap(34, 34, 34)
                        .addComponent(rdonu)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(txthoten)
                                        .addGap(110, 110, 110))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel16))
                                .addGap(50, 50, 50)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtsdt, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                                    .addComponent(txtdiachi, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                                    .addComponent(txtemail)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(169, 169, 169)
                                .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(119, 119, 119))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txthoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))))
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(rdonam)
                    .addComponent(rdonu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(btnthem)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Thiết lập thông tin khách hàng", jPanel3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 913, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 933, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 489, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdonuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdonuActionPerformed

    private void rdonamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdonamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdonamActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        if (!validateFormData()) {
            return; // Nếu dữ liệu không hợp lệ, không thực hiện thêm
        }

        // Hiển thị xác nhận trước khi thêm
        int confirmResult = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm khách hàng không?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (confirmResult == JOptionPane.YES_OPTION) {
            ArrayList<String> existingMaList = new ArrayList<>();
            for (KhachHang existingKhachHang : service.getListKhachHang()) {
                existingMaList.add(existingKhachHang.getMa());
            }
            // Sinh mã mới
            String newMa;
            int suffix = 1;
            do {
                newMa = "KH" + String.format("%03d", suffix); // Ví dụ: KH001
                suffix++;
            } while (existingMaList.contains(newMa));

            KhachHang k = new KhachHang();
            k.setMa(newMa);
            k.setHo_ten(txthoten.getText());
            k.setSdt(txtsdt.getText());
            k.setGioi_tinh(rdonam.isSelected() ? "Nam" : "Nữ");
            Date ngaySinhDate = txtngaysinh.getDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  // Choose your desired date format
            String ngaySinhString = dateFormat.format(ngaySinhDate);
            k.setNgay_sinh(ngaySinhString);

            k.setEmail(txtemail.getText());
            k.setDia_chi(txtdiachi.getText());

            // Hiển thị xác nhận trước khi thêm
            if (service.add(k)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                loadDataToTable(service.getListKhachHang());
                clear();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Hủy thêm mới");
        }
    }//GEN-LAST:event_btnthemActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        //String selected = (String) txtTimKiem.getText();
        ArrayList<KhachHang> khachHangs = new ArrayList<>();

        khachHangs.clear();
        String strFind = "";
        strFind = txtTimKiem.getText();
        ArrayList<KhachHang> listSearch = service.searchBySdt(strFind);
        loadDataToTable(listSearch);

    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void tblkhachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkhachhangMouseClicked
        int row = tblkhachhang.getSelectedRow();
        if (row < 0) {
            return;
        }
        KhachHang khachHang1 = new KhachHang();
        String makh = tblkhachhang.getValueAt(row, 1).toString();
        String idKH = "";

        ArrayList<KhachHang> listKH = khachHangService.getListKhachHang();
        for (KhachHang x : listKH) {
            if (String.valueOf(x.getMa()) != null && String.valueOf(x.getMa()).equals(makh)) {
                khachHang1 = x;
            }
        }

        int indexHD = tblHoaDon.getSelectedRow();
        String maHD = tblHoaDon.getValueAt(indexHD, 1).toString();

        HoaDonViewModel hd = new HoaDonViewModel();
        hd.setId_khach_hang(khachHang1);
        hd.setId_hoa_don(Integer.parseInt(maHD));
        if (JOptionPane.showConfirmDialog(this, "Ban co muon them khach hang khong?", "Thong Bao", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }

        System.out.println(hd.getId_hoa_don());
        System.out.println(hd.getId_khach_hang());
//        banHangfr.hoadon.setId_khach_hang(khachHang1);
        hoaDonRespon.updatehoadon_thanhtoan2(hd);
        System.out.println("run  : " + hoaDonRespon.updatehoadon_thanhtoan2(hd));
        if (hoaDonRespon.updatehoadon_thanhtoan2(hd) != null) {
            JOptionPane.showMessageDialog(this, "Cập nhập thông tin hóa đơn thành công");
//            banHangfr.loadChonKH(khach.getTen());

            banHangfr.loadChonKH(hd.getId_khach_hang().getHo_ten());
            banHangfr.loadtableTrangThaiChuaThanhToan();
        }
        this.dispose();

    }//GEN-LAST:event_tblkhachhangMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KhachHangBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhachHangBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhachHangBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhachHangBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KhachHangBanHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnthem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JRadioButton rdonam;
    private javax.swing.JRadioButton rdonu;
    private javax.swing.JTable tblkhachhang;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtdiachi;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txthoten;
    private javax.swing.JTextField txtma;
    private com.toedter.calendar.JDateChooser txtngaysinh;
    private javax.swing.JTextField txtsdt;
    // End of variables declaration//GEN-END:variables
}
