
public class RecursionTest {

	public static void main(String[] args) {
		RecursionTest me = new RecursionTest();
		
		int x = me.factorial(0);
		
		System.out.println("Recursive sum result: "+ x);
	}
	
	public int sum(int x) {
		System.out.println("Recursively adding "+ x);
		
		if (x == 0) {
			System.out.println("Exiting recursive function");
			return 0;
		}
		System.out.println("Recursively calling with"+ x);
		
		return x + sum(x-1);
	}
	
	public int factorial(int x) {
		System.out.println("factorial "+ x);
		
		if (x <= 1) {
			System.out.println("Exiting factorial");
			return 1;
		}
		System.out.println("factorial with"+ x);
		
		return x * factorial(x-1);
	}

}
