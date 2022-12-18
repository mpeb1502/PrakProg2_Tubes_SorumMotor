package view.sorum;

import model.Namot;
import model.Merek;
import model.Sorum;
import module.DaoModule;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;

public class SorumController {

    private final DaoModule daoModule;
    private final SorumFrame hewanFrame;
    private final SorumPdfExport hewanPdfExport;

    public SorumController(DaoModule daoModule) {
        this.daoModule = daoModule;
        this.hewanFrame = new SorumFrame();
        this.hewanPdfExport = new SorumPdfExport();

        this.hewanFrame.addButtonSimpanActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(hewanFrame,
                        "Simpan Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    SorumController.this.simpan();
                } else {
                    JOptionPane.showMessageDialog(hewanFrame, "Anda tidak mengsimpan data");
                }
            }
        });

        this.hewanFrame.addButtonExportPdfActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(hewanFrame,
                        "Export PDF?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    SorumController.this.exportPdf();
                } else {
                    JOptionPane.showMessageDialog(hewanFrame, "Anda tidak mengexport PDF");
                }
            }
        });

        this.hewanFrame.addButtonDeleteActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(hewanFrame,
                        "Hapus Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    SorumController.this.delete();
                } else {
                    JOptionPane.showMessageDialog(hewanFrame, "Anda tidak menghapus data");
                }
            }
        });
    }

    public void showHewanFrame() {
        List<Sorum> hewanList = daoModule.getHewanDao().findAll();
        hewanFrame.populateTable(hewanList);
        List<Namot> jenisHewanList = daoModule.getJenisHewanDao().findAll();
        hewanFrame.populateComboJenis(jenisHewanList);
        List<Merek> habitatList = daoModule.getHabitatDao().findAll();
        hewanFrame.populateComboHabitat(habitatList);
        hewanFrame.setVisible(true);
    }

    public void simpan() {
        String nama = this.hewanFrame.getNama();
        String umur = this.hewanFrame.getUmur();
        String jenisKelamin = this.hewanFrame.getJenisKelamin();
        Namot jenisHewan = this.hewanFrame.getJenisHewan();
        Merek habitat = this.hewanFrame.getHabitat();
        if (nama.isEmpty() || umur.isEmpty() || jenisKelamin.isEmpty()) {
            this.hewanFrame.showAlert("Form tidak boleh ada yang kosong");
        } else {
            Sorum hewan = new Sorum();
            hewan.setId(UUID.randomUUID().toString());
            hewan.setNama(nama);
            hewan.setUmur(umur);
            hewan.setJenisKelamin(jenisKelamin);
            hewan.setJenisHewan(jenisHewan);
            hewan.setHabitat(habitat);
            this.hewanFrame.addHewan(hewan);
            daoModule.getHewanDao().insert(hewan);
        }
    }

    public void delete() {
        Sorum namaH = new Sorum();
        namaH.setId(this.hewanFrame.takeHewan());

        if (namaH.getId() != "") {
            this.hewanFrame.deleteHewan();
            daoModule.getHewanDao().delete(namaH);
        } else {
            JOptionPane.showMessageDialog(this.hewanFrame, "Tolong pilih baris tabel yang mau dihapus");
        }
    }

    public void exportPdf() {
        try {
            hewanPdfExport.export(hewanFrame.getHewanList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
