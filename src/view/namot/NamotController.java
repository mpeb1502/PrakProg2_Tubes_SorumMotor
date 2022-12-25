package view.namot;

import model.Namot;
import module.DaoModule;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;

public class NamotController {
    private final DaoModule daoModule;
    private final NamotFrame namotFrame;

    public NamotController(DaoModule daoModule) {
        this.daoModule = daoModule;
        namotFrame = new NamotFrame();

        namotFrame.addButtonSimpanActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(namotFrame,
                        "Simpan Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    simpan();
                } else {
                    JOptionPane.showMessageDialog(namotFrame, "Anda tidak menyimpan data");
                }
            }
        });

        namotFrame.addButtonDeleteActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(namotFrame,
                        "Hapus Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    delete();
                } else {
                    JOptionPane.showMessageDialog(namotFrame, "Anda tidak menghapus data");
                }
            }
        });
    }

    public void showNamotFrame() {
        List<Namot> namotList = daoModule.getNamotDao().findAll();
        namotFrame.populateNamotTable(namotList);
        namotFrame.setVisible(true);
    }

    public void simpan() {
        String namot = this.namotFrame.getNamamotor();
        if (namot.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this.namotFrame, "Form tidak boleh ada yang kosong");
        } else {
            Namot namott = new Namot();
            namott.setId(UUID.randomUUID().toString());
            namott.setNamamotor(namot);
            this.namotFrame.addNamot(namott);
            daoModule.getNamotDao().insert(namott);
        }
    }

    public void delete() {
        Namot namot = new Namot();
        namot.setId(this.namotFrame.takeNamot());

        if (namot.getId() != "") {
            this.namotFrame.deleteNamot();
            daoModule.getNamotDao().delete(namot);
        } else {
            JOptionPane.showMessageDialog(this.namotFrame, "Tolong pilih baris tabel yang mau dihapus");
        }
    }
}
