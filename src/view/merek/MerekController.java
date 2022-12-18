package view.merek;

import model.Namot;
import module.DaoModule;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;

public class MerekController {
    private final DaoModule daoModule;
    private final MerekFrame jenisHewanFrame;

    public MerekController(DaoModule daoModule) {
        this.daoModule = daoModule;
        jenisHewanFrame = new MerekFrame();

        jenisHewanFrame.addButtonSimpanActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(jenisHewanFrame,
                        "Simpan Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    simpan();
                } else {
                    JOptionPane.showMessageDialog(jenisHewanFrame, "Anda tidak menyimpan data");
                }
            }
        });

        jenisHewanFrame.addButtonDeleteActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(jenisHewanFrame,
                        "Hapus Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    delete();
                } else {
                    JOptionPane.showMessageDialog(jenisHewanFrame, "Anda tidak menghapus data");
                }
            }
        });
    }

    public void showJenisHewanFrame() {
        List<Namot> jenisHewanList = daoModule.getJenisHewanDao().findAll();
        jenisHewanFrame.populateTable(jenisHewanList);
        jenisHewanFrame.setVisible(true);
    }

    public void simpan() {
        String jenis = this.jenisHewanFrame.getJenis();
        if (jenis.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this.jenisHewanFrame, "Form tidak boleh ada yang kosong");
        } else {
            Namot jenisHewan = new Namot();
            jenisHewan.setId(UUID.randomUUID().toString());
            jenisHewan.setJenis(jenis);
            this.jenisHewanFrame.addJenisHewan(jenisHewan);
            daoModule.getJenisHewanDao().insert(jenisHewan);
        }
    }

    public void delete() {
        Namot jenis = new Namot();
        jenis.setId(this.jenisHewanFrame.takeJenisHewan());

        if (jenis.getId() != "") {
            this.jenisHewanFrame.deleteJenisHewan();
            daoModule.getJenisHewanDao().delete(jenis);
        } else {
            JOptionPane.showMessageDialog(this.jenisHewanFrame, "Tolong pilih baris tabel yang mau dihapus");
        }
    }
}
