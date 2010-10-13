import javax.swing.JApplet;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonDemo extends JApplet implements ActionListener {
	public void init() {
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.cyan);
		contentPane.setLayout(new FlowLayout());
		
		JButton button = new JButton("I'm a Button");
		button.addActionListener(this);
		contentPane.add(button);
		
		JButton button2 = new JButton("I'm another Button");
		button2.addActionListener(this);
		contentPane.add(button2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Container cp = getContentPane();
		
		System.out.println(e.getActionCommand() + " was pressed.");
		
		// print out the name of the button
		if (e.getActionCommand() == "I'm a Button") {
			cp.setBackground(Color.yellow);
		}
		
		if (e.getActionCommand() == "I'm another Button") {
			cp.setBackground(Color.CYAN);
			
			for (int i = 0; i < 25; i++) {
				try {
					Thread.sleep(100);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
				if (i % 2 == 0) {
					cp.setBackground(Color.black);
				} else {
					cp.setBackground(Color.white);
				}
			}
		}
	}
}
