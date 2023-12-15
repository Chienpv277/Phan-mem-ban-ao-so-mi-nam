/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceImpl;

import DomainModel.SanPham;
import Repository.SanPhamRepository;
import Service.SanPhamService;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public class SanPhamSrviceImpl implements SanPhamService {

    private SanPhamRepository repository = new SanPhamRepository();

    @Override
    public ArrayList<SanPham> getListSanPham() {
        return repository.getListSanPham();
    }

    @Override
    public SanPham getSanPhamByID(int id) {
        return repository.getSanPhamById(id);
    }

    @Override
    public Boolean add(SanPham sanPham) {
        return repository.add(sanPham);
    }

    @Override
    public Boolean update( SanPham sanPham) {
        return repository.update(sanPham);
    }

    @Override
    public Boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public SanPham getIdByTenSanPham(String ten) {
         return repository.getIdByTenSanPham(ten);
    }

    @Override
    public ArrayList<SanPham> searchByName(String ten) {
        return repository.searchByName(ten);
    }

}
