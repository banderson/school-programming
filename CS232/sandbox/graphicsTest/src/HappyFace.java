import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JApplet;

public class HappyFace extends JApplet {

	public void paint(Graphics canvas) {
		canvas.drawOval(50, 50, 300, 300);
		canvas.fillOval(155, 100, 10, 20);
		canvas.fillOval(255, 100, 10, 20);

		canvas.setColor(Color.RED);

		canvas.drawArc(150, 260, 100, 50, 180, 180);
	}
}
