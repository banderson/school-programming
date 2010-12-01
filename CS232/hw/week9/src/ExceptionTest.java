
public class ExceptionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int x = 0, y = 0;
		
		try {
			x = 53 / y;
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			System.out.println("This always fires");
		}
		
		//System.out.println("x is "+ x);
		
		try {
			throwIt();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void throwIt() throws MyException {
		throw new MyException();
	}

}
