import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JApplet;

public class HappyFace extends JApplet {

	public void paint(Graphics canvas) {
		
		canvas.drawOval(99, 49, 202, 202);
		canvas.setColor(Color.yellow);
		canvas.fillOval(100, 50, 200, 200);
		
		canvas.setColor(Color.black);
		canvas.fillOval(155, 100, 10, 20);
		canvas.fillOval(230, 100, 10, 20);

		canvas.setColor(Color.red);

		canvas.drawArc(150, 160, 100, 50, 180, 180);
	}
}
