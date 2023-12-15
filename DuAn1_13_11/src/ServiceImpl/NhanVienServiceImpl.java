/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceImpl;

import DomainModel.ChucVu;
import DomainModel.NhanVien_1;
import Repository.NhanVienRepository;
import Service.NhanVienService;
import Utilities.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADAMIN
 */
public class NhanVienServiceImpl implements NhanVienService {

    private NhanVienRepository nhanVienRepository = new NhanVienRepository();

    @Override
    public List<NhanVien_1> getAll() {
        return nhanVienRepository.getAll();
    }

    @Override
    public String add(NhanVien_1 nhanVien) {
        boolean add = nhanVienRepository.add(nhanVien);
        if (add) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public List<NhanVien_1> searchByName(String hoTen) {
        List<NhanVien_1> listNhanVien_1 = new ArrayList<>();
        String query = "SELECT * FROM [dbo].[nhan_vien] where [ho_ten] LIKE N'%" + hoTen + "%'";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien_1 nhanVien = new NhanVien_1();
                ChucVu chucVu = new ChucVu();
                nhanVien.setId_nhan_vien(rs.getInt(1));
                chucVu.setTen(rs.getString(2));
                nhanVien.setChuc_vu(chucVu);
                nhanVien.setHo_ten(rs.getString(3));
                nhanVien.setNgay_sinh(rs.getDate(4));
                nhanVien.setSdt(rs.getString(5));
                nhanVien.setGioi_tinh(rs.getString(6));
                nhanVien.setDia_chi(rs.getString(7));
                nhanVien.setEmail(rs.getString(8));
                nhanVien.setSo_can_cuoc(rs.getString(9));
                nhanVien.setCreated_at(rs.getDate(10));
                nhanVien.setUpdated_at(rs.getDate(11));
                nhanVien.setCreated_by(rs.getString(12));
                nhanVien.setUpdated_by(rs.getString(13));
                nhanVien.setDeleted(rs.getString(14));
                nhanVien.setTrang_thai(rs.getInt(15));
                listNhanVien_1.add(nhanVien);
            }
            return listNhanVien_1;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return listNhanVien_1;
    }

    @Override
    public List<NhanVien_1> searchByEmail(String email) {
        List<NhanVien_1> listNhanVien_1 = new ArrayList<>();
        String query = "SELECT * FROM [dbo].[nhan_vien] where [email] LIKE N'%" + email + "%'";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien_1 nhanVien = new NhanVien_1();
                ChucVu chucVu = new ChucVu();
                nhanVien.setId_nhan_vien(rs.getInt(1));
                chucVu.setTen(rs.getString(2));
                nhanVien.setChuc_vu(chucVu);
                nhanVien.setHo_ten(rs.getString(3));
                nhanVien.setNgay_sinh(rs.getDate(4));
                nhanVien.setSdt(rs.getString(5));
                nhanVien.setGioi_tinh(rs.getString(6));
                nhanVien.setDia_chi(rs.getString(7));
                nhanVien.setEmail(rs.getString(8));
                nhanVien.setSo_can_cuoc(rs.getString(9));
                nhanVien.setCreated_at(rs.getDate(10));
                nhanVien.setUpdated_at(rs.getDate(11));
                nhanVien.setCreated_by(rs.getString(12));
                nhanVien.setUpdated_by(rs.getString(13));
                nhanVien.setDeleted(rs.getString(14));
                nhanVien.setTrang_thai(rs.getInt(15));
                listNhanVien_1.add(nhanVien);
            }
            return listNhanVien_1;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return listNhanVien_1;
    }

    @Override
    public List<NhanVien_1> searchBySdt(String sdt) {
        List<NhanVien_1> listNhanVien_1 = new ArrayList<>();
        String query = "SELECT * FROM [dbo].[nhan_vien] where [sdt] LIKE N'%" + sdt + "%'";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien_1 nhanVien = new NhanVien_1();
                ChucVu chucVu = new ChucVu();
                nhanVien.setId_nhan_vien(rs.getInt(1));
                chucVu.setTen(rs.getString(2));
                nhanVien.setChuc_vu(chucVu);
                nhanVien.setHo_ten(rs.getString(3));
                nhanVien.setNgay_sinh(rs.getDate(4));
                nhanVien.setSdt(rs.getString(5));
                nhanVien.setGioi_tinh(rs.getString(6));
                nhanVien.setDia_chi(rs.getString(7));
                nhanVien.setEmail(rs.getString(8));
                nhanVien.setSo_can_cuoc(rs.getString(9));
                nhanVien.setCreated_at(rs.getDate(10));
                nhanVien.setUpdated_at(rs.getDate(11));
                nhanVien.setCreated_by(rs.getString(12));
                nhanVien.setUpdated_by(rs.getString(13));
                nhanVien.setDeleted(rs.getString(14));
                nhanVien.setTrang_thai(rs.getInt(15));
                listNhanVien_1.add(nhanVien);
            }
            return listNhanVien_1;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return listNhanVien_1;
    }

    @Override
    public String update(NhanVien_1 nhanVien, int id) {
        NhanVien_1 nv = new NhanVien_1(nhanVien.getChuc_vu(), nhanVien.getHo_ten(), nhanVien.getNgay_sinh(), nhanVien.getSdt(), nhanVien.getSo_can_cuoc(), nhanVien.getGioi_tinh(), nhanVien.getDia_chi(), nhanVien.getEmail(), nhanVien.getTrang_thai());
        boolean update = nhanVienRepository.update(nhanVien, id);
        if (update) {
            return "Sửa thành công!";
        } else {
            return "Sửa lỗi!";
        }
    }

    @Override
    public List<NhanVien_1> getAllNghi() {
        List<NhanVien_1> listNv = new ArrayList<>();
        List<NhanVien_1> list = nhanVienRepository.getAllNghi();
        for (NhanVien_1 nhanVien : list) {
            listNv.add(new NhanVien_1(nhanVien));
        }
        return listNv;
    }

}
