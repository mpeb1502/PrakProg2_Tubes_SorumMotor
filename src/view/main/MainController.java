package view.main;

import module.DaoModule;
import view.merek.MerekController;
import view.sorum.SorumController;
import view.namot.NamotController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    private final MainFrame mainFrame;
    private final SorumController hewanController;
    private final MerekController jenisHewanController;
    private final NamotController habitatController;

    public MainController() {
        mainFrame = new MainFrame();
        DaoModule daoModule = new DaoModule();
        hewanController = new SorumController(daoModule);
        jenisHewanController = new MerekController(daoModule);
        habitatController = new NamotController(daoModule);
        mainFrame.addButtonJenisHewanActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showJenisHewanFrame();
            }
        });
        mainFrame.addButtonHewanActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHewanFrame();
            }
        });
        mainFrame.addButtonHabitatActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHabitatFrame();
            }
        });
    }

    public void showMainFrame() {
        this.mainFrame.setVisible(true);
    }

    public void showJenisHewanFrame() {
        jenisHewanController.showJenisHewanFrame();
    }

    public void showHewanFrame() {
        hewanController.showHewanFrame();
    }

    public void showHabitatFrame() {
        habitatController.showHabitatFrame();
    }
}
