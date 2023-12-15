/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModel.ChatLieu;
import Utilities.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChatLieuRepository {

    private DBConnection connection;

    public ArrayList<ChatLieu> getListChatLieu() {
        ArrayList<ChatLieu> list = new ArrayList<>();
        String sql = "select id_chat_lieu, ma, ten from chat_lieu"
                + " order by id_chat_lieu desc";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu chatLieu = new ChatLieu();
                chatLieu.setId(rs.getInt(1));
                chatLieu.setMa(rs.getString(2));
                chatLieu.setTen(rs.getString(3));
                list.add(chatLieu);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public ChatLieu getChatLieuById(int id) {
        ChatLieu chatLieu = null;
        String sql = "select id_chat_lieu, ma, ten from chat_lieu where id_chat_lieu = ?";

        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                chatLieu = new ChatLieu();
                chatLieu.setId(rs.getInt(1));
                chatLieu.setMa(rs.getString(2));
                chatLieu.setTen(rs.getString(3));
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return chatLieu;
    }

    public Boolean add(ChatLieu chatLieu) {
        String sql = "insert into chat_lieu(ma, ten) values (?, ?)";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, chatLieu.getMa());
            ps.setObject(2, chatLieu.getTen());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public Boolean update(Integer id, ChatLieu chatLieu) {
        String sql = "update chat_lieu set ma = ?, ten = ?  where id_chat_lieu = ?";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, chatLieu.getMa());
            ps.setObject(2, chatLieu.getTen());
            ps.setObject(3, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public Boolean delete(Integer id) {
        String sql = "delete from chat_lieu where id_chat_lieu = ?";
        try ( Connection con = connection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public ChatLieu getIdByTenChatLieu(String ten) {
    ChatLieu chatLieu = null;
    String sql = "SELECT id_chat_lieu FROM chat_lieu WHERE ten = ?";
    try (Connection con = connection.getConnection();  
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, ten);  // Sử dụng tham số 'ten' được cung cấp
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            chatLieu = new ChatLieu();
            chatLieu.setId(rs.getInt(1));
        }
    } catch (Exception e) {
        e.getMessage();  // Xử lý ngoại lệ một cách thích hợp, có thể ghi log nó
    }
    return chatLieu;
}
    

    public static void main(String[] args) {
        ChatLieu chatLieu = new ChatLieuRepository().getIdByTenChatLieu("Denin");

        System.out.println(chatLieu);

    }
}
