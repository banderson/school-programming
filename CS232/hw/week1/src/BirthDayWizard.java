import java.util.*;

// simple programming exercise at end of chapter (problem #9)
public class BirthDayWizard {
	
	public static void main(String[] args) {
		int birthYear, nthBirthDay, nthBirthdayYear;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("In what year were you born?");
		birthYear = input.nextInt();
		
		System.out.println("Enter the current year, and I'll tell you which birthday this is.");
		nthBirthdayYear = input.nextInt();
		
		nthBirthDay = nthBirthdayYear - birthYear;
		System.out.println("The person in question is turning " + nthBirthDay + " in "+ nthBirthdayYear +".");
		
	}

}
