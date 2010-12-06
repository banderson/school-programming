import java.awt.event.ActionListener;
import javax.swing.JButton;

class SaveButton extends JButton implements Command {
	 
    Mediator med;
    
	SaveButton(ActionListener al, Mediator m) {
		super("Save");
		addActionListener(al);
        med = m;
        med.registerSave(this);
	}
	
	public void execute() {
		med.save();
	}
}