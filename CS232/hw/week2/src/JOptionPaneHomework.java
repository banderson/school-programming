import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.Calendar;
import java.util.Date;

public class JOptionPaneHomework {
	public static void main(String[] args) {
	    SimpleDateFormat bdayFormat = new SimpleDateFormat("MM/dd/yyyy");
	    Date bday = new Date();
	    try {
	    	Object selection = JOptionPane.showInputDialog(null, "Enter you birthday (MM/DD/YYYY):", 
	    			"How old are you?", JOptionPane.QUESTION_MESSAGE);
	    	bday = bdayFormat.parse(selection.toString());
		} catch (Exception e) {
			System.out.println("Sorry, you must enter a valid date to play!");
			return;
		}
		
	    Calendar cal1 = bdayFormat.getCalendar(), 	// get a calendar based on users birthday
	    		 cal2 = Calendar.getInstance(); 	// gets a calendar based on today
	    
	    // calculate the difference in time between birthdate and right now
		long yearsOld = (cal2.getTimeInMillis() - cal1.getTimeInMillis())
						/ (24 * 60 * 60 * 1000) 	// convert milliseconds into days
						/ 365;						// convert days into years
		
		// tell the user how old they are
		JOptionPane.showMessageDialog(null, "You are "+ yearsOld+ " years old");
	}
}
