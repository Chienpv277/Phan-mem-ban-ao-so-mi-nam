/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceImpl;

import DomainModel.CoAo;
import Repository.CoAoRepository;
import Service.CoAoService;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public class CoAoServiceImpl implements CoAoService {

    private CoAoRepository repository = new CoAoRepository();

    @Override
    public ArrayList<CoAo> getListCoAo() {
        return repository.getListCoAo();
    }

    @Override
    public CoAo getCoAoByID(int id) {
        return repository.getCoAoById(id);
    }

    @Override
    public Boolean add(CoAo coAo) {
        return repository.add(coAo);
    }

    @Override
    public Boolean update(Integer id, CoAo coAo) {
        return repository.update(id, coAo);
    }

    @Override
    public Boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public CoAo getIdByTenCoAo(String ten) {
        return repository.getIdByTenCoAo(ten);
    }

}
