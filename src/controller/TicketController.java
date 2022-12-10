package controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import model.*;
import view.CustomFrame;
import view.TicketPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Blob;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

public class TicketController {

    public static void initiate(TicketPanel panel) {
        PaymentRecord ticket = Tickets.showPaymentRecord(TicketPanel.compraReciente);
        try {
            getQRimage(panel, ticket.getIdString());
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }

        prepareData(panel, ticket);
        panel.confirmar.addActionListener(e -> panel.parentFrame.changeScreen(CustomFrame.Screen.BILLBOARD));

        TicketPanel.compraReciente = null;
    }

    public static void prepareData(TicketPanel panel, PaymentRecord ticket) {
        Screening horario = Screening.getScreening(PaymentRecord.encontrarHorarioPorIdVenta(ticket.getId()));
        Room sala = Room.obtenerSala(horario.getIdSala());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp fechaHora = Timestamp.valueOf(format.format(horario.getFechaHora()));
        Movie pelicula = Movie.getMovie(horario.getIdPelicula());

        panel.tituloPelicula.setText(pelicula.getTituloCartelera());
        panel.sala.setText("Sala: " + sala.getId());
        panel.cantidadAsientos.setText("Cantidad:" + ticket.getCantidadAsientos());
        panel.fechaInicio.setText(fechaHora.toString());

        System.out.println(pelicula.getTituloCartelera());
    }

    public static void getQRimage(TicketPanel panel, String text) throws WriterException {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(CustomFrame.SECOND_BG_COLOR);

        JLabel imageContainer = new JLabel(new ImageIcon(generateQRcode(text, 350, null)));
        JLabel codeQR = new JLabel(text, SwingConstants.CENTER);

        container.add(imageContainer);
        container.add(codeQR);

        container.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.imageContainer.add(container);
    }

    public static BufferedImage generateQRcode(String text, int size, TicketPanel panel) throws WriterException {
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, size, size, hintMap);

        int matrixWidth = bitMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth,BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = image.createGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0,0, matrixWidth, matrixWidth);
        g2.setColor(CustomFrame.BGCOLOR);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (bitMatrix.get(i,j)) {
                    g2.fillRect(i,j,1,1);
                }
            }
        }

        //panel.imageIcon.setImage(image);
        return image;
    }
}
