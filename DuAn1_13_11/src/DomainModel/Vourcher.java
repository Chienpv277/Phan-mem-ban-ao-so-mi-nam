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
public class Vourcher {
    private int id_voucher;

    private int ma;

    private int ten;

    private int ngay_bat_dau;

    private int ngay_ket_thuc;

    private int khuyen_mai;

    private Date created_at;

    private Date updated_at;

    private String created_by;

    private String updated_by;

    private String deleted;

    private String trang_thai;

    public Vourcher() {
    }

    public Vourcher(int id_voucher, int ma, int ten, int ngay_bat_dau, int ngay_ket_thuc, int khuyen_mai, Date created_at, Date updated_at, String created_by, String updated_by, String deleted, String trang_thai) {
        this.id_voucher = id_voucher;
        this.ma = ma;
        this.ten = ten;
        this.ngay_bat_dau = ngay_bat_dau;
        this.ngay_ket_thuc = ngay_ket_thuc;
        this.khuyen_mai = khuyen_mai;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.deleted = deleted;
        this.trang_thai = trang_thai;
    }

    public Vourcher(int id_voucher, int ten) {
        this.id_voucher = id_voucher;
        this.ten = ten;
    }

    public Vourcher(int id_voucher) {
        this.id_voucher = id_voucher;
    }

    public int getId_voucher() {
        return id_voucher;
    }

    public void setId_voucher(int id_voucher) {
        this.id_voucher = id_voucher;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public int getTen() {
        return ten;
    }

    public void setTen(int ten) {
        this.ten = ten;
    }

    public int getNgay_bat_dau() {
        return ngay_bat_dau;
    }

    public void setNgay_bat_dau(int ngay_bat_dau) {
        this.ngay_bat_dau = ngay_bat_dau;
    }

    public int getNgay_ket_thuc() {
        return ngay_ket_thuc;
    }

    public void setNgay_ket_thuc(int ngay_ket_thuc) {
        this.ngay_ket_thuc = ngay_ket_thuc;
    }

    public int getKhuyen_mai() {
        return khuyen_mai;
    }

    public void setKhuyen_mai(int khuyen_mai) {
        this.khuyen_mai = khuyen_mai;
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

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    @Override
    public String toString() {
        return String.valueOf(id_voucher);
    }
    
    
}
