/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.ChucVu;
import DomainModel.NhanVien_1;
import Utilities.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.management.Query;

/**
 *
 * @author ADAMIN
 */
public class NhanVienRepository {

    public List<NhanVien_1> getAll() {
        List<NhanVien_1> listNhanVien_1 = new ArrayList<>();
        String query = "SELECT [id_nhan_vien] \"\n"
                + "                + \"      ,[dbo].[chuc_vu].[ten] \"\n"
                + "                + \"      ,[ho_ten] \"\n"
                + "                + \"      ,[ngay_sinh] \"\n"
                + "                + \"      ,[sdt] \"\n"
                + "                + \"      ,[gioi_tinh] \"\n"
                + "                + \"      ,[dia_chi] \"\n"
                + "                + \"      ,[email] \"\n"
                + "                + \"      ,[user] \"\n"
                + "                + \"      ,[password] \"\n"
                + "                + \"      ,[so_can_cuoc] \"\n"
                + "                + \"      ,[created_at] \"\n"
                + "                + \"      ,[updated_at] \"\n"
                + "                + \"      ,[created_by] \"\n"
                + "                + \"      ,[updated_by] \"\n"
                + "                + \"      ,[deleted] \"\n"
                + "                + \"      ,[trang_thai] \"\n"
                + "                + \"  FROM [dbo].[nhan_vien] inner join [dbo].[chuc_vu] on [dbo].[nhan_vien].id_chuc_vu = [dbo].[chuc_vu].id_chuc_vu ORDER BY id_nhan_vien DESC";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien_1 nhanVien = new NhanVien_1();
                ChucVu chucVu = new ChucVu();
                nhanVien.setId_nhan_vien(rs.getInt(1));
                chucVu.setTen(rs.getString(2));
                nhanVien.setChuc_vu(chucVu);
                nhanVien.setHo_ten(rs.getString(3));
                nhanVien.setNgay_sinh(rs.getDate(4));
                nhanVien.setSdt(rs.getString(5));
                nhanVien.setGioi_tinh(rs.getString(6));
                nhanVien.setDia_chi(rs.getString(7));
                nhanVien.setEmail(rs.getString(8));
                nhanVien.setUser(rs.getString(9));
                nhanVien.setPassword(rs.getString(10));
                nhanVien.setSo_can_cuoc(rs.getString(11));
                nhanVien.setCreated_at(rs.getDate(12));
                nhanVien.setUpdated_at(rs.getDate(13));
                nhanVien.setCreated_by(rs.getString(14));
                nhanVien.setUpdated_by(rs.getString(15));
                nhanVien.setDeleted(rs.getString(16));
                nhanVien.setTrang_thai(rs.getInt(17));
                listNhanVien_1.add(nhanVien);
            }
            return listNhanVien_1;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return listNhanVien_1;
    }

    public List<NhanVien_1> getAllNghi() {
        List<NhanVien_1> listNhanVien_1 = new ArrayList<>();
        String query = "SELECT [id_nhan_vien] \"\n"
                + "                + \"      ,[dbo].[chuc_vu].[ten] \"\n"
                + "                + \"      ,[ho_ten] \"\n"
                + "                + \"      ,[ngay_sinh] \"\n"
                + "                + \"      ,[sdt] \"\n"
                + "                + \"      ,[gioi_tinh] \"\n"
                + "                + \"      ,[dia_chi] \"\n"
                + "                + \"      ,[email] \"\n"
                + "                + \"      ,[so_can_cuoc] \"\n"
                + "                + \"      ,[created_at] \"\n"
                + "                + \"      ,[updated_at] \"\n"
                + "                + \"      ,[created_by] \"\n"
                + "                + \"      ,[updated_by] \"\n"
                + "                + \"      ,[deleted] \"\n"
                + "                + \"      ,[trang_thai] \"\n"
                + "                + \"  FROM [dbo].[nhan_vien] inner join [dbo].[chuc_vu] on [dbo].[nhan_vien].id_chuc_vu = [dbo].[chuc_vu].id_chuc_vu WHERE [trang_thai] = 2";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien_1 nhanVien = new NhanVien_1();
                ChucVu chucVu = new ChucVu();
                nhanVien.setId_nhan_vien(rs.getInt(1));
                chucVu.setTen(rs.getString(2));
                nhanVien.setChuc_vu(chucVu);
                nhanVien.setHo_ten(rs.getString(3));
                nhanVien.setNgay_sinh(rs.getDate(4));
                nhanVien.setSdt(rs.getString(5));
                nhanVien.setGioi_tinh(rs.getString(6));
                nhanVien.setDia_chi(rs.getString(7));
                nhanVien.setEmail(rs.getString(8));
                nhanVien.setSo_can_cuoc(rs.getString(9));
                nhanVien.setCreated_at(rs.getDate(10));
                nhanVien.setUpdated_at(rs.getDate(11));
                nhanVien.setCreated_by(rs.getString(12));
                nhanVien.setUpdated_by(rs.getString(13));
                nhanVien.setDeleted(rs.getString(14));
                nhanVien.setTrang_thai(rs.getInt(15));
                listNhanVien_1.add(nhanVien);
            }
            return listNhanVien_1;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return listNhanVien_1;
    }

    public NhanVien_1 getOne(String user, String password) {
        String query = "SELECT [id_nhan_vien] \"\n"
                + "                + \"      ,[ho_ten] \"\n"
                + "                + \"      ,[ngay_sinh] \"\n"
                + "                + \"      ,[sdt] \"\n"
                + "                + \"      ,[gioi_tinh] \"\n"
                + "                + \"      ,[dia_chi] \"\n"
                + "                + \"      ,[email] \"\n"
                + "                + \"      ,[user] \"\n"
                + "                + \"      ,[password] \"\n"
                + "                + \"      ,[so_can_cuoc] \"\n"
                + "                + \"      ,[created_at] \"\n"
                + "                + \"      ,[updated_at] \"\n"
                + "                + \"      ,[created_by] \"\n"
                + "                + \"      ,[updated_by] \"\n"
                + "                + \"      ,[deleted] \"\n"
                + "                + \"      ,[trang_thai] \"\n"
                + "                + \"  FROM [dbo].[nhan_vien] WHERE user=? AND password=?";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, user);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    NhanVien_1 nhanVien = new NhanVien_1(rs.getString(1), rs.getString(2));
                    return nhanVien;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean add(NhanVien_1 nhanVien) {
        String query = "INSERT INTO [dbo].[nhan_vien] "
                + "      ([id_chuc_vu] "
                + "      ,[ho_ten] "
                + "      ,[ngay_sinh] "
                + "      ,[sdt] "
                + "      ,[gioi_tinh] "
                + "      ,[dia_chi] "
                + "      ,[email] "
                + "      ,[user] "
                + "      ,[password] "
                + "      ,[so_can_cuoc] "
                + "      ,[updated_at] "
                + "      ,[created_by] "
                + "      ,[updated_by] "
                + "      ,[deleted] "
                + "      ,[trang_thai]) "
                + "     VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int check = 0;
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, nhanVien.getChuc_vu().getId_chuc_vu());
            ps.setObject(2, nhanVien.getHo_ten());
            ps.setObject(3, nhanVien.getNgay_sinh());
            ps.setObject(4, nhanVien.getSdt());
            ps.setObject(5, nhanVien.getGioi_tinh());
            ps.setObject(6, nhanVien.getDia_chi());
            ps.setObject(7, nhanVien.getEmail());
            ps.setObject(8, nhanVien.getUser());
            ps.setObject(9, nhanVien.getPassword());
            ps.setObject(10, nhanVien.getSo_can_cuoc());
            ps.setObject(11, nhanVien.getUpdated_at());
            ps.setObject(12, nhanVien.getCreated_by());
            ps.setObject(13, nhanVien.getUpdated_by());
            ps.setObject(14, nhanVien.getDeleted());
            ps.setObject(15, nhanVien.getTrang_thai());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean update(NhanVien_1 nhanVien, int id) {
        String query = "UPDATE [dbo].[nhan_vien] "
                + "   SET [id_chuc_vu] = ? "
                + "      ,[ho_ten] = ? "
                + "      ,[ngay_sinh] = ? "
                + "      ,[sdt] = ? "
                + "      ,[gioi_tinh] = ? "
                + "      ,[dia_chi] = ? "
                + "      ,[email] = ? "
                //                + "      ,[user] "
                //                + "      ,[password] "
                + "      ,[so_can_cuoc] = ? "
                + "      ,[created_at] = ? "
                + "      ,[updated_at] = ?"
                + "      ,[created_by] = ? "
                + "      ,[updated_by] = ? "
                + "      ,[deleted] = ? "
                + "      ,[trang_thai] = ? "
                + "     WHERE [id_nhan_vien] = ?";
        int check = 0;
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, nhanVien.getChuc_vu().getId_chuc_vu());
            ps.setObject(2, nhanVien.getHo_ten());
            ps.setObject(3, nhanVien.getNgay_sinh());
            ps.setObject(4, nhanVien.getSdt());
            ps.setObject(5, nhanVien.getGioi_tinh());
            ps.setObject(6, nhanVien.getDia_chi());
            ps.setObject(7, nhanVien.getEmail());
//            ps.setObject(8, nhanVien.getUser());
//            ps.setObject(9, nhanVien.getPassword());
            ps.setObject(8, nhanVien.getSo_can_cuoc());
            ps.setObject(9, nhanVien.getCreated_at());
            ps.setObject(10, nhanVien.getUpdated_at());
            ps.setObject(11, nhanVien.getCreated_by());
            ps.setObject(12, nhanVien.getUpdated_by());
            ps.setObject(13, nhanVien.getDeleted());
            ps.setObject(14, nhanVien.getTrang_thai());
            ps.setObject(15, id);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public static void main(String[] args) {
        List<NhanVien_1> list = new NhanVienRepository().getAll();
        for (NhanVien_1 nhanVien : list) {
            System.out.println(nhanVien.toString());
        }
//        NhanVien_1 nhanVien = new NhanVien_1Repository().getOne(1);
//        System.out.println(nhanVien.toString());
//List<NhanVien_1> list = new NhanVien_1Repository().getAll();
//        for (NhanVien_1 nhanVien : list) {
//            System.out.println(nhanVien.toString());
//        }
    }

}
