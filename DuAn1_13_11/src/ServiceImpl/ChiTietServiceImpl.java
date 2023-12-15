/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceImpl;

import DomainModel.ChiTietSanPham;
import Repository.ChiTietSanPhamRepository;
import Service.ChiTietService;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author FPTSHOP
 */
public class ChiTietServiceImpl implements ChiTietService {

    private ChiTietSanPhamRepository repository = new ChiTietSanPhamRepository();

    @Override
    public ArrayList<ChiTietSanPham> getListChiTiet() {
        return repository.getListChiTiet();
    }

    @Override
    public Boolean add(ChiTietSanPham chiTietSanPham) {
        return repository.add(chiTietSanPham);
    }

    @Override
    public Boolean update(Integer id, ChiTietSanPham chiTietSanPham) {
        return repository.update(id, chiTietSanPham);
    }

    @Override
    public Boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public ArrayList<ChiTietSanPham> getListChiTietByTen(String tenSP) {
        return repository.getListChiTietByTen(tenSP);
    }

    @Override
    public ArrayList<ChiTietSanPham> getListChiTietByHang(String tenHang) {
        return repository.getListChiTietByHang(tenHang);
    }

    @Override
    public ArrayList<ChiTietSanPham> getListChiTietByChatLieu(String tenChatLieu) {
        return repository.getListChiTietChatLieu(tenChatLieu);
    }

    @Override
    public ArrayList<ChiTietSanPham> getListChiTietByDangAo(String tenDangAo) {
        return repository.getListChiTietByDangAo(tenDangAo);
    }

    @Override
    public int countDB() {
        return repository.countDB();
    }

    @Override
    public Map<String, Integer> countSanPhamByTen() {
        return repository.countSanPham();
    }

    @Override
    public ArrayList<ChiTietSanPham> searchByTen(String ten) {
        return repository.searchByTen(ten);
    }

    @Override
    public ArrayList<ChiTietSanPham> searchByChatLieu(String ten) {
        return repository.searchByChatLieu(ten);
    }

    @Override
    public ArrayList<ChiTietSanPham> searchByCoTay(String ten) {
        return repository.searchByCoTay(ten);
    }

    @Override
    public ArrayList<ChiTietSanPham> searchByCoAo(String ten) {
        return repository.searchByCoAo(ten);
    }

    @Override
    public ArrayList<ChiTietSanPham> searchByDangAo(String ten) {
        return repository.searchByDangAo(ten);
    }

    @Override
    public ArrayList<ChiTietSanPham> searchByHang(String ten) {
        return repository.searchByHang(ten);
    }

    @Override
    public ArrayList<ChiTietSanPham> searchByMauSac(String ten) {
        return repository.searchByMauSac(ten);
    }

    @Override
    public ArrayList<ChiTietSanPham> searchBySize(String ten) {
        return repository.searchBySize(ten);
    }

    @Override
    public ArrayList<ChiTietSanPham> getListChiTietBySize(String tenSize) {
        return repository.getListChiTietBySize(tenSize);
    }

    @Override
    public ArrayList<ChiTietSanPham> getListChiTietByMauSac(String tenMauSac) {
        return repository.getListChiTietByMauSac(tenMauSac);
    }

    @Override
    public ArrayList<ChiTietSanPham> getListChiTietByCoAo(String tenCoAo) {
        return repository.getListChiTietByCoAo(tenCoAo);
    }

    @Override
    public ArrayList<ChiTietSanPham> getListChiTietByCoTay(String tenCoTay) {
        return repository.getListChiTietByCoTay(tenCoTay);
    }
}
