import java.io.Serializable;


public class SerializeData implements Serializable {
	
	public String name, town;
	public int age;
	
	public SerializeData() {
		this.town = "Boston";
		this.age = 29;
		this.name = "Ben Anderson";
	}
	
	public SerializeData(String name, String town, int age) {
		this.town = town;
		this.age = age;
		this.name = name;
	}
	
	public String toString() {
		return "Hi, my name's "+ name +", I'm "+ age +" years old and I live in "+ town;
	}
}
