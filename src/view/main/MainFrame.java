package view.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.Color;

public class MainFrame extends JFrame {
    private final JButton buttonJenisHewan;
    private final JButton buttonHewan;
    private final JButton buttonHabitat;

    public MainFrame() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.CYAN);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int exit = JOptionPane.showConfirmDialog(null,
                        "Apakah anda yakin ingin keluar?", "Keluar",
                        JOptionPane.YES_NO_OPTION);

                if (exit == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        JLabel label = new JLabel("Pilih Menu->>> :");
        label.setBounds(100,200,350,30);
        this.add(label);
        this.setSize(400, 700);
        this.setLayout(new FlowLayout());
        this.buttonJenisHewan = new JButton("Merek Motor");
        this.buttonHewan = new JButton("Sorum");
        this.buttonHabitat = new JButton("Nama Motor");
        this.add(buttonJenisHewan);
        this.add(buttonHewan);
        this.add(buttonHabitat);
    }

    public JButton getButtonJenisHewan() {
        return buttonJenisHewan;
    }

    public JButton getButtonHewan() {
        return buttonHewan;
    }

    public JButton getButtonHabitat() {
        return buttonHabitat;
    }

    public void addButtonJenisHewanActionListener(ActionListener actionListener) {
        buttonJenisHewan.addActionListener(actionListener);
    }

    public void addButtonHewanActionListener(ActionListener actionListener) {
        buttonHewan.addActionListener(actionListener);
    }

    public void addButtonHabitatActionListener(ActionListener actionListener) {
        buttonHabitat.addActionListener(actionListener);
    }
}
