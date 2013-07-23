package voyager.nove.control;

import org.jfree.chart.ChartPanel;

import voyager.nove.exception.IDElementoException;
import voyager.nove.model.indice.IDElemento;
import voyager.nove.model.indice.bean.IDElementoBean;
import voyager.nove.persistence.dao.ErogazioneDAO;
import voyager.nove.persistence.dao.ErogazioneJDBCDAO;
import voyager.nove.plotter.Plotter;

/**
 * @name ControllerComputazioneIndici
 *
 * @project Voyager
 *
 * @package voyager.nove.control
 *
 * @author Giacomo Marciani
 *
 */
public class ControllerComputazioneIndici {
	
	private static ControllerComputazioneIndici singletonControllerComputazioneIndici;
	private static ErogazioneDAO indiceDAO;
	private static Plotter plotter;

	private ControllerComputazioneIndici() {
		indiceDAO = ErogazioneJDBCDAO.getInstance();
		plotter = Plotter.getInstance();
	}

	public static synchronized ControllerComputazioneIndici getInstance() {
		if (singletonControllerComputazioneIndici == null) {
			singletonControllerComputazioneIndici = new ControllerComputazioneIndici();
		}		
		
		return singletonControllerComputazioneIndici;
	}	

	public ChartPanel getPlot(IDElementoBean elementoBean) throws IDElementoException {
		IDElemento elemento = new IDElemento().fromBean(elementoBean);
		long erogazioneElemento = this.getErogazione(elemento);
		long erogazioneSuperElemento = this.getErogazione(elemento.getSuperElemento());
		
		return plotter.plot("", erogazioneElemento, erogazioneSuperElemento);
	}
	
	private long getErogazione(IDElemento elemento) {
		return indiceDAO.read(elemento);
	}
	
}
