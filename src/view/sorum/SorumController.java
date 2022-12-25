package view.sorum;

import model.Namot;
import model.Mermo;
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
    private final SorumFrame sorumFrame;
    private final SorumPdfExport sorumPdfExport;

    public SorumController(DaoModule daoModule) {
        this.daoModule = daoModule;
        this.sorumFrame = new SorumFrame();
        this.sorumPdfExport = new SorumPdfExport();

        this.sorumFrame.addButtonSimpanActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(sorumFrame,
                        "Simpan Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    SorumController.this.simpan();
                } else {
                    JOptionPane.showMessageDialog(sorumFrame, "Anda tidak mengsimpan data");
                }
            }
        });

        this.sorumFrame.addButtonExportPdfActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(sorumFrame,
                        "Export PDF?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    SorumController.this.exportPdf();
                } else {
                    JOptionPane.showMessageDialog(sorumFrame, "Anda tidak mengexport PDF");
                }
            }
        });

        this.sorumFrame.addButtonDeleteActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(sorumFrame,
                        "Hapus Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    SorumController.this.delete();
                } else {
                    JOptionPane.showMessageDialog(sorumFrame, "Anda tidak menghapus data");
                }
            }
        });
    }

    public void showSorumFrame() {
        List<Sorum> sorumList = daoModule.getSorumDao().findAll();
        sorumFrame.populateSorumTable(sorumList);
        List<Mermo> mermoList = daoModule.getMermoDao().findAll();
        sorumFrame.populateComboMermo(mermoList);
        List<Namot> namotList = daoModule.getNamotDao().findAll();
        sorumFrame.populateComboNamot(namotList);
        
        sorumFrame.setVisible(true);
    }

    public void simpan() {
        String namapem = this.sorumFrame.getNamapem();
        String alamat = this.sorumFrame.getAlamat();
        String jenismot = this.sorumFrame.getJenismot();
        Mermo merekmotor = this.sorumFrame.getMerekmotor();
        Namot namamotor = this.sorumFrame.getNamamotor();
        if (namapem.isEmpty() || alamat.isEmpty() || jenismot.isEmpty()) {
            this.sorumFrame.showAlert("Form tidak boleh ada yang kosong");
        } else {
            Sorum sorum = new Sorum();
            sorum.setId(UUID.randomUUID().toString());
            sorum.setNamapem(namapem);
            sorum.setAlamat(alamat);
            sorum.setJenismot(jenismot);
            sorum.setMermo(merekmotor);
            sorum.setNamot(namamotor);
            this.sorumFrame.addSorum(sorum);
            daoModule.getSorumDao().insert(sorum);
        }
    }

    public void delete() {
        Sorum namaP = new Sorum();
        namaP.setId(this.sorumFrame.takeSorum());

        if (namaP.getId() != "") {
            this.sorumFrame.deleteSorum();
            daoModule.getSorumDao().delete(namaP);
        } else {
            JOptionPane.showMessageDialog(this.sorumFrame, "Tolong pilih baris tabel yang mau dihapus");
        }
    }

    public void exportPdf() {
        try {
            sorumPdfExport.export(sorumFrame.getSorumList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
