/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.DangAo;
import DomainModel.MauSac;
import Utilities.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MauSacRepository {

    private DBConnection connection;

    public ArrayList<MauSac> getListMauSac() {
        ArrayList<MauSac> list = new ArrayList<>();
        String sql = "select id_mau_sac, ma, ten from mau_sac"
                + " order by id_mau_sac desc";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MauSac mauSac = new MauSac();
                mauSac.setId(rs.getInt(1));
                mauSac.setMa(rs.getString(2));
                mauSac.setTen(rs.getString(3));
                list.add(mauSac);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public MauSac getIdByTenMauSac(String ten) {
        MauSac id = null;
        String sql = "SELECT id_mau_sac FROM mau_sac WHERE ten = ?";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ten);  // Sử dụng tham số 'ten' được cung cấp
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = new MauSac();
                id.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            e.getMessage();  // Xử lý ngoại lệ một cách thích hợp, có thể ghi log nó
        }
        return id;
    }

    public MauSac getMauSacById(int id) {
        MauSac mauSac = null;
        String sql = "select id_mau_sac, ma, ten from mau_sac where id_mau_sac = ?";

        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                mauSac = new MauSac();
                mauSac.setId(rs.getInt(1));
                mauSac.setMa(rs.getString(2));
                mauSac.setTen(rs.getString(3));
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return mauSac;
    }

    public Boolean add(MauSac mauSac) {
        String sql = "insert into mau_sac(ma,ten) values (?,?)";

        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, mauSac.getMa());
            ps.setObject(2, mauSac.getTen());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public Boolean update(Integer id, MauSac mauSac) {
        String sql = "update mau_sac set ma = ?, ten = ?  where id_mau_sac = ?";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, mauSac.getMa());
            ps.setObject(2, mauSac.getTen());
            ps.setObject(3, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public Boolean delete(Integer id) {
        String sql = "delete from mau_sac where id_mau_sac = ?";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public static void main(String[] args) {
        MauSacRepository repo = new MauSacRepository();
        MauSac mauSac = new MauSac();
        mauSac.setTen("Tím");
        repo.add(mauSac);
        System.out.println(mauSac);
    }
}
