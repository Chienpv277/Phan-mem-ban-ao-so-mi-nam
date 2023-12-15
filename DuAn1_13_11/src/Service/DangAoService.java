/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.DangAo;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public interface DangAoService {
    public ArrayList<DangAo> getListDangAo();
    public DangAo getDangAoByID(int id);
    public DangAo getIdByTenDangAo(String ten);
    public Boolean add(DangAo dangAo);
    public Boolean update(Integer id, DangAo dangAo);
    public Boolean delete(Integer id);
}
