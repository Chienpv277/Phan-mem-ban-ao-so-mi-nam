/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.KhachHang;
import Utilities.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public class KhachHangRepository {

    private DBConnection connection;

    public ArrayList<KhachHang> getListKhachHang() {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = "SELECT [id_khach_hang]\n"
                + "      ,[ma]\n"
                + "      ,[ho_ten]\n"
                + "      ,[sdt]\n"
                + "      ,[email]\n"
                + "      ,[ngay_sinh]\n"
                + "      ,[gioi_tinh]\n"
                + "      ,[dia_chi]\n"
                + "  FROM [dbo].[khach_hang]"
                + " order by id_khach_hang desc";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setId_khach_hang(rs.getInt(1));
                khachHang.setMa(rs.getString(2));
                khachHang.setHo_ten(rs.getString(3));
                khachHang.setSdt(rs.getString(4));
                khachHang.setEmail(rs.getString(5));
                khachHang.setNgay_sinh(rs.getString(6));
                khachHang.setGioi_tinh(rs.getString(7));
                khachHang.setDia_chi(rs.getString(8));
                list.add(khachHang);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public Boolean add(KhachHang khachHang) {
        String sql = "INSERT INTO [dbo].[khach_hang]\n"
                + "           ([ma]\n"
                + "             ,[ho_ten]\n"
                + "             ,[sdt]\n"
                + "             ,[email]\n"
                + "             ,[ngay_sinh]\n"
                + "             ,[gioi_tinh]\n"
                + "             ,[dia_chi])\n"
                + "     VALUES (?,?,?,?,?,?,?)";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, khachHang.getMa());
            ps.setObject(2, khachHang.getHo_ten());
            ps.setObject(3, khachHang.getSdt());
            ps.setObject(4, khachHang.getEmail());
            ps.setObject(5, khachHang.getNgay_sinh());
            ps.setObject(6, khachHang.getGioi_tinh());
            ps.setObject(7, khachHang.getDia_chi());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }
//

    public Boolean update(Integer id, KhachHang khachHang) {
        String sql = "update khach_hang set ma = ?,"
                + " ho_ten = ?, sdt = ?, email= ?, dia_chi = ?\n"
                + "where id_khach_hang = ?";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, khachHang.getMa());
            ps.setObject(2, khachHang.getHo_ten());
            ps.setObject(3, khachHang.getSdt());
            ps.setObject(4, khachHang.getEmail());
            ps.setObject(5, khachHang.getDia_chi());
            ps.setObject(6, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public Boolean delete(Integer id) {
        String sql = "delete from khach_hang where id_khach_hang = ?";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public ArrayList<KhachHang> searchByTen(String ten) {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = "SELECT [id_khach_hang]\n"
                + "      ,[ma]\n"
                + "      ,[ho_ten]\n"
                + "      ,[sdt]\n"
                + "      ,[email]\n"
                + "      ,[dia_chi]\n"
                + "  FROM [dbo].[khach_hang] where ho_ten like N'%" + ten + "%' \n"
                + " order by id_khach_hang desc";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setId_khach_hang(rs.getInt(1));
                khachHang.setMa(rs.getString(2));
                khachHang.setHo_ten(rs.getString(3));
                khachHang.setSdt(rs.getString(4));
                khachHang.setDia_chi(rs.getString(6));
                khachHang.setEmail(rs.getString(5));
                list.add(khachHang);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public ArrayList<KhachHang> searchBySdt(String sdt) {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = "SELECT [id_khach_hang]\n"
                + "      ,[ma]\n"
                + "      ,[ho_ten]\n"
                + "      ,[sdt]\n"
                + "      ,[email]\n"
                + "      ,[dia_chi]\n"
                + "  FROM [dbo].[khach_hang] where sdt like N'%" + sdt + "%' \n"
                + " order by id_khach_hang desc";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setId_khach_hang(rs.getInt(1));
                khachHang.setMa(rs.getString(2));
                khachHang.setHo_ten(rs.getString(3));
                khachHang.setSdt(rs.getString(4));
                khachHang.setDia_chi(rs.getString(6));
                khachHang.setEmail(rs.getString(5));
                list.add(khachHang);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public ArrayList<KhachHang> searchByEmail(String email) {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = "SELECT [id_khach_hang]\n"
                + "      ,[ma]\n"
                + "      ,[ho_ten]\n"
                + "      ,[sdt]\n"
                + "      ,[email]\n"
                + "      ,[dia_chi]\n"
                + "  FROM [dbo].[khach_hang] where email like N'%" + email + "%' \n"
                + " order by id_khach_hang desc";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setId_khach_hang(rs.getInt(1));
                khachHang.setMa(rs.getString(2));
                khachHang.setHo_ten(rs.getString(3));
                khachHang.setSdt(rs.getString(4));
                khachHang.setDia_chi(rs.getString(6));
                khachHang.setEmail(rs.getString(5));
                list.add(khachHang);
            }
        } catch (Exception e) {

        }
        return list;
    }
//    public KhachHang getIdByTenKhachHang(String ten) {
//        KhachHang khachHang = null;
//        String sql = "SELECT id_chat_lieu FROM chat_lieu WHERE ten = ?";
//        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setString(1, ten);  // Sử dụng tham số 'ten' được cung cấp
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                khachHang = new KhachHang();
//                khachHang.setId(rs.getInt(1));
//            }
//        } catch (Exception e) {
//            e.getMessage();  // Xử lý ngoại lệ một cách thích hợp, có thể ghi log nó
//        }
//        return khachHang;
//    }

    public static void main(String[] args) {
        ArrayList<KhachHang> khachHangs = new KhachHangRepository().getListKhachHang();
        for (KhachHang x : khachHangs) {
            System.out.println(x.toString());
        }
    }
}
