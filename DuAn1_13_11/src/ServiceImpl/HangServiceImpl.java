/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceImpl;

import DomainModel.Hang;
import Repository.HangRepository;
import Service.HangService;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public class HangServiceImpl implements HangService {

    private HangRepository repository = new HangRepository();

    @Override
    public ArrayList<Hang> getListHang() {
        return repository.getListHang();
    }

    @Override
    public Hang getHangByID(int id) {
        return repository.getHangById(id);
    }

    @Override
    public Boolean add(Hang hang) {
        return repository.add(hang);
    }

    @Override
    public Boolean update(Integer id, Hang hang) {
        return repository.update(id, hang);
    }

    @Override
    public Boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public Hang getIdByTenHang(String ten) {
         return repository.getIdByTenHang(ten);
    }

}
