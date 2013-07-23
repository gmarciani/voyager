package voyager.nove.view;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.*;

import voyager.nove.control.ControllerHome;
import voyager.nove.model.utente.bean.UtenteBean;
import voyager.nove.model.utente.competenze.Competenza;
import voyager.nove.utils.swing.FrameClosingListener;
import voyager.nove.utils.swing.GBCHelper;

/**
 * @Class BoundaryHome
 *
 * @Project Voyager
 *
 * @Package voyager.nove.view
 *
 * @Author Giacomo Marciani
 *
 */
public class BoundaryHome extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	public static final String TITOLO = "Voyager";
	
	java.net.URL url = ClassLoader.getSystemResource("voyager/nove/res/Voyager.png");
	Toolkit kit = Toolkit.getDefaultToolkit();
	Image img = kit.createImage(url);
	
	private static final String BUONGIORNO_BUONASERA = (Calendar.getInstance().get(Calendar.AM_PM) == Calendar.AM) ? "Buongiorno" : "Buonasera";
	
	private JPanel panelHome;
	private JPanel panelFunzioni;
	private JPanel cardHome;
	
	private JLabel labelVoyager;
	private JLabel labelBenvenuto;
	
	private UtenteBean utenteBean;
	
	private ControllerHome controllerHome;
	
	private int counter;

	public BoundaryHome(UtenteBean utenteBean) {
		this.utenteBean = utenteBean;
		this.controllerHome = ControllerHome.getInstance();
		buildFrame();
	}
	
	private void buildFrame() {
		
		//Initialization
		this.setTitle(TITOLO);
		this.setIconImage(img);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setResizable(true);
		this.setUndecorated(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new FrameClosingListener(this));			
		this.setLayout(new GridBagLayout());		
				
		//Panel Home
		this.panelHome = new JPanel();
		this.panelHome.setLayout(new GridBagLayout());
		counter = 0;
		for (Competenza competenza : this.utenteBean.getRuolo().getCompetenze()) {
			JButton button = new JButton(competenza.asString());
			if (competenza.getId() == Competenza.LOGIN) button.addActionListener(new LogoutListener());
			button.addActionListener(new CompetenzaListener(this.utenteBean, competenza));
			panelHome.add(button, new GBCHelper(0, counter).setWeight(0, 0).setFill(GridBagConstraints.HORIZONTAL).setInsets(0, 0, 20, 0));
			counter ++;
		}	
		
		//Card Home
		this.cardHome = new JPanel();
		this.cardHome.setLayout(new GridBagLayout());
		//cardHome.add(comp);
				
		//Panel Func
		this.panelFunzioni = new JPanel();
		this.panelFunzioni.setLayout(new CardLayout());
		this.panelFunzioni.setBorder(BorderFactory.createEtchedBorder());
		
		this.panelFunzioni.add(cardHome, "Home");
		for (Competenza competenza : this.utenteBean.getRuolo().getCompetenze()) {
			JPanel card = this.controllerHome.getBoundary(utenteBean, competenza.getId());
			this.panelFunzioni.add(card, competenza.asString());
		}	
				
		//Frame Packing
		this.labelVoyager = new JLabel("Voyager");
		this.labelVoyager.setFont(new Font(this.labelVoyager.getFont().getName(), Font.BOLD, 20));
		this.labelBenvenuto = new JLabel(BUONGIORNO_BUONASERA + " " + this.utenteBean.getNome() + "!");
		this.add(this.labelVoyager, new GBCHelper(0, 0).setWeight(0, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER).setInsets(10, 10, 10, 10));
		this.add(this.labelBenvenuto, new GBCHelper(1, 0).setWeight(0, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(10, 10, 10, 10));
		this.add(this.panelHome, new GBCHelper(0, 1).setWeight(0, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.NORTH).setInsets(10, 10, 10, 10));
		this.add(this.panelFunzioni, new GBCHelper(1, 1).setWeight(100, 100).setFill(GridBagConstraints.BOTH).setInsets(10, 10, 10, 10));
		this.pack();		
	}
	
	private class CompetenzaListener implements ActionListener {
		
		private Competenza competenza;
		
		public CompetenzaListener(UtenteBean utenteBean, Competenza competenza) {
			super();
			this.competenza = competenza;			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			mostraBoundary(this.competenza);
		}
	}
	
	private class LogoutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			logout();			
		}
		
	}
	
	private void mostraBoundary(Competenza competenza) {
		CardLayout layoutPanelFunc = (CardLayout) this.panelFunzioni.getLayout();
		layoutPanelFunc.show(this.panelFunzioni, competenza.asString());
	}
	
	private void logout() {
		this.setVisible(false);
		this.controllerHome.logout();
		this.dispose();
	}

}
