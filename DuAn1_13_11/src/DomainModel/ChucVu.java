/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

/**
 *
 * @author ADAMIN
 */
public class ChucVu {
     private int id_chuc_vu;
     private String ma;
     private String ten;

    public ChucVu() {
    }

    public ChucVu(int id_chuc_vu, String ma, String ten) {
        this.id_chuc_vu = id_chuc_vu;
        this.ma = ma;
        this.ten = ten;
    }

    public int getId_chuc_vu() {
        return id_chuc_vu;
    }

    public String getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public void setId_chuc_vu(int id_chuc_vu) {
        this.id_chuc_vu = id_chuc_vu;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Override
    public String toString() {
        return "ChucVu{" + "id_chuc_vu=" + id_chuc_vu + ", ma=" + ma + ", ten=" + ten + '}';
    }
     
     
}
