/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceImpl;

import DomainModel.ChatLieu;
import Repository.ChatLieuRepository;
import Service.ChatLieuService;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public class ChatLieuServiceImpl implements ChatLieuService {

    private ChatLieuRepository repository = new ChatLieuRepository();

    @Override
    public ArrayList<ChatLieu> getListChatLieu() {
        return repository.getListChatLieu();
    }

    @Override
    public ChatLieu getChatLieuByID(int id) {
        return repository.getChatLieuById(id);
    }

    @Override
    public Boolean add(ChatLieu chatLieu) {
        return repository.add(chatLieu);
    }

    @Override
    public Boolean update(Integer id, ChatLieu chatLieu) {
        return repository.update(id, chatLieu);
    }

    @Override
    public Boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public ChatLieu getIdByTenChatLieu(String ten) {
        return repository.getIdByTenChatLieu(ten);
    }

}
