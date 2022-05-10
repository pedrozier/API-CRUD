/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author zier
 */
public class TableModel extends AbstractTableModel {

    private List<MyUser> users = new ArrayList<>();
    private String[] columnList = {"ID", "UserName", "Password"};

    public void addRow(List<MyUser> list) {
        if (!list.isEmpty()) {
            users.addAll(list);
            this.fireTableDataChanged();
        }
    }

    public void removeRow(List<MyUser> list) {
        users.removeAll(list);
        this.fireTableDataChanged();
    }

    public void clearTable() {
        users = new ArrayList<>();
        this.fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return columnList[column];
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return columnList.length;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        super.setValueAt(value, rowIndex, columnIndex);

        switch (columnIndex) {
            case 0:
                users.get(rowIndex).setId((Long) value);
                break;
            case 1:
                users.get(rowIndex).setUserName((String) value);
                break;
            case 2:
                users.get(rowIndex).setPassword((String) value);
                break;
        }
        this.fireTableRowsUpdated(rowIndex, rowIndex);
    }

    @Override
    public Object getValueAt(int row, int column) {

        switch (column) {
            case 0:
                return users.get(row).getId();
            case 1:
                return users.get(row).getUserName();
            case 2:
                return users.get(row).getPassword();
        }
        return null;
    }

}
