import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TVWindow {

	public JFrame frmTvExploration;
	
	private P4Agenda agenda_;
	private TVMission mission_;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TVWindow window = new TVWindow(new P4Agenda(), new TVMission(new String(), new Integer(0)));
					window.frmTvExploration.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TVWindow(P4Agenda agenda, TVMission mission) {
		agenda_ = agenda;
		mission_ = mission;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTvExploration = new JFrame();
		frmTvExploration.setTitle("TV Exploration");
		frmTvExploration.setBounds(100, 100, 350, 220);
		frmTvExploration.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTvExploration.getContentPane().setLayout(null);
		
		JLabel lblDungeonName = new JLabel("Dungeon name:");
		lblDungeonName.setBounds(10, 11, 100, 14);
		frmTvExploration.getContentPane().add(lblDungeonName);
		
		JLabel lblTVName = new JLabel(mission_.getTVName());
		lblTVName.setBounds(120, 11, 304, 14);
		frmTvExploration.getContentPane().add(lblTVName);
		
		JLabel lblDeadline = new JLabel("Deadline:");
		lblDeadline.setBounds(10, 36, 100, 14);
		frmTvExploration.getContentPane().add(lblDeadline);
		
		JLabel lblDeadlineM = new JLabel(Integer.toString(mission_.getEndMonth()) + "/" + Integer.toString(mission_.getEndDay()));
		lblDeadlineM.setBounds(115, 36, 163, 14);
		frmTvExploration.getContentPane().add(lblDeadlineM);
		
		JLabel lblProgress = new JLabel("Progress:");
		lblProgress.setBounds(10, 61, 100, 14);
		frmTvExploration.getContentPane().add(lblProgress);
		
		JCheckBox chckbxCompleted = new JCheckBox("Completed");
		chckbxCompleted.setEnabled(false);
		chckbxCompleted.setBounds(120, 110, 97, 23);
		frmTvExploration.getContentPane().add(chckbxCompleted);
		
		JLabel lblFloor = new JLabel("Floor");
		lblFloor.setBounds(59, 86, 46, 14);
		frmTvExploration.getContentPane().add(lblFloor);
		
		JComboBox floorBox = new JComboBox();
		floorBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chckbxCompleted.setEnabled(floorBox.getSelectedIndex() + 1 == mission_.getNumberFloors()
						&& !mission_.getTVName().equals("Free exploration"));
			}
		});
		floorBox.setBounds(120, 83, 46, 20);
		frmTvExploration.getContentPane().add(floorBox);
		for(int i = 1; i <= mission_.getNumberFloors(); i++)
			floorBox.addItem(Integer.toString(i));
		floorBox.setSelectedIndex(mission_.getCurrentFloor() - 1);
		
		JLabel lblNoFloors = new JLabel("/ " + Integer.toString(mission_.getNumberFloors()));
		lblNoFloors.setBounds(176, 86, 46, 14);
		frmTvExploration.getContentPane().add(lblNoFloors);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mission_.setCurrentFloors(floorBox.getSelectedIndex() + 1);
				mission_.setComplete(chckbxCompleted.isEnabled() && chckbxCompleted.isSelected());
				
				agenda_.getDay().setWentToTV(true);
				agenda_.update(true);
				
				frmTvExploration.dispose();
			}
		});
		btnOk.setBounds(59, 140, 89, 23);
		frmTvExploration.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmTvExploration.dispose();
			}
		});
		btnCancel.setBounds(176, 140, 89, 23);
		frmTvExploration.getContentPane().add(btnCancel);
	}
}
