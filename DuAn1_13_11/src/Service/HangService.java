/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.Hang;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public interface HangService {
    public ArrayList<Hang> getListHang();
    public Hang getHangByID(int id);
    public Hang getIdByTenHang(String ten);
    public Boolean add(Hang hang);
    public Boolean update(Integer id, Hang hang);
    public Boolean delete(Integer id);
}
