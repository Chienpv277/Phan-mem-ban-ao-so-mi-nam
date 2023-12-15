/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DomainModel.*;
import Utilities.DBConnection;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Asus
 */
public class KhachHangService_cuong {

    public ArrayList<KhachHang1> getallkhachhang() {
        ArrayList<KhachHang1> lst = new ArrayList<>();
        String sql = "select * from khach_hang";
        Connection cn = DBConnection.getConnection();

        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                KhachHang1 kh = new KhachHang1();
                kh.setId(rs.getInt("id_khach_hang"));
                kh.setMa(rs.getString("ma"));
                kh.setTen(rs.getString("ho_ten"));
                kh.setGioitinh(rs.getBoolean("gioi_tinh"));
                kh.setNgaysinh(rs.getString("ngay_sinh"));
                kh.setSdt(rs.getString("sdt"));
                kh.setDiachi(rs.getString("dia_chi"));
                kh.setEmail(rs.getString("email"));
                lst.add(kh);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lst;
    }
    public ArrayList<KhachHang1> getallkhachhang1() {
        ArrayList<KhachHang1> lst = new ArrayList<>();
        String sql = "select id_khach_hang from khach_hang";
        Connection cn = DBConnection.getConnection();

        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                KhachHang1 kh = new KhachHang1();
                kh.setId(rs.getInt("id_khach_hang"));
//                kh.setMa(rs.getString("ma"));
//                kh.setTen(rs.getString("ho_ten"));
//                kh.setGioitinh(rs.getBoolean("gioi_tinh"));
//                kh.setNgaysinh(rs.getString("ngay_sinh"));
//                kh.setSdt(rs.getString("sdt"));
//                kh.setDiachi(rs.getString("dia_chi"));
//                kh.setEmail(rs.getString("email"));
                lst.add(kh);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lst;
    }
    
    public Integer addkhachhang(KhachHang1 k) {
        Integer row = null;
        String sql = "insert into khach_hang(ma,ho_ten,gioi_tinh,ngay_sinh,sdt,dia_chi,email)\n"
                + "values(?,?,?,?,?,?,?)";
        Connection cn = DBConnection.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);

            pstm.setString(1, k.getMa());
            pstm.setString(2, k.getTen());
            pstm.setBoolean(3, k.isGioitinh());
            pstm.setString(4, k.getNgaysinh());
            pstm.setString(5, k.getSdt());
            pstm.setString(6, k.getDiachi());
            pstm.setString(7, k.getEmail());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return row;
    }

    public Integer suakhachhang(KhachHang1 k) {
        Integer row = null;
        String sql = "update khach_hang\n"
                + "set ho_ten=?,gioi_tinh=?,ngay_sinh=?,sdt=?,dia_chi=?,email=?\n"
                + "where ma like ?";
        Connection cn = DBConnection.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);

            pstm.setString(7, k.getMa());
            pstm.setString(1, k.getTen());
            pstm.setBoolean(2, k.isGioitinh());
            pstm.setString(3, k.getNgaysinh());
            pstm.setString(4, k.getSdt());
            pstm.setString(5, k.getDiachi());
            pstm.setString(6, k.getEmail());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return row;
    }

    public Integer deletekhachhang(String ma) {
        Integer row = null;

        String sql = "delete from khach_hang \n"
                + "where ma = ?";
        Connection cn = DBConnection.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, ma);
            row = pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return row;

    }
}
