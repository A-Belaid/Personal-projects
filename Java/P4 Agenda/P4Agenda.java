import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.JSONObject;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JMenuItem;
import java.awt.Toolkit;

public class P4Agenda {

	private JFrame frmPersonaAgenda;
	
	private Profile profile_;
	private Day day_;
	
	private String saveFile_;
	private String saveFileFullPath_;
	
	//buttons
		///General
		JLabel lblDay;
		JLabel lblMmdd;
		JLabel lblPeriod;
		///Profile
			////Status
				/////Courage
				JRadioButton rdbtnAverage;
				JRadioButton rdbtnReliable;
				JRadioButton rdbtnBrave;
				JRadioButton rdbtnDaring;
				JRadioButton rdbtnHeroic;
				/////Diligence
				JRadioButton rdbtnCallow;
				JRadioButton rdbtnPersistent;
				JRadioButton rdbtnStrong;
				JRadioButton rdbtnThorough;
				JRadioButton rdbtnRockSolid;
				/////Understanding
				JRadioButton rdbtnBasic;
				JRadioButton rdbtnKindly;
				JRadioButton rdbtnGenerous;
				JRadioButton rdbtnMotherly;
				JRadioButton rdbtnSaintly;
				/////Expression
				JRadioButton rdbtnRough;
				JRadioButton rdbtnEloquent;
				JRadioButton rdbtnPersuasive;
				JRadioButton rdbtnTouching;
				JRadioButton rdbtnEnthralling;
				/////Knowledge
				JRadioButton rdbtnBroad;
				JRadioButton rdbtnInformed;
				JRadioButton rdbtnExpert;
				JRadioButton rdbtnProfessor;
				JRadioButton rdbtnSage;
			////Jobs
			JCheckBox chckbxEnvelopConstructor;
			JCheckBox chckbxTranslator;
			JCheckBox chckbxOrigamiFolder;
			JCheckBox chckbxAssistant;
			JCheckBox chckbxJanitor;
			JCheckBox chckbxTutor;
			////S.Links
			JComboBox magicianBox;
			JComboBox priestessBox;
			JComboBox empressBox;
			JComboBox emperorBox;
			JComboBox hierophantBox;
			JComboBox loversBox;
			JComboBox chariotBox;
			JComboBox justiceBox;
			JComboBox hermitBox;
			JComboBox fortuneBox;
			JComboBox strengthBox;
			JComboBox hangedBox;
			JComboBox deathBox;
			JComboBox temperanceBox;
			JComboBox devilBox;
			JComboBox towerBox;
			JComboBox moonBox;
			JComboBox sunBox;
		///Day plan
			////Mission
			private JLabel lblMission;
			private JButton btnMission;
			////Social Links
			private JLabel lblAvailableSocialLinks;
			private JComboBox availableLinksBox;
			private JButton btnHangout;
			////Fusion Forecast
			private JLabel lblFusionForecast;
			private JLabel lblConditions;
			JTextPane fConditionText;
			JTextPane fResultText;
			JRadioButton rdbtnSortRank;
			JRadioButton rdbtnSortGap;
			private JButton btnNextPeriod;
			private JButton btnNextDay;
			////Week plan
			JTextArea weekPlanText;
			private JMenuItem mntmNew;
			private JMenuItem mntmOpen;
			private JMenuItem mntmSave;
			private JMenuItem mntmSaveAs;
			private JPanel panel_5;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					P4Agenda window = new P4Agenda();
					window.frmPersonaAgenda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public P4Agenda() {
		profile_ = new Profile();
		day_ = new Day();
		
		saveFile_ = "";
		saveFileFullPath_ = "";
		
		profile_.getSLList().sortLinksByRank();
		
		initialize();
	}
	
	public P4Agenda getThis() {return this;}
	
	public Profile getProfile() {return profile_;}
	
	public Day getDay() {return day_;}
	
	public void setDay(int dateMonth, int dateDay, boolean isDaytime, boolean wentToTV)
	{
		day_ = new Day(dateMonth, dateDay, isDaytime, wentToTV);
	}
	
