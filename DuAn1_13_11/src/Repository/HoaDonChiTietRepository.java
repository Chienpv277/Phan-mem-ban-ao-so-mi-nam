/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.HoaDon;
import DomainModel.KhachHang;
import DomainModel.NhanVien;
import DomainModel.Vourcher;
import Utilities.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author 84386
 */
public class HoaDonChiTietRepository {

    public ArrayList<HoaDon> getListHoaDon() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "select a.id_chi_tiet_san_pham, b.id_san_pham, b.ma, b.ten, \n"
                + "                        c.id_mau_sac, c.ma, c.ten, \n"
                + "                		d.id_hang, d.ma, d.ten,               		\n"
                + "                		f.id_size, f.ma, f.ten\n"
                + "                		so_luong_ton, gia from chi_tiet_san_pham a\n"
                + "                		join san_pham b on a.id_san_pham = b.id_san_pham\n"
                + "                		join mau_sac c on a.id_mau_sac = c.id_mau_sac\n"
                + "                		join hang d on a.id_hang = d.id_hang             		\n"
                + "                		join size f on a.id_size = f.id_size\n"
                + "                             ORDER BY a.id_chi_tiet_san_pham DESC;";

        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                KhachHang khachHang = new KhachHang();
                NhanVien nhanVien = new NhanVien();
                Vourcher vourcher = new Vourcher();

                hoaDon.setId_hoa_don(rs.getInt(1));
                hoaDon.setCreated_at(rs.getDate(2));
                hoaDon.setNgay_thanh_toan(rs.getDate(3));

                khachHang.setHo_ten(rs.getString(4));
                khachHang.setId_khach_hang(rs.getInt(5));
                hoaDon.setId_khach_hang(khachHang);

                hoaDon.setTong_tien(rs.getFloat(6));
                hoaDon.setHinh_thuc_thanh_toan(rs.getString(7));
                hoaDon.setTrang_thai(rs.getInt(8));

                nhanVien.setHo_ten(rs.getString(9));
                nhanVien.setId_nhan_vien(rs.getInt(10));
                hoaDon.setId_nhan_vien(nhanVien);
                list.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
}
