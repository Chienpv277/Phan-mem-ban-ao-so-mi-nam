/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.CoAo;
import DomainModel.CoAo;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public interface CoAoService {
    public ArrayList<CoAo> getListCoAo();
    public CoAo getCoAoByID(int id);
    public CoAo getIdByTenCoAo(String ten);
    public Boolean add(CoAo coAo);
    public Boolean update(Integer id, CoAo coAo);
    public Boolean delete(Integer id);
}
