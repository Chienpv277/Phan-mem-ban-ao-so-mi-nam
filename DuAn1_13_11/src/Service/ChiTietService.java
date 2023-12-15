/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.ChiTietSanPham;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author FPTSHOP
 */
public interface ChiTietService {

    ArrayList<ChiTietSanPham> getListChiTiet();

    Boolean add(ChiTietSanPham chiTietSanPham);

    Boolean update(Integer id, ChiTietSanPham chiTietSanPham);

    Boolean delete(Integer id);

    ArrayList<ChiTietSanPham> getListChiTietByTen(String tenSP);

    ArrayList<ChiTietSanPham> getListChiTietByHang(String tenHang);

    ArrayList<ChiTietSanPham> getListChiTietByChatLieu(String tenChatLieu);

    ArrayList<ChiTietSanPham> getListChiTietBySize(String tenSize);

    ArrayList<ChiTietSanPham> getListChiTietByMauSac(String tenMauSac);

    ArrayList<ChiTietSanPham> getListChiTietByCoAo(String tenCoAo);

    ArrayList<ChiTietSanPham> getListChiTietByCoTay(String tenCoTay);

    ArrayList<ChiTietSanPham> searchByTen(String ten);

    ArrayList<ChiTietSanPham> searchByChatLieu(String ten);

    ArrayList<ChiTietSanPham> searchByCoTay(String ten);

    ArrayList<ChiTietSanPham> searchByCoAo(String ten);

    ArrayList<ChiTietSanPham> searchByDangAo(String ten);

    ArrayList<ChiTietSanPham> searchByHang(String ten);

    ArrayList<ChiTietSanPham> searchByMauSac(String ten);

    ArrayList<ChiTietSanPham> searchBySize(String ten);

    ArrayList<ChiTietSanPham> getListChiTietByDangAo(String tenDangAo);

    int countDB();

    Map<String, Integer> countSanPhamByTen();
}
