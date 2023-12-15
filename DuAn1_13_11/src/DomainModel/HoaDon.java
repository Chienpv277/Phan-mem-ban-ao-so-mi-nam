/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;


import java.util.Date;

/**
 *
 * @author 84386
 */
public class HoaDon{

    private int id_hoa_don;

    private NhanVien id_nhan_vien;

    private KhachHang id_khach_hang;

    private Date ngay_thanh_toan;
//    private Date ngay_thanh_toan;

    private String sdt;

    private String dia_chi;

    private Float phi_ship;

    private Float tong_tien;

    private Date created_at;

    private Date updated_at;

    private String created_by;

    private String updated_by;

    private String deleted;

    private String hinh_thuc_thanh_toan;

    private int trang_thai;

    public HoaDon() {
    }

    
    public HoaDon(int id_hoa_don) {
        this.id_hoa_don = id_hoa_don;
    }

    public HoaDon(int id_hoa_don, NhanVien id_nhan_vien, KhachHang id_khach_hang, Date ngay_thanh_toan, String sdt, String dia_chi, Float phi_ship, Float tong_tien, Date created_at, Date updated_at, String created_by, String updated_by, String deleted, String hinh_thuc_thanh_toan, int trang_thai) {
        this.id_hoa_don = id_hoa_don;
        this.id_nhan_vien = id_nhan_vien;
        this.id_khach_hang = id_khach_hang;
        this.ngay_thanh_toan = ngay_thanh_toan;
        this.sdt = sdt;
        this.dia_chi = dia_chi;
        this.phi_ship = phi_ship;
        this.tong_tien = tong_tien;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.deleted = deleted;
        this.hinh_thuc_thanh_toan = hinh_thuc_thanh_toan;
        this.trang_thai = trang_thai;
    }

    public HoaDon(int id_hoa_don, NhanVien id_nhan_vien, KhachHang id_khach_hang, Float tong_tien, Date created_at, String hinh_thuc_thanh_toan, int trang_thai) {
        this.id_hoa_don = id_hoa_don;
        this.id_nhan_vien = id_nhan_vien;
        this.id_khach_hang = id_khach_hang;
        this.tong_tien = tong_tien;
        this.created_at = created_at;
        this.hinh_thuc_thanh_toan = hinh_thuc_thanh_toan;
        this.trang_thai = trang_thai;
    }

    public HoaDon(Float tong_tien, String hinh_thuc_thanh_toan) {
        this.tong_tien = tong_tien;
        this.hinh_thuc_thanh_toan = hinh_thuc_thanh_toan;
    }

    public int getId_hoa_don() {
        return id_hoa_don;
    }

    public void setId_hoa_don(int id_hoa_don) {
        this.id_hoa_don = id_hoa_don;
    }

    public NhanVien getId_nhan_vien() {
        return id_nhan_vien;
    }

    public void setId_nhan_vien(NhanVien id_nhan_vien) {
        this.id_nhan_vien = id_nhan_vien;
    }

    public KhachHang getId_khach_hang() {
        return id_khach_hang;
    }

    public void setId_khach_hang(KhachHang id_khach_hang) {
        this.id_khach_hang = id_khach_hang;
    }

    public Date getNgay_thanh_toan() {
        return ngay_thanh_toan;
    }

    public void setNgay_thanh_toan(Date ngay_thanh_toan) {
        this.ngay_thanh_toan = ngay_thanh_toan;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public Float getPhi_ship() {
        return phi_ship;
    }

    public void setPhi_ship(Float phi_ship) {
        this.phi_ship = phi_ship;
    }

    public Float getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(Float tong_tien) {
        this.tong_tien = tong_tien;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getHinh_thuc_thanh_toan() {
        return hinh_thuc_thanh_toan;
    }

    public void setHinh_thuc_thanh_toan(String hinh_thuc_thanh_toan) {
        this.hinh_thuc_thanh_toan = hinh_thuc_thanh_toan;
    }

    public int getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(int trang_thai) {
        this.trang_thai = trang_thai;
    }

  
    
//    @Override
//    public String toString() {
//        return String.valueOf(id_hoa_don);
//    }
//    

    @Override
    public String toString() {
        return "HoaDon{" + "id_hoa_don=" + id_hoa_don + ", id_nhan_vien=" + id_nhan_vien + ", id_khach_hang=" + id_khach_hang + ", ngay_thanh_toan=" + ngay_thanh_toan + ", sdt=" + sdt + ", dia_chi=" + dia_chi + ", phi_ship=" + phi_ship + ", tong_tien=" + tong_tien + ", created_at=" + created_at + ", updated_at=" + updated_at + ", created_by=" + created_by + ", updated_by=" + updated_by + ", deleted=" + deleted + ", hinh_thuc_thanh_toan=" + hinh_thuc_thanh_toan + ", trang_thai=" + trang_thai + '}';
    }
    
    
    
}
