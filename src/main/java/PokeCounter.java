import javax.swing.*;

public class PokeCounter {

    public static void main(String[] args) {
        PokedexData pokedexData = new PokedexData();
        pokedexData.update();

        JFrame frame = new JFrame();
        frame.setTitle("Pokedex");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(265, 288);

        ImagePanel panel = new ImagePanel(pokedexData, 250, 250);
        frame.add(panel);

        Timer timer = new Timer(60000, e -> {
            pokedexData.update();
            panel.updateData(pokedexData);
            panel.repaint();
        });
        timer.start();

        frame.setVisible(true);
    }
}
