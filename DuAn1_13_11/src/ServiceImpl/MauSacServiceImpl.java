/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceImpl;

import DomainModel.MauSac;
import Repository.MauSacRepository;
import Service.MauSacService;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public class MauSacServiceImpl implements MauSacService {

    private MauSacRepository repository = new MauSacRepository();

    @Override
    public ArrayList<MauSac> getListMauSac() {
        return repository.getListMauSac();
    }

    @Override
    public MauSac getMauSacByID(int id) {
        return repository.getMauSacById(id);
    }

    @Override
    public Boolean add(MauSac mauSac) {
        return repository.add(mauSac);
    }

    @Override
    public Boolean update(Integer id, MauSac mauSac) {
        return repository.update(id, mauSac);
    }

    @Override
    public Boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public MauSac getIdByTenMauSac(String ten) {
         return repository.getIdByTenMauSac(ten);
    }

}
