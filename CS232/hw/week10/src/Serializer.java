import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {

	private SerializeData[] data = new SerializeData[10];
	private String fileName = "seri.txt";
	
	public Serializer() {
		for (int i = 0; i < data.length; i++) {
			data[i] = new SerializeData();
			data[i].age += i;
			
			if (i % 2 == 0) data[i].name = "Benjammin";
			
			if (i % 2 == 0) data[i].town = "Burlington";
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Serializer me = new Serializer();
		me.go();
	}
	
	public void go() {
		writeObjects();
		readObjects();
	}
	
	public void writeObjects() {
		ObjectOutputStream outStream = null;
		
		// open stream to binary file for writing
		try {
			outStream = new ObjectOutputStream(new FileOutputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// write the current objects to disk
		try {
			for (int i = 0; i < data.length; i++) {
				outStream.writeObject(data[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void readObjects() {
		// clear the in-memory array
		for (int i = 0; i < data.length; i++) {
			data[i] = new SerializeData();
		}
		
		// prove we cleared it out
//		for (int i = 0; i < data.length; i++) {
//			System.out.println(data[i]);
//		}
		
		// open a connection to the binary file
		ObjectInputStream inStream = null;
		try {
			inStream = new ObjectInputStream(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// read the results back in from the file
		try {
			for (int i = 0; i < data.length; i++) {
				data[i] = (SerializeData) inStream.readObject();
			}
		} catch (EOFException eof) {
			eof.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// prove it
		System.out.println("Printing out results from file: ");
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
	}
}
