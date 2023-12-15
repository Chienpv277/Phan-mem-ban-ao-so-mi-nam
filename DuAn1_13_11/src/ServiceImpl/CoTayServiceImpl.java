/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceImpl;

import DomainModel.CoTay;
import Repository.CoTayRepository;
import Service.CoTayService;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public class CoTayServiceImpl implements CoTayService {
    
    private CoTayRepository repository = new CoTayRepository();
    
    @Override
    public ArrayList<CoTay> getListCoTay() {
        return repository.getListCoTay();
    }
    
    @Override
    public CoTay getCoTayByID(int id) {
        return repository.getCoTayById(id);
    }
    
    @Override
    public Boolean add(CoTay coTay) {
        return repository.add(coTay);
    }
    
    @Override
    public Boolean update(Integer id, CoTay coTay) {
        return repository.update(id, coTay);
    }
    
    @Override
    public Boolean delete(Integer id) {
        return repository.delete(id);
    }
    
    @Override
    public CoTay getIdByTenCoTay(String ten) {
        return repository.getIdByTencoTay(ten);
    }
}
