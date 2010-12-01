
public class Fibbonacci {

	int[] myArray = {};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fibbonacci me = new Fibbonacci();
		int x = me.calc(6);
		
		System.out.println(x);
	}
	
	public int calc(int order) {
		
		if (order == 1 || order == 2)
			return 1;
		
		return calc(order-1) + calc(order-2);
	}

}
