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
public class NhanVien {

    private int id_nhan_vien;

    private int id_chuc_vu;

    private String ho_ten;

    private Date ngay_sinh;

    private String sdt;

    private String gioi_tinh;

    private String dia_chi;

    private String email;

    private String so_can_cuoc;

    private Date created_at;

    private Date updated_at;

    private String created_by;

    private String updated_by;

    private String deleted;

    private String trang_thai;

    public NhanVien() {
    }

    public NhanVien(int id_nhan_vien, int id_chuc_vu, String ho_ten, Date ngay_sinh, String sdt, String gioi_tinh, String dia_chi, String email, String so_can_cuoc, Date created_at, Date updated_at, String created_by, String updated_by, String deleted, String trang_thai) {
        this.id_nhan_vien = id_nhan_vien;
        this.id_chuc_vu = id_chuc_vu;
        this.ho_ten = ho_ten;
        this.ngay_sinh = ngay_sinh;
        this.sdt = sdt;
        this.gioi_tinh = gioi_tinh;
        this.dia_chi = dia_chi;
        this.email = email;
        this.so_can_cuoc = so_can_cuoc;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.deleted = deleted;
        this.trang_thai = trang_thai;
    }

    public NhanVien(int id_nhan_vien, String ho_ten) {
        this.id_nhan_vien = id_nhan_vien;
        this.ho_ten = ho_ten;
    }

    
    public NhanVien(int id_nhan_vien) {
        this.id_nhan_vien = id_nhan_vien;
    }

    public int getId_nhan_vien() {
        return id_nhan_vien;
    }

    public void setId_nhan_vien(int id_nhan_vien) {
        this.id_nhan_vien = id_nhan_vien;
    }

    public int getId_chuc_vu() {
        return id_chuc_vu;
    }

    public void setId_chuc_vu(int id_chuc_vu) {
        this.id_chuc_vu = id_chuc_vu;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }

    public Date getNgay_sinh() {
        return ngay_sinh;
    }

    public void setNgay_sinh(Date ngay_sinh) {
        this.ngay_sinh = ngay_sinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getGioi_tinh() {
        return gioi_tinh;
    }

    public void setGioi_tinh(String gioi_tinh) {
        this.gioi_tinh = gioi_tinh;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSo_can_cuoc() {
        return so_can_cuoc;
    }

    public void setSo_can_cuoc(String so_can_cuoc) {
        this.so_can_cuoc = so_can_cuoc;
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
        return  String.valueOf(id_nhan_vien)  ;
    }
    
    
}
