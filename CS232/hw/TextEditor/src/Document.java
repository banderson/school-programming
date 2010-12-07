import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author ben
 * Class: Document
 * Description: represents a document on the disk, which is compatible with the Editor
 */
public class Document {
	File file;
	private String content = "";
	private final String TEMP_FILENAME = "start.txt";
	private final String TEMP_FILE = "tmp/" + TEMP_FILENAME;
	
	public Document() {
		// start with temp text file that can be saved as a new file
		setFile(new File(TEMP_FILE));
		try {
			if (file.exists())
				save("");	// clear existing file
			else
				file.createNewFile(); // if it doesn't exist
			
		} catch (IOException e) {
			throw new RuntimeException("Couldn't write file: "+ e);
		}
	}

	void setFile(File f) {
		file = f;
		content = getContentFromDisk();
	}
	
	String getContent() {
		return content;
	}
	
	void setContent(String text) {
		content = text;
	}
	
	boolean hasChanged() {
		return !content.equals(getContentFromDisk()) || isNewFile();
	}
	
	boolean isNewFile() {
		// if the file name is still the original temp file, it's new
		return file.getName().equals(TEMP_FILENAME);
	}
	
	void save(String text) {
		if (file == null) 
			throw new RuntimeException("You must first select a file");
		
		// only save if changed
		if (hasChanged()) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				bw.write(content);
				bw.close();
			} catch (IOException e) {
				System.out.println("File didn't save");
			}
		} else {
			System.out.println("File didn't change, skipping save");
		}
		// reset "cached" local content
		content = text;
	}
	
	// read the file contents from disk
	private String getContentFromDisk() {
		// if there's no file, return empty string
		if (file == null || isNewFile()) return "";
		
		String text = "";
	    
	    try {
	      BufferedReader in =  new BufferedReader(new FileReader(file));
	      try {
	        String line = ""; 
	        while ((line = in.readLine()) != null){
	          text += line + "\n"; // readline() doesn't include newline?
	        }
	      }
	      finally {
	        in.close();
	      }
	    }
	    catch (IOException ex){
	      ex.printStackTrace();
	    }
	    
	    return text;
	}
}
