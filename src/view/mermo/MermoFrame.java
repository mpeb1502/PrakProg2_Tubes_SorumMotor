package view.mermo;

import model.Mermo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.List;

public class MermoFrame extends JFrame {
    private List<Mermo> mermoList;
    private final JTextField textFieldMermo;
    private MermoTableModel tableModel;
    private final JTable table;
    private final JButton buttonSimpan;
    private final JButton buttonDelete;
    private ImageIcon carIcon;
    private JLabel myLabel;

    public MermoFrame() {

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Tampilan Background >>>>
        carIcon = new ImageIcon(this.getClass().getResource("../bgwow.jpg"));
        myLabel = new JLabel(carIcon);
        myLabel.setSize(1360,800);
        // Tampilan Background <<<<
        
        // CSS Java untuk title apk dan icon 
        this.setTitle("<< Aplikasi Sorum Nota >>");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../sorum.png")));

        JLabel labelTitlee1 = new JLabel(" Halaman Inputan Merek Motor ");
        labelTitlee1.setBounds(110,15,350,10);
        labelTitlee1.setForeground(Color.white);

        JLabel labelInput = new JLabel("Merek Motor :");
        labelInput.setForeground(Color.WHITE);
        labelInput.setBounds(15, 280, 350, 10);
        textFieldMermo = new JTextField();
        textFieldMermo.setBounds(15, 300, 350, 30);
        buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 340, 100, 40);
        buttonDelete = new JButton("Hapus");
        buttonDelete.setBounds(130, 340, 100, 40);
        table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 40, 350, 200);

        this.add(buttonSimpan);
        this.add(buttonDelete);
        this.add(textFieldMermo);
        this.add(labelInput);
        this.add(scrollableTable);
        this.add(labelTitlee1);        
        this.add(myLabel);

        this.setSize(400, 700);
        this.setLayout(null);
    }

    public String getJenis() {
        return textFieldMermo.getText();
    }

    public void addMerekmotor(Mermo merekmotor) {
        tableModel.add(merekmotor);
        textFieldMermo.setText("");
    }

    public String takeMermo() {
        int[] selection = table.getSelectedRows();
        for (int i = 0; i < selection.length; i++) {
            selection[i] = table.convertRowIndexToModel(selection[i]);
        }

        if (selection.length > 0) {
            String merekmotor = (String) tableModel.getValueAt(selection[0], 1);
            return merekmotor;
        } else {
            return "";
        }
    }

    public void deleteMermo() {
        int[] selection = table.getSelectedRows();
        for (int i = 0; i < selection.length; i++) {
            selection[i] = table.convertRowIndexToModel(selection[i]);
        }
        tableModel.remove(selection[0]);
    }

    public void addButtonSimpanActionListener(ActionListener actionListener) {
        buttonSimpan.addActionListener(actionListener);
    }

    public void addButtonDeleteActionListener(ActionListener actionListener) {
        buttonDelete.addActionListener(actionListener);
    }

    public void populateMermoTable(List<Mermo> mermoList) {
        this.mermoList = mermoList;
        tableModel = new MermoTableModel(mermoList);
        table.setModel(tableModel);
    }
}