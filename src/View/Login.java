package View;

import CustomUI.CustomButton;
import CustomUI.CustomTextField;
import CustomUI.RoundContainer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Login extends JPanel {
    public CustomTextField userNameField;
    public CustomTextField passwordField;
    public CustomButton loginBtn;

    public Login() {
        this.setBackground(CustomFrame.BGCOLOR);
        this.setLayout(new BorderLayout(0,25));
        this.setBorder(BorderFactory.createEmptyBorder(25,50,50,50));

        // NORTH
        JPanel northContainer = new JPanel();
        try {
            BufferedImage logo = ImageIO.read(new File("src/Assets/logoCinepolis.png"));
            JLabel l = new JLabel(new ImageIcon(logo));

            northContainer.add(l);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        northContainer.setBackground(CustomFrame.BGCOLOR);
        this.add(northContainer, BorderLayout.NORTH);

        // CENTER
        RoundContainer centerContainer = RoundContainer.createRoundContainer(30, 35,20,35,20, CustomFrame.SECOND_BG_COLOR);
        centerContainer.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // CENTER --> Left margin
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.weighty = 1;
        c.weightx = 1;
        centerContainer.add(Box.createHorizontalBox(), c);

        // CENTER --> Form container
        JPanel formContainer = new JPanel();
        formContainer.setBackground(CustomFrame.SECOND_BG_COLOR);
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
        Dimension formSize = formContainer.getPreferredSize();
        formSize.width = 400;
        formContainer.setPreferredSize(formSize);

        JLabel titleLogin = new JLabel("Iniciar sesión", SwingConstants.CENTER);
        titleLogin.setFont(new Font("Montserrat", Font.BOLD, 23));
        titleLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, titleLogin.getMinimumSize().height));
        titleLogin.setForeground(CustomFrame.BGCOLOR);
        formContainer.add(titleLogin);

        // CENTER --> Form container --> TextField Username
        Font textFieldFont = new Font("Montserrat", Font.BOLD, 16);
        Color defaultBGColor = new Color(226, 226, 226);

        userNameField = new CustomTextField(15, textFieldFont, defaultBGColor, new Dimension(5,5), false);
        userNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        formContainer.add(userNameField);

        // CENTER --> Form container --> TextField Password
        passwordField = new CustomTextField(15, textFieldFont, defaultBGColor, new Dimension(5,5), true);
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        formContainer.add(passwordField);

        loginBtn = new CustomButton("Iniciar sesión", 20 ,CustomFrame.BGCOLOR, new Color(0,25,81), Color.white, textFieldFont);
        loginBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        formContainer.add(loginBtn);

        c.gridx = 1;
        c.weighty = 0;
        c.weightx = 0;
        centerContainer.add(formContainer, c);

        // CENTER --> Right margin
        c.gridx = 2;
        c.weighty = 1;
        c.weightx = 1;
        centerContainer.add(Box.createHorizontalBox(), c);
        this.add(centerContainer, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
