/**
 * Ingenieria en desarrollo de software
 * Proyecto final - Programacion III
 * <p>
 * Emiliano Fernandez Hernandez
 * Kenneth De Guadalupe Quintero Valles
 */

package view;

import controller.ScreeningFormController;
import springUtilities.SpringUtilities;
import ui.CustomButton;
import ui.RoundContainer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class AdminScreeningForm extends AbstractCinepolisPanel {
    public CustomFrame parentFrame;
    public JTextField screeningId;
    public JComboBox<String> screeningRoom;
    public JSpinner screeningStart;
    public JComboBox<String> screeningMovieId;

    public JButton btnCancelar, confirmarBtn;


    public AdminScreeningForm(CustomFrame parentFrame) {
        super(parentFrame);
        this.parentFrame = parentFrame;
    }

    @Override
    public void init() {
        //screeningId = new JTextField(10);
        screeningRoom = new JComboBox<>();
        screeningStart = new JSpinner();
        screeningMovieId = new JComboBox<>();

        SpinnerDateModel ScreeningModel = new SpinnerDateModel();
        ScreeningModel.setCalendarField(Calendar.MINUTE);
        screeningStart.setModel(ScreeningModel);
        screeningStart.setEditor(new JSpinner.DateEditor(screeningStart, "yyyy-MM-dd HH:mm"));

        SpinnerDateModel movieModel = new SpinnerDateModel();
        movieModel.setCalendarField(Calendar.MINUTE);

        final String[] labelsInputsScreening = new String[]{"Pelicula", "Sala", "Fecha y hora de inicio"};
        Object[] inputsScreening = new Object[]{screeningMovieId, screeningRoom, screeningStart};

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
        /*backBtn = new CustomButton("Volver", 40, new Color(243,127,127),
                new Color(176,92,92), Color.WHITE, new Font("Montserrat", Font.BOLD, 15));
        backBtn.setPreferredSize(new Dimension(125, 40));*/

        //wrapBackBtn.add(backBtn);
        //northContainer.add(wrapBackBtn, BorderLayout.LINE_END);

        //northContainer.add(Box.createHorizontalStrut(marginh));
        this.add(northContainer, BorderLayout.NORTH);


        // CENTER
        RoundContainer container = new RoundContainer(45);
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        container.setLayout(new BorderLayout());


        // Left -> Screening form
        JPanel screeningForm = new JPanel();
        screeningForm.setBackground(Color.white);
        screeningForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        screeningForm.setLayout(new SpringLayout());

        for (int i = 0; i < labelsInputsScreening.length; i++) {
            JLabel l = new JLabel(labelsInputsScreening[i]);
            l.setMaximumSize(new Dimension(l.getPreferredSize().width, 5));
            l.setFont(new Font("Montserrat", Font.BOLD, 15));
            screeningForm.add(l);

            //JPanel panel = new JPanel();
            //screeningForm.add(panel);

            if (inputsScreening[i] instanceof JTextField obj) {
                obj.setFont(new Font("Montserrat", Font.BOLD, 15));

                //obj.setMaximumSize(new Dimension(obj.getPreferredSize().width, 5));
                screeningForm.add(obj);
            } else if (inputsScreening[i] instanceof JSpinner obj) {
                obj.setFont(new Font("Montserrat", Font.BOLD, 15));
                obj.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
                obj.setBackground(Color.white);
                //obj.setMaximumSize(new Dimension(50, 15));
                screeningForm.add(obj);
            } else if (inputsScreening[i] instanceof JComboBox<?> obj) {
                obj.setFont(new Font("Montserrat", Font.BOLD, 15));
                obj.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
                obj.setBackground(Color.white);
                //obj.setMaximumSize(new Dimension(obj.getPreferredSize().width, 15));
                screeningForm.add(obj);
            }
        }


        SpringUtilities.makeGrid(screeningForm, inputsScreening.length, 2, 0, 0, 10, 56);
        container.add(screeningForm, BorderLayout.CENTER);

        this.add(container, BorderLayout.CENTER);

        // SOUTH
        Font textFieldFont = new Font("Montserrat", Font.BOLD, 16);
        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(southPanel.getPreferredSize().width, 40));
        southPanel.setLayout(new GridLayout(1, 3, 5, 0));
        southPanel.setBackground(CustomFrame.BGCOLOR);
        btnCancelar = new CustomButton("Cancelar", 10, new Color(208, 29, 29), new Color(189, 26, 26), Color.white, textFieldFont);
        confirmarBtn = new CustomButton("Modificar", 10, new Color(48, 183, 102), Color.LIGHT_GRAY, Color.white, textFieldFont);
        southPanel.add(btnCancelar);
        southPanel.add(confirmarBtn);
        this.add(southPanel, BorderLayout.SOUTH);

        ScreeningFormController.initiate(this);
    }

    @Override
    public void dispose() {

    }
}
