import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BinaryFile {

	public static void main(String[] args) {
		
		BinaryFile me = new BinaryFile();
		me.doIt();
	}
	
	public void doIt() {
		String fileName = "out.txt";
		ObjectInputStream stream = null;
		ObjectOutputStream outStream = null;
		
		try {
			outStream = new ObjectOutputStream(new FileOutputStream(fileName));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			for (int i = 0; i <= 100; i++) {
				outStream.writeInt(i);
			}
			outStream.flush();
			outStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		
		try {
			stream = new ObjectInputStream(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (true) {
			try {
				int x = stream.read();
				System.out.println(x + ", ");
				
			} catch(EOFException eof) {
				System.out.println("End of file encountered");
				break;
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
	}

}
