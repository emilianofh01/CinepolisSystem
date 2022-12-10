package view;

import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import controller.TicketController;
import ui.CustomButton;
import ui.RoundContainer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class TicketPanel extends AbstractCinepolisPanel {
    public static Blob compraReciente;
    public JPanel imageContainer;

    public JLabel tituloPelicula;
    public JLabel sala;
    public JLabel cantidadAsientos;
    public JLabel fechaInicio;
    public CustomButton confirmar;
    public TicketPanel(CustomFrame parentFrame) {
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
        /*backBtn = new CustomButton("Volver", 40, new Color(243,127,127),
                new Color(176,92,92), Color.WHITE, new Font("Montserrat", Font.BOLD, 15));
        backBtn.setPreferredSize(new Dimension(125, 40));*/

        //wrapBackBtn.add(backBtn);
        northContainer.add(wrapBackBtn, BorderLayout.LINE_END);

        //northContainer.add(Box.createHorizontalStrut(marginh));
        this.add(northContainer, BorderLayout.NORTH);


        // CENTER
        RoundContainer centerContainer = RoundContainer.createRoundContainer(30, 35, 20, 35, 20, CustomFrame.SECOND_BG_COLOR);
        centerContainer.setLayout(new BorderLayout());

        imageContainer = new JPanel();
        imageContainer.setLayout(new BoxLayout(imageContainer, BoxLayout.X_AXIS));

        //generateQRcode("Hola", 512, this);
        //imageIcon = new ImageIcon();
        //imageContainer.add(new JLabel(imageIcon));
        imageContainer.setPreferredSize(new Dimension(350,512));
        centerContainer.add(imageContainer, BorderLayout.LINE_START);

        JPanel leftCenter = new JPanel();
        leftCenter.setBackground(CustomFrame.SECOND_BG_COLOR);
        leftCenter.setLayout(new BorderLayout());
        JLabel title = new JLabel("¡Compra concretada exitosamente! ");
        title.setForeground(CustomFrame.BGCOLOR);
        title.setFont(new Font("Montserrat", Font.BOLD, 18));
        leftCenter.add(title, BorderLayout.NORTH);

        JPanel recordDetailsContainer = new JPanel();
        recordDetailsContainer.setLayout(new BoxLayout(recordDetailsContainer, BoxLayout.Y_AXIS));
        recordDetailsContainer.setBackground(CustomFrame.SECOND_BG_COLOR);

        recordDetailsContainer.add(Box.createVerticalStrut(50));


        tituloPelicula = new JLabel("El loquito de la luz");
        tituloPelicula.setFont(new Font("Montserrrat", Font.BOLD, 18));
        recordDetailsContainer.add(tituloPelicula);

        recordDetailsContainer.add(Box.createVerticalStrut(40));

        sala = new JLabel("Sala: 3");
        sala.setFont(new Font("Montserrrat", Font.BOLD, 16));
        recordDetailsContainer.add(sala);

        recordDetailsContainer.add(Box.createVerticalStrut(40));

        cantidadAsientos = new JLabel("Cantidad: 32");
        cantidadAsientos.setFont(new Font("Montserrrat", Font.BOLD, 16));
        recordDetailsContainer.add(cantidadAsientos);

        recordDetailsContainer.add(Box.createVerticalStrut(40));


        fechaInicio = new JLabel("5 de febrero 11:30");
        fechaInicio.setFont(new Font("Montserrrat", Font.BOLD, 16));
        recordDetailsContainer.add(fechaInicio);


        leftCenter.add(recordDetailsContainer, BorderLayout.CENTER);

        confirmar = new CustomButton("Finalizar", 15, CustomFrame.BGCOLOR,
                new Color(0, 25, 81), Color.white, new Font("Montserrat", Font.BOLD, 23));
        //confirmar.setMinimumSize(new Dimension(500, 35));
        confirmar.setMaximumSize(new Dimension(Integer.MAX_VALUE,35));
        leftCenter.add(confirmar, BorderLayout.SOUTH);


        centerContainer.add(leftCenter, BorderLayout.CENTER);
        this.add(centerContainer, BorderLayout.CENTER);

        TicketController.initiate(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        imageContainer.setSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Override
    public void dispose() {

    }
}
