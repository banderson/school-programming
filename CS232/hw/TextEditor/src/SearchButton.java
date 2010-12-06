import java.awt.event.ActionListener;
import javax.swing.JButton;

class SearchButton extends JButton implements Command {
	 
    Mediator med;
    
	SearchButton(ActionListener al, Mediator m) {
		super("Search");
		addActionListener(al);
        med = m;
        med.registerSearch(this);
	}
	
	public void execute() {
		med.search();
	}
}