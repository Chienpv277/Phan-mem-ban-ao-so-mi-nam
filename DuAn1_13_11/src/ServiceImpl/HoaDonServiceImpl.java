/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceImpl;

import DomainModel.HoaDon;
import DomainModel.HoaDonChiTiet;
import DomainModel.ChiTietSanPham;
import Repository.HoaDonRepository;
import ViewModel.HoaDonChiTietViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 84386
 */
public class HoaDonServiceImpl {

    HoaDonRepository repository;

//    public ArrayList<HoaDonChiTiet> getAllhoadon_byMa(String idHD) {
//        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
//        ArrayList<DomainModel.HoaDonChiTiet> kh = repository.getAllhoadonct_byMa(idHD);
//        for (DomainModel.HoaDonChiTiet x : kh) {
//            list.add(new HoaDonChiTiet(x.getId_hoa_don(), x.getId_chi_tiet_san_pham(), x.getGia(), x.getSo_luong(), x.getCreated_at()));
//        }
//        return list;
//    }
    public HoaDonServiceImpl() {
        this.repository = new HoaDonRepository();
    }

    public ArrayList<HoaDon> all() {
        return this.repository.all();
    }

    public ArrayList<HoaDon> getListHoaDon() {
        return this.repository.getListHoaDon();
    }

    public ArrayList<HoaDon> findbytrangthaiDaThanhToan() {
        return this.repository.findbytrangthaiDaThanhToan();
    }

    public ArrayList<HoaDon> findbytrangthaiChuaThanhToan() {
        return this.repository.findbytrangthaiChuaThanhToan();
    }
    
    public ArrayList<HoaDon> findbytrangthaiChuaThanhToanHD() {
        return this.repository.findbytrangthaiChuaThanhToanHD();
    }

    public ArrayList<HoaDon> getListHoaDonTienMat() {
        return this.repository.getListHoaDonTienMat();
    }

    public ArrayList<HoaDon> getListHoaDonChuyenKhoan() {
        return this.repository.getListHoaDonChuyenKhoan();
    }

    public HoaDon getByHoaDonId(int id) {
        return this.repository.getByHoaDonId(id);
    }

    public HoaDon getByHoaDonTenKhachHang(String ten) {
        return this.repository.getByHoaDonTenKhachHang(ten);
    }

    public HoaDon getByHoaDonNhanVien(int id) {
        return this.repository.getByHoaDonNhanVien(id);
    }
    
    public ArrayList<HoaDonChiTietViewModel> getListHoaDonChiietById(int id) {
        return this.repository.getListHoaDonChiietById(id);
    }
    //sua
    public HoaDonChiTietViewModel getListHoaDonChiietById1(int id) {
        return this.repository.getListHoaDonChiietById1(id);
    }
    
   

    public ArrayList<HoaDonChiTiet> getListHoaDonChiietByIdSP(int id) {
        return this.repository.getListHoaDonChiietByIdSP(id);
    }
    
    public ArrayList<HoaDonChiTiet> getListHoaDonChiietByIdSP1(int id) {
        return this.repository.getListHoaDonChiietByIdSP1(id);
    }

    public ArrayList<HoaDonChiTietViewModel> getListHoaDonChiTiet() {
        return this.repository.getListHoaDonChiTiet();
    }

    public ArrayList<HoaDonChiTiet> getListHoaDonChiTietVM() {
        return this.repository.getListHoaDonChiTietVM();
    }

    public List<HoaDon> getByHoaDonALL(String tim) {
        return this.repository.getByHoaDonALL(tim);
    }

//    public Boolean add(HoaDonChiTiet s) {
//        return this.repository.add(s);
//    }
//    
//     public HoaDonChiTietViewModel update1(HoaDonChiTiet s) {
//        return this.repository.update(s);
//    }
//     public HoaDonChiTietViewModel update2(HoaDonChiTiet s) {
//        return this.repository.update2(s);
//    }
//    public Long countHoaDon() {
//        return this.repository.countHoaDon();
//    }
//    
    public HoaDonChiTiet updateHDCT(HoaDonChiTiet hdct) {
//        var x = repository.updateHDCT(new HoaDonChiTiet(hdct.getId_hoa_don(), hdct.getId_chi_tiet_san_pham(), hdct.getSo_luong(), hdct.getGia()));
//        return new HoaDonChiTietViewModel(x.getId_hoa_don(), x.getId_chi_tiet_san_pham(), x.getSo_luong(), x.getGia());
        return repository.updateHDCT(new HoaDonChiTiet(hdct.getId_hoa_don(), hdct.getId_chi_tiet_san_pham(), hdct.getSo_luong(), hdct.getGia()));
    }

    public HoaDonChiTiet inserthdct(HoaDonChiTiet hdct) {
//        var x = repository.inserthoadonct(new HoaDonChiTiet(hdct.getId_hoa_don(), hdct.getId_chi_tiet_san_pham(), hdct.getSo_luong(), hdct.getGia()));
//        return new HoaDonChiTietViewModel(x.getId_hoa_don(), x.getId_chi_tiet_san_pham(), x.getSo_luong(), x.getGia());
        return repository.inserthoadonct(new HoaDonChiTiet(hdct.getId_hoa_don(), hdct.getId_chi_tiet_san_pham(), hdct.getSo_luong(), hdct.getGia()));

    }

    public boolean add(HoaDonChiTiet hoaDonChiTiet) {
        return repository.add(hoaDonChiTiet);
    }

//    public ArrayList<HoaDonChiTiet> getAllhoadon_byrepositoryMa(String idHD) {
//        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
//        ArrayList<DomainModel.HoaDonChiTiet> kh = repository.getListHoaDonChiietById(idHD);
//        for (DomainModel.HoaDonChiTiet x : kh) {
//            list.add(new HoaDonChiTiet(x.getId_hoa_don(), x.getClass(), x.getGia(), x.getSo_luong(), x.getCreated_at(), x.getThanh_tien()));
//        }
//        return list;
//    }

   
}
