import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
				file.createNewFile();
			
		} catch (IOException e) {
			throw new RuntimeException("Couldn't write file: "+ e);
		}
		content = getContentFromDisk();
	}
	
	File getFile() {
		return file;
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
	}
	
	// credit: http://www.javapractices.com/topic/TopicAction.do?Id=42
	private String getContentFromDisk() {
		// if there's no file, return empty string
		if (file == null) return "";
		
		StringBuilder contents = new StringBuilder();
	    
	    try {
	      BufferedReader input =  new BufferedReader(new FileReader(file));
	      try {
	        String line = null; //not declared within while loop
	        /*
	        * readLine is a bit quirky :
	        * it returns the content of a line MINUS the newline.
	        * it returns null only for the END of the stream.
	        * it returns an empty String if two newlines appear in a row.
	        */
	        while (( line = input.readLine()) != null){
	          contents.append(line);
	          contents.append(System.getProperty("line.separator"));
	        }
	      }
	      finally {
	        input.close();
	      }
	    }
	    catch (IOException ex){
	      ex.printStackTrace();
	    }
	    
	    return contents.toString();
	}
}
