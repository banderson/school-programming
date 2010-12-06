import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class App extends JFrame implements ActionListener {

    Mediator med = new Mediator();

	public static void main(String[] args) {
		App me = new App();
		me.setVisible(true);
	}
	
	public App() {
		setSize(400,300);
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		// create buttons and wire up mediator and actionListener events
		OpenButton openButton = new OpenButton(this, med);
		SaveButton saveButton = new SaveButton(this, med);
		SearchButton searchButton = new SearchButton(this, med);
		QuitButton quitButton = new QuitButton(this, med);
		// this handles cleanup when window is closed
		WindowDestroyer wd = new WindowDestroyer(med);
		addWindowListener(med.windowDestroyer);
		
		// build the layout!
		JTextArea myText = new JTextArea();
		med.registerTextArea(myText);
		cp.add(new JScrollPane(myText), BorderLayout.CENTER);
		
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new FlowLayout());
		myPanel.add(openButton);
		myPanel.add(saveButton);
		myPanel.add(searchButton);
		myPanel.add(quitButton);
		cp.add(myPanel, BorderLayout.PAGE_START);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// get the button clicked and cast as Command
		Command button = (Command)event.getSource(); 
		// execute the command associated to button
		button.execute();
	}
}