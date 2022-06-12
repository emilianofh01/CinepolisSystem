package View;

import CustomUI.RoundContainer;

import javax.swing.*;
import java.awt.*;

public class Login extends JPanel {
    public Login() {
        this.setBackground(CustomFrame.BGCOLOR);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));


        // CENTER
        RoundContainer containerCenter = RoundContainer.createRoundContainer(30, 0,0,0,0, CustomFrame.SECOND_BG_COLOR);


        this.add(containerCenter, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
