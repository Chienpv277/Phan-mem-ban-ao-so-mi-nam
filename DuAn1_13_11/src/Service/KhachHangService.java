/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.KhachHang;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public interface KhachHangService {
    public ArrayList<KhachHang> getListKhachHang();
    public ArrayList<KhachHang> searchByTen(String ten);
    public ArrayList<KhachHang> searchBySdt(String sdt);
    public ArrayList<KhachHang> searchByEmail(String email);
    public Boolean add(KhachHang khachHang);
    public Boolean update(Integer id, KhachHang khachHang);
    public Boolean delete(Integer id);
}
