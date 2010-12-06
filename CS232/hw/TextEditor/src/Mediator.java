import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

// mediator pattern: http://en.wikipedia.org/wiki/Mediator_pattern
public class Mediator {
	 
	OpenButton openButton;
	SaveButton saveButton;
	SearchButton searchButton;
	QuitButton quitButton;
	WindowDestroyer windowDestroyer;
	JTextArea textArea;
	
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
    	// populate textarea with text
    	JFileChooser fileopen = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("c files", "c");
        fileopen.addChoosableFileFilter(filter);
        int ret = fileopen.showDialog(null, "Open file");

        if (ret == JFileChooser.APPROVE_OPTION) {
          document.open(fileopen.getSelectedFile());
          textArea.setText(document.toString());
        }
    }
 
    void save() {
    	System.out.println("file changed?: "+ document.hasChanged());
    	try {
    		System.out.println(textArea.getText()+ " is the text");
    		document.save(textArea.getText());
    	} catch(IOException e) {
    		System.out.println("Couldn't save file");
    	}
    }
 
    void search() {
    }
    
    void quit() {
    	System.exit(0);
    }
}
