package voyager.nove.utils.swing;

import java.awt.*;

/**
 * @name GBCHelper
 *
 * @project Voyager
 *
 * @package voyager.nove.utils.swing
 *
 * @author Giacomo Marciani
 *
 */
public class GBCHelper extends GridBagConstraints {

	private static final long serialVersionUID = 1L;

	public GBCHelper(int gridx, int gridy) {
		this.gridx = gridx;
		this.gridy = gridy;
	}
	
	public GBCHelper(int gridx, int gridy, int gridwidth, int gridheight) {
		this.gridx = gridx;
		this.gridy = gridy;
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
	}
	
	public GBCHelper setAnchor(int anchor) {
		this.anchor = anchor;
		return this;
	}
	
	public GBCHelper setFill(int fill) {
		this.fill = fill;
		return this;
	}
	
	public GBCHelper setWeight (double weightx, double weighty) {
		this.weightx = weightx;
		this.weighty = weighty;
		return this;
	}
	
	public GBCHelper setInsets(int distance) {
		this.insets = new Insets(distance, distance, distance, distance);
		return this;
	}
	
	public GBCHelper setInsets(int top, int left, int bottom, int right) {
		this.insets = new Insets(top, left, bottom, right);
		return this;
	}
	
	public GBCHelper setIpad(int ipadx, int ipady) {
		this.ipadx = ipadx;
		this.ipady = ipady;
		return this;
	}
}
