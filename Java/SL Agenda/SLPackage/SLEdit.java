package SLPackage;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import SLPackage.SLink.Day;
import SLPackage.SLink.Time;


public class SLEdit {

	JFrame frame;
	JTextField textField;
	
	private String arcanaNames[] = {"FOOL", "MAGICIAN", "PRIESTESS", "EMPRESS", "EMPEROR", "HIEROPHANT", "LOVERS", "CHARIOT",
			"STRENGTH", "HUNGER", "HERMIT", "FORTUNE", "JUSTICE", "HANGED", "DEATH", "TEMPERANCE", "DEVIL", "TOWER", "STAR", "MOON", "SUN",
			"JUDGEMENT", "WORLD", "AEON"};
	
	private SLAgenda agenda_;
	private SLink link_;
	private SLList list_;
	
	private boolean isNewLink;
	
	private JComboBox arcanaBox;
	private JComboBox editBox_;
	
	JRadioButton rdbtn_rank1;
	JRadioButton rdbtn_rank2;
	JRadioButton rdbtn_rank3;
	JRadioButton rdbtn_rank4;
	JRadioButton rdbtn_rank5;
	JRadioButton rdbtn_rank6;
	JRadioButton rdbtn_rank7;
	JRadioButton rdbtn_rank8;
	JRadioButton rdbtn_rank9;
	JRadioButton rdbtnMax;
	
	JCheckBox chckbxM;
	JCheckBox chckbxTu;
	JCheckBox chckbxW;
	JCheckBox chckbxTh;
	JCheckBox chckbxF;
	JCheckBox chckbxSa;
	JCheckBox chckbxSu;
	
	JCheckBox chckbxAvailableHoldidays;
	JCheckBox chckbxAvailableRainy;
	JCheckBox chckbxAvailableExams;
	
	JRadioButton rdbtnAvailableDaytime;
	JRadioButton rdbtnAvailableEvening;
	
	JRadioButton rdbtnAvailableAnyDay;
	JRadioButton rdbtnAvailableHolidaysOnly;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SLEdit window = new SLEdit(new SLAgenda(new SLList()), true);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public SLEdit(SLAgenda agenda, boolean isNewLink)
	{
		agenda_ = agenda;
		
		list_ = agenda_.getList();
		
		editBox_ = agenda_.getLinkEditBox();
		
		this.isNewLink = isNewLink;
		
		if(isNewLink)
			link_ = new SLink();
		else
			link_ = agenda_.getEditLink();
		
		initialize();
	}
	
	public void resetFields()
	{
		//Reset Arcana
		arcanaBox.setSelectedItem(link_.getArcana());
		
		//Reset name
		textField.setText(link_.getName());
		
		//Reset Rank
		switch(link_.getRank())
		{
		case 1:
			rdbtn_rank1.setSelected(true);break;
		case 2:
			rdbtn_rank2.setSelected(true);break;
		case 3:
			rdbtn_rank3.setSelected(true);break;
		case 4:
			rdbtn_rank4.setSelected(true);break;
		case 5:
			rdbtn_rank5.setSelected(true);break;
		case 6:
			rdbtn_rank6.setSelected(true);break;
		case 7:
			rdbtn_rank7.setSelected(true);break;
		case 8:
			rdbtn_rank8.setSelected(true);break;
		case 9:
			rdbtn_rank9.setSelected(true);break;
		case 10:
			rdbtnMax.setSelected(true);break;
		}
		
		//Reset availabilty
		if(link_.getAvailableHolidayOnly())
			rdbtnAvailableHolidaysOnly.setSelected(true);
		else
			{
				rdbtnAvailableAnyDay.setSelected(true);
				
				//Reset availability days
				chckbxM.setSelected(link_.isAvailableInDay(Day.DAY_M));
				chckbxTu.setSelected(link_.isAvailableInDay(Day.DAY_TU));
				chckbxW.setSelected(link_.isAvailableInDay(Day.DAY_W));
				chckbxTh.setSelected(link_.isAvailableInDay(Day.DAY_TH));
				chckbxF.setSelected(link_.isAvailableInDay(Day.DAY_F));
				chckbxSa.setSelected(link_.isAvailableInDay(Day.DAY_SA));
				chckbxSu.setSelected(link_.isAvailableInDay(Day.DAY_SU));
				
				//Reset availabilty day period
				rdbtnAvailableDaytime.setSelected(link_.isAvailableInTime(Time.DAYTIME));
				rdbtnAvailableEvening.setSelected(link_.isAvailableInTime(Time.EVENING));
			}
		
		//Reset availabitily options
		chckbxAvailableHoldidays.setSelected(link_.getAvailableHoliday());
		chckbxAvailableRainy.setSelected(link_.getAvailableRain());
		chckbxAvailableExams.setSelected(link_.getAvailableExams());
	}
	
