/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.ChatLieu;
import DomainModel.DangAo;
import Utilities.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class DangAoRepository {
    private DBConnection connection;

    public ArrayList<DangAo> getListDangAo() {
        ArrayList<DangAo> list = new ArrayList<>();
        String sql = "select id_dang_ao, ma, ten from dang_ao"
                + " order by id_dang_ao desc";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DangAo dangAo = new DangAo();
                dangAo.setId(rs.getInt(1));
                dangAo.setMa(rs.getString(2));
                dangAo.setTen(rs.getString(3));
                list.add(dangAo);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public DangAo getDangAoById(int id) {
    DangAo dangAo = null;
    String sql = "select id_dang_ao, ma, ten from chat_lieu where id_dang_ao = ?";
    
    try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            dangAo = new DangAo();
            dangAo.setId(rs.getInt(1));
            dangAo.setMa(rs.getString(2));
            dangAo.setTen(rs.getString(3));
        }
    } catch (Exception e) {
       e.getMessage();
    }
    
    return dangAo;
}

    
    public Boolean add(DangAo dangAo) {
        String sql = "insert into dang_ao(ma, ten) values (?, ?)";
        try ( Connection con = connection.getConnection();  
               PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, dangAo.getMa());
            ps.setObject(2, dangAo.getTen());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }
    
    public Boolean update(Integer id, DangAo dangAo){
        String sql = "update dang_ao set ma = ?, ten = ?  where id_dang_ao = ?";
        try ( Connection con = connection.getConnection();  
               PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, dangAo.getMa());
            ps.setObject(2, dangAo.getTen());
            ps.setObject(3, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public Boolean delete(Integer id){
        String sql = "delete from chat_lieu where id_chat_lieu = ?";
        try ( Connection con = connection.getConnection();  
               PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }
    
    public DangAo getIdByTenDangAo(String ten) {
    DangAo id = null;
    String sql = "SELECT id_dang_ao FROM dang_ao WHERE ten = ?";
    try (Connection con = connection.getConnection();  
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, ten);  // Sử dụng tham số 'ten' được cung cấp
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            id = new DangAo();
            id.setId(rs.getInt(1));
        }
    } catch (Exception e) {
        e.getMessage();  // Xử lý ngoại lệ một cách thích hợp, có thể ghi log nó
    }
    return id;
}
    public static void main(String[] args) {
        DangAo list = new DangAoRepository().getIdByTenDangAo("Slim Fit");
        
            System.out.println(list);
        
    }
}
