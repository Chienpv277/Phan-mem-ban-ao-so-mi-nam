/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.SanPham;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public interface SanPhamService {
    public ArrayList<SanPham> getListSanPham();
    public SanPham getSanPhamByID(int id);
    public SanPham getIdByTenSanPham(String ten);
    public Boolean add(SanPham sanPham);
    public Boolean update(SanPham sanPham);
    public Boolean delete(Integer id);
    public ArrayList<SanPham> searchByName(String ten);
}
