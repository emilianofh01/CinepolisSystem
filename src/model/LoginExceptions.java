/**
 * Ingenieria en desarrollo de software
 * Proyecto final - Programacion III
 * <p>
 * Emiliano Fernandez Hernandez
 * Kenneth De Guadalupe Quintero Valles
 */

package model;

public class LoginExceptions extends RuntimeException {
    public LoginExceptions() {
        super("El usuario y contraseña no coinciden");
    }

    public LoginExceptions(String user) {
        super("No existe coincidencias con el usuario '" + user + "'");
    }
}
