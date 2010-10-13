import javax.swing.JApplet;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

public class MyTestApp extends JApplet {
	
	public void init() {
		//Container contentPane = getContentPane();
		//contentPane.setBackground(Color.BLUE);
	}

	public void paint(Graphics canvas) {
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.yellow);
		canvas.setColor(Color.blue);
		
		JLabel label = new JLabel("This is a fucking label!");
		contentPane.add(label);
		
		//canvas.fillRect(100, 100, 200, 200);
	}
}