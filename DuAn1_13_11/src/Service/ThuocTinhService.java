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
public interface ThuocTinhService {
    public ArrayList<MauSac> getListMauSac();
    public Boolean addMauSac(MauSac mauSac);
    public Boolean updateMauSac(int id, MauSac mauSac);
    
}
