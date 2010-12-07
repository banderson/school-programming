import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// mediator pattern: http://en.wikipedia.org/wiki/Mediator_pattern
public class Mediator {
	 
	OpenButton openButton;
	SaveButton saveButton;
	QuitButton quitButton;
	WindowDestroyer windowDestroyer;
	JTextArea textArea;
	SearchButton searchButton;
	JTextField searchField;
	JButton replaceButton;
	JTextField replaceField;
	JTextField replaceFindField;
	
	Document document;
	
	public Mediator() {
		document = new Document();
	}
 
    void registerOpen(OpenButton b) {
        openButton = b;
    }
    
    void registerSearch(SearchButton b) {
    	searchButton = b;
    }
    
    void registerSearchInput(JTextField searchInput) {
    	searchField = searchInput;
    }

	void registerReplace(JButton replaceButton, JTextField findText, JTextField replaceText) {
		this.replaceButton = replaceButton;
		replaceField = replaceText;
		replaceFindField = findText;
		
		this.replaceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				replace();
			}
		});
	}
 
    void registerSave(SaveButton b) {
    	saveButton = b;
    }
 
    void registerQuit(QuitButton b) {
    	quitButton = b;
    }
    
    void registerWindowListener(WindowDestroyer wd) {
    	windowDestroyer = wd;
    }
    
    void registerTextArea(JTextArea ta) {
    	textArea = ta;
    }
 
    void open() {
    	//TODO If open is pressed when another file is already open, call save on it before opening new file
    	// populate textarea with text
    	JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Open file");

        if (ret == JFileChooser.APPROVE_OPTION) {
          document.setFile(fileopen.getSelectedFile());
          textArea.setText(document.getContent());
        }
    }
    
    void save() {
    	syncContent();
    	if (!document.isNewFile()) {
    		document.save(textArea.getText());
    	} else {
    		JFileChooser fileopen = new JFileChooser();
    		fileopen.setSelectedFile(new File("NewFileName.txt"));
    		int ret = fileopen.showDialog(null, "Save file");
    		if (ret == JFileChooser.APPROVE_OPTION) {
    			//TODO this call tries to get disk contents and throws exceptions
    			document.setFile(fileopen.getSelectedFile());
    			document.save(textArea.getText());
	        }
    	}
    }
    
    void quit() {
    	syncContent();
    	if (document.hasChanged() && textArea.getText().length() > 0) {
    		int confirmSave = JOptionPane.showConfirmDialog(null, "Save changes before quitting?", "Your Changes Will be Lost", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
    		switch (confirmSave) {
    			case 0: //yes
    				save();
    				break;
    			case 2:	//cancel
    				// prevent program exit
    				return; 
    		}
    	}
    	System.exit(0);
    }
    
    // sync the content cache in the Document class so we can do change tracking
    private void syncContent() {
    	document.setContent(textArea.getText());
    }
    
    void search() {
    	String searchText = searchField.getText();
    	int matchPosition = getNextMatch(searchText);
    	
    	if (matchPosition > 0) {
	    	//highlight the word 
	    	textArea.select(matchPosition, matchPosition + searchText.length());
    	} else {
    		// alert user that match wasn't found
    		alert("'"+ searchText +"' was not found.");
    	}
    }

    void replace() {
    	String replaceText = replaceField.getText();
    	String findText = replaceFindField.getText();
    	
    	if (findText.isEmpty()) {
    		alert("Please enter replacement text.");
    		return;
    	}
    	
    	String newText = textArea.getText().replaceAll(findText, replaceText);
    	textArea.setText(newText);
    }
    
    private int getNextMatch(String searchText) {
    	// +1 to move past current match and don't get same match infinitely
    	int startPosition = textArea.getCaretPosition()+1;
    	return document.getContent().indexOf(searchText, startPosition);
    }
    
    private void alert(String message) {
    	new JOptionPane().showMessageDialog(null, message);
    }
}
