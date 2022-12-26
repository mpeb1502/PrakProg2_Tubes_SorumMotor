package view.sorum;

import model.Namot;
import model.Mermo;
import model.Sorum;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.text.StyleConstants;
import javax.swing.text.SimpleAttributeSet;


public class SorumFrame extends JFrame {
    private ImageIcon carIcon;
    private JLabel myLabel;
    private List<Namot> namotList;
    private List<Mermo> mermoList;
    private List<Sorum> sorumList;
    private final JTextField textFieldNama;
    private final JTextField textFieldAlamat;
    private JRadioButton radioButtonL;
    private JRadioButton radioButtonP;
    private SorumTableModel tableModel;
    private final JComboBox<String> comboMerek;
    private final JComboBox<String> comboNamot;
    private final JButton buttonSimpan;
    private final JButton buttonDelete;
    private final JButton buttonExportPdf;
    private final JTable table;

    

    public SorumFrame() {
        // Tampilan Background >>>>
        carIcon = new ImageIcon(this.getClass().getResource("../bgwow.jpg"));
        myLabel = new JLabel(carIcon);
        myLabel.setSize(1360,800);
        // Tampilan Background <<<<

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel labelTitlee1 = new JLabel(" Halaman Pembuatan Nota ");
        labelTitlee1.setBounds(120,15,350,10);
        labelTitlee1.setForeground(Color.white);

        JLabel labelInputNama = new JLabel("Nama Pembeli :");
        labelInputNama.setBounds(15, 40, 350, 10);
        labelInputNama.setForeground(Color.white);
        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        JLabel labelInputAlamat = new JLabel("Alamat :");
        labelInputAlamat.setBounds(15, 100, 350, 10);
        labelInputAlamat.setForeground(Color.white);
        textFieldAlamat = new JTextField();
        textFieldAlamat.setBounds(15, 120, 350, 30);

        JLabel labelJenisMotor = new JLabel("Jenis motor :");
        labelJenisMotor.setForeground(Color.white);
        labelJenisMotor.setBounds(15, 150, 350, 30);
        radioButtonL = new JRadioButton("Classic", true);
        radioButtonL.setBounds(15, 175, 350, 30);
        radioButtonP = new JRadioButton("Modern");
        radioButtonP.setBounds(15, 200, 350, 30);

        JLabel labelJenis = new JLabel("Merek motor :");
        labelJenis.setForeground(Color.white);
        labelJenis.setBounds(15, 240, 350, 10);
        comboMerek = new JComboBox<String>();
        comboMerek.setBounds(15, 260, 150, 30);

        JLabel labelNamotor = new JLabel("Nama Motor :");
        labelNamotor.setBounds(200, 240, 350, 10);
        labelNamotor.setForeground(Color.white);
        comboNamot = new JComboBox<String>();
        comboNamot.setBounds(200, 260, 150, 30);

        buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 315, 100, 40);

        buttonDelete = new JButton("Hapus");
        buttonDelete.setBounds(140, 315, 100, 40);

        buttonExportPdf = new JButton("Export PDF");
        buttonExportPdf.setBounds(260, 315, 100, 40);

        JLabel labelTitlee = new JLabel("<< TABEL DAFTAR NOTA PEMBELIAN MOTOR >>");
        labelTitlee.setBounds(50,390,350,10);
        labelTitlee.setForeground(Color.white);

        table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(5, 410, 370, 200);

        // CSS Java untuk title apk dan icon 
        this.setTitle("<< Aplikasi Sorum Nota >>");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../sorum.png")));
    
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButtonL);
        bg.add(radioButtonP);

        this.add(buttonSimpan);
        this.add(buttonDelete);
        this.add(buttonExportPdf);
        this.add(textFieldNama);
        this.add(labelInputNama);
        this.add(textFieldAlamat);
        this.add(labelInputAlamat);
        this.add(radioButtonL);
        this.add(radioButtonP);
        this.add(labelJenisMotor);
        this.add(labelJenis);
        this.add(comboMerek);
        this.add(labelNamotor);
        this.add(comboNamot);
        this.add(scrollableTable);
        this.add(labelTitlee);
        this.add(labelTitlee1);
        this.add(myLabel);

        this.setSize(400, 700);
        this.setLayout(null);
    }

    public void populateSorumTable(List<Sorum> sorumList) {
        this.sorumList = sorumList;
        tableModel = new SorumTableModel(sorumList);
        table.setModel(tableModel);
    }

    public void populateComboMermo(List<Mermo> mermoList) {
        this.mermoList = mermoList;
        comboMerek.removeAllItems();
        for (Mermo mermo : mermoList) {
            comboMerek.addItem(mermo.getMerekmotor());
        }
    }

    public void populateComboNamot(List<Namot> namotList) {
        this.namotList = namotList;
        comboNamot.removeAllItems();
        for (Namot namot : namotList) {
            comboNamot.addItem(namot.getNamamotor());
        }
    }

    public String getNamapem() {
        return textFieldNama.getText();
    }

    public String getAlamat() {
        return textFieldAlamat.getText();
    }

    public String getJenismot() {
        String jenismot = "";

        if (radioButtonL.isSelected()) {
            jenismot = radioButtonL.getText();
        }
        if (radioButtonP.isSelected()) {
            jenismot = radioButtonP.getText();
        }
        return jenismot;
    }

    public Mermo getMerekmotor() {
        return mermoList.get(comboMerek.getSelectedIndex());
    }

    public Namot getNamamotor() {
        return namotList.get(comboNamot.getSelectedIndex());
    }

    public void addSorum(Sorum sorum) {
        tableModel.add(sorum);
        textFieldNama.setText("");
        textFieldAlamat.setText("");
    }

    public String takeSorum() {
        int[] selection = table.getSelectedRows();
        for (int i = 0; i < selection.length; i++) {
            selection[i] = table.convertRowIndexToModel(selection[i]);
        }

        if (selection.length > 0) {
            String namaSorum = (String) tableModel.getValueAt(selection[0], 5);
            return namaSorum;
        } else {
            return "";
        }
    }

    public void deleteSorum() {
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

    public List<Sorum> getSorumList() {
        return sorumList;
    }
}
