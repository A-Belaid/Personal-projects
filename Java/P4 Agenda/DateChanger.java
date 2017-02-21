import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DateChanger {

	public JFrame frmChangeDate;
	
	private P4Agenda agenda_;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DateChanger window = new DateChanger(new P4Agenda());
					window.frmChangeDate.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DateChanger(P4Agenda agenda) {
		agenda_ = agenda;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChangeDate = new JFrame();
		frmChangeDate.setTitle("Change date");
		frmChangeDate.setBounds(100, 100, 300, 180);
		frmChangeDate.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmChangeDate.getContentPane().setLayout(null);
		
		JLabel lblChangeDateTo = new JLabel("Change date to: ");
		lblChangeDateTo.setBounds(10, 34, 106, 14);
		frmChangeDate.getContentPane().add(lblChangeDateTo);
		
		JComboBox dayBox = new JComboBox();
		dayBox.setBounds(209, 31, 65, 20);
		frmChangeDate.getContentPane().add(dayBox);
		
		JComboBox monthBox = new JComboBox();
		monthBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dayBox.removeAllItems();
				switch((String) monthBox.getSelectedItem())
				{
				case "3":
					dayBox.addItem("20");
					break;
				case "4":
					for(int i = 11; i <= 30; i++)
						dayBox.addItem(Integer.toString(i));
					break;
				case "5": case "7": case "8": case "10":
					for(int i = 1; i <= 31; i++)
						dayBox.addItem(Integer.toString(i));
					break;
				case "6": case "9": case "11":
					for(int i = 1; i <= 30; i++)
						dayBox.addItem(Integer.toString(i));
					break;
				case "12":
					for(int i = 1; i <= 24; i++)
						dayBox.addItem(Integer.toString(i));
					break;
				}
			}
		});
		monthBox.setBounds(126, 31, 45, 20);
		frmChangeDate.getContentPane().add(monthBox);
		
		for(int i = 3; i <= 12; i++)
			monthBox.addItem(Integer.toString(i));
		
		monthBox.setSelectedItem(Integer.toString(agenda_.getDay().getDateMonth()));
		dayBox.setSelectedItem(Integer.toString(agenda_.getDay().getDateDay()));
		
		JLabel label = new JLabel("/");
		label.setBounds(185, 34, 14, 14);
		frmChangeDate.getContentPane().add(label);
		
		JRadioButton rdbtnDaytime = new JRadioButton("Daytime/After school");
		rdbtnDaytime.setBounds(126, 55, 152, 23);
		frmChangeDate.getContentPane().add(rdbtnDaytime);
		rdbtnDaytime.setSelected(agenda_.getDay().getIsDaytime());
		
		JRadioButton rdbtnEvening = new JRadioButton("Evening");
		rdbtnEvening.setBounds(126, 81, 109, 23);
		frmChangeDate.getContentPane().add(rdbtnEvening);
		rdbtnEvening.setSelected(!agenda_.getDay().getIsDaytime());
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean wentToTV = false;
				String selectedMonth = (String)(monthBox.getSelectedItem());
				String selectedDay = (String)(dayBox.getSelectedItem());
				
				if(selectedMonth.equals(Integer.toString(agenda_.getDay().getDateMonth()))
						&& selectedDay.equals(Integer.toString(agenda_.getDay().getDateDay())))
						wentToTV = agenda_.getDay().getWentToTV();
				
				agenda_.setDay(Integer.parseInt(selectedMonth),
						Integer.parseInt(selectedDay), 
						rdbtnDaytime.isSelected(), wentToTV);
				
				agenda_.update(true);
				frmChangeDate.dispose();
			}
		});
		btnOk.setBounds(27, 111, 89, 23);
		frmChangeDate.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmChangeDate.dispose();
			}
		});
		btnCancel.setBounds(165, 111, 89, 23);
		frmChangeDate.getContentPane().add(btnCancel);
		
		JLabel lblChangePeriodTo = new JLabel("Change period to:");
		lblChangePeriodTo.setBounds(10, 59, 106, 14);
		frmChangeDate.getContentPane().add(lblChangePeriodTo);
		
		ButtonGroup periodGroup = new ButtonGroup();
		periodGroup.add(rdbtnDaytime);
		periodGroup.add(rdbtnEvening);
		
	}
}
