/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.DangAo;
import DomainModel.Hang;
import Utilities.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class HangRepository {
    private DBConnection connection;

    public ArrayList<Hang> getListHang() {
        ArrayList<Hang> list = new ArrayList<>();
        String sql = "select id_hang, ma, ten from hang"
                + " order by id_hang desc";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Hang hang = new Hang();
                hang.setId(rs.getInt(1));
                hang.setMa(rs.getString(2));
                hang.setTen(rs.getString(3));
                list.add(hang);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public Hang getHangById(int id) {
    Hang hang = null;
    String sql = "select id_hang, ma, ten from hang where id_hang = ?";
    
    try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            hang = new Hang();
            hang.setId(rs.getInt(1));
            hang.setMa(rs.getString(2));
            hang.setTen(rs.getString(3));
        }
    } catch (Exception e) {
       e.getMessage();
    }
    
    return hang;
}

    
    public Boolean add(Hang hang) {
        String sql = "insert into hang(ma, ten) values (?, ?)";
        try ( Connection con = connection.getConnection();  
               PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hang.getMa());
            ps.setObject(2, hang.getTen());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }
    
    public Boolean update(Integer id, Hang hang){
        String sql = "update hang set ma = ?, ten = ?  where id_hang = ?";
        try ( Connection con = connection.getConnection();  
               PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hang.getMa());
            ps.setObject(2, hang.getTen());
            ps.setObject(3, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public Boolean delete(Integer id){
        String sql = "delete from hang where id_hang = ?";
        try ( Connection con = connection.getConnection();  
               PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }
    public Hang getIdByTenHang(String ten) {
    Hang id = null;
    String sql = "SELECT id_hang FROM hang WHERE ten = ?";
    try (Connection con = connection.getConnection();  
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, ten);  // Sử dụng tham số 'ten' được cung cấp
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            id = new Hang();
            id.setId(rs.getInt(1));
        }
    } catch (Exception e) {
        e.getMessage();  // Xử lý ngoại lệ một cách thích hợp, có thể ghi log nó
    }
    return id;
}
    public static void main(String[] args) {
        Hang list = new HangRepository().getHangById(1);
        
            System.out.println(list.toString());
        
    }
    
}
