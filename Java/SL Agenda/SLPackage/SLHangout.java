package SLPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import SLPackage.SLink.SLState;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SLHangout {

	JFrame frame;
	
	private SLAgenda agenda_;
	private SLList list_;
	private SLink link_;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SLHangout window = new SLHangout(new SLAgenda(new SLList()));
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
	public SLHangout(SLAgenda agenda) {
		agenda_ = agenda;
		list_ = agenda_.getList();
		link_ = agenda_.getHangoutLink();
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("[" + link_.getArcana() + "] Hangout Outcome");
		frame.setBounds(100, 100, 431, 267);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblArcana = new JLabel("Arcana:");
		lblArcana.setBounds(10, 11, 46, 14);
		frame.getContentPane().add(lblArcana);
		
		JLabel lblarcana = new JLabel("[Arcana]");
		lblarcana.setBounds(66, 11, 339, 14);
		frame.getContentPane().add(lblarcana);
		lblarcana.setText(link_.getArcana());
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 36, 46, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblname = new JLabel("[Name]");
		lblname.setBounds(66, 36, 339, 14);
		frame.getContentPane().add(lblname);
		lblname.setText(link_.getName());
		
		JLabel lblRank = new JLabel("Rank:");
		lblRank.setBounds(10, 61, 46, 14);
		frame.getContentPane().add(lblRank);
		
		JLabel lblrank = new JLabel("[Rank]");
		lblrank.setBounds(66, 61, 339, 14);
		frame.getContentPane().add(lblrank);
		lblrank.setText(String.valueOf(link_.getRank()));
		
		JLabel lblHangoutOutcome = new JLabel("Hangout outcome:");
		lblHangoutOutcome.setBounds(10, 86, 109, 14);
		frame.getContentPane().add(lblHangoutOutcome);
		
		JRadioButton rdbtnOutcomeNone = new JRadioButton("None specified");
		rdbtnOutcomeNone.setBounds(125, 82, 109, 23);
		frame.getContentPane().add(rdbtnOutcomeNone);
		rdbtnOutcomeNone.setSelected(link_.getState().equals(SLState.NONE));
		
		JRadioButton rdbtnOutcomeStronger = new JRadioButton("Relationship is getting stronger");
		rdbtnOutcomeStronger.setBounds(125, 107, 270, 23);
		frame.getContentPane().add(rdbtnOutcomeStronger);
		rdbtnOutcomeStronger.setSelected(link_.getState().equals(SLState.STRONGER));
		
		JRadioButton rdbtnOutcomeSoon = new JRadioButton("Relationship will be getting closer soon");
		rdbtnOutcomeSoon.setBounds(125, 133, 270, 23);
		frame.getContentPane().add(rdbtnOutcomeSoon);
		rdbtnOutcomeSoon.setSelected(link_.getState().equals(SLState.SOON));
		
		JRadioButton rdbtnOutcomeRankup = new JRadioButton("Rank-up");
		rdbtnOutcomeRankup.setBounds(125, 159, 109, 23);
		frame.getContentPane().add(rdbtnOutcomeRankup);
		
		ButtonGroup outcomeGroup = new ButtonGroup();
		outcomeGroup.add(rdbtnOutcomeNone);
		outcomeGroup.add(rdbtnOutcomeStronger);
		outcomeGroup.add(rdbtnOutcomeSoon);
		outcomeGroup.add(rdbtnOutcomeRankup);
		
		JButton btnValidate = new JButton("Validate");
		btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnOutcomeNone.isSelected())
					list_.hangoutUpdate(link_,SLState.NONE);
				else if(rdbtnOutcomeStronger.isSelected())
					list_.hangoutUpdate(link_,SLState.STRONGER);
				else if(rdbtnOutcomeSoon.isSelected())
					list_.hangoutUpdate(link_,SLState.SOON);
				else if(rdbtnOutcomeRankup.isSelected())
					list_.hangoutUpdate(link_,SLState.RANKUP);
				
				agenda_.setFileChanged(true);
				agenda_.updateSearch();
				
				frame.dispose();
			}
		});
		btnValidate.setBounds(118, 194, 89, 23);
		frame.getContentPane().add(btnValidate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(217, 194, 89, 23);
		frame.getContentPane().add(btnCancel);
	}
}
