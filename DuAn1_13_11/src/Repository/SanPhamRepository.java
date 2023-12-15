/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.DangAo;
import DomainModel.SanPham;
import Utilities.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SanPhamRepository {

    private DBConnection connection;

    public ArrayList<SanPham> getListSanPham() {
        ArrayList<SanPham> list = new ArrayList<>();
        String sql = "select id_san_pham, ma, ten from san_pham \n"
                + "ORDER BY id_san_pham DESC;";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setId(rs.getInt(1));
                sanPham.setMa(rs.getString(2));
                sanPham.setTen(rs.getString(3));
                list.add(sanPham);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public SanPham getIdByTenSanPham(String ten) {
        SanPham id = null;
        String sql = "SELECT id_san_pham FROM san_pham WHERE ten = ?";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ten);  // Sử dụng tham số 'ten' được cung cấp
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = new SanPham();
                id.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            e.getMessage();  // Xử lý ngoại lệ một cách thích hợp, có thể ghi log nó
        }
        return id;
    }

    public SanPham getSanPhamById(int id) {
        SanPham sanPham = null;
        String sql = "select id_san_pham, ma, ten from san_pham where id_san_pham = ?";

        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                sanPham = new SanPham();
                sanPham.setId(rs.getInt(1));
                sanPham.setMa(rs.getString(2));
                sanPham.setTen(rs.getString(3));
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return sanPham;
    }

    public Boolean add(SanPham sanPham) {
        String sql = "insert into san_pham(ma, ten) values (?, ?)";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, sanPham.getMa());
            ps.setObject(2, sanPham.getTen());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

//    public Boolean update(Integer id, SanPham sanPham) {
//        String sql = "update san_Pham set ma = ?, ten = ?  where id_san_pham = ?";
//        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setObject(1, sanPham.getMa());
//            ps.setObject(2, sanPham.getTen());
//            ps.setObject(3, sanPham.getId());
//            ps.executeUpdate();
//            return true;
//        } catch (Exception e) {
//
//        }
//        return false;
//    }
    public Boolean update(SanPham sanPham) {
        String sql = "update san_pham set ma = ?, ten = ?  where id_san_pham = ?";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, sanPham.getMa());
            ps.setObject(2, sanPham.getTen());
            ps.setObject(3, sanPham.getId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng được cập nhật
        } catch (Exception e) {
            return false;
        }

    }

    public Boolean delete(Integer id) {
        String sql = "delete from san_pham where id_san_pham = ?";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public ArrayList<SanPham> searchByName(String ten){
        ArrayList<SanPham> list = new ArrayList<>();
        String sql = "select id_san_pham, ma, ten from san_pham where ten like N'%" +ten+ "%'"
                + "order by id_san_pham desc";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setId(rs.getInt(1));
                sanPham.setMa(rs.getString(2));
                sanPham.setTen(rs.getString(3));
                list.add(sanPham);
            }
        } catch (Exception e) {

        }
        return list;
    }
    
    
    public static void main(String[] args) {
        SanPham list = new SanPhamRepository().getSanPhamById(1);

        System.out.println(list.toString());

    }
}
