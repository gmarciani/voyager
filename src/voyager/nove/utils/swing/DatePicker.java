package voyager.nove.utils.swing;

import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;

/**
 * @name DatePicker
 *
 * @project Voyager
 *
 * @package voyager.nove.utils.swing
 *
 * @author Giacomo Marciani
 *
 */
public class DatePicker extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private static final int NUM_DAYS = 31;
	private static final int NUM_MONTHS = 12;
	private static final int FIRST_YEAR = 1900;
	private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
	private static final int NUM_YEARS = CURRENT_YEAR - FIRST_YEAR;
	
	private static final Integer[] MONTH_CHOICES = new Integer[NUM_MONTHS];
	private static final Integer[] DAY_CHOICES = new Integer[NUM_DAYS];
	private static final Integer[] YEAR_CHOICES = new Integer[NUM_YEARS];
	
	private JComboBox<Integer> comboDay;
	private JComboBox<Integer> comboMonth;
	private JComboBox<Integer> comboYear;
	
	public DatePicker() {
		super();
		buildPanel();
	}
	
	public DatePicker(GregorianCalendar date) {
		super();
		buildPanel();
		setDateAsGregorianCalendar(date);
	}
	
	public void addActionListener(ActionListener listener) {
		this.comboDay.addActionListener(listener);
		this.comboMonth.addActionListener(listener);
		this.comboYear.addActionListener(listener);
	}
	
	public GregorianCalendar getDateAsGregorianCalendar() {
		int day = comboDay.getSelectedIndex() + 1;
		int month = comboMonth.getSelectedIndex();
		int year = CURRENT_YEAR - comboYear.getSelectedIndex();
		
		return new GregorianCalendar(year, month, day);		
	}
		
	public void setDateAsGregorianCalendar(GregorianCalendar date) {
		comboDay.setSelectedItem(date.get(Calendar.DAY_OF_MONTH));
		comboMonth.setSelectedItem(date.get(Calendar.MONTH) + 1);
		comboYear.setSelectedItem(date.get(Calendar.YEAR));
	}
	
 	private void buildPanel() {
		buildComboChoices();
		comboDay = new JComboBox<Integer>(DAY_CHOICES);
		comboMonth = new JComboBox<Integer>(MONTH_CHOICES);
		comboYear = new JComboBox<Integer>(YEAR_CHOICES);
		
		this.add(comboDay);
		this.add(comboMonth);
		this.add(comboYear);		
	}	
	
	private void buildComboChoices() {
		buildDayChoices();
		buildMonthChoices();
		buildYearChoices();
	}
	
	private void buildDayChoices() {
		
		for (int i = 0; i < NUM_DAYS; i ++) {
			DAY_CHOICES[i] = i + 1;
		}
	}
	
	private void buildMonthChoices() {
		
		for (int i = 0; i < NUM_MONTHS; i ++) {
			MONTH_CHOICES[i] = i + 1;
		}
	}
	
	private void buildYearChoices() {
		
		for (int i = 0; i < NUM_YEARS; i ++) {
			YEAR_CHOICES[i] = CURRENT_YEAR - i;
		}
	}

}