	public byte getSelectedRank()
	{
		if(rdbtn_rank1.isSelected())
			return 1;
		else if(rdbtn_rank2.isSelected())
			return 2;
		else if(rdbtn_rank3.isSelected())
			return 3;
		else if(rdbtn_rank4.isSelected())
			return 4;
		else if(rdbtn_rank5.isSelected())
			return 5;
		else if(rdbtn_rank6.isSelected())
			return 6;
		else if(rdbtn_rank7.isSelected())
			return 7;
		else if(rdbtn_rank8.isSelected())
			return 8;
		else if(rdbtn_rank9.isSelected())
			return 9;
		else if(rdbtnMax.isSelected())
			return 10;
		
		return 1;
	}
	
	public void setSelectedDays()
	{
		link_.getAvailableDays().clear();
		
		if(chckbxM.isSelected())
			link_.addDay(Day.DAY_M);
		if(chckbxTu.isSelected())
			link_.addDay(Day.DAY_TU);
		if(chckbxW.isSelected())
			link_.addDay(Day.DAY_W);
		if(chckbxTh.isSelected())
			link_.addDay(Day.DAY_TH);
		if(chckbxF.isSelected())
			link_.addDay(Day.DAY_F);
		if(chckbxSa.isSelected())
			link_.addDay(Day.DAY_SA);
		if(chckbxSu.isSelected())
			link_.addDay(Day.DAY_SU);
	}
	
