/**
 * Ingenieria en desarrollo de software
 * Proyecto final - Programacion III
 * <p>
 * Emiliano Fernandez Hernandez
 * Kenneth De Guadalupe Quintero Valles
 */

package controller;

import model.Employee;
import model.LoginExceptions;
import view.LoginPanel;

public class LoginController {
    public static void initiate(LoginPanel panel) {
        panel.loginBtn.addActionListener(q -> LoginController.logIn(panel));
    }

    private static void logIn(LoginPanel panel) {

        Employee e = Employee.getEmployee(String.valueOf(panel.userNameField.getPassword()));
        if (e == null) {
            throw new LoginExceptions(String.valueOf(panel.userNameField.getPassword()));
        }
        if (e.comparePassword(String.valueOf(panel.passwordField.getPassword()))) {
            panel.logIn(e);
        } else {
            throw new LoginExceptions();
        }
    }
}
