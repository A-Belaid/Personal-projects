import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SLWindow {

	public JFrame frmSocialLinkHangout;
	
	private P4Agenda agenda_;
	private SLink link_;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SLWindow window = new SLWindow(new P4Agenda(), new SLink(new String()));
					window.frmSocialLinkHangout.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SLWindow(P4Agenda agenda, SLink link) {
		agenda_ = agenda;
		link_ = link;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSocialLinkHangout = new JFrame();
		frmSocialLinkHangout.setTitle("[" + link_.getArcana() + "] Social Link Hangout outcome");
		frmSocialLinkHangout.setBounds(100, 100, 450, 215);
		frmSocialLinkHangout.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSocialLinkHangout.getContentPane().setLayout(null);
		
		JLabel lblArcana = new JLabel("Arcana:");
		lblArcana.setBounds(10, 11, 46, 14);
		frmSocialLinkHangout.getContentPane().add(lblArcana);
		
		JLabel lblArcanaName = new JLabel(link_.getArcana());
		lblArcanaName.setBounds(66, 11, 109, 14);
		frmSocialLinkHangout.getContentPane().add(lblArcanaName);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 36, 46, 14);
		frmSocialLinkHangout.getContentPane().add(lblName);
		
		JLabel lblLinkName = new JLabel(link_.getName());
		lblLinkName.setBounds(66, 36, 109, 14);
		frmSocialLinkHangout.getContentPane().add(lblLinkName);
		
		JLabel lblRank = new JLabel("Rank:");
		lblRank.setBounds(10, 61, 46, 14);
		frmSocialLinkHangout.getContentPane().add(lblRank);
		
		JLabel lblLinkRank = new JLabel(Integer.toString(link_.getRank()));
		lblLinkRank.setBounds(66, 61, 46, 14);
		frmSocialLinkHangout.getContentPane().add(lblLinkRank);
		
		JLabel lblOutcome = new JLabel("Outcome:");
		lblOutcome.setBounds(10, 86, 65, 14);
		frmSocialLinkHangout.getContentPane().add(lblOutcome);
		
		JRadioButton rdbtnNoProgress = new JRadioButton("No progress");
		rdbtnNoProgress.setSelected(true);
		rdbtnNoProgress.setBounds(81, 82, 109, 23);
		frmSocialLinkHangout.getContentPane().add(rdbtnNoProgress);
		
		JRadioButton rdbtnRankUp = new JRadioButton("Rank Up");
		rdbtnRankUp.setBounds(81, 108, 109, 23);
		frmSocialLinkHangout.getContentPane().add(rdbtnRankUp);
		
		ButtonGroup outcomeGroup = new ButtonGroup();
		outcomeGroup.add(rdbtnNoProgress);
		outcomeGroup.add(rdbtnRankUp);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agenda_.getProfile().getSLList().hangout(link_.getArcana(), rdbtnRankUp.isSelected());
				agenda_.update(true);
				
				frmSocialLinkHangout.dispose();
			}
		});
		btnOk.setBounds(66, 142, 89, 23);
		frmSocialLinkHangout.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmSocialLinkHangout.dispose();
			}
		});
		btnCancel.setBounds(260, 142, 89, 23);
		frmSocialLinkHangout.getContentPane().add(btnCancel);
	}
}
