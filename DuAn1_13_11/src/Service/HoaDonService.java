/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DomainModel.HoaDon;
import Repository.HoaDonRepository;
import java.util.ArrayList;

/**
 *
 * @author 84386
 */
public class HoaDonService {

    HoaDonRepository repository;

    public HoaDonService() {
        this.repository = new HoaDonRepository();
    }

    public ArrayList<HoaDon> all() {
        return this.repository.all();
    }

}
