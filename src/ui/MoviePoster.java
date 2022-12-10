package ui;

import model.Movie;
import view.CustomFrame;
import view.PreviewMovie;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class MoviePoster extends JPanel {
    private URL poster;
    private String movie_title;
    private BufferedImage image;

    public int WIDTH = 150, HEIGHT = 225;
    private boolean showTitle = false;
    private JLabel title;

    public MoviePoster(Movie movie, CustomFrame parentframe) {
        this.poster = movie.getTrailerURL();
        this.movie_title = movie.getTituloCartelera();
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setBackground(Color.green);
        this.setLayout(null);

        ImageIcon icon;

        try {
            this.image = ImageIO.read(poster);

            icon = new ImageIcon(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        title = new JLabel(movie_title);
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Montserrat", Font.BOLD, 14));
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                PreviewMovie.selectedMovie = movie;
                parentframe.changeScreen(CustomFrame.Screen.PREVIEWMOVIE);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                showTitle=true;
                add(title);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                showTitle=false;
                remove(title);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.drawImage(image, 0,0, WIDTH,HEIGHT, null);

        if(showTitle) {
            g2.setColor(new Color(0,0,0, 178));
            g2.fillRect(0,0, getWidth(), getHEIGHT());

            g2.setColor(Color.WHITE);
            FontMetrics fm = g.getFontMetrics();

            int hm = 20, vm = 20;
            title.setBounds(hm/2,0, getWidth() - hm, getHeight());
            //title.setOpaque(true);

            /*int stringWidth = fm.stringWidth(movie_title);
            int xDiff = (getWidth() - stringWidth) / 2;
            int yDiff = (getHeight() - fm.getHeight()) / 2;
            g2.setFont(new Font("Montserrat", Font.BOLD, 12));
            g.drawString(movie_title, xDiff, yDiff);*/
        }
    }

    public URL getPoster() {
        return poster;
    }

    public void setPoster(URL poster) {
        this.poster = poster;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }
}
