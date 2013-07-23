package voyager.nove.utils.swing;

import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;
import java.util.Calendar;

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
public class DateTimePicker extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private static java.text.SimpleDateFormat SDF = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static final int NUM_DAYS = 31;
	private static final int NUM_MONTHS = 12;
	private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
	private static final int NUM_YEARS = 10;
	private static final int NUM_HOURS = 24;
	private static final int NUM_MINUTES = 60;
	
	private static final Integer[] MONTH_CHOICES = new Integer[NUM_MONTHS];
	private static final Integer[] DAY_CHOICES = new Integer[NUM_DAYS];
	private static final Integer[] YEAR_CHOICES = new Integer[NUM_YEARS];
	private static final Integer[] HOUR_CHOICES = new Integer[NUM_HOURS];
	private static final Integer[] MINUTE_CHOICES = new Integer[NUM_MINUTES];
	
	private JComboBox<Integer> comboDay;
	private JComboBox<Integer> comboMonth;
	private JComboBox<Integer> comboYear;
	private JComboBox<Integer> comboHour;
	private JComboBox<Integer> comboMinute;
	
	public DateTimePicker() {
		super();
		buildPanel();
	}
	
	public DateTimePicker(Date date) {
		super();
		buildPanel();
		setDate(date);
	}
	
	public void addActionListener(ActionListener listener) {
		this.comboDay.addActionListener(listener);
		this.comboMonth.addActionListener(listener);
		this.comboYear.addActionListener(listener);
		this.comboHour.addActionListener(listener);
		this.comboMinute.addActionListener(listener);
	}
	
	public Date getDate() {
		int day = comboDay.getSelectedIndex() + 1;
		int month = comboMonth.getSelectedIndex() + 1;
		int year = CURRENT_YEAR - comboYear.getSelectedIndex();
		int hour = comboHour.getSelectedIndex() + 1;
		int minute = comboMinute.getSelectedIndex();
		
		String dateString = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":00";
		
		Date date = null;
		
		try {
			date = (Date) SDF.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;		
	}
		
	public void setDate(Date date) {
		String dateString = SDF.format(date);
		
		String strs[] = dateString.split(" ");
		String ymd[] = strs[0].split("-");
		String hms[] = strs[1].split(":");
				
		comboDay.setSelectedItem(Integer.valueOf(ymd[2]));
		comboMonth.setSelectedItem(Integer.valueOf(ymd[1]));
		comboYear.setSelectedItem(Integer.valueOf(ymd[0]));
		comboHour.setSelectedItem(Integer.valueOf(hms[0]));
		comboMinute.setSelectedItem(Integer.valueOf(hms[1]));		
	}
	
 	private void buildPanel() {
		buildComboChoices();
		comboDay = new JComboBox<Integer>(DAY_CHOICES);
		comboMonth = new JComboBox<Integer>(MONTH_CHOICES);
		comboYear = new JComboBox<Integer>(YEAR_CHOICES);
		comboHour = new JComboBox<Integer>(HOUR_CHOICES);
		comboMinute = new JComboBox<Integer>(MINUTE_CHOICES);
		
		this.add(comboDay);
		this.add(comboMonth);
		this.add(comboYear);	
		this.add(comboHour);
		this.add(comboMinute);
	}	
	
	private void buildComboChoices() {
		buildDayChoices();
		buildMonthChoices();
		buildYearChoices();
		buildHourChoices();
		buildMinuteChoices();
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
			YEAR_CHOICES[i] = CURRENT_YEAR + i;
		}
	}
	
	private void buildHourChoices() {
		
		for (int i = 0; i < NUM_HOURS; i ++) {
			HOUR_CHOICES[i] = i + 1;
		}
		
	}
	
	private void buildMinuteChoices() {
		
		for (int i = 0; i < NUM_MINUTES; i ++) {
			MINUTE_CHOICES[i] = i;
		}
		
	}

}
