
public class prog1 {
	
	static int a = 0;
	int b = 0;
	
	public static void main(String[] args) {
		prog1 x = new prog1(12);
		prog1 y = new prog1(365);
		System.out.println(a);
	}
	
	public prog1(int c) {
		System.out.println("In constructor");
		a = a +1;
		System.out.println("a is " + a);
		b = b + c;
		System.out.println("b is "+ b);
	}
	
	public void doIt() {
		
	}

}
