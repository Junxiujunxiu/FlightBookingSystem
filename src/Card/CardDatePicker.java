/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Card;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JPanel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.jdatepicker.impl.UtilDateModel;

public class CardDatePicker extends JPanel {
	private Date date;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;

	/**
	 * Creates a datepicker panel used for selecting dates.
	 * Uses JDatePicker package.
	 * 
	 * @param width of panel
	 * @param height of panel
	 */
	public CardDatePicker(int width, int height) {
		final int WIDTH = width;
		final int HEIGHT = height;
		UtilDateModel model = new UtilDateModel();
//		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		this.datePanel = new JDatePanelImpl(model, p);
		this.datePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		this.add(datePicker); 

		// updates the each time a date is selected.
		datePicker.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.YEAR, getYear());
				calendar.set(Calendar.MONTH, getMonth());
				calendar.set(Calendar.DAY_OF_MONTH, getDay());

				// sets the time to be 00:00:00, only the date info is needed
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				date = calendar.getTime();
			}
		});
	}

	public Date getDate() { return this.date; }
	public void clearDate() { this.datePanel.getModel().setValue(null); }
	private int getYear() { return this.datePanel.getModel().getYear(); }
	private int getMonth() { return this.datePanel.getModel().getMonth(); }
	private int getDay() { return this.datePanel.getModel().getDay(); }
}

class DateLabelFormatter extends AbstractFormatter {

	/**
	 * AbstractFormatter to display the date inside the JDatePanel
	 */
    private String datePattern = "dd/MM/yyyy";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }
        return "";
    }
}