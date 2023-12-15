/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.DangAo;
import DomainModel.Size;
import Utilities.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SizeRepository {

    private DBConnection connection;

    public ArrayList<Size> getListSize() {
        ArrayList<Size> list = new ArrayList<>();
        String sql = "select id_size, ma, ten from size"
                + " order by id_size desc";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Size size = new Size();
                size.setId(rs.getInt(1));
                size.setMa(rs.getString(2));
                size.setTen(rs.getString(3));
                list.add(size);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public Size getSizeById(int id) {
        Size size = null;
        String sql = "select id_size, ma, ten from size where id_size = ?";

        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                size = new Size();
                size.setId(rs.getInt(1));
                size.setMa(rs.getString(2));
                size.setTen(rs.getString(3));
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return size;
    }

    public Size getIdByTenSize(String ten) {
        Size id = null;
        String sql = "SELECT id_size FROM size WHERE ten = ?";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ten);  // Sử dụng tham số 'ten' được cung cấp
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = new Size();
                id.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            e.getMessage();  // Xử lý ngoại lệ một cách thích hợp, có thể ghi log nó
        }
        return id;
    }

    public Boolean add(Size size) {
        String sql = "insert into size(ma, ten) values (?, ?)";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, size.getMa());
            ps.setObject(2, size.getTen());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public Boolean update(Integer id, Size size) {
        String sql = "update size set ma = ?, ten = ?  where id_size = ?";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, size.getMa());
            ps.setObject(2, size.getTen());
            ps.setObject(3, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public Boolean delete(Integer id) {
        String sql = "delete from size where id_size = ?";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public static void main(String[] args) {
        Size list = new SizeRepository().getSizeById(1);

        System.out.println(list.toString());

    }
}
