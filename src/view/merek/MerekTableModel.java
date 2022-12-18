package view.merek;

import javax.swing.table.*;
import java.util.List;
import model.Namot;

public class MerekTableModel extends AbstractTableModel {
    private String[] columnNames = { "Merek Motor" };
    private List<Namot> data;

    public MerekTableModel(List<Namot> data) {
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
        Namot rowItem = data.get(row);
        String value = "";
        switch (col) {
            case 0:
                value = rowItem.getJenis();
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

    public void add(Namot value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void remove(int value) {
        data.remove(value);
        fireTableRowsDeleted(data.size() - 1, data.size() - 1);
    }
}
