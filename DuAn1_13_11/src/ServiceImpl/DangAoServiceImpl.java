/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceImpl;

import DomainModel.DangAo;
import Repository.DangAoRepository;
import Service.DangAoService;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public class DangAoServiceImpl implements DangAoService {

    private DangAoRepository repository = new DangAoRepository();

    @Override
    public ArrayList<DangAo> getListDangAo() {
        return repository.getListDangAo();
    }

    @Override
    public DangAo getDangAoByID(int id) {
        return repository.getDangAoById(id);
    }

    @Override
    public Boolean add(DangAo dangAo) {
        return repository.add(dangAo);
    }

    @Override
    public Boolean update(Integer id, DangAo dangAo) {
        return repository.update(id, dangAo);
    }

    @Override
    public Boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public DangAo getIdByTenDangAo(String ten) {
       return repository.getIdByTenDangAo(ten);
    }

}
