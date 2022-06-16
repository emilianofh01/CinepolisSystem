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

public class ScreeningForm extends AbstractCinepolisPanel {
    public CustomFrame parentFrame;
    public JTextField screeningId;
    public JTextField screeningRoom;
    public JSpinner screeningStart;
    public JComboBox<String> screeningMovieId;

    public JButton btnCancelar, confirmarBtn;

    public ScreeningForm(CustomFrame parentFrame) {
        super(parentFrame);
        this.parentFrame = parentFrame;
    }

    @Override
    public void init() {
        //screeningId = new JTextField(10);
        screeningRoom = new JTextField(10);
        screeningStart = new JSpinner();
        screeningMovieId = new JComboBox<>();

        SpinnerDateModel ScreeningModel = new SpinnerDateModel();
        ScreeningModel.setCalendarField(Calendar.MINUTE);
        screeningStart.setModel(ScreeningModel);
        screeningStart.setEditor(new JSpinner.DateEditor(screeningStart, "h:mm a"));

        SpinnerDateModel movieModel = new SpinnerDateModel();
        movieModel.setCalendarField(Calendar.MINUTE);

        final String[] labelsInputsScreening = new String[]{"Movie", "Room", "Screening start time"};
        Object[] inputsScreening = new Object[]{screeningMovieId, screeningRoom, screeningStart};

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
        RoundContainer container = new RoundContainer(45);
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        container.setLayout(new BorderLayout());


        // Left -> Screening form
        JPanel screeningForm = new JPanel();
        screeningForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        screeningForm.setLayout(new SpringLayout());

        for (int i = 0; i < labelsInputsScreening.length; i++) {
            JLabel l = new JLabel(labelsInputsScreening[i]);
            l.setFont(new Font("Montserrat", Font.BOLD, 15));
            screeningForm.add(l);

            if (inputsScreening[i] instanceof JTextField obj) {
                obj.setFont(new Font("Montserrat", Font.BOLD, 15));
                obj.setMaximumSize(new Dimension(obj.getPreferredSize().width, 20));
                screeningForm.add(obj);
            } else if (inputsScreening[i] instanceof JSpinner obj) {
                obj.setFont(new Font("Montserrat", Font.BOLD, 15));
                obj.setMaximumSize(new Dimension(obj.getPreferredSize().width, 20));
                screeningForm.add(obj);
            } else if (inputsScreening[i] instanceof JComboBox<?> obj) {
                obj.setFont(new Font("Montserrat", Font.BOLD, 15));
                obj.setMaximumSize(new Dimension(obj.getPreferredSize().width, 20));
                screeningForm.add(obj);
            }
        }
        SpringUtilities.makeGrid(screeningForm, inputsScreening.length, 2, 6, 6, 0, 56);
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
