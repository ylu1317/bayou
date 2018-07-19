package edu.rice.pliny.apitrans.examples;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;

public class GraphicsTest {

    public void window(Graphics2D g2d, String filename) throws IOException {
        int x = 0;
        int y = 1;
        int rx = 0;
        int ry = 1;
        int rw = 2;
        int rh = 3;
        File img_file = new File(filename);

        g2d.drawRect(rx, ry, rw, rh);
        BufferedImage img = ImageIO.read(img_file);
        g2d.drawImage(img, x, y, null);
    }

    public void window2(Graphics g, String filename) throws SlickException {
        float fx = 0;
        float fy = 1;
        float frx = 0;
        float fry = 1;
        float frw = 2;
        float frh = 3;
        File img_file = new File(filename);

        g.drawRect(frx, fry, frw, frh);
        Image img = new Image(filename);
        g.drawImage(img, fx, fy);
    }
}
