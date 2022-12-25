package view.mermo;

import javax.swing.table.*;
import java.util.List;
import model.Mermo;

public class MermoTableModel extends AbstractTableModel {
    private String[] columnNames = { "<< Merek Motor >>" };
    private List<Mermo> data;

    public MermoTableModel(List<Mermo> data) {
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
        Mermo rowItem = data.get(row);
        String value = "";
        switch (col) {
            case 0:
                value = rowItem.getMerekmotor();
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

    public void add(Mermo value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void remove(int value) {
        data.remove(value);
        fireTableRowsDeleted(data.size() - 1, data.size() - 1);
    }
}
