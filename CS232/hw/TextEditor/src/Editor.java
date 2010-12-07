import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 
 * @author Ben Anderson
 * Editor: a simple text editor
 * 
 */
public class Editor extends JFrame implements ActionListener {

    Mediator med = new Mediator();

	public static void main(String[] args) {
		Editor me = new Editor();
		me.setVisible(true);
	}
	
	public Editor() {
		setSize(800,600);
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		// create buttons and wire up mediator and actionListener events
		OpenButton openButton = new OpenButton(this, med);
		SaveButton saveButton = new SaveButton(this, med);
		QuitButton quitButton = new QuitButton(this, med);
		// this handles cleanup when window is closed
		WindowDestroyer wd = new WindowDestroyer(med);
		addWindowListener(med.windowDestroyer);
		
		// build the layout
		JTextArea myText = new JTextArea();
		med.registerTextArea(myText);
		cp.add(new JScrollPane(myText), BorderLayout.CENTER);
		
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new FlowLayout());
		myPanel.add(openButton);
		myPanel.add(saveButton);
		myPanel.add(quitButton);
		cp.add(myPanel, BorderLayout.PAGE_START);
		
		// setup the search functionality
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout());
		SearchButton searchButton = new SearchButton(this, med);
		JTextField searchField = new JTextField(20);
		JLabel searchLabel = new JLabel("Search For: ");
		searchPanel.add(searchLabel);
		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		med.registerSearchInput(searchField);
		
		// setup the replace functionality
		JPanel replacePanel = new JPanel();
		replacePanel.setLayout(new FlowLayout());
		JButton replaceButton = new JButton("Replace All");
		JTextField replaceFindField = new JTextField(15);
		JTextField replaceWithField = new JTextField(15);
		JLabel replaceLabel = new JLabel("Replace: ");
		JLabel replaceLabel2 = new JLabel("With: ");
		replacePanel.add(replaceLabel);
		replacePanel.add(replaceFindField);
		replacePanel.add(replaceLabel2);
		replacePanel.add(replaceWithField);
		replacePanel.add(replaceButton);
		med.registerReplace(replaceButton, replaceFindField, replaceWithField);
		
		JTabbedPane tabs = new JTabbedPane();
		tabs.addTab("Search", null, searchPanel, "Should get updated");
		tabs.addTab("Replace", null, replacePanel, "Should get updated");
		cp.add(tabs, BorderLayout.PAGE_END);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// get the button clicked and cast as Command
		Command button = (Command)event.getSource(); 
		// execute the command associated to button
		button.execute();
	}
}