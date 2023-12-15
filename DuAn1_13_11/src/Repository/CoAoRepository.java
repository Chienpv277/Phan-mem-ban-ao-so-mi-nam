/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.CoAo;
import Utilities.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class CoAoRepository {
    private DBConnection connection;

    public ArrayList<CoAo> getListCoAo() {
        ArrayList<CoAo> list = new ArrayList<>();
        String sql = "select id_co_ao, ma, ten from co_ao"
                + " order by id_co_ao desc";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CoAo coAo = new CoAo();
                coAo.setId(rs.getInt(1));
                coAo.setMa(rs.getString(2));
                coAo.setTen(rs.getString(3));
                list.add(coAo);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public CoAo getCoAoById(int id) {
    CoAo coAo = null;
    String sql = "select id_co_ao, ma, ten from co_ao where id_co_ao = ?";
    
    try (Connection con = connection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            coAo = new CoAo();
            coAo.setId(rs.getInt(1));
            coAo.setMa(rs.getString(2));
            coAo.setTen(rs.getString(3));
        }
    } catch (Exception e) {
       e.getMessage();
    }
    
    return coAo;
}

    
    public Boolean add(CoAo coAo) {
        String sql = "insert into co_ao(ma, ten) values (?, ?)";
        try ( Connection con = connection.getConnection();  
               PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, coAo.getMa());
            ps.setObject(2, coAo.getTen());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }
    
    public Boolean update(Integer id, CoAo coAo){
        String sql = "update co_ao set ma = ?, ten = ?  where id_co_ao = ?";
        try ( Connection con = connection.getConnection();  
               PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, coAo.getMa());
            ps.setObject(2, coAo.getTen());
            ps.setObject(3, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public Boolean delete(Integer id){
        String sql = "delete from co_ao where id_co_ao = ?";
        try ( Connection con = connection.getConnection();  
               PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }
    public CoAo getIdByTenCoAo(String ten) {
    CoAo coAo = null;
    String sql = "SELECT id_co_ao FROM co_ao WHERE ten = ?";
    try (Connection con = connection.getConnection();  
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, ten);  // Sử dụng tham số 'ten' được cung cấp
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            coAo = new CoAo();
            coAo.setId(rs.getInt(1));
        }
    } catch (Exception e) {
        e.getMessage();  // Xử lý ngoại lệ một cách thích hợp, có thể ghi log nó
    }
    return coAo;
}
    public static void main(String[] args) {
        ArrayList<CoAo> list = new CoAoRepository().getListCoAo();
           for(CoAo x: list){
               System.out.println(x.toString());
           }
        
    }
}
