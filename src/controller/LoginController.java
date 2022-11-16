/**
 * Ingenieria en desarrollo de software
 * Proyecto final - Programacion III
 * <p>
 * Emiliano Fernandez Hernandez
 * Kenneth De Guadalupe Quintero Valles
 */

package controller;

import db.MYSQLConnection;
import model.Employee;
import model.LoginExceptions;
import view.LoginPanel;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;

public class LoginController {
    public static void initiate(LoginPanel panel) {
        if(panel.loginBtn != null)
            panel.loginBtn.addActionListener(q -> {
                try {
                    LoginController.logIn(panel);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            });
        if(panel.tryAgainBtn != null)
            panel.tryAgainBtn.addActionListener(q -> LoginController.tryAgain(panel));
    }

    private static void tryAgain(LoginPanel panel) {
        MYSQLConnection.getConnection();
        panel.tryAgain();
    }

    private static void logIn(LoginPanel panel) throws NoSuchAlgorithmException {
        Employee e = Employee.getEmployee(String.valueOf(panel.userNameField.getPassword()));
        if (e == null) {
            JOptionPane.showMessageDialog(panel, "Usuario o contraseña incorrectos. Intentar de nuevo");
            throw new LoginExceptions(String.valueOf(panel.userNameField.getPassword()));
        }
        if (e.comparePassword(String.valueOf(panel.passwordField.getPassword()))) {
            panel.logIn(e);
        } else {
            JOptionPane.showMessageDialog(panel, "Usuario o contraseña incorrectos. Intentar de nuevo");
            throw new LoginExceptions();
        }
    }
}
