/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import DomainModel.NhanVien_1;
import java.util.List;

/**
 *
 * @author ADAMIN
 */
public interface NhanVienService {

    List<NhanVien_1> getAll();

    String add(NhanVien_1 nhanVien);
    
    List<NhanVien_1> getAllNghi();
    
    String update(NhanVien_1 nhanVien, int id);

    List<NhanVien_1> searchByName(String hoTen);

    List<NhanVien_1> searchByEmail(String email);

    List<NhanVien_1> searchBySdt(String sdt);
}
