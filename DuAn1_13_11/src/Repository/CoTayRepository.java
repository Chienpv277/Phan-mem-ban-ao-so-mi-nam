/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.ChatLieu;
import DomainModel.CoTay;
import Utilities.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class CoTayRepository {
    private DBConnection connection;

    public ArrayList<CoTay> getListCoTay() {
        ArrayList<CoTay> list = new ArrayList<>();
        String sql = "select id_co_tay, ma, ten from co_tay"
                + " order by id_co_tay desc";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CoTay coTay = new CoTay();
                coTay.setId(rs.getInt(1));
                coTay.setMa(rs.getString(2));
                coTay.setTen(rs.getString(3));
                list.add(coTay);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public CoTay getCoTayById(int id) {
    CoTay coTay = null;
    String sql = "select id_co_tay, ma, ten from chat_lieu where id_co_tay = ?";
    
    try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            coTay = new CoTay();
            coTay.setId(rs.getInt(1));
            coTay.setMa(rs.getString(2));
            coTay.setTen(rs.getString(3));
        }
    } catch (Exception e) {
       e.getMessage();
    }
    
    return coTay;
}

    
    public Boolean add(CoTay coTay) {
        String sql = "insert into co_tay(ma, ten) values (?, ?)";
        try ( Connection con = connection.getConnection();  
               PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, coTay.getMa());
            ps.setObject(2, coTay.getTen());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }
    
    public Boolean update(Integer id, CoTay coTay){
        String sql = "update co_tay set ma = ?, ten = ?  where id_co_tay = ?";
        try ( Connection con = connection.getConnection();  
               PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, coTay.getMa());
            ps.setObject(2, coTay.getTen());
            ps.setObject(3, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public Boolean delete(Integer id){
        String sql = "delete from co_tay where id_co_tay = ?";
        try ( Connection con = connection.getConnection();  
               PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }
    public CoTay getIdByTencoTay(String ten) {
    CoTay coTay = null;
    String sql = "SELECT id_co_tay FROM co_tay WHERE ten = ?";
    try (Connection con = connection.getConnection();  
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, ten);  // Sử dụng tham số 'ten' được cung cấp
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            coTay = new CoTay();
            coTay.setId(rs.getInt(1));
        }
    } catch (Exception e) {
        e.getMessage();  // Xử lý ngoại lệ một cách thích hợp, có thể ghi log nó
    }
    return coTay;
}
    public static void main(String[] args) {
        CoTay list = new CoTayRepository().getCoTayById(1);
        
            System.out.println(list.toString());
        
    }
}
