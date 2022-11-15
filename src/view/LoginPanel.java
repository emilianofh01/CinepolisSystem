/**
 * Ingenieria en desarrollo de software
 * Proyecto final - Programacion III
 * <p>
 * Emiliano Fernandez Hernandez
 * Kenneth De Guadalupe Quintero Valles
 */


package view;

import controller.LoginController;
import db.MYSQLConnection;
import model.Employee;
import ui.CustomButton;
import ui.CustomTextField;
import ui.RoundContainer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginPanel extends AbstractCinepolisPanel {
    public CustomTextField userNameField;
    public CustomTextField passwordField;
    public CustomButton loginBtn;

    public LoginPanel(CustomFrame frame) {
        super(frame);
    }

    @Override
    public void init() {
        this.setBackground(CustomFrame.BGCOLOR);
        this.setLayout(new BorderLayout(0, 25));
        this.setBorder(BorderFactory.createEmptyBorder(25, 50, 50, 50));

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
        RoundContainer centerContainer = RoundContainer.createRoundContainer(30, 35, 20, 35, 20, CustomFrame.SECOND_BG_COLOR);
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

        formContainer.add(Box.createVerticalGlue());
        // NO CONNECTION
        if(MYSQLConnection.conn != null) {
            JLabel titleLogin = new JLabel("Iniciar sesión", SwingConstants.CENTER);
            titleLogin.setAlignmentX(.5f);
            titleLogin.setFont(new Font("Montserrat", Font.BOLD, 23));
            titleLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, titleLogin.getMinimumSize().height));
            titleLogin.setForeground(CustomFrame.BGCOLOR);
            formContainer.add(titleLogin);

            formContainer.add(Box.createVerticalStrut(50));

            // CENTER --> Form container --> TextField Username
            Font textFieldFont = new Font("Montserrat", Font.BOLD, 16);
            Color defaultBGColor = new Color(226, 226, 226);

            userNameField = new CustomTextField(15, textFieldFont, defaultBGColor, new Dimension(5, 5), false);
            userNameField.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
            userNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            userNameField.setPlaceholder("Nombre de usuario");
            formContainer.add(userNameField);

            formContainer.add(Box.createVerticalStrut(10));


            // CENTER --> Form container --> TextField Password
            passwordField = new CustomTextField(15, textFieldFont, defaultBGColor, new Dimension(5, 5), true);
            passwordField.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
            passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            passwordField.setPlaceholder("Contraseña");
            formContainer.add(passwordField);

            formContainer.add(Box.createVerticalStrut(25));


            loginBtn = new CustomButton("Iniciar sesión", 20, CustomFrame.BGCOLOR, new Color(0, 25, 81), Color.white, textFieldFont);
            loginBtn.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
            loginBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            loginBtn.setAlignmentX(.5f);
            formContainer.add(loginBtn);

            formContainer.add(Box.createVerticalGlue());

            LoginController.initiate(this);
        } else {
            System.out.println(MYSQLConnection.conn);
            try {
                Image noConnectionImg = ImageIO.read(new File("src/Assets/no-connection.jpg"));
                noConnectionImg = noConnectionImg.getScaledInstance(200,200, Image.SCALE_SMOOTH);
                JLabel l = new JLabel(new ImageIcon(noConnectionImg));
                l.setPreferredSize(new Dimension(250,250));

                formContainer.add(l);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

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

    public void logIn(Employee employee) {
        parentFrame.setLoggedEmployee(employee);
        parentFrame.changeScreen(CustomFrame.Screen.BILLBOARD);
    }

    @Override
    public void dispose() {

    }
}
