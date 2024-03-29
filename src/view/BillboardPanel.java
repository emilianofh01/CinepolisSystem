package view;

import controller.BillboardPanelController;
import model.CustomTableModel;
import model.Movie;
import ui.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class BillboardPanel extends AbstractCinepolisPanel{

    public JMenuItem logOutmenuBar, updateTableMenuBar;
    public CustomButton backBtn;
    public CustomTextField searchBar;
    public GroupMovie movieList;
    public CustomScrollPane scroll;

    public BillboardPanel(CustomFrame parentFrame) {
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
        RoundContainer billboardContainer = new RoundContainer(45);
        billboardContainer.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 30));
        billboardContainer.setLayout(new BorderLayout(0, 10));

            // TITLE
        JPanel titleContainer = new JPanel();
        titleContainer.setBackground(CustomFrame.SECOND_BG_COLOR);
        titleContainer.setLayout(new BorderLayout());

        JLabel frameTitle = new JLabel("Cartelera");
        frameTitle.setFont(new Font("Montserrat", Font.BOLD, 25));
        frameTitle.setForeground(Color.BLACK);
        titleContainer.add(frameTitle, BorderLayout.LINE_START);

        searchBar = new CustomTextField(15, new Font("Montserrat", Font.BOLD, 16), new Color(226, 226, 226),
                new Dimension(5,5), false, false);
        searchBar.setPlaceholder("Buscar");
        Dimension sizeSearch = searchBar.getSize();
        sizeSearch.width = 200;

        searchBar.setPreferredSize(sizeSearch);
        titleContainer.add(searchBar, BorderLayout.LINE_END);

        billboardContainer.add(titleContainer, BorderLayout.NORTH);


            // MOVIE LIST
        //ArrayList<Movie> movies = Movie.movieList();
        movieList = new GroupMovie(20,20, parentFrame);

        scroll = new CustomScrollPane(movieList);
        billboardContainer.add(scroll);

        this.add(billboardContainer, BorderLayout.CENTER);

        BillboardPanelController.initiate(this);
    }

    @Override
    public void dispose() {

    }
}
