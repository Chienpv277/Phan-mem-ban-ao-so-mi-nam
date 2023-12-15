/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import DomainModel.HoaDon;
import DomainModel.ChiTietSanPham;
import java.util.Date;

/**
 *
 * @author 84386
 */
public class HoaDonChiTietBanHangViewModel {
     private HoaDon id_hoa_don;
    
    private ChiTietSanPham id_chi_tiet_san_pham;
    
    private int so_luong;
    
    private Float gia;
    
    private Float thanh_tien;
    
    private Date created_at;

    private Date updated_at;

    private String created_by;

    private String updated_by;

    private String deleted;

    public HoaDonChiTietBanHangViewModel() {
    }

    public HoaDonChiTietBanHangViewModel(HoaDon id_hoa_don, ChiTietSanPham id_chi_tiet_san_pham, int so_luong, Float gia, Float thanh_tien, Date created_at, Date updated_at, String created_by, String updated_by, String deleted) {
        this.id_hoa_don = id_hoa_don;
        this.id_chi_tiet_san_pham = id_chi_tiet_san_pham;
        this.so_luong = so_luong;
        this.gia = gia;
        this.thanh_tien = thanh_tien;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.deleted = deleted;
    }

    public HoaDon getId_hoa_don() {
        return id_hoa_don;
    }

    public void setId_hoa_don(HoaDon id_hoa_don) {
        this.id_hoa_don = id_hoa_don;
    }

    public ChiTietSanPham getId_chi_tiet_san_pham() {
        return id_chi_tiet_san_pham;
    }

    public void setId_chi_tiet_san_pham(ChiTietSanPham id_chi_tiet_san_pham) {
        this.id_chi_tiet_san_pham = id_chi_tiet_san_pham;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    public Float getGia() {
        return gia;
    }

    public void setGia(Float gia) {
        this.gia = gia;
    }

    public Float getThanh_tien() {
        return thanh_tien;
    }

    public void setThanh_tien(Float thanh_tien) {
        this.thanh_tien = thanh_tien;
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
    
    
}
