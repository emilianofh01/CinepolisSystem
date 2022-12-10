package view;

import controller.AdminSelectionController;
import ui.CustomButton;
import ui.CustomScrollPane;
import ui.CustomTextField;
import ui.RoundContainer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AdminSelection extends AbstractCinepolisPanel{
    public JMenuItem logOutmenuBar, updateTableMenuBar;
    public CustomButton backBtn;
    public CustomButton goToBillboard;
    public CustomButton goToScreaning;

    public AdminSelection(CustomFrame parentFrame) {
        super(parentFrame);

    }

    @Override
    public void init() {
        this.setBackground(CustomFrame.BGCOLOR);
        this.setLayout(new BorderLayout(0, 15));
        this.setBorder(BorderFactory.createEmptyBorder(25, 50, 50, 50));

        //JMenu option = new JMenu("Opciones");

        //logOutmenuBar = new JMenuItem("Cerrar sesión");
        //updateTableMenuBar = new JMenuItem("Actualizar cartelera");

        //option.add(logOutmenuBar);
        //option.add(updateTableMenuBar);

        //parentFrame.menuBar.add(option);

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
        backBtn = new CustomButton("Cerrar sesion", 40, new Color(243,127,127),
                new Color(176,92,92), Color.WHITE, new Font("Montserrat", Font.BOLD, 15));
        backBtn.setPreferredSize(new Dimension(125, 40));

        wrapBackBtn.add(backBtn);
        northContainer.add(wrapBackBtn, BorderLayout.LINE_END);

        //northContainer.add(Box.createHorizontalStrut(marginh));
        this.add(northContainer, BorderLayout.NORTH);


        // CENTER (CONTENT)
        RoundContainer billboardContainer = new RoundContainer(45);
        billboardContainer.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        billboardContainer.setLayout(new BorderLayout(0, 20));

        JPanel container = new JPanel();
        container.setLayout(new GridLayout(1,2, 10,10));

        Font textFieldFont = new Font("Montserrat", Font.BOLD, 32);
        goToBillboard = new CustomButton("Ver cartelera", 20, CustomFrame.BGCOLOR, new Color(0, 25, 81), Color.white, textFieldFont);
        goToScreaning = new CustomButton("Gestionar horarios", 20, CustomFrame.BGCOLOR, new Color(0, 25, 81), Color.white, textFieldFont);


        container.add(goToBillboard);
        container.add(goToScreaning);

        billboardContainer.add(container, BorderLayout.CENTER);

        JLabel title = new JLabel("Seleccione una opcion");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Montserrat", Font.BOLD, 20));
        billboardContainer.add(title, BorderLayout.NORTH);


        this.add(billboardContainer, BorderLayout.CENTER);

        AdminSelectionController.initiate(this);
    }

    @Override
    public void dispose() {

    }
}
