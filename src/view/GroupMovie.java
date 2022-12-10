package view;

import controller.BillboardPanelController;
import model.Movie;
import ui.MoviePoster;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GroupMovie extends JPanel {
    private ArrayList<Movie> movieList;
    private CustomFrame parentFrame;
    private int vgap, hgap;

    public GroupMovie(ArrayList<Movie> list, int hgap, int vgap, CustomFrame parentFrame) {
        this.movieList = list;
        this.vgap = vgap;
        this.hgap = hgap;
        this.parentFrame = parentFrame;

        this.setLayout(new FlowLayout(FlowLayout.CENTER, this.hgap, this.vgap));
        this.setBackground(Color.WHITE);
    }

    public GroupMovie(int hgap, int vgap, CustomFrame parentFrame) {
        this.vgap = vgap;
        this.hgap = hgap;
        this.parentFrame = parentFrame;

        this.setLayout(new FlowLayout(FlowLayout.CENTER, hgap,vgap));
        this.setBackground(Color.WHITE);
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(ArrayList<Movie> movieList) {
        this.removeAll();
        this.movieList = movieList;

        int totalHeight = 0;
        int count = 0;

        if(!(movieList == null)) {
            for (Movie value : movieList) {
                MoviePoster movie = new MoviePoster(value, parentFrame);
                count++;

                if(count == 4) {
                    totalHeight += movie.HEIGHT + vgap;
                    count=0;
                }
                this.add(movie);
            }

            this.setPreferredSize(new Dimension(0, totalHeight +20));

        }

        this.repaint();
        this.revalidate();

    }
}
