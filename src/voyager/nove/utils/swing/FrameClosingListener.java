package voyager.nove.utils.swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
	
/**
 * @name FrameClosingListener
 *
 * @project Voyager
 *
 * @package voyager.nove.utils.swing
 *
 * @author Giacomo Marciani
 *
 */
public class FrameClosingListener extends WindowAdapter {
	
	JFrame parent;
	
	
	public FrameClosingListener(JFrame parent) {
		this.parent = parent;
	}
		
	public void windowClosing(WindowEvent event) {
		int choice = JOptionPane.showConfirmDialog(this.parent, "Sei sicuro di voler uscire?", "Voyager", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (choice == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}		
}
