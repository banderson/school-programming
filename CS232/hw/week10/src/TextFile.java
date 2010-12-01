import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class TextFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextFile t = new TextFile();
		t.doIt();
	}
	
	public void doIt() {
		String fileName = "out.txt";
		PrintWriter stream = null;
		
		try {
			stream = new PrintWriter(fileName);
			stream.println("This is a freaking test");
			stream.println("This is an effing test");
			stream.println("This is a dumb test");
			stream.println("This is a testing test");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		} 
		
		stream.flush();
		stream.close();
		
		
		File file = new File(fileName);
		
		Scanner inputStream = null;
		
		try {
			inputStream = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		
		while (inputStream.hasNextLine()) {
			System.out.println(inputStream.nextLine());
		}
	}

}
