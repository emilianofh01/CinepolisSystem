package controller;

import view.AdminSelection;
import view.CustomFrame;

public class AdminSelectionController {
    public static void initiate(AdminSelection panel) {
        panel.backBtn.addActionListener(e -> logOut(panel));
        panel.goToScreaning.addActionListener(e -> panel.parentFrame.changeScreen(CustomFrame.Screen.ADMINBILLBOARD));
        panel.goToBillboard.addActionListener(e -> panel.parentFrame.changeScreen(CustomFrame.Screen.BILLBOARD));
    }

    public static void logOut(AdminSelection panel) {
        panel.parentFrame.setLoggedEmployee(null);
        panel.parentFrame.changeScreen(CustomFrame.Screen.LOG_IN);
    }
}
