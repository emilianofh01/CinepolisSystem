import db.MYSQLConnection;
import model.Movie;
import model.Screening;
import view.CustomFrame;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //new CustomFrame(CustomFrame.Screen.LOG_IN);
        MYSQLConnection.getConnection();

        ArrayList<Screening> screen = Screening.screeningList();

        System.out.println(screen);
    }
}

