package Vista;

import javax.swing.*;
import java.awt.*;

public class CustomFrame extends JFrame {
    public CustomFrame(Object controller, JPanel view) {

    }

    public CustomFrame() {
        initializeFrame();
    }


    private void initializeFrame() {
        this.setMinimumSize(new Dimension(1080,701));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setUndecorated(true);
        //getRootPane().setWindowDecorationStyle(getRootPane().getWindowDecorationStyle());
        setTitle("Cinepolis");

        this.setVisible(true);
    }


}

class internalFrame extends JInternalFrame {

}