	public void setWeekPlan()
	{
		weekPlanText.setText("");
		Day currentDay = new Day(day_);
		String planText = "";
		
		for(int i = 0; i < 7; i++)
		{	
			planText += currentDay.verifyWeekday().toString() + " "
					+ Integer.toString(currentDay.getDateMonth())
					+ "/" + Integer.toString(currentDay.getDateDay()) + "\n";
			
			String currentMission = profile_.getMissionList().getAvailableMissionName(currentDay.getDateMonth(), currentDay.getDateDay());
			if(currentMission.equals("Free exploration (1/1)"))
				currentMission = "None";
			
			planText += "Mission: " + currentMission + "\n\n";
			
			Vector<String> availableSLArcanas = profile_.getSLList().getAvailableLinkArcanas(profile_.getStats(),
					currentDay.getDateMonth(), currentDay.getDateDay(), true, false);
			
			planText += "Social Links:\nDaytime/After School:\n";
			
			for(int j = 0; j < availableSLArcanas.size(); j++)
			{
				String arcana = availableSLArcanas.elementAt(j);
				planText += arcana + "(Rank " + Integer.toString(profile_.getSLList().getLinkByArcana(arcana).getRank()) + ")";
				
				if(j < availableSLArcanas.size() - 1)
				{
					planText += ", ";
					if(j%2 == 0  && j != 0)
						planText += "\n";
				}
			}
			
			availableSLArcanas = profile_.getSLList().getAvailableLinkArcanas(profile_.getStats(),
					currentDay.getDateMonth(), currentDay.getDateDay(), false, currentDay.getWentToTV());
			
			planText += "\nEvening:\n";
			
			for(int j = 0; j < availableSLArcanas.size(); j++)
			{
				String arcana = availableSLArcanas.elementAt(j);
				planText += arcana + "(Rank " + Integer.toString(profile_.getSLList().getLinkByArcana(arcana).getRank()) + ")";
				
				if(j < availableSLArcanas.size() - 1)
				{
					planText += ", ";
					if(j%2 == 0  && j != 0)
						planText += "\n";
				}
			}
			
			planText += "\n\nFusion Forecast:\nCondition: " + currentDay.getForecastConditon() + "\n";
			planText += "Result: " + currentDay.getForecastResult() + "\n\n\n";
			
			currentDay = currentDay.nextDay();
		}
		
		weekPlanText.setText(planText);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	
	public void save() throws IOException
	{
		if(saveFile_.equals(""))
			saveAs();
		else
		{
			JSONObject obj = new JSONObject();
			
			obj.put("saveFile_", saveFile_);
			obj.put("profile_", profile_.getJSON());
			obj.put("day_", day_.getJSON());
			
			FileWriter fileWriter = new FileWriter(saveFileFullPath_);
			
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			StringWriter out = new StringWriter();
		    obj.write(out);
		    String jsonText = out.toString();
		    
		    bufferedWriter.write(jsonText);
		    
		    bufferedWriter.close();
		}
		
		update(false);
	}
	
	public void saveAs()
	{
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("."));
		fc.setDialogTitle("Save as");
		fc.setFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));
		if(fc.showOpenDialog(mntmSaveAs) == JFileChooser.APPROVE_OPTION)
		{
			try {
				saveFileFullPath_ = fc.getSelectedFile().getAbsolutePath() + ".json";
				saveFile_ = fc.getSelectedFile().getName();
				save();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void load() throws IOException
	{
		
		FileReader fileReader = new FileReader(saveFileFullPath_);
		
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		String jsonText = bufferedReader.readLine();
		
		JSONObject obj = new JSONObject(jsonText);
		
		saveFile_ = obj.getString("saveFile_");
		
		day_.readJSON(obj.getJSONObject("day_"));
		
		update(false);
		
		profile_.readJSON(obj.getJSONObject("profile_"));
		
		update(false);
		
		bufferedReader.close();
	}
	
	public void initArcanaBox(JComboBox arcanaBox)
	{
		for(int i = 0; i <= 10; i++)
		{
			if(i < 10)
				arcanaBox.addItem(Integer.toString(i));
			else
				arcanaBox.addItem("Max");
		}
	}
	
	private void initialize() {
		frmPersonaAgenda = new JFrame();
		frmPersonaAgenda.setTitle("Persona 4 Agenda");
		frmPersonaAgenda.setBounds(100, 100, 482, 500);
		frmPersonaAgenda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPersonaAgenda.getContentPane().setLayout(null);
		
		lblDay = new JLabel("M");
		lblDay.setBounds(10, 11, 46, 14);
		frmPersonaAgenda.getContentPane().add(lblDay);
		
		lblPeriod = new JLabel("Daytime");
		lblPeriod.setBounds(10, 36, 46, 14);
		frmPersonaAgenda.getContentPane().add(lblPeriod);
		
		lblMmdd = new JLabel("4/11");
		lblMmdd.setBounds(66, 11, 46, 14);
		frmPersonaAgenda.getContentPane().add(lblMmdd);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 61, 449, 368);
		frmPersonaAgenda.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Profile", null, panel, null);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 0, 444, 340);
		panel.add(tabbedPane_1);
		
		JPanel panel_1 = new JPanel();
		tabbedPane_1.addTab("Status", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblCourage = new JLabel("Courage:");
		lblCourage.setBounds(12, 14, 79, 14);
		panel_1.add(lblCourage);
		
		rdbtnAverage = new JRadioButton("Average");
		rdbtnAverage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setCourage(1);
				update(true);
			}
		});
		rdbtnAverage.setSelected(true);
		rdbtnAverage.setBounds(97, 7, 109, 23);
		panel_1.add(rdbtnAverage);
		
		rdbtnReliable = new JRadioButton("Reliable");
		rdbtnReliable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setCourage(2);
				update(true);
			}
		});
		rdbtnReliable.setBounds(208, 7, 109, 23);
		panel_1.add(rdbtnReliable);
		
		rdbtnBrave = new JRadioButton("Brave");
		rdbtnBrave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setCourage(3);
				update(true);
			}
		});
		rdbtnBrave.setBounds(319, 7, 109, 23);
		panel_1.add(rdbtnBrave);
		
		rdbtnDaring = new JRadioButton("Daring");
		rdbtnDaring.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setCourage(4);
				update(true);
			}
		});
		rdbtnDaring.setBounds(97, 32, 109, 23);
		panel_1.add(rdbtnDaring);
		
		rdbtnHeroic = new JRadioButton("Heroic");
		rdbtnHeroic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setCourage(5);
				update(true);
			}
		});
		rdbtnHeroic.setBounds(208, 33, 109, 23);
		panel_1.add(rdbtnHeroic);
		
		ButtonGroup courageGroup = new ButtonGroup();
		courageGroup.add(rdbtnAverage);
		courageGroup.add(rdbtnReliable);
		courageGroup.add(rdbtnBrave);
		courageGroup.add(rdbtnDaring);
		courageGroup.add(rdbtnHeroic);
		
		JLabel lblDiligence = new JLabel("Diligence:");
		lblDiligence.setBounds(12, 64, 79, 14);
		panel_1.add(lblDiligence);
		
		rdbtnCallow = new JRadioButton("Callow");
		rdbtnCallow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setDiligence(1);
				update(true);
			}
		});
		rdbtnCallow.setSelected(true);
		rdbtnCallow.setBounds(97, 57, 109, 23);
		panel_1.add(rdbtnCallow);
		
		rdbtnPersistent = new JRadioButton("Persistent");
		rdbtnPersistent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setDiligence(2);
				update(true);
			}
		});
		rdbtnPersistent.setBounds(208, 57, 109, 23);
		panel_1.add(rdbtnPersistent);
		
		rdbtnStrong = new JRadioButton("Strong");
		rdbtnStrong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setDiligence(3);
				update(true);
			}
		});
		rdbtnStrong.setBounds(319, 57, 109, 23);
		panel_1.add(rdbtnStrong);
		
		rdbtnThorough = new JRadioButton("Thorough");
		rdbtnThorough.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setDiligence(4);
				update(true);
			}
		});
		rdbtnThorough.setBounds(97, 83, 109, 23);
		panel_1.add(rdbtnThorough);
		
		rdbtnRockSolid = new JRadioButton("Rock Solid");
		rdbtnRockSolid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setDiligence(5);
				update(true);
			}
		});
		rdbtnRockSolid.setBounds(208, 83, 109, 23);
		panel_1.add(rdbtnRockSolid);
		
		ButtonGroup diligenceGroup = new ButtonGroup();
		diligenceGroup.add(rdbtnCallow);
		diligenceGroup.add(rdbtnPersistent);
		diligenceGroup.add(rdbtnStrong);
		diligenceGroup.add(rdbtnThorough);
		diligenceGroup.add(rdbtnCallow);
		diligenceGroup.add(rdbtnRockSolid);
		
		JLabel lblUnderstanding = new JLabel("Understanding:");
		lblUnderstanding.setBounds(12, 113, 87, 14);
		panel_1.add(lblUnderstanding);
		
		rdbtnBasic = new JRadioButton("Basic");
		rdbtnBasic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setUnderstanding(1);
				update(true);
			}
		});
		rdbtnBasic.setSelected(true);
		rdbtnBasic.setBounds(97, 109, 109, 23);
		panel_1.add(rdbtnBasic);
		
		rdbtnKindly = new JRadioButton("Kindly");
		rdbtnKindly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setUnderstanding(2);
				update(true);
			}
		});
		rdbtnKindly.setBounds(208, 109, 109, 23);
		panel_1.add(rdbtnKindly);
		
		rdbtnGenerous = new JRadioButton("Generous");
		rdbtnGenerous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setUnderstanding(3);
				update(true);
			}
		});
		rdbtnGenerous.setBounds(319, 109, 109, 23);
		panel_1.add(rdbtnGenerous);
		
		rdbtnMotherly = new JRadioButton("Motherly");
		rdbtnMotherly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setUnderstanding(4);
				update(true);
			}
		});
		rdbtnMotherly.setBounds(97, 134, 109, 23);
		panel_1.add(rdbtnMotherly);
		
		rdbtnSaintly = new JRadioButton("Saintly");
		rdbtnSaintly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setUnderstanding(5);
				update(true);
			}
		});
		rdbtnSaintly.setBounds(208, 134, 109, 23);
		panel_1.add(rdbtnSaintly);
		
		ButtonGroup understandingGroup = new ButtonGroup();
		understandingGroup.add(rdbtnBasic);
		understandingGroup.add(rdbtnKindly);
		understandingGroup.add(rdbtnGenerous);
		understandingGroup.add(rdbtnMotherly);
		understandingGroup.add(rdbtnSaintly);
		
		JLabel lblExpression = new JLabel("Expression:");
		lblExpression.setBounds(12, 162, 79, 14);
		panel_1.add(lblExpression);
		
		rdbtnRough = new JRadioButton("Rough");
		rdbtnRough.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setExpression(1);
				update(true);
			}
		});
		rdbtnRough.setSelected(true);
		rdbtnRough.setBounds(97, 160, 109, 23);
		panel_1.add(rdbtnRough);
		
		rdbtnEloquent = new JRadioButton("Eloquent");
		rdbtnEloquent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setExpression(2);
				update(true);
			}
		});
		rdbtnEloquent.setBounds(208, 160, 109, 23);
		panel_1.add(rdbtnEloquent);
		
		rdbtnPersuasive = new JRadioButton("Persuasive");
		rdbtnPersuasive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setExpression(3);
				update(true);
			}
		});
		rdbtnPersuasive.setBounds(319, 158, 109, 23);
		panel_1.add(rdbtnPersuasive);
		
		rdbtnTouching = new JRadioButton("Touching");
		rdbtnTouching.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setExpression(4);
				update(true);
			}
		});
		rdbtnTouching.setBounds(97, 186, 109, 23);
		panel_1.add(rdbtnTouching);
		
		rdbtnEnthralling = new JRadioButton("Enthralling");
		rdbtnEnthralling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setExpression(5);
				update(true);
			}
		});
		rdbtnEnthralling.setBounds(208, 186, 109, 23);
		panel_1.add(rdbtnEnthralling);
		
		ButtonGroup expressionGroup = new ButtonGroup();
		expressionGroup.add(rdbtnRough);
		expressionGroup.add(rdbtnEloquent);
		expressionGroup.add(rdbtnPersuasive);
		expressionGroup.add(rdbtnTouching);
		expressionGroup.add(rdbtnEnthralling);
		
		JLabel lblKnowledge = new JLabel("Knowledge:");
		lblKnowledge.setBounds(10, 215, 81, 14);
		panel_1.add(lblKnowledge);
		
		rdbtnInformed = new JRadioButton("Informed");
		rdbtnInformed.setBounds(208, 211, 109, 23);
		rdbtnInformed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setKnowledge(2);
				update(true);
			}
		});
		panel_1.add(rdbtnInformed);
		
		rdbtnBroad = new JRadioButton("Aware");
		rdbtnBroad.setSelected(true);
		rdbtnBroad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setKnowledge(1);
				update(true);
			}
		});
		rdbtnBroad.setBounds(97, 211, 109, 23);
		panel_1.add(rdbtnBroad);
		
		rdbtnExpert = new JRadioButton("Expert");
		rdbtnExpert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setKnowledge(3);
				update(true);
			}
		});
		rdbtnExpert.setBounds(319, 211, 109, 23);
		panel_1.add(rdbtnExpert);
		
		rdbtnProfessor = new JRadioButton("Professor");
		rdbtnProfessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setKnowledge(4);
				update(true);
			}
		});
		rdbtnProfessor.setBounds(97, 237, 109, 23);
		panel_1.add(rdbtnProfessor);
		
		rdbtnSage = new JRadioButton("Sage");
		rdbtnSage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.setKnowledge(5);
				update(true);
			}
		});
		rdbtnSage.setBounds(208, 237, 109, 23);
		panel_1.add(rdbtnSage);
		
		ButtonGroup knowledgeGroup = new ButtonGroup();
		knowledgeGroup.add(rdbtnBroad);
		knowledgeGroup.add(rdbtnInformed);
		knowledgeGroup.add(rdbtnExpert);
		knowledgeGroup.add(rdbtnProfessor);
		knowledgeGroup.add(rdbtnSage);
		
		JPanel panel_2 = new JPanel();
		tabbedPane_1.addTab("Jobs", null, panel_2, null);
		panel_2.setLayout(null);
		
		chckbxEnvelopConstructor = new JCheckBox("Envelop Constructor");
		chckbxEnvelopConstructor.setEnabled(false);
		chckbxEnvelopConstructor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				profile_.setEnvelopConstructor(chckbxEnvelopConstructor.isSelected());
				update(true);
			}
		});
		chckbxEnvelopConstructor.setBounds(6, 7, 414, 23);
		panel_2.add(chckbxEnvelopConstructor);
		
		chckbxTranslator = new JCheckBox("Translator");
		chckbxTranslator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				profile_.setTranslator(chckbxTranslator.isSelected());
				update(true);
			}
		});
		chckbxTranslator.setEnabled(false);
		chckbxTranslator.setBounds(6, 58, 363, 23);
		panel_2.add(chckbxTranslator);
		
		chckbxOrigamiFolder = new JCheckBox("Origami Crane Folder");
		chckbxOrigamiFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				profile_.setOrigamiFolder(chckbxOrigamiFolder.isSelected());
				update(true);
			}
		});
		chckbxOrigamiFolder.setEnabled(false);
		chckbxOrigamiFolder.setBounds(6, 113, 414, 23);
		panel_2.add(chckbxOrigamiFolder);
		
		chckbxAssistant = new JCheckBox("Assistant Day Care Caretaker");
		chckbxAssistant.setEnabled(false);
		chckbxAssistant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				profile_.setAssistant(chckbxAssistant.isSelected());
				update(true);
			}
		});
		chckbxAssistant.setBounds(6, 164, 363, 23);
		panel_2.add(chckbxAssistant);
		
		chckbxJanitor = new JCheckBox("Hospital Janitor");
		chckbxJanitor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				profile_.setJanitor(chckbxJanitor.isSelected());
				update(true);
			}
		});
		chckbxJanitor.setEnabled(false);
		chckbxJanitor.setBounds(6, 210, 363, 23);
		panel_2.add(chckbxJanitor);
		
		chckbxTutor = new JCheckBox("Tutor");
		chckbxTutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				profile_.setTutor(chckbxTutor.isSelected());
				update(true);
			}
		});
		chckbxTutor.setEnabled(false);
		chckbxTutor.setBounds(6, 257, 363, 23);
		panel_2.add(chckbxTutor);
		
		JLabel lblAvailable = new JLabel("Available: 04/23");
		lblAvailable.setBounds(32, 37, 145, 14);
		panel_2.add(lblAvailable);
		
		JLabel lblAvailableDiligence = new JLabel("Available: 04/23; Knowledge: Informed");
		lblAvailableDiligence.setBounds(32, 88, 336, 14);
		panel_2.add(lblAvailableDiligence);
		
		JLabel lblAvailableKnowledge = new JLabel("Available 04/23; Diligence: Persistent");
		lblAvailableKnowledge.setBounds(32, 143, 349, 14);
		panel_2.add(lblAvailableKnowledge);
		
		JLabel lblAvailable_1 = new JLabel("Available: 04/23");
		lblAvailable_1.setBounds(32, 189, 349, 14);
		panel_2.add(lblAvailable_1);
		
		JLabel lblAvailableDiligence_1 = new JLabel("Available: 05/25; Diligence: Strong");
		lblAvailableDiligence_1.setBounds(32, 240, 337, 14);
		panel_2.add(lblAvailableDiligence_1);
		
		JLabel lblAvailableUnderstanding = new JLabel("Available: 05/25; Understanding: Saintly");
		lblAvailableUnderstanding.setBounds(32, 287, 337, 14);
		panel_2.add(lblAvailableUnderstanding);
		
		panel_5 = new JPanel();
		tabbedPane_1.addTab("S.Links", null, panel_5, null);
		panel_5.setLayout(null);
		
		JLabel lblMagician = new JLabel("Magician:");
		lblMagician.setBounds(10, 11, 70, 14);
		panel_5.add(lblMagician);
		
		JLabel lblPriestess = new JLabel("Priestess:");
		lblPriestess.setBounds(210, 11, 70, 14);
		panel_5.add(lblPriestess);
		
		JLabel lblEmpress = new JLabel("Empress:");
		lblEmpress.setBounds(10, 36, 70, 14);
		panel_5.add(lblEmpress);
		
		JLabel lblEmperor = new JLabel("Emperor:");
		lblEmperor.setBounds(210, 36, 70, 14);
		panel_5.add(lblEmperor);
		
		JLabel lblHierophant = new JLabel("Hierophant: ");
		lblHierophant.setBounds(10, 61, 95, 14);
		panel_5.add(lblHierophant);
		
		JLabel lblLovers = new JLabel("Lovers:");
		lblLovers.setBounds(210, 61, 70, 14);
		panel_5.add(lblLovers);
		
		JLabel lblChariot = new JLabel("Chariot:");
		lblChariot.setBounds(10, 86, 70, 14);
		panel_5.add(lblChariot);
		
		JLabel lblJustice = new JLabel("Justice: ");
		lblJustice.setBounds(210, 86, 70, 14);
		panel_5.add(lblJustice);
		
		JLabel lblHermit = new JLabel("Hermit:");
		lblHermit.setBounds(10, 111, 70, 14);
		panel_5.add(lblHermit);
		
		JLabel lblFortune = new JLabel("Fortune: ");
		lblFortune.setBounds(210, 111, 70, 14);
		panel_5.add(lblFortune);
		
		JLabel lblStrength = new JLabel("Strength:");
		lblStrength.setBounds(10, 136, 70, 14);
		panel_5.add(lblStrength);
		
		JLabel lblHanged = new JLabel("Hanged: ");
		lblHanged.setBounds(210, 136, 70, 14);
		panel_5.add(lblHanged);
		
		JLabel lblDeath = new JLabel("Death: ");
		lblDeath.setBounds(10, 161, 70, 14);
		panel_5.add(lblDeath);
		
		JLabel lblTemperance = new JLabel("Temperance: ");
		lblTemperance.setBounds(210, 161, 95, 14);
		panel_5.add(lblTemperance);
		
		JLabel lblDevil = new JLabel("Devil:");
		lblDevil.setBounds(10, 186, 70, 14);
		panel_5.add(lblDevil);
		
		JLabel lblTower = new JLabel("Tower:");
		lblTower.setBounds(210, 186, 70, 14);
		panel_5.add(lblTower);
		
		JLabel lblSun = new JLabel("Sun: ");
		lblSun.setBounds(210, 211, 70, 14);
		panel_5.add(lblSun);
		
		JLabel lblMoon = new JLabel("Moon: ");
		lblMoon.setBounds(10, 211, 70, 14);
		panel_5.add(lblMoon);
		
		magicianBox = new JComboBox();
		initArcanaBox(magicianBox);
		magicianBox.setEnabled(false);
		magicianBox.setBounds(115, 11, 55, 20);
		panel_5.add(magicianBox);
		
		priestessBox = new JComboBox();
		initArcanaBox(priestessBox);
		priestessBox.setEnabled(false);
		priestessBox.setBounds(315, 11, 55, 20);
		panel_5.add(priestessBox);
		
		empressBox = new JComboBox();
		initArcanaBox(empressBox);
		empressBox.setEnabled(false);
		empressBox.setBounds(115, 36, 55, 20);
		panel_5.add(empressBox);
		
		emperorBox = new JComboBox();
		initArcanaBox(emperorBox);
		emperorBox.setEnabled(false);
		emperorBox.setBounds(315, 36, 55, 20);
		panel_5.add(emperorBox);
		
		hierophantBox = new JComboBox();
		initArcanaBox(hierophantBox);
		hierophantBox.setEnabled(false);
		hierophantBox.setBounds(115, 61, 55, 20);
		panel_5.add(hierophantBox);
		
		loversBox = new JComboBox();
		initArcanaBox(loversBox);
		loversBox.setEnabled(false);
		loversBox.setBounds(315, 61, 55, 20);
		panel_5.add(loversBox);
		
		chariotBox = new JComboBox();
		initArcanaBox(chariotBox);
		chariotBox.setEnabled(false);
		chariotBox.setBounds(115, 86, 55, 20);
		panel_5.add(chariotBox);
		
		justiceBox = new JComboBox();
		initArcanaBox(justiceBox);
		justiceBox.setEnabled(false);
		justiceBox.setBounds(315, 86, 55, 20);
		panel_5.add(justiceBox);
		
		hermitBox = new JComboBox();
		initArcanaBox(hermitBox);
		hermitBox.setEnabled(false);
		hermitBox.setBounds(115, 111, 55, 20);
		panel_5.add(hermitBox);
		
		fortuneBox = new JComboBox();
		initArcanaBox(fortuneBox);
		fortuneBox.setEnabled(false);
		fortuneBox.setBounds(315, 111, 55, 20);
		panel_5.add(fortuneBox);
		
		strengthBox = new JComboBox();
		initArcanaBox(strengthBox);
		strengthBox.setEnabled(false);
		strengthBox.setBounds(115, 136, 55, 20);
		panel_5.add(strengthBox);
		
		deathBox = new JComboBox();
		initArcanaBox(deathBox);
		deathBox.setEnabled(false);
		deathBox.setBounds(115, 161, 55, 20);
		panel_5.add(deathBox);
		
		temperanceBox = new JComboBox();
		initArcanaBox(temperanceBox);
		temperanceBox.setEnabled(false);
		temperanceBox.setBounds(315, 161, 55, 20);
		panel_5.add(temperanceBox);
		
		sunBox = new JComboBox();
		initArcanaBox(sunBox);
		sunBox.setEnabled(false);
		sunBox.setBounds(315, 211, 55, 20);
		panel_5.add(sunBox);
		
		hangedBox = new JComboBox();
		initArcanaBox(hangedBox);
		hangedBox.setEnabled(false);
		hangedBox.setBounds(315, 136, 55, 20);
		panel_5.add(hangedBox);
		
		devilBox = new JComboBox();
		initArcanaBox(devilBox);
		devilBox.setEnabled(false);
		devilBox.setBounds(115, 186, 55, 20);
		panel_5.add(devilBox);
		
		towerBox = new JComboBox();
		initArcanaBox(towerBox);
		towerBox.setEnabled(false);
		towerBox.setBounds(315, 186, 55, 20);
		panel_5.add(towerBox);
		
		moonBox = new JComboBox();
		initArcanaBox(moonBox);
		moonBox.setEnabled(false);
		moonBox.setBounds(115, 211, 55, 20);
		panel_5.add(moonBox);
		
		magicianBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.getSLList().getLinkByArcana("MAGICIAN").setRank(magicianBox.getSelectedIndex());
				update(true, true);
			}
		});
		
		priestessBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.getSLList().getLinkByArcana("PRIESTESS").setRank(priestessBox.getSelectedIndex());
				update(true, true);
			}
		});
		
		empressBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.getSLList().getLinkByArcana("EMPRESS").setRank(empressBox.getSelectedIndex());
				update(true, true);
			}
		});
		
		emperorBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.getSLList().getLinkByArcana("EMPEROR").setRank(emperorBox.getSelectedIndex());
				update(true, true);
			}
		});
		
		hierophantBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.getSLList().getLinkByArcana("HIEROPHANT").setRank(hierophantBox.getSelectedIndex());
				update(true, true);
			}
		});
		
		loversBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.getSLList().getLinkByArcana("LOVERS").setRank(loversBox.getSelectedIndex());
				update(true, true);
			}
		});
		
		chariotBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.getSLList().getLinkByArcana("CHARIOT").setRank(chariotBox.getSelectedIndex());
				update(true, true);
			}
		});
		
		justiceBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.getSLList().getLinkByArcana("JUSTICE").setRank(justiceBox.getSelectedIndex());
				update(true, true);
			}
		});
		
		hermitBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.getSLList().getLinkByArcana("HERMIT").setRank(hermitBox.getSelectedIndex());
				update(true, true);
			}
		});
		
		fortuneBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.getSLList().getLinkByArcana("FORTUNE").setRank(fortuneBox.getSelectedIndex());
				update(true, true);
			}
		});
		
		strengthBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.getSLList().getLinkByArcana("STRENGTH").setRank(strengthBox.getSelectedIndex());
				update(true, true);
			}
		});
		
		hangedBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.getSLList().getLinkByArcana("HANGED").setRank(hangedBox.getSelectedIndex());
				update(true, true);
			}
		});
		
		deathBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.getSLList().getLinkByArcana("DEATH").setRank(deathBox.getSelectedIndex());
				update(true, true);
			}
		});
		
		temperanceBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.getSLList().getLinkByArcana("TEMPERANCE").setRank(temperanceBox.getSelectedIndex());
				update(true, true);
			}
		});
		
		devilBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.getSLList().getLinkByArcana("DEVIL").setRank(devilBox.getSelectedIndex());
				update(true, true);
			}
		});
		
		towerBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.getSLList().getLinkByArcana("TOWER").setRank(towerBox.getSelectedIndex());
				update(true, true);
			}
		});
		
		moonBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.getSLList().getLinkByArcana("MOON").setRank(moonBox.getSelectedIndex());
				update(true, true);
			}
		});
		
		sunBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_.getSLList().getLinkByArcana("SUN").setRank(sunBox.getSelectedIndex());
				update(true, true);
			}
		});
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Day plan", null, panel_3, null);
		panel_3.setLayout(null);
		
		lblMission = new JLabel("Mission:");
		lblMission.setBounds(10, 11, 110, 14);
		panel_3.add(lblMission);
		
		btnMission = new JButton(profile_.getMissionList().getAvailableMissionName(day_.getDateMonth(), day_.getDateDay()));
		btnMission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mission selectedMission = profile_.getMissionList().getMissionByName(btnMission.getText());
				if(selectedMission.getType().equals("Hint"))
				{
					HintWindow hintBox = new HintWindow(getThis(), (HintMission)(selectedMission));
					hintBox.frmHintSearch.setVisible(true);
				}
				else if(selectedMission.getType().equals("TV"))
				{
					TVWindow tvBox = new TVWindow(getThis(), (TVMission)(selectedMission));
					tvBox.frmTvExploration.setVisible(true);
				}
			}
		});
		btnMission.setBounds(165, 7, 269, 23);
		panel_3.add(btnMission);
		
		lblAvailableSocialLinks = new JLabel("Available Social Links: ");
		lblAvailableSocialLinks.setBounds(10, 55, 145, 14);
		panel_3.add(lblAvailableSocialLinks);
		
		availableLinksBox = new JComboBox();
		availableLinksBox.setBounds(165, 52, 269, 20);
		panel_3.add(availableLinksBox);
		
		availableLinksBox.addItem("[0 available Link(s)]");
		
		btnHangout = new JButton("Hang out with selected Link");
		btnHangout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SLink selectedLink = profile_.getSLList().getLinkByFullName((String)(availableLinksBox.getSelectedItem()));
				
				if(selectedLink != null)
				{
					SLWindow slBox = new SLWindow(getThis(), selectedLink);
					slBox.frmSocialLinkHangout.setVisible(true);
				}
			}
		});
		btnHangout.setBounds(165, 83, 269, 23);
		panel_3.add(btnHangout);
		
		lblFusionForecast = new JLabel("Fusion Forecast:");
		lblFusionForecast.setBounds(10, 120, 110, 14);
		panel_3.add(lblFusionForecast);
		
		lblConditions = new JLabel("Conditions:");
		lblConditions.setBounds(106, 145, 71, 14);
		panel_3.add(lblConditions);
		
		fConditionText = new JTextPane();
		fConditionText.setEditable(false);
		fConditionText.setText("None");
		fConditionText.setBounds(106, 170, 328, 20);
		panel_3.add(fConditionText);
		
		JLabel lblResults = new JLabel("Results:");
		lblResults.setBounds(106, 201, 46, 14);
		panel_3.add(lblResults);
		
		fResultText = new JTextPane();
		fResultText.setEditable(false);
		fResultText.setText("None");
		fResultText.setBounds(106, 226, 328, 23);
		panel_3.add(fResultText);
		
		btnNextPeriod = new JButton("Next Period");
		btnNextPeriod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				day_ = day_.nextPeriod();
				update(true);
			}
		});
		btnNextPeriod.setBounds(10, 273, 424, 23);
		panel_3.add(btnNextPeriod);
		
		btnNextDay = new JButton("Next Day");
		btnNextDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				day_ = day_.nextDay();
				update(true);
			}
		});
		btnNextDay.setBounds(10, 307, 424, 23);
		panel_3.add(btnNextDay);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Week plan", null, panel_4, null);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 424, 318);
		panel_4.add(scrollPane);
		
		weekPlanText = new JTextArea();
		weekPlanText.setEditable(false);
		scrollPane.setViewportView(weekPlanText);
		setWeekPlan();
		
		Vector<String> availableLinks = profile_.getSLList().getAvailableLinkNames(profile_.getStats(), 4, 11, true, false);
		for(int i = 0; i < availableLinks.size(); i++)
			availableLinksBox.addItem(availableLinks.get(i));
		
		JButton btnChangeDate = new JButton("Change Date");
		btnChangeDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateChanger changeDate = new DateChanger(getThis());
				changeDate.frmChangeDate.setVisible(true);
			}
		});
		btnChangeDate.setBounds(66, 32, 115, 23);
		frmPersonaAgenda.getContentPane().add(btnChangeDate);
		
		JLabel lblSortSocialLinks = new JLabel("Sort Social Links by:");
		lblSortSocialLinks.setBounds(189, 11, 180, 14);
		frmPersonaAgenda.getContentPane().add(lblSortSocialLinks);
		
		rdbtnSortRank = new JRadioButton("Lower ranks first");
		rdbtnSortRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(false);
			}
		});
		rdbtnSortRank.setSelected(true);
		rdbtnSortRank.setBounds(303, 7, 156, 23);
		frmPersonaAgenda.getContentPane().add(rdbtnSortRank);
		
		rdbtnSortGap = new JRadioButton("Least recently visited");
		rdbtnSortGap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(false);
			}
		});
		rdbtnSortGap.setBounds(303, 32, 156, 23);
		frmPersonaAgenda.getContentPane().add(rdbtnSortGap);
		
		ButtonGroup sortGroup = new ButtonGroup();
		sortGroup.add(rdbtnSortRank);
		sortGroup.add(rdbtnSortGap);
		
		JMenuBar menuBar = new JMenuBar();
		frmPersonaAgenda.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				profile_ = new Profile();
				day_ = new Day();
				
				saveFile_ = "";
				saveFileFullPath_ = "";
				
				rdbtnSortRank.setSelected(true);
				
				update(false);
			}
		});
		mnFile.add(mntmNew);
		
		mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JFileChooser fc = new JFileChooser();
					fc.setCurrentDirectory(new java.io.File("."));
					fc.setDialogTitle("Open");
					fc.setFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));
					
					if(fc.showOpenDialog(mntmOpen) == JFileChooser.APPROVE_OPTION)
					{
						saveFileFullPath_ = fc.getSelectedFile().getAbsolutePath();
						saveFile_ = fc.getSelectedFile().getName();
						
						load();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnFile.add(mntmOpen);
		
		mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					save();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnFile.add(mntmSave);
		
		mntmSaveAs = new JMenuItem("Save as");
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveAs();
			}
		});
		mnFile.add(mntmSaveAs);
	}
	
	public void update(boolean changes)
	{
		update(changes, false);
	}
	
	public void update(boolean changes, boolean fromArcanaBox)
	{
		//General
		lblMmdd.setText(Integer.toString(day_.getDateMonth()) + "/" + Integer.toString(day_.getDateDay()));
		
		if(day_.getIsDaytime())
			lblPeriod.setText("Daytime");
		else
			lblPeriod.setText("Evening");
		
		switch(day_.verifyWeekday())
		{
		case MON: lblDay.setText("M"); break;
		case TUE: lblDay.setText("Tu"); break;
		case WED: lblDay.setText("W"); break;
		case THU: lblDay.setText("Th"); break;
		case FRI: lblDay.setText("F"); break;
		case SAT: lblDay.setText("Sa"); break;
		case SUN: lblDay.setText("Su"); break;
		}
		//Profile
		///Status
		switch(profile_.getCourage())
		{
		case 1: rdbtnAverage.setSelected(true); break;
		case 2: rdbtnReliable.setSelected(true); break;
		case 3: rdbtnBrave.setSelected(true); break;
		case 4: rdbtnDaring.setSelected(true); break;
		case 5: rdbtnHeroic.setSelected(true); break;
		}
		
		switch(profile_.getDiligence())
		{
		case 1: rdbtnCallow.setSelected(true); break;
		case 2: rdbtnPersistent.setSelected(true); break;
		case 3: rdbtnStrong.setSelected(true); break;
		case 4: rdbtnThorough.setSelected(true); break;
		case 5: rdbtnRockSolid.setSelected(true); break;
		}
		
		switch(profile_.getUnderstanding())
		{
		case 1: rdbtnBasic.setSelected(true); break;
		case 2: rdbtnKindly.setSelected(true); break;
		case 3: rdbtnGenerous.setSelected(true); break;
		case 4: rdbtnMotherly.setSelected(true); break;
		case 5: rdbtnSaintly.setSelected(true); break;
		}
		
		switch(profile_.getExpression())
		{
		case 1: rdbtnRough.setSelected(true); break;
		case 2: rdbtnEloquent.setSelected(true); break;
		case 3: rdbtnPersuasive.setSelected(true); break;
		case 4: rdbtnTouching.setSelected(true); break;
		case 5: rdbtnEnthralling.setSelected(true); break;
		}
		
		switch(profile_.getKnowledge())
		{
		case 1: rdbtnBroad.setSelected(true); break;
		case 2: rdbtnInformed.setSelected(true); break;
		case 3: rdbtnExpert.setSelected(true); break;
		case 4: rdbtnProfessor.setSelected(true); break;
		case 5: rdbtnSage.setSelected(true); break;
		}
		///Jobs
		chckbxEnvelopConstructor.setEnabled(day_.checkDate(4, 23));
		chckbxEnvelopConstructor.setSelected(profile_.getIsEnvelopConstructor());
		
		chckbxTranslator.setEnabled(profile_.getKnowledge() >= 2 && day_.checkDate(4, 23));
		chckbxTranslator.setSelected(profile_.getIsTranslator());
		
		chckbxOrigamiFolder.setEnabled(profile_.getDiligence() >= 2 && day_.checkDate(4, 23));
		chckbxOrigamiFolder.setSelected(profile_.getIsOrigamiFolder());
		
		chckbxAssistant.setEnabled(day_.checkDate(4, 23));
		chckbxAssistant.setSelected(profile_.getIsAssistant());
		profile_.getSLList().unlockLink("TEMPERANCE", chckbxAssistant.isSelected());
		
		chckbxJanitor.setEnabled(profile_.getDiligence() >= 3 && day_.checkDate(5, 25));
		chckbxJanitor.setSelected(profile_.getIsJanitor());
		profile_.getSLList().unlockLink("DEVIL", chckbxJanitor.isSelected());
		
		chckbxTutor.setEnabled(profile_.getUnderstanding() == 5 && day_.checkDate(5, 25));
		chckbxTutor.setSelected(profile_.getIsTutor());
		profile_.getSLList().unlockLink("TOWER", chckbxTutor.isSelected());
		///S.Links
		if(!fromArcanaBox)
		{
			magicianBox.setEnabled(day_.checkDate(4, 16));
			if(magicianBox.isEnabled())
				magicianBox.setSelectedIndex(profile_.getSLList().getLinkByArcana("MAGICIAN").getRank());
			
			priestessBox.setEnabled(day_.checkDate(5, 17));
			priestessBox.setSelectedIndex(profile_.getSLList().getLinkByArcana("PRIESTESS").getRank());
			
			empressBox.setEnabled(day_.checkDate(5, 19) && profile_.getKnowledge() >= 3);
			empressBox.setSelectedIndex(profile_.getSLList().getLinkByArcana("EMPRESS").getRank());
			
			emperorBox.setEnabled(day_.checkDate(6, 9));
			emperorBox.setSelectedIndex(profile_.getSLList().getLinkByArcana("EMPEROR").getRank());;
			
			hierophantBox.setEnabled(day_.checkDate(5, 6));
			hierophantBox.setSelectedIndex(profile_.getSLList().getLinkByArcana("HIEROPHANT").getRank());
			
			loversBox.setEnabled(day_.checkDate(7, 23));
			loversBox.setSelectedIndex(profile_.getSLList().getLinkByArcana("LOVERS").getRank());
			
			chariotBox.setEnabled(day_.checkDate(4, 18));
			chariotBox.setSelectedIndex(profile_.getSLList().getLinkByArcana("CHARIOT").getRank());
			
			justiceBox.setEnabled(day_.checkDate(5, 3));
			justiceBox.setSelectedIndex(profile_.getSLList().getLinkByArcana("JUSTICE").getRank());
			
			hermitBox.setEnabled(day_.checkDate(5, 5));
			hermitBox.setSelectedIndex(profile_.getSLList().getLinkByArcana("HERMIT").getRank());
			
			fortuneBox.setEnabled(day_.checkDate(10, 21) && profile_.getCourage() == 5 && profile_.getKnowledge() == 5);
			fortuneBox.setSelectedIndex(profile_.getSLList().getLinkByArcana("FORTUNE").getRank());
			
			strengthBox.setEnabled(day_.checkDate(4, 19));
			strengthBox.setSelectedIndex(profile_.getSLList().getLinkByArcana("STRENGTH").getRank());
			
			hangedBox.setEnabled(day_.checkDate(6, 9) && profile_.getUnderstanding() >= 3);
			hangedBox.setSelectedIndex(profile_.getSLList().getLinkByArcana("HANGED").getRank());
			
			deathBox.setEnabled(day_.checkDate(6, 5) && profile_.getSLList().getLinkByArcana("DEVIL").getRank() >= 4);
			deathBox.setSelectedIndex(profile_.getSLList().getLinkByArcana("DEATH").getRank());
			
			temperanceBox.setEnabled(day_.checkDate(4, 23) && profile_.getSLList().getLinkByArcana("TEMPERANCE").getIsUnlocked());
			temperanceBox.setSelectedIndex(profile_.getSLList().getLinkByArcana("TEMPERANCE").getRank());
			
			devilBox.setEnabled(day_.checkDate(5, 25) && profile_.getSLList().getLinkByArcana("DEVIL").getIsUnlocked());
			devilBox.setSelectedIndex(profile_.getSLList().getLinkByArcana("DEVIL").getRank());
			
			towerBox.setEnabled(day_.checkDate(5, 25) && profile_.getSLList().getLinkByArcana("TOWER").getIsUnlocked());
			towerBox.setSelectedIndex(profile_.getSLList().getLinkByArcana("TOWER").getRank());
			
			moonBox.setEnabled(day_.checkDate(4, 27) && profile_.getCourage() >= 3 && profile_.getSLList().getLinkByArcana("STRENGTH").getRank() >= 4);
			moonBox.setSelectedIndex(profile_.getSLList().getLinkByArcana("MOON").getRank());
			
			sunBox.setEnabled(day_.checkDate(4, 25));
			sunBox.setSelectedIndex(profile_.getSLList().getLinkByArcana("SUN").getRank());
		}
		//Day plan
		///Mission
		btnMission.setText(profile_.getMissionList().getAvailableMissionName(day_.getDateMonth(), day_.getDateDay()));
		btnMission.setEnabled(day_.getIsDaytime());
		///Social Links
		availableLinksBox.removeAllItems();
		if(rdbtnSortRank.isSelected())
			profile_.getSLList().sortLinksByRank();
		else
			profile_.getSLList().sortLinksByGap();
		Vector<String> availableLinks = profile_.getSLList().getAvailableLinkNames(profile_.getStats(),
				day_.getDateMonth(), day_.getDateDay(),
				day_.getIsDaytime(), day_.getWentToTV());
		availableLinksBox.addItem("[" + Integer.toString(availableLinks.size()) + " available Link(s)]");
		for(int i = 0; i < availableLinks.size(); i++)
			availableLinksBox.addItem(availableLinks.get(i));
		///Fusion Forecast
		fConditionText.setText(day_.getForecastConditon());
		fResultText.setText(day_.getForecastResult());
		//Week plan
		setWeekPlan();
		
		
		//General II
		if(changes)
			frmPersonaAgenda.setTitle("*" + saveFile_ +" | Persona 4 Agenda");
		else
			frmPersonaAgenda.setTitle(saveFile_ + " | Persona 4 Agenda");
		
	}
}
