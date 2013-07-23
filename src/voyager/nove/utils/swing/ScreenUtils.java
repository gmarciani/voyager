package voyager.nove.utils.swing;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;

/**
 * @name ScreenUtils
 *
 * @project Voyager
 *
 * @package voyager.nove.utils.swing
 *
 * @author Giacomo Marciani
 *
 */
public class ScreenUtils {

	public static Point getCentralScreenLocation(Window window) {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int componentWidth = window.getWidth();
		int componentHeight = window.getHeight();
		int x = (screenWidth / 2) - (componentWidth / 2);
		int y = (screenHeight / 2) - (componentHeight / 2);
		
		return new Point(x, y);
	}

}
