package controller;

import com.google.zxing.WriterException;
import model.Screening;
import model.Tickets;
import ui.ComboBoxEditorScreaning;
import ui.ListCellRendererScreaning;
import view.CustomFrame;
import view.PreviewMovie;
import view.TicketPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Objects;

public class PreviewMovieController {
    public static void initiate(PreviewMovie panel) {
        panel.confirmar.setEnabled(false);
        obtenerHorarios(panel);
        panel.horarios.addActionListener(e -> seleccionarHorario(panel));
        panel.backBtn.addActionListener(e -> panel.parentFrame.changeScreen(CustomFrame.Screen.BILLBOARD));
        panel.confirmar.addActionListener(e -> {
            try {
                comprar(panel);
            } catch (WriterException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.cantidad.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int cantidad = Integer.parseInt(panel.cantidad.getValue().toString());
                BigDecimal costoPelicula = ((Screening) Objects.requireNonNull(panel.horarios.getSelectedItem())).getCosto();
                int disponibilidad = Screening.obtenerDisponibilidad(((Screening)panel.horarios.getSelectedItem()).getId());

                BigDecimal total = costoPelicula.multiply(BigDecimal.valueOf(cantidad));
                total = total.setScale(2, RoundingMode.CEILING);
                panel.precio.setText(cantidad <= 0 ? "$0.00" : "$"+total);

                if((int)panel.cantidad.getValue() <= 0) {
                    panel.confirmar.setEnabled(false);
                } else {
                    panel.confirmar.setEnabled(cantidad > 0 && cantidad <= disponibilidad);
                    if(cantidad > disponibilidad) {
                        JOptionPane.showMessageDialog(panel,"La cantidad de boletos es mayor a la disponible, favor de seleccionar hasta la cantidad disponible");
                    }
                }
            }
        });
    }

    public static void comprar(PreviewMovie panel) throws WriterException {
        ArrayList<Tickets> tickets = new ArrayList<>();
        double monto = Double.parseDouble(panel.precio.getText().replace("$",""));
        int cantidad = Integer.parseInt(panel.cantidad.getValue().toString());

        if(Screening.obtenerDisponibilidad(((Screening)panel.horarios.getSelectedItem()).getId()) <= cantidad) {
            if(JOptionPane.showConfirmDialog(panel, "¿Estas seguro de hacer esta compra?") == 0) {
                Blob codigoVen = Tickets.generateUUID();

                Tickets.createReport(codigoVen, cantidad, monto);

                for (int i = 0; i < cantidad; i++) {
                    tickets.add(new Tickets(((Screening)panel.horarios.getSelectedItem()).getId(), "A", codigoVen));
                }

                Tickets.buyTickets(tickets);
                TicketPanel.compraReciente = codigoVen;
                panel.parentFrame.changeScreen(CustomFrame.Screen.TICKETS);
            }
        } else {
            JOptionPane.showMessageDialog(panel,"La cantidad de boletos es mayor a la disponible, favor de seleccionar hasta la cantidad disponible");
        }
    }

    public static void obtenerHorarios(PreviewMovie panel) {
        ArrayList<Screening> funciones = Screening.screeningList(PreviewMovie.selectedMovie.getId());

        //panel.horarios.setEditable(true);
        panel.horarios.setRenderer(new ListCellRendererScreaning());
        panel.horarios.setEditor(new ComboBoxEditorScreaning());
        funciones.forEach(screening -> panel.horarios.addItem(screening));
        //System.out.println(Arrays.toString(funciones.stream().toArray()));
    }

    public static void seleccionarHorario(PreviewMovie panel) {
        if(panel.horarios.getSelectedIndex() <= 0) {
            panel.cantidad.setEnabled(false);
            panel.cantidad.setValue(0);
            panel.confirmar.setEnabled(false);
            panel.precio.setText("$0.00");
            panel.diponibilidadLabel.setText("Disponibilidad: 0");
        } else {
            int disponibilidad = Screening.obtenerDisponibilidad(((Screening)panel.horarios.getSelectedItem()).getId());
            panel.cantidad.setEnabled(true);
            panel.diponibilidadLabel.setText(String.format("Disponibilidad: %d", disponibilidad));

            //panel.confirmar.setEnabled(true);
        }
        System.out.println(panel.horarios.getSelectedItem());
    }
}
