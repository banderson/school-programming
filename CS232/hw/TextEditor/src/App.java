import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
		
		OpenButton openButton = new OpenButton(this, med);
		SaveButton saveButton = new SaveButton(this, med);
		SearchButton searchButton = new SearchButton(this, med);
		QuitButton quitButton = new QuitButton(this, med);

		JTextArea myText = new JTextArea();
		cp.add(myText, BorderLayout.CENTER);
		
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
		((Command)event.getSource()).execute();
	}
	
	interface Command {
		void execute();
	}
	
	class OpenButton extends JButton implements Command {
		 
	    Mediator med;
	    
		OpenButton(ActionListener al, Mediator m) {
			super("Open");
			addActionListener(al);
	        med = m;
	        med.registerOpen(this);
		}
		
		public void execute() {
			med.open();
		}
	}
	
	class QuitButton extends JButton implements Command {
		 
	    Mediator med;
	    
		QuitButton(ActionListener al, Mediator m) {
			super("Quit");
			addActionListener(al);
	        med = m;
	        med.registerQuit(this);
		}
		
		public void execute() {
			med.quit();
		}
	}
	
	class SearchButton extends JButton implements Command {
		 
	    Mediator med;
	    
		SearchButton(ActionListener al, Mediator m) {
			super("Search");
			addActionListener(al);
	        med = m;
	        med.registerSearch(this);
		}
		
		public void execute() {
			med.search();
		}
	}
	
	class SaveButton extends JButton implements Command {
		 
	    Mediator med;
	    
		SaveButton(ActionListener al, Mediator m) {
			super("Save");
			addActionListener(al);
	        med = m;
	        med.registerSave(this);
		}
		
		public void execute() {
			med.save();
		}
	}
	
	// mediator pattern: http://en.wikipedia.org/wiki/Mediator_pattern
	class Mediator {
	 
		OpenButton openButton;
		SaveButton saveButton;
		SearchButton searchButton;
		QuitButton quitButton;
	 
	    void registerOpen(OpenButton b) {
	        openButton = b;
	    }
	 
	    void registerSearch(SearchButton b) {
	    	searchButton = b;
	    }
	 
	    void registerSave(SaveButton b) {
	    	saveButton = b;
	    }
	 
	    void registerQuit(QuitButton b) {
	    	quitButton = b;
	    }
	 
	    void open() {
	    	openButton.setEnabled(false);
	        saveButton.setEnabled(true);
	        searchButton.setEnabled(true);
	        quitButton.setEnabled(true);
	        //show.setText("booking...");
	    }
	 
	    void save() {
	    	openButton.setEnabled(true);
	    	saveButton.setEnabled(false);
	    	searchButton.setEnabled(true);
	        quitButton.setEnabled(true);
	        //show.setText("viewing...");
	    }
	 
	    void search() {
	    	openButton.setEnabled(true);
	    	saveButton.setEnabled(true);
	    	searchButton.setEnabled(false);
	        quitButton.setEnabled(true);
	        //show.setText("searching...");
	    }
	 
	    void quit() {
	    	openButton.setEnabled(true);
	    	saveButton.setEnabled(true);
	    	searchButton.setEnabled(true);
	        quitButton.setEnabled(false);
	        //show.setText("searching...");
	    }
	}
}