package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class  CustomFrame extends JFrame {
    static public Color BGCOLOR = new Color(0,32,105);
    static public Color SECOND_BG_COLOR = Color.white;
    JPanel view;

    public CustomFrame(Object controller, JPanel view) {

    }

    public CustomFrame() {
        initializeFrame();
    }


    private void initializeFrame() {
        this.setMinimumSize(new Dimension(1080,701));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Cinepolis");
        this.pack();

        view = new Login();
        adaptContent();
        this.add(view);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                adaptContent();
            }
        });
        this.setVisible(true);

        this.repaint();
    }

    private void adaptContent() {
        view.setBounds(0,0, this.getContentPane().getWidth(), this.getContentPane().getHeight());
    }


}
