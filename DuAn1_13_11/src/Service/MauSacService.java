/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.MauSac;
import java.util.ArrayList;

/**
 *
 * @author FPTSHOP
 */
public interface MauSacService {
    public ArrayList<MauSac> getListMauSac();
    public MauSac getMauSacByID(int id);
    public MauSac getIdByTenMauSac(String ten);
    public Boolean add(MauSac mauSac);
    public Boolean update(Integer id, MauSac mauSac);
    public Boolean delete(Integer id);
}
