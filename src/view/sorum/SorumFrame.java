package view.sorum;

import model.Namot;
import model.Merek;
import model.Sorum;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Color;


public class SorumFrame extends JFrame {
    private List<Namot> jenisHewanList;
    private List<Merek> habitatList;
    private List<Sorum> hewanList;
    private final JTextField textFieldNama;
    private final JTextField textFieldUmur;
    private JRadioButton radioButtonL;
    private JRadioButton radioButtonP;
    private SorumTableModel tableModel;
    private final JComboBox<String> comboJenis;
    private final JComboBox<String> comboHabitat;
    private final JButton buttonSimpan;
    private final JButton buttonDelete;
    private final JButton buttonExportPdf;
    private final JTable table;

    public SorumFrame() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.CYAN);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("<<<Selamat Datang Aplikasi Nota Sorum Motor>>>");
        label.setBounds(60,5,350,30);

        JLabel labelInputNama = new JLabel("Nama Pembeli:");
        labelInputNama.setBounds(15, 40, 350, 10);
        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        JLabel labelInputUmur = new JLabel("Alamat:");
        labelInputUmur.setBounds(15, 100, 350, 10);
        textFieldUmur = new JTextField();
        textFieldUmur.setBounds(15, 120, 350, 30);

        JLabel labelJenisKelamin = new JLabel("Jenis Motor:");
        labelJenisKelamin.setBounds(15, 150, 350, 30);
        radioButtonL = new JRadioButton("Classic", true);
        radioButtonL.setBounds(15, 175, 350, 30);
        radioButtonP = new JRadioButton("Modern");
        radioButtonP.setBounds(15, 200, 350, 30);

        JLabel labelJenis = new JLabel("Merek Motor:");
        labelJenis.setBounds(15, 240, 350, 10);
        comboJenis = new JComboBox<String>();
        comboJenis.setBounds(15, 260, 150, 30);

        JLabel labelHabitat = new JLabel("Nama Motor:");
        labelHabitat.setBounds(15, 300, 350, 10);
        comboHabitat = new JComboBox<String>();
        comboHabitat.setBounds(15, 320, 150, 30);

        buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 360, 100, 40);

        buttonDelete = new JButton("Hapus");
        buttonDelete.setBounds(130, 360, 100, 40);

        buttonExportPdf = new JButton("Export PDF");
        buttonExportPdf.setBounds(245, 360, 100, 40);
        table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 410, 350, 200);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButtonL);
        bg.add(radioButtonP);

        this.add(label);
        this.add(buttonSimpan);
        this.add(buttonDelete);
        this.add(buttonExportPdf);
        this.add(textFieldNama);
        this.add(labelInputNama);
        this.add(textFieldUmur);
        this.add(labelInputUmur);
        this.add(radioButtonL);
        this.add(radioButtonP);
        this.add(labelJenisKelamin);
        this.add(labelJenis);
        this.add(comboJenis);
        this.add(labelHabitat);
        this.add(comboHabitat);
        this.add(scrollableTable);

        this.setSize(400, 700);
        this.setLayout(null);
    }

    public void populateTable(List<Sorum> hewanList) {
        this.hewanList = hewanList;
        tableModel = new SorumTableModel(hewanList);
        table.setModel(tableModel);
    }

    public void populateComboJenis(List<Namot> jenisHewanList) {
        this.jenisHewanList = jenisHewanList;
        comboJenis.removeAllItems();
        for (Namot jenisHewan : jenisHewanList) {
            comboJenis.addItem(jenisHewan.getJenis());
        }
    }

    public void populateComboHabitat(List<Merek> habitatList) {
        this.habitatList = habitatList;
        comboHabitat.removeAllItems();
        for (Merek habitat : habitatList) {
            comboHabitat.addItem(habitat.getNamaHabitat());
        }
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public String getUmur() {
        return textFieldUmur.getText();
    }

    public String getJenisKelamin() {
        String jenisKelamin = "";

        if (radioButtonL.isSelected()) {
            jenisKelamin = radioButtonL.getText();
        }
        if (radioButtonP.isSelected()) {
            jenisKelamin = radioButtonP.getText();
        }
        return jenisKelamin;
    }

    public Namot getJenisHewan() {
        return jenisHewanList.get(comboJenis.getSelectedIndex());
    }

    public Merek getHabitat() {
        return habitatList.get(comboHabitat.getSelectedIndex());
    }

    public void addHewan(Sorum hewan) {
        tableModel.add(hewan);
        textFieldNama.setText("");
        textFieldUmur.setText("");
    }

    public String takeHewan() {
        int[] selection = table.getSelectedRows();
        for (int i = 0; i < selection.length; i++) {
            selection[i] = table.convertRowIndexToModel(selection[i]);
        }

        if (selection.length > 0) {
            String namaHewan = (String) tableModel.getValueAt(selection[0], 5);
            return namaHewan;
        } else {
            return "";
        }
    }

    public void deleteHewan() {
        int[] selection = table.getSelectedRows();
        for (int i = 0; i < selection.length; i++) {
            selection[i] = table.convertRowIndexToModel(selection[i]);
        }
        tableModel.remove(selection[0]);
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void addButtonSimpanActionListener(ActionListener actionListener) {
        buttonSimpan.addActionListener(actionListener);
    }

    public void addButtonExportPdfActionListener(ActionListener actionListener) {
        buttonExportPdf.addActionListener(actionListener);
    }

    public void addButtonDeleteActionListener(ActionListener actionListener) {
        buttonDelete.addActionListener(actionListener);
    }

    public List<Sorum> getHewanList() {
        return hewanList;
    }
}
