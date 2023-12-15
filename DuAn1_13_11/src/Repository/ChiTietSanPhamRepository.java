/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.ChatLieu;
import DomainModel.ChiTietSanPham;
import DomainModel.CoAo;
import DomainModel.CoTay;
import DomainModel.DangAo;
import DomainModel.Hang;
//import DomainModel.QuanLySanPham.HinhAnh;
import DomainModel.MauSac;
import DomainModel.SanPham;
import DomainModel.Size;
import Utilities.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author FPTSHOP
 */
public class ChiTietSanPhamRepository {

    private DBConnection connection;

    public ArrayList<ChiTietSanPham> getListChiTiet() {
        ArrayList<ChiTietSanPham> list = new ArrayList<>();
        String sql = "select a.id_chi_tiet_san_pham, b.id_san_pham, b.ma, b.ten, \n"
                + "              c.id_mau_sac, c.ma, c.ten, \n"
                + "		d.id_hang, d.ma, d.ten, \n"
                + "		e.id_dang_ao, e.ma, e.ten, \n"
                + "		f.id_size, f.ma, f.ten, \n"
                + "		g.id_chat_lieu, d.ma, g.ten, \n"
                + "		h.id_co_ao, h.ma, h.ten, \n"
                + "		m.id_co_tay, m.ma, m.ten, \n"
                + "		so_luong_ton, gia from chi_tiet_san_pham a\n"
                + "		join san_pham b on a.id_san_pham = b.id_san_pham\n"
                + "		join mau_sac c on a.id_mau_sac = c.id_mau_sac\n"
                + "		join hang d on a.id_hang = d.id_hang\n"
                + "		join dang_ao e on a.id_dang_ao = e.id_dang_ao\n"
                + "		join size f on a.id_size = f.id_size\n"
                + "		join chat_lieu g on a.id_chat_lieu = g.id_chat_lieu\n"
                + "		join co_ao h on a.id_co_ao = h.id_co_ao\n"
                + "		join co_tay m on a.id_co_tay = m.id_co_tay\n"
                + "             ORDER BY a.id_chi_tiet_san_pham DESC;";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                DangAo dangAo = new DangAo();
                Size size = new Size();
                ChatLieu chatLieu = new ChatLieu();
                CoAo coAo = new CoAo();
                CoTay coTay = new CoTay();
//                HinhAnh hinhAnh = new HinhAnh();

                chiTietSanPham.setId(rs.getInt(1));

                sanPham.setId(rs.getInt(2));
                sanPham.setMa(rs.getString(3));
                sanPham.setTen(rs.getString(4));
                chiTietSanPham.setSanPham(sanPham);

                mauSac.setId(rs.getInt(5));
                mauSac.setMa(rs.getString(6));
                mauSac.setTen(rs.getString(7));
                chiTietSanPham.setMauSac(mauSac);

                hang.setId(rs.getInt(8));
                hang.setMa(rs.getString(9));
                hang.setTen(rs.getString(10));
                chiTietSanPham.setHang(hang);

                dangAo.setId(rs.getInt(11));
                dangAo.setMa(rs.getString(12));
                dangAo.setTen(rs.getString(13));
                chiTietSanPham.setDangAo(dangAo);

                size.setId(rs.getInt(14));
                size.setMa(rs.getString(15));
                size.setTen(rs.getString(16));
                chiTietSanPham.setSize(size);

                chatLieu.setId(rs.getInt(17));
                chatLieu.setMa(rs.getString(18));
                chatLieu.setTen(rs.getString(19));
                chiTietSanPham.setChatLieu(chatLieu);

                coAo.setId(rs.getInt(20));
                coAo.setMa(rs.getString(21));
                coAo.setTen(rs.getString(22));
                chiTietSanPham.setCoAo(coAo);

                coTay.setId(rs.getInt(23));
                coTay.setMa(rs.getString(24));
                coTay.setTen(rs.getString(25));
                chiTietSanPham.setCoTay(coTay);

                chiTietSanPham.setSoLuong(rs.getInt(26));
                chiTietSanPham.setGia(rs.getFloat(27));
                list.add(chiTietSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Boolean add(ChiTietSanPham s) {
        String query = "insert into chi_tiet_san_pham(id_san_pham, id_hang, "
                + "id_mau_sac, id_chat_lieu, id_dang_ao, id_co_ao,"
                + " id_co_tay, id_size, gia, so_luong_ton)\n"
                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, s.getSanPham().getId());
            ps.setInt(2, s.getHang().getId());
            ps.setInt(3, s.getMauSac().getId());
            ps.setInt(4, s.getChatLieu().getId());
            ps.setInt(5, s.getDangAo().getId());
            ps.setInt(6, s.getCoTay().getId());
            ps.setInt(7, s.getCoAo().getId());
            ps.setInt(8, s.getSize().getId());
            ps.setFloat(9, s.getGia());
            ps.setInt(10, s.getSoLuong());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean update(Integer id, ChiTietSanPham s) {
        String query = "update chi_tiet_san_pham set id_san_pham = ?, id_hang = ?, "
                + "id_mau_sac = ?, id_chat_lieu = ?, id_dang_ao = ?, id_co_ao = ?,"
                + " id_co_tay = ?, id_size = ?, gia = ?, so_luong_ton = ?\n"
                + "where id_chi_tiet_san_pham = ?";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {

            ps.setObject(1, s.getSanPham().getId());
            ps.setObject(2, s.getHang().getId());
            ps.setObject(3, s.getMauSac().getId());
            ps.setObject(4, s.getChatLieu().getId());
            ps.setObject(5, s.getDangAo().getId());
            ps.setObject(6, s.getCoAo().getId());
            ps.setObject(7, s.getCoTay().getId());
            ps.setObject(8, s.getSize().getId());
            ps.setObject(9, s.getGia());
            ps.setObject(10, s.getSoLuong());
            ps.setObject(11, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean delete(Integer id) {
        String sql = "delete from chi_tiet_san_pham where id_chi_tiet_san_pham = ?";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public ArrayList<ChiTietSanPham> getListChiTietByTen(String tenSanPham) {
        ArrayList<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
        String sql = "select a.id_chi_tiet_san_pham, b.id_san_pham, b.ma, b.ten, \n"
                + "              c.id_mau_sac, c.ma, c.ten, \n"
                + "		d.id_hang, d.ma, d.ten, \n"
                + "		e.id_dang_ao, e.ma, e.ten, \n"
                + "		f.id_size, f.ma, f.ten, \n"
                + "		g.id_chat_lieu, d.ma, g.ten, \n"
                + "		h.id_co_ao, h.ma, h.ten, \n"
                + "		m.id_co_tay, m.ma, m.ten, \n"
                + "		so_luong_ton, gia from chi_tiet_san_pham a\n"
                + "		join san_pham b on a.id_san_pham = b.id_san_pham\n"
                + "		join mau_sac c on a.id_mau_sac = c.id_mau_sac\n"
                + "		join hang d on a.id_hang = d.id_hang\n"
                + "		join dang_ao e on a.id_dang_ao = e.id_dang_ao\n"
                + "		join size f on a.id_size = f.id_size\n"
                + "		join chat_lieu g on a.id_chat_lieu = g.id_chat_lieu\n"
                + "		join co_ao h on a.id_co_ao = h.id_co_ao\n"
                + "		join co_tay m on a.id_co_tay = m.id_co_tay\n"
                + "             where b.ten like ?";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tenSanPham);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                DangAo dangAo = new DangAo();
                Size size = new Size();
                ChatLieu chatLieu = new ChatLieu();
                CoAo coAo = new CoAo();
                CoTay coTay = new CoTay();
//                HinhAnh hinhAnh = new HinhAnh();

                chiTietSanPham.setId(rs.getInt(1));

                sanPham.setId(rs.getInt(2));
                sanPham.setMa(rs.getString(3));
                sanPham.setTen(rs.getString(4));
                chiTietSanPham.setSanPham(sanPham);

                mauSac.setId(rs.getInt(5));
                mauSac.setMa(rs.getString(6));
                mauSac.setTen(rs.getString(7));
                chiTietSanPham.setMauSac(mauSac);

                hang.setId(rs.getInt(8));
                hang.setMa(rs.getString(9));
                hang.setTen(rs.getString(10));
                chiTietSanPham.setHang(hang);

                dangAo.setId(rs.getInt(11));
                dangAo.setMa(rs.getString(12));
                dangAo.setTen(rs.getString(13));
                chiTietSanPham.setDangAo(dangAo);

                size.setId(rs.getInt(14));
                size.setMa(rs.getString(15));
                size.setTen(rs.getString(16));
                chiTietSanPham.setSize(size);

                chatLieu.setId(rs.getInt(17));
                chatLieu.setMa(rs.getString(18));
                chatLieu.setTen(rs.getString(19));
                chiTietSanPham.setChatLieu(chatLieu);

                coAo.setId(rs.getInt(20));
                coAo.setMa(rs.getString(21));
                coAo.setTen(rs.getString(22));
                chiTietSanPham.setCoAo(coAo);

                coTay.setId(rs.getInt(23));
                coTay.setMa(rs.getString(24));
                coTay.setTen(rs.getString(25));
                chiTietSanPham.setCoTay(coTay);

                chiTietSanPham.setSoLuong(rs.getInt(26));
                chiTietSanPham.setGia(rs.getFloat(27));
                chiTietSanPhams.add(chiTietSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietSanPhams;
    }

    public ArrayList<ChiTietSanPham> searchByTen(String ten) {
        ArrayList<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
        String sql = "select a.id_chi_tiet_san_pham, b.id_san_pham, b.ma, b.ten, \n"
                + "              c.id_mau_sac, c.ma, c.ten, \n"
                + "		d.id_hang, d.ma, d.ten, \n"
                + "		e.id_dang_ao, e.ma, e.ten, \n"
                + "		f.id_size, f.ma, f.ten, \n"
                + "		g.id_chat_lieu, d.ma, g.ten, \n"
                + "		h.id_co_ao, h.ma, h.ten, \n"
                + "		m.id_co_tay, m.ma, m.ten, \n"
                + "		so_luong_ton, gia from chi_tiet_san_pham a\n"
                + "		join san_pham b on a.id_san_pham = b.id_san_pham\n"
                + "		join mau_sac c on a.id_mau_sac = c.id_mau_sac\n"
                + "		join hang d on a.id_hang = d.id_hang\n"
                + "		join dang_ao e on a.id_dang_ao = e.id_dang_ao\n"
                + "		join size f on a.id_size = f.id_size\n"
                + "		join chat_lieu g on a.id_chat_lieu = g.id_chat_lieu\n"
                + "		join co_ao h on a.id_co_ao = h.id_co_ao\n"
                + "		join co_tay m on a.id_co_tay = m.id_co_tay\n"
                + "             where b.ten like N'%"+ten+"%'";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                DangAo dangAo = new DangAo();
                Size size = new Size();
                ChatLieu chatLieu = new ChatLieu();
                CoAo coAo = new CoAo();
                CoTay coTay = new CoTay();
//                HinhAnh hinhAnh = new HinhAnh();

                chiTietSanPham.setId(rs.getInt(1));

                sanPham.setId(rs.getInt(2));
                sanPham.setMa(rs.getString(3));
                sanPham.setTen(rs.getString(4));
                chiTietSanPham.setSanPham(sanPham);

                mauSac.setId(rs.getInt(5));
                mauSac.setMa(rs.getString(6));
                mauSac.setTen(rs.getString(7));
                chiTietSanPham.setMauSac(mauSac);

                hang.setId(rs.getInt(8));
                hang.setMa(rs.getString(9));
                hang.setTen(rs.getString(10));
                chiTietSanPham.setHang(hang);

                dangAo.setId(rs.getInt(11));
                dangAo.setMa(rs.getString(12));
                dangAo.setTen(rs.getString(13));
                chiTietSanPham.setDangAo(dangAo);

                size.setId(rs.getInt(14));
                size.setMa(rs.getString(15));
                size.setTen(rs.getString(16));
                chiTietSanPham.setSize(size);

                chatLieu.setId(rs.getInt(17));
                chatLieu.setMa(rs.getString(18));
                chatLieu.setTen(rs.getString(19));
                chiTietSanPham.setChatLieu(chatLieu);

                coAo.setId(rs.getInt(20));
                coAo.setMa(rs.getString(21));
                coAo.setTen(rs.getString(22));
                chiTietSanPham.setCoAo(coAo);

                coTay.setId(rs.getInt(23));
                coTay.setMa(rs.getString(24));
                coTay.setTen(rs.getString(25));
                chiTietSanPham.setCoTay(coTay);

                chiTietSanPham.setSoLuong(rs.getInt(26));
                chiTietSanPham.setGia(rs.getFloat(27));
                chiTietSanPhams.add(chiTietSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietSanPhams;
    }

    public ArrayList<ChiTietSanPham> searchByHang(String ten) {
        ArrayList<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
        String sql = "select a.id_chi_tiet_san_pham, b.id_san_pham, b.ma, b.ten, \n"
                + "              c.id_mau_sac, c.ma, c.ten, \n"
                + "		d.id_hang, d.ma, d.ten, \n"
                + "		e.id_dang_ao, e.ma, e.ten, \n"
                + "		f.id_size, f.ma, f.ten, \n"
                + "		g.id_chat_lieu, d.ma, g.ten, \n"
                + "		h.id_co_ao, h.ma, h.ten, \n"
                + "		m.id_co_tay, m.ma, m.ten, \n"
                + "		so_luong_ton, gia from chi_tiet_san_pham a\n"
                + "		join san_pham b on a.id_san_pham = b.id_san_pham\n"
                + "		join mau_sac c on a.id_mau_sac = c.id_mau_sac\n"
                + "		join hang d on a.id_hang = d.id_hang\n"
                + "		join dang_ao e on a.id_dang_ao = e.id_dang_ao\n"
                + "		join size f on a.id_size = f.id_size\n"
                + "		join chat_lieu g on a.id_chat_lieu = g.id_chat_lieu\n"
                + "		join co_ao h on a.id_co_ao = h.id_co_ao\n"
                + "		join co_tay m on a.id_co_tay = m.id_co_tay\n"
                + "             where d.ten like N'%"+ten+"%'";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                DangAo dangAo = new DangAo();
                Size size = new Size();
                ChatLieu chatLieu = new ChatLieu();
                CoAo coAo = new CoAo();
                CoTay coTay = new CoTay();
//                HinhAnh hinhAnh = new HinhAnh();

                chiTietSanPham.setId(rs.getInt(1));

                sanPham.setId(rs.getInt(2));
                sanPham.setMa(rs.getString(3));
                sanPham.setTen(rs.getString(4));
                chiTietSanPham.setSanPham(sanPham);

                mauSac.setId(rs.getInt(5));
                mauSac.setMa(rs.getString(6));
                mauSac.setTen(rs.getString(7));
                chiTietSanPham.setMauSac(mauSac);

                hang.setId(rs.getInt(8));
                hang.setMa(rs.getString(9));
                hang.setTen(rs.getString(10));
                chiTietSanPham.setHang(hang);

                dangAo.setId(rs.getInt(11));
                dangAo.setMa(rs.getString(12));
                dangAo.setTen(rs.getString(13));
                chiTietSanPham.setDangAo(dangAo);

                size.setId(rs.getInt(14));
                size.setMa(rs.getString(15));
                size.setTen(rs.getString(16));
                chiTietSanPham.setSize(size);

                chatLieu.setId(rs.getInt(17));
                chatLieu.setMa(rs.getString(18));
                chatLieu.setTen(rs.getString(19));
                chiTietSanPham.setChatLieu(chatLieu);

                coAo.setId(rs.getInt(20));
                coAo.setMa(rs.getString(21));
                coAo.setTen(rs.getString(22));
                chiTietSanPham.setCoAo(coAo);

                coTay.setId(rs.getInt(23));
                coTay.setMa(rs.getString(24));
                coTay.setTen(rs.getString(25));
                chiTietSanPham.setCoTay(coTay);

                chiTietSanPham.setSoLuong(rs.getInt(26));
                chiTietSanPham.setGia(rs.getFloat(27));
                chiTietSanPhams.add(chiTietSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietSanPhams;
    }
    
    public ArrayList<ChiTietSanPham> searchByMauSac(String ten) {
        ArrayList<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
        String sql = "select a.id_chi_tiet_san_pham, b.id_san_pham, b.ma, b.ten, \n"
                + "              c.id_mau_sac, c.ma, c.ten, \n"
                + "		d.id_hang, d.ma, d.ten, \n"
                + "		e.id_dang_ao, e.ma, e.ten, \n"
                + "		f.id_size, f.ma, f.ten, \n"
                + "		g.id_chat_lieu, d.ma, g.ten, \n"
                + "		h.id_co_ao, h.ma, h.ten, \n"
                + "		m.id_co_tay, m.ma, m.ten, \n"
                + "		so_luong_ton, gia from chi_tiet_san_pham a\n"
                + "		join san_pham b on a.id_san_pham = b.id_san_pham\n"
                + "		join mau_sac c on a.id_mau_sac = c.id_mau_sac\n"
                + "		join hang d on a.id_hang = d.id_hang\n"
                + "		join dang_ao e on a.id_dang_ao = e.id_dang_ao\n"
                + "		join size f on a.id_size = f.id_size\n"
                + "		join chat_lieu g on a.id_chat_lieu = g.id_chat_lieu\n"
                + "		join co_ao h on a.id_co_ao = h.id_co_ao\n"
                + "		join co_tay m on a.id_co_tay = m.id_co_tay\n"
                + "             where c.ten like N'%"+ten+"%'";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                DangAo dangAo = new DangAo();
                Size size = new Size();
                ChatLieu chatLieu = new ChatLieu();
                CoAo coAo = new CoAo();
                CoTay coTay = new CoTay();
//                HinhAnh hinhAnh = new HinhAnh();

                chiTietSanPham.setId(rs.getInt(1));

                sanPham.setId(rs.getInt(2));
                sanPham.setMa(rs.getString(3));
                sanPham.setTen(rs.getString(4));
                chiTietSanPham.setSanPham(sanPham);

                mauSac.setId(rs.getInt(5));
                mauSac.setMa(rs.getString(6));
                mauSac.setTen(rs.getString(7));
                chiTietSanPham.setMauSac(mauSac);

                hang.setId(rs.getInt(8));
                hang.setMa(rs.getString(9));
                hang.setTen(rs.getString(10));
                chiTietSanPham.setHang(hang);

                dangAo.setId(rs.getInt(11));
                dangAo.setMa(rs.getString(12));
                dangAo.setTen(rs.getString(13));
                chiTietSanPham.setDangAo(dangAo);

                size.setId(rs.getInt(14));
                size.setMa(rs.getString(15));
                size.setTen(rs.getString(16));
                chiTietSanPham.setSize(size);

                chatLieu.setId(rs.getInt(17));
                chatLieu.setMa(rs.getString(18));
                chatLieu.setTen(rs.getString(19));
                chiTietSanPham.setChatLieu(chatLieu);

                coAo.setId(rs.getInt(20));
                coAo.setMa(rs.getString(21));
                coAo.setTen(rs.getString(22));
                chiTietSanPham.setCoAo(coAo);

                coTay.setId(rs.getInt(23));
                coTay.setMa(rs.getString(24));
                coTay.setTen(rs.getString(25));
                chiTietSanPham.setCoTay(coTay);

                chiTietSanPham.setSoLuong(rs.getInt(26));
                chiTietSanPham.setGia(rs.getFloat(27));
                chiTietSanPhams.add(chiTietSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietSanPhams;
    }
    
    public ArrayList<ChiTietSanPham> searchByDangAo(String ten) {
        ArrayList<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
        String sql = "select a.id_chi_tiet_san_pham, b.id_san_pham, b.ma, b.ten, \n"
                + "              c.id_mau_sac, c.ma, c.ten, \n"
                + "		d.id_hang, d.ma, d.ten, \n"
                + "		e.id_dang_ao, e.ma, e.ten, \n"
                + "		f.id_size, f.ma, f.ten, \n"
                + "		g.id_chat_lieu, d.ma, g.ten, \n"
                + "		h.id_co_ao, h.ma, h.ten, \n"
                + "		m.id_co_tay, m.ma, m.ten, \n"
                + "		so_luong_ton, gia from chi_tiet_san_pham a\n"
                + "		join san_pham b on a.id_san_pham = b.id_san_pham\n"
                + "		join mau_sac c on a.id_mau_sac = c.id_mau_sac\n"
                + "		join hang d on a.id_hang = d.id_hang\n"
                + "		join dang_ao e on a.id_dang_ao = e.id_dang_ao\n"
                + "		join size f on a.id_size = f.id_size\n"
                + "		join chat_lieu g on a.id_chat_lieu = g.id_chat_lieu\n"
                + "		join co_ao h on a.id_co_ao = h.id_co_ao\n"
                + "		join co_tay m on a.id_co_tay = m.id_co_tay\n"
                + "             where e.ten like N'%"+ten+"%'";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                DangAo dangAo = new DangAo();
                Size size = new Size();
                ChatLieu chatLieu = new ChatLieu();
                CoAo coAo = new CoAo();
                CoTay coTay = new CoTay();
//                HinhAnh hinhAnh = new HinhAnh();

                chiTietSanPham.setId(rs.getInt(1));

                sanPham.setId(rs.getInt(2));
                sanPham.setMa(rs.getString(3));
                sanPham.setTen(rs.getString(4));
                chiTietSanPham.setSanPham(sanPham);

                mauSac.setId(rs.getInt(5));
                mauSac.setMa(rs.getString(6));
                mauSac.setTen(rs.getString(7));
                chiTietSanPham.setMauSac(mauSac);

                hang.setId(rs.getInt(8));
                hang.setMa(rs.getString(9));
                hang.setTen(rs.getString(10));
                chiTietSanPham.setHang(hang);

                dangAo.setId(rs.getInt(11));
                dangAo.setMa(rs.getString(12));
                dangAo.setTen(rs.getString(13));
                chiTietSanPham.setDangAo(dangAo);

                size.setId(rs.getInt(14));
                size.setMa(rs.getString(15));
                size.setTen(rs.getString(16));
                chiTietSanPham.setSize(size);

                chatLieu.setId(rs.getInt(17));
                chatLieu.setMa(rs.getString(18));
                chatLieu.setTen(rs.getString(19));
                chiTietSanPham.setChatLieu(chatLieu);

                coAo.setId(rs.getInt(20));
                coAo.setMa(rs.getString(21));
                coAo.setTen(rs.getString(22));
                chiTietSanPham.setCoAo(coAo);

                coTay.setId(rs.getInt(23));
                coTay.setMa(rs.getString(24));
                coTay.setTen(rs.getString(25));
                chiTietSanPham.setCoTay(coTay);

                chiTietSanPham.setSoLuong(rs.getInt(26));
                chiTietSanPham.setGia(rs.getFloat(27));
                chiTietSanPhams.add(chiTietSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietSanPhams;
    }
    
    public ArrayList<ChiTietSanPham> searchByCoTay(String ten) {
        ArrayList<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
        String sql = "select a.id_chi_tiet_san_pham, b.id_san_pham, b.ma, b.ten, \n"
                + "              c.id_mau_sac, c.ma, c.ten, \n"
                + "		d.id_hang, d.ma, d.ten, \n"
                + "		e.id_dang_ao, e.ma, e.ten, \n"
                + "		f.id_size, f.ma, f.ten, \n"
                + "		g.id_chat_lieu, d.ma, g.ten, \n"
                + "		h.id_co_ao, h.ma, h.ten, \n"
                + "		m.id_co_tay, m.ma, m.ten, \n"
                + "		so_luong_ton, gia from chi_tiet_san_pham a\n"
                + "		join san_pham b on a.id_san_pham = b.id_san_pham\n"
                + "		join mau_sac c on a.id_mau_sac = c.id_mau_sac\n"
                + "		join hang d on a.id_hang = d.id_hang\n"
                + "		join dang_ao e on a.id_dang_ao = e.id_dang_ao\n"
                + "		join size f on a.id_size = f.id_size\n"
                + "		join chat_lieu g on a.id_chat_lieu = g.id_chat_lieu\n"
                + "		join co_ao h on a.id_co_ao = h.id_co_ao\n"
                + "		join co_tay m on a.id_co_tay = m.id_co_tay\n"
                + "             where m.ten like N'%"+ten+"%'";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                DangAo dangAo = new DangAo();
                Size size = new Size();
                ChatLieu chatLieu = new ChatLieu();
                CoAo coAo = new CoAo();
                CoTay coTay = new CoTay();
//                HinhAnh hinhAnh = new HinhAnh();

                chiTietSanPham.setId(rs.getInt(1));

                sanPham.setId(rs.getInt(2));
                sanPham.setMa(rs.getString(3));
                sanPham.setTen(rs.getString(4));
                chiTietSanPham.setSanPham(sanPham);

                mauSac.setId(rs.getInt(5));
                mauSac.setMa(rs.getString(6));
                mauSac.setTen(rs.getString(7));
                chiTietSanPham.setMauSac(mauSac);

                hang.setId(rs.getInt(8));
                hang.setMa(rs.getString(9));
                hang.setTen(rs.getString(10));
                chiTietSanPham.setHang(hang);

                dangAo.setId(rs.getInt(11));
                dangAo.setMa(rs.getString(12));
                dangAo.setTen(rs.getString(13));
                chiTietSanPham.setDangAo(dangAo);

                size.setId(rs.getInt(14));
                size.setMa(rs.getString(15));
                size.setTen(rs.getString(16));
                chiTietSanPham.setSize(size);

                chatLieu.setId(rs.getInt(17));
                chatLieu.setMa(rs.getString(18));
                chatLieu.setTen(rs.getString(19));
                chiTietSanPham.setChatLieu(chatLieu);

                coAo.setId(rs.getInt(20));
                coAo.setMa(rs.getString(21));
                coAo.setTen(rs.getString(22));
                chiTietSanPham.setCoAo(coAo);

                coTay.setId(rs.getInt(23));
                coTay.setMa(rs.getString(24));
                coTay.setTen(rs.getString(25));
                chiTietSanPham.setCoTay(coTay);

                chiTietSanPham.setSoLuong(rs.getInt(26));
                chiTietSanPham.setGia(rs.getFloat(27));
                chiTietSanPhams.add(chiTietSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietSanPhams;
    }
    
    public ArrayList<ChiTietSanPham> searchByCoAo(String ten) {
        ArrayList<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
        String sql = "select a.id_chi_tiet_san_pham, b.id_san_pham, b.ma, b.ten, \n"
                + "              c.id_mau_sac, c.ma, c.ten, \n"
                + "		d.id_hang, d.ma, d.ten, \n"
                + "		e.id_dang_ao, e.ma, e.ten, \n"
                + "		f.id_size, f.ma, f.ten, \n"
                + "		g.id_chat_lieu, d.ma, g.ten, \n"
                + "		h.id_co_ao, h.ma, h.ten, \n"
                + "		m.id_co_tay, m.ma, m.ten, \n"
                + "		so_luong_ton, gia from chi_tiet_san_pham a\n"
                + "		join san_pham b on a.id_san_pham = b.id_san_pham\n"
                + "		join mau_sac c on a.id_mau_sac = c.id_mau_sac\n"
                + "		join hang d on a.id_hang = d.id_hang\n"
                + "		join dang_ao e on a.id_dang_ao = e.id_dang_ao\n"
                + "		join size f on a.id_size = f.id_size\n"
                + "		join chat_lieu g on a.id_chat_lieu = g.id_chat_lieu\n"
                + "		join co_ao h on a.id_co_ao = h.id_co_ao\n"
                + "		join co_tay m on a.id_co_tay = m.id_co_tay\n"
                + "             where h.ten like N'%"+ten+"%'";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                DangAo dangAo = new DangAo();
                Size size = new Size();
                ChatLieu chatLieu = new ChatLieu();
                CoAo coAo = new CoAo();
                CoTay coTay = new CoTay();
//                HinhAnh hinhAnh = new HinhAnh();

                chiTietSanPham.setId(rs.getInt(1));

                sanPham.setId(rs.getInt(2));
                sanPham.setMa(rs.getString(3));
                sanPham.setTen(rs.getString(4));
                chiTietSanPham.setSanPham(sanPham);

                mauSac.setId(rs.getInt(5));
                mauSac.setMa(rs.getString(6));
                mauSac.setTen(rs.getString(7));
                chiTietSanPham.setMauSac(mauSac);

                hang.setId(rs.getInt(8));
                hang.setMa(rs.getString(9));
                hang.setTen(rs.getString(10));
                chiTietSanPham.setHang(hang);

                dangAo.setId(rs.getInt(11));
                dangAo.setMa(rs.getString(12));
                dangAo.setTen(rs.getString(13));
                chiTietSanPham.setDangAo(dangAo);

                size.setId(rs.getInt(14));
                size.setMa(rs.getString(15));
                size.setTen(rs.getString(16));
                chiTietSanPham.setSize(size);

                chatLieu.setId(rs.getInt(17));
                chatLieu.setMa(rs.getString(18));
                chatLieu.setTen(rs.getString(19));
                chiTietSanPham.setChatLieu(chatLieu);

                coAo.setId(rs.getInt(20));
                coAo.setMa(rs.getString(21));
                coAo.setTen(rs.getString(22));
                chiTietSanPham.setCoAo(coAo);

                coTay.setId(rs.getInt(23));
                coTay.setMa(rs.getString(24));
                coTay.setTen(rs.getString(25));
                chiTietSanPham.setCoTay(coTay);

                chiTietSanPham.setSoLuong(rs.getInt(26));
                chiTietSanPham.setGia(rs.getFloat(27));
                chiTietSanPhams.add(chiTietSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietSanPhams;
    }
    
    public ArrayList<ChiTietSanPham> searchBySize(String ten) {
        ArrayList<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
        String sql = "select a.id_chi_tiet_san_pham, b.id_san_pham, b.ma, b.ten, \n"
                + "              c.id_mau_sac, c.ma, c.ten, \n"
                + "		d.id_hang, d.ma, d.ten, \n"
                + "		e.id_dang_ao, e.ma, e.ten, \n"
                + "		f.id_size, f.ma, f.ten, \n"
                + "		g.id_chat_lieu, d.ma, g.ten, \n"
                + "		h.id_co_ao, h.ma, h.ten, \n"
                + "		m.id_co_tay, m.ma, m.ten, \n"
                + "		so_luong_ton, gia from chi_tiet_san_pham a\n"
                + "		join san_pham b on a.id_san_pham = b.id_san_pham\n"
                + "		join mau_sac c on a.id_mau_sac = c.id_mau_sac\n"
                + "		join hang d on a.id_hang = d.id_hang\n"
                + "		join dang_ao e on a.id_dang_ao = e.id_dang_ao\n"
                + "		join size f on a.id_size = f.id_size\n"
                + "		join chat_lieu g on a.id_chat_lieu = g.id_chat_lieu\n"
                + "		join co_ao h on a.id_co_ao = h.id_co_ao\n"
                + "		join co_tay m on a.id_co_tay = m.id_co_tay\n"
                + "             where f.ten like N'%"+ten+"%'";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                DangAo dangAo = new DangAo();
                Size size = new Size();
                ChatLieu chatLieu = new ChatLieu();
                CoAo coAo = new CoAo();
                CoTay coTay = new CoTay();
//                HinhAnh hinhAnh = new HinhAnh();

                chiTietSanPham.setId(rs.getInt(1));

                sanPham.setId(rs.getInt(2));
                sanPham.setMa(rs.getString(3));
                sanPham.setTen(rs.getString(4));
                chiTietSanPham.setSanPham(sanPham);

                mauSac.setId(rs.getInt(5));
                mauSac.setMa(rs.getString(6));
                mauSac.setTen(rs.getString(7));
                chiTietSanPham.setMauSac(mauSac);

                hang.setId(rs.getInt(8));
                hang.setMa(rs.getString(9));
                hang.setTen(rs.getString(10));
                chiTietSanPham.setHang(hang);

                dangAo.setId(rs.getInt(11));
                dangAo.setMa(rs.getString(12));
                dangAo.setTen(rs.getString(13));
                chiTietSanPham.setDangAo(dangAo);

                size.setId(rs.getInt(14));
                size.setMa(rs.getString(15));
                size.setTen(rs.getString(16));
                chiTietSanPham.setSize(size);

                chatLieu.setId(rs.getInt(17));
                chatLieu.setMa(rs.getString(18));
                chatLieu.setTen(rs.getString(19));
                chiTietSanPham.setChatLieu(chatLieu);

                coAo.setId(rs.getInt(20));
                coAo.setMa(rs.getString(21));
                coAo.setTen(rs.getString(22));
                chiTietSanPham.setCoAo(coAo);

                coTay.setId(rs.getInt(23));
                coTay.setMa(rs.getString(24));
                coTay.setTen(rs.getString(25));
                chiTietSanPham.setCoTay(coTay);

                chiTietSanPham.setSoLuong(rs.getInt(26));
                chiTietSanPham.setGia(rs.getFloat(27));
                chiTietSanPhams.add(chiTietSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietSanPhams;
    }
    
    public ArrayList<ChiTietSanPham> searchByChatLieu(String ten) {
        ArrayList<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
        String sql = "select a.id_chi_tiet_san_pham, b.id_san_pham, b.ma, b.ten, \n"
                + "              c.id_mau_sac, c.ma, c.ten, \n"
                + "		d.id_hang, d.ma, d.ten, \n"
                + "		e.id_dang_ao, e.ma, e.ten, \n"
                + "		f.id_size, f.ma, f.ten, \n"
                + "		g.id_chat_lieu, d.ma, g.ten, \n"
                + "		h.id_co_ao, h.ma, h.ten, \n"
                + "		m.id_co_tay, m.ma, m.ten, \n"
                + "		so_luong_ton, gia from chi_tiet_san_pham a\n"
                + "		join san_pham b on a.id_san_pham = b.id_san_pham\n"
                + "		join mau_sac c on a.id_mau_sac = c.id_mau_sac\n"
                + "		join hang d on a.id_hang = d.id_hang\n"
                + "		join dang_ao e on a.id_dang_ao = e.id_dang_ao\n"
                + "		join size f on a.id_size = f.id_size\n"
                + "		join chat_lieu g on a.id_chat_lieu = g.id_chat_lieu\n"
                + "		join co_ao h on a.id_co_ao = h.id_co_ao\n"
                + "		join co_tay m on a.id_co_tay = m.id_co_tay\n"
                + "             where g.ten like N'%"+ten+"%'";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                DangAo dangAo = new DangAo();
                Size size = new Size();
                ChatLieu chatLieu = new ChatLieu();
                CoAo coAo = new CoAo();
                CoTay coTay = new CoTay();
//                HinhAnh hinhAnh = new HinhAnh();

                chiTietSanPham.setId(rs.getInt(1));

                sanPham.setId(rs.getInt(2));
                sanPham.setMa(rs.getString(3));
                sanPham.setTen(rs.getString(4));
                chiTietSanPham.setSanPham(sanPham);

                mauSac.setId(rs.getInt(5));
                mauSac.setMa(rs.getString(6));
                mauSac.setTen(rs.getString(7));
                chiTietSanPham.setMauSac(mauSac);

                hang.setId(rs.getInt(8));
                hang.setMa(rs.getString(9));
                hang.setTen(rs.getString(10));
                chiTietSanPham.setHang(hang);

                dangAo.setId(rs.getInt(11));
                dangAo.setMa(rs.getString(12));
                dangAo.setTen(rs.getString(13));
                chiTietSanPham.setDangAo(dangAo);

                size.setId(rs.getInt(14));
                size.setMa(rs.getString(15));
                size.setTen(rs.getString(16));
                chiTietSanPham.setSize(size);

                chatLieu.setId(rs.getInt(17));
                chatLieu.setMa(rs.getString(18));
                chatLieu.setTen(rs.getString(19));
                chiTietSanPham.setChatLieu(chatLieu);

                coAo.setId(rs.getInt(20));
                coAo.setMa(rs.getString(21));
                coAo.setTen(rs.getString(22));
                chiTietSanPham.setCoAo(coAo);

                coTay.setId(rs.getInt(23));
                coTay.setMa(rs.getString(24));
                coTay.setTen(rs.getString(25));
                chiTietSanPham.setCoTay(coTay);

                chiTietSanPham.setSoLuong(rs.getInt(26));
                chiTietSanPham.setGia(rs.getFloat(27));
                chiTietSanPhams.add(chiTietSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietSanPhams;
    }
    
    
    public ArrayList<ChiTietSanPham> getListChiTietByHang(String tenHang) {
        ArrayList<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
        String sql = "select a.id_chi_tiet_san_pham, b.id_san_pham, b.ma, b.ten, \n"
                + "              c.id_mau_sac, c.ma, c.ten, \n"
                + "		d.id_hang, d.ma, d.ten, \n"
                + "		e.id_dang_ao, e.ma, e.ten, \n"
                + "		f.id_size, f.ma, f.ten, \n"
                + "		g.id_chat_lieu, d.ma, g.ten, \n"
                + "		h.id_co_ao, h.ma, h.ten, \n"
                + "		m.id_co_tay, m.ma, m.ten, \n"
                + "		so_luong_ton, gia from chi_tiet_san_pham a\n"
                + "		join san_pham b on a.id_san_pham = b.id_san_pham\n"
                + "		join mau_sac c on a.id_mau_sac = c.id_mau_sac\n"
                + "		join hang d on a.id_hang = d.id_hang\n"
                + "		join dang_ao e on a.id_dang_ao = e.id_dang_ao\n"
                + "		join size f on a.id_size = f.id_size\n"
                + "		join chat_lieu g on a.id_chat_lieu = g.id_chat_lieu\n"
                + "		join co_ao h on a.id_co_ao = h.id_co_ao\n"
                + "		join co_tay m on a.id_co_tay = m.id_co_tay\n"
                + "             where d.ten like ?";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tenHang);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                DangAo dangAo = new DangAo();
                Size size = new Size();
                ChatLieu chatLieu = new ChatLieu();
                CoAo coAo = new CoAo();
                CoTay coTay = new CoTay();
//                HinhAnh hinhAnh = new HinhAnh();

                chiTietSanPham.setId(rs.getInt(1));

                sanPham.setId(rs.getInt(2));
                sanPham.setMa(rs.getString(3));
                sanPham.setTen(rs.getString(4));
                chiTietSanPham.setSanPham(sanPham);

                mauSac.setId(rs.getInt(5));
                mauSac.setMa(rs.getString(6));
                mauSac.setTen(rs.getString(7));
                chiTietSanPham.setMauSac(mauSac);

                hang.setId(rs.getInt(8));
                hang.setMa(rs.getString(9));
                hang.setTen(rs.getString(10));
                chiTietSanPham.setHang(hang);

                dangAo.setId(rs.getInt(11));
                dangAo.setMa(rs.getString(12));
                dangAo.setTen(rs.getString(13));
                chiTietSanPham.setDangAo(dangAo);

                size.setId(rs.getInt(14));
                size.setMa(rs.getString(15));
                size.setTen(rs.getString(16));
                chiTietSanPham.setSize(size);

                chatLieu.setId(rs.getInt(17));
                chatLieu.setMa(rs.getString(18));
                chatLieu.setTen(rs.getString(19));
                chiTietSanPham.setChatLieu(chatLieu);

                coAo.setId(rs.getInt(20));
                coAo.setMa(rs.getString(21));
                coAo.setTen(rs.getString(22));
                chiTietSanPham.setCoAo(coAo);

                coTay.setId(rs.getInt(23));
                coTay.setMa(rs.getString(24));
                coTay.setTen(rs.getString(25));
                chiTietSanPham.setCoTay(coTay);

                chiTietSanPham.setSoLuong(rs.getInt(26));
                chiTietSanPham.setGia(rs.getFloat(27));
                chiTietSanPhams.add(chiTietSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietSanPhams;
    }

    public ArrayList<ChiTietSanPham> getListChiTietChatLieu(String tenChatLieu) {
        ArrayList<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
        String sql = "select a.id_chi_tiet_san_pham, b.id_san_pham, b.ma, b.ten, \n"
                + "              c.id_mau_sac, c.ma, c.ten, \n"
                + "		d.id_hang, d.ma, d.ten, \n"
                + "		e.id_dang_ao, e.ma, e.ten, \n"
                + "		f.id_size, f.ma, f.ten, \n"
                + "		g.id_chat_lieu, d.ma, g.ten, \n"
                + "		h.id_co_ao, h.ma, h.ten, \n"
                + "		m.id_co_tay, m.ma, m.ten, \n"
                + "		so_luong_ton, gia from chi_tiet_san_pham a\n"
                + "		join san_pham b on a.id_san_pham = b.id_san_pham\n"
                + "		join mau_sac c on a.id_mau_sac = c.id_mau_sac\n"
                + "		join hang d on a.id_hang = d.id_hang\n"
                + "		join dang_ao e on a.id_dang_ao = e.id_dang_ao\n"
                + "		join size f on a.id_size = f.id_size\n"
                + "		join chat_lieu g on a.id_chat_lieu = g.id_chat_lieu\n"
                + "		join co_ao h on a.id_co_ao = h.id_co_ao\n"
                + "		join co_tay m on a.id_co_tay = m.id_co_tay\n"
                + "             where g.ten like ?";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tenChatLieu);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                DangAo dangAo = new DangAo();
                Size size = new Size();
                ChatLieu chatLieu = new ChatLieu();
                CoAo coAo = new CoAo();
                CoTay coTay = new CoTay();
//                HinhAnh hinhAnh = new HinhAnh();

                chiTietSanPham.setId(rs.getInt(1));

                sanPham.setId(rs.getInt(2));
                sanPham.setMa(rs.getString(3));
                sanPham.setTen(rs.getString(4));
                chiTietSanPham.setSanPham(sanPham);

                mauSac.setId(rs.getInt(5));
                mauSac.setMa(rs.getString(6));
                mauSac.setTen(rs.getString(7));
                chiTietSanPham.setMauSac(mauSac);

                hang.setId(rs.getInt(8));
                hang.setMa(rs.getString(9));
                hang.setTen(rs.getString(10));
                chiTietSanPham.setHang(hang);

                dangAo.setId(rs.getInt(11));
                dangAo.setMa(rs.getString(12));
                dangAo.setTen(rs.getString(13));
                chiTietSanPham.setDangAo(dangAo);

                size.setId(rs.getInt(14));
                size.setMa(rs.getString(15));
                size.setTen(rs.getString(16));
                chiTietSanPham.setSize(size);

                chatLieu.setId(rs.getInt(17));
                chatLieu.setMa(rs.getString(18));
                chatLieu.setTen(rs.getString(19));
                chiTietSanPham.setChatLieu(chatLieu);

                coAo.setId(rs.getInt(20));
                coAo.setMa(rs.getString(21));
                coAo.setTen(rs.getString(22));
                chiTietSanPham.setCoAo(coAo);

                coTay.setId(rs.getInt(23));
                coTay.setMa(rs.getString(24));
                coTay.setTen(rs.getString(25));
                chiTietSanPham.setCoTay(coTay);

                chiTietSanPham.setSoLuong(rs.getInt(26));
                chiTietSanPham.setGia(rs.getFloat(27));
                chiTietSanPhams.add(chiTietSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietSanPhams;
    }

    public ArrayList<ChiTietSanPham> getListChiTietByDangAo(String tenDangAo) {
        ArrayList<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
        String sql = "select a.id_chi_tiet_san_pham, b.id_san_pham, b.ma, b.ten, \n"
                + "              c.id_mau_sac, c.ma, c.ten, \n"
                + "		d.id_hang, d.ma, d.ten, \n"
                + "		e.id_dang_ao, e.ma, e.ten, \n"
                + "		f.id_size, f.ma, f.ten, \n"
                + "		g.id_chat_lieu, d.ma, g.ten, \n"
                + "		h.id_co_ao, h.ma, h.ten, \n"
                + "		m.id_co_tay, m.ma, m.ten, \n"
                + "		so_luong_ton, gia from chi_tiet_san_pham a\n"
                + "		join san_pham b on a.id_san_pham = b.id_san_pham\n"
                + "		join mau_sac c on a.id_mau_sac = c.id_mau_sac\n"
                + "		join hang d on a.id_hang = d.id_hang\n"
                + "		join dang_ao e on a.id_dang_ao = e.id_dang_ao\n"
                + "		join size f on a.id_size = f.id_size\n"
                + "		join chat_lieu g on a.id_chat_lieu = g.id_chat_lieu\n"
                + "		join co_ao h on a.id_co_ao = h.id_co_ao\n"
                + "		join co_tay m on a.id_co_tay = m.id_co_tay\n"
                + "             where e.ten like ?";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tenDangAo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                DangAo dangAo = new DangAo();
                Size size = new Size();
                ChatLieu chatLieu = new ChatLieu();
                CoAo coAo = new CoAo();
                CoTay coTay = new CoTay();
//                HinhAnh hinhAnh = new HinhAnh();

                chiTietSanPham.setId(rs.getInt(1));

                sanPham.setId(rs.getInt(2));
                sanPham.setMa(rs.getString(3));
                sanPham.setTen(rs.getString(4));
                chiTietSanPham.setSanPham(sanPham);

                mauSac.setId(rs.getInt(5));
                mauSac.setMa(rs.getString(6));
                mauSac.setTen(rs.getString(7));
                chiTietSanPham.setMauSac(mauSac);

                hang.setId(rs.getInt(8));
                hang.setMa(rs.getString(9));
                hang.setTen(rs.getString(10));
                chiTietSanPham.setHang(hang);

                dangAo.setId(rs.getInt(11));
                dangAo.setMa(rs.getString(12));
                dangAo.setTen(rs.getString(13));
                chiTietSanPham.setDangAo(dangAo);

                size.setId(rs.getInt(14));
                size.setMa(rs.getString(15));
                size.setTen(rs.getString(16));
                chiTietSanPham.setSize(size);

                chatLieu.setId(rs.getInt(17));
                chatLieu.setMa(rs.getString(18));
                chatLieu.setTen(rs.getString(19));
                chiTietSanPham.setChatLieu(chatLieu);

                coAo.setId(rs.getInt(20));
                coAo.setMa(rs.getString(21));
                coAo.setTen(rs.getString(22));
                chiTietSanPham.setCoAo(coAo);

                coTay.setId(rs.getInt(23));
                coTay.setMa(rs.getString(24));
                coTay.setTen(rs.getString(25));
                chiTietSanPham.setCoTay(coTay);

                chiTietSanPham.setSoLuong(rs.getInt(26));
                chiTietSanPham.setGia(rs.getFloat(27));
                chiTietSanPhams.add(chiTietSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietSanPhams;
    }

    public ArrayList<ChiTietSanPham> getListChiTietBySize(String tenDangAo) {
        ArrayList<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
        String sql = "select a.id_chi_tiet_san_pham, b.id_san_pham, b.ma, b.ten, \n"
                + "              c.id_mau_sac, c.ma, c.ten, \n"
                + "		d.id_hang, d.ma, d.ten, \n"
                + "		e.id_dang_ao, e.ma, e.ten, \n"
                + "		f.id_size, f.ma, f.ten, \n"
                + "		g.id_chat_lieu, d.ma, g.ten, \n"
                + "		h.id_co_ao, h.ma, h.ten, \n"
                + "		m.id_co_tay, m.ma, m.ten, \n"
                + "		so_luong_ton, gia from chi_tiet_san_pham a\n"
                + "		join san_pham b on a.id_san_pham = b.id_san_pham\n"
                + "		join mau_sac c on a.id_mau_sac = c.id_mau_sac\n"
                + "		join hang d on a.id_hang = d.id_hang\n"
                + "		join dang_ao e on a.id_dang_ao = e.id_dang_ao\n"
                + "		join size f on a.id_size = f.id_size\n"
                + "		join chat_lieu g on a.id_chat_lieu = g.id_chat_lieu\n"
                + "		join co_ao h on a.id_co_ao = h.id_co_ao\n"
                + "		join co_tay m on a.id_co_tay = m.id_co_tay\n"
                + "             where f.ten like ?";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tenDangAo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                DangAo dangAo = new DangAo();
                Size size = new Size();
                ChatLieu chatLieu = new ChatLieu();
                CoAo coAo = new CoAo();
                CoTay coTay = new CoTay();
//                HinhAnh hinhAnh = new HinhAnh();

                chiTietSanPham.setId(rs.getInt(1));

                sanPham.setId(rs.getInt(2));
                sanPham.setMa(rs.getString(3));
                sanPham.setTen(rs.getString(4));
                chiTietSanPham.setSanPham(sanPham);

                mauSac.setId(rs.getInt(5));
                mauSac.setMa(rs.getString(6));
                mauSac.setTen(rs.getString(7));
                chiTietSanPham.setMauSac(mauSac);

                hang.setId(rs.getInt(8));
                hang.setMa(rs.getString(9));
                hang.setTen(rs.getString(10));
                chiTietSanPham.setHang(hang);

                dangAo.setId(rs.getInt(11));
                dangAo.setMa(rs.getString(12));
                dangAo.setTen(rs.getString(13));
                chiTietSanPham.setDangAo(dangAo);

                size.setId(rs.getInt(14));
                size.setMa(rs.getString(15));
                size.setTen(rs.getString(16));
                chiTietSanPham.setSize(size);

                chatLieu.setId(rs.getInt(17));
                chatLieu.setMa(rs.getString(18));
                chatLieu.setTen(rs.getString(19));
                chiTietSanPham.setChatLieu(chatLieu);

                coAo.setId(rs.getInt(20));
                coAo.setMa(rs.getString(21));
                coAo.setTen(rs.getString(22));
                chiTietSanPham.setCoAo(coAo);

                coTay.setId(rs.getInt(23));
                coTay.setMa(rs.getString(24));
                coTay.setTen(rs.getString(25));
                chiTietSanPham.setCoTay(coTay);

                chiTietSanPham.setSoLuong(rs.getInt(26));
                chiTietSanPham.setGia(rs.getFloat(27));
                chiTietSanPhams.add(chiTietSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietSanPhams;
    }

    public ArrayList<ChiTietSanPham> getListChiTietByMauSac(String tenDangAo) {
        ArrayList<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
        String sql = "select a.id_chi_tiet_san_pham, b.id_san_pham, b.ma, b.ten, \n"
                + "              c.id_mau_sac, c.ma, c.ten, \n"
                + "		d.id_hang, d.ma, d.ten, \n"
                + "		e.id_dang_ao, e.ma, e.ten, \n"
                + "		f.id_size, f.ma, f.ten, \n"
                + "		g.id_chat_lieu, d.ma, g.ten, \n"
                + "		h.id_co_ao, h.ma, h.ten, \n"
                + "		m.id_co_tay, m.ma, m.ten, \n"
                + "		so_luong_ton, gia from chi_tiet_san_pham a\n"
                + "		join san_pham b on a.id_san_pham = b.id_san_pham\n"
                + "		join mau_sac c on a.id_mau_sac = c.id_mau_sac\n"
                + "		join hang d on a.id_hang = d.id_hang\n"
                + "		join dang_ao e on a.id_dang_ao = e.id_dang_ao\n"
                + "		join size f on a.id_size = f.id_size\n"
                + "		join chat_lieu g on a.id_chat_lieu = g.id_chat_lieu\n"
                + "		join co_ao h on a.id_co_ao = h.id_co_ao\n"
                + "		join co_tay m on a.id_co_tay = m.id_co_tay\n"
                + "             where c.ten like ?";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tenDangAo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                DangAo dangAo = new DangAo();
                Size size = new Size();
                ChatLieu chatLieu = new ChatLieu();
                CoAo coAo = new CoAo();
                CoTay coTay = new CoTay();
//                HinhAnh hinhAnh = new HinhAnh();

                chiTietSanPham.setId(rs.getInt(1));

                sanPham.setId(rs.getInt(2));
                sanPham.setMa(rs.getString(3));
                sanPham.setTen(rs.getString(4));
                chiTietSanPham.setSanPham(sanPham);

                mauSac.setId(rs.getInt(5));
                mauSac.setMa(rs.getString(6));
                mauSac.setTen(rs.getString(7));
                chiTietSanPham.setMauSac(mauSac);

                hang.setId(rs.getInt(8));
                hang.setMa(rs.getString(9));
                hang.setTen(rs.getString(10));
                chiTietSanPham.setHang(hang);

                dangAo.setId(rs.getInt(11));
                dangAo.setMa(rs.getString(12));
                dangAo.setTen(rs.getString(13));
                chiTietSanPham.setDangAo(dangAo);

                size.setId(rs.getInt(14));
                size.setMa(rs.getString(15));
                size.setTen(rs.getString(16));
                chiTietSanPham.setSize(size);

                chatLieu.setId(rs.getInt(17));
                chatLieu.setMa(rs.getString(18));
                chatLieu.setTen(rs.getString(19));
                chiTietSanPham.setChatLieu(chatLieu);

                coAo.setId(rs.getInt(20));
                coAo.setMa(rs.getString(21));
                coAo.setTen(rs.getString(22));
                chiTietSanPham.setCoAo(coAo);

                coTay.setId(rs.getInt(23));
                coTay.setMa(rs.getString(24));
                coTay.setTen(rs.getString(25));
                chiTietSanPham.setCoTay(coTay);

                chiTietSanPham.setSoLuong(rs.getInt(26));
                chiTietSanPham.setGia(rs.getFloat(27));
                chiTietSanPhams.add(chiTietSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietSanPhams;
    }

    public ArrayList<ChiTietSanPham> getListChiTietByCoAo(String tenDangAo) {
        ArrayList<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
        String sql = "select a.id_chi_tiet_san_pham, b.id_san_pham, b.ma, b.ten, \n"
                + "              c.id_mau_sac, c.ma, c.ten, \n"
                + "		d.id_hang, d.ma, d.ten, \n"
                + "		e.id_dang_ao, e.ma, e.ten, \n"
                + "		f.id_size, f.ma, f.ten, \n"
                + "		g.id_chat_lieu, d.ma, g.ten, \n"
                + "		h.id_co_ao, h.ma, h.ten, \n"
                + "		m.id_co_tay, m.ma, m.ten, \n"
                + "		so_luong_ton, gia from chi_tiet_san_pham a\n"
                + "		join san_pham b on a.id_san_pham = b.id_san_pham\n"
                + "		join mau_sac c on a.id_mau_sac = c.id_mau_sac\n"
                + "		join hang d on a.id_hang = d.id_hang\n"
                + "		join dang_ao e on a.id_dang_ao = e.id_dang_ao\n"
                + "		join size f on a.id_size = f.id_size\n"
                + "		join chat_lieu g on a.id_chat_lieu = g.id_chat_lieu\n"
                + "		join co_ao h on a.id_co_ao = h.id_co_ao\n"
                + "		join co_tay m on a.id_co_tay = m.id_co_tay\n"
                + "             where h.ten like ?";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tenDangAo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                DangAo dangAo = new DangAo();
                Size size = new Size();
                ChatLieu chatLieu = new ChatLieu();
                CoAo coAo = new CoAo();
                CoTay coTay = new CoTay();
//                HinhAnh hinhAnh = new HinhAnh();

                chiTietSanPham.setId(rs.getInt(1));

                sanPham.setId(rs.getInt(2));
                sanPham.setMa(rs.getString(3));
                sanPham.setTen(rs.getString(4));
                chiTietSanPham.setSanPham(sanPham);

                mauSac.setId(rs.getInt(5));
                mauSac.setMa(rs.getString(6));
                mauSac.setTen(rs.getString(7));
                chiTietSanPham.setMauSac(mauSac);

                hang.setId(rs.getInt(8));
                hang.setMa(rs.getString(9));
                hang.setTen(rs.getString(10));
                chiTietSanPham.setHang(hang);

                dangAo.setId(rs.getInt(11));
                dangAo.setMa(rs.getString(12));
                dangAo.setTen(rs.getString(13));
                chiTietSanPham.setDangAo(dangAo);

                size.setId(rs.getInt(14));
                size.setMa(rs.getString(15));
                size.setTen(rs.getString(16));
                chiTietSanPham.setSize(size);

                chatLieu.setId(rs.getInt(17));
                chatLieu.setMa(rs.getString(18));
                chatLieu.setTen(rs.getString(19));
                chiTietSanPham.setChatLieu(chatLieu);

                coAo.setId(rs.getInt(20));
                coAo.setMa(rs.getString(21));
                coAo.setTen(rs.getString(22));
                chiTietSanPham.setCoAo(coAo);

                coTay.setId(rs.getInt(23));
                coTay.setMa(rs.getString(24));
                coTay.setTen(rs.getString(25));
                chiTietSanPham.setCoTay(coTay);

                chiTietSanPham.setSoLuong(rs.getInt(26));
                chiTietSanPham.setGia(rs.getFloat(27));
                chiTietSanPhams.add(chiTietSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietSanPhams;
    }

    public ArrayList<ChiTietSanPham> getListChiTietByCoTay(String tenDangAo) {
        ArrayList<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
        String sql = "select a.id_chi_tiet_san_pham, b.id_san_pham, b.ma, b.ten, \n"
                + "              c.id_mau_sac, c.ma, c.ten, \n"
                + "		d.id_hang, d.ma, d.ten, \n"
                + "		e.id_dang_ao, e.ma, e.ten, \n"
                + "		f.id_size, f.ma, f.ten, \n"
                + "		g.id_chat_lieu, d.ma, g.ten, \n"
                + "		h.id_co_ao, h.ma, h.ten, \n"
                + "		m.id_co_tay, m.ma, m.ten, \n"
                + "		so_luong_ton, gia from chi_tiet_san_pham a\n"
                + "		join san_pham b on a.id_san_pham = b.id_san_pham\n"
                + "		join mau_sac c on a.id_mau_sac = c.id_mau_sac\n"
                + "		join hang d on a.id_hang = d.id_hang\n"
                + "		join dang_ao e on a.id_dang_ao = e.id_dang_ao\n"
                + "		join size f on a.id_size = f.id_size\n"
                + "		join chat_lieu g on a.id_chat_lieu = g.id_chat_lieu\n"
                + "		join co_ao h on a.id_co_ao = h.id_co_ao\n"
                + "		join co_tay m on a.id_co_tay = m.id_co_tay\n"
                + "             where m.ten like ?";
        // + "join anh on chi_tiet_san_pham.id_hinh_anh = anh.id_hinh_anh ";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tenDangAo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                MauSac mauSac = new MauSac();
                Hang hang = new Hang();
                DangAo dangAo = new DangAo();
                Size size = new Size();
                ChatLieu chatLieu = new ChatLieu();
                CoAo coAo = new CoAo();
                CoTay coTay = new CoTay();
//                HinhAnh hinhAnh = new HinhAnh();

                chiTietSanPham.setId(rs.getInt(1));

                sanPham.setId(rs.getInt(2));
                sanPham.setMa(rs.getString(3));
                sanPham.setTen(rs.getString(4));
                chiTietSanPham.setSanPham(sanPham);

                mauSac.setId(rs.getInt(5));
                mauSac.setMa(rs.getString(6));
                mauSac.setTen(rs.getString(7));
                chiTietSanPham.setMauSac(mauSac);

                hang.setId(rs.getInt(8));
                hang.setMa(rs.getString(9));
                hang.setTen(rs.getString(10));
                chiTietSanPham.setHang(hang);

                dangAo.setId(rs.getInt(11));
                dangAo.setMa(rs.getString(12));
                dangAo.setTen(rs.getString(13));
                chiTietSanPham.setDangAo(dangAo);

                size.setId(rs.getInt(14));
                size.setMa(rs.getString(15));
                size.setTen(rs.getString(16));
                chiTietSanPham.setSize(size);

                chatLieu.setId(rs.getInt(17));
                chatLieu.setMa(rs.getString(18));
                chatLieu.setTen(rs.getString(19));
                chiTietSanPham.setChatLieu(chatLieu);

                coAo.setId(rs.getInt(20));
                coAo.setMa(rs.getString(21));
                coAo.setTen(rs.getString(22));
                chiTietSanPham.setCoAo(coAo);

                coTay.setId(rs.getInt(23));
                coTay.setMa(rs.getString(24));
                coTay.setTen(rs.getString(25));
                chiTietSanPham.setCoTay(coTay);

                chiTietSanPham.setSoLuong(rs.getInt(26));
                chiTietSanPham.setGia(rs.getFloat(27));
                chiTietSanPhams.add(chiTietSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietSanPhams;
    }

    public int countDB() {
        int rowCount = 0;
        String sql = "SELECT COUNT(*) FROM chi_tiet_san_pham";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rowCount = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    public Map<String, Integer> countSanPham() {
        Map<String, Integer> soLuongTheoTen = new HashMap<>();
        String sql = "SELECT b.ten AS ten_san_pham, COUNT(*) AS so_luong "
                + "FROM chi_tiet_san_pham a "
                + "JOIN san_pham b ON a.id_san_pham = b.id_san_pham "
                + "GROUP BY b.ten";

        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ten = rs.getString("ten_san_pham");
                int soLuong = rs.getInt("so_luong");
                soLuongTheoTen.put(ten, soLuong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return soLuongTheoTen;
    }
    

    public static void main(String[] args) {
        ArrayList<ChiTietSanPham> list = new ChiTietSanPhamRepository().getListChiTietByCoAo("Cổ áo sơ mi xòe");
        System.out.println(list);
    }

}
