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
    public CustomButton loginBtn, tryAgainBtn;

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
        northContainer.setBackground(CustomFrame.BGCOLOR);
        int marginh = 20;
        //northContainer.add(Box.createHorizontalStrut(marginh));
        northContainer.setLayout(new BorderLayout());
        try {
            BufferedImage logo = ImageIO.read(new File("src/Assets/logoCinepolis.png"));

            BufferedImage resized = new BufferedImage(191,43, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = resized.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(logo ,0, 0, 191,43, null);


            JLabel l = new JLabel(new ImageIcon(resized));
            l.setPreferredSize(new Dimension(191, 43));

            northContainer.add(l, BorderLayout.LINE_START);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JPanel wrapBackBtn = new JPanel();
        wrapBackBtn.setBackground(CustomFrame.BGCOLOR);

        northContainer.add(wrapBackBtn, BorderLayout.LINE_END);

        //northContainer.add(Box.createHorizontalStrut(marginh));
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
        //formContainer.setBackground(Color.red);
        Dimension formSize = formContainer.getPreferredSize();
        formSize.width = 400;
        formContainer.setPreferredSize(formSize);

        // NO CONNECTION
        if(MYSQLConnection.conn != null) {
            formContainer.add(Box.createVerticalGlue());

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

            userNameField = new CustomTextField(15, textFieldFont, defaultBGColor, new Dimension(5, 5), false, true);
            userNameField.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
            userNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            userNameField.setPlaceholder("Nombre de usuario");
            formContainer.add(userNameField);

            formContainer.add(Box.createVerticalStrut(10));


            // CENTER --> Form container --> TextField Password
            passwordField = new CustomTextField(15, textFieldFont, defaultBGColor, new Dimension(5, 5), true, true);
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

        } else {
            System.out.println(MYSQLConnection.conn);

            JLabel titleLogin = new JLabel("No se pudo establecer conexión", SwingConstants.CENTER);
            titleLogin.setFont(new Font("Montserrat", Font.BOLD, 23));
            titleLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, titleLogin.getMinimumSize().height));
            titleLogin.setForeground(CustomFrame.BGCOLOR);
            formContainer.add(titleLogin);

            try {
                int imgWidth = 400, imgHeight = 279;
                Image noConnectionImg = ImageIO.read(new File("src/Assets/no-connection.png"));
                noConnectionImg = noConnectionImg.getScaledInstance(imgWidth,imgHeight, Image.SCALE_SMOOTH);

                JLabel l = new JLabel(new ImageIcon(noConnectionImg));
                l.setPreferredSize(new Dimension(imgWidth,imgHeight));

                formContainer.add(l);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            formContainer.add(Box.createVerticalStrut(50));

            Font textFieldFont = new Font("Montserrat", Font.BOLD, 16);
            tryAgainBtn = new CustomButton("Reintentar", 15, CustomFrame.BGCOLOR, new Color(0, 25, 81),
                    Color.white, textFieldFont);
            tryAgainBtn.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
            tryAgainBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            formContainer.add(tryAgainBtn);
        }

        LoginController.initiate(this);

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
        if(parentFrame.getLoggedEmployee().isAdmin()) {
            parentFrame.changeScreen(CustomFrame.Screen.ADMINSELECTION);
        } else {
            parentFrame.changeScreen(CustomFrame.Screen.BILLBOARD);
        }
    }

    public void tryAgain() {
        MYSQLConnection.getConnection();
        parentFrame.changeScreen(CustomFrame.Screen.LOG_IN);
    }

    @Override
    public void dispose() {

    }
}
