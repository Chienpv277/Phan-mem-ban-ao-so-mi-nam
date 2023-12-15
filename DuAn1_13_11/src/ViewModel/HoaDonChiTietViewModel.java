/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import DomainModel.*;
import DomainModel.ChiTietSanPham;
import DomainModel.Hang;
import DomainModel.MauSac;
import DomainModel.SanPham;
import DomainModel.Size;
import java.util.Date;

/**
 *
 * @author 84386
 */
public class HoaDonChiTietViewModel {

//    private int id_hoa_don_chi_tiet;
//
//    private int id_hoa_don;
//
//    private int id_chi_tiet_san_pham;
    private HoaDon id_hoa_don;

    private ChiTietSanPham id_chi_tiet_san_pham;

    private SanPham id_san_pham;

    private MauSac id_mau_sac;
    
    private Hang id_hang;

    private Size id_size;

    private int so_luong;

    private Float gia;
    
    private Float thanh_tien;

    public HoaDonChiTietViewModel() {
    }

    public HoaDonChiTietViewModel( HoaDon id_hoa_don, ChiTietSanPham id_chi_tiet_san_pham, SanPham id_san_pham, MauSac id_mau_sac, Hang id_hang, Size id_size, int so_luong, Float gia) {
//        this.id_hoa_don_chi_tiet = id_hoa_don_chi_tiet;
        this.id_hoa_don = id_hoa_don;
        this.id_chi_tiet_san_pham = id_chi_tiet_san_pham;
        this.id_san_pham = id_san_pham;
        this.id_mau_sac = id_mau_sac;
        this.id_hang = id_hang;
        this.id_size = id_size;
        this.so_luong = so_luong;
        this.gia = gia;
    }

    public HoaDonChiTietViewModel(HoaDon id_hoa_don, ChiTietSanPham id_chi_tiet_san_pham, SanPham id_san_pham, MauSac id_mau_sac, Hang id_hang, Size id_size, int so_luong, Float gia, Float thanh_tien) {
        this.id_hoa_don = id_hoa_don;
        this.id_chi_tiet_san_pham = id_chi_tiet_san_pham;
        this.id_san_pham = id_san_pham;
        this.id_mau_sac = id_mau_sac;
        this.id_hang = id_hang;
        this.id_size = id_size;
        this.so_luong = so_luong;
        this.gia = gia;
        this.thanh_tien = thanh_tien;
    }

    public Float getThanh_tien() {
        return thanh_tien;
    }

    public void setThanh_tien(Float thanh_tien) {
        this.thanh_tien = thanh_tien;
    }
    

    public HoaDonChiTietViewModel(HoaDon id_hoa_don, ChiTietSanPham id_chi_tiet_san_pham, int so_luong, Float gia) {
        this.id_hoa_don = id_hoa_don;
        this.id_chi_tiet_san_pham = id_chi_tiet_san_pham;
        this.so_luong = so_luong;
        this.gia = gia;
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

    public SanPham getId_san_pham() {
        return id_san_pham;
    }

    public void setId_san_pham(SanPham id_san_pham) {
        this.id_san_pham = id_san_pham;
    }

    public MauSac getId_mau_sac() {
        return id_mau_sac;
    }

    public void setId_mau_sac(MauSac id_mau_sac) {
        this.id_mau_sac = id_mau_sac;
    }

    public Hang getId_hang() {
        return id_hang;
    }

    public void setId_hang(Hang id_hang) {
        this.id_hang = id_hang;
    }

    public Size getId_size() {
        return id_size;
    }

    public void setId_size(Size id_size) {
        this.id_size = id_size;
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

//    @Override
//    public String toString() {
//        return "HoaDonChiTietViewModel{" + "id_hoa_don=" + id_hoa_don + ", id_chi_tiet_san_pham=" + id_chi_tiet_san_pham + ", id_san_pham=" + id_san_pham + ", id_mau_sac=" + id_mau_sac + ", id_hang=" + id_hang + ", id_size=" + id_size + ", so_luong=" + so_luong + ", gia=" + gia + '}';
//    }

//    @Override
//    public String toString() {
//        return "HoaDonChiTietViewModel{" + "id_san_pham=" + id_san_pham + ", thanh_tien=" + thanh_tien + '}';
//    }

    
   
   
}
