package vs1;

import java.awt.*;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
    public MyPanel() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//		Graphics2D g2d = (Graphics2D) g;
//
//		// prostokat
//		g2d.drawRect(10, 10, 380, 380);
//		// kolo
//		g2d.drawOval(10, 10, 380, 380);
    }
}