/**
 * Ingenieria en desarrollo de software
 * Proyecto final - Programacion III
 * <p>
 * Emiliano Fernandez Hernandez
 * Kenneth De Guadalupe Quintero Valles
 */

package view;

import db.MYSQLConnection;
import model.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class CustomFrame extends JFrame {
    static public Color BGCOLOR = new Color(0, 32, 105);
    static public Color SECOND_BG_COLOR = Color.white;
    Employee loggedEmployee;

    AbstractCinepolisPanel view;
    Screen screen;
    JMenuBar menuBar;

    public enum Screen {
        LOG_IN(LoginPanel::new),
        BILLBOARD(BillboardPanel::new),
        SCREENINGFORM(ScreeningForm::new);


        private final Function<CustomFrame, AbstractCinepolisPanel> panelGetter;

        Screen(Function<CustomFrame, AbstractCinepolisPanel> panelGetter) {
            this.panelGetter = panelGetter;
        }

        public AbstractCinepolisPanel getPanel(CustomFrame frame) {
            return panelGetter.apply(frame);
        }
    }

    public CustomFrame(Screen initialScreen) {
        initializeFrame();
        changeScreen(initialScreen);
    }

    public void changeScreen(Screen s) {
        if (view != null) {
            view.dispose();
            remove(view);
            this.menuBar.removeAll();
        }
        this.screen = s;
        this.add(this.view = s.getPanel(this));
        revalidate();
    }

    private void initializeFrame() {
        try {
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/Assets/Montserrat-Bold.ttf")));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        this.setResizable(false);
        this.setIconImage(new ImageIcon("src/assets/imageIcon.jpg").getImage());
        this.setMinimumSize(new Dimension(1080, 701));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        MYSQLConnection.closeConnection();
                    }
                }
        );
        this.setTitle("Cinepolis");
        this.setVisible(true);
        this.repaint();
    }

    public Employee getLoggedEmployee() {
        return loggedEmployee;
    }

    public void setLoggedEmployee(Employee loggedEmployee) {
        this.loggedEmployee = loggedEmployee;
    }
}
