/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.CoTay;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public interface CoTayService {

    public ArrayList<CoTay> getListCoTay();

    public CoTay getCoTayByID(int id);

    public CoTay getIdByTenCoTay(String ten);

    public Boolean add(CoTay coTay);

    public Boolean update(Integer id, CoTay coTay);

    public Boolean delete(Integer id);
}
