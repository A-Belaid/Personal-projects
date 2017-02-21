package SLPackage;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import SLPackage.SLList.Sort;
import SLPackage.SLink.Day;
import SLPackage.SLink.Time;

import javax.swing.JList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class SLAgenda {

	private JFrame frmSocialLinkAgenda;
	
	private SLList list_;
	
	private SLink linkEdit_;
	private SLink linkHangout_;
	
	private JTextPane panelAvailableLinks;
	private JComboBox hangoutMainBox;
	private JComboBox hangoutOtherBox;
	
	private JComboBox linkEditBox;
	
	private String currentFileName_;
	
	private boolean isFileChanged_;
	
	public void setFileChanged(boolean isChanged) {isFileChanged_ = isChanged;}
	
	public SLAgenda getThis() {return this;}
	
	public SLList getList() {return list_;}
	
	public SLink getEditLink() {return linkEdit_;}
	
	public SLink getHangoutLink() {return linkHangout_;}
	
	public JTextPane getPanelAvailableLinks() {return panelAvailableLinks;}
	
	public JComboBox getHangoutMainBox() {return hangoutMainBox;}
	
	public JComboBox getHangoutOtherBox() {return hangoutOtherBox;}
	
	public JComboBox getLinkEditBox() {return linkEditBox;}
	
	public String getTitleName()
	{
		if(isFileChanged_)
			return "*" + currentFileName_;
		else
			return currentFileName_;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SLAgenda window = new SLAgenda(new SLList());
					window.frmSocialLinkAgenda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SLAgenda(SLList list) {
		list_ = list;
		
		linkEdit_ = null;
		
		currentFileName_ = "";
		
		isFileChanged_ = false;
		
		initialize();
	}

	public void updateSearch()
	{
		hangoutMainBox.removeAllItems();
		hangoutMainBox.addItem("");
		Vector<SLink> searchList = list_.getSelectedList();
		for(byte i = 0; i < searchList.size(); i++)
			hangoutMainBox.addItem(searchList.elementAt(i).getArcana());
		
		hangoutOtherBox.removeAllItems();
		hangoutOtherBox.addItem("");
		Vector<SLink> rejectList = list_.getRejectList();
		for(byte i = 0; i < rejectList.size(); i++)
			hangoutOtherBox.addItem(rejectList.elementAt(i).getArcana());
		
		panelAvailableLinks.setText(list_.displaySelectedInfo());
		linkEditBox.setSelectedIndex(linkEditBox.getSelectedIndex());
		
		frmSocialLinkAgenda.setTitle(getTitleName() + " | Social Link Agenda");
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSocialLinkAgenda = new JFrame();
		frmSocialLinkAgenda.setTitle("Social Link Agenda");
		frmSocialLinkAgenda.setBounds(100, 100, 457, 450);
		frmSocialLinkAgenda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmSocialLinkAgenda.setJMenuBar(menuBar);
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmSocialLinkAgenda.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelPlanning = new JPanel();
		tabbedPane.addTab("Planning", null, panelPlanning, null);
		panelPlanning.setLayout(null);
		
		JLabel lblDay = new JLabel("Day:");
		lblDay.setBounds(10, 11, 46, 14);
		panelPlanning.add(lblDay);
		
		JRadioButton rdbtnM = new JRadioButton("M");
		rdbtnM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_.setSelectedDay(Day.DAY_M);
				updateSearch();
			}
		});
		rdbtnM.setBounds(38, 7, 46, 23);
		panelPlanning.add(rdbtnM);
		rdbtnM.setSelected(true);
		
		JRadioButton rdbtnTu = new JRadioButton("Tu");
		rdbtnTu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_.setSelectedDay(Day.DAY_TU);
				updateSearch();
			}
		});
		rdbtnTu.setBounds(86, 7, 53, 23);
		panelPlanning.add(rdbtnTu);
		
		JRadioButton rdbtnW = new JRadioButton("W");
		rdbtnW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_.setSelectedDay(Day.DAY_W);
				updateSearch();
			}
		});
		rdbtnW.setBounds(141, 7, 53, 23);
		panelPlanning.add(rdbtnW);
		
		JRadioButton rdbtnTh = new JRadioButton("Th");
		rdbtnTh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_.setSelectedDay(Day.DAY_TH);
				updateSearch();
			}
		});
		rdbtnTh.setBounds(196, 7, 53, 23);
		panelPlanning.add(rdbtnTh);
		
		JRadioButton rdbtnF = new JRadioButton("F");
		rdbtnF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_.setSelectedDay(Day.DAY_F);
				updateSearch();
			}
		});
		rdbtnF.setBounds(251, 7, 53, 23);
		panelPlanning.add(rdbtnF);
		
		JRadioButton rdbtnSa = new JRadioButton("Sa");
		rdbtnSa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_.setSelectedDay(Day.DAY_SA);
				updateSearch();
			}
		});
		rdbtnSa.setBounds(306, 7, 53, 23);
		panelPlanning.add(rdbtnSa);
		
		JRadioButton rdbtnSu = new JRadioButton("Su");
		rdbtnSu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_.setSelectedDay(Day.DAY_SU);
				updateSearch();
			}
		});
		rdbtnSu.setBounds(361, 7, 53, 23);
		panelPlanning.add(rdbtnSu);
		
		//Grouper les boutons radios jour
		ButtonGroup dayGroup = new ButtonGroup();
		dayGroup.add(rdbtnM);
		dayGroup.add(rdbtnTu);
		dayGroup.add(rdbtnW);
		dayGroup.add(rdbtnTh);
		dayGroup.add(rdbtnF);
		dayGroup.add(rdbtnSa);
		dayGroup.add(rdbtnSu);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setBounds(10, 36, 46, 14);
		panelPlanning.add(lblTime);
		
		JRadioButton rdbtnDaytime = new JRadioButton("Daytime/After school");
		rdbtnDaytime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_.setSelectedPeriod(Time.DAYTIME);
				updateSearch();
			}
		});
		rdbtnDaytime.setBounds(58, 32, 136, 23);
		panelPlanning.add(rdbtnDaytime);
		rdbtnDaytime.setSelected(true);
		
		JRadioButton rdbtnEvening = new JRadioButton("Evening");
		rdbtnEvening.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_.setSelectedPeriod(Time.EVENING);
				updateSearch();
			}
		});
		rdbtnEvening.setBounds(251, 32, 119, 23);
		panelPlanning.add(rdbtnEvening);
		
		//Grouper les boutons radio période
		ButtonGroup timeGroup = new ButtonGroup();
		timeGroup.add(rdbtnDaytime);
		timeGroup.add(rdbtnEvening);
		
		JLabel lblOthers = new JLabel("Others:");
		lblOthers.setBounds(10, 61, 46, 14);
		panelPlanning.add(lblOthers);
		
		JCheckBox chckbxHoliday = new JCheckBox("Holiday");
		chckbxHoliday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_.setSelectedHoliday(chckbxHoliday.isSelected());
				updateSearch();
			}
		});
		chckbxHoliday.setBounds(58, 57, 81, 23);
		panelPlanning.add(chckbxHoliday);
		
		JCheckBox chckbxRainy = new JCheckBox("Rainy day");
		chckbxRainy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_.setSelectedRainy(chckbxRainy.isSelected());
				updateSearch();
			}
		});
		chckbxRainy.setBounds(141, 57, 108, 23);
		panelPlanning.add(chckbxRainy);
		
		JCheckBox chckbxExams = new JCheckBox("1 week before exams");
		chckbxExams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_.setSelectedExams(chckbxExams.isSelected());
				updateSearch();
			}
		});
		chckbxExams.setBounds(251, 57, 163, 23);
		panelPlanning.add(chckbxExams);
		
		JLabel lblTodaysAvailableSocial = new JLabel("Today's available Social Links:");
		lblTodaysAvailableSocial.setBounds(10, 137, 365, 14);
		panelPlanning.add(lblTodaysAvailableSocial);
		
		hangoutMainBox = new JComboBox();
		hangoutMainBox.setBounds(196, 273, 129, 20);
		panelPlanning.add(hangoutMainBox);
		
		hangoutOtherBox = new JComboBox();
		hangoutOtherBox.setEnabled(false);
		hangoutOtherBox.setBounds(196, 304, 129, 20);
		panelPlanning.add(hangoutOtherBox);
		
		JLabel lblSortBy = new JLabel("Sort by:");
		lblSortBy.setBounds(10, 86, 46, 14);
		panelPlanning.add(lblSortBy);
		
		JRadioButton rdbtnSortRank = new JRadioButton("Lowest Rank");
		rdbtnSortRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_.setSelectedSort(Sort.SORT_RANK);
				updateSearch();
			}
		});
		rdbtnSortRank.setSelected(true);
		rdbtnSortRank.setBounds(58, 82, 109, 23);
		panelPlanning.add(rdbtnSortRank);
		
		JRadioButton rdbtnSortGap = new JRadioButton("Least recently visited");
		rdbtnSortGap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_.setSelectedSort(Sort.SORT_GAP);
				updateSearch();
			}
		});
		rdbtnSortGap.setBounds(196, 82, 163, 23);
		panelPlanning.add(rdbtnSortGap);
		
		JRadioButton rdbtnSortStatus = new JRadioButton("Most likely to Rank-up");
		rdbtnSortStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_.setSelectedSort(Sort.SORT_STATUS);
				updateSearch();
			}
		});
		rdbtnSortStatus.setBounds(58, 107, 191, 23);
		panelPlanning.add(rdbtnSortStatus);
		
		//Group Sort buttons
		ButtonGroup sortGroup = new ButtonGroup();
		sortGroup.add(rdbtnSortRank);
		sortGroup.add(rdbtnSortStatus);
		sortGroup.add(rdbtnSortGap);
		
		JRadioButton rdbtnHangoutAvailable = new JRadioButton("Hang out with available Link: ");
		rdbtnHangoutAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hangoutMainBox.setEnabled(true);
				hangoutOtherBox.setEnabled(false);
			}
		});
		rdbtnHangoutAvailable.setSelected(true);
		rdbtnHangoutAvailable.setBounds(5, 272, 191, 23);
		panelPlanning.add(rdbtnHangoutAvailable);
		
		JRadioButton rdbtnHangoutOther = new JRadioButton("Hang out with other link: ");
		rdbtnHangoutOther.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hangoutMainBox.setEnabled(false);
				hangoutOtherBox.setEnabled(true);
			}
		});
		rdbtnHangoutOther.setBounds(6, 303, 188, 23);
		panelPlanning.add(rdbtnHangoutOther);
		
		//Group hangout buttons
		ButtonGroup hangoutGroup = new ButtonGroup();
		hangoutGroup.add(rdbtnHangoutAvailable);
		hangoutGroup.add(rdbtnHangoutOther);
		
		JButton btnBeginHangout = new JButton("Begin Hangout");
		btnBeginHangout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnHangoutAvailable.isSelected())
					linkHangout_ = list_.getLinkByArcana((String)hangoutMainBox.getSelectedItem());
				else
					linkHangout_ = list_.getLinkByArcana((String)hangoutOtherBox.getSelectedItem());
				
				if(linkHangout_ != null)
				{
					SLHangout hangoutWindow = new SLHangout(getThis());
					hangoutWindow.frame.setVisible(true);
				}
			}
		});
		btnBeginHangout.setBounds(10, 333, 129, 23);
		panelPlanning.add(btnBeginHangout);
		
		panelAvailableLinks = new JTextPane();
		panelAvailableLinks.setBounds(10, 162, 397, 97);

		JScrollPane scrollAvailableLinks = new JScrollPane(panelAvailableLinks,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollAvailableLinks.setBounds(10, 162, 397, 97);
		panelPlanning.add(scrollAvailableLinks);
		
		JPanel panelSLList = new JPanel();
		tabbedPane.addTab("Social Links List", null, panelSLList, null);
		panelSLList.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 38, 46, 14);
		panelSLList.add(lblName);
		
		JLabel lblArcana = new JLabel("Arcana:");
		lblArcana.setBounds(10, 104, 46, 14);
		panelSLList.add(lblArcana);
		
		JLabel lblarcana = new JLabel("[Arcana]");
		lblarcana.setBounds(64, 104, 199, 14);
		panelSLList.add(lblarcana);
		
		JLabel lblRank = new JLabel("Rank:");
		lblRank.setBounds(273, 104, 46, 14);
		panelSLList.add(lblRank);
		
		JLabel lblrank = new JLabel("[Rank]");
		lblrank.setBounds(316, 104, 46, 14);
		panelSLList.add(lblrank);
		
		JTextPane panelAvailableDays = new JTextPane();
		panelAvailableDays.setEditable(false);
		panelAvailableDays.setText("[Available days]");
		panelAvailableDays.setBounds(10, 129, 409, 222);

		JScrollPane scrollAvailableDays = new JScrollPane(panelAvailableDays,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollAvailableDays.setBounds(10, 129, 409, 222);
		panelSLList.add(scrollAvailableDays);
		
		linkEditBox = new JComboBox();
		linkEditBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				linkEdit_ = list_.getLinkByName((String)linkEditBox.getItemAt(linkEditBox.getSelectedIndex()));
				
				if(linkEdit_ == null)
				{
					lblarcana.setText("");
					lblrank.setText("");
					panelAvailableDays.setText("");
				}
				else
				{
					lblarcana.setText(linkEdit_.getArcana());
					
					if(linkEdit_.getRank() < 10)
						lblrank.setText(String.valueOf(linkEdit_.getRank()));
					else
						lblrank.setText("Max");
					
					panelAvailableDays.setText(linkEdit_.diplayEditInfo());
				}
			}
		});
		linkEditBox.setBounds(54, 35, 265, 20);
		panelSLList.add(linkEditBox);
		linkEditBox.addItem("");
		for(byte i = 0; i < list_.getList().size(); i++)
			linkEditBox.addItem(list_.getList().elementAt(i).getName());
		
		JButton btnCreateANew = new JButton("Create a new Social Link");
		btnCreateANew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SLEdit editWindow = new SLEdit(getThis(), true);
				editWindow.frame.setVisible(true);
			}
		});
		btnCreateANew.setBounds(54, 63, 265, 23);
		panelSLList.add(btnCreateANew);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				linkEdit_ = list_.getLinkByName((String)linkEditBox.getItemAt(linkEditBox.getSelectedIndex()));
				if(linkEdit_ != null)
				{
					SLEdit editWindow = new SLEdit(getThis(), false);
					editWindow.frame.setVisible(true);
				}
			}
		});
		btnEdit.setBounds(330, 34, 89, 23);
		panelSLList.add(btnEdit);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_ = new SLList();
				
				rdbtnM.setSelected(true);
				rdbtnDaytime.setSelected(true);
				chckbxHoliday.setSelected(false);
				chckbxRainy.setSelected(false);
				chckbxExams.setSelected(false);
				rdbtnSortRank.setSelected(true);
				
				linkEditBox.removeAllItems();
				linkEditBox.addItem("");
				
				updateSearch();
				frmSocialLinkAgenda.setTitle("Social Link Agenda");
			}
		});
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					JFileChooser fc = new JFileChooser();
					fc.setCurrentDirectory(new java.io.File("."));
					fc.setDialogTitle("Open");
					fc.setFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));
					
					if(fc.showOpenDialog(mntmOpen) == JFileChooser.APPROVE_OPTION)
					{
						list_ = new SLList();
						
						rdbtnM.setSelected(true);
						rdbtnDaytime.setSelected(true);
						chckbxHoliday.setSelected(false);
						chckbxRainy.setSelected(false);
						chckbxExams.setSelected(false);
						rdbtnSortRank.setSelected(true);
						
						linkEditBox.removeAllItems();
						
						list_.openFile(fc.getSelectedFile().getAbsolutePath());
						
						linkEditBox.addItem("");
						for(byte i = 0; i < list_.getList().size(); i++)
							linkEditBox.addItem(list_.getList().elementAt(i).getName());
						
						currentFileName_ = fc.getSelectedFile().getName();
						isFileChanged_ = false;
						updateSearch();
					}
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					panelAvailableLinks.setText("Error: Cannot open file");
				}
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(!list_.getSaveFile().equals(""))
						{
							list_.saveFile();
							isFileChanged_ = false;
							updateSearch();
						}
					else
					{
						JFileChooser fc = new JFileChooser();
						fc.setCurrentDirectory(new java.io.File("."));
						fc.setDialogTitle("Save as");
						fc.setFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));
						if(fc.showOpenDialog(mntmSave) == JFileChooser.APPROVE_OPTION)
						{
							list_.saveFile(fc.getSelectedFile().getAbsolutePath() + ".json");
							currentFileName_ = fc.getSelectedFile().getName();
							isFileChanged_ = false;
							updateSearch();
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					panelAvailableLinks.setText("Error: Cannot save file");
				}
			}
		});
		mnFile.add(mntmSave);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save as");
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new java.io.File("."));
				fc.setDialogTitle("Save as");
				fc.setFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));
				if(fc.showOpenDialog(mntmSaveAs) == JFileChooser.APPROVE_OPTION)
				{
					try {
						list_.saveFile(fc.getSelectedFile().getAbsolutePath() + ".json");
						currentFileName_ = fc.getSelectedFile().getName();
						isFileChanged_ = false;
						updateSearch();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						panelAvailableLinks.setText("Error: Cannot save file");
					}
				}
			}
		});
		mnFile.add(mntmSaveAs);
		
		//updateSearch();
	}
}
