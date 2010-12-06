import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Document {
	File file;
	private String content;
	
	void close() {
		
	}
	
	boolean hasChanged() {
		return !content.equals(contents());
	}
	
	void open(File f) {
		file = f;
		content = contents();
	}
	
	void save(String text) throws IOException {
		if (file == null) throw new RuntimeException("You must first select a file");
		System.out.println("starting save: "+ text);
		// update the local cached content
		content = text;
		
		// only save if changed
		if (hasChanged()) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				bw.write(content);
				System.out.println("writing file: "+ content);
				bw.close();
			} catch (IOException e) {
				System.out.println("Fucking file didn't save");
			}
		} else {
			System.out.println("File didn't change, not saving");
		}
	}
	
	File getFile() {
		return file;
	}
	
	// http://www.javapractices.com/topic/TopicAction.do?Id=42
	String contents() {
		if (file == null)
			return "";
		
		StringBuilder contents = new StringBuilder();
	    
	    try {
	      //use buffering, reading one line at a time
	      //FileReader always assumes default encoding is OK!
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
	
	public String toString() {
		return contents();
	}
}
