/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.ChatLieu;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public interface ChatLieuService {
    public ArrayList<ChatLieu> getListChatLieu();
    public ChatLieu getChatLieuByID(int id);
    public ChatLieu getIdByTenChatLieu(String ten);
    public Boolean add(ChatLieu chatLieu);
    public Boolean update(Integer id, ChatLieu chatLieu);
    public Boolean delete(Integer id);
}
