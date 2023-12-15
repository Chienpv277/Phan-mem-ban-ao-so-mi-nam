/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.HoaDon;
import DomainModel.HoaDonViewModel;
import DomainModel.HoaDonChiTiet;
import DomainModel.KhachHang;
import DomainModel.NhanVien;
import DomainModel.ChatLieu;
import DomainModel.ChiTietSanPham;
import DomainModel.CoAo;
import DomainModel.CoTay;
import DomainModel.DangAo;
import DomainModel.Hang;
import DomainModel.MauSac;
import DomainModel.SanPham;
import DomainModel.Size;
import Utilities.DBConnection;
import Utilities.JDBCHelper;
import ViewModel.GioHangViewModel;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import ViewModel.HoaDonChiTietViewModel;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 84386
 */
public class HoaDonRepository {
//    HoaDonRepository hd = new HoaDonRepository();

    Connection cn;
    long count, soTrang, trang = 1;
    Statement st;
    ResultSet rs;

    public HoaDon gethdBymaHD(String maHD) {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "select id from hoadon where maHD=?";
        ResultSet rs = JDBCHelper.excuteQuery(sql, maHD);
        try {
            while (rs.next()) {
                return new HoaDon(rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int deleteHDCT(int idhd, int idctsp) {
        String sql = "delete from hoa_don_chi_tiet where id_hoa_don = ? AND id_chi_tiet_san_pham = ?";
        int row = JDBCHelper.executeUpdate(sql, idhd, idctsp);
        return row;
    }
    private DBConnection connection;

    public HoaDonChiTiet deleteGH(int idHD, int idCTSP) {
        String sql = "delete from hoa_don_chi_tiet where id_hoa_don = ? AND id_chi_tiet_san_pham = ?";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHD);
            ps.setInt(2, idCTSP);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public HoaDon gethdBymaHD(int maHD) {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "select id_hoa_don from hoa_don where id_hoa_don = ?";
        ResultSet rs = JDBCHelper.excuteQuery(sql, maHD);
        try {
            while (rs.next()) {
                return new HoaDon(rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ChiTietSanPham gethdBymaCTSP(int maHD) {
        ArrayList<ChiTietSanPham> list = new ArrayList<>();
        String sql = "select id_chi_tiet_san_pham from chi_tiet_san_pham where id_chi_tiet_san_pham = ?";
        ResultSet rs = JDBCHelper.excuteQuery(sql, maHD);
        try {
            while (rs.next()) {
                return new ChiTietSanPham(rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<HoaDon> all() {
        ArrayList<HoaDon> hd = new ArrayList<>();
        ArrayList<NhanVien> nv = new ArrayList<>();
        ArrayList<KhachHang> kh = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "select  * "
                    + " from hoa_don join khach_hang on hoa_don.id_khach_hang = khach_hang.id_khach_hang \n"
                    + "                     join nhan_vien on hoa_don.id_nhan_vien = nhan_vien.id_nhan_vien \n";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int id_hoa_don = rs.getInt("id_hoa_don");
                NhanVien id_nhan_vien = new NhanVien(rs.getInt("id_nhan_vien"));
                String ho_ten_NV = rs.getString("ho_ten");
                KhachHang id_khach_hang = new KhachHang(rs.getInt("id_khach_hang"));
//                KhachHang id_khach_hang = new KhachHang(rs.getInt("id_khach_hang"));
                Date ntt = rs.getDate("ngay_thanh_toan");
                String sdt = rs.getString("sdt");
                String dc = rs.getString("dia_chi");
                Float phiship = rs.getFloat("phi_ship");
                Float tt = rs.getFloat("tong_tien");
                Date ca = rs.getDate("created_at");
                Date ua = rs.getDate("updated_at");
                String cb = rs.getString("created_by");
                String ub = rs.getString("updated_by");
                String d = rs.getString("deleted");
                String httt = rs.getString("hinh_thuc_thanh_toan");
                int trangthai = rs.getInt("trang_thai");

                HoaDon hoadon = new HoaDon(id_hoa_don, id_nhan_vien, id_khach_hang, ntt, sdt, dc, phiship, tt, ca, ua, cb, ub, d, httt, trangthai);
                hd.add(hoadon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hd;
    }

    //ok
    public ArrayList<HoaDon> getListHoaDon() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "select hoa_don.id_hoa_don,\n"
                + "                hoa_don.created_at,\n"
                + "                hoa_don.ngay_thanh_toan,\n"
                + "                khach_hang.ho_ten,\n"
                + "                hoa_don.id_khach_hang, \n"
                + "                hoa_don.tong_tien,\n"
                + "                hoa_don.hinh_thuc_thanh_toan,\n"
                + "                hoa_don.trang_thai,\n"
                + "                nhan_vien.ho_ten, \n"
                + "		   hoa_don.id_nhan_vien, \n"
                + "                vourcher.[%_khuyen_mai] ,\n"
                + "                hoa_don.id_voucher \n"
                + "                from hoa_don \n"
                + "				join khach_hang on hoa_don.id_khach_hang = khach_hang.id_khach_hang \n"
                + "                join nhan_vien on hoa_don.id_nhan_vien = nhan_vien.id_nhan_vien \n"
                + "                join vourcher on hoa_don.id_voucher = vourcher.id_voucher  ";

        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                KhachHang khachHang = new KhachHang();
                NhanVien nhanVien = new NhanVien();

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

    public ArrayList<HoaDon> getListHoaDonChuyenKhoan() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "select hoa_don.id_hoa_don,"
                + "hoa_don.created_at,"
                + "hoa_don.ngay_thanh_toan,"
                + "khach_hang.ho_ten,"
                + "hoa_don.id_khach_hang, "
                + "hoa_don.tong_tien,"
                + "hoa_don.hinh_thuc_thanh_toan,"
                + "hoa_don.trang_thai,"
                + "                nhan_vien.ho_ten, \n"
                + "		   hoa_don.id_nhan_vien \n"
                //                + "                vourcher.[%_khuyen_mai] ,\n"
                //                + "                hoa_don.id_voucher \n"
                + " from hoa_don join khach_hang on hoa_don.id_khach_hang = khach_hang.id_khach_hang \n"
                + "                     join nhan_vien on hoa_don.id_nhan_vien = nhan_vien.id_nhan_vien \n"
                //                + "                    join vourcher on hoa_don.id_voucher = vourcher.id_voucher "
                + "where hinh_thuc_thanh_toan = 'chuyen khoan'";

        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                KhachHang khachHang = new KhachHang();
                NhanVien nhanVien = new NhanVien();

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

    public ArrayList<HoaDon> getListHoaDonTienMat() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "select hoa_don.id_hoa_don,"
                + "hoa_don.created_at,"
                + "hoa_don.ngay_thanh_toan,"
                + "khach_hang.ho_ten,"
                + "hoa_don.id_khach_hang, "
                + "hoa_don.tong_tien,"
                + "hoa_don.hinh_thuc_thanh_toan,"
                + "hoa_don.trang_thai,"
                + "                nhan_vien.ho_ten, \n"
                + "		   hoa_don.id_nhan_vien \n"
                //                + "                vourcher.[%_khuyen_mai] ,\n"
                //                + "                hoa_don.id_voucher \n"
                + " from hoa_don join khach_hang on hoa_don.id_khach_hang = khach_hang.id_khach_hang \n"
                + "                     join nhan_vien on hoa_don.id_nhan_vien = nhan_vien.id_nhan_vien \n"
                //                + "                    join vourcher on hoa_don.id_voucher = vourcher.id_voucher "
                + "where hinh_thuc_thanh_toan = 'tien mat'";

        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                KhachHang khachHang = new KhachHang();
                NhanVien nhanVien = new NhanVien();

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

//                
//                
//                hoaDon.setId_voucher(vourcher);
                list.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //ok
    public ArrayList<HoaDon> findbytrangthaiDaThanhToan() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "select hoa_don.id_hoa_don,"
                + "hoa_don.created_at,"
                + "hoa_don.ngay_thanh_toan,"
                + "khach_hang.ho_ten,"
                + "hoa_don.id_khach_hang, "
                + "hoa_don.tong_tien,"
                + "hoa_don.hinh_thuc_thanh_toan,"
                + "hoa_don.trang_thai,"
                + "                nhan_vien.ho_ten, \n"
                + "		   hoa_don.id_nhan_vien \n"
                + " from hoa_don join khach_hang on hoa_don.id_khach_hang = khach_hang.id_khach_hang \n"
                + "                     join nhan_vien on hoa_don.id_nhan_vien = nhan_vien.id_nhan_vien \n"
                + "where hoa_don.trang_thai = 1"
                + "order by ngay_thanh_toan desc";

        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                KhachHang khachHang = new KhachHang();
                NhanVien nhanVien = new NhanVien();

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

//                
//                
//                hoaDon.setId_voucher(vourcher);
                list.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //ok
    public ArrayList<HoaDon> findbytrangthaiChuaThanhToan() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "select hoa_don.id_hoa_don,"
                + "hoa_don.created_at,"
                + "hoa_don.ngay_thanh_toan,"
                + "khach_hang.ho_ten,"
                + "hoa_don.id_khach_hang, "
                + "hoa_don.tong_tien,"
                + "hoa_don.hinh_thuc_thanh_toan,"
                + "hoa_don.trang_thai,"
                + "                nhan_vien.ho_ten, \n"
                + "		   hoa_don.id_nhan_vien \n"
                //                + "                vourcher.[%_khuyen_mai] ,\n"
                //                + "                hoa_don.id_voucher \n"
                + " from hoa_don join khach_hang on hoa_don.id_khach_hang = khach_hang.id_khach_hang \n"
                + "                     join nhan_vien on hoa_don.id_nhan_vien = nhan_vien.id_nhan_vien \n"
                //                + "                    join vourcher on hoa_don.id_voucher = vourcher.id_voucher "
                + "where hoa_don.trang_thai = 0";

        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                KhachHang khachHang = new KhachHang();
                NhanVien nhanVien = new NhanVien();

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

//                
//                
//                hoaDon.setId_voucher(vourcher);
                list.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDon> findbytrangthaiChuaThanhToanHD() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "select hoa_don.id_hoa_don,\n"
                + "       hoa_don.created_at,\n"
                + "       hoa_don.ngay_thanh_toan,\n"
                + "       khach_hang.ho_ten,  \n"
                + "	   khach_hang.id_khach_hang,   \n"
                + "       hoa_don.tong_tien,\n"
                + "       hoa_don.hinh_thuc_thanh_toan,\n"
                + "       hoa_don.trang_thai,\n"
                + "       nhan_vien.ho_ten,\n"
                + "	   nhan_vien.id_nhan_vien\n"
                + "      from hoa_don \n"
                + "	  join khach_hang on hoa_don.id_khach_hang = khach_hang.id_khach_hang \n"
                + "      join nhan_vien on hoa_don.id_nhan_vien = nhan_vien.id_nhan_vien \n"
                + "       where hoa_don.trang_thai = 0";

        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                KhachHang khachHang = new KhachHang();
                NhanVien nhanVien = new NhanVien();

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

//                
//                
//                hoaDon.setId_voucher(vourcher);
                list.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDon> findbytrangthaiChuaThanhToanHDNull() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "	select       hoa_don.id_hoa_don,\n"
                + "                 hoa_don.created_at,\n"
                + "                 hoa_don.ngay_thanh_toan,\n"
                + "                 hoa_don.id_khach_hang, \n"
                + "                 hoa_don.tong_tien,\n"
                + "                 hoa_don.hinh_thuc_thanh_toan,\n"
                + "                 hoa_don.trang_thai,        \n"
                + "                 hoa_don.id_nhan_vien \n"
                + "                 from hoa_don \n"
                + "                 where hoa_don.trang_thai = 0";

        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                KhachHang khachHang = new KhachHang();
                NhanVien nhanVien = new NhanVien();

                hoaDon.setId_hoa_don(rs.getInt(1));
                hoaDon.setCreated_at(rs.getDate(2));
                hoaDon.setNgay_thanh_toan(rs.getDate(3));

//                khachHang.setHo_ten(rs.getString(4));
                khachHang.setId_khach_hang(rs.getInt(4));

                hoaDon.setId_khach_hang(khachHang);
                hoaDon.setTong_tien(rs.getFloat(5));
                hoaDon.setHinh_thuc_thanh_toan(rs.getString(6));
                hoaDon.setTrang_thai(rs.getInt(7));

//                nhanVien.setHo_ten(rs.getString(9));
                nhanVien.setId_nhan_vien(rs.getInt(8));
                hoaDon.setId_nhan_vien(nhanVien);

//                
//                
//                hoaDon.setId_voucher(vourcher);
                list.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDon> findbyHD() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "select hoa_don.id_hoa_don,\n"
                + "       hoa_don.created_at,\n"
                + "       hoa_don.ngay_thanh_toan,\n"
                + "       khach_hang.ho_ten,  \n"
                + "	   khach_hang.id_khach_hang,   \n"
                + "         khach_hang.ma, \n"
                + "       hoa_don.tong_tien,\n"
                + "       hoa_don.hinh_thuc_thanh_toan,\n"
                + "       hoa_don.trang_thai,\n"
                + "       nhan_vien.ho_ten,\n"
                + "	   nhan_vien.id_nhan_vien\n"
                + "      from hoa_don \n"
                + "	  join khach_hang on hoa_don.id_khach_hang = khach_hang.id_khach_hang \n"
                + "      join nhan_vien on hoa_don.id_nhan_vien = nhan_vien.id_nhan_vien \n"
                + "     order by ngay_thanh_toan desc";

        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                KhachHang khachHang = new KhachHang();
                NhanVien nhanVien = new NhanVien();

                hoaDon.setId_hoa_don(rs.getInt(1));
                hoaDon.setCreated_at(rs.getDate(2));
                hoaDon.setNgay_thanh_toan(rs.getDate(3));

                khachHang.setHo_ten(rs.getString(4));
                khachHang.setId_khach_hang(rs.getInt(5));
                khachHang.setMa(rs.getString(6));

                hoaDon.setId_khach_hang(khachHang);
                hoaDon.setTong_tien(rs.getFloat(7));
                hoaDon.setHinh_thuc_thanh_toan(rs.getString(8));
                hoaDon.setTrang_thai(rs.getInt(9));

                nhanVien.setHo_ten(rs.getString(10));
                nhanVien.setId_nhan_vien(rs.getInt(11));
                hoaDon.setId_nhan_vien(nhanVien);

//                
//                
//                hoaDon.setId_voucher(vourcher);
                list.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDon> findbyHDchuatt() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "select hoa_don.id_hoa_don,\n"
                + "       hoa_don.created_at,\n"
                + "       hoa_don.ngay_thanh_toan,\n"
                + "       khach_hang.ho_ten,  \n"
                + "	   khach_hang.id_khach_hang,   \n"
                + "       hoa_don.tong_tien,\n"
                + "       hoa_don.hinh_thuc_thanh_toan,\n"
                + "       hoa_don.trang_thai,\n"
                + "       nhan_vien.ho_ten,\n"
                + "	   nhan_vien.id_nhan_vien\n"
                + "      from hoa_don \n"
                + "	  join khach_hang on hoa_don.id_khach_hang = khach_hang.id_khach_hang \n"
                + "      join nhan_vien on hoa_don.id_nhan_vien = nhan_vien.id_nhan_vien where hoa_don.trang_thai = 0 order by id_hoa_don desc\n";

        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                KhachHang khachHang = new KhachHang();
                NhanVien nhanVien = new NhanVien();

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

//                
//                
//                hoaDon.setId_voucher(vourcher);
                list.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    ///HÓA ÐON CHI TI?T
    public ArrayList<HoaDonChiTietViewModel> getListHoaDonChiTiet() {
        ArrayList<HoaDonChiTietViewModel> list = new ArrayList<>();
        String sql = "select hoa_don_chi_tiet.id_hoa_don,\n"
                + "		hoa_don_chi_tiet.id_chi_tiet_san_pham, \n"
                + "		san_pham.id_san_pham, "
                + "             san_pham.ten, \n"
                + "             mau_sac.id_mau_sac, "
                + "mau_sac.ten,\n"
                + "		hang.id_hang ,"
                + "hang.ten,\n"
                + "             size.id_size, "
                + "size.ten,  \n"
                + "             hoa_don_chi_tiet.so_luong,"
                + " hoa_don_chi_tiet.gia ,\n"
                + "		hoa_don.trang_thai,\n"
                + "		hoa_don_chi_tiet.thanh_tien\n"
                + "		from hoa_don_chi_tiet \n"
                + "                     join hoa_don on hoa_don_chi_tiet.id_hoa_don = hoa_don.id_hoa_don\n"
                + "			join chi_tiet_san_pham on hoa_don_chi_tiet.id_chi_tiet_san_pham = chi_tiet_san_pham.id_chi_tiet_san_pham\n"
                + "               	join san_pham  on chi_tiet_san_pham.id_san_pham = san_pham.id_san_pham\n"
                + "               	join mau_sac  on chi_tiet_san_pham.id_mau_sac = mau_sac.id_mau_sac\n"
                + "               	join hang  on chi_tiet_san_pham.id_hang = hang.id_hang        \n"
                + "              	join size  on chi_tiet_san_pham.id_size = size.id_size";

        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTietViewModel hoaDonChiTietViewModel = new HoaDonChiTietViewModel();
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                HoaDon hoaDon = new HoaDon();
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                Size size = new Size();

                hoaDon.setId_hoa_don(rs.getInt(1));
                hoaDon.setTrang_thai(rs.getInt(13));
                hoaDonChiTietViewModel.setId_hoa_don(hoaDon);

                chiTietSanPham.setId(rs.getInt(2));
                hoaDonChiTietViewModel.setId_chi_tiet_san_pham(chiTietSanPham);

                sanPham.setId(rs.getInt(3));
                sanPham.setTen(rs.getString(4));
                hoaDonChiTietViewModel.setId_san_pham(sanPham);

                mauSac.setId(rs.getInt(5));
                mauSac.setTen(rs.getString(6));
                hoaDonChiTietViewModel.setId_mau_sac(mauSac);

                hang.setId(rs.getInt(7));
                hang.setTen(rs.getString(8));
                hoaDonChiTietViewModel.setId_hang(hang);

                size.setId(rs.getInt(9));
                size.setTen(rs.getString(10));
                hoaDonChiTietViewModel.setId_size(size);

                hoaDonChiTietViewModel.setSo_luong(rs.getInt(11));
                hoaDonChiTietViewModel.setGia(rs.getFloat(12));
                list.add(hoaDonChiTietViewModel);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDonChiTiet> getListHoaDonChiTietThanhToan() {
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        String sql = "select id_hoa_don, thanh_tien from hoa_don_chi_tiet";

        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//                HoaDonChiTietViewModel hoaDonChiTietViewModel = new HoaDonChiTietViewModel();
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                HoaDon hoaDon = new HoaDon();
//                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
//                SanPham sanPham = new SanPham();
//                MauSac mauSac = new MauSac();
//                Hang hang = new Hang();
//                Size size = new Size();

                hoaDon.setId_hoa_don(rs.getInt(1));
//                hoaDon.setTrang_thai(rs.getInt(13));
                hoaDonChiTiet.setId_hoa_don(hoaDon);
//
//                chiTietSanPham.setId(rs.getInt(2));
//                hoaDonChiTietViewModel.setId_chi_tiet_san_pham(chiTietSanPham);
//
//                sanPham.setId(rs.getInt(3));
//                sanPham.setTen(rs.getString(4));
//                hoaDonChiTietViewModel.setId_san_pham(sanPham);
//
//                mauSac.setId(rs.getInt(5));
//                mauSac.setTen(rs.getString(6));
//                hoaDonChiTietViewModel.setId_mau_sac(mauSac);
//
//                hang.setId(rs.getInt(7));
//                hang.setTen(rs.getString(8));
//                hoaDonChiTietViewModel.setId_hang(hang);
//
//                size.setId(rs.getInt(9));
//                size.setTen(rs.getString(10));
//                hoaDonChiTietViewModel.setId_size(size);
//
//                hoaDonChiTietViewModel.setSo_luong(rs.getInt(11));
//                hoaDonChiTietViewModel.setGia(rs.getFloat(12));
                hoaDonChiTiet.setThanh_tien(rs.getFloat(2));
                list.add(hoaDonChiTiet);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDonChiTiet> getListHoaDonChiTietVM() {
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        String sql = "select hoa_don_chi_tiet.id_hoa_don,\n"
                + "		hoa_don_chi_tiet.id_chi_tiet_san_pham, \n"
                + "		san_pham.id_san_pham, san_pham.ten, \n"
                + "             mau_sac.id_mau_sac, mau_sac.ten,\n"
                + "		hang.id_hang ,hang.ten,\n"
                + "             size.id_size, size.ten,  \n"
                + "             chi_tiet_san_pham.so_luong_ton, chi_tiet_san_pham.gia ,\n"
                + "		hoa_don.trang_thai\n"
                + "		from hoa_don_chi_tiet \n"
                + "                     join hoa_don on hoa_don_chi_tiet.id_hoa_don = hoa_don.id_hoa_don\n"
                + "			join chi_tiet_san_pham on hoa_don_chi_tiet.id_hoa_don = chi_tiet_san_pham.id_chi_tiet_san_pham\n"
                + "               	join san_pham  on chi_tiet_san_pham.id_san_pham = san_pham.id_san_pham\n"
                + "               	join mau_sac  on chi_tiet_san_pham.id_mau_sac = mau_sac.id_mau_sac\n"
                + "               	join hang  on chi_tiet_san_pham.id_hang = hang.id_hang        \n"
                + "              	join size  on chi_tiet_san_pham.id_size = size.id_size";

        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTietViewModel hoaDonChiTietViewModel = new HoaDonChiTietViewModel();
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                HoaDon hoaDon = new HoaDon();
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                Size size = new Size();

                hoaDon.setId_hoa_don(rs.getInt(1));
                hoaDon.setTrang_thai(rs.getInt(13));
                hoaDonChiTietViewModel.setId_hoa_don(hoaDon);

                chiTietSanPham.setId(rs.getInt(2));
                hoaDonChiTietViewModel.setId_chi_tiet_san_pham(chiTietSanPham);

                sanPham.setId(rs.getInt(3));
                sanPham.setTen(rs.getString(4));
                hoaDonChiTietViewModel.setId_san_pham(sanPham);

                mauSac.setId(rs.getInt(5));
                mauSac.setTen(rs.getString(6));
                hoaDonChiTietViewModel.setId_mau_sac(mauSac);

                hang.setId(rs.getInt(7));
                hang.setTen(rs.getString(8));
                hoaDonChiTietViewModel.setId_hang(hang);

                size.setId(rs.getInt(9));
                size.setTen(rs.getString(10));
                hoaDonChiTietViewModel.setId_size(size);

                hoaDonChiTietViewModel.setSo_luong(rs.getInt(11));
                hoaDonChiTietViewModel.setGia(rs.getFloat(12));
                list.add(hoaDonChiTiet);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDonChiTietViewModel> getListHoaDonChiietById(int id) {
        ArrayList<HoaDonChiTietViewModel> list = new ArrayList<>();
        String sql = "select hoa_don_chi_tiet.id_hoa_don,\n"
                + "		hoa_don_chi_tiet.id_chi_tiet_san_pham, \n"
                + "		san_pham.id_san_pham, san_pham.ten, \n"
                + "             mau_sac.id_mau_sac, mau_sac.ten,\n"
                + "		hang.id_hang ,hang.ten,\n"
                + "             size.id_size, size.ten,  \n"
                + "             chi_tiet_san_pham.so_luong_ton, chi_tiet_san_pham.gia ,\n"
                + "		hoa_don.trang_thai\n"
                + "		from hoa_don_chi_tiet \n"
                + "                     join hoa_don on hoa_don_chi_tiet.id_hoa_don = hoa_don.id_hoa_don\n"
                + "			join chi_tiet_san_pham on hoa_don_chi_tiet.id_chi_tiet_san_pham = chi_tiet_san_pham.id_chi_tiet_san_pham\n"
                + "               	join san_pham  on chi_tiet_san_pham.id_san_pham = san_pham.id_san_pham\n"
                + "               	join mau_sac  on chi_tiet_san_pham.id_mau_sac = mau_sac.id_mau_sac\n"
                + "               	join hang  on chi_tiet_san_pham.id_hang = hang.id_hang        \n"
                + "              	join size  on chi_tiet_san_pham.id_size = size.id_size"
                + "                     where hoa_don_chi_tiet.id_hoa_don = ?";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                HoaDonChiTietViewModel chiTietViewModel = new HoaDonChiTietViewModel();
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                HoaDon hoaDon = new HoaDon();
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                Size size = new Size();

                hoaDon.setId_hoa_don(rs.getInt(1));
                hoaDon.setTrang_thai(rs.getInt(13));
                chiTietViewModel.setId_hoa_don(hoaDon);

                chiTietSanPham.setId(rs.getInt(2));
                chiTietViewModel.setId_chi_tiet_san_pham(chiTietSanPham);

                sanPham.setId(rs.getInt(3));
                sanPham.setTen(rs.getString(4));
                chiTietViewModel.setId_san_pham(sanPham);

                mauSac.setId(rs.getInt(5));
                mauSac.setTen(rs.getString(6));
                chiTietViewModel.setId_mau_sac(mauSac);

                hang.setId(rs.getInt(7));
                hang.setTen(rs.getString(8));
                chiTietViewModel.setId_hang(hang);

                size.setId(rs.getInt(9));
                size.setTen(rs.getString(10));
                chiTietViewModel.setId_size(size);

                chiTietViewModel.setSo_luong(rs.getInt(11));
                chiTietViewModel.setGia(rs.getFloat(12));
                list.add(chiTietViewModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public HoaDonChiTietViewModel getListHoaDonChiietById1(int id) {
        HoaDonChiTietViewModel chiTietViewModel = new HoaDonChiTietViewModel();
        String sql = "select hoa_don_chi_tiet.id_hoa_don,\n"
                + "		hoa_don_chi_tiet.id_chi_tiet_san_pham, \n"
                + "		san_pham.id_san_pham, san_pham.ten, \n"
                + "             mau_sac.id_mau_sac, mau_sac.ten,\n"
                + "		hang.id_hang ,hang.ten,\n"
                + "             size.id_size, size.ten,  \n"
                + "             chi_tiet_san_pham.so_luong_ton, chi_tiet_san_pham.gia ,\n"
                + "		hoa_don.trang_thai\n"
                + "		from hoa_don_chi_tiet \n"
                + "                     join hoa_don on hoa_don_chi_tiet.id_hoa_don = hoa_don.id_hoa_don\n"
                + "			join chi_tiet_san_pham on hoa_don_chi_tiet.id_chi_tiet_san_pham = chi_tiet_san_pham.id_chi_tiet_san_pham\n"
                + "               	join san_pham  on chi_tiet_san_pham.id_san_pham = san_pham.id_san_pham\n"
                + "               	join mau_sac  on chi_tiet_san_pham.id_mau_sac = mau_sac.id_mau_sac\n"
                + "               	join hang  on chi_tiet_san_pham.id_hang = hang.id_hang        \n"
                + "              	join size  on chi_tiet_san_pham.id_size = size.id_size"
                + "                     where hoa_don_chi_tiet.id_hoa_don = ?";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

//                HoaDonChiTietViewModel chiTietViewModel = new HoaDonChiTietViewModel();
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                HoaDon hoaDon = new HoaDon();
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                Size size = new Size();

                hoaDon.setId_hoa_don(rs.getInt(1));
                hoaDon.setTrang_thai(rs.getInt(13));
                chiTietViewModel.setId_hoa_don(hoaDon);

                chiTietSanPham.setId(rs.getInt(2));
                chiTietViewModel.setId_chi_tiet_san_pham(chiTietSanPham);

                sanPham.setId(rs.getInt(3));
                sanPham.setTen(rs.getString(4));
                chiTietViewModel.setId_san_pham(sanPham);

                mauSac.setId(rs.getInt(5));
                mauSac.setTen(rs.getString(6));
                chiTietViewModel.setId_mau_sac(mauSac);

                hang.setId(rs.getInt(7));
                hang.setTen(rs.getString(8));
                chiTietViewModel.setId_hang(hang);

                size.setId(rs.getInt(9));
                size.setTen(rs.getString(10));
                chiTietViewModel.setId_size(size);

                chiTietViewModel.setSo_luong(rs.getInt(11));
                chiTietViewModel.setGia(rs.getFloat(12));
//                list.add(chiTietViewModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietViewModel;
    }

//    public GioHangViewModel loadGioHang(int id) {
//        GioHangViewModel list = new GioHangViewModel();
//        String sql = "select hoa_don_chi_tiet.id_hoa_don,\n"
//                + "       hoa_don_chi_tiet.id_chi_tiet_san_pham,\n"
//                + "	   san_pham.id_san_pham ,san_pham.ten,\n"
//                + "       hoa_don_chi_tiet.so_luong, \n"
//                + "	   hoa_don_chi_tiet.gia  ,\n"
//                + "	   hoa_don_chi_tiet.thanh_tien\n"
//                + "               	from hoa_don_chi_tiet \n"
//                + "                join hoa_don on hoa_don_chi_tiet.id_hoa_don = hoa_don.id_hoa_don\n"
//                + "				join chi_tiet_san_pham on hoa_don_chi_tiet.id_chi_tiet_san_pham = chi_tiet_san_pham.id_chi_tiet_san_pham\n"
//                + "				join san_pham  on chi_tiet_san_pham.id_san_pham = san_pham.id_san_pham\n"
//                + "               \n"
//                + "                where hoa_don_chi_tiet.id_hoa_don =?";
//        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
//        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setObject(1, id);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//
//                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
//                HoaDon hoaDon = new HoaDon();
//                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
//                SanPham sanPham = new SanPham();
//
//                hoaDon.setId_hoa_don(rs.getInt(1));
//                list.setId_hoa_don(hoaDon);
//                chiTietSanPham.setId(rs.getInt(2));
//                list.setId_chi_tiet_san_pham(chiTietSanPham);
//                sanPham.setId(rs.getInt(3));
//                sanPham.setTen(rs.getString(4));
//                list.setId_san_pham(sanPham);
//                list.setSo_luong(rs.getInt(5));
//                list.setGia(rs.getFloat(6));
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
    public ArrayList<HoaDonChiTietViewModel> getListHoaDonChiietByIdGioHang(int id) {
        ArrayList<HoaDonChiTietViewModel> list = new ArrayList<>();
        String sql = "select hoa_don_chi_tiet.id_hoa_don,\n"
                + "		hoa_don_chi_tiet.id_chi_tiet_san_pham, \n"
                + "		san_pham.id_san_pham, "
                + "             san_pham.ten, \n"
                + "             mau_sac.id_mau_sac, "
                + "mau_sac.ten,\n"
                + "		hang.id_hang ,"
                + "hang.ten,\n"
                + "             size.id_size, "
                + "size.ten,  \n"
                + "             hoa_don_chi_tiet.so_luong,"
                + " hoa_don_chi_tiet.gia ,\n"
                + "		hoa_don.trang_thai,\n"
                + "		hoa_don_chi_tiet.thanh_tien\n"
                + "		from hoa_don_chi_tiet \n"
                + "                     join hoa_don on hoa_don_chi_tiet.id_hoa_don = hoa_don.id_hoa_don\n"
                + "			join chi_tiet_san_pham on hoa_don_chi_tiet.id_chi_tiet_san_pham = chi_tiet_san_pham.id_chi_tiet_san_pham\n"
                + "               	join san_pham  on chi_tiet_san_pham.id_san_pham = san_pham.id_san_pham\n"
                + "               	join mau_sac  on chi_tiet_san_pham.id_mau_sac = mau_sac.id_mau_sac\n"
                + "               	join hang  on chi_tiet_san_pham.id_hang = hang.id_hang        \n"
                + "              	join size  on chi_tiet_san_pham.id_size = size.id_size"
                + "                     where hoa_don_chi_tiet.id_hoa_don = ?";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTietViewModel chiTietViewModel = new HoaDonChiTietViewModel();
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                HoaDon hoaDon = new HoaDon();
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                Size size = new Size();

                hoaDon.setId_hoa_don(rs.getInt(1));
                hoaDon.setTrang_thai(rs.getInt(13));
                chiTietViewModel.setId_hoa_don(hoaDon);

                chiTietSanPham.setId(rs.getInt(2));
                chiTietViewModel.setId_chi_tiet_san_pham(chiTietSanPham);

                sanPham.setId(rs.getInt(3));
                sanPham.setTen(rs.getString(4));
                chiTietViewModel.setId_san_pham(sanPham);

                mauSac.setId(rs.getInt(5));
                mauSac.setTen(rs.getString(6));
                chiTietViewModel.setId_mau_sac(mauSac);

                hang.setId(rs.getInt(7));
                hang.setTen(rs.getString(8));
                chiTietViewModel.setId_hang(hang);

                size.setId(rs.getInt(9));
                size.setTen(rs.getString(10));
                chiTietViewModel.setId_size(size);

                chiTietViewModel.setSo_luong(rs.getInt(11));
                chiTietViewModel.setGia(rs.getFloat(12));
                chiTietViewModel.setThanh_tien(rs.getFloat(14));
                list.add(chiTietViewModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
//
//    public static void main(String[] args) {
//        HoaDonChiTietViewModel lists = new HoaDonRepository().loadGioHang(3);
//
//        System.out.println(lists.toString());
//
//    }

    ///loi
    public HoaDonChiTiet getListHoaDonChiietById11(int id) {
        HoaDonChiTiet list = new HoaDonChiTiet();
        String sql = "select hoa_don_chi_tiet.id_hoa_don,\n"
                + "		hoa_don_chi_tiet.id_chi_tiet_san_pham, \n"
                + "		san_pham.id_san_pham, san_pham.ten, \n"
                + "             mau_sac.id_mau_sac, mau_sac.ten,\n"
                + "		hang.id_hang ,hang.ten,\n"
                + "             size.id_size, size.ten,  \n"
                + "             chi_tiet_san_pham.so_luong_ton, chi_tiet_san_pham.gia ,\n"
                + "		hoa_don.trang_thai\n"
                + "		from hoa_don_chi_tiet \n"
                + "                     join hoa_don on hoa_don_chi_tiet.id_hoa_don = hoa_don.id_hoa_don\n"
                + "			join chi_tiet_san_pham on hoa_don_chi_tiet.id_hoa_don = chi_tiet_san_pham.id_chi_tiet_san_pham\n"
                + "               	join san_pham  on chi_tiet_san_pham.id_san_pham = san_pham.id_san_pham\n"
                + "               	join mau_sac  on chi_tiet_san_pham.id_mau_sac = mau_sac.id_mau_sac\n"
                + "               	join hang  on chi_tiet_san_pham.id_hang = hang.id_hang        \n"
                + "              	join size  on chi_tiet_san_pham.id_size = size.id_size"
                + "                     where hoa_don_chi_tiet.id_hoa_don = ?";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                HoaDon hoaDon = new HoaDon();
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                Size size = new Size();

                hoaDon.setId_hoa_don(rs.getInt(1));
                hoaDon.setTrang_thai(rs.getInt(13));
                list.setId_hoa_don(hoaDon);

                chiTietSanPham.setId(rs.getInt(2));
                list.setId_chi_tiet_san_pham(chiTietSanPham);

//                sanPham.setId(rs.getInt(3));
//                sanPham.setTen(rs.getString(4));
//                list.setId_san_pham(sanPham);
//
//                mauSac.setId(rs.getInt(5));
//                mauSac.setTen(rs.getString(6));
//                list.setId_mau_sac(mauSac);
//
//                hang.setId(rs.getInt(7));
//                hang.setTen(rs.getString(8));
//                list.setId_hang(hang);
//
//                size.setId(rs.getInt(9));
//                size.setTen(rs.getString(10));
//                list.setId_size(size);
                list.setSo_luong(rs.getInt(11));
                list.setGia(rs.getFloat(12));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDonChiTiet> getListHoaDonChiietByIdSP(int id) {
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        String sql = "select hoa_don_chi_tiet.id_hoa_don,\n"
                + "		hoa_don_chi_tiet.id_chi_tiet_san_pham, \n"
                //                + "		san_pham.id_san_pham, san_pham.ten, \n"
                //                + "             mau_sac.id_mau_sac, mau_sac.ten,\n"
                //                + "		hang.id_hang ,hang.ten,\n"
                //                + "             size.id_size, size.ten,  \n"
                + "             chi_tiet_san_pham.so_luong_ton, chi_tiet_san_pham.gia ,\n"
                + "		hoa_don.trang_thai\n"
                + "		from hoa_don_chi_tiet \n"
                + "                     join hoa_don on hoa_don_chi_tiet.id_hoa_don = hoa_don.id_hoa_don\n"
                + "			join chi_tiet_san_pham on hoa_don_chi_tiet.id_hoa_don = chi_tiet_san_pham.id_chi_tiet_san_pham\n"
                + "               	join san_pham  on chi_tiet_san_pham.id_san_pham = san_pham.id_san_pham\n"
                + "               	join mau_sac  on chi_tiet_san_pham.id_mau_sac = mau_sac.id_mau_sac\n"
                + "               	join hang  on chi_tiet_san_pham.id_hang = hang.id_hang        \n"
                + "              	join size  on chi_tiet_san_pham.id_size = size.id_size"
                + "                     where hoa_don_chi_tiet.id_hoa_don = ?";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                HoaDon hoaDon = new HoaDon();
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                Size size = new Size();

                hoaDon.setId_hoa_don(rs.getInt(1));
                hoaDon.setTrang_thai(rs.getInt(13));
                hoaDonChiTiet.setId_hoa_don(hoaDon);

                chiTietSanPham.setId(rs.getInt(2));
                hoaDonChiTiet.setId_chi_tiet_san_pham(chiTietSanPham);

//                sanPham.setId(rs.getInt(3));
//                sanPham.setTen(rs.getString(4));
//                list.setId_san_pham(sanPham);
//
//                mauSac.setId(rs.getInt(5));
//                mauSac.setTen(rs.getString(6));
//                list.setId_mau_sac(mauSac);
//
//                hang.setId(rs.getInt(7));
//                hang.setTen(rs.getString(8));
//                list.setId_hang(hang);
//
//                size.setId(rs.getInt(9));
//                size.setTen(rs.getString(10));
//                list.setId_size(size);
                hoaDonChiTiet.setSo_luong(rs.getInt(11));
                hoaDonChiTiet.setGia(rs.getFloat(12));
                list.add(hoaDonChiTiet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDonChiTiet> getListHoaDonChiietByIdSP1(int id) {
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        String sql = "select hoa_don_chi_tiet.id_hoa_don,\n"
                + "		hoa_don_chi_tiet.id_chi_tiet_san_pham, \n"
                //                + "		san_pham.id_san_pham, san_pham.ten, \n"
                //                + "             mau_sac.id_mau_sac, mau_sac.ten,\n"
                //                + "		hang.id_hang ,hang.ten,\n"
                //                + "             size.id_size, size.ten,  \n"
                + "             chi_tiet_san_pham.so_luong_ton, chi_tiet_san_pham.gia ,\n"
                + "		hoa_don.trang_thai\n"
                + "		from hoa_don_chi_tiet \n"
                + "                     join hoa_don on hoa_don_chi_tiet.id_hoa_don = hoa_don.id_hoa_don\n"
                + "			join chi_tiet_san_pham on hoa_don_chi_tiet.id_hoa_don = chi_tiet_san_pham.id_chi_tiet_san_pham\n"
                + "               	join san_pham  on chi_tiet_san_pham.id_san_pham = san_pham.id_san_pham\n"
                + "               	join mau_sac  on chi_tiet_san_pham.id_mau_sac = mau_sac.id_mau_sac\n"
                + "               	join hang  on chi_tiet_san_pham.id_hang = hang.id_hang        \n"
                + "              	join size  on chi_tiet_san_pham.id_size = size.id_size"
                + "                     where hoa_don_chi_tiet.id_hoa_don = ?";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                HoaDon hoaDon = new HoaDon();
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                Size size = new Size();

                hoaDon.setId_hoa_don(rs.getInt(1));
                hoaDon.setTrang_thai(rs.getInt(13));
                hoaDonChiTiet.setId_hoa_don(hoaDon);

                chiTietSanPham.setId(rs.getInt(2));
                hoaDonChiTiet.setId_chi_tiet_san_pham(chiTietSanPham);

//                sanPham.setId(rs.getInt(3));
//                sanPham.setTen(rs.getString(4));
//                list.setId_san_pham(sanPham);
//
//                mauSac.setId(rs.getInt(5));
//                mauSac.setTen(rs.getString(6));
//                list.setId_mau_sac(mauSac);
//
//                hang.setId(rs.getInt(7));
//                hang.setTen(rs.getString(8));
//                list.setId_hang(hang);
//
//                size.setId(rs.getInt(9));
//                size.setTen(rs.getString(10));
//                list.setId_size(size);
                hoaDonChiTiet.setSo_luong(rs.getInt(11));
                hoaDonChiTiet.setGia(rs.getFloat(12));
                list.add(hoaDonChiTiet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static HoaDon getByHoaDonId(int id) {
        HoaDon list = new HoaDon();
        String sql = "select hoa_don.id_hoa_don,"
                + "hoa_don.created_at,"
                + "hoa_don.ngay_thanh_toan,"
                + "khach_hang.ho_ten,"
                + "hoa_don.id_khach_hang, "
                + "hoa_don.tong_tien,"
                + "hoa_don.hinh_thuc_thanh_toan,"
                + "hoa_don.trang_thai,"
                + "                nhan_vien.ho_ten, \n"
                + "		   hoa_don.id_nhan_vien, \n"
                + "                vourcher.[%_khuyen_mai] ,\n"
                + "                hoa_don.id_voucher \n"
                + " from hoa_don "
                + "join khach_hang on hoa_don.id_khach_hang = khach_hang.id_khach_hang \n"
                + "join nhan_vien on hoa_don.id_nhan_vien = nhan_vien.id_nhan_vien \n"
                + "join vourcher on hoa_don.id_voucher = vourcher.id_voucher "
                + "where hoa_don.id_hoa_don = ?";

        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                KhachHang khachHang = new KhachHang();
                NhanVien nhanVien = new NhanVien();

                list.setId_hoa_don(rs.getInt(1));
                list.setCreated_at(rs.getDate(2));
                list.setNgay_thanh_toan(rs.getDate(3));

                khachHang.setHo_ten(rs.getString(4));
                khachHang.setId_khach_hang(rs.getInt(5));
                list.setId_khach_hang(khachHang);

                list.setId_khach_hang(khachHang);
                list.setTong_tien(rs.getFloat(6));
                list.setHinh_thuc_thanh_toan(rs.getString(7));
                list.setTrang_thai(rs.getInt(8));

                nhanVien.setHo_ten(rs.getString(9));
                nhanVien.setId_nhan_vien(rs.getInt(10));
                list.setId_nhan_vien(nhanVien);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public HoaDon getByHoaDonTenKhachHang(String ten) {
        HoaDon list = new HoaDon();
        String sql = "select hoa_don.id_hoa_don,"
                + "hoa_don.created_at,"
                + "hoa_don.ngay_thanh_toan,"
                + "khach_hang.ho_ten,"
                + "hoa_don.id_khach_hang, "
                + "hoa_don.tong_tien,"
                + "hoa_don.hinh_thuc_thanh_toan,"
                + "hoa_don.trang_thai,"
                + "                nhan_vien.ho_ten, \n"
                + "		   hoa_don.id_nhan_vien \n"
                //                + "                vourcher.[%_khuyen_mai] ,\n"
                //                + "                hoa_don.id_voucher \n"
                + " from hoa_don "
                + "join khach_hang on hoa_don.id_khach_hang = khach_hang.id_khach_hang \n"
                + "join nhan_vien on hoa_don.id_nhan_vien = nhan_vien.id_nhan_vien \n"
                //                + "join vourcher on hoa_don.id_voucher = vourcher.id_voucher "
                + "where khach_hang.ho_ten = ?";

        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                KhachHang khachHang = new KhachHang();
                NhanVien nhanVien = new NhanVien();

                list.setId_hoa_don(rs.getInt(1));
                list.setCreated_at(rs.getDate(2));
                list.setNgay_thanh_toan(rs.getDate(3));

                khachHang.setHo_ten(rs.getString(4));
                khachHang.setId_khach_hang(rs.getInt(5));
                list.setId_khach_hang(khachHang);

                list.setId_khach_hang(khachHang);
                list.setTong_tien(rs.getFloat(6));
                list.setHinh_thuc_thanh_toan(rs.getString(7));
                list.setTrang_thai(rs.getInt(8));

                nhanVien.setHo_ten(rs.getString(9));
                nhanVien.setId_nhan_vien(rs.getInt(10));
                list.setId_nhan_vien(nhanVien);

//                vourcher.setKhuyen_mai(rs.getInt(10));
//                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public HoaDon getByHoaDonTenKhachHang1(String ten) {
        HoaDon list = new HoaDon();
        String sql = "select id_khach_hang, ho_ten from khach_hang where khach_hang.ho_ten = ?";

        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                KhachHang khachHang = new KhachHang();
                khachHang.setHo_ten(rs.getString(2));
                khachHang.setId_khach_hang(rs.getInt(1));
                list.setId_khach_hang(khachHang);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static HoaDon getByHoaDonNhanVien(int id) {
        HoaDon list = new HoaDon();
        String sql = "select hoa_don.id_hoa_don,"
                + "hoa_don.created_at,"
                + "hoa_don.ngay_thanh_toan,"
                + "khach_hang.ho_ten,"
                + "hoa_don.id_khach_hang, "
                + "hoa_don.tong_tien,"
                + "hoa_don.hinh_thuc_thanh_toan,"
                + "hoa_don.trang_thai,"
                + "                nhan_vien.ho_ten, \n"
                + "		   hoa_don.id_nhan_vien \n"
                //                + "                vourcher.[%_khuyen_mai] ,\n"
                //                + "                hoa_don.id_voucher \n"
                + " from hoa_don "
                + "join khach_hang on hoa_don.id_khach_hang = khach_hang.id_khach_hang \n"
                + "join nhan_vien on hoa_don.id_nhan_vien = nhan_vien.id_nhan_vien \n"
                //                + "join vourcher on hoa_don.id_voucher = vourcher.id_voucher "
                + "where nhan_vien.id_nhan_vien = ?";

        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                KhachHang khachHang = new KhachHang();
                NhanVien nhanVien = new NhanVien();

                list.setId_hoa_don(rs.getInt(1));
                list.setCreated_at(rs.getDate(2));
                list.setNgay_thanh_toan(rs.getDate(3));

                khachHang.setHo_ten(rs.getString(4));
                khachHang.setId_khach_hang(rs.getInt(5));
                list.setId_khach_hang(khachHang);

                list.setId_khach_hang(khachHang);
                list.setTong_tien(rs.getFloat(6));
                list.setHinh_thuc_thanh_toan(rs.getString(7));
                list.setTrang_thai(rs.getInt(8));

                nhanVien.setHo_ten(rs.getString(9));
                nhanVien.setId_nhan_vien(rs.getInt(10));
                list.setId_nhan_vien(nhanVien);

//                vourcher.setKhuyen_mai(rs.getInt(10));
//                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HoaDon> getByHoaDonALL(String tim) {
        List<HoaDon> hoaDons = new ArrayList<>();
//        float so = 0;
//        if (tim.isEmpty()) {
//            so = 0;
//        } else if (tim.chars().allMatch(Character::isDigit)) {
//            so = Float.parseFloat(tim);
//        }
        String sql = "select hoa_don.id_hoa_don,"
                + "hoa_don.created_at,"
                + "hoa_don.ngay_thanh_toan,"
                + "khach_hang.ho_ten,"
                + "hoa_don.id_khach_hang, "
                + "hoa_don.tong_tien,"
                + "hoa_don.hinh_thuc_thanh_toan,"
                + "hoa_don.trang_thai,"
                + "                nhan_vien.ho_ten, \n"
                + "		   hoa_don.id_nhan_vien, \n"
                + "                vourcher.[%_khuyen_mai] ,\n"
                + "                hoa_don.id_voucher \n"
                + " from hoa_don "
                + "join khach_hang on hoa_don.id_khach_hang = khach_hang.id_khach_hang \n"
                + "                join nhan_vien on hoa_don.id_nhan_vien = nhan_vien.id_nhan_vien \n"
                + "                join vourcher on hoa_don.id_voucher = vourcher.id_voucher\n"
                + "				where hoa_don.created_at like '?' \n"
                + "				or hoa_don.ngay_thanh_toan like '?'\n"
                + "				or khach_hang.ho_ten like N'cuong' \n"
                + "				or hoa_don.tong_tien = ? \n"
                + "				or hoa_don.hinh_thuc_thanh_toan like N'?'\n"
                + "				or hoa_don.trang_thai = ?\n"
                + "				or hoa_don.id_hoa_don = ? \n"
                + "				or nhan_vien.ho_ten  like N'?'";

        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tim);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon list = new HoaDon();
                KhachHang khachHang = new KhachHang();
                NhanVien nhanVien = new NhanVien();

                list.setId_hoa_don(rs.getInt(1));
                list.setCreated_at(rs.getDate(2));
                list.setNgay_thanh_toan(rs.getDate(3));

                khachHang.setHo_ten(rs.getString(4));
                khachHang.setId_khach_hang(rs.getInt(5));
                list.setId_khach_hang(khachHang);

                list.setId_khach_hang(khachHang);
                list.setTong_tien(rs.getFloat(6));
                list.setHinh_thuc_thanh_toan(rs.getString(7));
                list.setTrang_thai(rs.getInt(8));

                nhanVien.setHo_ten(rs.getString(9));
                nhanVien.setId_nhan_vien(rs.getInt(10));
                list.setId_nhan_vien(nhanVien);

                hoaDons.add(list);

//                vourcher.setKhuyen_mai(rs.getInt(10));
//                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDons;
    }

    public static void main(String[] args) {
        List<HoaDonChiTiet> lists = new HoaDonRepository().getListHoaDonChiTietThanhToan();
        for (HoaDonChiTiet x : lists) {
            System.out.println(x.toString());
        }
    }

    public HoaDonChiTiet inserthoadonct(HoaDonChiTiet hdct) {
        String sql = "INSERT INTO [dbo].[hoa_don_chi_tiet]\n"
                + "           (\n"
                + "           [id_hoa_don],[id_chi_tiet_san_pham]\n"
                + "           ,[so_luong]\n"
                + "           ,[gia]\n"
                + "           ,[thanh_tien]\n"
                + "           ,[created_at]\n"
                + "         )\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,getdate())";
        JDBCHelper.executeUpdate(sql,
                //                hdct.getIdHD().getIdHD(),
                //                hdct.getIdCTSP().getIdCTSP(),
                //                hdct.getSoLuong(),
                //                hdct.getDonGia()

                hdct.getId_hoa_don().getId_hoa_don(),
                hdct.getId_chi_tiet_san_pham().getId(),
                hdct.getSo_luong(),
                hdct.getGia(),
                hdct.getThanh_tien()
        );
        return hdct;
    }

    public HoaDonChiTiet updateHDCT(HoaDonChiTiet hdct) {
        String sql = "update [hoa_don_chi_tiet] set [so_luong]=? where [id_chi_tiet_san_pham] =? and [id_hoa_don]=?";
        JDBCHelper.executeUpdate(sql,
                hdct.getSo_luong(),
                hdct.getGia(),
                hdct.getId_chi_tiet_san_pham().getId(),
                hdct.getId_hoa_don().getId_hoa_don()
        );
        return hdct;
    }

    public HoaDon updatehoadon_thanhtoan1(HoaDon hd) {
        String sql = "UPDATE  dbo.hoa_don SET   [tong_tien] = ? ,[trang_thai] = 1 , [ngay_thanh_toan] = GETDATE(),[hinh_thuc_thanh_toan] = ?  WHERE hoa_don.id_hoa_don = ?";
        JDBCHelper.executeUpdate(sql,
                hd.getTong_tien(),
                //                hd.getIdKM().getIdKM(),
                hd.getHinh_thuc_thanh_toan(),
                //                hd.getGhiChu(),
                hd.getId_hoa_don(),
                hd.getId_nhan_vien().getId_nhan_vien(),
                hd.getId_khach_hang().getId_khach_hang()
        );
        return hd;
    }

    public HoaDon updatehoadon_thanhtoan(HoaDon hd) {
        String sql = "UPDATE  dbo.hoa_don SET  id_nhan_vien = ?, id_khach_hang = ?, [tong_tien] = ? ,[trang_thai] = 1 , [ngay_thanh_toan] = GETDATE(),[hinh_thuc_thanh_toan] = ?  WHERE hoa_don.id_hoa_don = ?";
        JDBCHelper.executeUpdate(sql,
                hd.getId_nhan_vien().getId_nhan_vien(),
                hd.getId_khach_hang().getId_khach_hang(),
                hd.getTong_tien(),
                //                hd.getIdKM().getIdKM(),
                hd.getHinh_thuc_thanh_toan(),
                //                hd.getGhiChu(),
                hd.getId_hoa_don()
        );
        return hd;
    }

    public HoaDonViewModel updatehoadon_thanhtoan2(HoaDonViewModel hd) {
        String sql = "UPDATE  dbo.hoa_don SET id_khach_hang = ? WHERE hoa_don.id_hoa_don = ?";
        JDBCHelper.executeUpdate(sql,
                //                hd.getId_nhan_vien().getId_nhan_vien(),
                hd.getId_khach_hang().getId_khach_hang(),
                //                hd.getTong_tien(),
                //                hd.getIdKM().getIdKM(),
                //                hd.getHinh_thuc_thanh_toan(),
                //                hd.getGhiChu(),
                hd.getId_hoa_don()
        );
        return hd;
    }

    public HoaDon updatehoadon_huy(HoaDon hd) {
        String sql = "UPDATE  dbo.hoa_don SET [trang_thai] = 0 , [ngay_thanh_toan] = GETDATE( )WHERE hoa_don.id_hoa_don = ?";
        JDBCHelper.executeUpdate(sql,
                //                hd.getTong_tien(),
                //                hd.getIdKM().getIdKM(),
                //                hd.getHinh_thuc_thanh_toan(),
                //                hd.getGhiChu(),
                hd.getId_hoa_don()
        );
        return hd;
    }

    public Boolean add(HoaDonChiTiet s) {
        String sql = "INSERT INTO [dbo].[hoa_don_chi_tiet]\n"
                + "           (\n"
                + "           [id_hoa_don]"
                + "           ,[id_chi_tiet_san_pham]\n"
                + "           ,[so_luong]\n"
                + "           ,[gia]\n"
                + "           ,[thanh_tien]\n"
                //                + "           ,[created_at]\n"
                + "         )\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(2, s.getId_chi_tiet_san_pham().getId());
            ps.setInt(1, s.getId_hoa_don().getId_hoa_don());
            ps.setInt(3, s.getSo_luong());
            ps.setFloat(4, s.getGia());
            ps.setFloat(5, s.getThanh_tien());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean addHoaDonMoi(HoaDon s) {
        String sql = "INSERT INTO [dbo].[hoa_don] ([created_at] ,[trang_thai], id_khach_hang, id_nhan_vien ) VALUES  (getDate(),?,6,1)";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

//            ps.setInt(3, s.getId_nhan_vien().getId_nhan_vien());
//            ps.setInt(2, s.getId_khach_hang().getId_khach_hang());
//            ps.setString(3, s.getSdt());
//            ps.setString(3, s.getHinh_thuc_thanh_toan());
            ps.setFloat(1, s.getTrang_thai());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public HoaDon getByHoaDonSDTKhachHang(String sdt) {
        HoaDon list = new HoaDon();
        String sql = "select hoa_don.id_hoa_don,"
                + "hoa_don.created_at,"
                + "hoa_don.ngay_thanh_toan,"
                + "khach_hang.ho_ten,"
                + "hoa_don.id_khach_hang, "
                + "hoa_don.tong_tien,"
                + "hoa_don.hinh_thuc_thanh_toan,"
                + "hoa_don.trang_thai,"
                + "                nhan_vien.ho_ten, \n"
                + "		   hoa_don.id_nhan_vien, \n"
                + "                vourcher.[%_khuyen_mai] ,\n"
                + "                hoa_don.id_voucher \n"
                + " from hoa_don "
                + "join khach_hang on hoa_don.id_khach_hang = khach_hang.id_khach_hang \n"
                + "join nhan_vien on hoa_don.id_nhan_vien = nhan_vien.id_nhan_vien \n"
                + "join vourcher on hoa_don.id_voucher = vourcher.id_voucher "
                + "where khach_hang.sdt = ?";

        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, sdt);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                KhachHang khachHang = new KhachHang();
                NhanVien nhanVien = new NhanVien();

                list.setId_hoa_don(rs.getInt(1));
                list.setCreated_at(rs.getDate(2));
                list.setNgay_thanh_toan(rs.getDate(3));

                khachHang.setHo_ten(rs.getString(4));
                khachHang.setId_khach_hang(rs.getInt(5));
                list.setId_khach_hang(khachHang);

                list.setId_khach_hang(khachHang);
                list.setTong_tien(rs.getFloat(6));
                list.setHinh_thuc_thanh_toan(rs.getString(7));
                list.setTrang_thai(rs.getInt(8));

                nhanVien.setHo_ten(rs.getString(9));
                nhanVien.setId_nhan_vien(rs.getInt(10));
                list.setId_nhan_vien(nhanVien);

//                vourcher.setKhuyen_mai(rs.getInt(10));
//                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateSoLuongSp(String id, int soLuong) {
        int check = 0;
        String query = "UPDATE [dbo].[chi_tiet_san_pham] SET [so_luong_ton] = ? WHERE id= ? ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, soLuong);
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean updateHoaDon(int id, HoaDon hoaDon) {
        int check = 0;
        String query = "UPDATE  dbo.hoa_don SET [tong_tien] = ? ,[trang_thai] = 1 , [ngay_thanh_toan] = GETDATE(),[hinh_thuc_thanh_toan] = ? WHERE hoa_don.id_hoa_don = ?";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, hoaDon.getTong_tien());
            ps.setObject(2, hoaDon.getHinh_thuc_thanh_toan());
            ps.setObject(3, id);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public ChiTietSanPham updatehoadon_thanhtoan_soluong(ChiTietSanPham hd) {
        String sql = "update chi_tiet_san_pham set so_luong_ton = so_luong_ton - ? Where id_chi_tiet_san_pham = ?";
        JDBCHelper.executeUpdate(sql,
                hd.getSoLuong(),
                hd.getId()
        );
        return hd;
    }

//    public ChiTietSanPham update_ThanhToan(ChiTietSanPham ctsp) {
//        String sql = "update chi_tiet_san_pham set so_luong_ton = so_luong_ton - ? Where id_chi_tiet_san_pham = ?";
//        JDBCHelper.executeUpdate(sql, ctsp.getSoLuong(), ctsp.getId());
//        return ctsp;
//    }
//     public ChiTietSanPham test(String tenSP) {
//        String sql = "SELECT a.id_chi_tiet_san_pham\n"
//                + "FROM hoa_don_chi_tiet a\n"
//                + "JOIN hoa_don e on a.id_hoa_don = e.id_hoa_don\n"
//                + "JOIN chi_tiet_san_pham b ON a.id_chi_tiet_san_pham = b.id_chi_tiet_san_pham\n"
//                + "JOIN san_pham c ON b.id_san_pham = c.id_san_pham\n"
//                + "WHERE a.id_hoa_don = ? AND c.ten = ?;";
//        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setObject(1, tenSanPham);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//
//            }
//        } catch (Exception e) {
//            e.getMessage();
//        }
//    }
    public ChiTietSanPham getIDCTSP(String tenSanPham, int idHoaDon) {
        String sql = "SELECT a.id_chi_tiet_san_pham \n"
                + "FROM hoa_don_chi_tiet a\n"
                + "JOIN hoa_don e on a.id_hoa_don = e.id_hoa_don\n"
                + "JOIN chi_tiet_san_pham b ON a.id_chi_tiet_san_pham = b.id_chi_tiet_san_pham\n"
                + "JOIN san_pham c ON b.id_san_pham = c.id_san_pham\n"
                + "WHERE a.id_hoa_don = ? AND c.ten = ?;";

        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHoaDon);
            ps.setString(2, tenSanPham);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Lấy giá trị từ ResultSet và trả về đối tượng ChiTietSanPham
                int idChiTietSanPham = rs.getInt("id_chi_tiet_san_pham");

                return new ChiTietSanPham(idChiTietSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra console để theo dõi vấn đề
        }

        return null; // Trả về null nếu không tìm thấy kết quả
    }
    
    public int deleteHoaDonChiTietByQuery(int idHoaDon, String tenSanPham) {
    String sql = "DELETE FROM hoa_don_chi_tiet "
            + "WHERE id_hoa_don = ? "
            + "AND id_chi_tiet_san_pham IN ("
            + "    SELECT a.id_chi_tiet_san_pham "
            + "    FROM hoa_don_chi_tiet a "
            + "    JOIN chi_tiet_san_pham b ON a.id_chi_tiet_san_pham = b.id_chi_tiet_san_pham "
            + "    JOIN san_pham c ON b.id_san_pham = c.id_san_pham "
            + "    WHERE a.id_hoa_don = ? AND c.ten = ? "
//            + "    LIMIT 1"  // Chỉ giữ lại một chi tiết sản phẩm
            + ");";

    try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idHoaDon);
        ps.setInt(2, idHoaDon);
        ps.setString(3, tenSanPham);

        // Thực hiện câu truy vấn xóa và trả về số hàng bị ảnh hưởng
        return ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace(); // In lỗi ra console để theo dõi vấn đề
    }

    return 0; // Trả về 0 nếu có lỗi
}


}
