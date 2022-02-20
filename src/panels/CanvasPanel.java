package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CanvasPanel extends JPanel {

    private Image image = null;
    private int iWidth2;
    private int iHeight2;

    public CanvasPanel()
    {
    }

    private BufferedImage getScaledImage(Image srcImg, int w, int h){

        //Create a new image with good size that contains or might contain arbitrary alpha values between and including 0.0 and 1.0.
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);

        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        g2.drawImage(srcImg, 0, 0, w, h, null);

        g2.dispose();

        return resizedImg;
    }

    public void setImage(Image image, int w, int h) {
        this.image = getScaledImage(image, w, h);
        this.iWidth2 = image.getWidth(this)/2;
        this.iHeight2 = image.getHeight(this)/2;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (image != null)
        {
            //int x = this.getParent().getWidth()/2 - iWidth2;
            //int y = this.getParent().getHeight()/2 - iHeight2;
            g.drawImage(image,0,0,this);
        }
    }


}
