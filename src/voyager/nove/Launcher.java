package voyager.nove;

import java.awt.EventQueue;

import javax.swing.JFrame;

import voyager.nove.utils.swing.ScreenUtils;
import voyager.nove.view.BoundaryLogin;

/**
 * @name Launcher
 *
 * @project Voyager
 *
 * @package voyager.nove
 *
 * @author Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 *
 */
public class Launcher {

	public Launcher() {}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				BoundaryLogin frame = new BoundaryLogin();
				frame.setLocation(ScreenUtils.getCentralScreenLocation(frame));
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				frame.setVisible(true);				
			}
		});

	}

}
