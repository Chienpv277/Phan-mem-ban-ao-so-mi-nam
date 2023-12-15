/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mode;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ngoc
 */
public class TableMode extends DefaultTableModel {

    public TableMode() {
        super();
        this.addColumn("STT");
//        this.addColumn("ID");
        this.addColumn("Khach Hang");
        this.addColumn("Nhan vien");
        this.addColumn("Tong tien");
        this.addColumn("Hinh thuc thanh toan");
        this.addColumn("Ngay Tao");
        this.addColumn("Ngay Thanh Toan");
        this.addColumn("Trang thai");

//        this.addColumn("Khuyen mai");
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Class getColumnClass(int column) {
        switch (column) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            default:
                return String.class;
        }
    }
}
