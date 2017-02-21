import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HintWindow {

	public JFrame frmHintSearch;
	
	private P4Agenda agenda_;
	private HintMission mission_;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HintWindow window = new HintWindow(new P4Agenda(), new HintMission(new String()));
					window.frmHintSearch.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HintWindow(P4Agenda agenda, HintMission mission) {
		agenda_ = agenda;
		mission_ = mission;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHintSearch = new JFrame();
		frmHintSearch.setTitle("Hint search");
		frmHintSearch.setBounds(100, 100, 260, 180);
		frmHintSearch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmHintSearch.getContentPane().setLayout(null);
		
		JLabel lblMission = new JLabel("Mission: ");
		lblMission.setBounds(10, 11, 46, 14);
		frmHintSearch.getContentPane().add(lblMission);
		
		JLabel lblMissionName = new JLabel(mission_.getName());
		lblMissionName.setBounds(66, 11, 358, 14);
		frmHintSearch.getContentPane().add(lblMissionName);
		
		JLabel lblDeadline = new JLabel("Deadline:");
		lblDeadline.setBounds(10, 36, 60, 14);
		frmHintSearch.getContentPane().add(lblDeadline);
		
		JLabel lblDeadlineDate = new JLabel(Integer.toString(mission_.getEndMonth()) + "/" + Integer.toString(mission_.getEndDay()));
		lblDeadlineDate.setBounds(76, 36, 148, 14);
		frmHintSearch.getContentPane().add(lblDeadlineDate);
		
		JLabel lblProgress = new JLabel("Progress:");
		lblProgress.setBounds(10, 61, 46, 14);
		frmHintSearch.getContentPane().add(lblProgress);
		
		JCheckBox chckbxComplete = new JCheckBox("Complete");
		chckbxComplete.setBounds(66, 57, 97, 23);
		frmHintSearch.getContentPane().add(chckbxComplete);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mission_.setComplete(chckbxComplete.isSelected());
				agenda_.update(true);
				
				frmHintSearch.dispose();
			}
		});
		btnOk.setBounds(10, 107, 89, 23);
		frmHintSearch.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmHintSearch.dispose();
			}
		});
		btnCancel.setBounds(135, 107, 89, 23);
		frmHintSearch.getContentPane().add(btnCancel);
	}
}
