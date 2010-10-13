
public class ClassTest {
	
	static int ticketCount;
	static final int STAT_COUNT = 14000;
	
	int localTicketCount;

	public static void main(String[] args) {
		ClassTest cashier1 = new ClassTest();
		ClassTest cashier2 = new ClassTest();
		
		ticketCount = 0;
		
		cashier1.doIt();
		cashier2.doIt();
		
		cashier1.sellTicket(10);
		cashier2.sellTicket(25);
		
		System.out.println(cashier1.statusReport());
		System.out.println(cashier2.statusReport());
	}
	
	void doIt() {
		localTicketCount = 0;
	}
	
	void sellTicket(int numberOfTickets) {
		localTicketCount += numberOfTickets;
		ticketCount += numberOfTickets;
	}
	
	void resetSystem() {
		ticketCount = 0;
	}
	
	String statusReport() {
		String rtn;
		rtn = "Total tickets sold to the concert is: \t\t" + ticketCount;
		rtn += "\nTotal tickets sold by me: \t\t\t"+ localTicketCount;
		
		return rtn;
	}

}
