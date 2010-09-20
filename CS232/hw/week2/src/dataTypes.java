
public class dataTypes {

	public static void main(String[] args) {
		int x = 345;
		double y = 3.14159265;

		System.out.println("x is "+ x +" y is "+ y);
		System.out.println(x + y);
		System.out.println(Integer.MAX_VALUE);
		
		// hex literal form of the long max-value
		long a = 0x7FFFFFFFFFFFFFFFL;
		//proof 
		System.out.println(a == Long.MAX_VALUE); // => true
		
		String s = "This is a string";
		String z = "This is a string";
		// TODO: why does this work?
		System.out.println(z == s);
	}

}
