import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

@Getter
@Setter
public class ImagePanel extends JPanel {
    private BufferedImage image;
    private String text;
    private Long caught;
    private Long total;

    public ImagePanel(String fileName, String text, int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.drawImage(new ImageIcon(fileName).getImage(), 0, 0, width, height, null);
        g2d.dispose();
        this.text = text;
        this.caught = 0L;
        this.total = 0L;
    }

    public ImagePanel(PokedexData data, int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.drawImage(new ImageIcon(data.getGame().getId() + ".png").getImage(), 0, 0, width, height, null);
        g2d.dispose();
        this.text = data.getCaught() + "/" + data.getTotal();
        this.caught = data.getCaught();
        this.total = data.getTotal();
    }

    public void updateData(PokedexData data) {
        this.text = data.getCaught() + "/" + data.getTotal();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 55));
        int x = (getWidth() - g.getFontMetrics().stringWidth(text)) / 2;
        int y = 120;
        g.drawString(text, x, y);
    }
}
