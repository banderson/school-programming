import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GraphicalApplication extends JFrame implements ActionListener {

	public static void main(String[] args) {
		GraphicalApplication me = new GraphicalApplication();
		me.setVisible(true);
	}
	
	public GraphicalApplication() {
		setSize(300,300);
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		JButton openButton = new JButton("Open");
		JButton saveButton = new JButton("Save");
		JButton myButton3 = new JButton("Hi there 3");
		JButton myButton4 = new JButton("Hi there 4");
		JButton myButton5 = new JButton("Hi there 5");
		JButton myButton6 = new JButton("Hi there 6");

		JTextField myText = new JTextField();
		cp.add(myText, BorderLayout.CENTER);
		
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new FlowLayout());
		myPanel.add(myButton3);
		myPanel.add(myButton6);
		cp.add(myPanel, BorderLayout.PAGE_END);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
