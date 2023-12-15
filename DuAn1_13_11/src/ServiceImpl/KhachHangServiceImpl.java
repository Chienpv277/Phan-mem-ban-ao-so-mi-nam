/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceImpl;

import DomainModel.KhachHang;
import Repository.KhachHangRepository;
import Service.KhachHangService;
import java.util.ArrayList;

/**
 *
 * @author 84386
 */
public class KhachHangServiceImpl implements KhachHangService {

    private KhachHangRepository repository = new KhachHangRepository();

    @Override
    public ArrayList<KhachHang> getListKhachHang() {
        return repository.getListKhachHang();
    }

    @Override
    public Boolean add(KhachHang khachHang) {
        return repository.add(khachHang);
    }

    @Override
    public Boolean update(Integer id, KhachHang khachHang) {
        return repository.update(id, khachHang);
    }

    @Override
    public Boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public ArrayList<KhachHang> searchByTen(String ten) {
        return repository.searchByTen(ten);
    }

    @Override
    public ArrayList<KhachHang> searchBySdt(String sdt) {
        return repository.searchBySdt(sdt);
    }

    @Override
    public ArrayList<KhachHang> searchByEmail(String email) {
        return repository.searchByEmail(email);
    }

}
