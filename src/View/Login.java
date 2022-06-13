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
        RoundContainer containerCenter = RoundContainer.createRoundContainer(30, 45,20,45,20, CustomFrame.SECOND_BG_COLOR);
        containerCenter.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // CENTER --> Left margin
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        containerCenter.add(Box.createHorizontalBox(), c);

        // CENTER --> Form container
        JPanel formContainer = new JPanel();
        Dimension formSize = formContainer.getPreferredSize();
        formSize.width = 400;
        formContainer.setPreferredSize(formSize);
        formContainer.setBackground(Color.red);
        c.gridx = 1;
        c.gridy = 0;
        c.weighty = 0;
        c.weightx = 0;
        //c.fill = GridBagConstraints.BOTH;
        containerCenter.add(formContainer, c);

        // CENTER --> Right margin
        c.gridx = 2;
        c.gridy = 0;
        c.weighty = 1;
        c.weightx = 1;
        //c.fill = GridBagConstraints.BOTH;
        containerCenter.add(Box.createHorizontalBox(), c);

        this.add(containerCenter, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
