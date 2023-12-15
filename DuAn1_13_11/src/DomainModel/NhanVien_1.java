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
public class NhanVien_1 {

    private int id_nhan_vien;

    private ChucVu chuc_vu;

    private String ho_ten;

    private Date ngay_sinh;

    private String sdt;

    private String gioi_tinh;

    private String dia_chi;

    private String email;

    private String so_can_cuoc;
    
    private String user;
    
    private String password;

    private Date created_at;

    private Date updated_at;

    private String created_by;

    private String updated_by;

    private String deleted;

    private int trang_thai;

    public NhanVien_1() {
    }

    public NhanVien_1(String ho_ten, Date ngay_sinh, String sdt, String gioi_tinh, String dia_chi, String email, Date created_at, int trang_thai) {
        this.ho_ten = ho_ten;
        this.ngay_sinh = ngay_sinh;
        this.sdt = sdt;
        this.gioi_tinh = gioi_tinh;
        this.dia_chi = dia_chi;
        this.email = email;
        this.created_at = created_at;
        this.trang_thai = trang_thai;
    }

    public NhanVien_1(String user, String password) {
        this.user = user;
        this.password = password;
    }
    
    

    public NhanVien_1(ChucVu chuc_vu, String ho_ten, Date ngay_sinh, String sdt, String so_can_cuoc, String gioi_tinh, String dia_chi, String email, String user, String password, int trang_thai) {
        this.chuc_vu = chuc_vu;
        this.ho_ten = ho_ten;
        this.ngay_sinh = ngay_sinh;
        this.sdt = sdt;
        this.so_can_cuoc = so_can_cuoc;
        this.gioi_tinh = gioi_tinh;
        this.dia_chi = dia_chi;
        this.email = email;
        this.user = user;
        this.password = password;
        this.trang_thai = trang_thai;
    }
    
    public NhanVien_1(ChucVu chuc_vu, String ho_ten, Date ngay_sinh, String sdt, String so_can_cuoc, String gioi_tinh, String dia_chi, String email, int trang_thai) {
        this.chuc_vu = chuc_vu;
        this.ho_ten = ho_ten;
        this.ngay_sinh = ngay_sinh;
        this.sdt = sdt;
        this.so_can_cuoc = so_can_cuoc;
        this.gioi_tinh = gioi_tinh;
        this.dia_chi = dia_chi;
        this.email = email;
        this.trang_thai = trang_thai;
    }

    public NhanVien_1(int id_nhan_vien, ChucVu chuc_vu, String ho_ten, Date ngay_sinh, String sdt, String gioi_tinh, String dia_chi, String email, String user, String password, String so_can_cuoc, Date created_at, Date updated_at, String created_by, String updated_by, String deleted, int trang_thai) {
        this.id_nhan_vien = id_nhan_vien;
        this.chuc_vu = chuc_vu;
        this.ho_ten = ho_ten;
        this.ngay_sinh = ngay_sinh;
        this.sdt = sdt;
        this.gioi_tinh = gioi_tinh;
        this.dia_chi = dia_chi;
        this.email = email;
        this.user = user;
        this.password = password;
        this.so_can_cuoc = so_can_cuoc;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.deleted = deleted;
        this.trang_thai = trang_thai;
    }

    public NhanVien_1(NhanVien_1 nhanVien) {
        this.id_nhan_vien = nhanVien.getId_nhan_vien();
        this.chuc_vu = nhanVien.getChuc_vu();
        this.ho_ten = nhanVien.getHo_ten();
        this.ngay_sinh = nhanVien.getNgay_sinh();
        this.sdt = nhanVien.getSdt();
        this.gioi_tinh = nhanVien.getGioi_tinh();
        this.dia_chi = nhanVien.getDia_chi();
        this.email = nhanVien.getEmail();
        this.so_can_cuoc = nhanVien.getSo_can_cuoc();
        this.created_at = nhanVien.getCreated_at();
        this.updated_at = nhanVien.getUpdated_at();
        this.created_by = nhanVien.getCreated_by();
        this.updated_by = nhanVien.getUpdated_by();
        this.deleted = nhanVien.getDeleted();
        this.trang_thai = nhanVien.getTrang_thai();
    }


//    public NhanVien(ChucVu cv, String hoTen, Date ngaySinh, String sdt, String soCanCuoc, String gioiTinh, String diaChi, String email, int trangThai1) {
//        this.chuc_vu = chuc_vu;
//        this.ho_ten = ho_ten;
//        this.ngay_sinh = ngay_sinh;
//        this.sdt = sdt;
//        this.so_can_cuoc = so_can_cuoc;
//        this.gioi_tinh = gioi_tinh;
//        this.dia_chi = dia_chi;
//        this.email = email;
//        this.trang_thai = trang_thai;
//    }

    public int getId_nhan_vien() {
        return id_nhan_vien;
    }

    public ChucVu getChuc_vu() {
        return chuc_vu;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public Date getNgay_sinh() {
        return ngay_sinh;
    }

    public String getSdt() {
        return sdt;
    }

    public String getGioi_tinh() {
        return gioi_tinh;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public String getEmail() {
        return email;
    }

    public String getSo_can_cuoc() {
        return so_can_cuoc;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public String getDeleted() {
        return deleted;
    }

    public int getTrang_thai() {
        return trang_thai;
    }

    public void setId_nhan_vien(int id_nhan_vien) {
        this.id_nhan_vien = id_nhan_vien;
    }

    public void setChuc_vu(ChucVu chuc_vu) {
        this.chuc_vu = chuc_vu;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }

    public void setNgay_sinh(Date ngay_sinh) {
        this.ngay_sinh = ngay_sinh;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setGioi_tinh(String gioi_tinh) {
        this.gioi_tinh = gioi_tinh;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSo_can_cuoc(String so_can_cuoc) {
        this.so_can_cuoc = so_can_cuoc;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public void setTrang_thai(int trang_thai) {
        this.trang_thai = trang_thai;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "id_nhan_vien=" + id_nhan_vien + ", chuc_vu=" + chuc_vu + ", ho_ten=" + ho_ten + ", ngay_sinh=" + ngay_sinh + ", sdt=" + sdt + ", gioi_tinh=" + gioi_tinh + ", dia_chi=" + dia_chi + ", email=" + email + ", so_can_cuoc=" + so_can_cuoc + ", user=" + user + ", password=" + password + ", created_at=" + created_at + ", updated_at=" + updated_at + ", created_by=" + created_by + ", updated_by=" + updated_by + ", deleted=" + deleted + ", trang_thai=" + trang_thai + '}';
    }

    

}
