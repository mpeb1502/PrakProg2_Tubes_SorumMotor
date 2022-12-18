package view.namot;

import javax.swing.table.*;
import java.util.List;
import model.Merek;

public class NamotTableModel extends AbstractTableModel {
    private String[] columnNames = { "Nama Motor" };
    private List<Merek> data;

    public NamotTableModel(List<Merek> data) {
        this.data = data;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        Merek rowItem = data.get(row);
        String value = "";
        switch (col) {
            case 0:
                value = rowItem.getNamaHabitat();
                break;
            case 1:
                value = rowItem.getId();
                break;
        }
        return value;
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(Merek value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void remove(int value) {
        data.remove(value);
        fireTableRowsDeleted(data.size() - 1, data.size() - 1);
    }
}
