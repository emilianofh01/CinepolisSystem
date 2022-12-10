package view;

import controller.PreviewMovieController;
import model.Movie;
import ui.CustomButton;
import ui.RoundContainer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PreviewMovie extends AbstractCinepolisPanel {
    public static Movie selectedMovie;
    public JMenuItem logOutmenuBar, updateTableMenuBar;
    public CustomButton backBtn;
    public JComboBox<Object> horarios;
    public JSpinner cantidad;
    public JLabel precio, diponibilidadLabel;
    public CustomButton confirmar;
    RoundContainer movieContent;

    public PreviewMovie(CustomFrame parentFrame) {
        super(parentFrame);
    }

    @Override
    public void init() {
        this.setBackground(CustomFrame.BGCOLOR);
        this.setLayout(new BorderLayout(0, 15));
        this.setBorder(BorderFactory.createEmptyBorder(25, 50, 50, 50));

        JMenu option = new JMenu("Opciones");

        logOutmenuBar = new JMenuItem("Cerrar sesión");
        updateTableMenuBar = new JMenuItem("Actualizar cartelera");

        option.add(logOutmenuBar);
        option.add(updateTableMenuBar);

        parentFrame.menuBar.add(option);

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
        backBtn = new CustomButton("Volver", 40, new Color(243,127,127),
                new Color(176,92,92), Color.WHITE, new Font("Montserrat", Font.BOLD, 15));
        backBtn.setPreferredSize(new Dimension(125, 40));

        wrapBackBtn.add(backBtn);
        northContainer.add(wrapBackBtn, BorderLayout.LINE_END);

        //northContainer.add(Box.createHorizontalStrut(marginh));
        this.add(northContainer, BorderLayout.NORTH);


        // CENTER (CONTENT)
        movieContent = new RoundContainer(45);
        movieContent.setLayout(new BorderLayout());
        movieContent.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        movieContent.setLayout(new BorderLayout(0, 10));

        JLabel posterContainer = new JLabel();
        posterContainer.setHorizontalAlignment(SwingConstants.CENTER);
        posterContainer.setVerticalAlignment(SwingConstants.CENTER);
        posterContainer.setPreferredSize(new Dimension(334, 501));

        ImageIcon icon;


        try {
            BufferedImage image = ImageIO.read(selectedMovie.getTrailerURL());
            BufferedImage resized = new BufferedImage(334,501, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = resized.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(image, 0,0, 334, 501, null);
            g2.dispose();

            icon = new ImageIcon(resized);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        posterContainer.setIcon(icon);
        movieContent.add(posterContainer, BorderLayout.LINE_START);

        JPanel movieInfo = new JPanel();
        movieInfo.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
        movieInfo.setBackground(CustomFrame.SECOND_BG_COLOR);
        movieInfo.setLayout(new BorderLayout());

        JLabel movieTitle = new JLabel(selectedMovie.getTituloCartelera());
        movieTitle.setHorizontalAlignment(SwingConstants.CENTER);
        movieTitle.setFont(new Font("Montserrat", Font.BOLD, 25));
        movieTitle.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        movieTitle.setBackground(Color.red);
        movieInfo.add(movieTitle, BorderLayout.NORTH);

        JPanel infoCenter = new JPanel();
        infoCenter.setBackground(CustomFrame.SECOND_BG_COLOR);
        infoCenter.setBorder(BorderFactory.createEmptyBorder(25,0,0,0));
        infoCenter.setLayout(new BoxLayout(infoCenter, BoxLayout.Y_AXIS));
        JLabel descripcionTitle = new JLabel("Descripcion");
        descripcionTitle.setMinimumSize(new Dimension(Integer.MAX_VALUE, 30));
        descripcionTitle.setFont(new Font("Montserrat", Font.BOLD, 19));
        infoCenter.add(descripcionTitle);

        JTextArea descripcion = new JTextArea(selectedMovie.getDescripcion());
        descripcion.setFont(new Font("Montserrat", Font.PLAIN, 14));
        descripcion.setForeground(new Color(0,0,0,100));
        //descripcion.setBackground(Color.red);
        descripcion.setEditable(false);
        descripcion.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        descripcion.setWrapStyleWord(true);
        descripcion.setLineWrap(true);
        infoCenter.add(descripcion);

        JPanel horarioContainer = new JPanel();
        horarioContainer.setBackground(CustomFrame.SECOND_BG_COLOR);
        horarioContainer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        horarioContainer.setLayout(new GridLayout(1,2));
        JLabel titleHorario = new JLabel("Horarios: ");
        titleHorario.setFont(new Font("Montserrat", Font.BOLD, 17));
        horarios = new JComboBox<>();
        horarios.addItem("Seleccionar horario");
        horarioContainer.add(titleHorario);
        horarioContainer.add(horarios);
        infoCenter.add(horarioContainer);

        JPanel cantidadContainer = new JPanel();
        cantidadContainer.setBackground(CustomFrame.SECOND_BG_COLOR);
        cantidadContainer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        cantidadContainer.setLayout(new GridLayout(1,2));
        JLabel titleCantidad = new JLabel("Cantidad: ");
        titleCantidad.setFont(new Font("Montserrat", Font.BOLD, 17));
        cantidad = new JSpinner();
        cantidad.setEnabled(false);
        cantidadContainer.add(titleCantidad);
        cantidadContainer.add(cantidad);
        infoCenter.add(cantidadContainer);

        JPanel precioYDisponibilidad = new JPanel();
        precioYDisponibilidad.setBackground(CustomFrame.SECOND_BG_COLOR);
        precioYDisponibilidad.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        precioYDisponibilidad.setLayout(new FlowLayout(FlowLayout.CENTER));

        RoundContainer precioContainer = new RoundContainer(15, new Insets(2,2,2,2), CustomFrame.BGCOLOR);
        precio = new JLabel("$0.00");
        precio.setForeground(Color.white);
        precio.setVerticalAlignment(SwingConstants.CENTER);
        precio.setFont(new Font("Montserrat", Font.BOLD, 15));
        precioContainer.add(precio);
        //precioContainer.setMaximumSize(new Dimension(200, 30));
        precioYDisponibilidad.add(precioContainer);

        RoundContainer disponibilidadContainer = new RoundContainer(15, new Insets(2,2,2,2), CustomFrame.BGCOLOR);
        diponibilidadLabel = new JLabel("Disponibilidad: 0");
        diponibilidadLabel.setForeground(Color.white);
        diponibilidadLabel.setVerticalAlignment(SwingConstants.CENTER);
        diponibilidadLabel.setFont(new Font("Montserrat", Font.BOLD, 15));
        disponibilidadContainer.add(diponibilidadLabel);
        //disponibilidadContainer.setMaximumSize(new Dimension(200, 30));
        precioYDisponibilidad.add(disponibilidadContainer);

        precioYDisponibilidad.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        infoCenter.add(precioYDisponibilidad);

        infoCenter.add(Box.createVerticalStrut(30));
        confirmar = new CustomButton("Comprar", 15, CustomFrame.BGCOLOR,
                new Color(0, 25, 81), Color.white, new Font("Montserrat", Font.BOLD, 23));
        //confirmar.setMinimumSize(new Dimension(500, 35));
        confirmar.setMaximumSize(new Dimension(Integer.MAX_VALUE,35));
        infoCenter.add(confirmar);



        movieInfo.add(infoCenter, BorderLayout.CENTER);


        movieContent.add(movieInfo, BorderLayout.CENTER);
        this.add(movieContent, BorderLayout.CENTER);

        System.out.println(movieInfo.getWidth());

        PreviewMovieController.initiate(this);
    }

    @Override
    public void dispose() {

    }
}
