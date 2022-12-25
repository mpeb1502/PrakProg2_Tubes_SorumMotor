package view.mermo;

import model.Mermo;
import module.DaoModule;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;

public class MermoController {
    private final DaoModule daoModule;
    private final MermoFrame mermoFrame;

    public MermoController(DaoModule daoModule) {
        this.daoModule = daoModule;
        mermoFrame = new MermoFrame();

        mermoFrame.addButtonSimpanActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(mermoFrame,
                        "Simpan Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    simpan();
                } else {
                    JOptionPane.showMessageDialog(mermoFrame, "Anda tidak menyimpan data");
                }
            }
        });

        mermoFrame.addButtonDeleteActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(mermoFrame,
                        "Hapus Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    delete();
                } else {
                    JOptionPane.showMessageDialog(mermoFrame, "Anda tidak menghapus data");
                }
            }
        });
    }

    public void showMermoFrame() {
        List<Mermo> mermoList = daoModule.getMermoDao().findAll();
        mermoFrame.populateMermoTable(mermoList);
        mermoFrame.setVisible(true);
    }

    public void simpan() {
        String mermo = this.mermoFrame.getJenis();
        if (mermo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this.mermoFrame, "Form tidak boleh ada yang kosong");
        } else {
            Mermo mermoo = new Mermo();
            mermoo.setId(UUID.randomUUID().toString());
            mermoo.setMerekmotor(mermo);
            this.mermoFrame.addMerekmotor(mermoo);
            daoModule.getMermoDao().insert(mermoo);
        }
    }

    public void delete() {
        Mermo mermo = new Mermo();
        mermo.setId(this.mermoFrame.takeMermo());

        if (mermo.getId() != "") {
            this.mermoFrame.deleteMermo();
            daoModule.getMermoDao().delete(mermo);
        } else {
            JOptionPane.showMessageDialog(this.mermoFrame, "Tolong pilih baris tabel yang mau dihapus");
        }
    }
}
