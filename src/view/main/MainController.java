package view.main;

import module.DaoModule;
import view.sorum.SorumController;
import view.mermo.MermoController;
import view.namot.NamotController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    private final MainFrame mainFrame;
    private final SorumController sorumController;
    private final MermoController mermoController;
    private final NamotController namotController;

    public MainController() {
        mainFrame = new MainFrame();
        DaoModule daoModule = new DaoModule();
        sorumController = new SorumController(daoModule);
        mermoController = new MermoController(daoModule);
        namotController = new NamotController(daoModule);
        
        mainFrame.addButtonMermoActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMermoFrame();
            }
        });
        mainFrame.addButtonSorumActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSorumFrame();
            }
        });
        mainFrame.addButtonNamotActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNamotFrame();
            }
        });
    }

    public void showMainFrame() {
        this.mainFrame.setVisible(true);
    }

    public void showMermoFrame() {
        mermoController.showMermoFrame();
    }

    public void showSorumFrame() {
        sorumController.showSorumFrame();
    }

    public void showNamotFrame() {
        namotController.showNamotFrame();
    }
}
