/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceImpl;

import DomainModel.Size;
import Repository.SizeRepository;
import Service.SizeService;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public class SizeServiceImpl implements SizeService {

    private SizeRepository repository = new SizeRepository();

    @Override
    public ArrayList<Size> getListSize() {
        return repository.getListSize();
    }

    @Override
    public Size getSizeByID(int id) {
        return repository.getSizeById(id);
    }

    @Override
    public Boolean add(Size size) {
        return repository.add(size);
    }

    @Override
    public Boolean update(Integer id, Size size) {
        return repository.update(id, size);
    }

    @Override
    public Boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public Size getIdByTenSize(String ten) {
         return repository.getIdByTenSize(ten);
    }

}
