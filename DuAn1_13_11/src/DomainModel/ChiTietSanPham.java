/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

import java.time.LocalDate;

/**
 *
 * @author FPTSHOP
 */
public class ChiTietSanPham {

    private int id;

    private SanPham sanPham;

    private Hang hang;

    private MauSac mauSac;

    private ChatLieu chatLieu;

    private DangAo dangAo;

    private Size size;

    private CoTay coTay;

    private CoAo coAo;

    // private HinhAnh hinhAnh;
    private int soLuong;

    private float gia;

    private LocalDate created_at;

    private LocalDate updated_at;

    private String created_by;

    private String updated_by;

    private String delete;

    public ChiTietSanPham() {
    }
    
    public ChiTietSanPham(int id) {
        this.id = id;
    }

    public ChiTietSanPham(int id, SanPham sanPham, Hang hang, MauSac mauSac, ChatLieu chatLieu, DangAo dangAo, Size size, CoTay coTay, CoAo coAo, int soLuong, float gia, LocalDate created_at, LocalDate updated_at, String created_by, String updated_by, String delete) {
        this.id = id;
        this.sanPham = sanPham;
        this.hang = hang;
        this.mauSac = mauSac;
        this.chatLieu = chatLieu;
        this.dangAo = dangAo;
        this.size = size;
        this.coTay = coTay;
        this.coAo = coAo;
        this.soLuong = soLuong;
        this.gia = gia;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.delete = delete;
    }

    public ChiTietSanPham(int idChiTietSanPham, int idHoaDonResult) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public Hang getHang() {
        return hang;
    }

    public void setHang(Hang hang) {
        this.hang = hang;
    }

    public MauSac getMauSac() {
        return mauSac;
    }

    public void setMauSac(MauSac mauSac) {
        this.mauSac = mauSac;
    }

    public ChatLieu getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(ChatLieu chatLieu) {
        this.chatLieu = chatLieu;
    }

    public DangAo getDangAo() {
        return dangAo;
    }

    public void setDangAo(DangAo dangAo) {
        this.dangAo = dangAo;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public CoTay getCoTay() {
        return coTay;
    }

    public void setCoTay(CoTay coTay) {
        this.coTay = coTay;
    }

    public CoAo getCoAo() {
        return coAo;
    }

    public void setCoAo(CoAo coAo) {
        this.coAo = coAo;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
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

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String trangThai() {
        if (soLuong > 0) {
            return "Còn hàng";
        } else {
            return "Hết hàng";
        }
    }
    
    

    @Override
    public String toString() {
        return "ChiTietSanPham{" + "id=" + id + ", sanPham=" + sanPham.getTen()
                + ", hang=" + hang.getTen() + ", mauSac=" + mauSac.getTen()
                + ", chatLieu=" + chatLieu.getId() + ", dangAo=" + dangAo.getId()
                + ", size=" + size.getId() + ", coTay=" + coTay.getId()
                + ", coAo=" + coAo.getId() + ", soLuong=" + soLuong
                + ", gia=" + gia + ", created_at=" + created_at + ", updated_at="
                + updated_at + ", created_by=" + created_by
                + ", updated_by=" + updated_by + ", delete=" + delete + '}';
    }

}
