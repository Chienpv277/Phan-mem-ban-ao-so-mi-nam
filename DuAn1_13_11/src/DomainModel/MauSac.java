/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

import java.time.LocalDate;

/**
 *
 * @author FPTSHOP
 */
public class MauSac {
    private int id;
    
    private String ma;
    
    private String ten;
    
    private LocalDate created_at;
    
    private LocalDate updated_at;
    
    private String created_by;
    
    private String updated_by;

    public MauSac() {
    }

    public MauSac(int id, String ma, String ten, LocalDate created_at, LocalDate updated_at, String created_by, String updated_by) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.created_by = created_by;
        this.updated_by = updated_by;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    @Override
    public String toString() {
        return "ChatLieu{" + "id=" + id + ", ma=" + ma + ", ten=" + ten + '}';
    }
    
}