	public void setSelectedPeriod()
	{
		if(rdbtnAvailableDaytime.isSelected())
			link_.setAvailableTime(Time.DAYTIME);
		if(rdbtnAvailableEvening.isSelected())
			link_.setAvailableTime(Time.EVENING);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Create/Edit Social Link");
		frame.setBounds(100, 100, 465, 419);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblArcana = new JLabel("Arcana:");
		lblArcana.setBounds(10, 11, 46, 14);
		frame.getContentPane().add(lblArcana);
		
		arcanaBox = new JComboBox();
		arcanaBox.setBounds(70, 8, 106, 20);
		frame.getContentPane().add(arcanaBox);
		for(byte i = 0; i < arcanaNames.length; i++)
		{
			arcanaBox.addItem(arcanaNames[i]);
		}
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(196, 11, 46, 14);
		frame.getContentPane().add(lblName);
		
		textField = new JTextField();
		textField.setBounds(244, 8, 195, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblRank = new JLabel("Rank:");
		lblRank.setBounds(10, 55, 46, 14);
		frame.getContentPane().add(lblRank);
		
		rdbtn_rank1 = new JRadioButton("1");
		rdbtn_rank1.setBounds(46, 51, 56, 23);
		frame.getContentPane().add(rdbtn_rank1);
		
		rdbtn_rank2 = new JRadioButton("2");
		rdbtn_rank2.setBounds(104, 51, 72, 23);
		frame.getContentPane().add(rdbtn_rank2);
		
		rdbtn_rank3 = new JRadioButton("3");
		rdbtn_rank3.setBounds(178, 51, 64, 23);
		frame.getContentPane().add(rdbtn_rank3);
		
		rdbtn_rank4 = new JRadioButton("4");
		rdbtn_rank4.setBounds(244, 51, 64, 23);
		frame.getContentPane().add(rdbtn_rank4);
		
		rdbtn_rank5 = new JRadioButton("5");
		rdbtn_rank5.setBounds(322, 51, 52, 23);
		frame.getContentPane().add(rdbtn_rank5);
		
		rdbtn_rank6 = new JRadioButton("6");
		rdbtn_rank6.setBounds(46, 76, 56, 23);
		frame.getContentPane().add(rdbtn_rank6);
		
		rdbtn_rank7 = new JRadioButton("7");
		rdbtn_rank7.setBounds(104, 77, 46, 23);
		frame.getContentPane().add(rdbtn_rank7);
		
		rdbtn_rank8 = new JRadioButton("8");
		rdbtn_rank8.setBounds(178, 77, 56, 23);
		frame.getContentPane().add(rdbtn_rank8);
		
		rdbtn_rank9 = new JRadioButton("9");
		rdbtn_rank9.setBounds(244, 77, 64, 23);
		frame.getContentPane().add(rdbtn_rank9);
		
		rdbtnMax = new JRadioButton("Max");
		rdbtnMax.setBounds(322, 77, 52, 23);
		frame.getContentPane().add(rdbtnMax);
		
		JLabel lblAvailability = new JLabel("Availability:");
		lblAvailability.setBounds(10, 106, 67, 14);
		frame.getContentPane().add(lblAvailability);
		
		chckbxM = new JCheckBox("M");
		chckbxM.setBounds(196, 127, 41, 23);
		frame.getContentPane().add(chckbxM);
		
		chckbxTu = new JCheckBox("Tu");
		chckbxTu.setBounds(244, 127, 52, 23);
		frame.getContentPane().add(chckbxTu);
		
		chckbxW = new JCheckBox("W");
		chckbxW.setBounds(298, 127, 46, 23);
		frame.getContentPane().add(chckbxW);
		
		chckbxTh = new JCheckBox("Th");
		chckbxTh.setBounds(346, 127, 52, 23);
		frame.getContentPane().add(chckbxTh);
		
		chckbxF = new JCheckBox("F");
		chckbxF.setBounds(196, 153, 33, 23);
		frame.getContentPane().add(chckbxF);
		
		chckbxSa = new JCheckBox("Sa");
		chckbxSa.setBounds(244, 153, 52, 23);
		frame.getContentPane().add(chckbxSa);
		
		chckbxSu = new JCheckBox("Su");
		chckbxSu.setBounds(298, 153, 46, 23);
		frame.getContentPane().add(chckbxSu);
		
		chckbxAvailableHoldidays = new JCheckBox("Available during holdidays");
		chckbxAvailableHoldidays.setBounds(196, 179, 247, 23);
		frame.getContentPane().add(chckbxAvailableHoldidays);
		
		chckbxAvailableRainy = new JCheckBox("Available during rainy days");
		chckbxAvailableRainy.setBounds(46, 283, 247, 23);
		frame.getContentPane().add(chckbxAvailableRainy);
		
		chckbxAvailableExams = new JCheckBox("Availabe 1 week before exams");
		chckbxAvailableExams.setBounds(46, 309, 247, 23);
		frame.getContentPane().add(chckbxAvailableExams);
		
		rdbtnAvailableDaytime = new JRadioButton("Available during daytime/after school");
		rdbtnAvailableDaytime.setBounds(196, 205, 247, 23);
		frame.getContentPane().add(rdbtnAvailableDaytime);
		
		rdbtnAvailableEvening = new JRadioButton("Available during the evening");
		rdbtnAvailableEvening.setBounds(196, 231, 247, 23);
		frame.getContentPane().add(rdbtnAvailableEvening);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedArcana = (String)arcanaBox.getSelectedItem();
				if(list_.canAddArcana(selectedArcana, link_))
				{
					if(!isNewLink)
						editBox_.removeItem(link_.getName());
					
					link_.setArcana(selectedArcana);
					link_.setName(textField.getText());
					link_.setRank(getSelectedRank());
					link_.setAvailableHolidayOnly(rdbtnAvailableHolidaysOnly.isSelected());
					if(!link_.getAvailableHolidayOnly())
					{
						setSelectedDays();
						link_.setAvailableHoliday(chckbxAvailableHoldidays.isSelected());
						setSelectedPeriod();
					}
					link_.setAvailableRain(chckbxAvailableRainy.isSelected());
					link_.setAvailableExams(chckbxAvailableExams.isSelected());
					
					if(isNewLink)
						list_.addLink(link_);
					
					editBox_.addItem(link_.getName());
					editBox_.setSelectedItem(link_.getName());
					
					agenda_.setFileChanged(true);
					agenda_.updateSearch();
					
					frame.dispose();
				}
				else
					JOptionPane.showConfirmDialog(frame, "The selected Arcana is currently being used by another Social Link"
							, "Error: Cannot save changes", JOptionPane.WARNING_MESSAGE);
				
			}
		});
		btnSave.setBounds(46, 339, 89, 23);
		frame.getContentPane().add(btnSave);
		
		JButton btnResetFields = new JButton("Reset fields");
		btnResetFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFields();
			}
		});
		btnResetFields.setBounds(145, 339, 130, 23);
		frame.getContentPane().add(btnResetFields);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(285, 339, 89, 23);
		frame.getContentPane().add(btnCancel);
		
		rdbtnAvailableAnyDay = new JRadioButton("Available these days:");
		rdbtnAvailableAnyDay.setBounds(46, 127, 153, 20);
		rdbtnAvailableAnyDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxM.setEnabled(true);
				chckbxTu.setEnabled(true);
				chckbxW.setEnabled(true);
				chckbxTh.setEnabled(true);
				chckbxF.setEnabled(true);
				chckbxSa.setEnabled(true);
				chckbxSu.setEnabled(true);
				chckbxAvailableHoldidays.setEnabled(true);
				rdbtnAvailableDaytime.setEnabled(true);
				rdbtnAvailableEvening.setEnabled(true);
			}
		});
		frame.getContentPane().add(rdbtnAvailableAnyDay);
		
		rdbtnAvailableHolidaysOnly = new JRadioButton("Available during holidays only (includes Sundays)");
		rdbtnAvailableHolidaysOnly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxM.setEnabled(false);
				chckbxTu.setEnabled(false);
				chckbxW.setEnabled(false);
				chckbxTh.setEnabled(false);
				chckbxF.setEnabled(false);
				chckbxSa.setEnabled(false);
				chckbxSu.setEnabled(false);
				chckbxAvailableHoldidays.setEnabled(false);
				rdbtnAvailableDaytime.setEnabled(false);
				rdbtnAvailableEvening.setEnabled(false);
			}
		});
		rdbtnAvailableHolidaysOnly.setBounds(46, 257, 393, 23);
		frame.getContentPane().add(rdbtnAvailableHolidaysOnly);
		
		//Group availabily days buttons
		ButtonGroup isHolidaysOnlyGroup = new ButtonGroup();
		isHolidaysOnlyGroup.add(rdbtnAvailableAnyDay);
		isHolidaysOnlyGroup.add(rdbtnAvailableHolidaysOnly);
		
		//Group daytime buttons
		ButtonGroup availableTimeGroup = new ButtonGroup();
		availableTimeGroup.add(rdbtnAvailableDaytime);
		availableTimeGroup.add(rdbtnAvailableEvening);
		
		//Group rank buttons
		ButtonGroup rankGroup = new ButtonGroup();
		rankGroup.add(rdbtn_rank1);
		rankGroup.add(rdbtn_rank2);
		rankGroup.add(rdbtn_rank3);
		rankGroup.add(rdbtn_rank4);
		rankGroup.add(rdbtn_rank5);
		rankGroup.add(rdbtn_rank6);
		rankGroup.add(rdbtn_rank7);
		rankGroup.add(rdbtn_rank8);
		rankGroup.add(rdbtn_rank9);
		rankGroup.add(rdbtnMax);
		
		resetFields();
	}

}
