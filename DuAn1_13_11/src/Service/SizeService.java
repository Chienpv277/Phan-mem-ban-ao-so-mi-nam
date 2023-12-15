/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.Size;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public interface SizeService {
    public ArrayList<Size> getListSize();
    public Size getSizeByID(int id);
    public Size getIdByTenSize(String ten);
    public Boolean add(Size size);
    public Boolean update(Integer id, Size size);
    public Boolean delete(Integer id);
}
